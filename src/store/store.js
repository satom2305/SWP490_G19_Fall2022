import Vue from "vue";
import Vuex from "vuex";
import auth from "./auth.module";
import user from "./user.module";
import category from "./category.module";
import product from "./product.module";
import promotion from "./promotion.module"

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    user,
    category,
    product,
    promotion,
  }
});