<template>
  <div>
    <Progress></Progress>
    <slot></slot>
  </div>
</template>

<script src="@/assets/js/jquery-3.3.1.min.js"></script>
<script src="@/assets/js/bootstrap.min.js"></script>
<script src="@/assets/js/jquery.nice-select.min.js"></script>
<script src="@/assets/js/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/SlickNav/1.0.10/jquery.slicknav.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="@/assets/js/mixitup.min.js"></script>
<script src="@/assets/js/owl.carousel.min.js"></script>
<script src="@/assets/js/main.js"></script>
  
<script>
import Progress from "@/components/Progress";

export default {
	name: "UserLayout",
	components: {
		Progress,
	},
	methods: {
	},
	created() {
		// handleJQuery()
	}
};
</script>

<style>
</style>
  