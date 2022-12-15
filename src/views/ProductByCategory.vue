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
                <div class="col-lg-4 col-md-4"></div>
                <div class="col-lg-4 col-md-3">
                  <div class="filter__option">
                    <span class="icon_grid-2x2"></span>
                    <span class="icon_ul"></span>
                  </div>
                </div>
              </div>
            </div>
            <div
              v-if="listProductbyCategory && listProductbyCategory.length > 0"
            >
              <div class="row">
                <div
                  v-for="item in productListPaginate"
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
                  Không tìm thấy sản phẩm nào trong danh mục
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
        </div>
      </div>
    </section>
    <!-- <section style="min-height:53vh;" class="featured spad" v-else>
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
            Không tìm thấy sản phẩm nào trong danh mục
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
// import { handleJQuery } from "../common/utils";
import baseMixins from "../components/mixins/base";
import { formatPriceSearchV2 } from "../common/common";
import UserHeader from "../Layout/Components/UserHeader";
import Humberger from "../Layout/Components/Humberger";
import UserFooter from "../Layout/Components/UserFooter";
import SectionBegin from "../Layout/Components/SectionBegin";
export default {
  name: "ProductByCategory",
  components: { UserHeader, Humberger, UserFooter, SectionBegin },
  mixins: [baseMixins],
  data() {
    return {
      productListPaginate: null,
      listProductbyCategory: null,
      topProduct: null,
      pagination: {
        currentPage: 1,
        perPage: 12,
        totalRows: 6,
      },
    };
  },
  mounted() {
    // handleJQuery();
    // handlebotfe();
    console.log(this.$route.params.id);
    this.getTopProduct();
    this.getListProductByCategory();
  },
  methods: {
    async getListProductByCategory() {
      const id = this.$router.currentRoute.params.id;
      const res = await this.getWithBigInt(`/rest/categories`, id);
      if (res) {
        this.listProductbyCategory = res.data.data.products;
        this.productListPaginate = res.data.data.products.slice(
          0,
          this.pagination.perPage
        );
      }
      console.log(this.listProductbyCategory);
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
      this.productListPaginate = this.listProductbyCategory.slice(
        (page - 1) * this.pagination.perPage,
        page * this.pagination.perPage
      );
    },
    formatPrice(price) {
      if (!price) return "";
      return formatPriceSearchV2(price + "");
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
