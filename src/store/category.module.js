import { FETCH_CATEGORY, FETCH_CATEGORY_BY_ID, CREATE_CATEGORY, UPDATE_CATEGORY, DELETE_CATEGORY,FETCH_CATEGORY_BY_CATEGORYNAME } from "./action.type";
import baseMixins from '../components/mixins/base'
const state = {
  category: []
}
const getters = {
  getCategory(state) {
    return state.category;
  },
}

const mutations = {
  setCategory(state, payload) {
    state.category = payload;
  },
}

const actions = {
  [FETCH_CATEGORY](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/categories')
      if (response && response.status === 200 && response.data) {
        context.commit("setCategory", response.data.data)
      }
      resolve(response)
    })
  },
  [FETCH_CATEGORY_BY_ID](context, categoryId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/categories', categoryId)
      resolve(response)
    })
  },
  [FETCH_CATEGORY_BY_CATEGORYNAME](context, categoryName) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt(`/rest/categories/searchCategoryByName/${categoryName}`)
      resolve(response)
    })
  },
  [DELETE_CATEGORY](context, categoryId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.delete(`/rest/categories/${categoryId}`)
      resolve(response)
    })
  },
  [UPDATE_CATEGORY](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/categories/${payload.categoryId}`, payload.categoryData)
      resolve(response)
    })
  },
  [CREATE_CATEGORY](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post(`/rest/categories`, payload)
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