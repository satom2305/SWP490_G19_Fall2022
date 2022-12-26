<template>
  <div>
    <!-- Page Preloder -->

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

    <!-- Blog Section Begin -->
    <section class="blog spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-md-5">
            <div class="blog__sidebar">
              <div class="blog__sidebar__item">
                <h4 class="title_sidebar">Có thể bạn quan tâm</h4>
                <div class="blog__sidebar__recent">
                  <a
                    v-for="(item, index) in listBlogCare"
                    :key="index"
                    class="blog__sidebar__recent__item"
                    @click="showBlogDetail(item.postId)"
                  >
                    <div class="blog__sidebar__recent__item__pic">
                      <img src="img/blog/sidebar/sr-1.jpg" alt="" />
                    </div>
                    <div class="blog__sidebar__recent__item__text">
                      <h6>
                        {{ item.title }}
                      </h6>
                      <span>{{ getDateFormat(index) }}</span>
                    </div>
                  </a>
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
                    <img
                      :src="item.image_link_thumbnail"
                      width="300"
                      height="300"
                      alt=""
                    />
                  </div>
                  <div class="blog__item__text">
                    <ul>
                      <li>{{ getDateFormat(index) }}</li>
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
    <UserFooter />
    <!-- Footer Section End -->
  </div>
</template>

<script>
import baseMixins from "../components/mixins/base";
import moment from "moment";
import UserHeader from "../Layout/Components/UserHeader";
import UserFooter from "../Layout/Components/UserFooter";
import Humberger from "../Layout/Components/Humberger";
import SectionBegin from "../Layout/Components/SectionBegin.vue";
import { handleJQuery, botChatAI } from "../common/utils";
export default {
  name: "Blog",
  components: { UserHeader, UserFooter, Humberger, SectionBegin },
  mixins: [baseMixins],
  data() {
    return {
      listBlog: null,
      listBlogCare: null,
      listBlogPaginate: null,
      pagination: {
        currentPage: 1,
        perPage: 6,
        totalRows: 6,
      },
    };
  },
  mounted() {
    botChatAI();
    this.getListBlog();
    // console.log(this.$router);
  },
  methods: {
    async getListBlog() {
      const res = await this.getWithBigInt(`/rest/posts`);
      if (res && res.data && res.data.data) {
        this.listBlog = res.data.data;
        this.listBlogCare = res.data.data.slice(0, 3);
        this.pagination.totalRows = res.data.data.length;
        this.listBlogPaginate = res.data.data.slice(0, this.pagination.perPage);
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
    getDateFormat(index) {
      return moment(this.listBlog[index].date).format("ll");
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
