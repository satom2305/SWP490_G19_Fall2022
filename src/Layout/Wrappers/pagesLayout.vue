<template>
  <div class="app-container app-theme-white">
    <Progress></Progress>
    <slot></slot>
  </div>
</template>

<script>
import Progress from "@/components/Progress";

export default {
  name: "app",
  components: {
    Progress,
  },
  methods: {},
};
</script>
