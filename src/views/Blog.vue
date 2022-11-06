<template>
  <BlogPage />
</template>

<script>
import { handleJQuery } from '../common/utils';
import BlogPage from './BlogPage.vue';
export default {
  name: 'blog',
  components: {BlogPage},
  created() {
    handleJQuery
  }
}
</script>

<style scoped></style>
