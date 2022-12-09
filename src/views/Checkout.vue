<template>
  <div>
    <!-- Page Preloder -->
    <!--  <div id="preloder">-->
    <!--    <div class="loader"></div>-->
    <!--  </div>-->

    <!-- Humberger Begin -->
    <Humberger />
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <UserHeader />
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <SectionBegin />
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <!-- Breadcrumb Section End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
      <div class="container">
        <div class="checkout__form">
          <h4>Thông tin đơn hàng</h4>
          <div class="row">
            <div class="col-lg-8 col-md-6">
              <div
                class="checkout__input"
                :class="{
                  'invalid-input': validationStatus($v.currentData.address),
                }"
              >
                <p>Địa chỉ<span>*</span></p>
                <input
                  type="text"
                  placeholder="Nhập địa chỉ"
                  v-model.trim="$v.currentData.address.$model"
                  :class="{
                    'is-invalid': validationStatus($v.currentData.address),
                  }"
                />
                <div
                  v-if="!$v.currentData.address.required"
                  class="invalid-feedback"
                >
                  Địa chỉ không được để trống.
                </div>
              </div>
              <div
                class="checkout__input"
                :class="{
                  'invalid-input': validationStatus($v.currentData.city),
                }"
              >
                <p>Thành phố<span>*</span></p>
                <input
                  type="text"
                  placeholder="Nhập tên thành phố"
                  v-model.trim="$v.currentData.city.$model"
                  :class="{
                    'is-invalid': validationStatus($v.currentData.city),
                  }"
                />
                <div
                  v-if="!$v.currentData.city.required"
                  class="invalid-feedback"
                >
                  Thành phố không được để trống.
                </div>
              </div>
              <div
                class="checkout__input"
                :class="{
                  'invalid-input': validationStatus($v.currentData.district),
                }"
              >
                <p>Phường/xã/huyện<span>*</span></p>
                <input
                  type="text"
                  placeholder="Nhập tên"
                  v-model.trim="$v.currentData.district.$model"
                  :class="{
                    'is-invalid': validationStatus($v.currentData.district),
                  }"
                />
                <div
                  v-if="!$v.currentData.district.required"
                  class="invalid-feedback"
                >
                  Phường/xã/huyện không được để trống.
                </div>
              </div>
              <div
                class="checkout__input"
                :class="{
                  'invalid-input': validationStatus($v.currentData.wards),
                }"
              >
                <p>Quận/Thị trấn<span>*</span></p>
                <input
                  type="text"
                  placeholder="Nhập tên"
                  v-model.trim="$v.currentData.wards.$model"
                  :class="{
                    'is-invalid': validationStatus($v.currentData.wards),
                  }"
                />
                <div
                  v-if="!$v.currentData.wards.required"
                  class="invalid-feedback"
                >
                  Quận/Thị trấn không được để trống.
                </div>
              </div>
              <div class="row">
                <div class="col-lg-6">
                  <div
                    class="checkout__input"
                    :class="{
                      'invalid-input': validationStatus(
                        $v.currentData.phoneNumber
                      ),
                    }"
                  >
                    <p>Số điện thoại<span>*</span></p>
                    <input
                      type="number"
                      placeholder="Nhập số điện thoại"
                      v-model="$v.currentData.phoneNumber.$model"
                      :class="{
                        'is-invalid': validationStatus(
                          $v.currentData.phoneNumber
                        ),
                      }"
                    />
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
                  </div>
                </div>
              </div>
              <div class="checkout__input">
                <p>Ghi chú</p>
                <input
                  type="text"
                  v-model="currentData.note"
                  placeholder="Nhập ghi chú của bạn cho shop hoặc đơn vị vận chuyển."
                />
              </div>
              <div class="checkout__input">
                <p>Mã giảm giá</p>
                <input
                  type="text"
                  v-model="currentData.note"
                  placeholder="Nhập mã giảm giá"
                />
                <button
                  class="site-btn"
                  @click="handleSubmit"
                  style="cursor:pointer"
                >
                  Áp mã giảm giá
                </button>
              </div>
            </div>
            <div class="col-lg-4 col-md-6">
              <div class="checkout__order">
                <h4>Đơn hàng của bạn</h4>
                <div class="checkout__order__products">
                  Sản phẩm <span>Giá</span>
                </div>
                <ul v-for="(item, index) in listCart" :key="index">
                  <li>
                    {{ item.product.productName }} x
                    {{ item.quantity }}
                    <span>{{
                      formatPrice(item.product.sellPrice * item.quantity)
                    }}</span>
                  </li>
                </ul>
                <div class="checkout__order__subtotal">
                  Giá <span>{{ formatPrice(subPrice) }}đ</span>
                </div>
                <div class="checkout__order__total">
                  Tổng giá đơn hàng <span>{{ formatPrice(totalPrice) }}đ</span>
                </div>
                <button
                  class="site-btn"
                  @click="handleSubmit"
                  style="cursor:pointer"
                >
                  Đặt hàng
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Checkout Section End -->

    <!-- Footer Section Begin -->
    <UserFooter />
    <!-- Footer Section End -->
    <modalPayment :total-price="totalPrice" />
  </div>
</template>

<script>
import { handleJQuery } from "../common/utils";
import baseMixins from "../components/mixins/base";
import { formatPriceSearchV2 } from "@/common/common";
import {
  CREATE_ORDER,
  // CREATE_ORDER_DETAIL,
  CREATE_ORDER_DETAIL_BY_ORDER_ID,
} from "@/store/action.type";
import { required, helpers } from "vuelidate/lib/validators";
import UserHeader from "../Layout/Components/UserHeader";
import UserFooter from "../Layout/Components/UserFooter";
import Humberger from "../Layout/Components/Humberger";
import SectionBegin from "../Layout/Components/SectionBegin";
import moment from "moment";
import modalPayment from "../Layout/Components/PaymentInfo.vue";

const DEFAULT_PROMOTION_ID = 3;
const ORDER_STATUS_ID = 1;
const initOrder = {
  totalPrice: 0,
  note: null,
  orderStatusId: ORDER_STATUS_ID,
  date: null,
  promotionId: DEFAULT_PROMOTION_ID,
  address: null,
  city: null,
  district: null,
  wards: null,
  phoneNumber: 0,
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
  /^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$/
);

export default {
  name: "check-out",
  mixins: [baseMixins],
  components: { UserHeader, modalPayment, Humberger, UserFooter, SectionBegin },
  data() {
    return {
      listCart: [],
      currentData: Object.assign({}, initOrder),
    };
  },
  validations: {
    currentData: {
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
  mounted() {
    // handleJQuery();
    this.getListCart();
  },
  computed: {
    totalPrice() {
      return this.listCart && this.listCart.length > 0
        ? this.listCart
            .map((cart) => cart.quantity * cart.product.sellPrice)
            .reduce((prev, current) => prev + current, 0)
        : 0;
    },
    subPrice() {
      return this.listCart && this.listCart.length > 0
        ? this.listCart
            .map((cart) => cart.quantity * cart.product.sellPrice)
            .reduce((prev, current) => prev + current, 0)
        : 0;
    },
  },
  methods: {
    formatPrice(price) {
      if (!price) return 0;
      return formatPriceSearchV2(price + "");
    },
    async getListCart() {
      const res = await this.getWithBigInt("/rest/carts");
      if (res && res.data && res.data.data) {
        this.listCart = res.data.data;
      }
    },
    async handleCreateOrder() {
      let successMsg = `Tạo đơn hàng thành công.`;
      let errorMsg = `Tạo đơn hàng không thành công.`;
      let outofProd = `Sản phẩm bạn đặt hiện tại đã hết!`;
      let {
        note,
        address,
        city,
        district,
        wards,
        phoneNumber,
        orderStatusId,
        promotionId,
      } = { ...this.currentData };
      let payload = {
        totalPrice: this.totalPrice,
        promotionId,
        note: note ? note.trim() : null,
        orderStatusId,
        date: moment(new Date()).format("YYYY-MM-DDTHH:mm:ss.SSSZ"),
        phoneNumber: phoneNumber && Number(phoneNumber),
        address,
        city,
        district,
        wards,
      };
      let payloadForCreateDetail = this.listCart.map((item) => {
        return {
          productId: item.product ? item.product.productId + "" : null,
          productName: item.product ? item.product.productName : null,
          quantity: item.quantity,
          productPrice:
            (item.product && item.quantity
              ? item.product.sellPrice * item.quantity
              : 0) + "",
        };
      });

      let res = await this.$store.dispatch(CREATE_ORDER, payload);
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
        }
      }
      this.$root.$emit("bv::show::modal", "modal-payment");
      if (res.data.status == "not ok") {
        this.$message({
          message: outofProd,
          type: "error",
          showClose: true,
        });
      }
    },
    handleSubmit() {
      this.$v.$reset();
      this.$v.$touch();
      if (this.$v.currentData.$invalid) {
        return;
      }
      this.handleCreateOrder();
    },
    validationStatus: function(validation) {
      return typeof validation != "undefined" ? validation.$error : false;
    },
  },
};
</script>

<style scoped>
.is-invalid {
  border-color: #dc3545;
  /* box-shadow: 0 0 0 0.2rem rgba(220, 53, 69, 0.25); */
}

.invalid-feedback {
  display: none;
  width: 100%;
  margin-top: 0.25rem;
  font-size: 80%;
  color: #ff7851;
}

.invalid-input .invalid-feedback {
  display: block !important;
}
</style>
