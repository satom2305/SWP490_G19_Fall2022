<template>
    <div>
      <div class="humberger__menu__overlay"></div>
      <div class="humberger__menu__wrapper">
        <div class="humberger__menu__logo">
          <a href="/"><img src="img/logo.png" alt=""/></a>
        </div>
        <div class="humberger__menu__cart">
          <ul>
            <li>
              <a href="#">
                <font-awesome-icon icon="fa fa-heart" /> <span>1</span>
              </a>
            </li>
            <li>
              <a href="#">
                <font-awesome-icon icon="fa fa-shopping-bag" /> <span>3</span>
              </a>
            </li>
          </ul>
          <div class="header__cart__price">item: <span>$150.00</span></div>
        </div>
        
        <nav class="humberger__menu__nav mobile-menu">
          <ul>
            <li class="active"><a href="/">Home</a></li>
            <li><a href="./shop-grid">Shop</a></li>
            <li>
              <a href="#">Pages</a>
              <ul class="header__menu__dropdown">
                <li><a href="./shop-detail">Shop Details</a></li>
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
        <!--    <div class="header__top__right__social">-->
        <!--      <a href="#"><font-awesome-icon icon="fa fa-facebook"/></a>-->
        <!--      <a href="#"><font-awesome-icon icon="fa fa-twitter"/></a>-->
        <!--      <a href="#"><font-awesome-icon icon="fa fa-linkedin"/></a>-->
        <!--      <a href="#"><font-awesome-icon icon="fa fa-pinterest-p"/></a>-->
        <!--    </div>-->
        <div class="humberger__menu__contact">
          <ul>
            <li>
              <font-awesome-icon icon="fa fa-envelope" /> hello@colorlib.com
            </li>
            <li>Free Shipping for all Order of $99</li>
          </ul>
        </div>
      </div>
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
                  class="listCate"
                  v-for="(item, index) in listCategory"
                  :key="index"
                >
                  <li>
                    <a href="#">{{ item.categoryName }}</a>
                  </li>
                </ul>
              </div>
            </div>
            <div class="col-lg-9">
              <div class="hero__search">
                <div class="hero__search__form">
                  <div class="hero__search__categories">
                    All Categories
                    <span class="arrow_carrot-down"></span>
                  </div>
                  <input type="text" v-model="searchValue" placeholder="Bạn muốn tìm sản phẩm nào?" />
                  <button :disabled="!searchValue " @click="fetchProductByName(searchValue)" class="site-btn">SEARCH</button>
                </div>
                <div class="hero__search__phone">
                  <div class="hero__search__phone__icon">
                    <font-awesome-icon icon="fa fa-phone" />
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
  
      <!-- Categories Section Begin -->
      <section class="featured spad" v-if="(seachProductListPaginate && seachProductListPaginate.length > 0)">
        <div class="container">
        <div class="row row-cols-3">
          <div v-for="(item, index) in seachProductListPaginate" :key="index" class="col">
            <div class="featured__item" @click="showProductDetail(item.productId)">
              <div class="featured__item__pic">
                <img :src="item.mainImg" />
                <ul class="featured__item__pic__hover">
                  <li>
                    <a href="#">
                      <font-awesome-icon icon="fa fa-shopping-cart" />
                    </a>
                  </li>
                </ul>
              </div>
              <div class="featured__item__text">
                <h6>
                  <span>{{ item.productName }}</span>
                </h6>
                <h5>{{ formatPrice(item.sellPrice) }}đ</h5>
              </div>
            </div>
          </div>
        </div>
        <div class="t-mx-auto t-w-fit">
          <b-pagination v-model="searchPagination.currentProductPage" :total-rows="listSearchProduct.length" :per-page="searchPagination.perPage"
            aria-controls="my-table" @change="onSearchProductPageChanged"></b-pagination>
        </div>
        </div>
      </section>
      <section class="featured spad" v-else>
        <div class="container">
          <div class="d-flex justify-content-center flex-column align-items-center">
            <div>
              <i class="fa-solid fa-magnifying-glass" style="color: #b6b6b6; font-size: 2rem;"></i>
            </div>
            <div class="custom-empty-content my-2">Không tìm thấy sản phẩm nào</div>
          </div>
        </div>
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
              <div
                class="featured__item"
                @click="showProductDetail(item.productId)"
              >
                <div class="featured__item__pic">
                  <img :src="item.mainImg" />
                  <ul class="featured__item__pic__hover">
                    <li>
                      <a href="#">
                        <font-awesome-icon icon="fa fa-shopping-cart" />
                      </a>
                    </li>
                  </ul>
                </div>
                <div class="featured__item__text">
                  <h6>
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
                    <a href="#" class="latest-product__item">
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
                    <a href="#" class="latest-product__item">
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
                <h4>Đánh giá cao</h4>
                <div
                  v-for="(item, index) in reviewProduct"
                  :key="index"
                  class="latest-product__slider owl-carousel"
                >
                  <div class="latest-prdouct__slider__item">
                    <a href="#" class="latest-product__item">
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
      <footer class="footer spad">
        <div class="container">
          <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
              <div class="footer__about">
                <div class="footer__about__logo">
                  <a href="/"><img src="img/logo.png" alt=""/></a>
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
                <!--            <div class="footer__widget__social">-->
                <!--              <a href="#"><font-awesome-icon icon="fa fa-facebook"/></a>-->
                <!--              <a href="#"><font-awesome-icon icon="fa fa-instagram"/></a>-->
                <!--              <a href="#"><font-awesome-icon icon="fa fa-twitter"/></a>-->
                <!--              <a href="#"><font-awesome-icon icon="fa fa-pinterest"/></a>-->
                <!--            </div>-->
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-12">
              <div class="footer__copyright">
                <div class="footer__copyright__text">
                  <p>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright &copy; All rights reserved | This template is made
                    with
                    <font-awesome-icon icon="fa fa-heart" aria-hidden="true" /> by
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
    </div>
  </template>
    
  <script>
    import VueSlickCarousel from "vue-slick-carousel";
    import { handleJQuery, botChatAI } from "@/common/utils";
    import baseMixins from "../components/mixins/base";
    // import { handlebotfe } from "@/common/bot-fe";
    import { formatPriceSearchV2 } from "../common/common";
    import UserHeader from "../Layout/Components/UserHeader";
    import { FETCH_PRODUCTS_BY_NAME } from "@/store/action.type";
    export default {
      name: "HomePage",
      components: { VueSlickCarousel, UserHeader },
      mixins: [baseMixins],
      data() {
        return {
          searchValue: null,
          listSearchProduct: [],
          listProduct: [],
          topProduct: [],
          seachProductListPaginate: [],
          productListPaginate: [],
          listCategory: [],
          listPost: [],
          rateProduct: [],
          reviewProduct: [],
          listPostPaginate: [],
          searchPagination: {
            currentPostPage: 1,
            currentProductPage: 1,
            perPage: 3,
            totalRows: 6,
          },
          pagination: {
            currentPostPage: 1,
            currentProductPage: 1,
            perPage: 3,
            totalRows: 6,
          },
          listTypeProduct: [
            {
              name: "Cây Cảnh Trong Nhà",
              img:
                "https://res.cloudinary.com/des083zke/image/upload/v1666838904/Capstone_Project_tw/gia-cay-thiet-moc-lan-dep_wb4ce1.jpg",
            },
            {
              name: "Cây Cảnh Phong Thủy",
              img:
                "https://res.cloudinary.com/des083zke/image/upload/v1667445194/Capstone_Project_tw/trong-cay-nguyet-que-mong-cau-vinh-quang-cho-gia-dinh-cay-nguyet-que-1-1507715523-width660height639_ejwxti.jpg",
            },
            {
              name: "Cây Cảnh Để Bàn",
              img:
                "https://res.cloudinary.com/des083zke/image/upload/v1667446961/Capstone_Project_tw/sen-nuda-bui-9-10cm-350-350-jpg_fk27d3.jpg",
            },
            {
              name: "Cây Cảnh Văn Phòng",
              img:
                "https://res.cloudinary.com/des083zke/image/upload/v1667467662/Capstone_Project_tw/cay-ngu-gia-bi-mini_wzeso7.jpg",
            },
            {
              name: "Cây Cảnh Loại To",
              img:
                "https://thietkesanvuonviet.com/wp-content/uploads/2020/01/cay-bong-mat-it-rung-la_14.jpg",
            },
            {
              name: "Cây Cảnh Sen Đá",
              img:
                "https://res.cloudinary.com/des083zke/image/upload/v1667446961/Capstone_Project_tw/sen-nuda-bui-9-10cm-350-350-jpg_fk27d3.jpg",
            },
            {
              name: "Cây Thủy Sinh",
              img:
                "https://wikihow.com.vn/wp-content/uploads/2020/04/cay-thuy-sinh-la-gi.jpg",
            },
            {
              name: "Cây Dây Leo",
              img:
                "https://mamnonhoami.edu.vn/wp-content/uploads/2021/04/cay-day-leo-bai-tho-mam-non.jpg",
            },
            {
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
  
        let searchValue = this.$route.query.searchValue
        if (searchValue && searchValue.trim() !== '') {
          this.searchValue = searchValue;
          this.fetchProductByName(searchValue);
        }
        this.getListProduct();
        this.getListCategory();
        this.getListPost();
        this.getTopProduct();
      },
      methods: {
        async fetchProductByName(value) {
          if (!value || value.trim() === '') return
          let res = await this.$store.dispatch(FETCH_PRODUCTS_BY_NAME, value.trim());
          if (res && res.data) {
            this.listSearchProduct = res.data.data;
            this.searchPagination.totalRows = res.data.data.length;
            this.seachProductListPaginate = res.data.data.slice(
              0,
              this.searchPagination.perPage
            );
          }
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
            this.rateProduct = res.data.data.slice(3, 9);
          }
        },
        async getTopProduct() {
          // const res = await clientService.getListProduct()
          const res = await this.getWithBigInt("/rest/products/lastSixProducts");
          if (res && res.data && res.data.data) {
            this.topProduct = res.data.data;
            this.reviewProduct = res.data.data.reverse();
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
        onSearchProductPageChanged(page) {
          this.searchPagination.currentPage = page;
          this.seachProductListPaginate = this.listSearchProduct.slice(
            (page - 1) * this.searchPagination.perPage,
            page * this.searchPagination.perPage
          );
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
    .custom-empty-content {
      color: #b6b6b6;
      font-size: 1.1rem;
      font-weight: 700;
    }
    </style>
    