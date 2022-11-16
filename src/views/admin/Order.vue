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
        <div class="text-right">
          <b-button
            variant="info"
            class="mb-4"
            @click="navigateToCreateOrder()"
            style="max-width: 200px"
          >
            <i class="fas fa-edit"></i> Thêm đơn hàng
          </b-button>
        </div>
        <b-table
          :items="getOrders"
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
          <template #cell(user)="row">
            {{ row.item.user ? row.item.user.username : "" }}
          </template>
          <template #cell(date)="row">
            {{ formatDateTime(row.item.date) }}
          </template>
          <template #cell(totalPrice)="row">
            {{ formatPrice(row.item.totalPrice) }}
          </template>
          <template #cell(orderStatus)="row">
            <div
              v-if="row.item.orderStatus"
              class="d-flex justify-content-center"
            >
              <b-badge
                v-if="row.item.orderStatus.id === 1"
                class="badge-process custom-badge-label"
                >{{ row.item.orderStatus.statusName }}</b-badge
              >
              <b-badge
                v-if="row.item.orderStatus.id === 2"
                class="badge-success custom-badge-label"
                >{{ row.item.orderStatus.statusName }}</b-badge
              >
              <b-badge
                v-else-if="row.item.orderStatus.id === 3"
                class="badge-inactive custom-badge-label"
                >{{ row.item.orderStatus.statusName }}</b-badge
              >
            </div>
          </template>
          <template #cell(promotion)="row">
            {{
              row.item.promotion
                ? row.item.promotion.salePercent + " %"
                : "Không áp dụng"
            }}
          </template>
          <template #cell(place)="row">
            {{ getUserPlace(row.item) }}
          </template>
          <template #cell(actions)="row">
            <div class="d-flex justify-content-center flex-wrap">
              <div>
                <a
                  v-if="
                    row.item.orderStatus &&
                      checkShowAction(
                        ACTION_FOR_ORDER.UPDATE,
                        row.item.orderStatus.id
                      )
                  "
                  href="javascript:void(0)"
                  type="button"
                  v-b-tooltip.hover
                  title="Cập nhật"
                  class="m-2"
                  @click.prevent="navigateToUpdateOrder(row.item)"
                >
                  <i class="fas fa-edit" style="font-size: 1rem"></i>
                </a>
                <a
                  v-if="
                    row.item.orderStatus &&
                      checkShowAction(
                        ACTION_FOR_ORDER.DELETE,
                        row.item.orderStatus.id
                      )
                  "
                  href="javascript:void(0)"
                  type="button"
                  v-b-tooltip.hover
                  title="Xoá"
                  class="m-2"
                  @click.prevent="
                    openModalActionForOrder(row.item, ACTION_FOR_ORDER.DELETE)
                  "
                >
                  <i
                    class="fas fa-trash"
                    style="font-size: 1rem; color: red"
                  ></i>
                </a>
              </div>
              <div>
                <a
                  v-if="
                    row.item.orderStatus &&
                      checkShowAction(
                        ACTION_FOR_ORDER.CONFIRM,
                        row.item.orderStatus.id
                      )
                  "
                  href="javascript:void(0)"
                  type="button"
                  v-b-tooltip.hover
                  title="Duyệt"
                  class="m-2"
                  @click.prevent="
                    openModalActionForOrder(row.item, ACTION_FOR_ORDER.CONFIRM)
                  "
                >
                  <i class="fas fa-check" style="font-size: 1rem"></i>
                </a>
                <a
                  v-if="
                    row.item.orderStatus &&
                      checkShowAction(
                        ACTION_FOR_ORDER.REJECT,
                        row.item.orderStatus.id
                      )
                  "
                  href="javascript:void(0)"
                  type="button"
                  v-b-tooltip.hover
                  title="Huỷ"
                  class="m-2"
                  @click.prevent="
                    openModalActionForOrder(row.item, ACTION_FOR_ORDER.REJECT)
                  "
                >
                  <i
                    class="fas fa-times"
                    style="font-size: 1rem; color: red"
                  ></i>
                </a>
              </div>
            </div>
          </template>
        </b-table>

        <b-row v-if="getOrders && getOrders.length > 0">
          <b-col class="pagination">
            <b-pagination
              v-model="dataFilter.page"
              :per-page="dataFilter.limit"
              :total-rows="getOrders.length"
              @change="changePage"
            ></b-pagination>
          </b-col>
          <b-col class="mt-1">
            <span class="text-muted">
              {{ fromPage }} đến {{ toPage }} trên {{ getOrders.length }} bản
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
      id="confirm-action-for-order"
      :title="
        currentTitleModalAction
          ? currentTitleModalAction
          : 'Xác nhận thao tác với đơn hàng'
      "
      :no-close-on-backdrop="true"
    >
      <div class="pb-3">
        <span>{{
          currentContentModalAction ? currentContentModalAction : ""
        }}</span>
        <span class="font-weight-bold" v-if="this.currentOrder">{{
          this.currentOrder.orderId
        }}</span>
        không ?
      </div>
      <b-button
        class="mr-2 btn-light2 pull-right"
        @click="cancelActionForOrder()"
      >
        Hủy
      </b-button>
      <b-button
        variant="primary pull-right"
        class="mr-2"
        type="submit"
        @click="handleActionForOrder()"
      >
        Đồng ý
      </b-button>
    </b-modal>
    <ModalCreateOrder
      :order="currentOrder"
      :orderDetail="currentOrderDetail"
      :currentTitleModal="currentTitleModalCreateOrder"
      :isUpdate="isUpdateOrder"
      @cancelCreateOrder="cancelCreateOrder"
    />
    <b-modal
      hide-footer
      id="delete-order-detail"
      :title="'Xác nhận xoá sản phẩm khỏi đơn hàng'"
      :no-close-on-backdrop="true"
    >
      <div class="pb-3">
        Bạn có muốn xoá sản phẩm
        <span
          class="font-weight-bold"
          v-if="currentOrderDetail && currentOrderDetail.product"
        >
          {{ currentOrderDetail.product.productName }}</span
        >
        khỏi đơn hàng không ?
      </div>
      <b-button
        class="mr-2 btn-light2 pull-right"
        @click="cancelDeleteOrderDetail()"
      >
        Hủy
      </b-button>
      <b-button
        variant="primary pull-right"
        class="mr-2"
        type="submit"
        @click="handleDeleteOrderDetail()"
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
import { formatDateTime } from "../../common/utils";
import {
  FETCH_ORDERS,
  UPDATE_ORDER,
  DELETE_ORDER,
  FETCH_ORDER_DETAIL_BY_ORDER_ID,
  CREATE_ORDER_DETAIL,
  UPDATE_ORDER_DETAIL,
  DELETE_ORDER_DETAIL,
} from "@/store/action.type";
import { formatPriceSearchV2 } from "../../common/common";
import ModalCreateOrder from "@/Layout/Components/admin/ModalCreateOrder.vue";
const initDataFilter = {
  page: 1,
  limit: 10,
};
const ACTION_FOR_ORDER = {
  UPDATE: "update",
  DELETE: "delete",
  CONFIRM: "confirm",
  REJECT: "reject",
};
const initOrder = {
  totalPrice: 0,
  note: null,
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
};
const initOrderDetail = {
  order_detail_id: null,
  order: null,
  product: null,
  productName: null,
  productPrice: 0,
  quantity: null,
};
export default {
  name: "OrderManagement",
  data() {
    return {
      ACTION_FOR_ORDER,
      subheading: "Tạo và quản lý các đơn hàng",
      icon: "pe-7s-portfolio icon-gradient bg-happy-itmeo",
      heading: "Quản lý đơn hàng",
      loadingHeader: true,
      dataFilter: Object.assign({}, { ...initDataFilter }),
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
      isUpdateOrder: false,
      currentOrder: null,
      currentOrderDetail: [],
      currentActionForOrder: null,
      currentTitleModalAction: null,
      currentContentModalAction: null,
      currentTitleModalCreateOrder: null,
      orderStatusOption: [
        { value: "1", text: "Chờ xác nhận" },
        { value: "2", text: "Đã xác nhận" },
        { value: "3", text: "Đã huỷ" },
      ],
      fields: [
        {
          key: "key",
          label: "STT",
          tdClass: "align-middle",
          thClass: "align-middle",
          visible: true,
          thStyle: { width: "4%" },
        },
        {
          key: "user",
          label: "Người tạo đơn",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-left align-middle",
        },
        {
          key: "place",
          label: "Địa chỉ",
          visible: true,
          thStyle: { width: "15%" },
          thClass: "text-left align-middle",
        },
        {
          key: "phoneNumber",
          label: "Số điện thoại",
          visible: true,
          thStyle: { width: "6%" },
          thClass: "text-left align-middle",
        },
        {
          key: "totalPrice",
          label: "Tổng giá",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-left align-middle",
        },
        {
          key: "promotion",
          label: "Khuyến mại",
          visible: true,
          thStyle: { width: "6%" },
          thClass: "text-center align-middle",
          tdClass: "text-center",
        },
        {
          key: "orderStatus",
          label: "Trạng thái",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-center align-middle",
          tdClass: "text-center",
        },
        {
          key: "date",
          label: "Ngày tạo",
          visible: true,
          thStyle: { width: "6%" },
          thClass: "text-left align-middle",
        },
        {
          key: "actions",
          label: "Chức năng",
          visible: true,
          thStyle: { width: "10%" },
          thClass: "text-center align-middle",
        },
      ],
    };
  },
  mixins: [baseMixins],
  components: {
    PageTitle,
    ModalCreateOrder,
  },
  mounted() {
    this.fetchOrders();
  },
  computed: {
    ...mapGetters(["getOrders"]),
    visibleFields() {
      return this.fields.filter((field) => field.visible);
    },
    fromPage() {
      return (this.dataFilter.page - 1) * this.dataFilter.limit + 1;
    },
    toPage() {
      return this.getOrders.length
        ? this.dataFilter.page * this.dataFilter.limit >= this.getOrders.length
          ? this.getOrders.length
          : this.dataFilter.page * this.dataFilter.limit
        : 0;
    },
  },
  methods: {
    getUserPlace(order) {
      if (!order) return "";
      return [order.address, order.wards, order.district, order.city]
        .map((item) => {
          if (!item || item === "") return;
          return item;
        })
        .filter((item) => item)
        .join(", ");
    },
    checkShowAction(action, status) {
      if (!status) return false;
      if (action === ACTION_FOR_ORDER.UPDATE) return status === 1;
      if (action === ACTION_FOR_ORDER.DELETE)
        return status === 1 || status === 3;
      if (action === ACTION_FOR_ORDER.CONFIRM) return status === 1;
      if (action === ACTION_FOR_ORDER.REJECT) return status === 1;
    },
    formatDateTime(date) {
      if (!date) return "";
      return formatDateTime(new Date(date));
    },
    formatPrice(price) {
      if (!price) return "";
      return formatPriceSearchV2(price + "");
    },
    changePage(e) {
      this.dataFilter.page = e;
    },
    async fetchOrders() {
      let response = await this.$store.dispatch(FETCH_ORDERS);
      if (response) {
        setTimeout(() => {
          if (this.loadingHeader) this.loadingHeader = !this.loadingHeader;
        }, 200);
      }
      if (response && response.data)
        this.$store.commit("setOrders", response.data.data);
    },
    navigateToOrderDetail(orderId) {
      this.$router.push({
        path: "/admin/order-detail/order/",
        query: { orderId: orderId },
      });
    },
    navigateToUpdateOrder(order) {
      if (!order.orderId) return;
      this.isUpdateOrder = true;
      this.currentTitleModalCreateOrder = "Cập nhật đơn hàng";
      this.currentOrder = { ...order };
      this.$root.$emit("bv::show::modal", "modal-create-order");
      // this.fetchOrderDetailById(order.orderId, () => {
      //   this.$root.$emit("bv::show::modal", "modal-create-order");
      // })
    },
    navigateToCreateOrder() {
      this.currentTitleModalCreateOrder = "Tạo đơn hàng";
      this.currentOrder = Object.assign({}, { ...initOrder });
      this.$root.$emit("bv::show::modal", "modal-create-order");
    },
    cancelCreateOrder(isFetchOrders) {
      this.currentOrder = Object.assign({}, { ...initOrder });
      this.currentTitleModalCreateOrder = null;
      this.isUpdateOrder = false;
      if (isFetchOrders) this.fetchOrders();
    },
    openModalActionForOrder(order, action) {
      this.$root.$emit("bv::show::modal", "confirm-action-for-order");
      this.currentOrder = { ...order };
      switch (action) {
        case ACTION_FOR_ORDER.DELETE: {
          this.currentActionForOrder = ACTION_FOR_ORDER.DELETE;
          this.currentTitleModalAction = "Xác nhận xoá đơn hàng";
          this.currentContentModalAction = "Bạn có muốn xoá đơn hàng";
          break;
        }
        case ACTION_FOR_ORDER.CONFIRM: {
          this.currentActionForOrder = ACTION_FOR_ORDER.CONFIRM;
          this.currentTitleModalAction = "Xác nhận duyệt đơn hàng";
          this.currentContentModalAction = "Bạn có muốn duyệt đơn hàng";
          break;
        }
        case ACTION_FOR_ORDER.REJECT: {
          this.currentActionForOrder = ACTION_FOR_ORDER.REJECT;
          this.currentTitleModalAction = "Xác nhận huỷ đơn hàng";
          this.currentContentModalAction = "Bạn có muốn huỷ đơn hàng";
          break;
        }
        default: {
          this.currentTitleModalAction = "";
          this.currentContentModalAction = "";
        }
      }
    },
    cancelActionForOrder() {
      this.currentOrder = null;
      this.currentTitleModalAction = "";
      this.currentContentModalAction = "";
      this.currentActionForOrder = null;
      this.$root.$emit("bv::hide::modal", "confirm-action-for-order");
    },
    handleActionForOrder() {
      if (this.currentActionForOrder === ACTION_FOR_ORDER.DELETE)
        this.handleDeleteOrder();
      if (
        this.currentActionForOrder === ACTION_FOR_ORDER.CONFIRM ||
        this.currentActionForOrder === ACTION_FOR_ORDER.REJECT
      )
        this.handleConfirmAndRejectOrder();
    },
    async handleConfirmAndRejectOrder() {
      if (!this.currentOrder || !this.currentOrder.orderId) {
        this.$message.closeAll();
        this.$message({
          message: "Thao tác với đơn hàng không thành công.",
          type: "error",
          showClose: true,
        });
        return;
      }
      let {
        totalPrice,
        note,
        orderStatus,
        date,
        promotion,
        address,
        city,
        district,
        wards,
        phoneNumber,
      } = { ...this.currentOrder };
      let payload = {
        totalPrice: totalPrice + "",
        phoneNumber: phoneNumber ? Number(phoneNumber) : "",
        orderStatusId:
          this.currentActionForOrder === ACTION_FOR_ORDER.CONFIRM
            ? "2"
            : this.currentActionForOrder === ACTION_FOR_ORDER.REJECT
            ? "3"
            : orderStatus.id + "",
        promotionId: promotion ? promotion.promotionId : null,
        address,
        city,
        district,
        wards,
        phoneNumber,
        note,
        date,
      };
      let res = await this.$store.dispatch(UPDATE_ORDER, {
        orderId: this.currentOrder.orderId,
        orderData: payload,
      });
      if (res && res.status === 200) {
        this.$message.closeAll();
        this.$message({
          message: "Thao tác với đơn hàng thành công.",
          type: "success",
          showClose: true,
        });
        this.fetchOrders();
        this.cancelActionForOrder();
      }
    },
    async handleDeleteOrder() {
      if (!this.currentOrder || !this.currentOrder.orderId) {
        this.$message.closeAll();
        this.$message({
          message: "Xoá đơn hàng không thành công.",
          type: "error",
          showClose: true,
        });
        return;
      }
      let res = await this.$store.dispatch(
        DELETE_ORDER,
        this.currentOrder.orderId
      );
      if (res && res.status === 200) {
        this.$message.closeAll();
        this.$message({
          message: "Xoá đơn hàng thành công.",
          type: "success",
          showClose: true,
        });
        this.fetchOrders();
        this.cancelActionForOrder();
      }
    },
  },
};
</script>

<style scoped></style>
