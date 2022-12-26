<template>
  <div>
    <!-- Page Preloder -->
    <!--    <div id="preloder">-->
    <!--      <div class="loader"></div>-->
    <!--    </div>-->
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
    <!-- <div></div> -->
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
                {{ productDetail.category.categoryName }}
              </p>
              <div class="product__details__quantity">
                <div class="quantity">
                  <div class="pro-qty">
                    <input type="number" v-model="quantity" :disabled="true" />
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
                  <b>Số lượng</b> <span>{{ productDetail.amount }}</span>
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
                  style="font-size: 16px;"
                >
                  {{ productDetail.description }}
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
              <h2>Sản phẩm liên quan</h2>
            </div>
          </div>
        </div>
        <div class="row">
          <div
            v-for="(item, index) in productListPaginate"
            :key="index"
            class="col-lg-3 col-md-4 col-sm-6"
          >
            <div class="product__item">
              <div class="product__item__pic set-bg">
                <img :src="item.mainImg" height="300" alt="" />
              </div>
              <div class="product__item__text">
                <h6>
                  <a @click="showProductDetail(item.productId)">{{
                    item.productName
                  }}</a>
                </h6>
                <h5>{{ formatPrice(item.sellPrice) }}đ</h5>
              </div>
            </div>
          </div>
        </div>
        <div class="t-mx-auto t-w-fit">
          <b-pagination
            v-model="currentPage"
            :total-rows="listProductbyCategory.length"
            :per-page="pagination.perPage"
            aria-controls="my-table"
            @change="onPageChanged"
          ></b-pagination>
        </div>
      </div>
    </section>
    <!-- Related Product Section End -->

    <!-- Footer Section Begin -->
    <UserFooter />
    <!-- Footer Section End -->
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { handleJQuery, verifyAccountRole, botChatAI } from "@/common/utils";
import baseMixins from "../components/mixins/base";
import moment from "moment-timezone";
import { formatPriceSearchV2 } from "@/common/common";
import UserHeader from "../Layout/Components/UserHeader.vue";
import UserFooter from "../Layout/Components/UserFooter.vue";
import Humberger from "../Layout/Components/Humberger.vue";
import SectionBegin from "../Layout/Components/SectionBegin.vue";
import {
  FETCH_REVIEWS,
  UPDATE_REVIEW,
  CREATE_REVIEW,
} from "@/store/action.type";
export default {
  name: "ShopDetail",
  components: { UserHeader, UserFooter, Humberger, SectionBegin },
  mixins: [baseMixins],
  props: {},
  data() {
    return {
      listProductbyCategory: null,
      productDetail: null,
      cateId: null,
      listImg: [],
      quantity: 1,
      currentProductId: null,
      currentUserId: null,
      currentNav: "reviews",
      newReview: null,
      productReviews: [],
      productListPaginate: null,
      pagination: {
        currentPage: 1,
        perPage: 4,
        totalRows: 6,
      },
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
    };
  },
  mounted() {
    // handleJQuery();
    botChatAI();
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
        this.cateId = res.data.data.category.categoryId;
        const resCate = await this.getWithBigInt(
          `/rest/categories`,
          this.cateId
        );
        if (resCate && resCate.data && resCate.data.data) {
          this.listProductbyCategory = resCate.data.data.products;
          this.pagination.totalRows = resCate.data.data.products.length;
          this.productListPaginate = resCate.data.data.products.slice(
            0,
            this.pagination.perPage
          );
        }
      }
    },
    onPageChanged(page) {
      this.pagination.currentPage = page;
      this.productListPaginate = this.listProductbyCategory.slice(
        (page - 1) * this.pagination.perPage,
        page * this.pagination.perPage
      );
    },
    showProductDetail(id) {
      this.$router.push({ path: `/shop-detail/${id}` });
      this.$router.go(this.$router.currentRoute);
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
