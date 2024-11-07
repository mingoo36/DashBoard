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
          <div class="check-info" v-if="state.selectedBuilding">
            전체 수: {{ calculateAssetsData(state.selectedBuilding)?.totalAssets }} |
            점검 수: {{ calculateAssetsData(state.selectedBuilding)?.checkedAssets }} |
            미점검 수: {{ calculateAssetsData(state.selectedBuilding)?.uncheckedAssets }} |
            점검율: {{ calculateAssetsData(state.selectedBuilding)?.checkRate }}%
          </div>

          <div class="asset-info">
            <div class="asset-item" v-for="asset in getAssets(state.selectedBuilding?.buildingName)" :key="asset.id">
              <div class="checklist">
                <input type="checkbox" v-model="asset.checked" />
                <label>{{ asset.c3 }} - 점검예정일: {{ asset.c2 }}</label>
              </div>
              <div>점검분야: {{ asset.c5 }}</div>
              <div>점검종류: {{ asset.c6 }}</div>
              <!-- 점검여부에 따른 색상 변경 -->
              <div :style="getCheckStatusStyle(asset.c7)">점검여부: {{ asset.c7 }}</div>
            </div>
          </div>
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
          plugins: {
            legend: {
              display: false // 범례를 아예 숨깁니다.
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

    // 점검 여부에 따라 스타일을 반환하는 메서드 추가
    const getCheckStatusStyle = (status) => {
      return status === "점검"
          ? { color: 'blue', fontWeight: 'bold' }
          : { color: 'red', fontWeight: 'bold' };
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
      getCheckStatusStyle, // 메서드 반환
    };
  },
};
</script>

<style scoped>
/* 스타일 수정 없이 유지 */
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
  width: 70%; /* 너비 줄임 */
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
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.checklist {
  display: flex;
  align-items: center;
  gap: 10px;
  background-color: #f7f7f7;
  border-radius: 8px;
  padding: 10px;
}

.checklist input {
  width: 20px;
  height: 20px;
}

.checklist label {
  font-size: 16px;
  color: #333;
}

.asset-item {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 10px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.asset-item .checklist {
  background-color: #e8f0fe;
}

.check-info {
  display: flex;             /* 플렉스 박스 사용 */
  justify-content: center;   /* 수평 중앙 정렬 */
  align-items: center;       /* 수직 중앙 정렬 */
  margin-bottom: 15px;
  font-size: 18px;
  color: #555;
  text-align: center;        /* 텍스트 중앙 정렬 */
}


.asset-info {
  font-size: 14px;
}
</style>

