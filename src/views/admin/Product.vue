<template>
  <div>
    <page-title
      :heading="heading"
      :subheading="subheading"
      :icon="icon"
      :loading="loadingHeader"
    ></page-title>
    <b-card class="main-card">
      <template v-if="loadingHeader">
        <a-skeleton active :paragraph="{ rows: 5 }"></a-skeleton>
      </template>
      <template v-else>
        <div class="search-area mb-3">
          <div class="d-flex justify-content-between">
            <div class="d-flex">
              <div style="width: 20rem" class="mr-2">
                <div class="label-form">Tên sản phẩm</div>
                <b-input
                  type="text"
                  placeholder="Nhập tên sản phẩm"
                  v-model="dataFilter.productName"
                  @input="fetchProductByName($event)"
                />
              </div>
              <b-button
                variant="primary"
                class="mr-2 mb-4 custom-btn-common"
                @click="handleSearch"
              >
                <font-awesome-icon :icon="['fas', 'search']" />
                Tìm kiếm
              </b-button>
              <b-button
                variant="light"
                @click="handleReset"
                class="mr-2 mb-4 custom-btn-common"
              >
                <font-awesome-icon :icon="['fas', 'eraser']" />
                Xóa lọc
              </b-button>
            </div>
            <div class="mt-4">
              <b-button
                variant="info"
                class="mb-4"
                @click="navigateToCreateProduct()"
                style="max-width: 200px"
              >
                <i class="fas fa-edit"></i> Thêm sản phẩm
              </b-button>
            </div>
          </div>
        </div>
        <b-table
          :items="getProducts"
          :fields="visibleFields"
          :bordered="true"
          :hover="true"
          :fixed="true"
          :per-page="dataFilter.limit"
          :current-page="dataFilter.page"
          :foot-clone="false"
        >
          <template #cell(key)="row">
            {{ dataFilter.limit * (dataFilter.page - 1) + row.index + 1 }}
          </template>
          <template #cell(category)="row">
            {{ row.item.category ? row.item.category.categoryName : "" }}
          </template>
          <template #cell(productStatus)="row" style="text-align: center">
            <b-badge
              class="mb-2 mr-2 badge-active"
              v-if="row.item.productStatus === 1"
            >
              Hoạt động
            </b-badge>
            <b-badge
              class="mb-2 mr-2 badge-inactive"
              v-if="row.item.productStatus === 2"
            >
              Không hoạt động
            </b-badge>
          </template>
          <template #cell(actions)="row">
            <div class="d-flex justify-content-center">
              <div class="px-3">
                <a
                  href="javascript:void(0)"
                  type="button"
                  v-b-tooltip.hover
                  title="Cập nhật"
                  @click.prevent="navigateToUpdateProduct(row.item)"
                >
                  <i class="fas fa-edit" style="font-size: 1.1rem"></i>
                </a>
              </div>
              <div class="px-3" v-if="row.item.productStatus === 1">
                <a
                  href="javascript:void(0)"
                  type="button"
                  v-b-tooltip.hover
                  title="Khoá sản phẩm"
                  @click.prevent="openModalDisableProduct(row.item)"
                >
                  <i
                    class="fas fa-times"
                    style="font-size: 1.1rem; color: red"
                  ></i>
                </a>
              </div>
            </div>
          </template>
        </b-table>

        <b-row v-if="getProducts && getProducts.length > 0">
          <b-col class="pagination">
            <b-pagination
              v-model="dataFilter.page"
              :per-page="dataFilter.limit"
              :total-rows="getProducts.length"
              @change="changePage"
            ></b-pagination>
          </b-col>
          <b-col class="mt-1">
            <span class="text-muted">
              {{ fromPage }} đến {{ toPage }} trên {{ getProducts.length }} bản
              ghi
            </span>
          </b-col>
        </b-row>
        <b-row v-else class="justify-content-center">
          <span>Không tìm thấy bản ghi nào</span>
        </b-row>
      </template>
    </b-card>

    <b-modal
      hide-footer
      id="disable-product"
      :title="'Xác nhận khoá sản phẩm'"
      :no-close-on-backdrop="true"
    >
      <div class="pb-3">
        Bạn có muốn khoá sản phẩm
        <span class="font-weight-bold" v-if="this.currentProduct">{{
          this.currentProduct.productName
        }}</span>
        không ?
      </div>
      <b-button
        class="mr-2 btn-light2 pull-right"
        @click="cancelDisableProduct"
      >
        Hủy
      </b-button>
      <b-button
        variant="primary pull-right"
        class="mr-2"
        type="submit"
        @click="handleDisableProduct"
      >
        Đồng ý
      </b-button>
    </b-modal>
  </div>
</template>

<script>
import PageTitle from "../../Layout/Components/PageTitle";
import baseMixins from "../../components/mixins/base";
import { required } from "vuelidate/lib/validators";
import { mapGetters } from "vuex";
import router from "@/router";
import {
  FETCH_CATEGORY,
  FETCH_PRODUCTS,
  FETCH_PRODUCTS_AVAILABLE,
  FETCH_PRODUCT_BY_ID,
  DISABLE_PRODUCT,
  FETCH_PRODUCTS_BY_NAME,
} from "@/store/action.type";
import debounce from "lodash/debounce";
const initDataFilter = {
  productName: null,
  page: 1,
  limit: 10,
};
export default {
  name: "ProductManagement",
  data() {
    this.fetchProductByName = debounce(this.fetchProductByName, 500);
    return {
      subheading: "Tạo và quản lý các sản phẩm",
      icon: "pe-7s-portfolio icon-gradient bg-happy-itmeo",
      heading: "Quản lý sản phẩm",
      loadingHeader: true,
      dataFilter: Object.assign({}, { ...initDataFilter }),
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
      currentProduct: null,
      fields: [
        {
          key: "key",
          label: "STT",
          tdClass: "align-middle",
          thClass: "align-middle",
          visible: true,
          thStyle: { width: "4%" },
          sortable: true,
        },
        {
          key: "productId",
          label: "ID sản phẩm",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-left align-middle",
          sortable: true,
        },
        {
          key: "category",
          label: "Loại sản phẩm",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-left align-middle",
          sortable: true,
        },
        {
          key: "productName",
          label: "Tên sản phẩm",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-left align-middle",
          sortable: true,
        },
        {
          key: "productStatus",
          label: "Trạng thái",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-center align-middle",
          tdClass: "text-center align-middle",
          sortable: true,
        },
        {
          key: "actions",
          label: "Chức năng",
          visible: true,
          thStyle: { width: "5%" },
          thClass: "text-center align-middle",
        },
      ],
    };
  },
  mixins: [baseMixins],
  components: {
    PageTitle,
  },
  mounted() {
    this.fetchProduct();
  },
  computed: {
    ...mapGetters(["getProducts", "getCategory"]),
    visibleFields() {
      return this.fields.filter((field) => field.visible);
    },
    fromPage() {
      return (this.dataFilter.page - 1) * this.dataFilter.limit + 1;
    },
    toPage() {
      return this.getProducts.length
        ? this.dataFilter.page * this.dataFilter.limit >=
          this.getProducts.length
          ? this.getProducts.length
          : this.dataFilter.page * this.dataFilter.limit
        : 0;
    },
  },
  methods: {
    changePage(e) {
      this.dataFilter.page = e;
    },
    handleSearch() {
      if (
        !this.dataFilter.productName ||
        this.dataFilter.productName.trim() === ""
      ) {
        this.fetchProduct();
      } else {
        this.fetchProductByName(this.dataFilter.productName);
      }
    },
    handleReset() {
      this.dataFilter.productName = null;
      this.fetchProduct();
    },
    async fetchProductByName(value) {
      if (!value || value.trim() === "") return;
      let response = await this.$store.dispatch(
        FETCH_PRODUCTS_BY_NAME,
        value.trim()
      );
      if (response && response.data)
        this.$store.commit("setProducts", response.data.data);
    },
    async fetchProduct() {
      let response = await this.$store.dispatch(FETCH_PRODUCTS);
      if (response) {
        setTimeout(() => {
          if (this.loadingHeader) this.loadingHeader = !this.loadingHeader;
        }, 200);
      }
      if (response && response.data)
        this.$store.commit("setProducts", response.data.data);
    },
    navigateToUpdateProduct(product) {
      if (!product.productId) return;
      this.$router.push({
        path: `/admin/product/update/${product.productId}`,
      });
    },
    navigateToCreateProduct() {
      this.$router.push({
        path: `/admin/product/create`,
      });
    },
    openModalDisableProduct(product) {
      this.$root.$emit("bv::show::modal", "disable-product");
      this.currentProduct = { ...product };
    },
    cancelDisableProduct() {
      this.currentProduct = null;
      this.$root.$emit("bv::hide::modal", "disable-product");
    },
    async handleDisableProduct() {
      if (!this.currentProduct || !this.currentProduct.productId) {
        this.$message.closeAll();
        this.$message({
          message: "Khoá sản phẩm không thành công.",
          type: "error",
          showClose: true,
        });
        return;
      }
      let res = await this.$store.dispatch(
        DISABLE_PRODUCT,
        this.currentProduct.productId
      );
      if (res && res.status === 200) {
        this.$message.closeAll();
        this.$message({
          message: "Khoá sản phẩm thành công.",
          type: "success",
          showClose: true,
        });
        this.fetchProduct();
        this.cancelDisableProduct();
      }
    },
  },
};
</script>

<style scoped></style>
