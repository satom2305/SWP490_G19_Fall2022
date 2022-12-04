<template>
  <div>
    <!-- Page Preloder -->

    <!-- Humberger Begin -->
    <Humberger/>
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <UserHeader />
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <SectionBegin/>
    
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
                :total-rows="pagination.totalRows"
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
    <UserFooter/>
    <!-- Footer Section End -->
  </div>
</template>

<script>
import { handleJQuery } from "../common/utils";
import baseMixins from "../components/mixins/base";
import moment from "moment";
import UserHeader from "../Layout/Components/UserHeader";
import UserFooter from "../Layout/Components/UserFooter";
import Humberger from "../Layout/Components/Humberger";
import SectionBegin from "../Layout/Components/SectionBegin.vue";
export default {
  name: "BlogPage",
  components: { UserHeader,UserFooter,Humberger,SectionBegin },
  mixins: [baseMixins],
  data() {
    return {
      listBlog: null,
      listBlogPaginate: null,
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

<style scoped></style>
