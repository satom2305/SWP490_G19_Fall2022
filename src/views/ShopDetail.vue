<template>
  <div>
    <!-- Page Preloder -->
    <!--    <div id="preloder">-->
    <!--      <div class="loader"></div>-->
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
    <!-- <div></div> -->
    <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <div class="breadcrumb__text">
              <!-- <h2>Vegetable’s Package</h2> -->
              <div class="breadcrumb__option">
                <!-- <a href="./">Home</a>
                <a href="./">Vegetables</a> -->
                <span v-if="productDetail">{{
                  productDetail.productName
                }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Product Details Section Begin -->
    <section class="product-details spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-md-6">
            <div class="product__details__pic">
              <div class="product__details__pic__item">
                <img
                  v-if="productDetail"
                  class="product__details__pic__item--large"
                  :src="productDetail.mainImg"
                  alt=""
                />
              </div>
              <div
                v-for="(item, index) in listImg"
                :key="index"
                class="product__details__pic__slider owl-carousel"
              >
                <img
                  data-imgbigurl="img/product/details/product-details-2.jpg"
                  :src="item[0]"
                  alt=""
                />
                <!-- <img
                  data-imgbigurl="img/product/details/product-details-3.jpg"
                  src="img/product/details/thumb-2.jpg"
                  alt=""
                />
                <img
                  data-imgbigurl="img/product/details/product-details-5.jpg"
                  src="img/product/details/thumb-3.jpg"
                  alt=""
                />
                <img
                  data-imgbigurl="img/product/details/product-details-4.jpg"
                  src="img/product/details/thumb-4.jpg"
                  alt=""
                /> -->
              </div>
            </div>
          </div>
          <div class="col-lg-6 col-md-6" v-if="productDetail">
            <div class="product__details__text">
              <h3>{{ productDetail.productName }}</h3>
              <div class="product__details__rating">
                <!-- <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star-half-o"></i> -->
                <!-- <span>(18 reviews)</span> -->
              </div>
              <div class="product__details__price">
                {{ formatPrice(productDetail.sellPrice) }}đ
              </div>
              <p>
                {{ productDetail.description }}
              </p>
              <div class="product__details__quantity">
                <div class="quantity">
                  <div class="pro-qty">
                    <input type="number" v-model="quantity" />
                  </div>
                </div>
              </div>
              <button
                style="border: none; cursor: pointer"
                @click="addToCart()"
                class="primary-btn"
                :disabled="!quantity || quantity <= 0"
              >
                Thêm vào giỏ hàng
              </button>
              <ul>
                <li>
                  <b>Availability</b> <span>{{ productDetail.amount }}</span>
                </li>
              </ul>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="product__details__tab">
              <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item" @click="navigateToNav('description')">
                  <a
                    :class="[
                      'nav-link',
                      { active: currentNav === 'description' },
                    ]"
                    data-toggle="tab"
                    href="#tabs-1"
                    role="tab"
                    aria-selected="true"
                    >Mô tả</a
                  >
                </li>
                <li class="nav-item" @click="navigateToNav('reviews')">
                  <a
                    :class="['nav-link', { active: currentNav === 'reviews' }]"
                    data-toggle="tab"
                    href="#tabs-3"
                    role="tab"
                    aria-selected="false"
                    >Đánh giá <span>({{ getTotalReview }})</span></a
                  >
                </li>
              </ul>
              <div class="tab-content">
                <div
                  :class="[
                    'tab-pane',
                    { active: currentNav === 'description' },
                  ]"
                  id="tabs-1"
                  role="tabpanel"
                >
                  Sản phẩm cây cối.
                </div>
                <div
                  :class="['tab-pane', { active: currentNav === 'reviews' }]"
                  id="tabs-3"
                  role="tabpanel"
                >
                  <div class="">
                    <a-list
                      v-if="productReviews.length"
                      :data-source="productReviews"
                      :header="`${productReviews.length} đánh giá`"
                      item-layout="horizontal"
                    >
                      <template #renderItem="item">
                        <a-list-item>
                          <a-comment
                            v-if="!item.toggleUpdate"
                            :author="item.userId.username"
                            :avatar="
                              'https://ps.w.org/simple-user-avatar/assets/icon-256x256.png?rev=2413146'
                            "
                            :content="item.reviewDetail"
                            :datetime="getDate(item)"
                          />
                          <!-- <a-form-item v-if="item.toggleUpdate">
                            <a-input :rows="8" v-model="item.reviewDetail" />
                          </a-form-item> -->
                          <a-form v-if="item.toggleUpdate" style="width: 80%">
                            <a-form-item>
                              <a-textarea
                                :rows="4"
                                v-model="item.reviewDetail"
                              />
                            </a-form-item>
                          </a-form>
                          <div
                            v-if="
                              item.userId &&
                                userInfo &&
                                item.userId.userId === userInfo.userId
                            "
                          >
                            <div class="p-2">
                              <a
                                href="javascript:void(0)"
                                type="button"
                                v-b-tooltip.hover
                                title="Cập nhật"
                                @click.prevent="toggleUpdate(item)"
                              >
                                <i
                                  v-if="!item.toggleUpdate"
                                  class="fas fa-edit text-success"
                                  style="font-size: 1.1rem"
                                ></i>
                                <i
                                  v-if="item.toggleUpdate"
                                  class="fas fa-check text-success"
                                  style="font-size: 1.1rem"
                                ></i>
                              </a>
                            </div>
                            <div class="p-2" v-if="item.toggleUpdate">
                              <a
                                href="javascript:void(0)"
                                type="button"
                                v-b-tooltip.hover
                                title="Huỷ"
                                @click.prevent="cancelUpdate(item)"
                              >
                                <i
                                  class="fas fa-times text-danger"
                                  style="font-size: 1.1rem"
                                ></i>
                              </a>
                            </div>
                          </div>
                        </a-list-item>
                      </template>
                    </a-list>
                    <div v-if="verifyAccountRole">
                      <a-comment>
                        <template #avatar>
                          <a-avatar
                            src="https://ps.w.org/simple-user-avatar/assets/icon-256x256.png?rev=2413146"
                            alt="avatar"
                          />
                        </template>
                        <template #content>
                          <a-form-item>
                            <a-textarea :rows="4" v-model="newReview" />
                          </a-form-item>
                          <a-form-item>
                            <a-button
                              type=""
                              @click="createReview"
                              style="background-color: #01904a; color: white"
                            >
                              Đánh giá
                            </a-button>
                          </a-form-item>
                        </template>
                      </a-comment>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Product Details Section End -->

    <!-- Related Product Section Begin -->
    <section class="related-product">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="section-title related__product__title">
              <h2>Related Product</h2>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-3 col-md-4 col-sm-6">
            <div class="product__item">
              <div
                class="product__item__pic set-bg"
                data-setbg="img/product/product-1.jpg"
              >
                <ul class="product__item__pic__hover">
                  <li>
                    <a href="#"><i class="fa fa-heart"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="fa fa-retweet"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="fa fa-shopping-cart"></i></a>
                  </li>
                </ul>
              </div>
              <div class="product__item__text">
                <h6><a href="#">Crab Pool Security</a></h6>
                <h5>$30.00</h5>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-4 col-sm-6">
            <div class="product__item">
              <div
                class="product__item__pic set-bg"
                data-setbg="img/product/product-2.jpg"
              >
                <ul class="product__item__pic__hover">
                  <li>
                    <a href="#"><i class="fa fa-heart"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="fa fa-retweet"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="fa fa-shopping-cart"></i></a>
                  </li>
                </ul>
              </div>
              <div class="product__item__text">
                <h6><a href="#">Crab Pool Security</a></h6>
                <h5>$30.00</h5>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-4 col-sm-6">
            <div class="product__item">
              <div
                class="product__item__pic set-bg"
                data-setbg="img/product/product-3.jpg"
              >
                <ul class="product__item__pic__hover">
                  <li>
                    <a href="#"><i class="fa fa-heart"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="fa fa-retweet"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="fa fa-shopping-cart"></i></a>
                  </li>
                </ul>
              </div>
              <div class="product__item__text">
                <h6><a href="#">Crab Pool Security</a></h6>
                <h5>$30.00</h5>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-4 col-sm-6">
            <div class="product__item">
              <div
                class="product__item__pic set-bg"
                data-setbg="img/product/product-7.jpg"
              >
                <ul class="product__item__pic__hover">
                  <li>
                    <a href="#"><i class="fa fa-heart"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="fa fa-retweet"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="fa fa-shopping-cart"></i></a>
                  </li>
                </ul>
              </div>
              <div class="product__item__text">
                <h6><a href="#">Crab Pool Security</a></h6>
                <h5>$30.00</h5>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Related Product Section End -->

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
import { mapGetters } from "vuex";
import { handleJQuery, verifyAccountRole } from "@/common/utils";
import baseMixins from "../components/mixins/base";
import moment from "moment-timezone";
import { formatPriceSearchV2 } from "@/common/common";
import UserHeader from "../Layout/Components/UserHeader.vue";
import {
  FETCH_REVIEWS,
  UPDATE_REVIEW,
  CREATE_REVIEW,
} from "@/store/action.type";
export default {
  name: "ShopDetail",
  components: { UserHeader },
  mixins: [baseMixins],
  props: {},
  data() {
    return {
      productDetail: null,
      listImg: [],
      quantity: 1,
      currentProductId: null,
      currentUserId: null,
      currentNav: "reviews",
      newReview: null,
      productReviews: [],
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
    };
  },
  mounted() {
    handleJQuery();
    this.getDetailProduct();
    this.currentProductId = Number(this.$route.params.id);
    this.fetchReviews();
  },
  computed: {
    ...mapGetters(["getReviews"]),
    getTotalReview() {
      return this.productReviews ? this.productReviews.length : 0;
    },
    verifyAccountRole() {
      return verifyAccountRole();
    },
  },
  methods: {
    formatPrice(price) {
      if (!price) return 0;
      return formatPriceSearchV2(price + "");
    },
    async getDetailProduct() {
      const id = this.$router.currentRoute.params.id;
      const res = await this.getWithBigInt(`/rest/products`, id);
      if (res && res.data && res.data.data) {
        this.productDetail = res.data.data;
      }
    },
    async addToCart() {
      if (!this.verifyAccountRole) {
        this.$router.push({ path: `/login` });
        return;
      }
      const userId = this.userInfo ? this.userInfo.userId : null;
      const productId = this.$router.currentRoute.params.id;

      if (!userId || !productId) return;
      const res = await this.post(`/rest/carts`, {
        userId,
        productId,
        quantity: this.quantity,
      });

      if (res && res.data && res.data.success) {
        this.$message.closeAll();
        this.$message({
          message: "Thêm sản phẩm vào giỏ hàng thành công",
          type: "success",
          showClose: true,
        });
        this.getDetailProduct();
      }
    },
    proceedToCheckout() {
      this.$router.push({ path: `/cart` });
    },
    navigateToNav(navSection) {
      this.currentNav = navSection;
    },
    getDate(item) {
      return (
        item &&
        item.date &&
        moment(new Date(item.date)).format("HH:mm DD/MM/YYYY")
      );
    },
    toggleUpdate(review) {
      review.toggleUpdate = !review.toggleUpdate;
      if (!review.toggleUpdate) this.updateReview(review);
    },
    cancelUpdate(review) {
      if (review.toggleUpdate) review.toggleUpdate = false;
      review.reviewDetail = review.currentReview;
    },
    async fetchReviews() {
      let res = await this.$store.dispatch(FETCH_REVIEWS);
      if (res.status === 200 && res.data.data) {
        this.getReviewsByProduct(res.data.data);
      }
    },
    getReviewsByProduct(reviews) {
      this.productReviews = reviews.filter(
        (item) =>
          item.productId && item.productId.productId === this.currentProductId
      );
      if (this.productReviews && this.productReviews.length > 0) {
        this.productReviews = this.productReviews.map((review) => {
          return {
            ...review,
            toggleUpdate: false,
            currentReview: review.reviewDetail,
          };
        });
      }
    },
    async createReview() {
      let payload = {
        userId: this.userInfo ? this.userInfo.userId + "" : null,
        productId: this.currentProductId,
        reviewDetail: this.newReview,
        date: moment(new Date()).format("YYYY-MM-DD"),
      };
      let res = await this.$store.dispatch(CREATE_REVIEW, payload);
      if (res.status === 200) {
        this.newReview = null;
        this.$message.closeAll();
        this.$message({
          message: "Thêm đánh giá thành công",
          type: "success",
          showClose: true,
        });
        this.fetchReviews();
      }
    },
    async updateReview(review) {
      if (!review.reviewDetail || review.reviewDetail.trim() === "") {
        review.toggleUpdate = true;
        this.$message.closeAll();
        this.$message({
          message: "Nội dung đánh giá không được để trống",
          type: "warning",
          showClose: true,
        });
        return;
      }
      let payload = {
        reviewId: review.review_id,
        reviewData: {
          userId: review.userId ? review.userId.userId : null,
          productId: review.productId ? review.productId.productId : null,
          reviewDetail: review.reviewDetail,
          date: moment(new Date(review.date)).format("YYYY-MM-DD"),
        },
      };
      let res = await this.$store.dispatch(UPDATE_REVIEW, payload);
      if (res.status === 200) {
        this.$message.closeAll();
        this.$message({
          message: "Cập nhật đánh giá thành công",
          type: "success",
          showClose: true,
        });
        this.fetchReviews();
      }
    },
  },
};
</script>

<style scoped></style>
