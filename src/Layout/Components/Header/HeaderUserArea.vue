<template>
  <div class="d-flex">
    <div class="header-btn-lg pr-0">
      <div class="widget-content p-0">
        <div class="widget-content-wrapper">
          <div class="widget-content-left header-user-info">
            <div class="widget-heading">
              {{ userInfo.fullName }}
            </div>
            <div class="widget-subheading">
              {{ userInfo.username }}
            </div>
          </div>
          <div class="widget-content-left ml-3">
            <b-dropdown
              toggle-class="p-0 mr-2"
              menu-class="dropdown-menu-lg"
              variant="link"
              right
            >
              <span slot="button-content">
                <div class="icon-wrapper icon-wrapper-alt rounded-circle">
                  <img
                    width="42"
                    class="rounded-circle"
                    src="@/assets/images/avatars/default-user.png"
                    alt=""
                  />
                </div>
              </span>
              <div tabindex="-1" class="dropdown-divider"></div>
              <button
                type="button"
                tabindex="0"
                class="dropdown-item"
                @click="navigateToUserPage"
              >
                Trang bán hàng
              </button>
              <div tabindex="-1" class="dropdown-divider"></div>
              <button
                type="button"
                tabindex="0"
                class="dropdown-item"
                @click="logout"
              >
                Đăng xuất
              </button>
            </b-dropdown>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getAuthenticatedUser, logout, performLogout } from "@/common/utils";
import baseMixins from "../../../components/mixins/base";

import { mapGetters } from "vuex";
import axios from "axios";
import StorageService from "@/common/storage.service";
import Configuration from "@/configuration";
const API_ENDPOINT = Configuration.value("baseURL");

export default {
  name: "HeaderUserArea",
  data() {
    return {};
  },
  mixins: [baseMixins],
  computed: {
    ...mapGetters(["userInfo"]),
  },
  mounted() {
    this.fetchUserInfo();
  },
  methods: {
    fetchUserInfo() {
      this.$store.dispatch("fetchUserInfo");
    },
    logout() {
      logout();
    },
    navigateToUserPage() {
      this.$router.push({ path: `/` });
    },
  },
};
</script>

<style lang="scss" scoped>
.widget-heading {
  color: #01904a !important;
}
.widget-subheading {
  color: #01904a !important;
}
.widget-content-wrapper {
  padding: 0 1rem;
}
</style>
