<template>
  <div>
    <Sidebar />
    <div class="main-content">

      <div class="dashboard-container">
        <div class="dashboard-item">
          <h5 class="section-title">선문대학교 점검현황</h5>
          <DashboardStats />
        </div>

        <div class="dashboard-item">
          <h5 class="section-title">사업장 위치</h5>
          <DashboardStats2 />
        </div>
      </div>

      <h5 class="section-title">사업장별 점검 진행현황</h5>
      <Workplace />

      <h5 class="section-title">공지사항</h5>
      <ChartContainer />

    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import DashboardStats from "@/components/DashboardState.vue";
import Sidebar from "@/components/Sidebar.vue";
import axios from "axios";
import ChartContainer from "@/components/ChartContainer.vue";
import Workplace from "@/components/Workplace.vue";
import DashboardStats2 from "@/components/DashboardState2.vue";

export default {
  name: 'Home',
  components: {
    Workplace,
    ChartContainer,
    Sidebar,
    DashboardStats,
    DashboardStats2
  },
  setup() {
    const state = reactive({
      buildings: []
    });
    axios.get("/api/buildings").then(({ data }) => {
      state.buildings = data;
    });
    return { state };
  },
}
</script>

<style scoped>
.main-content {
  margin-left: 250px;
  padding: 20px;
}

.dashboard-container {
  display: flex; /* 가로 배치 */
  justify-content: space-between; /* 아이템 사이 간격 조정 */
  margin-left:0px; /* 왼쪽 여백 추가 */
}

.dashboard-item {
  flex: 1; /* 각 대시보드 아이템이 동일한 너비를 가지도록 설정 */
  margin-right: 20px; /* 오른쪽 아이템과 간격 추가 */
  background-color: #ffffff; /* 배경색 추가 */
  border-radius: 8px; /* 모서리 둥글게 */
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
  padding: 20px; /* 내부 여백 추가 */
}

.dashboard-item:last-child {
  margin-right: 0; /* 마지막 아이템의 오른쪽 여백 제거 */
}

.section-title {
  font-size: 1.5rem; /* 제목 크기 조정 */
  font-weight: bold; /* 제목 두께 조정 */
  color: #2c3e50; /* 제목 색상 조정 */
  margin-bottom: 15px; /* 제목과 다음 요소 간의 여백 추가 */
}
</style>
