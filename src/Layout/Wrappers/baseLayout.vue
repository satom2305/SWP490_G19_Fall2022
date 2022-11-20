<template>
  <div class="app-container app-theme-white">
    <Progress></Progress>
    <transition name="fade" mode="out-in" appear>
      <Header/>
    </transition>
    <transition name="fade" mode="out-in" appear>
      <Sidebar/>
    </transition>
    <div class="app-main__outer" :style="{'z-index': '8'}">
      <template>
        <div class="app-main__inner">
          <slot></slot>
        </div>
        <transition name="fade" mode="out-in" appear>
          <Footer/>
        </transition>
      </template>
    </div>
    <!-- Modal Component -->
    <b-modal id="modallg" size="lg" title="Large Modal"> Hello Modal!</b-modal>
    <b-modal id="modalsm" size="sm" title="Small Modal"> Hello Modal!</b-modal>
  </div>
</template>

<script>
import Progress from "@/components/Progress";
import Header from "../Components/admin/Header.vue";
import Sidebar from "../Components/Sidebar";
import Footer from "../Components/Footer";

import {library} from "@fortawesome/fontawesome-svg-core";
import {faCog} from "@fortawesome/free-solid-svg-icons";

library.add(faCog);
export default {
  name: "app",
  components: {
    Header,
    Sidebar,
    Footer,
    Progress,
  },
  methods: {},
};
</script>
