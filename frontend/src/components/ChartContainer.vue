<!-- src/components/ChartContainer.vue -->
<template>
  <div class="chart-container">
    <div class="notice-list">
      <div class="notice-item" v-for="notice in state.notices" :key="notice.id">
        <h6 class="notice-title">{{ notice.title }}</h6>
        <p class="notice-meta">
          <span class="notice-author">{{ notice.name }}</span> |
          <span class="notice-time">{{ notice.time }}</span>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";

export default {
  name: 'ChartContainer',
  setup() {
    const state = reactive({
      notices: []
    });

    // API 호출하여 건물 데이터 가져오기
    axios.get("/api/notices")
        .then(({ data }) => {
          state.notices = data;
        })
        .catch(error => {
          console.error("Error fetching notices:", error);
        });

    return { state };
  }
};
</script>

<style scoped>
.chart-container {
  height: 400px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* 상단 정렬 */
  align-items: stretch; /* 너비를 100%로 사용 */
  background-color: #f9f9f9;
  margin-bottom: 40px;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow-y: auto; /* 세로 스크롤 추가 */
}

.notice-list {
  display: flex;
  flex-direction: column;
  gap: 10px; /* 공지사항 간격 추가 */
}

.notice-item {
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

