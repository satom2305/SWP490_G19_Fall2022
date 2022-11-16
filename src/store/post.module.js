import { FETCH_POSTS, FETCH_POST_BY_ID, CREATE_POST, UPDATE_POST, DELETE_POST } from "./action.type";
import baseMixins from '../components/mixins/base'
const state = {
  posts: []
}
const getters = {
  getPosts(state) {
    return state.posts;
  },
}

const mutations = {
  setPosts(state, payload) {
    state.posts = payload;
  },
}

const actions = {
  [FETCH_POSTS](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/posts')
      if (response && response.status === 200 && response.data) {
        context.commit("setPOSTS", response.data.data)
      }
      resolve(response)
    })
  },
  [FETCH_POST_BY_ID](context, postId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/posts', postId)
      resolve(response)
    })
  },
  [CREATE_POST](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post(`/rest/posts`, payload)
      resolve(response)
    })
  },
  [UPDATE_POST](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/posts/${payload.postId}`, payload.postData)
      resolve(response)
    })
  },
  [DELETE_POST](context, postId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/posts/${postId}`)
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