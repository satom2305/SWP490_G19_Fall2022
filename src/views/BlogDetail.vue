<template>
  <div>
    <!--  &lt;!&ndash; Page Preloder &ndash;&gt;-->
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

    <!-- Blog Details Hero Begin -->
    <!-- Blog Details Hero End -->

    <!-- Blog Details Section Begin -->
    <section class="blog-details spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-md-5 order-md-1 order-2">
            <div class="blog__sidebar">
              <div class="blog__sidebar__item">
                <h4>Tin liên quan</h4>
                <div class="blog__sidebar__recent">
                  <a
                    v-for="(item, index) in listBlogCare"
                    :key="index"
                    class="blog__sidebar__recent__item"
                  >
                    <div class="blog__sidebar__recent__item__pic">
                      <img src="img/blog/sidebar/sr-1.jpg" alt="" />
                    </div>
                    <div class="blog__sidebar__recent__item__text">
                      <h6>{{ item.title }}</h6>
                      <span>{{ dateFormat }}</span>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-8 col-md-7 order-md-1 order-1">
            <div class="blog__details__text">
              <div>
                <h2 style="font-weight: bold;">{{ blogDetail.title }}</h2>
                <ul>
                  <li>By {{ blogDetail.user.username }}</li>
                  <li>{{ dateFormat }}</li>
                </ul>
              </div>
              <img :src="blogDetail.image_link_detail" alt="" />
              <p>
                {{ blogDetail.content }}
              </p>
            </div>
            <!-- <div class="blog__details__content">
              <div class="row">
                <div class="col-lg-6">
                  <div class="blog__details__author">
                    <div class="blog__details__author__pic">
                      <img src="img/blog/details/details-author.jpg" alt="" />
                    </div>
                    <div class="blog__details__author__text">
                      <h6>Michael Scofield</h6>
                      <span>Admin</span>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6">
                  <div class="blog__details__widget">
                    <ul>
                      <li><span>Categories:</span> Food</li>
                      <li>
                        <span>Tags:</span> All, Trending, Cooking, Healthy Food,
                        Life Style
                      </li>
                    </ul>
                    <div class="blog__details__social">
                      <a href="#"><i class="fa fa-facebook"></i></a>
                      <a href="#"><i class="fa fa-twitter"></i></a>
                      <a href="#"><i class="fa fa-google-plus"></i></a>
                      <a href="#"><i class="fa fa-linkedin"></i></a>
                      <a href="#"><i class="fa fa-envelope"></i></a>
                    </div>
                  </div>
                </div>
              </div>
            </div> -->
          </div>
        </div>
      </div>
    </section>
    <!-- Blog Details Section End -->

    <!-- Related Blog Section Begin -->
    <section class="related-blog spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="section-title related-blog-title">
              <h2>Tin bạn có thể thích</h2>
            </div>
          </div>
        </div>
        <div class="row">
          <div
            v-for="(item, index) in listBlogCare"
            :key="index"
            class="col-lg-4 col-md-4 col-sm-6"
          >
            <div class="blog__item">
              <div class="blog__item__pic">
                <img :src="item.image_link_thumbnail" height="300" alt="" />
              </div>
              <div class="blog__item__text">
                <ul>
                  <li><i class="fa fa-calendar-o"></i> May 4,2019</li>
                </ul>
                <h5>
                  <a href="#">{{ item.title }}</a>
                </h5>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Related Blog Section End -->

    <!-- Footer Section Begin -->
    <UserFooter />
  </div>
</template>

<script>
import { handleJQuery } from "../common/utils";
import baseMixins from "../components/mixins/base";
import moment from "moment";
import UserHeader from "../Layout/Components/UserHeader";
import UserFooter from "../Layout/Components/UserFooter";
import Humberger from "../Layout/Components/Humberger";
import SectionBegin from "../Layout/Components/SectionBegin";
export default {
  name: "blog-detail",
  components: { UserHeader, UserFooter, Humberger, SectionBegin },
  mixins: [baseMixins],
  data() {
    return {
      currentYear: new Date().getFullYear(),
      blogDetail: null,
      dateFormat: null,
      listBlogCare: null,
    };
  },
  mounted() {
    // handleJQuery();
    this.getDetailBlog();
    this.getListBlog();
    // console.log(this.$router);
  },
  methods: {
    async getDetailBlog() {
      const id = this.$router.currentRoute.params.id;
      const res = await this.getWithBigInt(`/rest/posts`, id);
      if (res && res.data && res.data.data) {
        this.blogDetail = res.data.data;
        this.dateFormat = moment(res.data.data.date).format("ll");
        console.log(this.blogDetail);
      }
    },
    async getListBlog() {
      const res = await this.getWithBigInt(`/rest/posts`);
      if (res && res.data && res.data.data) {
        this.listBlogCare = res.data.data.slice(0, 3);
      }
    },
  },
};
</script>

<style scoped>
.blog__sidebar__item {
  width: 70%;
}
.title_sidebar {
  text-align: center;
}
</style>
