<template>
  <div>
    <Humberger />
    <!--    Humberger End-->
    <!--    Header Section Begin-->
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
                  placeholder="Bạn muốn tìm sản phẩm nào?"
                  v-model="searchValue"
                />
                <button
                  :disabled="!searchValue || searchValue.trim() === ''"
                  @click="navigateToSearch(`/search`)"
                  class="site-btn"
                >
                  TÌM KIẾM
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
            <div
              class="hero__item set-bg"
              data-setbg="https://img5.thuthuatphanmem.vn/uploads/2021/10/02/ngon-co-xanh-non-tu-tu-troi-len-khoi-mat-dat-mem-mai-va-xanh_K668e_045032333.jpg"
            >
              <div class="hero__text">
                <span>TREE WORLD</span>
                <h2>
                  Hãy ngắm nhìn thiên nhiên <br />để thấy tâm hồn chính mình
                </h2>
                <p>Vận chuyển tận tình</p>
                <a href="/shop-product" class="primary-btn">Mua ngay</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Hero Section End -->

    <!-- Categories Section Begin -->
    <section class="">
      <VueSlickCarousel class="t-w-4/5 t-block t-mx-auto" v-bind="settings">
        <div v-for="(item, index) in listTypeProduct" :key="index" class="p-5">
          <img :src="item.img" height="300" width="350" />
          <h5 class="t-text-center block p-4">
            <a @click="handleGetProductbyCategory(item.categoryId)">{{
              item.name
            }}</a>
          </h5>
        </div>
      </VueSlickCarousel>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
    <section class="featured spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="section-title">
              <h2>Sản phẩm</h2>
            </div>
            <!-- <div class="featured__controls">
              <ul>
                <li class="active" data-filter="*">All</li>
                <li data-filter=".oranges">Oranges</li>
                <li data-filter=".fresh-meat">Fresh Meat</li>
                <li data-filter=".vegetables">Vegetables</li>
                <li data-filter=".fastfood">Fastfood</li>
              </ul>
            </div> -->
          </div>
        </div>
        <div class="row row-cols-3">
          <div
            v-for="(item, index) in productListPaginate"
            :key="index"
            class="col"
          >
            <div class="featured__item">
              <div class="featured__item__pic">
                <img :src="item.mainImg" />
                <ul class="featured__item__pic__hover">
                  <li>
                    <div @click="addToCart(item.productId)">
                      <a><font-awesome-icon icon="fa fa-shopping-cart"/></a>
                    </div>
                  </li>
                </ul>
              </div>
              <div class="featured__item__text">
                <h6 @click="showProductDetail(item.productId)">
                  <span>{{ item.productName }}</span>
                </h6>
                <h5>{{ formatPrice(item.sellPrice) }}đ</h5>
              </div>
            </div>
          </div>
        </div>
        <div class="t-mx-auto t-w-fit">
          <b-pagination
            v-model="pagination.currentProductPage"
            :total-rows="listProduct.length"
            :per-page="pagination.perPage"
            aria-controls="my-table"
            @change="onProductPageChanged"
          ></b-pagination>
        </div>
      </div>
    </section>
    <!-- Featured Section End -->

    <!-- Banner Begin -->
    <div class="banner">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-md-6 col-sm-6">
            <div class="banner__pic">
              <img src="img/banner/banner-1.jpg" alt="" />
            </div>
          </div>
          <div class="col-lg-6 col-md-6 col-sm-6">
            <div class="banner__pic">
              <img src="img/banner/banner-2.jpg" alt="" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Banner End -->

    <!-- Latest Product Section Begin -->
    <section class="latest-product spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-md-6">
            <div class="latest-product__text">
              <h4>Sản phẩm mới</h4>
              <div
                v-for="(item, index) in topProduct"
                :key="index"
                class="latest-product__slider owl-carousel"
              >
                <div class="latest-prdouct__slider__item">
                  <a
                    class="latest-product__item"
                    @click="showProductDetail(item.productId)"
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
          <div class="col-lg-4 col-md-6">
            <div class="latest-product__text">
              <h4>Sản phẩm bán chạy</h4>
              <div
                v-for="(item, index) in rateProduct"
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
          <div class="col-lg-4 col-md-6">
            <div class="latest-product__text">
              <h4>Đề cử cho bạn</h4>
              <div
                v-for="(item, index) in reviewProduct"
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
    </section>
    <!-- Latest Product Section End -->

    <!-- Blog Section Begin -->
    <section class="from-blog spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="section-title">
              <h2>Tin tức</h2>
            </div>
            <!-- <div class="featured__controls">
              <ul>
                <li class="active" data-filter="*">All</li>
                <li data-filter=".oranges">Oranges</li>
                <li data-filter=".fresh-meat">Fresh Meat</li>
                <li data-filter=".vegetables">Vegetables</li>
                <li data-filter=".fastfood">Fastfood</li>
              </ul>
            </div> -->
          </div>
        </div>
        <div class="row row-cols-3">
          <div
            v-for="(item, index) in listPostPaginate"
            :key="index"
            class="col"
          >
            <div class="blog__item" @click="showBlogDetail(item.postId)">
              <div class="blog__item__pic">
                <img
                  :src="item.image_link_thumbnail"
                  width="300"
                  height="300"
                />
              </div>
              <div class="blog__item__text">
                <h5>
                  <a>{{ item.title }}</a>
                </h5>
              </div>
            </div>
          </div>
        </div>
        <div class="t-mx-auto t-w-fit">
          <b-pagination
            v-model="pagination.currentPostPage"
            :total-rows="listPost.length"
            :per-page="pagination.perPage"
            aria-controls="my-table"
            @change="onPostPageChanged"
          ></b-pagination>
        </div>
      </div>
    </section>
    <!-- Blog Section End -->

    <!-- Footer Section Begin -->
    <UserFooter />
  </div>
</template>

<script>
import VueSlickCarousel from "vue-slick-carousel";
import { handleJQuery, botChatAI, verifyAccountRole } from "@/common/utils";
import baseMixins from "../components/mixins/base";
// import { handlebotfe } from "@/common/bot-fe";
import { formatPriceSearchV2 } from "../common/common";
import UserHeader from "../Layout/Components/UserHeader";
import UserFooter from "../Layout/Components/UserFooter.vue";
import Humberger from "../Layout/Components/Humberger.vue";
import router from "../router/index";
export default {
  name: "HomePage",
  components: { VueSlickCarousel, UserHeader, UserFooter, Humberger },
  mixins: [baseMixins],
  data() {
    return {
      searchValue: null,
      listProduct: [],
      topProduct: [],
      productListPaginate: [],
      listCategory: [],
      listPost: [],
      rateProduct: [],
      reviewProduct: [],
      listPostPaginate: [],
      productDetail: null,
      quantity: 1,
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
      pagination: {
        currentPostPage: 1,
        currentProductPage: 1,
        perPage: 3,
        totalRows: 6,
      },
      listTypeProduct: [
        {
          categoryId: 1,
          name: "Cây Cảnh Trong Nhà",
          img:
            "https://res.cloudinary.com/des083zke/image/upload/v1666838904/Capstone_Project_tw/gia-cay-thiet-moc-lan-dep_wb4ce1.jpg",
        },
        {
          categoryId: 2,
          name: "Cây Cảnh Phong Thủy",
          img:
            "https://res.cloudinary.com/des083zke/image/upload/v1667445194/Capstone_Project_tw/trong-cay-nguyet-que-mong-cau-vinh-quang-cho-gia-dinh-cay-nguyet-que-1-1507715523-width660height639_ejwxti.jpg",
        },
        {
          categoryId: 3,
          name: "Cây Cảnh Để Bàn",
          img:
            "https://res.cloudinary.com/des083zke/image/upload/v1667446961/Capstone_Project_tw/sen-nuda-bui-9-10cm-350-350-jpg_fk27d3.jpg",
        },
        {
          categoryId: 4,
          name: "Cây Cảnh Văn Phòng",
          img:
            "https://res.cloudinary.com/des083zke/image/upload/v1667467662/Capstone_Project_tw/cay-ngu-gia-bi-mini_wzeso7.jpg",
        },
        {
          categoryId: 5,
          name: "Cây Cảnh Loại To",
          img:
            "https://thietkesanvuonviet.com/wp-content/uploads/2020/01/cay-bong-mat-it-rung-la_14.jpg",
        },
        {
          categoryId: 6,
          name: "Cây Cảnh Sen Đá",
          img:
            "https://res.cloudinary.com/des083zke/image/upload/v1667446961/Capstone_Project_tw/sen-nuda-bui-9-10cm-350-350-jpg_fk27d3.jpg",
        },
        {
          categoryId: 7,
          name: "Cây Thủy Sinh",
          img:
            "https://wikihow.com.vn/wp-content/uploads/2020/04/cay-thuy-sinh-la-gi.jpg",
        },
        {
          categoryId: 8,
          name: "Cây Dây Leo",
          img:
            "https://mamnonhoami.edu.vn/wp-content/uploads/2021/04/cay-day-leo-bai-tho-mam-non.jpg",
        },
        {
          categoryId: 9,
          name: "Xương Rồng Cảnh",
          img:
            "https://dalatfarm.net/wp-content/uploads/2020/11/xuong-rong-kim-ho-1.jpg",
        },
      ],
      settings: {
        dots: true,
        infinite: false,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 4,
        initialSlide: 0,
        responsive: [
          {
            breakpoint: 1024,
            settings: {
              slidesToShow: 3,
              slidesToScroll: 3,
              infinite: true,
              dots: true,
            },
          },
          {
            breakpoint: 600,
            settings: {
              slidesToShow: 2,
              slidesToScroll: 2,
              initialSlide: 2,
            },
          },
          {
            breakpoint: 480,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1,
            },
          },
        ],
      },
    };
  },
  mounted() {
    handleJQuery();
    botChatAI();
    // handlebotfe();
    this.getListProduct();
    this.getListCategory();
    this.getListPost();
    this.getTopProduct();
  },
  computed: {
    verifyAccountRole() {
      return verifyAccountRole();
    },
  },
  methods: {
    navigateToSearch(url) {
      router.push({
        path: url,
        query: {
          searchValue: this.searchValue,
        },
      });
    },
    // async getDetailProduct() {
    //   const id = this.$router.currentRoute.params.id;
    //   const res = await this.getWithBigInt(`/rest/products`, id);
    //   if (res && res.data && res.data.data) {
    //     this.productDetail = res.data.data;
    //   }
    // },
    async addToCart(productId) {
      if (!this.verifyAccountRole) {
        this.$router.push({ path: `/login` });
        return;
      }
      const userId = this.userInfo ? this.userInfo.userId : null;
      // const productId = this.$router.currentRoute.params.id;

      console.log(this.$route);
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
        // this.getDetailProduct();
      }
    },
    async handleGetProductbyCategory(categoryId) {
      this.$router.push({ path: `/product-category/${categoryId}` });
      if (this.$route.name == "CategoryProduct") {
        this.$router.go(this.$router.currentRoute);
      }
      console.log(this.$route);
    },

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
        this.rateProduct = res.data.data.slice(2, 8);
        this.reviewProduct = res.data.data.slice(9, 15);
      }
    },
    async getTopProduct() {
      // const res = await clientService.getListProduct()
      const res = await this.getWithBigInt("/rest/products/lastSixProducts");
      if (res && res.data && res.data.data) {
        this.topProduct = res.data.data;
      }
    },
    async getListCategory() {
      const res = await this.getWithBigInt("/rest/categories");
      if (res && res.data && res.data.data) {
        this.listCategory = res.data.data;
      }
    },
    async getListPost() {
      const res = await this.getWithBigInt("/rest/posts");
      if (res && res.data && res.data.data) {
        this.listPost = res.data.data;
        this.pagination.totalRows = res.data.data.length;
        this.listPostPaginate = res.data.data.slice(0, this.pagination.perPage);
      }
    },
    showProductDetail(id) {
      this.$router.push({ path: `/shop-detail/${id}` });
    },
    showBlogDetail(id) {
      this.$router.push({ path: `/blog-detail/${id}` });
    },
    onProductPageChanged(page) {
      this.pagination.currentPage = page;
      this.productListPaginate = this.listProduct.slice(
        (page - 1) * this.pagination.perPage,
        page * this.pagination.perPage
      );
    },
    onPostPageChanged(page) {
      this.pagination.currentPostPage = page;
      this.listPostPaginate = this.listPost.slice(
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
<style lang="css">
.listCate {
  margin-bottom: 0px;
}
.featured__item {
  cursor: pointer;
}
</style>
