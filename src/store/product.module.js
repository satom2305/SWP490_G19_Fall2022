import { FETCH_PRODUCTS, FETCH_PRODUCTS_AVAILABLE, FETCH_PRODUCT_BY_ID, CREATE_PRODUCT, UPDATE_PRODUCT, DISABLE_PRODUCT, FETCH_PRODUCTS_BY_NAME } from "./action.type";
import baseMixins from '../components/mixins/base'
const state = {
  products: []
}
const getters = {
  getProducts(state) {
    return state.products;
  },
}

const mutations = {
  setProducts(state, payload) {
    state.products = payload;
  },
}

const actions = {
  [FETCH_PRODUCTS](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/products/listAllProduct')
      if (response && response.status === 200 && response.data) {
        context.commit("setProducts", response.data.data)
      }
      resolve(response)
    })
  },
  [FETCH_PRODUCTS_AVAILABLE](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/products/listProduct')
      if (response && response.status === 200 && response.data) {
        context.commit("setProducts", response.data.data)
      }
      resolve(response)
    })
  },
  [FETCH_PRODUCT_BY_ID](context, productId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/products', productId)
      resolve(response)
    })
  },
  [FETCH_PRODUCTS_BY_NAME](context, name) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt(`/rest/products/searchProductByName/${name}`)
      resolve(response)
    })
  },
  [CREATE_PRODUCT](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post(`/rest/products`, payload)
      resolve(response)
    })
  },
  [UPDATE_PRODUCT](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/products/${payload.productId}`, payload.productData)
      resolve(response)
    })
  },
  [DISABLE_PRODUCT](context, productId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/products/disable/${productId}`)
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