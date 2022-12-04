<template>
    <div>
      <!-- Page Preloder -->
      <!--    <div id="preloder">-->
      <!--        <div class="loader"></div>-->
      <!--    </div>-->
  
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
      <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
        <div class="container">
          <!-- <div class="row">
            <div class="col-lg-12 text-center">
              <div class="breadcrumb__text">
                <h2>Shopping Cart</h2>
                <div class="breadcrumb__option">
                  <a href="./">Home</a>
                  <span>Shopping Cart</span>
                </div>
              </div>
            </div>
          </div> -->
        </div>
      </section>
      <!-- Breadcrumb Section End -->
  
      <!-- Shoping Cart Section Begin -->
      <section class="shoping-cart spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="shoping__cart__table">
              <table>
                <thead>
                  <tr>
                    <th class="shoping__product">Mã đơn hàng</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Trạng thái đơn hàng</th>
                    <th>Tổng giá</th>
                    <th>Thông tin đơn</th>
                  </tr>
                </thead>
                <tbody v-for="(item, index) in listOrder" :key="index">
                  <tr>
                    <td class="shoping__cart__item">
                      <h5>{{ item.orderId }}</h5>
                    </td>
                    <td class="shoping__cart__item">
                      {{ item.address }}
                    </td>
                    <td class="shoping__cart__item">
                      {{ item.phoneNumber }}
                    </td>
                    <td class="shoping__cart__item">
                      {{ item.orderStatus.statusName }}
                    </td>
                    <td class="shoping__cart__total">
                      {{ formatPrice(item.totalPrice) }}đ
                    </td>
                    <td
                      class="shoping__cart__item__close"
                    >
                      <span
                        class="icon_close"
                        @click="openModalOrderDetail(item)"
                        >x</span
                      >
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="shoping__cart__btns">
              <a href="/" class="primary-btn cart-btn mx-0"
                >Tiếp tục mua hàng</a
              >
              <button
                v-if="listCart && listCart.length > 0"
                class="primary-btn cart-btn cart-btn-right"
                style="border: none; cursor: pointer"
                @click="confirmUpdateCart"
              >
                <span v-if="!toggleUpdateCart">Cập nhật</span>
                <span v-if="toggleUpdateCart">Xác nhận</span>
              </button>
            </div>
          </div>
          <div class="col-lg-6">
            <!-- <div class="shoping__continue">
              <div class="shoping__discount">
                <h5>Discount Codes</h5>
                <form action="#">
                  <input type="text" placeholder="Enter your coupon code" />
                  <button type="submit" class="site-btn">APPLY COUPON</button>
                </form>
              </div>
            </div> -->
          </div>
          <div class="col-lg-6">
            <div class="shoping__checkout">
              <h5>Chi tiết đơn hàng</h5>
              <ul>
                <li>
                  Giá <span>{{ formatPrice(this.subPrice) }}đ</span>
                </li>
                <li>
                  Tổng giá <span>{{ formatPrice(this.totalPrice) }}đ</span>
                </li>
              </ul>
              <button
                style="cursor: pointer; border: none;"
                @click="proceedToCheckout()"
                class="primary-btn w-100"
                v-if="listCart && listCart.length > 0"
              >
                Tiến hành thanh toán
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
      <!-- Shoping Cart Section End -->
  
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
      <ModalOrderDetail :current-order-detail="currentOrderDetail" />
      <!-- Footer Section End -->
      <!-- Js Plugins -->
    </div>
  </template>
  
  <script>
  import { handleJQuery } from "../common/utils";
  import baseMixins from "../components/mixins/base";
  import { formatPriceSearchV2 } from "../common/common";
  import UserHeader from "../Layout/Components/UserHeader";
  import ModalOrderDetail from "../Layout/Components/ModalOrderDetail.vue"
  export default {
  name: "MyOrder",
  mixins: [baseMixins],
  components: { UserHeader,ModalOrderDetail },
  data() {
    return {
      listCart: [],
      toggleUpdateCart: false,
      currentCart: null,
      listOrder:null,
      currentOrder:null,
      currentOrderDetail:null,
    };
  },
  mounted() {
    handleJQuery();
    this.getListCart();
    this.getListOrder();
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
    async getListCart() {
      const res = await this.getWithBigInt("/rest/carts");
      if (res && res.data && res.data.data) {
        this.listCart = res.data.data;
      }
    },
    async getListOrder() {
      const res = await this.getWithBigInt("/rest/orders/orderUser");
      if (res && res.data && res.data.data) {
        this.listOrder = res.data.data;
      }
      console.log(this.listOrder);
    },
    async deleteCart() {
      if (!this.currentCart || !this.currentCart.cartId) return;
      const res = await this.delete(`/rest/carts/${this.currentCart.cartId}`);
      if (res && res.status === 200) {
        this.$message.closeAll();
        this.$message({
          message: "Xoá sản phẩm khỏi giỏ hàng thành công.",
          type: "success",
          showClose: true,
        });
        this.closeModalConfirmDeleteCart();
        this.getListCart();
      }
    },
    onChangeQuantity(quantity, cart) {
      cart.quantity = quantity;
      this.$nextTick(() => {});
    },
   async openModalOrderDetail(order) {
      this.currentOrder = { ...order };
      const res = await this.getWithBigInt("/rest/orderDetails/order",order.orderId);
      if (res && res.data && res.data.data) {
        this.currentOrderDetail = res.data.data;
        this.$root.$emit("bv::show::modal", "modal-order-detail");
        console.log(res);
      }
    },
    closeModalConfirmDeleteCart(cart) {
      this.currentCart = null;
      this.$root.$emit("bv::hide::modal", "modal-delete-cart");
    },
    proceedToCheckout() {
      this.$router.push({ path: `/checkout` });
    },
    formatPrice(price) {
      if (!price) return 0;
      return formatPriceSearchV2(price + "");
    },
    confirmUpdateCart() {
      this.toggleUpdateCart = !this.toggleUpdateCart;
    },
  },
};
  </script>
  
  <style scoped>
  .custom-empty-content {
    color: #b6b6b6;
    font-size: 1.1rem;
    font-weight: 700;
  }
  </style>
  