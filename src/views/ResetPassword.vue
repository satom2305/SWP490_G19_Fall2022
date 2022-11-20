<template>
	<b-form @submit="sendEmailResetPassword">
		<div class="h-100 bg-animation">
			<div class="d-flex h-100 justify-content-center align-items-center">
				<div class="mx-auto app-login-box">
					<div class="h5 modal-title text-center">
						<div>
							<img src="@/assets/static/images/logo.png" class="brand-img" width="100" />
						</div>
						<h4 class="mt-2">
							<span style="font-weight: 500">HỆ THỐNG QUẢN LÝ TREE WORLD</span>
						</h4>
					</div>
					<div class="modal-dialog w-100 mx-auto">
						<div class="modal-content">
							<div class="modal-body">
								<h4 class="mt-2 text-center">
									<span style="font-weight: 500">Thay đổi mật khẩu mới</span>
								</h4>
								<div v-if="step === INPUT_USERNAME">
									<b-form-group id="exampleInputGroup4" label-for="exampleInput4">
										<b-form-input id="exampleInput4" v-model="$v.form.username.$model" type="text" required
											placeholder="Nhập tên đăng nhập tại đây"
											:class="{ 'is-invalid': validationStatus($v.form.username) }">
										</b-form-input>
										<div v-if="!$v.form.username.required" class="invalid-feedback">
											Tên đăng nhập không được để trống.
										</div>
									</b-form-group>
								</div>
								<div>
									<span v-if="step === INPUT_USERNAME">
										Hệ thống sẽ tự động gửi mật khẩu mới tới email bạn dùng để đăng ký tài khoản Tree World
									</span>
									<span v-if="step === SEND_EMAIL_SUCCESS">
										Thay đổi mật khẩu mới thành công. Vui lòng kiểm tra email của bạn.
									</span>
								</div>
							</div>
							<div class="modal-footer clearfix justify-content-center flex-column">
								<div class="w-100" v-if="step === INPUT_USERNAME">
									<b-button type="submit" size="lg" class="button-login" block style="height: 40px"
										@click.prevent="sendEmailResetPassword" :disabled="loadingButton">{{ step !== 1 ? 'Tiếp tục' :
												'Xác nhận'
										}}
									</b-button>
								</div>
								<div class="w-100 p-2 text-center">
								<div @click="navigateToLogin" class="text-success" style="cursor: pointer">Quay lại trang đăng nhập</div>
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
import {
	getAuthenticatedUser
} from "../common/utils";
import axios from "axios";
import StorageService from "../common/storage.service";
import router from "../router/index";
import { EventBus } from "@/common/event-bus";
import Configuration from "@/configuration";
import baseMixins from "@/components/mixins/base";
import { required, helpers } from "vuelidate/lib/validators";
import { SEND_EMAIL_RESET_PASSWORD } from "../store/action.type";
const API_ENDPOINT = Configuration.value("baseURL");
const validEmail = helpers.regex('validEmail', /^\S+@\S+\.\S+$/)
const initForm = {
	username: null
}
const INPUT_USERNAME = 'input_username'
const SEND_EMAIL_SUCCESS = 'send_email_success'
export default {
	data() {
		return {
			form: Object.assign({}, { ...initForm }),
			loadingButton: false,
			requestId: null,
			timer: 60000,
			durationOTP: 180,
			INPUT_USERNAME,
			SEND_EMAIL_SUCCESS,
			isFinishTimer: false,
			isFinishTimerOTP: false,
			needRegisterSmartOtp: false,
			step: INPUT_USERNAME,
		};
	},
	components: {},
	mixins: [baseMixins],
	validations: {
		form: {
			username: {
				required,
			},
		},
	},
	mounted() {
	},
	methods: {
		handleCountdownProgress(data) {
			if (data.seconds !== 1) return;
			setTimeout(() => {
				this.isFinishTimer = true;
			}, 1000);
		},
		validationStatus: function (validation) {
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
					this.sendEmail()
					break;
				default:
					break;
			}
		},
		async handleAfterResetPassword(response) {
			
		},
		async sendEmailResetPassword() {
			this.$v.$reset();
			this.$v.$touch();
			if (this.$v.form.$invalid) {
				return;
			}
			this.loadingButton = true;
			EventBus.$emit("send-progress", true);
			let { username } = { ...this.form }
			let response = await this.$store.dispatch(SEND_EMAIL_RESET_PASSWORD, {
				username
			})
			if (response) {
				EventBus.$emit("close-progress", true);
				this.loadingButton = false;
			}
			if (response && response.status === 200 && response.data.success) {
				this.step = this.SEND_EMAIL_SUCCESS
			} else {
				this.$message({
					message: "Có lỗi xảy ra",
					type: "warning",
					showClose: true,
				});
			}
		},
		navigateToLogin() {
			router.push({ path: '/login' })
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