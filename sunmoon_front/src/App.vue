<!-- src/views/Dashboard.vue -->
<template>
  <div>
      <Sidebar />
      <div class="main-content">
        <router-view />
      </div>
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue';
import store from "@/scripts/store";


export default {
  name: 'Dashboard',
  components: {
    Sidebar
  },
  setup(){
    const id = sessionStorage.getItem("id");

    if (id){
      store.commit("setAccount", id);
    }
  }
};
</script>

<style scoped>
</style>
