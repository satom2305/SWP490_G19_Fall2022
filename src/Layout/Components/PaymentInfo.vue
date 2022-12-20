<template>
    <b-modal size="xl" hide-footer id="modal-payment" :title="currentTitleModal" :no-close-on-backdrop="true"
      @hide="cancelCreateOrder(false)">
      <b-card id="update-order" class="main-card search-wrapper mb-20">
        <div class="mb-4">
          <div class="mb-2 font-weight-bold">Thông tin đơn hàng</div>    
          <div class="checkout__order">
                <h4>Đơn hàng của bạn</h4>
                <div class="checkout__order__products">
                  Sản phẩm <span>Giá</span>
                </div>
                <ul v-for="(item, index) in listCart" :key="index">
                  <li>
                    {{ item.product.productName }} x
                    {{ item.quantity }}
                    <span>{{
                      getFormatPrice(item.product.sellPrice * item.quantity)
                    }}đ</span>
                  </li>
                </ul>
                <div class="checkout__order__total">
                  Tổng giá đơn hàng <span>{{ getFormatPrice(totalPrice) }}đ</span>
                </div>
            </div>
            <div class="mb-2 font-weight-bold">Thanh toán(Chuyển khoản ngân hàng)</div> 
            <p>Chủ tài khoản : Trần Quang Minh</p>
            <p>Ngân hàng : MB bank</p>
            <p>Số tài khoản : 9704 2292 0103 6493</p>
            <p>Nội dung chuyển khoản : "tên tài khoản" + mua cây</p>
        </div>
      </b-card>
  
    </b-modal>
  </template>
    
  <script>
  import PageTitle from "@/Layout/Components/PageTitle";
  import baseMixins from "@/components/mixins/base";
  import { required, helpers, integer } from "vuelidate/lib/validators";
  import { formatPriceSearchV2 } from "@/common/common";
  import Vue from "vue";
  import Multiselect from "vue-multiselect";
  import moment from "moment-timezone";
  import DatePicker from "vue2-datepicker";
  import { mapGetters } from "vuex";
  import "vue2-datepicker/index.css";
  import "vue2-datepicker/locale/vi";
  Vue.component("multiselect", Multiselect);
  import {
    CREATE_ORDER,
    UPDATE_ORDER,
    FETCH_PROMOTIONS,
    FETCH_PRODUCTS_AVAILABLE,
    CREATE_ORDER_DETAIL,
    UPDATE_ORDER_DETAIL_BY_ORDER_ID,
    DELETE_ORDER_DETAIL,
    FETCH_ORDER_DETAIL_BY_ORDER_ID
  } from "@/store/action.type";
  import { CREATE_ORDER_DETAIL_BY_ORDER_ID } from '../../store/action.type';
  const initOrder = {
    note: null,
    totalPrice: 0,
    orderStatusId: null,
    date: null,
    promotionId: 0,
    address: null,
    city: null,
    district: null,
    wards: null,
    phoneNumber: 0,
    orderId: null,
    promotion: null,
    orderStatus: { id: 1, statusName: "Chờ xác nhận" },
    user: null,
    listCart : null,
  };
  const initOrderDetail = {
    order_detail_id: null,
    order: null,
    product: null,
    productName: null,
    productPrice: 0,
    quantity: 1
  }
  const validPhoneNumber = helpers.regex('validPhoneNumber', /^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$/)
  export default {
    name: "ModalCreateOrder",
    components: { PageTitle, DatePicker },
    props: {
      currentTitleModal: String,
      order: {
        type: Object,
        default: null
      },
      orderDetail: Array,
      isUpdate: Boolean,
      totalPrice: String,
    },
    data() {
      return {
        heading: "Tạo đơn hàng",
        subheading: "Tạo đơn hàng",
        userInfo: localStorage.getItem("userInfo")
          ? JSON.parse(localStorage.getItem("userInfo"))
          : null,
        loadingHeader: true,
        orderStatusOptions: [
          { value: 1, text: 'Chờ xác nhận' },
          { value: 2, text: 'Đã xác nhận' },
          { value: 3, text: 'Đã huỷ' },
        ],
        promotionOptions: [],
        productOptions: [],
        currentData: Object.assign({}, initOrder),
        currentDetailData: [],
        currentDetailId: null,
        currentSelectDetail: null,
        loadingOrderDetail: true,
        listCart:null,
        cartData:null,
      };
    },
    mixins: [baseMixins],
    validations: {
      currentData: {
        orderStatus: {
          required,
        },
        totalPrice: {
          required,
        },
        address: {
          required,
        },
        city: {
          required,
        },
        district: {
          required,
        },
        wards: {
          required,
        },
        phoneNumber: {
          required,
          validPhoneNumber,
        }
      },
    },
    computed: {
      ...mapGetters(["getPromotions", "getProducts"]),
      saveCurrentData() {
        return this.order
      },
    },
    watch: {
      order() {
        this.setCurrentUpdateData()
        this.fetchOrderDetailById()
      }
    },
    mounted() {
       
        this.getListCart();
      if (!this.getPromotions || this.getPromotions.length === 0) {
        this.$store.dispatch(FETCH_PROMOTIONS).then(res => {
          if (res && res.status === 200 && res.data) this.getPromotionOptions(res.data.data)
        })
      } else {
        this.getPromotionOptions(this.getPromotions)
      }
      if (!this.getProducts || this.getProducts.length === 0) {
        this.$store.dispatch(FETCH_PRODUCTS_AVAILABLE).then(res => {
          if (res && res.status === 200 && res.data) this.getProductOptions(res.data.data)
        })
      } else {
        this.getProductOptions(this.getProducts)
      }
      this.$root.$on('bv::modal::show', (bvEvent, modalId) => {
        if(modalId === 'modal-create-order') this.fetchOrderDetailById()
      })
    },
    computed: {
      disabledUpdateOrder() {
        return false
        // return (this.currentData && this.isUpdate && !(this.currentData.orderStatus && this.currentData.orderStatus.value === 1))
      },
      getTotalProductPrice() {
        return this.currentDetailData && this.currentDetailData.length > 0
          ? this.currentDetailData.map(item => item.product && item.quantity ? item.product.sellPrice * item.quantity : 0).reduce((prev, current) => prev + current, 0)
          : 0
      },
    },
    methods: {
      checkInvalidProduct() {
        if (this.currentDetailData && this.currentDetailData.length > 0) {
          let incorrectDetail = this.currentDetailData.filter(item => !item.product || item.quantity < 0 || !item.quantity)
          return (incorrectDetail && incorrectDetail.length > 0)
        }
        return false
      },
      addNewProductForDetail() {
        let newProduct = Object.assign({}, { ...initOrderDetail })
        this.currentDetailData.push(newProduct)
      },
      calculateProductPrice(index) {
        this.$nextTick(() => {
          this.currentDetailData[index].productPrice = this.currentDetailData[index].product && this.currentDetailData[index].quantity
            ? this.currentDetailData[index].product.sellPrice * this.currentDetailData[index].quantity
            : 0
          this.calculateOrderPrice()
        })
        
      },
      calculateOrderPrice() {
        this.currentData.totalPrice = this.currentDetailData && this.currentDetailData.length > 0
          ? this.currentDetailData.map(item => item.product && item.quantity ? item.product.sellPrice * item.quantity : 0).reduce((prev, current) => prev + current, 0)
          : 0
      },
      setCurrentUpdateData() {
        if (!this.order) return
        let { promotion, user, orderStatus } = { ...this.order }
        this.currentData = {
          ...this.order,
          promotion: promotion ? { text: promotion.salePercent + '%', value: promotion.promotionId } : null,
          orderStatus: orderStatus ? { text: orderStatus.statusName, value: orderStatus.id } : null,
          user: user ? { text: user.username, value: user.userId } : null,
        }
      },
      setCurrentUpdateDetailData() {
        this.currentDetailData = this.currentDetailData.map(item => {
          return {
            ...item,
            product: item.product ? { text: item.product.productName, value: item.product.productId, img: item.product.mainImg, sellPrice: item.product.sellPrice, } : null,
          }
        })
      },
      async fetchOrderDetailById() {
        if (!this.order || !this.order.orderId) return
        let response = await this.$store.dispatch(FETCH_ORDER_DETAIL_BY_ORDER_ID, this.order.orderId);
        if (response && response.data && response.data.success) {
          this.currentDetailData = [...response.data.data]
          this.setCurrentUpdateDetailData()
          setTimeout(() => {
            if (this.loadingOrderDetail) this.loadingOrderDetail = false 
          }, 300)
        } else {
          this.$message({
            message: "Lấy chi tiết đơn hàng không thành công.",
            type: "error",
            showClose: true,
          });
        }
      },
      validateKeyCode: function (e) {
        if ((e.key < 48 || e.key > 57 || e.charCode === 13) && e.charCode !== 45 && e.charCode !== 32) {
          return true
        }
        e.preventDefault()
      },
      getPromotionOptions(promotions) {
        this.promotionOptions = promotions.filter(item => item.amount > 0).map(item => {
          return {
            text: item.salePercent ? item.salePercent + '%' : '',
            value: item.promotionId
          }
        })
      },
      getProductOptions(products) {
        this.productOptions = products.filter(item => item.amount > 0).map(item => {
          return {
            text: item.productName,
            value: item.productId,
            img: item.mainImg,
            sellPrice: item.sellPrice,
          }
        })
      },
      getFormatPrice(price) {
        return price ? formatPriceSearchV2(price + '') : 0
      },
      async handleCreateAndUpdateOrder() {
        let successMsg = `${this.isUpdate ? 'Cập nhật' : 'Tạo'} đơn hàng thành công.`
        let errorMsg = `${this.isUpdate ? 'Cập nhật' : 'Tạo'} đơn hàng không thành công.`
        let {
          totalPrice,
          note,
          date,
          address,
          city,
          district,
          wards,
          phoneNumber,
          orderStatus,
          promotion,
          user,
          orderId
        } = { ...this.currentData };
        let payload = {
          orderId,
          orderData: {
            totalPrice: this.getTotalProductPrice,
            promotionId: promotion ? promotion.value : null,
            note,
            totalPrice: totalPrice && Number((totalPrice + '').replace(',', '')),
            orderStatusId: orderStatus ? orderStatus.value : null,
            date: this.isUpdate ? date : moment(new Date()).format('YYYY-MM-DDTHH:mm:ss.SSSZ'),
            phoneNumber: phoneNumber && Number(phoneNumber),
            address, city, district, wards,
          },
        };
        let payloadForCreateDetail = this.currentDetailData.map(item => {
          return {
            productId: item.product ? item.product.value + '' : null,
            productName: item.product ? item.product.text : null,
            quantity: item.quantity,
            productPrice: (item.product && item.quantity ? item.product.sellPrice * item.quantity : 0) + '',
          }
        })
        let payloadForUpdateDetail = {
          orderId: orderId,
          orderDetailData: this.currentDetailData
            .filter(detail => detail && detail.order_detail_id)
            .map(item => {
              return {
                order_detail_id: item.order_detail_id ? item.order_detail_id + '' : null,
                productId: item.product ? item.product.value + '' : null,
                productName: item.product ? item.product.text : null,
                quantity: item.quantity,
                productPrice: (item.product && item.quantity ? item.product.sellPrice * item.quantity : 0) + '',
              }
            }),
          newOrderDetailData: this.currentDetailData
            .filter(detail => detail && !detail.order_detail_id)
            .map(item => {
              return {
                orderId: orderId,
                productId: item.product ? item.product.value + '' : null,
                productName: item.product ? item.product.text : null,
                quantity: item.quantity,
                productPrice: (item.product && item.quantity ? item.product.sellPrice * item.quantity : 0) + '',
              }
            })
        }
        if (this.isUpdate) {
          let promiseList = [this.$store.dispatch(UPDATE_ORDER, payload)]
          if (payloadForUpdateDetail.orderDetailData.length > 0) promiseList.push(this.$store.dispatch(UPDATE_ORDER_DETAIL_BY_ORDER_ID, payloadForUpdateDetail))
          if (payloadForUpdateDetail.newOrderDetailData.length > 0) promiseList.push(this.$store.dispatch(CREATE_ORDER_DETAIL_BY_ORDER_ID, payloadForUpdateDetail.newOrderDetailData))
          Promise.all(promiseList).then(res => {
            if (res[0].status === 200 && res[1].status === 200) {
              this.$message({
                message: successMsg,
                type: "success",
                showClose: true,
              });
              this.cancelCreateOrder(true)
            }
          })
        }
        if (!this.isUpdate) {
          let res = await this.$store.dispatch(CREATE_ORDER, payload.orderData)
          if (res.status === 200 && res.data && res.data.data) {
            let newOrderId = res.data.data.orderId
            payloadForCreateDetail = payloadForCreateDetail.map(item => {
              return {
                ...item,
                orderId: newOrderId
              }
            })
            let resForDetail = await this.$store.dispatch(CREATE_ORDER_DETAIL_BY_ORDER_ID, payloadForCreateDetail)
            if (resForDetail.status === 200) {
              this.$message({
                message: successMsg,
                type: "success",
                showClose: true,
              });
              this.cancelCreateOrder(true)
            }
          }
        }
      },
      handleSubmit() {
        this.$v.$reset();
        this.$v.$touch();
        if (this.$v.currentData.$invalid) {
          return;
        }
        let checkInvalidProduct = this.checkInvalidProduct()
        if (checkInvalidProduct) {
          this.$message.closeAll()
          this.$message({
            message: "Bạn cần điền đầy đủ thông tin sản phẩm.",
            type: "warning",
            showClose: true,
          })
          return
        }
        this.handleCreateAndUpdateOrder();
      },
      handleReset() {
        // if (this.isUpdate) {
        //   this.setCurrentUpdateData()
        //   this.setCurrentUpdateDetailData()
        // }
        // if (!this.isUpdate) {
        //   this.currentData = Object.assign({}, initOrder)
        //   this.currentDetailData = []
        // }
        // this.$nextTick(() => {
        //   this.$v.$reset();
        // });
      },
      validationStatus: function (validation) {
        return typeof validation != "undefined" ? validation.$error : false;
      },
      cancelCreateOrder(isFetchOrders) {
        this.$v.$reset()
        this.loadingOrderDetail = true
        this.currentData = Object.assign({}, {...initOrder})
        this.currentDetailData = []
        this.$emit('cancelCreateOrder', isFetchOrders)
        this.$root.$emit("bv::hide::modal", "modal-create-order");
        this.$router.push({ path: `/my-order` });
      },
      openModalDeleteOrderDetail(orderDetail, indexVal) {
        if (orderDetail && orderDetail.order_detail_id) {
          this.currentDetailId = orderDetail.order_detail_id
          this.currentSelectDetail = orderDetail
          this.$root.$emit("bv::show::modal", "delete-order-detail");
        }
        else {
          this.currentDetailData = this.currentDetailData.filter((item, index) => index !== indexVal)
          this.calculateOrderPrice()
        }
      },
      cancelDeleteOrderDetail() {
        this.$root.$emit("bv::hide::modal", "delete-order-detail");
      },
    async getListCart() {
      const res = await this.getWithBigInt("/rest/carts");
      if (res && res.data && res.data.data) {
        this.listCart = res.data.data;
        const cartData = JSON.parse(localStorage.getItem('quantity'));
        this.listCart = cartData;
      }
    },
      async handleDeleteOrderDetail() {
        if (this.currentDetailId) {
          let res = await this.$store.dispatch(DELETE_ORDER_DETAIL, this.currentDetailId)
          if (res.status === 200) {
            this.cancelDeleteOrderDetail()
            this.currentDetailData = this.currentDetailData.filter(item => item.order_detail_id !== this.currentDetailId)
            this.currentDetailId = null
            this.currentSelectDetail = null
            this.$message.closeAll()
            this.$message({
              message: "Xoá sản phẩm khỏi đơn hàng thành công.",
              type: "success",
              showClose: true,
            });
          }
        }
      }
    },
  };
  </script>
  
  <style lang="scss" scoped>
  #update-order {
    .multiselect {
      min-height: 32px !important;
    }
  }
  .is-invalid-option {
    border-radius: 5px;
    border: 1px solid #ff7851 !important;
  }
  .custom-wrapper {
    overflow-wrap: break-word;
  }
  .custom-banner-image {
    background-repeat: no-repeat;
    background-position: center;
    background-size: contain;
    box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.05);
    border: 1px solid rgba(0, 0, 0, 0.2);
  }
  .banner-image__preview {
    width: 100%;
    height: 10rem;
    .overlay {
      width: 100%;
      height: 100%;
      display: none;
      background-color: rgba(0, 0, 0, 0.4);
    }
    &:hover .overlay {
      display: block;
      display: flex;
      justify-content: center;
      cursor: pointer;
      transition-duration: 500ms;
    }
    .banner-image__btn {
      border: none;
      outline: none;
      background-color: transparent;
      margin: 1rem;
      cursor: pointer;
      border-radius: 10px;
      color: white;
      font-size: 1.5rem;
    }
  }
  </style>
    
  <style lang="scss">
  #update-order {
    .was-validated .form-control:invalid,
    .form-control.is-invalid {
      background-image: none !important;
    }
    .invalid-date-feedback {
      display: none;
      width: 100%;
      margin-top: 0.25rem;
      font-size: 80%;
      color: #ff7851;
    }
    .invalid-editor-feedback {
      display: none;
      width: 100%;
      margin-top: 0.25rem;
      font-size: 80%;
      color: #ff7851;
    }
    .invalid-date {
      .invalid-date-feedback {
        display: block !important;
      }
    }
    .invalid-editor {
      .invalid-editor-feedback {
        display: block !important;
      }
    }
  }
  </style>