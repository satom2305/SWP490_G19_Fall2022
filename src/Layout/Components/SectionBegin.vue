<template>
  <div>
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
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { handleJQuery } from "@/common/utils";
import baseMixins from "@/components/mixins/base";
import router from "@/router/index";
import { FETCH_CATEGORY_BY_ID } from "@/store/action.type";
export default {
  name: "SectionBegin",
  components: {},
  mixins: [baseMixins],
  data() {
    return {
      searchValue: null,
      listCategory: null,
      listProductbyCategory: null,
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
    this.getListCategory();
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
    async handleGetProductbyCategory(categoryId) {
      // let res = await this.$store.dispatch(FETCH_CATEGORY_BY_ID, categoryId);
      // if (res && res.data && res.data.data) {
      //   this.listProductbyCategory = res.data.data.products;
      // }
      this.$router.push({ path: `/product-category/${categoryId}` });
      if (this.$route.name == "CategoryProduct") {
        this.$router.go(this.$router.currentRoute);
      }
      console.log(this.$route);
    },
  },
};
</script>
<style lang="css" scoped>
.listCate {
  margin-bottom: 0px;
}
</style>
