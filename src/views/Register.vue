<template>
  <b-form @submit="register">
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
              <span style="font-weight: 500">TREE WORLD</span>
            </h4>
          </div>
          <div class="modal-dialog w-100 mx-auto">
            <div class="modal-content">
              <div class="modal-body">
                <h4 class="mt-2 text-center">
                  <span v-if="step !== 1" class="back-button" @click="back">
                    <font-awesome-icon class="back-icon" icon="arrow-left" />
                  </span>
                  <span style="font-weight: 500">Đăng ký tài khoản</span>
                </h4>
                <div v-if="step === 1">
                  <b-form-group
                    id="exampleInputGroup1"
                    label-for="exampleInput1"
                  >
                    <b-form-input
                      id="exampleInput1"
                      v-model="$v.form.username.$model"
                      type="text"
                      required
                      placeholder="Tên đăng nhập"
                      :class="{
                        'is-invalid': validationStatus($v.form.username),
                      }"
                    >
                    </b-form-input>
                    <div
                      v-if="!$v.form.username.required"
                      class="invalid-feedback"
                    >
                      Tên đăng nhập không được để trống.
                    </div>
                  </b-form-group>
                  <b-form-group
                    id="exampleInputGroup2"
                    label-for="exampleInput2"
                  >
                    <b-form-input
                      id="exampleInput2"
                      v-model="$v.form.password.$model"
                      type="password"
                      required
                      placeholder="Mật khẩu"
                      :class="{
                        'is-invalid': validationStatus($v.form.password),
                      }"
                    >
                    </b-form-input>
                    <div
                      v-if="!$v.form.password.required"
                      class="invalid-feedback"
                    >
                      Mật khẩu không được để trống.
                    </div>
                  </b-form-group>
                  <b-form-group
                    id="exampleInputGroup3"
                    label-for="exampleInput3"
                  >
                    <b-form-input
                      id="exampleInput3"
                      v-model="form.rePassword"
                      type="password"
                      required
                      placeholder="Nhập lại mật khẩu"
                      :class="{
                        'is-invalid':
                          ($v.form.password.required &&
                            (!form.rePassword ||
                              form.rePassword.trim() === '')) ||
                          ($v.form.password.required &&
                            form.rePassword &&
                            form.rePassword.trim() !== form.password.trim()),
                      }"
                    >
                    </b-form-input>
                    <div
                      v-if="
                        $v.form.password.required &&
                          (!form.rePassword || form.rePassword.trim() === '')
                      "
                      class="invalid-feedback"
                    >
                      Mật khẩu nhập lại không được để trống.
                    </div>
                    <div
                      v-if="
                        $v.form.password.required &&
                          form.rePassword &&
                          form.rePassword.trim() !== form.password.trim()
                      "
                      class="invalid-feedback"
                    >
                      Mật khẩu nhập lại không trùng khớp.
                    </div>
                  </b-form-group>
                  <b-form-group
                    id="exampleInputGroup4"
                    label-for="exampleInput4"
                  >
                    <b-form-input
                      id="exampleInput4"
                      v-model="$v.form.email.$model"
                      type="email"
                      required
                      placeholder="Nhập email"
                      :class="{ 'is-invalid': validationStatus($v.form.email) }"
                    >
                    </b-form-input>
                    <div
                      v-if="!$v.form.email.required"
                      class="invalid-feedback"
                    >
                      Địa chỉ email không được để trống.
                    </div>
                    <div
                      v-if="!$v.form.email.validEmail"
                      class="invalid-feedback"
                    >
                      Địa chỉ email không hợp lệ.
                    </div>
                  </b-form-group>
                </div>
              </div>
              <div
                class="modal-footer clearfix justify-content-center flex-column"
              >
                <div class="w-100">
                  <b-button
                    type="submit"
                    size="lg"
                    class="button-login"
                    block
                    style="height: 40px"
                    @click.prevent="submit"
                    :disabled="loadingButton"
                    >{{ step !== 1 ? "Tiếp tục" : "Đăng ký" }}
                  </b-button>
                </div>
                <div class="w-100 p-2 text-center">
                  <div
                    @click="navigateToLogin"
                    class="text-success"
                    style="cursor: pointer"
                  >
                    Đã có tài khoản? Đăng nhập tại đây
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="text-center opacity-8 mt-3">
            Copyright &copy; Tree World {{ new Date().getFullYear() }}
          </div>
        </div>

        <!-- <div class="right-content"></div> -->
      </div>
    </div>
  </b-form>
</template>

<script>
import { getAuthenticatedUser } from "../common/utils";
import axios from "axios";
import StorageService from "../common/storage.service";
import router from "../router/index";
import { EventBus } from "@/common/event-bus";
import Configuration from "@/configuration";
import baseMixins from "@/components/mixins/base";
import { required, helpers } from "vuelidate/lib/validators";
import { REGISTER } from "../store/action.type";
const API_ENDPOINT = Configuration.value("baseURL");
const validEmail = helpers.regex("validEmail", /^\S+@\S+\.\S+$/);
const initForm = {
  username: null,
  password: null,
  rePassword: null,
  email: null,
};
export default {
  data() {
    return {
      form: Object.assign({}, { ...initForm }),
      loadingButton: false,
      requestId: null,
      timer: 60000,
      durationOTP: 180,
      isFinishTimer: false,
      isFinishTimerOTP: false,
      needRegisterSmartOtp: false,
      step: 1,
    };
  },
  components: {},
  mixins: [baseMixins],
  validations: {
    form: {
      username: {
        required,
      },
      password: {
        required,
      },
      email: {
        required,
        validEmail,
      },
    },
  },
  mounted() {},
  methods: {
    handleCountdownProgress(data) {
      if (data.seconds !== 1) return;
      setTimeout(() => {
        this.isFinishTimer = true;
      }, 1000);
    },
    validationStatus: function(validation) {
      return typeof validation != "undefined" ? validation.$error : false;
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
          this.register();
          break;
        default:
          break;
      }
    },
    // async handleAfterRegister(response) {
    //   let res = await this.$store.dispatch("login", {
    //     username: this.form.username,
    //     password: this.form.password,
    //   });
    //   if (res && res.status === 200) {
    //     this.loadingButton = false;
    //     StorageService.save("Token", res.data.token);
    //     StorageService.save("userInfo", JSON.stringify(res.data));
    //     await getAuthenticatedUser(res.data);
    //     this.$message.closeAll();
    //     const isAdmin =
    //       StorageService.get("userInfo") &&
    //       JSON.parse(StorageService.get("userInfo")).role === "[ADMIN]";
    //     // const savedPath = localStorage.getItem('savedPath');
    //     router.push(isAdmin ? "/admin" : "/").catch((e) => {
    //       this.$message({
    //         message: "Đã có lỗi xảy ra",
    //         type: "error",
    //         showClose: true,
    //       });
    //     });
    //   }
    // },
    async register() {
      this.$v.$reset();
      this.$v.$touch();
      if (this.$v.form.$invalid) {
        return;
      }
      this.loadingButton = true;
      EventBus.$emit("send-progress", true);
      let { email, username, password } = { ...this.form };
      let response = await this.$store.dispatch(REGISTER, {
        email,
        username,
        password,
      });
      if (response) {
        EventBus.$emit("close-progress", true);
        this.loadingButton = false;
      }
      if (
        response &&
        response.data.status === "ok" &&
        response.data.success == true
      ) {
        this.navigateToLogin();
      } else {
        this.$message({
          message: response.data.message,
          type: "warning",
          showClose: true,
        });
      }
    },
    navigateToLogin() {
      router.push({ path: "/login" });
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
