<template>
  <div>
    <!-- Page Preloder -->
    <!--  <div id="preloder">-->
    <!--    <div class="loader"></div>-->
    <!--  </div>-->

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
      <div class="humberger__menu__logo">
        <a href="#"><img src="img/logo.png" alt=""/></a>
      </div>
      <div class="humberger__menu__cart">
        <ul>
          <li>
            <a href="#"><i class="fa fa-heart"></i> <span>1</span></a>
          </li>
          <li>
            <a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a>
          </li>
        </ul>
        <div class="header__cart__price">item: <span>$150.00</span></div>
      </div>
      <div class="humberger__menu__widget">
        <div class="header__top__right__language">
          <img src="img/language.png" alt="" />
          <div>English</div>
          <span class="arrow_carrot-down"></span>
          <ul>
            <li><a href="#">Spanis</a></li>
            <li><a href="#">English</a></li>
          </ul>
        </div>
        <div class="header__top__right__auth">
          <a href="#"><i class="fa fa-user"></i> Login</a>
        </div>
      </div>
      <nav class="humberger__menu__nav mobile-menu">
        <ul>
          <li class="active"><a href="./">Home</a></li>
          <li><a href="./shop-grid">Shop</a></li>
          <li>
            <a href="#">Pages</a>
            <ul class="header__menu__dropdown">
              <li><a href="./shop-details">Shop Details</a></li>
              <li><a href="./shoping-cart">Shoping Cart</a></li>
              <li><a href="./checkout">Check Out</a></li>
              <li><a href="./blog-details">Blog Details</a></li>
            </ul>
          </li>
          <li><a href="./blog">Blog</a></li>
          <li><a href="./contact">Contact</a></li>
        </ul>
      </nav>
      <div id="mobile-menu-wrap"></div>
      <div class="header__top__right__social">
        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-linkedin"></i></a>
        <a href="#"><i class="fa fa-pinterest-p"></i></a>
      </div>
      <div class="humberger__menu__contact">
        <ul>
          <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
          <li>Free Shipping for all Order of $99</li>
        </ul>
      </div>
    </div>
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <UserHeader />
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <section class="hero hero-normal">
      <div class="container">
        <div class="row">
          <div class="col-lg-3">
            <div class="hero__categories">
              <div class="hero__categories__all">
                <i class="fa fa-bars"></i>
                <span>All departments</span>
              </div>
              <ul>
                <li><a href="#">Fresh Meat</a></li>
                <li><a href="#">Vegetables</a></li>
                <li><a href="#">Fruit & Nut Gifts</a></li>
                <li><a href="#">Fresh Berries</a></li>
                <li><a href="#">Ocean Foods</a></li>
                <li><a href="#">Butter & Eggs</a></li>
                <li><a href="#">Fastfood</a></li>
                <li><a href="#">Fresh Onion</a></li>
                <li><a href="#">Papayaya & Crisps</a></li>
                <li><a href="#">Oatmeal</a></li>
                <li><a href="#">Fresh Bananas</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-9">
            <div class="hero__search">
              <div class="hero__search__form">
                <form action="#">
                  <div class="hero__search__categories">
                    All Categories
                    <span class="arrow_carrot-down"></span>
                  </div>
                  <input type="text" placeholder="What do yo u need?" />
                  <button type="submit" class="site-btn">SEARCH</button>
                </form>
              </div>
              <div class="hero__search__phone">
                <div class="hero__search__phone__icon">
                  <i class="fa fa-phone"></i>
                </div>
                <div class="hero__search__phone__text">
                  <h5>+65 11.188.888</h5>
                  <span>support 24/7 time</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
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
                  PLACE ORDER
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Checkout Section End -->

    <!-- Footer Section Begin -->
    <footer class="footer spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="footer__about">
              <div class="footer__about__logo">
                <a href="./"><img src="img/logo.png" alt=""/></a>
              </div>
              <ul>
                <li>Address: 60-49 Road 11378 New York</li>
                <li>Phone: +65 11.188.888</li>
                <li>Email: hello@colorlib.com</li>
              </ul>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
            <div class="footer__widget">
              <h6>Useful Links</h6>
              <ul>
                <li><a href="#">About Us</a></li>
                <li><a href="#">About Our Shop</a></li>
                <li><a href="#">Secure Shopping</a></li>
                <li><a href="#">Delivery infomation</a></li>
                <li><a href="#">Privacy Policy</a></li>
                <li><a href="#">Our Sitemap</a></li>
              </ul>
              <ul>
                <li><a href="#">Who We Are</a></li>
                <li><a href="#">Our Services</a></li>
                <li><a href="#">Projects</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Innovation</a></li>
                <li><a href="#">Testimonials</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-4 col-md-12">
            <div class="footer__widget">
              <h6>Join Our Newsletter Now</h6>
              <p>
                Get E-mail updates about our latest shop and special offers.
              </p>
              <form action="#">
                <input type="text" placeholder="Enter your mail" />
                <button type="submit" class="site-btn">Subscribe</button>
              </form>
              <div class="footer__widget__social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-instagram"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-pinterest"></i></a>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="footer__copyright">
              <div class="footer__copyright__text">
                <p>
                  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                  Copyright &copy; 2022 All rights reserved | This template is
                  made with
                  <i class="fa fa-heart" aria-hidden="true"></i> by
                  <a href="https://colorlib.com" target="_blank">Colorlib</a>
                  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                </p>
              </div>
              <div class="footer__copyright__payment">
                <img src="img/payment-item.png" alt="" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- Footer Section End -->
  </div>
</template>

<script>
import { handleJQuery } from "../common/utils";
import baseMixins from "../components/mixins/base";
import { formatPriceSearchV2 } from "@/common/common";
import {
  CREATE_ORDER,
  CREATE_ORDER_DETAIL,
  CREATE_ORDER_DETAIL_BY_ORDER_ID,
} from "@/store/action.type";
import { required, helpers } from "vuelidate/lib/validators";
import UserHeader from "../Layout/Components/UserHeader";
import moment from "moment";

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
  components: { UserHeader },
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
    handleJQuery();
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
