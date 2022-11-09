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
            <b-button variant="info" class="mb-4" @click="openModalUpdatePromotion(null, false)"
              style="max-width: 200px">
              <i class="fas fa-edit"></i> Thêm khuyến mại
            </b-button>
          </div>
          <b-table
            :items="getPromotions"
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
            <template #cell(salePercent)="row">
              {{ row.item.salePercent ? row.item.salePercent + ' %' : row.item.salePercent  }}
            </template>
            <template #cell(actions)="row">
              <div class="d-flex justify-content-center">
                <div class="px-3">
                  <a href="javascript:void(0)" type="button" v-b-tooltip.hover title="Cập nhật"
                    @click.prevent="openModalUpdatePromotion(row.item, true)">
                    <i class="fas fa-edit" style="font-size: 1.1rem"></i>
                  </a>
                </div>
                <div class="px-3">
                  <a href="javascript:void(0)" type="button" v-b-tooltip.hover title="Xoá"
                    @click.prevent="openModalDeletePromotion(row.item)">
                    <i class="fas fa-times" style="font-size: 1.1rem; color: red"></i>
                  </a>
                </div>
              </div>
            </template>
          </b-table>

          <b-row v-if="getPromotions && getPromotions.length > 0">
            <b-col class="pagination">
              <b-pagination
                v-model="dataFilter.page"
                :per-page="dataFilter.limit"
                :total-rows="getPromotions.length"
                @change="changePage"
              ></b-pagination>
            </b-col>
            <b-col class="mt-1">
              <span class="text-muted">
                {{ fromPage }} đến {{ toPage }} trên {{ getPromotions.length }} bản ghi
              </span>
            </b-col>
          </b-row>
        <b-row v-else class="justify-content-center">
          <span>Không tìm thấy bản ghi nào</span>
        </b-row>
        </template>
      </b-card>
  
      <b-modal id="update-promotion" :title="isUpdatePromotion ? 'Cập nhật khuyến mại' : 'Thêm khuyến mại'"
        :no-close-on-backdrop="true" size="lg" @hidden="cancelUpdatePromotion">
        <b-form-group>
          <label>Mã khuyến mại<span class="text-danger">*</span>:</label>
          <b-form-input
            id="input-code"
            v-model.trim="$v.currentData.promotionCode.$model"
            maxlength="1000"
            placeholder="Nhập mã khuyến mại"
            :class="{ 'is-invalid': validationStatus($v.currentData.promotionCode) }"
          ></b-form-input>
          <div
            v-if="!$v.currentData.promotionCode.required"
            class="invalid-feedback"
          >
            Mã khuyến mại không được để trống.
          </div>
        </b-form-group>
        <b-form-group>
          <label>Phần trăm giảm giá<span class="text-danger">*</span>:</label>
          <b-form-input
            id="input-sale-percent"
            type="number"
            v-model.trim="$v.currentData.salePercent.$model"
            placeholder="Nhập phần trăm giảm giá"
            :class="{ 'is-invalid': validationStatus($v.currentData.salePercent) }"
          ></b-form-input>
          <div
            v-if="!$v.currentData.salePercent.required"
            class="invalid-feedback"
          >
            Phần trăm giảm giá không được để trống.
          </div>
          <div
            v-if="!$v.currentData.amount.maxValue"
            class="invalid-feedback"
          >
           Phần trăm giảm giá không vượt quá 100%.
          </div>
        </b-form-group>
        <b-form-group>
          <label>Số lượng<span class="text-danger">*</span>:</label>
          <b-form-input
            id="input-amount"
            type="number"
            v-model.trim="$v.currentData.amount.$model"
            placeholder="Nhập số lượng"
            :class="{ 'is-invalid': validationStatus($v.currentData.amount) }"
          ></b-form-input>
          <div
            v-if="!$v.currentData.amount.required"
            class="invalid-feedback"
          >
            Số lượng không được để trống.
          </div>
          <div
            v-if="!$v.currentData.amount.minValue"
            class="invalid-feedback"
          >
            Số lượng không nhỏ hơn 1.
          </div>
        </b-form-group>
        <template #modal-footer>
          <b-button class="mr-2 btn-light2 pull-right" @click="cancelUpdatePromotion">
            Hủy
          </b-button>
          <b-button variant="primary pull-right" @click.prevent="handleSubmit">
            Đồng ý
          </b-button>
        </template>
      </b-modal>
  
      <b-modal hide-footer id="delete-promotion" :title="'Xác nhận xoá khuyến mại'" :no-close-on-backdrop="true">
        <div class="pb-3">
          Bạn có muốn xoá khuyến mại <span class="font-weight-bold" v-if="this.currentPromotion">{{ this.currentPromotion.promotionCode }}</span> không ?
        </div>
        <b-button class="mr-2 btn-light2 pull-right" @click="cancelDeletePromotion">
          Hủy
        </b-button>
        <b-button variant="primary pull-right" class="mr-2" type="submit" @click="handleDeletePromotion">
          Đồng ý
        </b-button>
      </b-modal>
    </div>
  </template>
    
  <script>
  import PageTitle from "../../Layout/Components/PageTitle";
  import baseMixins from "../../components/mixins/base";
  import { required, minValue, maxValue } from "vuelidate/lib/validators";
  import { mapGetters } from "vuex";
  import router from '@/router';
  import { FETCH_PROMOTIONS, FETCH_PROMOTION_BY_CODE, CREATE_PROMOTION, UPDATE_PROMOTION, DELETE_PROMOTION } from "@/store/action.type";
  
  const initPromotion = {
    promotionId: null,
    salePercent: 0,
    promotionCode: null,
    amount: 1,
  };
  
  const initDataFilter = {
    promotionCode: null,
    page: 1,
    limit: 10
  };
  
  export default {
    name: "PromotionManagement",
    data() {
      return {
        subheading: "Tạo và quản lý các khuyến mại",
        icon: "pe-7s-portfolio icon-gradient bg-happy-itmeo",
        heading: "Quản lý khuyến mại",
        dataUpdate: {},
        loadingHeader: true,
        userInfo: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')) : null,
        isUpdatePromotion: false,
        currentData: Object.assign({}, { ...initPromotion }),
        dataFilter: Object.assign({}, initDataFilter),
        currentPromotion: null,
        inputTypePassword: 'password',
        fields: [
          { key: "key", label: "STT", tdClass: 'align-middle', thClass: 'align-middle', visible: true, thStyle: { width: '4%' } },
          { key: "promotionCode", label: "ID khuyến mại", visible: true, thStyle: { width: '8%' }, thClass: 'text-left align-middle' },
          { key: "salePercent", label: "Phần trăm giảm giá", visible: true, thStyle: { width: '8%' }, thClass: 'text-left align-middle' },
          { key: "amount", label: "Số lượng", visible: true, thStyle: { width: '8%' }, thClass: 'text-left align-middle' },          
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
        promotionCode: {
          required,
        },
        amount: {
          minValue: minValue(1),
          required,
        },
        salePercent: {
          required,
          maxValue: maxValue(100),
        },
      },
    },
    components: {
      PageTitle,
    },
    mounted() {
      const dataSearch = this.$route.query.dataSearch;
  
      if (dataSearch) this.dataFilter = JSON.parse(String(dataSearch));
      this.fetchPromotion();
    },
    computed: {
      ...mapGetters(["getPromotions"]),
      visibleFields() {
        return this.fields.filter(field => field.visible)
      },
      fromPage() {
        return (this.dataFilter.page - 1) * this.dataFilter.limit + 1;
      },
      toPage() {
        return this.getPromotions.length
          ? this.dataFilter.page * this.dataFilter.limit >= this.getPromotions.length
          ? this.getPromotions.length
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
          router.push({ path: '/admin/promotion', query: { dataSearch: JSON.stringify(this.dataFilter) }})
        }
        // this.fetchPromotion();
      },
      handleSearch() {
        this.dataFilter.page = 1
        router.push({ path: '/admin/promotion', query: { dataSearch: JSON.stringify(this.dataFilter) }})
        this.fetchPromotion();
      },
      handleResetFilter() {
        this.$router.replace('/admin/promotion');
        this.dataFilter = Object.assign({}, initDataFilter);
        this.handleSearch();
      },
      async fetchPromotion() {
        let payload = {...this.dataFilter}
        let response = await this.$store.dispatch(FETCH_PROMOTIONS, payload);
        if (response) {
          setTimeout(() => {
            if (this.loadingHeader) this.loadingHeader = !this.loadingHeader
          }, 200)
        }
  
        if (response && response.data) this.$store.commit("setPromotions", response.data.data);
      },
      openModalUpdatePromotion(promotion, isUpdatePromotion) {
        this.isUpdatePromotion = isUpdatePromotion
  
        if (isUpdatePromotion) {
          this.currentData = Object.assign({}, {
            promotionId: promotion.promotionId,
            promotionCode: promotion.promotionCode,
            salePercent: promotion.salePercent,
            amount: promotion.amount,
          });
        }
        this.$root.$emit("bv::show::modal", "update-promotion");
      },
      cancelUpdatePromotion() {
        this.isUpdatePromotion = null
        this.currentPromotion = null
        this.currentData = Object.assign({}, { ...initPromotion })
        this.$nextTick(() => {
          this.$v.currentData.$reset();
        });
        this.$root.$emit("bv::hide::modal", "update-promotion");
      },
      async handleSubmit() {
        this.$v.$reset();
        this.$v.$touch();
        if (this.$v.currentData.$invalid) {
          return;
        }
        let payload = {
          promotionCode: this.currentData.promotionCode,
          salePercent: this.currentData.salePercent,
          amount: this.currentData.amount,
        }
        this.handleUpdatePromotion(payload)
      },
      async handleUpdatePromotion(payload) {
        let errorMsg = this.isUpdatePromotion ? "Cập nhật khuyến mại không thành công." : "Thêm khuyến mại không thành công."
        let successMsg = this.isUpdatePromotion ? "Cập nhật khuyến mại thành công." : "Thêm khuyến mại thành công."

        let payloadForUpdate = {
          promotionId: this.currentData.promotionId,
          promotionData: payload,
        }
        let res = await (this.isUpdatePromotion ? this.$store.dispatch(UPDATE_PROMOTION, payloadForUpdate) : this.$store.dispatch(CREATE_PROMOTION, payload))
        if (res) this.$message.closeAll()

        console.log(res)
        if (res.status === 200 && res.data.data && res.data.data !== 'null') {
          this.$message({
            message: successMsg,
            type: "success",
            showClose: true,
          });
          this.cancelUpdatePromotion()
          this.fetchPromotion()
        } else {
          this.$message({
            message: res.data ? res.data.message : errorMsg,
            type: "error",
            showClose: true,
          });
        }
      },
      openModalDeletePromotion(promotion) {
        this.$root.$emit("bv::show::modal", "delete-promotion");
        this.currentPromotion = { ...promotion }
      },
      cancelDeletePromotion() {
        this.currentPromotion = null
        this.$root.$emit("bv::hide::modal", "delete-promotion");
      },
      async handleDeletePromotion() {
        if (!this.currentPromotion || !this.currentPromotion.promotionId) {
          this.$message.closeAll()
          this.$message({
            message: "Xoá khuyến mại không thành công.",
            type: "error",
            showClose: true,
          });
          return
        }
        let res = await this.$store.dispatch(DELETE_PROMOTION, this.currentPromotion.promotionId)
        if (res && res.status === 200) {
          this.$message.closeAll()
          this.$message({
            message: "Xoá khuyến mại thành công.",
            type: "success",
            showClose: true,
          });
          this.fetchPromotion();
          this.cancelDeletePromotion()
        }
      },
    },
  };
  </script>
    
  <style scoped>
  
  </style>
    