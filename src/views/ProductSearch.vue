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
    <section class="hero">
      <div class="container">
        <div class="row">
          <div class="col-lg-3">
            <div class="hero__categories">
              <div class="hero__categories__all">
                <font-awesome-icon icon="fa fa-bars" />
                <span>Danh Mục</span>
              </div>
              <ul
                @click="handleGetProductbyCategory(item.categoryId)"
                class="listCate"
                v-for="(item, index) in listCategory"
                :key="index"
              >
                <li>
                  <a>{{ item.categoryName }}</a>
                </li>
              </ul>
            </div>
          </div>
          <div class="col-lg-9">
            <div class="hero__search">
              <div class="hero__search__form">
                <div class="hero__search__categories">
                  Tìm kiếm sản phẩm
                  <span class="arrow_carrot-down"></span>
                </div>
                <input
                  type="text"
                  v-model="searchValue"
                  placeholder="Bạn muốn tìm sản phẩm nào?"
                />
                <button
                  :disabled="!searchValue"
                  @click="fetchProductByName(searchValue)"
                  class="site-btn"
                >
                  SEARCH
                </button>
              </div>
              <div class="hero__search__phone">
                <div class="hero__search__phone__icon">
                  <font-awesome-icon icon="fa fa-phone" />
                </div>
                <div class="hero__search__phone__text">
                  <h5>+84384137197</h5>
                  <span>Hỗ trợ 24/7</span>
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

    <!-- Product Section Begin -->
    <section class="product spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-5">
            <div class="sidebar">
              <!-- <div class="sidebar__item">
                <h4>Department</h4>
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
                </ul>
              </div> -->
              <div class="sidebar__item">
                <div class="latest-product__text">
                  <h4>Sản phẩm mới</h4>
                  <div
                    v-for="(item, index) in topProduct"
                    :key="index"
                    class="latest-product__slider owl-carousel"
                  >
                    <div class="latest-prdouct__slider__item">
                      <a
                        @click="showProductDetail(item.productId)"
                        class="latest-product__item"
                      >
                        <div class="latest-product__item__pic">
                          <img :src="item.mainImg" alt="" />
                        </div>
                        <div class="latest-product__item__text">
                          <h6>{{ item.productName }}</h6>
                          <span>{{ formatPrice(item.sellPrice) }}đ</span>
                        </div>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-9 col-md-7">
            <div class="filter__item">
              <div class="row">
                <div class="col-lg-4 col-md-5">
                  <!-- <div class="filter__sort">
                    <span>Sắp xếp theo</span>
                    <select>
                      <option value="0">Giá tăng dần</option>
                      <option value="0">Giá giảm dần</option>
                    </select>
                  </div> -->
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="filter__found">
                    <!-- <h6><span>16</span> Sản phẩm</h6> -->
                  </div>
                </div>
                <div class="col-lg-4 col-md-3">
                  <div class="filter__option">
                    <span class="icon_grid-2x2"></span>
                    <span class="icon_ul"></span>
                  </div>
                </div>
              </div>
            </div>
            <div
              v-if="
                seachProductListPaginate && seachProductListPaginate.length > 0
              "
            >
              <div class="row">
                <div
                  v-for="item in seachProductListPaginate"
                  :key="item"
                  class="col-lg-4 col-md-6 col-sm-6"
                >
                  <div
                    @click="showProductDetail(item.productId)"
                    class="product__item"
                  >
                    <div class="product__item__pic set-bg">
                      <img :src="item.mainImg" alt="" />
                      <!-- <ul class="product__item__pic__hover">
                        <li>
                          <a href="#"><i class="fa fa-shopping-cart"></i></a>
                        </li>
                      </ul> -->
                    </div>
                    <div class="product__item__text">
                      <h6>
                        <a>{{ item.productName }}</a>
                      </h6>
                      <h5>{{ formatPrice(item.sellPrice) }}đ</h5>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div v-else class="container">
              <div
                class="d-flex justify-content-center flex-column align-items-center"
              >
                <div>
                  <i
                    class="fa-solid fa-magnifying-glass"
                    style="color: #b6b6b6; font-size: 2rem;"
                  ></i>
                </div>
                <div class="custom-empty-content my-2">
                  Không tìm thấy sản phẩm nào
                </div>
              </div>
            </div>
            <!-- <div class="row">
              <div
                v-for="item in seachProductListPaginate"
                :key="item"
                class="col-lg-4 col-md-6 col-sm-6"
              >
                <div
                  @click="showProductDetail(item.productId)"
                  class="product__item"
                >
                  <div class="product__item__pic set-bg">
                    <img :src="item.mainImg" alt="" />
                    <ul class="product__item__pic__hover">
                      <li>
                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                      </li>
                    </ul>
                  </div>
                  <div class="product__item__text">
                    <h6>
                      <a>{{ item.productName }}</a>
                    </h6>
                    <h5>{{ formatPrice(item.sellPrice) }}đ</h5>
                  </div>
                </div>
              </div>
            </div> -->
            <div class="t-mx-auto t-w-fit">
              <b-pagination
                v-model="searchPagination.currentProductPage"
                :total-rows="listSearchProduct.length"
                :per-page="searchPagination.perPage"
                aria-controls="my-table"
                @change="onSearchProductPageChanged"
              ></b-pagination>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- <section class="featured spad" v-else>
      <div class="container">
        <div
          class="d-flex justify-content-center flex-column align-items-center"
        >
          <div>
            <i
              class="fa-solid fa-magnifying-glass"
              style="color: #b6b6b6; font-size: 2rem;"
            ></i>
          </div>
          <div class="custom-empty-content my-2">
            Không tìm thấy sản phẩm nào
          </div>
        </div>
      </div>
    </section> -->
    <!-- Product Section End -->

    <!-- Footer Section Begin -->
    <UserFooter />
    <!-- Footer Section End -->
  </div>
</template>

<script>
import { handleJQuery, botChatAI } from "../common/utils";
import baseMixins from "../components/mixins/base";
import { formatPriceSearchV2 } from "../common/common";
import UserHeader from "../Layout/Components/UserHeader";
import Humberger from "../Layout/Components/Humberger";
import UserFooter from "../Layout/Components/UserFooter";
import SectionBegin from "../Layout/Components/SectionBegin";
import { FETCH_PRODUCTS_BY_NAME } from "@/store/action.type";
export default {
  name: "ShopProduct",
  components: { UserHeader, Humberger, UserFooter, SectionBegin },
  mixins: [baseMixins],
  data() {
    return {
      listProduct: null,
      topProduct: null,
      listCategory: null,
      productListPaginate: null,
      searchValue: null,
      listSearchProduct: [],
      seachProductListPaginate: [],
      searchPagination: {
        currentPostPage: 1,
        currentProductPage: 1,
        perPage: 6,
        totalRows: 6,
      },
      pagination: {
        currentPage: 1,
        perPage: 12,
        totalRows: 6,
      },
    };
  },
  mounted() {
    let searchValue = this.$route.query.searchValue;
    if (searchValue && searchValue.trim() !== "") {
      this.searchValue = searchValue;
      this.fetchProductByName(searchValue);
    }
    handleJQuery();
    // handlebotfe();
    botChatAI();
    this.getListProduct();
    this.getTopProduct();
    this.getListCategory();
  },
  methods: {
    async fetchProductByName(value) {
      if (!value || value.trim() === "") return;
      let res = await this.$store.dispatch(
        FETCH_PRODUCTS_BY_NAME,
        value.trim()
      );
      if (res && res.data) {
        this.listSearchProduct = res.data.data;
        this.searchPagination.totalRows = res.data.data.length;
        this.seachProductListPaginate = res.data.data.slice(
          0,
          this.searchPagination.perPage
        );
      }
    },
    async getListCategory() {
      const res = await this.getWithBigInt("/rest/categories");
      if (res && res.data && res.data.data) {
        this.listCategory = res.data.data;
      }
    },
    onSearchProductPageChanged(page) {
      this.searchPagination.currentPage = page;
      this.seachProductListPaginate = this.listSearchProduct.slice(
        (page - 1) * this.searchPagination.perPage,
        page * this.searchPagination.perPage
      );
    },
    // onProductPageChanged(page) {
    //   this.pagination.currentPage = page;
    //   this.productListPaginate = this.listProduct.slice(
    //     (page - 1) * this.pagination.perPage,
    //     page * this.pagination.perPage
    //   );
    // },
    async getListProduct() {
      // const res = await clientService.getListProduct()
      const res = await this.getWithBigInt("/rest/products/listProduct");
      if (res && res.data && res.data.data) {
        this.listProduct = res.data.data;
        this.pagination.totalRows = res.data.data.length;
        this.productListPaginate = res.data.data.slice(
          0,
          this.pagination.perPage
        );
        console.log(this.listProduct);
      }
    },
    showProductDetail(id) {
      this.$router.push({ path: `/shop-detail/${id}` });
    },
    async getTopProduct() {
      // const res = await clientService.getListProduct()
      const res = await this.getWithBigInt("/rest/products/lastSixProducts");
      if (res && res.data && res.data.data) {
        this.topProduct = res.data.data;
      }
    },
    onPageChanged(page) {
      this.pagination.currentPage = page;
      this.productListPaginate = this.listProduct.slice(
        (page - 1) * this.pagination.perPage,
        page * this.pagination.perPage
      );
    },
    formatPrice(price) {
      if (!price) return "";
      return formatPriceSearchV2(price + "");
    },
    async handleGetProductbyCategory(categoryId) {
      this.$router.push({ path: `/product-category/${categoryId}` });
      if (this.$route.name == "CategoryProduct") {
        this.$router.go(this.$router.currentRoute);
      }
      console.log(this.$route);
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
.listCate {
  margin-bottom: 0px;
}
.hero__categories {
  position: absolute;
  top: 0;
  width: 90%;
  z-index: 1000000;
  background: #fff;
}
.row {
  position: relative;
}
.hero__categories ul {
  display: none;
}
</style>
