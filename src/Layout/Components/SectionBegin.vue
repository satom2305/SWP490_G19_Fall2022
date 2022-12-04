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
                  Tìm kiếm sản phẩm
                  <span class="arrow_carrot-down"></span>
                </div>
                <input type="text" placeholder="Bạn muốn tìm sản phẩm nào?" v-model="searchValue" />
                <button :disabled="(!searchValue || searchValue.trim() === '')" @click="navigateToSearch(`/search`)" class="site-btn">
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
  export default {
    name: "SectionBegin",
    components: {},
    mixins: [baseMixins],
    data() {
      return {
        searchValue: null,
        listCategory: [],
      };
    },
    mounted() {
      handleJQuery();
      // handlebotfe();
      this.getListCategory();
    },
    methods: {
      navigateToSearch(url) {
        router.push({
          path: url,
          query: {
            searchValue: this.searchValue
          }
        })
      },
      
      async getListCategory() {
        const res = await this.getWithBigInt("/rest/categories");
        if (res && res.data && res.data.data) {
          this.listCategory = res.data.data;
        }
      },
    },
  };
  </script>
  <style lang="css" scoped>
  .listCate {
  margin-bottom: 0px;
}
  </style>
  