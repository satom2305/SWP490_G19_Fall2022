<template>
	<div>
		<page-title :heading="heading" :subheading="subheading" :loading="loadingHeader"></page-title>
		<b-card id="update-product" class="main-card search-wrapper mb-20">
			<template v-if="loadingHeader">
				<a-skeleton active :paragraph="{ rows: 5 }"></a-skeleton>
			</template>
			<template v-else>
				<b-row>
					<b-col xl="4">
						<div class="banner-image__wrapper">
							<b-form-group>
								<label>Ảnh banner:</label>
								<div class="custom-banner-image banner-image__preview mb-2" :style="getUrlImage
									? { 'background-image': `url(${currentData.mainImg})` }
									: null
								">
									<div class="overlay" v-if="currentData.mainImg">
										<button class="banner-image__btn" @click="showImageSelect">
											<i class="fas fa-eye"></i>
										</button>
										<button class="banner-image__btn" @click="removeImageSelect">
											<i class="fas fa-trash-alt"></i>
										</button>
									</div>
								</div>
								<b-form-group v-if="
									!disabledUpdateDisabledProduct
								">
									<b-form-input id="input-file" type="text" v-model="currentData.mainImg" placeholder="Nhập link ảnh">
									</b-form-input>
								</b-form-group>
							</b-form-group>
						</div>
					</b-col>
					<b-col xl="8">
						<div>
							<b-row>
								<!-- <b-col md="4">
                <b-form-group>
                  <label>Thứ tự <span class="text-danger">*</span>:</label>
                  <b-form-input
                    id="input-order"
                    type="number"
                    v-model.trim="$v.currentData.order.$model"
                    placeholder="Nhập số thứ tự"
                    :class="{
                      'is-invalid': validationStatus($v.currentData.order),
                    }"
                    :disabled="disabledUpdateDisabledProduct"
                  ></b-form-input>
                  <div
                    v-if="!$v.currentData.order.required"
                    class="invalid-feedback"
                  >
                    Số thứ tự không được để trống.
                  </div>
                  <div
                    v-if="!$v.currentData.order.maxLength"
                    class="invalid-feedback"
                  >
                    Số thứ tự nhỏ hơn 100.
                  </div>
                </b-form-group>
              </b-col> -->
							</b-row>

							<b-row>
								<b-col md="4">
									<b-form-group>
										<label>Tên sản phẩm <span class="text-danger">*</span>:</label>
										<b-form-input id="input-0" v-model.trim="$v.currentData.productName.$model"
											placeholder="Nhập tên sản phẩm" :class="{
												'is-invalid': validationStatus($v.currentData.productName),
											}" :disabled="disabledUpdateDisabledProduct"></b-form-input>
										<div v-if="!$v.currentData.productName.required" class="invalid-feedback">
											Tên sản phẩm không được để trống.
										</div>
										<!-- <div v-if="!$v.currentData.title.maxLength" class="invalid-feedback">
											Tiêu đề không quá 1000 ký tự.
										</div> -->
									</b-form-group>
								</b-col>
								<b-col md="4">
									<b-form-group>
										<label>Giá gốc <span class="text-danger">*</span>:</label>
										<b-form-input id="input-original-price" @keypress="validateKeyCode"
											@keyup="formatAmount('original')" type="text" v-model.trim="$v.currentData.originalPrice.$model"
											placeholder="Nhập giá gốc" :class="{
												'is-invalid': validationStatus($v.currentData.originalPrice),
											}" :disabled="disabledUpdateDisabledProduct"></b-form-input>
										<div v-if="!$v.currentData.originalPrice.required" class="invalid-feedback">
											Giá sản phẩm không được để trống.
										</div>
										<!-- <div v-if="!$v.currentData.title.maxLength" class="invalid-feedback">
											Tiêu đề không quá 1000 ký tự.
										</div> -->
									</b-form-group>
								</b-col>
								<b-col md="4">
									<b-form-group>
										<label>Giá bán <span class="text-danger">*</span>:</label>
										<b-form-input id="input-original-price" @keypress="validateKeyCode" @keyup="formatAmount('sell')"
											type="text" v-model.trim="$v.currentData.sellPrice.$model" placeholder="Nhập giá bán" :class="{
												'is-invalid': validationStatus($v.currentData.sellPrice),
											}" :disabled="disabledUpdateDisabledProduct"></b-form-input>
										<!-- <div v-if="!$v.currentData.title.maxLength" class="invalid-feedback">
											Tiêu đề không quá 1000 ký tự.
										</div> -->
									</b-form-group>
								</b-col>
								<b-col md="4">
									<b-form-group>
										<label>Số lượng <span class="text-danger">*</span>:</label>
										<b-form-input id="input-original-price" v-model="$v.currentData.amount.$model" type="number"
											placeholder="Nhập số lượng" :class="{
												'is-invalid': validationStatus($v.currentData.amount),
											}" :disabled="disabledUpdateDisabledProduct"></b-form-input>
										<div v-if="!$v.currentData.amount.required" class="invalid-feedback">
											Số lượng không được để trống.
										</div>
										<!-- <div v-if="!$v.currentData.title.maxLength" class="invalid-feedback">
											Tiêu đề không quá 1000 ký tự.
										</div> -->
									</b-form-group>
								</b-col>
								<b-col md="4">
									<b-form-group>
										<label>Giảm giá:</label>
										<b-form-input id="input-original-price" v-model.trim="currentData.salePercent" type="number"
											placeholder="Nhập phần trăm giảm giá" :disabled="disabledUpdateDisabledProduct"></b-form-input>
									</b-form-group>
								</b-col>
								<!-- <b-col md="4">
									<b-form-group>
										<label>Thời gian bắt đầu sản phẩm <span class="text-danger">*</span>:</label>
										<div :class="{
											'invalid-date':
												(currentData.toDate &&
													currentData.fromDate >= currentData.toDate) ||
												validationStatus($v.currentData.fromDate),
										}">
											<date-picker :disabled-date="disabledBeforeToday" class="w-100" :input-class="[
												'form-control',
												{
													'is-invalid':
														validationStatus($v.currentData.fromDate) ||
														(currentData.fromDate &&
															currentData.fromDate >= currentData.toDate),
												},
											]" v-model.trim="$v.currentData.fromDate.$model" type="datetime" format="DD/MM/YYYY HH:mm"
												placeholder="Chọn ngày" :showSecond="false" :disabled="disabledUpdateDisabledProduct" />
											<div class="invalid-date-feedback">
												<span v-if="!$v.currentData.fromDate.required">
													Thời gian bắt đầu sản phẩm không được để trống.
												</span>
												<span v-if="
													currentData.fromDate &&
													currentData.toDate &&
													currentData.fromDate >= currentData.toDate
												">
													Thời gian bắt đầu sản phẩm cần nhỏ hơn thời gian hết
													sản phẩm.
												</span>
											</div>
										</div>
									</b-form-group>
								</b-col> -->
								<!-- <b-col md="4">
									<b-form-group>
										<label>Thời gian hết sản phẩm <span class="text-danger">*</span>:</label>
										<div :class="{
											'invalid-date':
												(currentData.toDate &&
													currentData.fromDate >= currentData.toDate) ||
												validationStatus($v.currentData.toDate),
										}">
											<date-picker :disabled-date="disabledBeforeToday" class="w-100" :input-class="[
												'form-control',
												{
													'is-invalid':
														validationStatus($v.currentData.toDate) ||
														(currentData.toDate &&
															currentData.fromDate >= currentData.toDate),
												},
											]" v-model.trim="$v.currentData.toDate.$model" type="datetime" format="DD/MM/YYYY HH:mm"
												placeholder="Chọn ngày" :showSecond="false" :disabled="disabledUpdateDisabledProduct" />
											<div class="invalid-date-feedback">
												<span v-if="!$v.currentData.fromDate.required">
													Thời gian bắt đầu sản phẩm không được để trống.
												</span>
												<span v-if="
													currentData.fromDate &&
													currentData.toDate &&
													currentData.fromDate >= currentData.toDate
												">
													Thời gian hết sản phẩm cần lớn hơn thời gian bắt đầu
													sản phẩm.
												</span>
											</div>
										</div>
									</b-form-group>
								</b-col> -->
							</b-row>

							<b-row>
								<b-col md="4">
									<b-form-group>
										<label>Loại sản phẩm <span class="text-danger">*</span>:</label>
										<multiselect v-model="$v.currentData.category.$model" track-by="text" label="text"
											:show-labels="false" :disabled="disabledUpdateDisabledProduct" placeholder="Chọn"
											:options="categoryOptions" :searchable="true" :class="{
												'is-invalid-option': validationStatus(
													$v.currentData.category
												),
											}">
											<template slot="singleLabel" slot-scope="{ option }">
												{{ option.text }}
											</template>
										</multiselect>
										<div v-if="!$v.currentData.category.required" class="invalid-feedback">
											Loại sản phẩm không được để trống.
										</div>
									</b-form-group>
								</b-col>
								<!-- <b-col md="4">
									<b-form-group>
										<label>Tiện ích <span class="text-danger">*</span>:</label>
										<multiselect v-model="$v.currentData.utilityCode.$model" track-by="text" label="text"
											:show-labels="false" placeholder="Chọn" :disabled="disableSelect"
											:options="utilitiesOption.filter((utility) => utility.value)" :searchable="true" :class="{
												'is-invalid-option': validationStatus(
													$v.currentData.utilityCode
												),
											}">
											<template slot="singleLabel" slot-scope="{ option }">
												{{ option.text }}
											</template>
										</multiselect>
										<div v-if="!$v.currentData.utilityCode.required" class="invalid-feedback">
											Mã tiện ích không được để trống.
										</div>
									</b-form-group>
								</b-col> -->
							</b-row>
						</div>
					</b-col>
				</b-row>
				<div class="w-100">
					<b-form-group>
						<label>Mô tả sản phẩm <span class="text-danger">*</span>:</label>
						<b-form-textarea v-model="$v.currentData.description.$model" :disabled="disabledUpdateDisabledProduct">
						</b-form-textarea>
					</b-form-group>
					<div v-if="validationStatus($v.currentData.description)" class="invalid-editor">
						<div v-if="!$v.currentData.description.required" class="invalid-editor-feedback">
							Mô tả sản phẩm không được để trống.
						</div>
					</div>
				</div>
				<div class="text-right">
					<b-button variant="outline-secondary" @click.prevent="handleReset">
						<i class="fas fa-undo"></i>
						Hoàn tác
					</b-button>
					<b-button variant="primary" @click.prevent="handleSubmit" :disabled="disabledUpdateDisabledProduct">
						<i class="fas fa-check"></i>
						Đồng ý
					</b-button>
				</div>
			</template>
		</b-card>
		<b-modal id="banner-image" title="Ảnh sản phẩm" :no-close-on-backdrop="true" size="lg" hide-footer>
			<div class="d-flex justify-content-center" v-if="currentMainImg || this.currentData.mainImg">
				<img style="width: 40rem" class="custom-banner-image" :src="getUrlImage" alt="Ảnh banner" />
			</div>
		</b-modal>
	</div>
</template>
  
<script>
import PageTitle from "@/Layout/Components/PageTitle";
import baseMixins from "@/components/mixins/base";
import { required } from "vuelidate/lib/validators";
import { formatTime } from "../../common/utils";
import { formatPriceSearchV2 } from "../../common/common";
import Vue from "vue";
import Multiselect from "vue-multiselect";
import moment from "moment-timezone";
import DatePicker from "vue2-datepicker";
import "vue2-datepicker/index.css";
import "vue2-datepicker/locale/vi";
import router from "@/router";
Vue.component("multiselect", Multiselect);
import { FETCH_CATEGORY, FETCH_PRODUCT_BY_ID, CREATE_PRODUCT, UPDATE_PRODUCT } from "../../store/action.type";

const initProduct = {
	productId: null,
	productName: null,
	description: null,
	originalPrice: 0,
	salePercent: 0,
	sellPrice: 0,
	category: null,
	mainImg: null,
	amount: 0,
	createdDate: null,
	productStatus: 1,
};

export default {
	name: "CreatePromotion",
	components: { PageTitle, DatePicker },
	data() {
		return {
			heading: "Tạo sản phẩm",
			subheading: "Tạo sản phẩm",
			currentData: Object.assign({}, initProduct),
			saveCurrentData: Object.assign({}, initProduct),
			userInfo: localStorage.getItem("userInfo")
				? JSON.parse(localStorage.getItem("userInfo"))
				: null,
			isUpdate: false,
			loadingHeader: true,
			loadingImage: false,
			currentProductDetail: null,
			currentMainImg: null,
			categoryOptions: [],
		};
	},
	mixins: [baseMixins],
	validations: {
		currentData: {
			productName: {
				required,
			},
			originalPrice: {
				required,
			},
			sellPrice: {
				required,
			},
			amount: {
				required,
			},
			category: {
				required,
			},
			description: {
				required,
			},
		},
	},
	created() {

		let url = this.$route.path

		if (!url) return

		if (url === '/admin/product/create') {
			setTimeout(() => {
				this.loadingHeader = false
			}, 300)

			this.$store.dispatch(FETCH_CATEGORY).then(res => {
				if (res && res.status === 200 && res.data) {
					this.categoryOptions = res.data.data.map(item => {
						return {
							text: item.categoryName,
							value: item.categoryId
						}
					})
				}
			})

			return
		}

		if (url.includes('/admin/product/update')) {
			this.heading = 'Cập nhật sản phẩm'
			this.subheading = 'Cập nhật thông tin sản phẩm'
			this.isUpdate = true
			let productId = this.$route.params.id

			if (!productId) return

			Promise.all([
				this.$store.dispatch(FETCH_CATEGORY),
				this.$store.dispatch(FETCH_PRODUCT_BY_ID, productId)
			]).then(res => {
				if (res[0] && res[0].status === 200 && res[0].data) {
					this.categoryOptions = res[0].data.data.map(item => {
						return {
							text: item.categoryName,
							value: item.categoryId
						}
					})
				}

				if (res[1]) {
					this.loadingHeader = false
				}

				if (res[1] && res[1].status === 200) {
					this.currentData = Object.assign({}, { ...res[1].data.data });
					this.saveCurrentData = Object.assign({}, { ...res[1].data.data });

					this.currentMainImg = res[1].data.data.mainImg;
					this.currentData.category = this.currentData.category ? {
						text: this.currentData.category.categoryName,
						value: this.currentData.category.categoryId,
					} : null

					this.formatAmount('sell')
					this.formatAmount('original')
				}
			})
		}

	},
	computed: {
		getUrlImage() {
			return this.currentData.mainImg
		},
		disabledUpdateDisabledProduct() {
			return this.currentData.productStatus === 0
		}
	},
	methods: {
		validateKeyCode: function (e) {
			if ((e.key < 48 || e.key > 57 || e.charCode === 13) && e.charCode !== 45 && e.charCode !== 32) {
				return true
			}

			e.preventDefault()
		},
		formatAmount(type) {
			if (type === 'original') this.currentData.originalPrice = this.currentData.originalPrice !== null ? formatPriceSearchV2(this.currentData.originalPrice + '') : null
			if (type === 'sell') this.currentData.sellPrice = this.currentData.sellPrice !== null ? formatPriceSearchV2(this.currentData.sellPrice + '') : null
		},
		showImageSelect() {
			this.$root.$emit("bv::show::modal", "banner-image");
		},
		removeImageSelect() {
			this.currentMainImg = null;
			this.currentData.mainImg = null;
		},
		handleCreateAndUpdatePromotion() {
			let {
				productId,
				productName,
				description,
				originalPrice,
				salePercent,
				sellPrice,
				amount,
				productStatus,
				category,
				mainImg,
				createdDate,
			} = { ...this.currentData };

			let payload = {
				productId,
				productData: {
					productName,
					categoryId: category ? category.value + '' : null,
					description,
					originalPrice: originalPrice && Number(originalPrice.replace(',', '')),
					mainImg,
					salePercent,
					sellPrice: sellPrice && Number(sellPrice.replace(',', '')),
					createdDate,
					amount,
					productStatus,
				},
			};

			if (!this.isUpdate) payload.productData.createdDate = formatTime(new Date());

			this.$store.dispatch(this.isUpdate ? UPDATE_PRODUCT : CREATE_PRODUCT, this.isUpdate ? payload : payload.productData).then((res) => {
				if (!res) return

				let successMsg = `${this.isUpdate ? 'Cập nhật' : 'Tạo'} sản phẩm thành công.`
				let errorMsg = `${this.isUpdate ? 'Cập nhật' : 'Tạo'} sản phẩm không thành công.`
				if (res.status === 200) {
					this.$message({
						message: successMsg,
						type: "success",
						showClose: true,
					});
					setTimeout(() => {
						this.$router.push({ path: `/admin/product` });
					}, 500)
				}
				if (res.status !== 200) {
					this.$message({
						message: errorMsg,
						type: "error",
						showClose: true,
					});
				}
			});
		},
		handleSubmit() {
			this.$v.$reset();
			this.$v.$touch();

			if (this.$v.currentData.$invalid) {
				return;
			}
			if (!this.currentData.description) {
				this.$message.closeAll()
				this.$message({
					message: "Mô tả sản phẩm không được để trống",
					type: "warning",
					showClose: true,
				});
				return;
			}
			this.handleCreateAndUpdatePromotion();
		},
		handleReset() {
			this.currentData = this.isUpdate ? Object.assign({}, this.saveCurrentData) : Object.assign({}, initProduct)

			this.currentMainImg = this.currentData.mainImg;
			this.$nextTick(() => {
				this.$v.$reset();
			});
		},
		handleFormatCurrentData() {
			this.currentData.category = this.currentData.categoryId
				? this.categoryOptions.filter((category) =>
					category.value === this.currentData.categoryId
				)
				: null;
		},
		validationStatus: function (validation) {
			return typeof validation != "undefined" ? validation.$error : false;
		},
	},
};
</script>
  
<style lang="scss" scoped>
#update-product {
	.multiselect {
		min-height: 32px !important;
	}
}

.is-invalid-option {
	border-radius: 5px;
	border: 1px solid #ff7851 !important;
}

.custom-wrapper {
	overflow-wrap: break-word;
}

.custom-banner-image {
	background-repeat: no-repeat;
	background-position: center;
	background-size: contain;
	box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
	border: 1px solid rgba(0, 0, 0, 0.2);
}

.banner-image__preview {
	width: 100%;
	height: 10rem;

	.overlay {
		width: 100%;
		height: 100%;
		display: none;
		background-color: rgba(0, 0, 0, 0.4);
	}

	&:hover .overlay {
		display: block;
		display: flex;
		justify-content: center;
		cursor: pointer;
		transition-duration: 500ms;
	}

	.banner-image__btn {
		border: none;
		outline: none;
		background-color: transparent;
		margin: 1rem;
		cursor: pointer;
		border-radius: 10px;
		color: white;
		font-size: 1.5rem;
	}
}
</style>
  
<style lang="scss">
#update-product {

	.was-validated .form-control:invalid,
	.form-control.is-invalid {
		background-image: none !important;
	}

	.invalid-date-feedback {
		display: none;
		width: 100%;
		margin-top: 0.25rem;
		font-size: 80%;
		color: #ff7851;
	}

	.invalid-editor-feedback {
		display: none;
		width: 100%;
		margin-top: 0.25rem;
		font-size: 80%;
		color: #ff7851;
	}

	.invalid-date {
		.invalid-date-feedback {
			display: block !important;
		}
	}

	.invalid-editor {
		.invalid-editor-feedback {
			display: block !important;
		}
	}
}
</style>