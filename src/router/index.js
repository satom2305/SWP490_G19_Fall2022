import Vue from 'vue'
import Router from 'vue-router'
import StorageService from '@/common/storage.service';

Vue.use(Router)
const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      meta: { layout: 'userpages', loginPage: true, nonRequiresAuth: true, title: 'Đăng nhập' },
      component: () => import("@/views/Login"),
    },
    {
      path: '/register',
      name: 'Register',
      meta: { layout: 'userpages', loginPage: true, nonRequiresAuth: true, title: 'Đăng ký tài khoản' },
      component: () => import("@/views/Register"),
    },
    {
      path: '/reset-password',
      name: 'ResetPassword',
      component: () => import("@/views/ResetPassword"),
      meta: { layout: 'userpages', loginPage: true, nonRequiresAuth: true, title: 'Quên mật khẩu' },
    },
    {
      path: '/',
      name: 'HomePage',
      meta: { title: 'Tree World', layout: 'userpages' },
      component: () => import("@/views/HomePage"),
    },
    {
      path: '/tree-world',
      name: 'HomePage',
      meta: { title: 'Tree World', layout: 'userpages' },
      component: () => import("@/views/HomePage"),
    },
    {
      path: '/shop-product',
      name: 'ShopProduct',
      meta: { title: 'Shop Product', layout: 'userpages' },
      component: () => import("@/views/ShopProduct"),
    },
    {
      path: '/shop-detail/:id',
      name: 'ShopDetail',
      meta: { title: 'Shop Detail', layout: 'userpages' },
      component: () => import("@/views/ShopDetail"),
    },
    {
      path: '/shopping-cart',
      name: 'ShoppingCart',
      meta: { title: 'Shopping Cart', layout: 'userpages' },
      component: () => import("@/views/ShoppingCart"),
    },
    {
      path: '/check-out',
      name: 'Checkout',
      meta: { title: 'Checkout', layout: 'userpages' },
      component: () => import("@/views/Checkout"),
    },
    {
      path: '/blog-detail/:id',
      name: 'BlogDetail',
      meta: { title: 'BlogDetail', layout: 'userpages' },
      component: () => import("@/views/BlogDetail"),
    },
    {
      path: '/Blog',
      name: 'Blog',
      meta: { title: 'Blog', layout: 'userpages' },
      component: () => import("@/views/Blog"),
    },
    {
      path: '/contact',
      name: 'Contact',
      meta: { title: 'Contact', layout: 'userpages' },
      component: () => import("@/views/Contact"),
    },
    {
      path: '/admin',
      name: 'DefaultPage',
      meta: { title: 'Quản lý Tree World', layout: 'default' }
    },
    {
      path: '/admin/user',
      name: 'UserManagement',
      meta: { title: 'Quản lý người dùng', layout: 'default' },
      component: () => import("@/views/admin/User"),
    },
    {
      path: '/admin/product',
      name: 'ProductManagement',
      meta: { title: 'Quản lý sản phẩm', layout: 'default' },
      component: () => import("@/views/admin/Product"),
    },
    {
      path: '/admin/product/create',
      name: 'ProductCreate',
      meta: { title: 'Tạo sản phẩm', layout: 'default' },
      component: () => import("@/views/admin/ProductCreate"),
    },
    {
      path: '/admin/product/update/:id',
      name: 'ProductUpdate',
      meta: { title: 'Cập nhật sản phẩm', layout: 'default' },
      component: () => import("@/views/admin/ProductCreate"),
    },
    {
      path: '/admin/product',
      name: 'ProductManagement',
      meta: { title: 'Quản lý sản phẩm', layout: 'default' },
      component: () => import("@/views/admin/Product"),
    },
    {
      path: '/admin/product-category',
      name: 'ProductCategoryManagement',
      meta: { title: 'Quản lý loại sản phẩm', layout: 'default' },
      component: () => import("@/views/admin/ProductCategory"),
    },
    {
      path: '/admin/promotion',
      name: 'PromotionManagement',
      meta: { title: 'Quản lý khuyến mại', layout: 'default' },
      component: () => import("@/views/admin/Promotion"),
    },
    {
      path: '/admin/post',
      name: 'PostManagement',
      meta: { title: 'Quản lý bài đăng', layout: 'default' },
      component: () => import("@/views/admin/Post"),
    },
    {
      path: '/admin/post/create',
      name: 'PostCreate',
      meta: { title: 'Tạo bài đăng', layout: 'default' },
      component: () => import("@/views/admin/PostCreate"),
    },
    {
      path: '/admin/post/update/:id',
      name: 'PostUpdate',
      meta: { title: 'Cập nhật bài đăng', layout: 'default' },
      component: () => import("@/views/admin/PostCreate"),
    },
    {
      path: '/admin/order',
      name: 'OrderManagement',
      meta: { title: 'Quản lý đơn hàng', layout: 'default' },
      component: () => import("@/views/admin/Order"),
    },
    {
      path: '/error-not-allow',
      name: 'ErrorNotAllow',
      component: () => import("@/views/ErrorNotAllow"),
      meta: { title: 'Lỗi', layout: 'userpages' }
    }
  ]
});
router.beforeEach((to, from, next) => {
  if (to.path !== '/login') {
    localStorage.setItem('savedPath', to.fullPath)
  }
  const publicPages = ['/login', '/config-api', '/register', '/reset-password'];
  const authRequired = !publicPages.includes(to.path);
  const isAdmin = StorageService.get("userInfo") && JSON.parse(StorageService.get("userInfo")).role === '[ADMIN]'
  const isGuest = StorageService.get("userInfo") && JSON.parse(StorageService.get("userInfo")).role === '[GUEST]'
  const isAuthenticated = !!StorageService.get("Token") || isGuest;
  if (to.path.includes('/admin') && isAuthenticated && !isAdmin) {
    next('/error-not-allow')
  }
  if (authRequired && !isAuthenticated) {
    next('/login')
  }
  next()
});

router.onError(error => {
  console.error(error);
  Vue.prototype.$log.error('Failure Reason: ', error.message, error);
  if (/ChunkLoadError:.*failed./i.test(error.message)) {
    Vue.prototype.$log.error('Reloading Window 1');
    window.location.reload();
  }
  else if (/Loading.*chunk.*failed./i.test(error.message)) {
    Vue.prototype.$log.error('Reloading Window 2');
    window.location.reload();
  }
});

const DEFAULT_TITLE = 'Ewallet web admin'
router.afterEach((to, from) => {
  Vue.nextTick(() => {
    document.title = to.meta.title || DEFAULT_TITLE
  })
})

export default router;