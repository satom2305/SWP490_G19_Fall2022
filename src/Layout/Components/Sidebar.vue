<template>
  <div>
    <div
      class="app-sidebar sidebar-shadow"
      @mouseover="toggleSidebarHover('add', 'closed-sidebar-open')"
      @mouseleave="toggleSidebarHover('remove', 'closed-sidebar-open')"
    >
      <div class="app-header__logo">
        <img class="logo-header" src="@/assets/static/images/logo.png" />
        <span class="title-logo">Tree World</span>
      </div>
      <div class="app-sidebar-content custom-box-shadow">
        <VuePerfectScrollbar class="app-sidebar-scroll" v-once>
          <SidebarV2 :menu="menu" :collapsed="collapsed" />
        </VuePerfectScrollbar>
      </div>
    </div>
    <div class="app-header__toggle" @click="toggleSidebar('closed-sidebar')">
      <template v-if="!clickedToggleSidebar">
        <i class="fas fa-bars"></i>
      </template>
      <template v-else>
        <i class="fas fa-align-left"></i>
      </template>
    </div>
  </div>
</template>

<script>
import VuePerfectScrollbar from "vue-perfect-scrollbar";
import SidebarV2 from "./SidebarV2";
import "@fortawesome/fontawesome-free/css/all.css";

export default {
  components: {
    VuePerfectScrollbar,
    SidebarV2,
  },
  data() {
    return {
      isOpen: false,
      sidebarActive: false,
      menu: [
        {
          title: "Sản phẩm",
          icon: "fas fa-tree",
          child: [
            {
              href: "/admin/product-category",
              title: "Danh sách loại sản phẩm",
            },
            {
              href: "/admin/product",
              title: "Danh sách sản phẩm",
            },
            {
              href: "/admin/promotion",
              title: "Danh sách khuyến mại",
            },
          ],
        },
        {
          title: "Quản trị",
          icon: "fa fa-user",
          child: [
            {
              href: "/admin/user",
              title: "Người dùng",
            },
          ],
        },
      ],
      collapsed: true,

      windowWidth: 0,

      clickedToggleSidebar: false,
    };
  },
  props: {
    sidebarbg: String,
  },
  methods: {
    toggleBodyClass(className) {
      const el = document.body;
      this.isOpen = !this.isOpen;

      if (this.isOpen) {
        el.classList.add(className);
      } else {
        el.classList.remove(className);
      }
    },
    toggleSidebar(className) {
      const el = document.body;
      this.sidebarActive = !this.sidebarActive;
      this.clickedToggleSidebar = !this.clickedToggleSidebar;

      this.windowWidth = document.documentElement.clientWidth;

      if (this.windowWidth > "992") {
        if (this.clickedToggleSidebar) {
          el.classList.add(className);
        } else {
          el.classList.remove(className);
        }
      }
    },
    toggleSidebarHover(add, className) {
      const el = document.body;
      this.sidebarActive = !this.sidebarActive;

      this.windowWidth = document.documentElement.clientWidth;

      if (this.windowWidth > "992") {
        if (add === "add") {
          el.classList.add(className);
        } else {
          el.classList.remove(className);
        }
      }
    },
    getWindowWidth() {
      const el = document.body;

      this.windowWidth = document.documentElement.clientWidth;

      if (this.windowWidth < "1350") {
        this.clickedToggleSidebar = true;
        el.classList.add("closed-sidebar", "closed-sidebar-md");
      } else {
        this.clickedToggleSidebar = false;
        el.classList.remove("closed-sidebar", "closed-sidebar-md");
      }
    },
  },
  created() {
    const userInfo = localStorage.getItem("userInfo")
      ? JSON.parse(localStorage.getItem("userInfo"))
      : null;

    if (!userInfo) return;

    if (userInfo.role === "[ADMIN]") return;

    // userInfo.permissions = userInfo.permissions.replaceAll(' ', '').split(',')

    // this.menu.forEach((item) => {
    //   item.child = item.child.filter((child) => userInfo.permissions.includes(child.permission))
    // })

    // this.menu = this.menu.filter((item) => item.child.length > 0)
  },
  mounted() {
    this.$nextTick(function() {
      window.addEventListener("resize", this.getWindowWidth);

      //Init
      this.getWindowWidth();
    });
  },

  beforeDestroy() {
    window.removeEventListener("resize", this.getWindowWidth);
  },
};
</script>

<style lang="scss" scoped>
.logo-header {
  margin-top: 1px;
  height: 47px;
  background: no-repeat;
  cursor: pointer;
}
</style>

<style lang="scss">
@import "../../assets/custom-menu-antd.scss";
.logo-src {
  background: no-repeat;
}
.title-logo {
  margin-left: 20px;
  font-weight: 500;
  font-size: 16px;
}
.app-header__toggle {
  padding: 0 1rem;
  height: 60px;
  width: auto;
  display: flex;
  align-items: center;
  transition: width 0.2s;
  flex-shrink: 0;
  position: fixed;
  top: 0;
  margin-left: 1rem;
  left: calc(300px);
  z-index: 10;
  cursor: pointer;

  i {
    color: #01904a;
    font-size: 1.5rem;
    border-radius: 0 0 4px 4px;
    &:active {
      color: rgb(196, 196, 196);
    }
  }
}
.closed-sidebar {
  .app-header__toggle {
    left: 80px;
  }
}
.closed-sidebar-md {
  .app-header__toggle {
    display: none !important;
  }
}
.app-header__logo {
  color: #fff;
  background: #2e323a !important;
}
.app-sidebar-content {
  // background: linear-gradient(313deg, rgba(66,109,173,1) 0%, #342b2b 0%, rgba(55,82,113,1) 56%) !important;
}
</style>
