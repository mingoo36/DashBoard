<template>
  <div class="dashboard-stats">
    <div class="stats-container">
      <div class="legend">
        <div class="legend-item">
          <span class="legend-label">전체:</span>
          <span class="legend-value">{{ total }}</span>
        </div>
        <div class="legend-item">
          <span class="legend-label">점검:</span>
          <span class="legend-value">{{ unchecked }}</span>
        </div>
        <div class="legend-item">
          <span class="legend-label">미점검:</span>
          <span class="legend-value">{{ checked }}</span>
        </div>
      </div>
      <div class="donut-chart" :style="{ background: getDonutGradient(checkRate) }">
        <span class="percentage-text">{{ checkRate }}%</span>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed, onMounted } from "vue";
import axios from "axios";

export default {
  name: "DashboardStats",
  setup() {
    const state = reactive({
      buildings: [],
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

    const total = computed(() => {
      return Object.values(state.assets).reduce((acc, assets) => acc + assets.length, 0);
    });

    const checked = computed(() => {
      return Object.values(state.assets).reduce((acc, assets) => {
        return acc + assets.filter(asset => asset.c7 === "미점검").length+1;
      }, 0);
    });

    const unchecked = computed(() => total.value - checked.value);

    const checkRate = computed(() => {
      const totalAssets = total.value;
      return totalAssets > 0 ? Math.round((checked.value / totalAssets) * 100) : 0;
    });

    const getDonutGradient = (rate) => {
      return `conic-gradient(#fdbcb4 0 ${rate}%, #DEDEDE ${rate}% 100%)`;
    };

    onMounted(() => {
      fetchBuildings();
    });

    return {
      total,
      checked,
      unchecked,
      checkRate,
      getDonutGradient,
    };
  },
};
</script>

<style scoped>
.dashboard-stats {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 40px;
}

.stats-container {
  display: flex; /* 수평 배치 */
  align-items: center; /* 세로 중앙 정렬 */
}

.donut-chart {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  clip-path: circle(50% at 50% 50%);
  background: #DEDEDE;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  transition: transform 0.2s, box-shadow 0.2s; /* 부드러운 전환 효과 추가 */
}

.donut-chart:hover {
  transform: scale(1.05); /* 호버 시 약간 확대 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* 호버 시 그림자 강화 */
}

.donut-chart:before {
  content: '';
  position: absolute; /* 절대 위치 설정 */
  top: 50%;
  left: 50%;
  width: 60%; /* 구멍의 크기 */
  height: 60%; /* 구멍의 크기 */
  background-color: #fff; /* 구멍의 배경색 */
  border-radius: 50%; /* 구멍도 원형으로 */
  transform: translate(-50%, -50%); /* 중앙 정렬 */
}


.percentage-text {
  position: absolute;
  font-size: 24px;
  color: #333;
}

.legend {
  margin-right: 20px; /* 차트와 범례 간격 조정 */
  text-align: right; /* 범례 텍스트 오른쪽 정렬 */
}

.legend-item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px; /* 범례 항목 간격 조정 */
}

.legend-label {
  font-size: 18px; /* 범례 레이블 글씨 크기 */
  color: #333;
}

.legend-value {
  font-size: 18px; /* 범례 값 글씨 크기 */
  color: #333;
}
</style>






