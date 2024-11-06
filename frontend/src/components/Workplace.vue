<template>
  <div>
    <canvas id="buildingChart" style="width: 80%; height: 300px;"></canvas>

    <div class="modal-wrap" v-show="state.modalCheck" @click="modalOpen">
      <div class="modal-container" @click.stop>
        <div class="modal-header">
          <h3 class="modal-title">{{ state.selectedBuilding?.buildingName }} 체크리스트</h3>
          <button class="modal-close" @click="modalOpen">✖</button>
        </div>
        <div class="modal-body">
          <span class="check-info" v-if="state.selectedBuilding">
            전체 수: {{ calculateAssetsData(state.selectedBuilding)?.totalAssets }} |
            점검 수: {{ calculateAssetsData(state.selectedBuilding)?.checkedAssets }} |
            미점검 수: {{ calculateAssetsData(state.selectedBuilding)?.uncheckedAssets }} |
            점검율: {{ calculateAssetsData(state.selectedBuilding)?.checkRate }}%
          </span>

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
            <tr v-for="asset in getAssets(state.selectedBuilding?.buildingName)" :key="asset.id">
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
  </div>
</template>

<script>
import { reactive, onMounted } from "vue";
import axios from "axios";
import Chart from "chart.js/auto";

export default {
  name: "Workplace",
  setup() {
    const state = reactive({
      buildings: [],
      selectedBuilding: null,
      modalCheck: false,
      assets: {},
    });

    let chart = null; // Chart.js 인스턴스를 저장할 변수

    const fetchBuildings = async () => {
      try {
        const response = await axios.get("/api/buildings");
        state.buildings = response.data;
        for (const building of state.buildings) {
          const dbName = getDatabaseName(building.buildingName);
          const assetResponse = await axios.get(`/api/${dbName}`);
          state.assets[building.buildingName] = assetResponse.data;
        }
        createChart();
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

    const calculateAssetsData = (building) => {
      if (!building) return null; // building이 null일 경우 null 반환
      const assets = getAssets(building.buildingName);
      const total = assets.length;
      const checked = assets.filter((asset) => asset.c7 === "점검").length;
      const unchecked = total - checked;
      const checkRate = total > 0 ? Math.round((checked / total) * 100) : 0;

      return {
        totalAssets: total,
        checkedAssets: checked,
        uncheckedAssets: unchecked,
        checkRate: checkRate,
      };
    };

    const createChart = () => {
      const ctx = document.getElementById("buildingChart").getContext("2d");
      const labels = state.buildings.map(b => b.buildingName);
      const data = state.buildings.map((building) => {
        const { checkedAssets, totalAssets } = calculateAssetsData(building);
        return totalAssets > 0 ? (checkedAssets / totalAssets) * 100 : 0;
      });

      if (chart) {
        chart.destroy(); // 기존 차트를 파괴하여 메모리 누수 방지
      }

      chart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [{
            label: '% 점검',
            data: data,
            backgroundColor: 'rgba(79, 152, 255, 0.6)',
            borderColor: 'rgba(79, 152, 255, 1)',
            borderWidth: 1
          }]
        },
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true,
              title: {
                display: true,
                text: '점검률 (%)'
              }
            },
            x: {
              title: {
                display: true,
              }
            }
          },
          onClick: (e) => {
            const activePoints = chart.getElementsAtEventForMode(e, 'nearest', { intersect: true }, true);
            if (activePoints.length > 0) {
              const { index } = activePoints[0];
              openModal(state.buildings[index]);
            }
          }
        }
      });
    };

    const modalOpen = () => {
      state.modalCheck = !state.modalCheck;
    };

    const openModal = (building) => {
      state.selectedBuilding = building;
      modalOpen();
    };

    onMounted(() => {
      fetchBuildings();
    });

    return {
      state,
      modalOpen,
      openModal,
      getAssets,
      calculateAssetsData,
    };
  },
};
</script>

<style scoped>
.modal-wrap {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-container {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 60%; /* 너비 줄임 */
  max-width: 700px; /* 최대 너비 조정 */
  max-height: 80vh; /* 최대 높이 설정 */
  overflow-y: auto; /* 세로 방향으로 스크롤 가능 */
  box-shadow: 0 2px 15px rgba(0, 0, 0, 0.5); /* 그림자 효과 */
}

.modal-header {
  display: flex;
  justify-content: center; /* 중앙 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
  margin-bottom: 20px;
  position: relative; /* 상대 위치 지정 */
}

.modal-close {
  position: absolute; /* 절대 위치 지정 */
  right: 10px; /* 오른쪽 여백 */
  top: 10px; /* 위쪽 여백 */
  background: transparent;
  border: none;
  font-size: 24px;
  cursor: pointer;
}

.modal-title {
  font-size: 24px;
  font-weight: bold;
  text-align: center; /* 중앙 정렬 */
  margin: 0; /* 여백 제거 */
}

.modal-body {
  margin-bottom: 20px;
}

.check-info {
  margin-bottom: 15px;
  font-size: 18px;
  color: #555;
  text-align: center; /* 가운데 정렬 */
}

.table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

.table th,
.table td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: left;
}

.table th {
  background-color: #f7f7f7; /* 헤더 배경색 */
}

.table tr:nth-child(even) {
  background-color: #f9f9f9; /* 홀수 행 배경색 */
}

.table tr:hover {
  background-color: #f1f1f1; /* 마우스 오버 시 효과 */
}
</style>
