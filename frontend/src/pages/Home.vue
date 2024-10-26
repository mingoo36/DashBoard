<template>
  <div>
    <Sidebar />
    <div class="main-content">
      <h3>중대재해처벌법대응 운영솔루션</h3>

      <h5 class="section-title">전체 점검현황</h5>
      <DashboardStats />

      <h5 class="section-title">사업장 점검현황</h5>
      <Workplace />


      <h5 class="section-title">공지사항</h5>
      <ChartContainer />
      <!-- 통계 카드 섹션 -->
      <ActivityLog />


    </div>
  </div>
</template>

<script>
import {reactive} from "vue";

import DashboardStats from "@/components/DashboardState.vue";
import Sidebar from "@/components/Sidebar.vue";
import axios from "axios";
import ActivityLog from "@/components/ActivityLog.vue";
import ChartContainer from "@/components/ChartContainer.vue";
import Workplace from "@/components/Workplace.vue";




export default {
  name: 'Home',
  components: {Workplace, ChartContainer, ActivityLog, Sidebar, DashboardStats},
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
.section-title {
  font-size: 1.5rem; /* 제목 크기 조정 */
  font-weight: bold; /* 제목 두께 조정 */
  color: #2c3e50; /* 제목 색상 조정 */
  margin-bottom: 15px; /* 제목과 다음 요소 간의 여백 추가 */
}

</style>