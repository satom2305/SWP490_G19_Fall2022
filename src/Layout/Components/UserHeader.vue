<template>
  <header class="header">
    <div class="header__top">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-md-6">
            <div class="header__top__left">
              <ul>
                <li>
                  <font-awesome-icon icon="fa fa-envelope" />
                  treeworld@gmail.com
                </li>
                <li>Giao hàng tận nơi</li>
              </ul>
            </div>
          </div>
          <div class="col-lg-6 col-md-6">
            <div class="header__top__right">
              <div class="header__top__right__auth">
                <div class="d-flex align-items-center">
                  <template v-if="userInfo && userInfo.username">
                    <a-dropdown>
                      <a class="ant-dropdown-link" @click.prevent>
                        <div class="ml-4">
                          <a-avatar
                            src="https://ps.w.org/simple-user-avatar/assets/icon-256x256.png?rev=2413146"
                            alt="avatar"
                          />
                          <span class="ml-3">{{ userInfo.username }}</span>
                        </div>
                      </a>
                      <template #overlay>
                        <a-menu>
                          <a-menu-item
                            v-if="userInfo && userInfo.role === '[ADMIN]'"
                          >
                            <template>
                              <div @click="navigateToAdmin">Trang quản lý</div>
                            </template>
                          </a-menu-item>
                          <a-menu-item>
                            <div
                              @click="openModalUpdatePassword"
                              v-if="
                                userInfo &&
                                  userInfo &&
                                  userInfo.role !== '[GUEST]'
                              "
                            >
                              Cập nhật mật khẩu
                            </div>
                          </a-menu-item>
                          <a-menu-item>
                            <div @click="navigateToLogin">Đăng xuất</div>
                          </a-menu-item>
                        </a-menu>
                      </template>
                    </a-dropdown>
                  </template>
                  <template v-else>
                    <a href="/register" class="mr-2">
                      <font-awesome-icon icon="fa fa-user" /> Register
                    </a>
                    <a href="/login">
                      <font-awesome-icon icon="fa fa-user" /> Login
                    </a>
                  </template>
                </div>
              </div>
              <b-modal
                hide-footer
                id="update-password"
                :title="'Cập nhật mật khẩu'"
                :no-close-on-backdrop="true"
                @hide="cancelUpdatePassword()"
              >
                <b-form-group id="exampleInputGroup4" label-for="exampleInput4">
                  <b-form-input
                    id="exampleInput4"
                    v-model="form.oldPassword"
                    type="password"
                    required
                    placeholder="Nhập lại mật khẩu cũ"
                    :class="{
                      'is-invalid':
                        ($v.form.password.required &&
                          (!form.oldPassword ||
                            form.oldPassword.trim() === '')) ||
                        ($v.form.password.required &&
                          form.oldPassword &&
                          form.oldPassword.trim() !== form.password.trim()),
                    }"
                  >
                  </b-form-input>
                  <div
                    v-if="
                      $v.form.password.required &&
                        (!form.oldPassword || form.oldPassword.trim() === '')
                    "
                    class="invalid-feedback"
                  >
                    Mật khẩu không được để trống.
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
                <b-form-group id="exampleInputGroup2" label-for="exampleInput2">
                  <b-form-input
                    id="exampleInput2"
                    v-model="$v.form.password.$model"
                    type="password"
                    required
                    placeholder="Mật khẩu mới"
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
                  <div
                    v-if="$v.form.password == form.oldPassword"
                    class="invalid-feedback"
                  >
                    Mật khẩu không được trùng với mật khẩu cũ.
                  </div>
                  <div
                    v-if="!$v.form.password.minLength"
                    class="invalid-feedback"
                  >
                    Mật khẩu không được ít hơn 6 kí tự.
                  </div>
                </b-form-group>
                <b-form-group id="exampleInputGroup3" label-for="exampleInput3">
                  <b-form-input
                    id="exampleInput3"
                    v-model="form.rePassword"
                    type="password"
                    required
                    placeholder="Nhập lại mật khẩu mới"
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
                <b-button
                  class="mr-2 btn-light2 pull-right"
                  @click="cancelUpdatePassword()"
                >
                  Hủy
                </b-button>
                <b-button
                  :disabled="loadingButton"
                  variant="primary pull-right"
                  class="mr-2"
                  type="submit"
                  @click="handleUpdatePassword()"
                >
                  Đồng ý
                </b-button>
              </b-modal>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-lg-3">
          <div class="header__logo">
            <a href="/"><img src="@/assets/static/images/logo.png" alt=""/></a>
          </div>
        </div>
        <div class="col-lg-6">
          <nav class="header__menu">
            <ul>
              <li class="active">
                <a href="/">Trang chủ</a>
              </li>
              <li>
                <a href="/shop-product">Sản phẩm</a>
              </li>
              <li>
                <a href="#">Chức năng</a>
                <ul class="header__menu__dropdown">
                  <li>
                    <a href="/cart">Giỏ hàng</a>
                  </li>
                  <li>
                    <a href="/my-order">Đơn hàng của tôi</a>
                  </li>
                  <li>
                    <a href="/contact">Liên hệ </a>
                  </li>
                </ul>
              </li>
              <li>
                <a href="/blog">Tin tức</a>
              </li>
            </ul>
          </nav>
        </div>
        <div class="col-lg-3">
          <div class="header__cart">
            <ul>
              <!-- <li>
                  <a href="#">
                    <font-awesome-icon icon="fa fa-heart" /> <span>1</span>
                  </a>
                </li> -->
              <li>
                <a href="/cart">
                  <font-awesome-icon icon="fa fa-shopping-bag" />
                  <!-- <span>3</span> -->
                </a>
              </li>
            </ul>
            <!-- <div class="header__cart__price">item: <span>$150.00</span></div> -->
          </div>
        </div>
      </div>
      <div class="humberger__open">
        <font-awesome-icon icon="fa fa-bars" />
      </div>
    </div>
  </header>
</template>

<script>
import router from "@/router/index";
import { EventBus } from "@/common/event-bus";
import Configuration from "@/configuration";
import baseMixins from "@/components/mixins/base";
import { required, helpers, minLength } from "vuelidate/lib/validators";
import { CHANGE_PASSWORD } from "@/store/action.type";

const API_ENDPOINT = Configuration.value("baseURL");

const initForm = {
  password: null,
};

export default {
  data() {
    return {
      form: Object.assign({}, { ...initForm }),
      loadingButton: false,
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
    };
  },
  components: {},
  mixins: [baseMixins],
  validations: {
    form: {
      password: {
        required,
        minLength: minLength(6),
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
    openModalUpdatePassword() {
      this.$root.$emit("bv::show::modal", "update-password");
    },
    cancelUpdatePassword() {
      this.$root.$emit("bv::hide::modal", "update-password");
      this.form.password = "";
      this.form.oldPassword = "";
      this.form.rePassword = "";
    },
    async handleUpdatePassword() {
      if (!this.userInfo || !this.userInfo.username) {
        this.$message.closeAll();
        this.$message({
          message: "Thông tin tài khoản không hợp lệ. Vui lòng đăng nhập lại",
          type: "warning",
          showClose: true,
        });
        return;
      }

      this.$v.$reset();
      this.$v.$touch();

      if (this.$v.form.$invalid) {
        return;
      }

      this.loadingButton = true;
      EventBus.$emit("send-progress", true);
      let payload = {
        data: {
          password: this.form.password,
        },
        username: this.userInfo.username,
      };
      let response = await this.$store.dispatch(CHANGE_PASSWORD, payload);
      if (response) {
        EventBus.$emit("close-progress", true);
        this.loadingButton = false;
        this.$message.closeAll();
      }

      if (response && response.status === 200 && response.data.success) {
        this.$v.$reset();
        this.cancelUpdatePassword();
        this.$message({
          message:
            "Cập nhật mật khẩu thành công. Hệ thống sẽ quay về trang đăng nhập trong giây lát.",
          type: "success",
          showClose: true,
        });
        setTimeout(() => {
          this.navigateToLogin();
        }, 3000);
      } else {
        this.$message({
          message: "Có lỗi xảy ra",
          type: "warning",
          showClose: true,
        });
      }
    },
    // async checkoldPassword(){
    //   let response = await this.$store.dispatch(CHANGE_PASSWORD, payload);
    //   if(res){

    //   }
    // },
    navigateToLogin() {
      router.push({ path: "/login" });
    },
    navigateToAdmin() {
      router.push({ path: "/admin" });
    },
  },
};
</script>

<style></style>
