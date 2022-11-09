import { FETCH_PROMOTIONS, FETCH_PROMOTION_BY_CODE, CREATE_PROMOTION, UPDATE_PROMOTION, DELETE_PROMOTION } from "./action.type";
import baseMixins from '../components/mixins/base'
const state = {
  promotions: []
}
const getters = {
  getPromotions(state) {
    return state.promotions;
  },
}

const mutations = {
  setPromotions(state, payload) {
    state.promotions = payload;
  },
}

const actions = {
  [FETCH_PROMOTIONS](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/promotions')
      if (response && response.status === 200 && response.data) {
        context.commit("setPromotions", response.data.data)
      }
      resolve(response)
    })
  },
  [FETCH_PROMOTION_BY_CODE](context, promotionCode) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/promotions', promotionCode)
      resolve(response)
    })
  },
  [CREATE_PROMOTION](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post(`/rest/promotions`, payload)
      resolve(response)
    })
  },
  [UPDATE_PROMOTION](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/promotions/${payload.promotionId}`, payload.promotionData)
      resolve(response)
    })
  },
  [DELETE_PROMOTION](context, promotionId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.delete(`/rest/promotions/${promotionId}`)
      resolve(response)
    })
  },
}

export default {
  state,
  actions,
  mutations,
  getters,
};