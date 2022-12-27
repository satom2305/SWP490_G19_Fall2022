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
              <table v-if="listCart && listCart.length > 0">
                <thead>
                  <tr>
                    <th class="shoping__product">Products</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody v-for="(item, index) in listCart" :key="index">
                  <tr>
                    <td class="shoping__cart__item">
                      <img
                        :src="item.product.mainImg"
                        width="150"
                        height="150"
                        alt=""
                      />
                      <h5>{{ item.product.productName }}</h5>
                    </td>
                    <td class="shoping__cart__price">
                      {{ formatPrice(item.product.sellPrice) }}đ
                    </td>
                    <td class="shoping__cart__quantity">
                      <div class="quantity">
                        <div class="pro-qty">
                          <input
                            v-if="toggleUpdateCart"
                            type="number"
                            v-model="item.quantity"
                            @input="
                              onChangeQuantity(
                                $event.target.value,
                                item,
                                item.product.amount
                              )
                            "
                          />
                          <div class="my-2" v-else>
                            {{ item.quantity }}
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="shoping__cart__total">
                      {{ formatPrice(item.product.sellPrice * item.quantity) }}đ
                    </td>
                    <td
                      class="shoping__cart__item__close"
                      v-if="toggleUpdateCart"
                    >
                      <span
                        class="icon_close"
                        @click="openModalConfirmDeleteCart(item)"
                        >x</span
                      >
                    </td>
                  </tr>
                </tbody>
              </table>
              <div
                class="d-flex justify-content-center flex-column align-items-center"
                v-else
              >
                <div>
                  <i
                    class="fa-solid fa-bag-shopping"
                    style="color: #b6b6b6; font-size: 2rem;"
                  ></i>
                </div>
                <div class="custom-empty-content my-2">Giỏ hàng trống</div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="shoping__cart__btns">
              <a href="/" class="primary-btn cart-btn mx-0"
                >Tiếp tục mua hàng</a
              >
              <button
                v-if="listCart && listCart.length > 0"
                class="primary-btn cart-btn cart-btn-right"
                style="border: none; cursor: pointer"
                @click="updateListCart()"
              >
                <span>{{ toggleUpdateCart ? "Xác nhận" : "Cập nhật" }}</span>
              </button>
            </div>
          </div>
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
          <div class="col-lg-6">
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
          </div>
        </div>
      </div>
    </section>
    <!-- Shoping Cart Section End -->

    <!-- Footer Section Begin -->
    <UserFooter />
    <b-modal
      hide-footer
      id="modal-delete-cart"
      :title="'Xác nhận xoá sản phẩm khỏi giỏ hàng'"
      :no-close-on-backdrop="true"
    >
      <div class="pb-3">
        Bạn có muốn xoá sản phẩm
        <span
          class="font-weight-bold"
          v-if="currentCart && currentCart.product"
        >
          {{ currentCart.product.productName }}</span
        >
        khỏi giỏ hàng không ?
      </div>
      <b-button
        class="mr-2 btn-light2 pull-right"
        @click="closeModalConfirmDeleteCart()"
      >
        Hủy
      </b-button>
      <b-button
        variant="primary pull-right"
        class="mr-2"
        type="submit"
        @click="deleteCart()"
      >
        Đồng ý
      </b-button>
    </b-modal>
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
export default {
  name: "MyOrder",
  mixins: [baseMixins],
  components: { UserHeader, UserFooter, Humberger, SectionBegin },
  data() {
    return {
      listCart: [],
      toggleUpdateCart: false,
      currentCart: null,
      cartData: null,
    };
  },
  mounted() {
    // handleJQuery();

    botChatAI();
    this.getListCart();
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
    handleMaxQuantity(maxQuantity, value) {
      if (value > maxQuantity) {
        value = maxQuantity;
      } else if (value < 0) {
        value = "0";
      }
    },
    async getListCart() {
      const res = await this.getWithBigInt("/rest/carts");
      if (res && res.data && res.data.data) {
        this.listCart = res.data.data;
      }
    },
    async updateListCart() {
      this.confirmUpdateCart();
      if (!this.toggleUpdateCart) {
        localStorage.setItem("quantity", JSON.stringify(this.listCart));
        this.cartData = JSON.parse(localStorage.getItem("quantity"));
        let payloadforUpdateCart = this.cartData.map((item) => {
          return {
            cartId: item.cartId,
            productId: item.product.productId,
            quantity: item.quantity,
            userId: item.user.userId,
          };
        });
        const res = await this.put(
          "/rest/carts/updateListCart",
          payloadforUpdateCart
        );
        if (res && res.data && res.data.data) {
          this.listCart = res.data.data;
        }
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
    onChangeQuantity(quantity, cart, maxQuantity) {
      if (quantity <= maxQuantity) {
        console.log(quantity, cart, maxQuantity);
        cart.quantity = quantity;
      } else {
        cart.quantity = maxQuantity;
      }
      this.$nextTick(() => {});
    },
    openModalConfirmDeleteCart(cart) {
      this.currentCart = { ...cart };
      this.$root.$emit("bv::show::modal", "modal-delete-cart");
    },
    closeModalConfirmDeleteCart(cart) {
      this.currentCart = null;
      this.$root.$emit("bv::hide::modal", "modal-delete-cart");
    },
    proceedToCheckout() {
      localStorage.setItem("quantity", JSON.stringify(this.listCart));
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
</style>
