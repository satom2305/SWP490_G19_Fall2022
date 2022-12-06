import { FETCH_USERS, FETCH_USER_BY_USERNAME, FETCH_USER_BY_ID, UPDATE_USER, CREATE_USER, DISABLE_USER, CHANGE_PASSWORD, CREATE_STAFF } from "./action.type";
import baseMixins from '../components/mixins/base'
const state = {
  users: []
}
const getters = {
  getUsers(state) {
    return state.users;
  },
}

const mutations = {
  setUsers(state, payload) {
    state.users = payload;
  },
}

const actions = {
  [FETCH_USERS](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/users')
      if (response && response.status === 200 && response.data) {
        context.commit("setUsers", response.data.data)
      }
      resolve(response)
    })
  },
  [FETCH_USER_BY_ID](context, userId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/users/userId', userId)
      resolve(response)
    })
  },
  [FETCH_USER_BY_USERNAME](context, userName) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.getWithBigInt('/rest/users', userName)
      resolve(response)
    })
  },
  [DISABLE_USER](context, userId) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/users/disable/${userId}`)
      resolve(response)
    })
  },
  [UPDATE_USER](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.put(`/rest/users/${payload.userId}`, payload.userData)
      resolve(response)
    })
  },
  [CREATE_USER](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post(`/rest/users`, payload)
      resolve(response)
    })
  },
  [CREATE_STAFF](context, payload) {
    return new Promise(async resolve => {
      let response = await baseMixins.methods.post(`/rest/users/staff`, payload)
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