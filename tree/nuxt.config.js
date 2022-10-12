export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'tree-world',
    htmlAttrs: {
      lang: 'en',
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' },
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
    script: [
      {
        src: '@/assets/js/main.js',
      },
      {
        src: '@/assets/js/jquery-3.3.1.min.js',
      },
      {
        src: '@/assets/js/jquery.slicknav.js',
      },
      {
        src: '@/assets/js/jquery.nice-select.js',
      },
      {
        src: '@/assets/js/jquery-ui.min.js',
      },
      {
        src: '@/assets/js/mixitup.min.js',
      },
      {
        src: '@/assets/js/owl.carousel.min.js',
      },
    ],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    '@/assets/css/style.css',
    '@/assets/css/slicknav.min.css',
    // '@/assets/css/owl.carousel.min.css',
    '@fortawesome/fontawesome-svg-core/styles.css',
    '@/assets/css/nice-select.css',
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '@/plugins/fontawesome.js',

  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/eslint
    '@nuxtjs/eslint-module',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/axios
    'bootstrap-vue/nuxt',
    '@nuxtjs/axios',
  ],

  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: {
    // Workaround to avoid enforcing hard-coded localhost:3000: https://github.com/nuxt-community/axios-module/issues/308
    baseURL: '/',
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {},
}
