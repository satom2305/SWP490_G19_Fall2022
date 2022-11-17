import {
  FETCH_ORDERS, CREATE_ORDER, UPDATE_ORDER, DELETE_ORDER,
  FETCH_ORDER_DETAIL, FETCH_ORDER_DETAIL_BY_ID, FETCH_ORDER_DETAIL_BY_ORDER_ID, CREATE_ORDER_DETAIL,
  UPDATE_ORDER_DETAIL, UPDATE_ORDER_DETAIL_BY_ORDER_ID, CREATE_ORDER_DETAIL_BY_ORDER_ID, DELETE_ORDER_DETAIL
} from "./action.type";
import baseMixins from '../components/mixins/base'
const state = {
  orders: [],
  orderDetails: [],
}
const getters = {
  getOrders(state) {
    return state.orders;
  },
  getOrderDetails(state) {
    return state.orderDetails;
  }
}

const mutations = {
  setOrders(state, payload) {
    state.orders = payload;
  },
  setOrderDetails(state, payload) {
    state.orderDetails = payload;
  },
}

const actions = {
  [FETCH_ORDERS](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/orders')
      if (response && response.status === 200 && response.data) {
        context.commit("setOrders", response.data.data)
      }
      resolve(response)
    })
  },
  [CREATE_ORDER](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post('/rest/orders', payload)
      resolve(response)
    })
  },
  [UPDATE_ORDER](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/orders/${payload.orderId}`, payload.orderData)
      resolve(response)
    })
  },
  [DELETE_ORDER](context, orderId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.delete(`/rest/orders/${orderId}`)
      resolve(response)
    })
  },
  [FETCH_ORDER_DETAIL](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/orderDetails')
      if (response && response.status === 200 && response.data) {
        context.commit("setOrderDetails", response.data.data)
      }
      resolve(response)
    })
  },
  [FETCH_ORDER_DETAIL_BY_ID](context, orderId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.get(`/rest/orderDetails/${orderId}`)
      resolve(response)
    })
  },
  [FETCH_ORDER_DETAIL_BY_ORDER_ID](context, orderId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.get(`/rest/orderDetails/order/${orderId}`)
      resolve(response)
    })
  },
  [CREATE_ORDER_DETAIL](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post('/rest/orderDetails', payload)
      resolve(response)
    })
  },
  [UPDATE_ORDER_DETAIL](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/orderDetails/${payload.orderDetailId}`, payload.orderDetailData)
      resolve(response)
    })
  },
  [CREATE_ORDER_DETAIL_BY_ORDER_ID](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post('/rest/orderDetails/listOrderDetail', payload)
      resolve(response)
    })
  },
  [UPDATE_ORDER_DETAIL_BY_ORDER_ID](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/orderDetails/order/${payload.orderId}`, payload.orderDetailData)
      resolve(response)
    })
  },
  [DELETE_ORDER_DETAIL](context, orderDetailId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.delete(`/rest/orderDetails/${orderDetailId}`)
      resolve(response)
    })
  },
}

export default {
  state,
  actions,
  mutations,
  getters
};