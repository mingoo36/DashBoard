<template>
  <div>
    <Sidebar />
    <div class="main-content">
      <h4>중대재해처벌법대응 운영솔루션</h4>

      <!-- 통계 카드 섹션 -->
      <DashboardStats />

      <!-- 그래프 섹션 -->
      <ChartContainer/>

      <!-- 활동 로그 섹션 -->
      <ActivityLog/>
    </div>
  </div>
</template>

<script>
//import axios from "axios";
import {reactive} from "vue";
import ChartContainer from "@/components/ChartContainer.vue";
import ActivityLog from "@/components/ActivityLog.vue";
import DashboardStats from "@/components/DashboardState.vue";
import Sidebar from "@/components/Sidebar.vue";
import axios from "axios";



export default {
  name: 'Home',
  components: {Sidebar, DashboardStats, ActivityLog, ChartContainer},
  setup() {
    const state = reactive({
      buildings: []
    })
    axios.get("/api/buildings").then(({data}) => {
      state.buildings = data
    });
    return {state}
  },

}
</script>

<style scoped>
.main-content {
  margin-left: 320px;
  padding: 20px;
}
</style>