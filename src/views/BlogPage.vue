<template>
  <div>
    <!-- Page Preloder -->

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
    <header class="header">
      <div class="header__top">
        <div class="container">
          <div class="row">
            <div class="col-lg-6 col-md-6">
              <div class="header__top__left">
                <ul>
                  <li>
                    <font-awesome-icon icon="fa fa-envelope" />
                    hello@colorlib.com
                  </li>
                  <li>Free Shipping for all Order of $99</li>
                </ul>
              </div>
            </div>
            <div class="col-lg-6 col-md-6">
              <div class="header__top__right">
                <!--              <div class="header__top__right__social">-->
                <!--                <a href="#"><font-awesome-icon icon="fa fa-facebook"/></a>-->
                <!--                <a href="#"><font-awesome-icon icon="fa fa-twitter"/></a>-->
                <!--                <a href="#"><font-awesome-icon icon="fa fa-linkedin"/></a>-->
                <!--                <a href="#"><font-awesome-icon icon="fa fa-pinterest-p"/></a>-->
                <!--              </div>-->
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
                  <a href="#"><font-awesome-icon icon="fa fa-user" /> Login</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-lg-3">
            <div class="header__logo">
              <a href="/"><img src="@/assets/img/logo.png" alt=""/></a>
            </div>
          </div>
          <div class="col-lg-6">
            <nav class="header__menu">
              <ul>
                <li class="active"><a href="/">Home</a></li>
                <li><a href="/shop-product">Shop </a></li>
                <li>
                  <a href="#">Pages</a>
                  <ul class="header__menu__dropdown">
                    <li>
                      <a href="/shop-detail">Shop Details</a>
                    </li>
                    <li>
                      <a href="/shopping-cart">Shopping Carts</a>
                    </li>
                    <li><a href="/check-out">Check Out</a></li>
                    <li>
                      <a href="/blog-detail">Blog Details</a>
                    </li>
                  </ul>
                </li>
                <li><a href="/blog">Blog</a></li>
                <li><a href="/contact">Contact </a></li>
              </ul>
            </nav>
          </div>
          <div class="col-lg-3">
            <div class="header__cart">
              <ul>
                <li>
                  <a href="#"
                    ><font-awesome-icon icon="fa fa-heart" /> <span>1</span></a
                  >
                </li>
                <li>
                  <a href="#"
                    ><font-awesome-icon icon="fa fa-shopping-bag" />
                    <span>3</span></a
                  >
                </li>
              </ul>
              <div class="header__cart__price">item: <span>$150.00</span></div>
            </div>
          </div>
        </div>
        <div class="humberger__open">
          <font-awesome-icon icon="fa fa-bars" />
        </div>
      </div>
    </header>
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

    <!-- Blog Section Begin -->
    <section class="blog spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-md-5">
            <div class="blog__sidebar">
              <div class="blog__sidebar__search">
                <form action="#">
                  <input type="text" placeholder="Search..." />
                  <button type="submit">
                    <span class="icon_search"></span>
                  </button>
                </form>
              </div>
              <div class="blog__sidebar__item">
                <h4>Categories</h4>
                <ul>
                  <li><a href="#">All</a></li>
                  <li><a href="#">Beauty (20)</a></li>
                  <li><a href="#">Food (5)</a></li>
                  <li><a href="#">Life Style (9)</a></li>
                  <li><a href="#">Travel (10)</a></li>
                </ul>
              </div>
              <div class="blog__sidebar__item">
                <h4>Recent News</h4>
                <div class="blog__sidebar__recent">
                  <a href="#" class="blog__sidebar__recent__item">
                    <div class="blog__sidebar__recent__item__pic">
                      <img src="img/blog/sidebar/sr-1.jpg" alt="" />
                    </div>
                    <div class="blog__sidebar__recent__item__text">
                      <h6>
                        09 Kinds Of Vegetables<br />
                        Protect The Liver
                      </h6>
                      <span>MAR 05, 2019</span>
                    </div>
                  </a>
                  <a href="#" class="blog__sidebar__recent__item">
                    <div class="blog__sidebar__recent__item__pic">
                      <img src="img/blog/sidebar/sr-2.jpg" alt="" />
                    </div>
                    <div class="blog__sidebar__recent__item__text">
                      <h6>
                        Tips You To Balance<br />
                        Nutrition Meal Day
                      </h6>
                      <span>MAR 05, 2019</span>
                    </div>
                  </a>
                  <a href="#" class="blog__sidebar__recent__item">
                    <div class="blog__sidebar__recent__item__pic">
                      <img src="img/blog/sidebar/sr-3.jpg" alt="" />
                    </div>
                    <div class="blog__sidebar__recent__item__text">
                      <h6>
                        4 Principles Help You Lose <br />Weight With Vegetables
                      </h6>
                      <span>MAR 05, 2019</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="blog__sidebar__item">
                <h4>Search By</h4>
                <div class="blog__sidebar__item__tags">
                  <a href="#">Apple</a>
                  <a href="#">Beauty</a>
                  <a href="#">Vegetables</a>
                  <a href="#">Fruit</a>
                  <a href="#">Healthy Food</a>
                  <a href="#">Lifestyle</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-8 col-md-7">
            <div class="row">
              <div
                v-for="(item, index) in listBlogPaginate"
                :key="index"
                class="col-lg-6 col-md-6 col-sm-6"
                @click="showBlogDetail(item.postId)"
              >
                <div class="blog__item">
                  <div class="blog__item__pic">
                    <img :src="item.image_link_thumbnail" alt="" />
                  </div>
                  <div class="blog__item__text">
                    <ul>
                      <li>{{ dateFormat }}</li>
                    </ul>
                    <h5>
                      <a>{{ item.title }}</a>
                    </h5>
                  </div>
                </div>
              </div>
            </div>
            <div class="t-mx-auto t-w-fit">
              <b-pagination
                v-model="currentPage"
                :total-rows="listBlog.length"
                :per-page="pagination.perPage"
                aria-controls="my-table"
                @change="onPageChanged"
              ></b-pagination>
            </div>
          </div>
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
import moment from "moment";
export default {
  name: "BlogPage",
  mixins: [baseMixins],
  data() {
    return {
      listBlog: null,
      listBlogPaginate: null,
      dateFormat: null,
      pagination: {
        currentPage: 1,
        perPage: 6,
        totalRows: 6,
      },
    };
  },
  mounted() {
    handleJQuery();
    this.getListBlog();
    // console.log(this.$router);
  },
  methods: {
    async getListBlog() {
      const res = await this.getWithBigInt(`/rest/posts`);
      if (res && res.data && res.data.data) {
        this.listBlog = res.data.data;
        this.pagination.totalRows = res.data.data.length;
        this.listBlogPaginate = res.data.data.slice(0, this.pagination.perPage);
        this.dateFormat = moment(res.data.data.date).format("ll");
        console.log(this.listBlog);
      }
    },
    showBlogDetail(id) {
      this.$router.push({ path: `/blog-detail/${id}` });
    },
    onPageChanged(page) {
      this.listBlogPaginate = this.listBlog.slice(
        (page - 1) * this.pagination.perPage,
        page * this.pagination.perPage
      );
    },
  },
};
</script>

<style scoped></style>
