<template>
  <div>
    <page-title
      :heading="heading"
      :subheading="subheading"
      :loading="loadingHeader"
    ></page-title>
    <b-card id="update-product" class="main-card search-wrapper mb-20">
      <template v-if="loadingHeader">
        <a-skeleton active :paragraph="{ rows: 5 }"></a-skeleton>
      </template>
      <template v-else>
        <b-row>
          <b-col xl="4">
            <div v-if="currentData" class="banner-image__wrapper">
              <b-form-group>
                <label>Ảnh thumbnail:</label>
                <div
                  class="custom-banner-image banner-image__preview mb-2"
                  :style="
                    currentData.image_link_thumbnail
                      ? {
                          'background-image': `url(${currentData.image_link_thumbnail})`,
                        }
                      : null
                  "
                >
                  <div class="overlay" v-if="currentData.image_link_thumbnail">
                    <button
                      class="banner-image__btn"
                      @click="showImageSelect(currentData.image_link_thumbnail)"
                    >
                      <i class="fas fa-eye"></i>
                    </button>
                    <button
                      class="banner-image__btn"
                      @click="removeImageSelect('image_link_thumbnail')"
                    >
                      <i class="fas fa-trash-alt"></i>
                    </button>
                  </div>
                </div>
                <b-form-group>
                  <b-form-input
                    id="input-thumbnail"
                    type="text"
                    v-model="currentData.image_link_thumbnail"
                    placeholder="Nhập link ảnh thumbnail"
                  >
                  </b-form-input>
                </b-form-group>
              </b-form-group>
            </div>
            <div v-if="currentData" class="banner-image__wrapper">
              <b-form-group>
                <label>Ảnh chi tiết:</label>
                <div
                  class="custom-banner-image banner-image__preview mb-2"
                  :style="
                    currentData.image_link_detail
                      ? {
                          'background-image': `url(${currentData.image_link_detail})`,
                        }
                      : null
                  "
                >
                  <div class="overlay" v-if="currentData.image_link_detail">
                    <button
                      class="banner-image__btn"
                      @click="showImageSelect(currentData.image_link_detail)"
                    >
                      <i class="fas fa-eye"></i>
                    </button>
                    <button
                      class="banner-image__btn"
                      @click="removeImageSelect('image_link_detail')"
                    >
                      <i class="fas fa-trash-alt"></i>
                    </button>
                  </div>
                </div>
                <b-form-group>
                  <b-form-input
                    id="input-img-detail"
                    type="text"
                    v-model="currentData.image_link_detail"
                    placeholder="Nhập link ảnh"
                  >
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
                    :disabled="disabledUpdatePost"
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
                    <label
                      >Tiêu đề bài đăng
                      <span class="text-danger">*</span>:</label
                    >
                    <b-form-input
                      id="input-title"
                      v-model.trim="$v.currentData.title.$model"
                      placeholder="Nhập tiêu đề bài đăng"
                      :class="{
                        'is-invalid': validationStatus($v.currentData.title),
                      }"
                    ></b-form-input>
                    <div
                      v-if="!$v.currentData.title.required"
                      class="invalid-feedback"
                    >
                      Tiêu đề bài đăng không được để trống.
                    </div>
                    <!-- <div v-if="!$v.currentData.title.maxLength" class="invalid-feedback">
											Tiêu đề không quá 1000 ký tự.
										</div> -->
                  </b-form-group>
                </b-col>
                <!-- <b-col md="4">
									<b-form-group>
										<label>Thời gian bắt đầu bài đăng <span class="text-danger">*</span>:</label>
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
												placeholder="Chọn ngày" :showSecond="false" :disabled="disabledUpdatePost" />
											<div class="invalid-date-feedback">
												<span v-if="!$v.currentData.fromDate.required">
													Thời gian bắt đầu bài đăng không được để trống.
												</span>
												<span v-if="
													currentData.fromDate &&
													currentData.toDate &&
													currentData.fromDate >= currentData.toDate
												">
													Thời gian bắt đầu bài đăng cần nhỏ hơn thời gian hết
													bài đăng.
												</span>
											</div>
										</div>
									</b-form-group>
								</b-col> -->
                <!-- <b-col md="4">
									<b-form-group>
										<label>Thời gian hết bài đăng <span class="text-danger">*</span>:</label>
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
												placeholder="Chọn ngày" :showSecond="false" :disabled="disabledUpdatePost" />
											<div class="invalid-date-feedback">
												<span v-if="!$v.currentData.fromDate.required">
													Thời gian bắt đầu bài đăng không được để trống.
												</span>
												<span v-if="
													currentData.fromDate &&
													currentData.toDate &&
													currentData.fromDate >= currentData.toDate
												">
													Thời gian hết bài đăng cần lớn hơn thời gian bắt đầu
													bài đăng.
												</span>
											</div>
										</div>
									</b-form-group>
								</b-col> -->
              </b-row>

              <b-row>
                <b-col md="4">
                  <b-form-group>
                    <label
                      >Người đăng <span class="text-danger">*</span>:</label
                    >
                    <multiselect
                      v-model="$v.currentData.user.$model"
                      track-by="text"
                      label="text"
                      :show-labels="false"
                      placeholder="Chọn"
                      :options="userOptions"
                      :searchable="true"
                      :class="{
                        'is-invalid-option': validationStatus(
                          $v.currentData.user
                        ),
                      }"
                    >
                      <template slot="singleLabel" slot-scope="{ option }">
                        {{ option.text }}
                      </template>
                    </multiselect>
                    <div
                      v-if="!$v.currentData.user.required"
                      class="invalid-feedback"
                    >
                      Thông tin người đăng không được để trống.
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
            <label>Nội dung bài đăng <span class="text-danger">*</span>:</label>
            <b-form-textarea v-model="$v.currentData.content.$model">
            </b-form-textarea>
          </b-form-group>
          <div
            v-if="validationStatus($v.currentData.content)"
            class="invalid-editor"
          >
            <div
              v-if="!$v.currentData.content.required"
              class="invalid-editor-feedback"
            >
              Nội dung bài đăng không được để trống.
            </div>
          </div>
        </div>
        <div class="text-right">
          <b-button variant="outline-secondary" @click.prevent="handleReset">
            <i class="fas fa-undo"></i>
            Hoàn tác
          </b-button>
          <b-button variant="primary" @click.prevent="handleSubmit">
            <i class="fas fa-check"></i>
            Đồng ý
          </b-button>
        </div>
      </template>
    </b-card>
    <b-modal
      id="banner-image"
      title="Ảnh bài đăng"
      :no-close-on-backdrop="true"
      size="lg"
      hide-footer
    >
      <div class="d-flex justify-content-center" v-if="currentUrlImage">
        <img
          style="width: 40rem"
          class="custom-banner-image"
          :src="currentUrlImage"
          alt="Ảnh banner"
        />
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
import {
  FETCH_USERS,
  FETCH_POST_BY_ID,
  CREATE_POST,
  UPDATE_POST,
} from "@/store/action.type";
const initPost = {
  postId: null,
  title: null,
  content: null,
  user: null,
  image_link_detail: null,
  image_link_thumbnail: null,
  date: null,
};
export default {
  name: "CreatePost",
  components: { PageTitle, DatePicker },
  data() {
    return {
      heading: "Tạo bài đăng",
      subheading: "Tạo bài đăng",
      currentData: Object.assign({}, initPost),
      saveCurrentData: Object.assign({}, initPost),
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
      isUpdate: false,
      loadingHeader: true,
      loadingImage: false,
      currentPostDetail: null,
      currentUrlImage: null,
      userOptions: [],
    };
  },
  mixins: [baseMixins],
  validations: {
    currentData: {
      title: {
        required,
      },
      user: {
        required,
      },
      content: {
        required,
      },
    },
  },
  created() {
    let url = this.$route.path;
    if (!url) return;
    if (url === "/admin/post/create") {
      setTimeout(() => {
        this.loadingHeader = false;
      }, 300);
      this.$store.dispatch(FETCH_USERS).then((res) => {
        if (res && res.status === 200 && res.data) {
          this.userOptions = res.data.data.map((item) => {
            return {
              text: item.username,
              value: item.userId,
            };
          });
        }
      });
      return;
    }
    if (url.includes("/admin/post/update")) {
      this.heading = "Cập nhật bài đăng";
      this.subheading = "Cập nhật thông tin bài đăng";
      this.isUpdate = true;
      let postId = this.$route.params.id;
      if (!postId) return;
      Promise.all([
        this.$store.dispatch(FETCH_USERS),
        this.$store.dispatch(FETCH_POST_BY_ID, postId),
      ]).then((res) => {
        if (res[0] && res[0].status === 200 && res[0].data) {
          this.userOptions = res[0].data.data.map((item) => {
            return {
              text: item.username,
              value: item.userId,
            };
          });
        }
        if (res[1]) {
          this.loadingHeader = false;
        }
        if (res[1] && res[1].status === 200) {
          this.currentData = Object.assign({}, { ...res[1].data.data });
          this.saveCurrentData = Object.assign({}, { ...res[1].data.data });

          this.currentData.user = this.currentData.user
            ? {
                text: this.currentData.user.username,
                value: this.currentData.user.userId,
              }
            : null;
        }
      });
    }
  },
  computed: {},
  methods: {
    validateKeyCode: function(e) {
      if (
        (e.key < 48 || e.key > 57 || e.charCode === 13) &&
        e.charCode !== 45 &&
        e.charCode !== 32
      ) {
        return true;
      }
      e.preventDefault();
    },
    showImageSelect(value) {
      this.currentUrlImage = value;
      this.$root.$emit("bv::show::modal", "banner-image");
    },
    removeImageSelect(key) {
      this.currentUrlImage = null;
      this.currentData[key] = null;
    },
    handleCreateAndUpdatePost() {
      let {
        postId,
        title,
        content,
        image_link_detail,
        image_link_thumbnail,
        user,
        date,
      } = { ...this.currentData };
      let payload = {
        postId,
        postData: {
          title,
          userId: user ? user.value : null,
          content,
          image_link_detail,
          image_link_thumbnail,
          date,
        },
      };
      if (!this.isUpdate) payload.postData.date = formatTime(new Date());
      this.$store
        .dispatch(
          this.isUpdate ? UPDATE_POST : CREATE_POST,
          this.isUpdate ? payload : payload.postData
        )
        .then((res) => {
          if (!res) return;
          let successMsg = `${
            this.isUpdate ? "Cập nhật" : "Tạo"
          } bài đăng thành công.`;
          let errorMsg = `${
            this.isUpdate ? "Cập nhật" : "Tạo"
          } bài đăng không thành công.`;
          if (res.status === 200) {
            this.$message({
              message: successMsg,
              type: "success",
              showClose: true,
            });
            setTimeout(() => {
              this.$router.push({ path: `/admin/post` });
            }, 500);
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
      if (!this.currentData.content) {
        this.$message.closeAll();
        this.$message({
          message: "Nội dung bài đăng không được để trống",
          type: "warning",
          showClose: true,
        });
        return;
      }
      this.handleCreateAndUpdatePost();
    },
    handleReset() {
      this.currentData = this.isUpdate
        ? Object.assign({}, this.saveCurrentData)
        : Object.assign({}, initPost);
      this.currentData.user = this.currentData.user
        ? {
            text: this.currentData.user.username,
            value: this.currentData.user.userId,
          }
        : null;
      this.$nextTick(() => {
        this.$v.$reset();
      });
    },
    validationStatus: function(validation) {
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
