<template>
  <div class="basic-info">
    <div class="container">
      <header>
        <h4 class="header-title">공지사항</h4>
      </header>
      <div class="notice-list">
        <div v-for="notice in state.notices" :key="notice.id" class="notice-item">
          <div class="notice-header">
            <h5 class="notice-title">{{ notice.title }}</h5>
            <div class="notice-meta">
              <span class="notice-author">{{ notice.name }}</span> |
              <span class="notice-time">{{ notice.time }}</span>
            </div>
          </div>
          <div class="text-end">
            <button type="button" class="btn btn-primary btn-sm">자세히 보기</button>
          </div>
          <hr />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";

export default {
  name: 'BasicInfo',
  setup() {
    const state = reactive({
      notices: []
    });

    // API 호출하여 공지사항 데이터 가져오기
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
.basic-info {
  padding: 20px;
  margin-left: 320px; /* 사이드바 너비만큼 마진 추가 */
}

.container {
  margin-top: 20px;
}

.header-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.notice-list {
  margin-top: 20px;
}

.notice-item {
  margin-bottom: 15px;
  padding: 15px;
  border: 1px solid #e0e0e0;
  border-radius: 5px; /* 약간의 둥근 모서리 */
  background-color: #ffffff; /* 배경색 */
  transition: box-shadow 0.3s ease; /* 호버 시 그림자 효과 */
}

.notice-item:hover {
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* 호버 시 그림자 효과 */
}

.notice-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.notice-title {
  font-weight: bold;
  font-size: 1.2em;
  margin: 0;
}

.notice-meta {
  font-size: 0.9em;
  color: #888;
}

.notice-content {
  margin-top: 10px;
  line-height: 1.5; /* 줄 간격 조정 */
}

.text-end {
  text-align: right;
}
</style>


