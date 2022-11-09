<template>
    <div>
      <page-title :heading="heading" :subheading="subheading" :icon="icon" :loading="loadingHeader"></page-title>
      <b-card class="main-card">
        <template v-if="loadingHeader">
          <a-skeleton active :paragraph="{ rows: 5 }"></a-skeleton>
        </template>
        <template v-else>
          <!-- <div class="search-area mb-3">
            <b-row class="mb-3">
              <b-col md="2">
                <div class="label-form">Tên đăng nhập</div>
                <b-input
                  type="text"
                  placeholder="Nhập tên đăng nhập"
                  v-model.trim="dataFilter.username"
                />
              </b-col>
              <b-col md="2">
                <div class="label-form">Email</div>
                <b-input
                  type="text"
                  placeholder="Nhập email"
                  v-model.trim="dataFilter.email"
                />
              </b-col>
            </b-row>
            <b-row class="mb-3">
              <b-col md="6">
                <b-button variant="primary" class="mr-2" @click="handleSearch">
                  <font-awesome-icon :icon="['fas', 'search']" />
                  Tìm kiếm
                </b-button>
                <b-button variant="light" @click="handleResetFilter">
                  <font-awesome-icon :icon="['fas', 'eraser']" />
                  Xóa lọc
                </b-button>
              </b-col>
            </b-row>
          </div> -->
          <div class="text-right">
            <b-button variant="info" class="mb-4" @click="handleOpenModalUpdateCategory(null, false)"
              style="max-width: 200px">
              <i class="fas fa-edit"></i> Thêm loại sản phẩm
            </b-button>
          </div>
          <b-table
            :items="getCategory"
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
            <template #cell(actions)="row">
              <div class="d-flex justify-content-center">
                <div class="px-3">
                  <a href="javascript:void(0)" type="button" v-b-tooltip.hover title="Cập nhật"
                    @click.prevent="handleOpenModalUpdateCategory(row.item, true)">
                    <i class="fas fa-edit" style="font-size: 1.1rem"></i>
                  </a>
                </div>
                <div class="px-3">
                  <a href="javascript:void(0)" type="button" v-b-tooltip.hover title="Xoá"
                    @click.prevent="handleOpenModalDeleteCategory(row.item)">
                    <i class="fas fa-times" style="font-size: 1.1rem; color: red"></i>
                  </a>
                </div>
              </div>
            </template>
          </b-table>

          <b-row v-if="getCategory && getCategory.length > 0">
            <b-col class="pagination">
              <b-pagination
                v-model="dataFilter.page"
                :per-page="dataFilter.limit"
                :total-rows="getCategory.length"
                @change="changePage"
              ></b-pagination>
            </b-col>
            <b-col class="mt-1">
              <span class="text-muted">
                {{ fromPage }} đến {{ toPage }} trên {{ getCategory.length }} bản ghi
              </span>
            </b-col>
          </b-row>
        <b-row v-else class="justify-content-center">
          <span>Không tìm thấy bản ghi nào</span>
        </b-row>
        </template>
      </b-card>
  
      <b-modal id="update-category" :title="isUpdateCategory ? 'Cập nhật loại sản phẩm' : 'Thêm loại sản phẩm'"
        :no-close-on-backdrop="true" size="lg" @hidden="handleCancelUpdateCategory">
        <b-form-group>
          <label>Tên loại sản phẩm<span class="text-danger">*</span>:</label>
          <b-form-input
            id="input-name"
            v-model.trim="$v.currentData.categoryName.$model"
            maxlength="1000"
            placeholder="Nhập tên loại sản phẩm"
            :class="{ 'is-invalid': validationStatus($v.currentData.categoryName) }"
          ></b-form-input>
          <div
            v-if="!$v.currentData.categoryName.required"
            class="invalid-feedback"
          >
            Tên loại sản phẩm không được để trống.
          </div>
        </b-form-group>
        <template #modal-footer>
          <b-button class="mr-2 btn-light2 pull-right" @click="handleCancelUpdateCategory">
            Hủy
          </b-button>
          <b-button variant="primary pull-right" @click.prevent="handleSubmit">
            Đồng ý
          </b-button>
        </template>
      </b-modal>
  
      <b-modal hide-footer id="delete-category" :title="'Xác nhận xoá loại sản phẩm'" :no-close-on-backdrop="true">
        <div class="pb-3">
          Bạn có muốn xoá loại sản phẩm<span class="font-weight-bold" v-if="this.currentCategory">{{ this.currentCategory.categoryName }}</span> không ?
        </div>
        <b-button class="mr-2 btn-light2 pull-right" @click="handleCancelDeleteCategory">
          Hủy
        </b-button>
        <b-button variant="primary pull-right" class="mr-2" type="submit" @click="handleDeleteCategory">
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
  import router from '@/router';
  import { FETCH_CATEGORY, FETCH_CATEGORY_BY_ID, CREATE_CATEGORY, UPDATE_CATEGORY, DELETE_CATEGORY } from "@/store/action.type";

  
  const initCategory = {
    categoryId: null,
    categoryName: null,
  };
  
  const initDataFilter = {
    categoryName: null,
    page: 1,
    limit: 10
  };
  
  export default {
    name: "CategoryManagement",
    data() {
      return {
        subheading: "Tạo và quản lý các loại sản phẩm",
        icon: "pe-7s-portfolio icon-gradient bg-happy-itmeo",
        heading: "Quản lý loại sản phẩm",
        dataUpdate: {},
        loadingHeader: true,
        userInfo: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')) : null,
        isUpdateCategory: false,
        currentData: Object.assign({}, { ...initCategory }),
        dataFilter: Object.assign({}, initDataFilter),
        currentCategory: null,
        inputTypePassword: 'password',
        fields: [
          { key: "key", label: "STT", tdClass: 'align-middle', thClass: 'align-middle', visible: true, thStyle: { width: '4%' } },
          { key: "categoryId", label: "ID loại sản phẩm", visible: true, thStyle: { width: '8%' }, thClass: 'text-left align-middle' },
          { key: "categoryName", label: "Tên loại sản phẩm", visible: true, thStyle: { width: '8%' }, thClass: 'text-left align-middle' },
          {
            key: "actions",
            label: "Chức năng",
            visible: true,
            thStyle: { width: '5%' },
            thClass: 'text-center align-middle'
          }
        ],
      };
    },
    mixins: [baseMixins],
    validations: {
      currentData: {
        categoryName: {
          required,
        },
      },
    },
    components: {
      PageTitle,
    },
    mounted() {
      const dataSearch = this.$route.query.dataSearch;
  
      if (dataSearch) this.dataFilter = JSON.parse(String(dataSearch));
      this.fetchCategory();
    },
    computed: {
      ...mapGetters(["getCategory"]),
      visibleFields() {
        return this.fields.filter(field => field.visible)
      },
      fromPage() {
        return (this.dataFilter.page - 1) * this.dataFilter.limit + 1;
      },
      toPage() {
        return this.getCategory.length
          ? this.dataFilter.page * this.dataFilter.limit >= this.getCategory.length
          ? this.getCategory.length
          : this.dataFilter.page * this.dataFilter.limit
          : 0;
      },
    },
    methods: {
      validationStatus: function (validation) {
        return typeof validation != "undefined" ? validation.$error : false;
      },
      changePage(e) {
        this.dataFilter.page = e
        const dataSearch = this.$route.query.dataSearch;

        if ((dataSearch && dataSearch !== JSON.stringify(this.dataFilter)) || !dataSearch) {
          router.push({ path: '/admin/product-category', query: { dataSearch: JSON.stringify(this.dataFilter) }})
        }
        // this.fetchCategory();
      },
      handleSearch() {
        this.dataFilter.page = 1
        router.push({ path: '/admin/product-category', query: { dataSearch: JSON.stringify(this.dataFilter) }})
        this.fetchCategory();
      },
      handleResetFilter() {
        this.$router.replace('/admin/product-category');
        this.dataFilter = Object.assign({}, initDataFilter);
        this.handleSearch();
      },
      async fetchCategory() {
        let payload = {...this.dataFilter}
        let response = await this.$store.dispatch(FETCH_CATEGORY, payload);
        if (response) {
          setTimeout(() => {
            if (this.loadingHeader) this.loadingHeader = !this.loadingHeader
          }, 200)
        }
  
        if (response && response.data) this.$store.commit("setCategory", response.data.data);
      },
      handleOpenModalUpdateCategory(category, isUpdateCategory) {
        this.isUpdateCategory = isUpdateCategory
  
        if (isUpdateCategory) {
          this.currentData = Object.assign({}, {
            categoryName: category.categoryName,
            categoryId: category.categoryId,
          });
        }
        this.$root.$emit("bv::show::modal", "update-category");
      },
      handleCancelUpdateCategory() {
        this.isUpdateCategory = null
        this.currentCategory = null
        this.currentData = Object.assign({}, { ...initCategory })
        this.$nextTick(() => {
          this.$v.currentData.$reset();
        });
        this.$root.$emit("bv::hide::modal", "update-category");
      },
      async handleSubmit() {
        this.$v.$reset();
        this.$v.$touch();
        if (this.$v.currentData.$invalid) {
          return;
        }
        let payload = {
          categoryName: this.currentData.categoryName,
        }
        this.handleUpdateCategory(payload)
      },
      async handleUpdateCategory(payload) {
        let errorMsg = this.isUpdateCategory ? "Cập nhật loại sản phẩm không thành công." : "Thêm loại sản phẩm không thành công."
        let successMsg = this.isUpdateCategory ? "Cập nhật loại sản phẩm thành công." : "Thêm loại sản phẩm thành công."

        let payloadForUpdate = {
          categoryId: this.currentData.categoryId,
          categoryData: payload,
        }
        let res = await (this.isUpdateCategory ? this.$store.dispatch(UPDATE_CATEGORY, payloadForUpdate) : this.$store.dispatch(CREATE_CATEGORY, payload))
        if (res) this.$message.closeAll()
        if (res.status === 200) {
          this.$message({
            message: successMsg,
            type: "success",
            showClose: true,
          });
          this.handleCancelUpdateCategory()
          this.fetchCategory()
        } else {
          this.$message({
            message: errorMsg,
            type: "error",
            showClose: true,
          });
        }
      },
      handleOpenModalDeleteCategory(category) {
        this.$root.$emit("bv::show::modal", "delete-category");
        this.currentCategory = { ...category }
      },
      handleCancelDeleteCategory() {
        this.currentCategory = null
        this.$root.$emit("bv::hide::modal", "delete-category");
      },
      async handleDeleteCategory() {
        if (!this.currentCategory || !this.currentCategory.categoryId) {
          this.$message.closeAll()
          this.$message({
            message: "Xoá loại tài khoản không thành công.",
            type: "error",
            showClose: true,
          });
          return
        }
        let res = await this.$store.dispatch(DELETE_CATEGORY, this.currentCategory.categoryId)
        if (res && res.status === 200) {
          this.$message.closeAll()
          this.$message({
            message: "Xoá loại tài khoản thành công.",
            type: "success",
            showClose: true,
          });
          this.fetchCategory();
          this.handleCancelDeleteCategory()
        }
      },
    },
  };
  </script>
    
  <style scoped>
  
  </style>
    