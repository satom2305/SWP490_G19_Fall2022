import { FETCH_REVIEWS, FETCH_REVIEW_BY_ID, CREATE_REVIEW, UPDATE_REVIEW } from "./action.type";
import baseMixins from '../components/mixins/base'
const state = {
  reviews: []
}
const getters = {
  getReviews(state) {
    return state.reviews;
  },
}

const mutations = {
  setReviews(state, payload) {
    state.reviews = payload;
  },
}

const actions = {
  [FETCH_REVIEWS](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/reviews')
      if (response && response.status === 200 && response.data) {
        context.commit("setReviews", response.data.data)
      }
      resolve(response)
    })
  },
  [FETCH_REVIEW_BY_ID](context, reviewId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/reviews', reviewId)
      resolve(response)
    })
  },
  [CREATE_REVIEW](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post(`/rest/reviews/createReview`, payload)
      resolve(response)
    })
  },
  [UPDATE_REVIEW](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/reviews/${payload.reviewId}`, payload.reviewData)
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