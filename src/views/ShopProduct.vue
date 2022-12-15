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
            <!-- <div class="product__discount">
              <div class="section-title product__discount__title">
                <h2>Sale Off</h2>
              </div>
              <div class="row">
                <div class="product__discount__slider owl-carousel">
                  <div class="col-lg-4">
                    <div class="product__discount__item">
                      <div
                        class="product__discount__item__pic set-bg"
                        data-setbg="img/product/discount/pd-1.jpg"
                      >
                        <div class="product__discount__percent">-20%</div>
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
                      <div class="product__discount__item__text">
                        <span>Dried Fruit</span>
                        <h5><a href="#">Raisin’n’nuts</a></h5>
                        <div class="product__item__price">
                          $30.00 <span>$36.00</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="product__discount__item">
                      <div
                        class="product__discount__item__pic set-bg"
                        data-setbg="img/product/discount/pd-2.jpg"
                      >
                        <div class="product__discount__percent">-20%</div>
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
                      <div class="product__discount__item__text">
                        <span>Vegetables</span>
                        <h5><a href="#">Vegetables’package</a></h5>
                        <div class="product__item__price">
                          $30.00 <span>$36.00</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="product__discount__item">
                      <div
                        class="product__discount__item__pic set-bg"
                        data-setbg="img/product/discount/pd-3.jpg"
                      >
                        <div class="product__discount__percent">-20%</div>
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
                      <div class="product__discount__item__text">
                        <span>Dried Fruit</span>
                        <h5><a href="#">Mixed Fruitss</a></h5>
                        <div class="product__item__price">
                          $30.00 <span>$36.00</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="product__discount__item">
                      <div
                        class="product__discount__item__pic set-bg"
                        data-setbg="img/product/discount/pd-4.jpg"
                      >
                        <div class="product__discount__percent">-20%</div>
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
                      <div class="product__discount__item__text">
                        <span>Dried Fruit</span>
                        <h5><a href="#">Raisin’n’nuts</a></h5>
                        <div class="product__item__price">
                          $30.00 <span>$36.00</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="product__discount__item">
                      <div
                        class="product__discount__item__pic set-bg"
                        data-setbg="img/product/discount/pd-5.jpg"
                      >
                        <div class="product__discount__percent">-20%</div>
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
                      <div class="product__discount__item__text">
                        <span>Dried Fruit</span>
                        <h5><a href="#">Raisin’n’nuts</a></h5>
                        <div class="product__item__price">
                          $30.00 <span>$36.00</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="product__discount__item">
                      <div
                        class="product__discount__item__pic set-bg"
                        data-setbg="img/product/discount/pd-6.jpg"
                      >
                        <div class="product__discount__percent">-20%</div>
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
                      <div class="product__discount__item__text">
                        <span>Dried Fruit</span>
                        <h5><a href="#">Raisin’n’nuts</a></h5>
                        <div class="product__item__price">
                          $30.00 <span>$36.00</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div> -->
            <div class="filter__item">
              <div class="row">
                <div class="col-lg-4 col-md-5">
                  <div class="filter__sort">
                    <span>Sắp xếp theo</span>
                    <select @change="onchange">
                      <option value="0">Mặc định</option>
                      <option value="1">Giá tăng dần</option>
                      <option value="2">Giá giảm dần</option>
                    </select>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="filter__found">
                    <h6><span>16</span> Sản phẩm</h6>
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
            <div class="t-mx-auto t-w-fit">
              <b-pagination
                v-model="currentPage"
                :total-rows="listProduct.length"
                :per-page="pagination.perPage"
                aria-controls="my-table"
                @change="onPageChanged"
              ></b-pagination>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Product Section End -->

    <!-- Footer Section Begin -->
    <UserFooter />
    <!-- Footer Section End -->
  </div>
</template>

<script>
import { handleJQuery } from "../common/utils";
import baseMixins from "../components/mixins/base";
import { formatPriceSearchV2 } from "../common/common";
import UserHeader from "../Layout/Components/UserHeader";
import Humberger from "../Layout/Components/Humberger";
import UserFooter from "../Layout/Components/UserFooter";
import SectionBegin from "../Layout/Components/SectionBegin";
export default {
  name: "ShopProduct",
  components: { UserHeader, Humberger, UserFooter, SectionBegin },
  mixins: [baseMixins],
  data() {
    return {
      listProduct: null,
      productListPaginate: null,
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
    this.getListProduct();
    this.getTopProduct();
  },
  methods: {
    async getListProduct() {
      // const res = await clientService.getListProduct()
      const res = await this.getWithBigInt("/rest/products/listAllProduct");
      if (res && res.data && res.data.data) {
        this.listProduct = res.data.data;
        this.pagination.totalRows = res.data.data.length;
        this.productListPaginate = res.data.data.slice(
          0,
          this.pagination.perPage
        );
      }
    },
    showProductDetail(id) {
      this.$router.push({ path: `/shop-detail/${id}` });
    },
   async onchange(event){
      if(event.target.value == "0"){
        const res = await this.getWithBigInt("/rest/products/listAllProduct");
        if (res && res.data && res.data.data) {
        this.listProduct = res.data.data;
        this.pagination.totalRows = res.data.data.length;
        this.productListPaginate = res.data.data.slice(
          0,
          this.pagination.perPage
        );
      }
      }
      if(event.target.value == "1"){
        const res = await this.getWithBigInt("/rest/products/sort/asc");
        if (res && res.data && res.data.data) {
        this.listProduct = res.data.data;
        this.pagination.totalRows = res.data.data.length;
        this.productListPaginate = res.data.data.slice(
          0,
          this.pagination.perPage
        );
      }
      }
      if(event.target.value == "2"){
        const res = await this.getWithBigInt("/rest/products/sort/desc");
        if (res && res.data && res.data.data) {
        this.listProduct = res.data.data;
        this.pagination.totalRows = res.data.data.length;
        this.productListPaginate = res.data.data.slice(
          0,
          this.pagination.perPage
        );
      }
      }
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
  },
};
</script>

<style scoped></style>
