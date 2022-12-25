<template>
  <div>
    <!-- Page Preloder -->
    <!--    <div id="preloder">-->
    <!--        <div class="loader"></div>-->
    <!--    </div>-->

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

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="shoping__cart__table">
              <table>
                <thead>
                  <tr>
                    <th>Mã đơn hàng</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Trạng thái đơn</th>
                    <th>Tổng giá</th>
                    <th>Thông tin đơn</th>
                  </tr>
                </thead>
                <tbody v-for="(item, index) in listOrder" :key="index">
                  <tr>
                    <td class="shoping__cart__item">
                      <h5>{{ item.orderId }}</h5>
                    </td>
                    <td class="shoping__cart__item">
                      {{ item.address }}
                    </td>
                    <td class="shoping__cart__item">
                      {{ item.phoneNumber }}
                    </td>
                    <td class="shoping__cart__item">
                      {{ item.orderStatus.statusName }}
                    </td>
                    <td class="shoping__cart__total">
                      {{ formatPrice(item.totalPrice) }}đ
                    </td>
                    <td class="shoping__cart__item__close">
                      <span
                        class="icon_close"
                        @click="openModalOrderDetail(item)"
                        ><font-awesome-icon icon="fa-solid fa-eye"
                      /></span>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="row">
          <!-- <div class="col-lg-12">
            <div class="shoping__cart__btns">
              <a href="/" class="primary-btn cart-btn mx-0"
                >Tiếp tục mua hàng</a
              >
              <button
                v-if="listCart && listCart.length > 0"
                class="primary-btn cart-btn cart-btn-right"
                style="border: none; cursor: pointer"
                @click="confirmUpdateCart"
              >
                <span v-if="!toggleUpdateCart">Cập nhật</span>
                <span v-if="toggleUpdateCart">Xác nhận</span>
              </button>
            </div>
          </div> -->
          <div class="col-lg-6">
            <!-- <div class="shoping__continue">
              <div class="shoping__discount">
                <h5>Discount Codes</h5>
                <form action="#">
                  <input type="text" placeholder="Enter your coupon code" />
                  <button type="submit" class="site-btn">APPLY COUPON</button>
                </form>
              </div>
            </div> -->
          </div>
          <!-- <div class="col-lg-6">
            <div class="shoping__checkout">
              <h5>Chi tiết đơn hàng</h5>
              <ul>
                <li>
                  Giá <span>{{ formatPrice(this.subPrice) }}đ</span>
                </li>
                <li>
                  Tổng giá <span>{{ formatPrice(this.totalPrice) }}đ</span>
                </li>
              </ul>
              <button
                style="cursor: pointer; border: none;"
                @click="proceedToCheckout()"
                class="primary-btn w-100"
                v-if="listCart && listCart.length > 0"
              >
                Tiến hành thanh toán
              </button>
            </div>
          </div> -->
        </div>
      </div>
    </section>
    <!-- Shoping Cart Section End -->

    <!-- Footer Section Begin -->
    <UserFooter />
    <ModalOrderDetail :current-order-detail="currentOrderDetail" />
    <!-- Footer Section End -->
    <!-- Js Plugins -->
  </div>
</template>

<script>
import { handleJQuery, botChatAI } from "../common/utils";
import baseMixins from "../components/mixins/base";
import { formatPriceSearchV2 } from "../common/common";
import UserHeader from "../Layout/Components/UserHeader";
import UserFooter from "../Layout/Components/UserFooter";
import Humberger from "../Layout/Components/Humberger";
import SectionBegin from "../Layout/Components/SectionBegin";
import ModalOrderDetail from "../Layout/Components/ModalOrderDetail.vue";
export default {
  name: "MyOrder",
  mixins: [baseMixins],
  components: {
    UserHeader,
    ModalOrderDetail,
    SectionBegin,
    Humberger,
    UserFooter,
  },
  data() {
    return {
      listCart: [],
      toggleUpdateCart: false,
      currentCart: null,
      listOrder: null,
      currentOrder: null,
      currentOrderDetail: null,
    };
  },
  mounted() {
    botChatAI();
    this.getListCart();
    this.getListOrder();
  },
  computed: {
    totalPrice() {
      return this.listCart && this.listCart.length > 0
        ? this.listCart
            .map((cart) => cart.quantity * cart.product.sellPrice)
            .reduce((prev, current) => prev + current, 0)
        : 0;
    },
    subPrice() {
      return this.listCart && this.listCart.length > 0
        ? this.listCart
            .map((cart) => cart.quantity * cart.product.sellPrice)
            .reduce((prev, current) => prev + current, 0)
        : 0;
    },
  },
  methods: {
    async getListCart() {
      const res = await this.getWithBigInt("/rest/carts");
      if (res && res.data && res.data.data) {
        this.listCart = res.data.data;
      }
    },
    async getListOrder() {
      const res = await this.getWithBigInt("/rest/orders/orderUser");
      if (res && res.data && res.data.data) {
        this.listOrder = res.data.data;
      }
    },
    async deleteCart() {
      if (!this.currentCart || !this.currentCart.cartId) return;
      const res = await this.delete(`/rest/carts/${this.currentCart.cartId}`);
      if (res && res.status === 200) {
        this.$message.closeAll();
        this.$message({
          message: "Xoá sản phẩm khỏi giỏ hàng thành công.",
          type: "success",
          showClose: true,
        });
        this.closeModalConfirmDeleteCart();
        this.getListCart();
      }
    },
    onChangeQuantity(quantity, cart) {
      cart.quantity = quantity;
      this.$nextTick(() => {});
    },
    async openModalOrderDetail(order) {
      this.currentOrder = { ...order };
      const res = await this.getWithBigInt(
        "/rest/orderDetails/order",
        order.orderId
      );
      if (res && res.data && res.data.data) {
        this.currentOrderDetail = res.data.data;
        this.$root.$emit("bv::show::modal", "modal-order-detail");
      }
    },
    closeModalConfirmDeleteCart(cart) {
      this.currentCart = null;
      this.$root.$emit("bv::hide::modal", "modal-delete-cart");
    },
    proceedToCheckout() {
      this.$router.push({ path: `/checkout` });
    },
    formatPrice(price) {
      if (!price) return 0;
      return formatPriceSearchV2(price + "");
    },
    confirmUpdateCart() {
      this.toggleUpdateCart = !this.toggleUpdateCart;
    },
  },
};
</script>

<style scoped>
.custom-empty-content {
  color: #b6b6b6;
  font-size: 1.1rem;
  font-weight: 700;
}
.shoping__cart__table table tbody tr td.shoping__cart__item {
  /* width: 700px; */
  text-align: center;
}
.shoping__cart__table table tbody tr td.shoping__cart__item__close {
  text-align: center;
}
</style>
