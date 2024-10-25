<template>
  <div class="facility-management">
    <div class="container">
      <header>
        <h4>사업장 관리</h4>
      </header>
      <div class="row">
        <!-- 건물 카드 목록 -->
        <div class="col-md-6 mb-3" v-for="building in state.buildings" :key="building.id"> <!-- 여백 추가 -->
          <div class="card shadow-sm" style="width: 100%;"> <!-- 너비 1.5배 조정 -->
            <svg class="bd-placeholder-img card-img-top" width="100%" height="200" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
              <title>{{ building.buildingName }}</title>
              <rect width="100%" height="100%" fill="#55595c"/>
              <text x="50%" y="50%" fill="#eceeef" dy=".3em">{{ building.buildingName }}</text>
            </svg>
            <div class="card-body">
              <h5 class="card-title">{{ building.buildingName }}</h5>
              <p class="card-text">관리자: {{ building.manager }}</p>
              <p class="card-text">위치: {{ building.location }}</p>
              <p class="card-text">전화번호: {{ building.number }}</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div>
                <small class="text-body-secondary">9 mins</small>
              </div>
            </div>
          </div>
        </div>
        <!-- 추가 카드가 필요할 경우 이곳에 더 추가 -->
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";

export default {
  name: 'FacilityManagement',
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
.facility-management {
  padding: 20px;
  margin-left: 320px; /* 사이드바 너비만큼 마진 추가 */
}

.container {
  margin-top: 20px; /* Add some space between the sidebar and the content */
}
</style>


