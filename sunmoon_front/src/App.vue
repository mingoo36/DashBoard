<!-- src/views/Dashboard.vue -->
<template>
  <div>
      <Sidebar />
      <router-view />
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
