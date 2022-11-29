import baseMixins from '../components/mixins/base'
const state = {
  cart: null,
  checkout: null,
}
const getters = {
  getCart(state) {
    return state.cart;
  },
  getCheckout(state) {
    return state.checkout;
  }
}

const mutations = {
  setCart(state, payload) {
    state.cart = payload;
  },
  setCheckout(state, payload) {
    state.checkout = payload;
  },
}

const actions = {
  updateCart(context, payload) {
    context.commit("setCart", payload)
  },
  updateCheckout(context, payload) {
    context.commit("setCheckout", payload)
  },
}

export default {
  state,
  actions,
  mutations,
  getters
};
