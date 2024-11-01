<template>
  <div class="chart-container">
    <div class="notice-list">
      <div
          class="notice-item"
          v-for="building in state.buildings"
          :key="building.id"
          @click="openModal(building)"
      >
        <div
            class="donut-chart"
            :style="{ background: getDonutGradient(building) }"
        >
          <span class="percentage-text">{{ calculateCheckRate(building) }}%</span>
        </div>
        <h5 class="card-title">{{ building.buildingName }}</h5>
      </div>
    </div>

    <div class="modal-wrap" v-show="state.modalCheck" @click="modalOpen">
      <div class="modal-container" @click.stop>
        <div class="modal-btn">
          <button @click="confirmAction">확인</button>
        </div>
        <div class="modal-header">
          <h3>{{ state.selectedBuilding?.buildingName }} 체크리스트</h3>
          <span class="check-info">
            전체 수: {{ totalAssets }} |
            점검 수: {{ checkedAssets }} |
            미점검 수: {{ uncheckedAssets }} |
            점검율: {{ checkRate }}%
          </span>
        </div>
        <table class="table">
          <thead>
          <tr>
            <th>점검예정일</th>
            <th>점검표명</th>
            <th>점검대상</th>
            <th>점검분야</th>
            <th>점검종류</th>
            <th>점검여부</th>
            <th>점검자</th>
            <th>점검완료일</th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="asset in getAssets(state.selectedBuilding?.buildingName)"
              :key="asset.id"
          >
            <td>{{ asset.c2 }}</td>
            <td>{{ asset.c3 }}</td>
            <td>{{ asset.c4 }}</td>
            <td>{{ asset.c5 }}</td>
            <td>{{ asset.c6 }}</td>
            <td>{{ asset.c7 }}</td>
            <td>{{ asset.c8 }}</td>
            <td>{{ asset.c9 }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";

export default {
  name: "Workplace",
  setup() {
    const state = reactive({
      buildings: [],
      selectedBuilding: null,
      modalCheck: false,
      assets: {},
    });

    const fetchBuildings = async () => {
      try {
        const response = await axios.get("/api/buildings");
        state.buildings = response.data;
        for (const building of state.buildings) {
          const dbName = getDatabaseName(building.buildingName);
          const assetResponse = await axios.get(`/api/${dbName}`);
          state.assets[building.buildingName] = assetResponse.data;
        }
      } catch (error) {
        console.error("Error fetching buildings:", error);
      }
    };

    const getDatabaseName = (buildingName) => {
      switch (buildingName) {
        case "공학관":
          return "gonghakcheck";
        case "보건의료관":
          return "bogeoncheck";
        case "스포츠과학관":
          return "sportscheck";
        case "원화관":
          return "wonhwacheck";
        case "인문관":
          return "inmuncheck";
        case "자연과학관":
          return "jayeoncheck";
        case "본관":
          return "bongwancheck";
        default:
          return "";
      }
    };

    const getAssets = (buildingName) => {
      return state.assets[buildingName] || [];
    };

    const calculateCheckRate = (building) => {
      const assets = getAssets(building.buildingName);
      const total = assets.length;
      const checked = assets.filter((asset) => asset.c7 === "점검").length;
      return total > 0 ? Math.round((checked / total) * 100) : 0;
    };

    const getDonutGradient = (building) => {
      const rate = calculateCheckRate(building);
      return `conic-gradient(#4F98FF 0 ${rate}%, #DEDEDE ${rate}% 100%)`;
    };

    const modalOpen = () => {
      state.modalCheck = !state.modalCheck;
    };

    const openModal = (building) => {
      state.selectedBuilding = building;
      modalOpen();
    };

    const confirmAction = () => {
      modalOpen();
    };

    fetchBuildings();

    return {
      state,
      modalOpen,
      openModal,
      confirmAction,
      getAssets,
      calculateCheckRate,
      getDonutGradient,
    };
  },
};
</script>

<style scoped>
.chart-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: stretch;
  background-color: #f9f9f9;
  margin-bottom: 40px;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow-x: auto;
}

.notice-list {
  display: flex;
  flex-wrap: wrap; /* 여러 줄에 걸쳐 배치 가능 */
  gap: 20px;
}

.notice-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-width: calc(25% - 20px); /* 한 줄에 4개 배치 */
  max-width: calc(33.33% - 20px); /* 한 줄에 3개 배치 */
  background-color: white;
  padding: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: background 0.3s;
}

.donut-chart {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  clip-path: circle(50% at 50% 50%);
  background: #DEDEDE;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  transition: background 0.3s, transform 0.3s; /* 변화를 부드럽게 */
}

.donut-chart:hover {
  background: #4F98FF; /* 호버 시 배경 색상 변경 */
  transform: scale(1.1); /* 약간 확대 효과 추가 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* 호버 시 그림자 강조 */
}

.donut-chart:before {
  content: '';
  position: absolute;
  top: 50%;
  left: 50%;
  width: 60%;
  height: 60%;
  background-color: #fff;
  border-radius: 50%;
  transform: translate(-50%, -50%);
}

.percentage-text {
  position: absolute;
  font-size: 14px;
  color: #333;
}


.modal-wrap {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
}

.modal-container {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 800px;
  max-height: 80%;
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
  overflow-y: auto;
}

.modal-btn {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

.table {
  width: 100%;
  margin-top: 10px;
  border-collapse: collapse;
}

.table th,
.table td {
  padding: 10px;
  border: 1px solid #ddd;
  text-align: left;
}

.table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.table tbody tr:hover {
  background-color: #f5f5f5;
}

.check-info {
  margin-left: 20px;
  font-size: 1em;
  color: #555;
}
</style>
