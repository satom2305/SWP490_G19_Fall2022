<template>
  <b-modal
    size="xl"
    hide-footer
    id="modal-create-order"
    :title="currentTitleModal"
    :no-close-on-backdrop="true"
    @hide="cancelCreateOrder(false)"
  >
    <b-card id="update-order" class="main-card search-wrapper mb-20">
      <div class="mb-4">
        <div class="mb-2 font-weight-bold">Thông tin đơn hàng</div>
        <b-row>
          <b-col md="4">
            <b-form-group>
              <label>Mã khuyến mại:</label>
              <multiselect
                v-model="currentData.promotion"
                track-by="text"
                label="text"
                :show-labels="false"
                :disabled="disabledUpdateOrder"
                placeholder="Chọn"
                :options="promotionOptions"
                :searchable="true"
              >
                <template slot="singleLabel" slot-scope="{ option }">
                  {{ option.text }}
                </template>
              </multiselect>
            </b-form-group>
          </b-col>
          <b-col md="4">
            <b-form-group>
              <label>Trạng thái <span class="text-danger">*</span>:</label>
              <multiselect
                v-model="$v.currentData.orderStatus.$model"
                track-by="text"
                label="text"
                :show-labels="false"
                :disabled="disabledUpdateOrder"
                placeholder="Chọn"
                :options="orderStatusOptions"
                :searchable="true"
                :class="{
                  'is-invalid-option': validationStatus(
                    $v.currentData.orderStatus
                  ),
                }"
              >
                <template slot="singleLabel" slot-scope="{ option }">
                  {{ option.text }}
                </template>
              </multiselect>
              <div
                v-if="!$v.currentData.orderStatus.required"
                class="invalid-feedback"
              >
                Trạng thái không được để trống.
              </div>
            </b-form-group>
          </b-col>
          <b-col md="4">
            <b-form-group>
              <label>Số điện thoại <span class="text-danger">*</span>:</label>
              <b-form-input
                id="input-phonenumber"
                type="number"
                v-model="$v.currentData.phoneNumber.$model"
                placeholder="Nhập số điện thoại"
                :class="{
                  'is-invalid': validationStatus($v.currentData.phoneNumber),
                }"
                :disabled="disabledUpdateOrder"
              ></b-form-input>
              <div
                v-if="!$v.currentData.phoneNumber.required"
                class="invalid-feedback"
              >
                Số điện thoại không được để trống.
              </div>
              <div
                v-if="!$v.currentData.phoneNumber.validPhoneNumber"
                class="invalid-feedback"
              >
                Số điện thoại không hợp lệ.
              </div>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="4">
            <b-form-group>
              <label>Địa chỉ <span class="text-danger">*</span>:</label>
              <b-form-input
                id="input-price"
                type="text"
                v-model.trim="$v.currentData.address.$model"
                placeholder="Nhập địa chỉ"
                :class="{
                  'is-invalid': validationStatus($v.currentData.address),
                }"
                :disabled="disabledUpdateOrder"
              ></b-form-input>
              <div
                v-if="!$v.currentData.address.required"
                class="invalid-feedback"
              >
                Địa chỉ không được để trống.
              </div>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="4">
            <b-form-group>
              <label>Phường/xã/huyện <span class="text-danger">*</span>:</label>
              <b-form-input
                id="input-district"
                type="text"
                v-model.trim="$v.currentData.district.$model"
                placeholder="Nhập tên"
                :class="{
                  'is-invalid': validationStatus($v.currentData.district),
                }"
                :disabled="disabledUpdateOrder"
              ></b-form-input>
              <div
                v-if="!$v.currentData.district.required"
                class="invalid-feedback"
              >
                Phường/xã/huyện không được để trống.
              </div>
            </b-form-group>
          </b-col>
          <b-col md="4">
            <b-form-group>
              <label>Quận/Thị trấn <span class="text-danger">*</span>:</label>
              <b-form-input
                id="input-wards"
                type="text"
                v-model.trim="$v.currentData.wards.$model"
                placeholder="Nhập tên"
                :class="{
                  'is-invalid': validationStatus($v.currentData.wards),
                }"
                :disabled="disabledUpdateOrder"
              ></b-form-input>
              <div
                v-if="!$v.currentData.wards.required"
                class="invalid-feedback"
              >
                Quận/Thị trấn không được để trống.
              </div>
            </b-form-group>
          </b-col>
          <b-col md="4">
            <b-form-group>
              <label>Thành phố <span class="text-danger">*</span>:</label>
              <b-form-input
                id="input-city"
                type="text"
                v-model.trim="$v.currentData.city.$model"
                placeholder="Nhập tên"
                :class="{
                  'is-invalid': validationStatus($v.currentData.city),
                }"
                :disabled="disabledUpdateOrder"
              ></b-form-input>
              <div
                v-if="!$v.currentData.city.required"
                class="invalid-feedback"
              >
                Thành phố không được để trống.
              </div>
            </b-form-group>
          </b-col>
        </b-row>
        <div class="w-100">
          <b-form-group>
            <label>Ghi chú đơn hàng:</label>
            <b-form-textarea
              v-model="currentData.note"
              :disabled="disabledUpdateOrder"
            >
            </b-form-textarea>
          </b-form-group>
        </div>
      </div>
      <div class="w-100 mb-4" v-if="currentDetailData">
        <div class="d-flex align-items-center mb-2">
          <span class="font-weight-bold">Danh sách sản phẩm</span>
          <b-button
            class="ml-3"
            variant="outline-success"
            @click.prevent="addNewProductForDetail"
            :disabled="disabledUpdateOrder"
          >
            <i class="fas fa-plus"></i>
            Thêm
          </b-button>
        </div>
        <div class="text-center my-3" v-if="loadingOrderDetail && isUpdate">
          <b-spinner variant="success" label="Spinning"></b-spinner>
        </div>
        <div v-else>
          <div v-for="(item, index) in currentDetailData" :key="index">
            <b-row>
              <b-col md="4">
                <b-form-group>
                  <label>Sản phẩm:</label>
                  <multiselect
                    v-model="item.product"
                    track-by="text"
                    label="text"
                    :show-labels="false"
                    :disabled="disabledUpdateOrder"
                    placeholder="Chọn"
                    :options="productOptions"
                    :searchable="true"
                    @select="calculateProductPrice(index)"
                    @remove="calculateProductPrice(index)"
                  >
                    <template slot="singleLabel" slot-scope="{ option }">
                      {{ option.text }}
                    </template>
                  </multiselect>
                </b-form-group>
              </b-col>
              <b-col md="2">
                <b-form-group>
                  <label>Số lượng:</label>
                  <b-form-input
                    id="input-product-quantity"
                    v-model="item.quantity"
                    type="number"
                    placeholder="Nhập số lượng"
                    :disabled="disabledUpdateOrder"
                    @input="calculateProductPrice(index)"
                  >
                  </b-form-input>
                </b-form-group>
              </b-col>
              <b-col md="2">
                <b-form-group>
                  <label>Tổng giá:</label>
                  <div>{{ getFormatPrice(item.productPrice) }}đ</div>
                </b-form-group>
              </b-col>
              <b-col md="2">
                <b-button
                  class="mt-4"
                  variant="danger"
                  @click.prevent="openModalDeleteOrderDetail(item, index)"
                  :disabled="disabledUpdateOrder"
                >
                  <i class="fas fa-trash"></i>
                  Xoá
                </b-button>
              </b-col>
            </b-row>
          </div>
        </div>
      </div>
      <div>
        <b-row>
          <b-col md="4">
            <b-form-group>
              <label class="font-weight-bold mr-2">Tổng giá sản phẩm:</label>
              <span>{{ getFormatPrice(getTotalProductPrice) }}đ</span>
            </b-form-group>
            <b-form-group>
              <label class="font-weight-bold mr-2">Tổng giá đơn hàng:</label>
              <span>{{ getFormatPrice(currentData.totalPrice) }}đ</span>
            </b-form-group>
          </b-col>
        </b-row>
      </div>
      <div class="text-right">
        <b-button variant="outline-danger" @click="cancelCreateOrder(false)">
          <i class="fas fa-times"></i>
          Huỷ
        </b-button>
        <!-- <b-button variant="outline-secondary" @click.prevent="handleReset">
          <i class="fas fa-undo"></i>
          Hoàn tác
        </b-button> -->
        <b-button
          variant="primary"
          @click.prevent="handleSubmit"
          :disabled="disabledUpdateOrder"
        >
          <i class="fas fa-check"></i>
          Đồng ý
        </b-button>
      </div>
    </b-card>

    <b-modal
      hide-footer
      id="delete-order-detail"
      :title="'Xác nhận xoá sản phẩm khỏi đơn hàng'"
      :no-close-on-backdrop="true"
    >
      <div class="pb-3">
        Bạn có muốn xoá sản phẩm
        <span class="font-weight-bold" v-if="currentSelectDetail">
          {{ currentSelectDetail.productName }}</span
        >
        khỏi đơn hàng không ?
      </div>
      <b-button
        class="mr-2 btn-light2 pull-right"
        @click.prevent="cancelDeleteOrderDetail()"
      >
        Hủy
      </b-button>
      <b-button
        variant="primary pull-right"
        class="mr-2"
        type="submit"
        @click.prevent="handleDeleteOrderDetail()"
      >
        Đồng ý
      </b-button>
    </b-modal>
  </b-modal>
</template>

<script>
import PageTitle from "@/Layout/Components/PageTitle";
import baseMixins from "@/components/mixins/base";
import { required, helpers } from "vuelidate/lib/validators";
import { formatPriceSearchV2 } from "@/common/common";
import Vue from "vue";
import Multiselect from "vue-multiselect";
import moment from "moment-timezone";
import DatePicker from "vue2-datepicker";
import { mapGetters } from "vuex";
import "vue2-datepicker/index.css";
import "vue2-datepicker/locale/vi";
Vue.component("multiselect", Multiselect);
import {
  CREATE_ORDER,
  UPDATE_ORDER,
  FETCH_PROMOTIONS,
  FETCH_PRODUCTS_AVAILABLE,
  CREATE_ORDER_DETAIL,
  UPDATE_ORDER_DETAIL_BY_ORDER_ID,
  DELETE_ORDER_DETAIL,
  FETCH_ORDER_DETAIL_BY_ORDER_ID,
} from "@/store/action.type";
import { CREATE_ORDER_DETAIL_BY_ORDER_ID } from "../../../store/action.type";
const initOrder = {
  note: null,
  totalPrice: 0,
  orderStatusId: null,
  date: null,
  promotionId: 0,
  address: null,
  city: null,
  district: null,
  wards: null,
  phoneNumber: 0,
  orderId: null,
  promotion: null,
  orderStatus: { id: 1, statusName: "Chờ xác nhận" },
  user: null,
};
const initOrderDetail = {
  order_detail_id: null,
  order: null,
  product: null,
  productName: null,
  productPrice: 0,
  quantity: 1,
};
const validPhoneNumber = helpers.regex(
  "validPhoneNumber",
  /(84|0[3|5|7|8|9])+([0-9]{8})\b/g
);
export default {
  name: "ModalCreateOrder",
  components: { PageTitle, DatePicker },
  props: {
    currentTitleModal: String,
    order: {
      type: Object,
      default: null,
    },
    orderDetail: Array,
    isUpdate: Boolean,
  },
  data() {
    return {
      heading: "Tạo đơn hàng",
      subheading: "Tạo đơn hàng",
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
      loadingHeader: true,
      orderStatusOptions: [
        { value: 1, text: "Chờ xác nhận" },
        { value: 2, text: "Đã xác nhận" },
        { value: 3, text: "Đã huỷ" },
      ],
      promotionOptions: [],
      productOptions: [],
      currentData: Object.assign({}, initOrder),
      currentDetailData: [],
      currentDetailId: null,
      currentSelectDetail: null,
      loadingOrderDetail: true,
    };
  },
  mixins: [baseMixins],
  validations: {
    currentData: {
      orderStatus: {
        required,
      },
      totalPrice: {
        required,
      },
      address: {
        required,
      },
      city: {
        required,
      },
      district: {
        required,
      },
      wards: {
        required,
      },
      phoneNumber: {
        required,
        validPhoneNumber,
      },
    },
  },
  computed: {
    ...mapGetters(["getPromotions", "getProducts"]),
    saveCurrentData() {
      return this.order;
    },
  },
  watch: {
    order() {
      // this.setCurrentUpdateData()
      // this.fetchOrderDetailById()
    },
  },
  mounted() {
    if (!this.getPromotions || this.getPromotions.length === 0) {
      this.$store.dispatch(FETCH_PROMOTIONS).then((res) => {
        if (res && res.status === 200 && res.data)
          this.getPromotionOptions(res.data.data);
      });
    } else {
      this.getPromotionOptions(this.getPromotions);
    }
    if (!this.getProducts || this.getProducts.length === 0) {
      this.$store.dispatch(FETCH_PRODUCTS_AVAILABLE).then((res) => {
        if (res && res.status === 200 && res.data)
          this.getProductOptions(res.data.data);
      });
    } else {
      this.getProductOptions(this.getProducts);
    }
    this.$root.$on("bv::modal::show", (bvEvent, modalId) => {
      if (modalId === "modal-create-order") this.fetchOrderDetailById();
    });
  },
  computed: {
    disabledUpdateOrder() {
      return false;
      // return (this.currentData && this.isUpdate && !(this.currentData.orderStatus && this.currentData.orderStatus.value === 1))
    },
    getTotalProductPrice() {
      return this.currentDetailData && this.currentDetailData.length > 0
        ? this.currentDetailData
            .map((item) =>
              item.product && item.quantity
                ? item.product.sellPrice * item.quantity
                : 0
            )
            .reduce((prev, current) => prev + current, 0)
        : 0;
    },
  },
  methods: {
    checkInvalidProduct() {
      if (this.currentDetailData && this.currentDetailData.length > 0) {
        let incorrectDetail = this.currentDetailData.filter(
          (item) => !item.product || item.quantity < 0 || !item.quantity
        );
        return incorrectDetail && incorrectDetail.length > 0;
      }
      return false;
    },
    addNewProductForDetail() {
      let newProduct = Object.assign({}, { ...initOrderDetail });
      this.currentDetailData.push(newProduct);
    },
    calculateProductPrice(index) {
      this.$nextTick(() => {
        this.currentDetailData[index].productPrice =
          this.currentDetailData[index].product &&
          this.currentDetailData[index].quantity
            ? this.currentDetailData[index].product.sellPrice *
              this.currentDetailData[index].quantity
            : 0;
        this.calculateOrderPrice();
      });
    },
    calculateOrderPrice() {
      this.currentData.totalPrice =
        this.currentDetailData && this.currentDetailData.length > 0
          ? this.currentDetailData
              .map((item) =>
                item.product && item.quantity
                  ? item.product.sellPrice * item.quantity
                  : 0
              )
              .reduce((prev, current) => prev + current, 0)
          : 0;
    },
    setCurrentUpdateData() {
      if (!this.order) return;
      let { promotion, user, orderStatus } = { ...this.order };
      this.currentData = {
        ...this.order,
        promotion: promotion
          ? { text: promotion.salePercent + "%", value: promotion.promotionId }
          : null,
        orderStatus: orderStatus
          ? { text: orderStatus.statusName, value: orderStatus.id }
          : null,
        user: user ? { text: user.username, value: user.userId } : null,
      };
    },
    setCurrentUpdateDetailData() {
      this.currentDetailData = this.currentDetailData.map((item) => {
        return {
          ...item,
          product: item.product
            ? {
                text: item.product.productName,
                value: item.product.productId,
                img: item.product.mainImg,
                sellPrice: item.product.sellPrice,
              }
            : null,
        };
      });
    },
    async fetchOrderDetailById() {
      if (!this.order || !this.order.orderId) return;
      let response = await this.$store.dispatch(
        FETCH_ORDER_DETAIL_BY_ORDER_ID,
        this.order.orderId
      );
      if (response && response.data && response.data.success) {
        this.currentDetailData = [...response.data.data];
        this.setCurrentUpdateDetailData();
        setTimeout(() => {
          if (this.loadingOrderDetail) this.loadingOrderDetail = false;
        }, 300);
      } else {
        this.$message({
          message: "Lấy chi tiết đơn hàng không thành công.",
          type: "error",
          showClose: true,
        });
      }
    },
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
    getPromotionOptions(promotions) {
      this.promotionOptions = promotions
        .filter((item) => item.amount > 0)
        .map((item) => {
          return {
            text: item.salePercent ? item.salePercent + "%" : "",
            value: item.promotionId,
          };
        });
    },
    getProductOptions(products) {
      this.productOptions = products
        .filter((item) => item.amount > 0)
        .map((item) => {
          return {
            text: item.productName,
            value: item.productId,
            img: item.mainImg,
            sellPrice: item.sellPrice,
          };
        });
    },
    getFormatPrice(price) {
      return price ? formatPriceSearchV2(price + "") : 0;
    },
    async handleCreateAndUpdateOrder() {
      let successMsg = `${
        this.isUpdate ? "Cập nhật" : "Tạo"
      } đơn hàng thành công.`;
      let errorMsg = `${
        this.isUpdate ? "Cập nhật" : "Tạo"
      } đơn hàng không thành công.`;
      let {
        totalPrice,
        note,
        date,
        address,
        city,
        district,
        wards,
        phoneNumber,
        orderStatus,
        promotion,
        user,
        orderId,
      } = { ...this.currentData };
      let payload = {
        orderId,
        orderData: {
          totalPrice: this.getTotalProductPrice,
          promotionId: promotion ? promotion.value : null,
          note,
          totalPrice: totalPrice && Number((totalPrice + "").replace(",", "")),
          orderStatusId: orderStatus ? orderStatus.value : null,
          date: this.isUpdate
            ? date
            : moment(new Date()).format("YYYY-MM-DDTHH:mm:ss.SSSZ"),
          phoneNumber: phoneNumber && Number(phoneNumber),
          address,
          city,
          district,
          wards,
        },
      };
      let payloadForCreateDetail = this.currentDetailData.map((item) => {
        return {
          productId: item.product ? item.product.value + "" : null,
          productName: item.product ? item.product.text : null,
          quantity: item.quantity,
          productPrice:
            (item.product && item.quantity
              ? item.product.sellPrice * item.quantity
              : 0) + "",
        };
      });
      let payloadForUpdateDetail = {
        orderId: orderId,
        orderDetailData: this.currentDetailData
          .filter((detail) => detail && detail.order_detail_id)
          .map((item) => {
            return {
              order_detail_id: item.order_detail_id
                ? item.order_detail_id + ""
                : null,
              productId: item.product ? item.product.value + "" : null,
              productName: item.product ? item.product.text : null,
              quantity: item.quantity,
              productPrice:
                (item.product && item.quantity
                  ? item.product.sellPrice * item.quantity
                  : 0) + "",
            };
          }),
        newOrderDetailData: this.currentDetailData
          .filter((detail) => detail && !detail.order_detail_id)
          .map((item) => {
            return {
              orderId: orderId,
              productId: item.product ? item.product.value + "" : null,
              productName: item.product ? item.product.text : null,
              quantity: item.quantity,
              productPrice:
                (item.product && item.quantity
                  ? item.product.sellPrice * item.quantity
                  : 0) + "",
            };
          }),
      };
      if (this.isUpdate) {
        let promiseList = [this.$store.dispatch(UPDATE_ORDER, payload)];
        if (payloadForUpdateDetail.orderDetailData.length > 0)
          promiseList.push(
            this.$store.dispatch(
              UPDATE_ORDER_DETAIL_BY_ORDER_ID,
              payloadForUpdateDetail
            )
          );
        if (payloadForUpdateDetail.newOrderDetailData.length > 0)
          promiseList.push(
            this.$store.dispatch(
              CREATE_ORDER_DETAIL_BY_ORDER_ID,
              payloadForUpdateDetail.newOrderDetailData
            )
          );
        Promise.all(promiseList).then((res) => {
          if (res[0].status === 200 && res[1].status === 200) {
            this.$message({
              message: successMsg,
              type: "success",
              showClose: true,
            });
            this.cancelCreateOrder(true);
          }
        });
      }
      if (!this.isUpdate) {
        let res = await this.$store.dispatch(CREATE_ORDER, payload.orderData);
        if (res.status === 200 && res.data && res.data.data) {
          let newOrderId = res.data.data.orderId;
          payloadForCreateDetail = payloadForCreateDetail.map((item) => {
            return {
              ...item,
              orderId: newOrderId,
            };
          });
          let resForDetail = await this.$store.dispatch(
            CREATE_ORDER_DETAIL_BY_ORDER_ID,
            payloadForCreateDetail
          );
          if (resForDetail.status === 200) {
            this.$message({
              message: successMsg,
              type: "success",
              showClose: true,
            });
            this.cancelCreateOrder(true);
          }
        }
      }
    },
    handleSubmit() {
      this.$v.$reset();
      this.$v.$touch();
      if (this.$v.currentData.$invalid) {
        return;
      }
      let checkInvalidProduct = this.checkInvalidProduct();
      if (checkInvalidProduct) {
        this.$message.closeAll();
        this.$message({
          message: "Bạn cần điền đầy đủ thông tin sản phẩm.",
          type: "warning",
          showClose: true,
        });
        return;
      }
      this.handleCreateAndUpdateOrder();
    },
    handleReset() {
      // if (this.isUpdate) {
      //   this.setCurrentUpdateData()
      //   this.setCurrentUpdateDetailData()
      // }
      // if (!this.isUpdate) {
      //   this.currentData = Object.assign({}, initOrder)
      //   this.currentDetailData = []
      // }
      // this.$nextTick(() => {
      //   this.$v.$reset();
      // });
    },
    validationStatus: function(validation) {
      return typeof validation != "undefined" ? validation.$error : false;
    },
    cancelCreateOrder(isFetchOrders) {
      this.$v.$reset();
      this.loadingOrderDetail = true;
      this.currentData = Object.assign({}, { ...initOrder });
      this.currentDetailData = [];
      this.$emit("cancelCreateOrder", isFetchOrders);
      this.$root.$emit("bv::hide::modal", "modal-create-order");
    },
    openModalDeleteOrderDetail(orderDetail, indexVal) {
      if (orderDetail && orderDetail.order_detail_id) {
        this.currentDetailId = orderDetail.order_detail_id;
        this.currentSelectDetail = orderDetail;
        this.$root.$emit("bv::show::modal", "delete-order-detail");
      } else {
        this.currentDetailData = this.currentDetailData.filter(
          (item, index) => index !== indexVal
        );
        this.calculateOrderPrice();
      }
    },
    cancelDeleteOrderDetail() {
      this.$root.$emit("bv::hide::modal", "delete-order-detail");
    },
    async handleDeleteOrderDetail() {
      if (this.currentDetailId) {
        let res = await this.$store.dispatch(
          DELETE_ORDER_DETAIL,
          this.currentDetailId
        );
        if (res.status === 200) {
          this.cancelDeleteOrderDetail();
          this.currentDetailData = this.currentDetailData.filter(
            (item) => item.order_detail_id !== this.currentDetailId
          );
          this.currentDetailId = null;
          this.currentSelectDetail = null;
          this.$message.closeAll();
          this.$message({
            message: "Xoá sản phẩm khỏi đơn hàng thành công.",
            type: "success",
            showClose: true,
          });
        }
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#update-order {
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
#update-order {
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
