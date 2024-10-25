<!-- src/components/ChartContainer.vue -->
<template>
  <div class="chart-container">
    <div class="notice-list">
      <div class="notice-item" v-for="building in state.buildings" :key="building.id">
        <h5 class="card-title">{{ building.buildingName }}</h5>
        <p class="card-text">관리자: {{ building.manager }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";

export default {
  name: 'Workplace',
  setup() {
    const state = reactive({
      buildings: []
    });

    // API 호출하여 건물 데이터 가져오기
    axios.get("/api/buildings")
        .then(({ data }) => {
          state.buildings = data;
        })
        .catch(error => {
          console.error("Error fetching buildings:", error);
        });

    return { state };
  }
};
</script>

<style scoped>
.chart-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* 상단 정렬 */
  align-items: stretch; /* 너비를 100%로 사용 */
  background-color: #f9f9f9;
  margin-bottom: 40px;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow-x: auto; /* 가로 스크롤 추가 */
}

.notice-list {
  display: flex;
  flex-direction: row; /* 가로 방향으로 나열 */
  gap: 10px; /* 카드 간격 추가 */
}

.notice-item {
  min-width: 250px; /* 카드 최소 너비 */
  background-color: white; /* 카드 배경색 */
  border-radius: 5px; /* 카드 모서리 둥글게 */
  padding: 15px; /* 카드 내 여백 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 카드 그림자 */
}

.notice-title {
  font-size: 16px; /* 제목 폰트 크기 */
  font-weight: bold; /* 제목 두껍게 */
  margin: 0; /* 기본 마진 제거 */
}

.notice-meta {
  font-size: 12px; /* 메타 정보 폰트 크기 */
  color: #555; /* 메타 정보 색상 */
  margin-top: 5px; /* 위쪽 여백 */
}

.notice-author,
.notice-time {
  margin-right: 10px; /* 오른쪽 여백 */
}
</style>






