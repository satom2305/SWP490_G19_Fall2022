import StorageService from "../common/storage.service"
import baseMixins from '../components/mixins/base'
import axios from "axios";
import Configuration from "@/configuration";

const API_ENDPOINT = Configuration.value("baseURL");
import {
  LOGIN, REGISTER, CHANGE_PASSWORD, SEND_EMAIL_RESET_PASSWORD
} from "@/store/action.type"; 

const state = {
  intervalName: "",
  userInfo: {
    id: 0,
    fullName: "",
    username: "",
    createAt: "",
    roles: ""
  }
}
const getters = {
  getIntervalName(state) {
    return state.intervalName;
  },
  userInfo(state) {
    return state.userInfo;
  }
}

const mutations = {
  setIntervalName(state, intervalName) {
    state.intervalName = intervalName;
  },
  setUserInfo(state, userInfo) {
    state.userInfo = userInfo;
  }
}

const actions = {
  fetchUserInfo(context) {
    if (StorageService.get("userInfo")) {
      let userInfo = JSON.parse(StorageService.get("userInfo"));
      context.commit("setUserInfo", userInfo);
    }
  },
  [LOGIN](context, payload) {
    return new Promise(async resolve => {
      axios.post(`${API_ENDPOINT}/login`,payload).then(response => {
        resolve(response)
      }).catch((error) => {
        resolve(error)
      })
    })
  },
  [REGISTER](context, payload) {
    return new Promise(async resolve => {
      axios.post(`${API_ENDPOINT}/rest/users`,payload).then(response => {
        resolve(response)
      }).catch((error) => {
        resolve(error)
      })
    })
  },
  [SEND_EMAIL_RESET_PASSWORD](context, payload) {
    return new Promise(async resolve => {
      axios.post(`${API_ENDPOINT}/rest/users/forgetPassword`,payload).then(response => {
        resolve(response)
      }).catch((error) => {
        resolve(error)
      })
    })
  },
  [CHANGE_PASSWORD](context, payload) {
    return new Promise(async resolve => {
      axios.put(`${API_ENDPOINT}/rest/users/changePwd/${payload}/${payload}`).then(response => {
        resolve(response)
      }).catch((error) => {
        resolve(error)
      })
    })
  }
}

export default {
  namespaced: false,
  state,
  actions,
  mutations,
  getters
};
