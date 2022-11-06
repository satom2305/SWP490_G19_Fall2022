<template>
  <b-form @submit="login">
    <div class="h-100 bg-animation">
      <div class="d-flex h-100 justify-content-center align-items-center">
        <div class="mx-auto app-login-box">
          <div class="h5 modal-title text-center">
            <div>
              <img
                  src="@/assets/static/images/logo.png"
                  class="brand-img"
                  width="100"
              />
            </div>
            <h4 class="mt-2">
              <span style="font-weight: 500">HỆ THỐNG QUẢN LÝ TREE WORLD</span>
            </h4>
          </div>
          <div class="modal-dialog w-100 mx-auto">
            <div class="modal-content">
              <div class="modal-body">
                <h4 class="mt-2 text-center">
                  <span v-if="step !== 1" class="back-button" @click="back">
                    <font-awesome-icon class="back-icon" icon="arrow-left"/>
                  </span>
                  <span style="font-weight: 500">Đăng nhập</span>
                </h4>
                <div v-if="step === 1">
                  <b-form-group id="exampleInputGroup1" label-for="exampleInput1">
                    <b-form-input
                        id="exampleInput1"
                        v-model="form.username"
                        type="text"
                        required
                        placeholder="Tên đăng nhập"
                    >
                    </b-form-input>
                  </b-form-group>
                  <b-form-group id="exampleInputGroup2" label-for="exampleInput2">
                    <b-form-input
                        id="exampleInput2"
                        v-model="form.password"
                        type="password"
                        required
                        placeholder="Mật khẩu"
                    >
                    </b-form-input>
                  </b-form-group>
                </div>
              </div>
              <div class="modal-footer clearfix justify-content-center">
                <div class="w-100">
                  <b-button
                    type="submit"
                    size="lg"
                    class="button-login"
                    block
                    style="height: 40px"
                    @click.prevent="submit"
                    :disabled="loadingButton"
                  >{{ step !== 1 ? 'Tiếp tục' : 'Đăng nhập' }}
                  </b-button>
                </div>
              </div>
            </div>
          </div>
          <div class="text-center opacity-8 mt-3">
            Copyright &copy; Tree World {{ new Date().getFullYear() }}
          </div>
        </div>

        <div class="right-content"></div>
      </div>
    </div>
  </b-form>
</template>

<script>
import {
  getAuthenticatedUser
} from "../common/utils";
import axios from "axios";
import StorageService from "../common/storage.service";
import router from "../router/index";
import {EventBus} from "@/common/event-bus";
import Configuration from "@/configuration";
import baseMixins from "@/components/mixins/base";

const API_ENDPOINT = Configuration.value("baseURL");

export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      loadingButton: false,
      requestId: null,
      timer: 60000,
      durationOTP: 180,
      isFinishTimer: false,
      isFinishTimerOTP: false,
      needRegisterSmartOtp: false,
      step: 1,
      dataQr: {
        value: null,
        size: 150
      },
    };
  },
  components: {},
  mixins: [baseMixins],
  mounted() {
    setInterval(() => {
      if (this.step !== 2) return;

      if (this.durationOTP === 0) {
        this.isFinishTimerOTP = true;
        return;
      }
      this.durationOTP -= 1;
    }, 1000)
  },
  methods: {
    handleCountdownProgress(data) {
      if (data.seconds !== 1) return;

      setTimeout(() => {
        this.isFinishTimer = true;
      }, 1000);
    },
    back() {
      this.step = this.needRegisterSmartOtp ? this.step - 1 : this.step - 2;
      this.timer = 60000;
      this.durationOTP = 180;
      this.isFinishTimerOTP = false;
      this.isFinishTimer = false;
    },
    submit() {
      switch (this.step) {
        case 1:
          this.login()
          break;
        default:
          break;
      }
    },
    async handleAfterLogin(response) {
      this.loadingButton = false;
      StorageService.save("Token", response.data.token);
      StorageService.save("userInfo", JSON.stringify(response.data));
      await getAuthenticatedUser(response.data);
      this.$message.closeAll();
      
      const isAdmin = StorageService.get("userInfo") && JSON.parse(StorageService.get("userInfo")).role === '[ADMIN]'
      console.log(StorageService.get("userInfo"))
      // const savedPath = localStorage.getItem('savedPath');
      router.push(isAdmin ? '/admin' : "/")
        .catch((e) => {
          this.$message({
            message: 'Đã có lỗi xảy ra',
            type: "warning",
            showClose: true,
          });
        });
    },
    login() {
      this.loadingButton = true;
      EventBus.$emit("send-progress", true);
      axios
        .post(`${API_ENDPOINT}/login`, this.form)
        .then(async (response) => {
          EventBus.$emit("close-progress", true);
          if (response) {
            this.loadingButton = false;
          }

          if (response.status === 200) {
            this.handleAfterLogin(response)
          }
        })
        .catch((error) => {
          EventBus.$emit("close-progress", true);
          this.loadingButton = false;
          if (error.response && error.response.data) {
            this.$message({
              message: error.response.data.message,
              type: "warning",
              showClose: true,
            });
          } else {
            this.$message({
              message: "Có lỗi xảy ra",
              type: "warning",
              showClose: true,
            });
          }
        });
    },
  },
};
</script>


<style lang="scss" scoped>
.button-login {
  color: #fff;
  background-color: #069255;
  border-color: #069255;
}

@media only screen and (max-width: 1024px) {
  .right-content {
    display: none;
  }

  .brand-img {
    width: 100px;
  }
}

@media only screen and (max-width: 1366px) {
  .brand-img {
    width: 100px;
  }
  .modal-dialog {
    margin: 0 auto;
  }
  .app-login-box h4 {
    margin-bottom: 5px;
  }
  .input-otp {
    height: 80px;
  }
}

@media only screen and (max-width: 768px) {
  .brand-img {
    width: 100px;
  }
}

.back-button {
  position: absolute;
  top: 30px;
  left: 18px;
}

.back-icon {
  font-weight: normal;
  color: #069255;
  cursor: pointer;
}

.input-otp {
  height: 100px;
}

</style>
