<template>
  <div class="dashboard-stats">
    <div class="card card-background">
      <div class="card-content">
        <div class="stat-item">
          <h3>{{ total }}</h3>
          <p>Total</p>
        </div>
        <div class="stat-item">
          <h3>{{ checked }}</h3>
          <p>점검</p>
        </div>
        <div class="stat-item">
          <h3>{{ unchecked }}</h3>
          <p>미점검</p>
        </div>
        <div class="stat-item">
          <h3>{{ checkRate }}%</h3>
          <p>점검률(%)</p>
        </div>
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
        return acc + assets.filter(asset => asset.c7 === "점검").length;
      }, 0);
    });

    const unchecked = computed(() => total.value - checked.value);

    const checkRate = computed(() => {
      const totalAssets = total.value;
      return totalAssets > 0 ? Math.round((checked.value / totalAssets) * 100) : 0;
    });

    onMounted(() => {
      fetchBuildings();
    });

    return {
      total,
      checked,
      unchecked,
      checkRate,
    };
  },
};
</script>

<style scoped>
.dashboard-stats {
  display: flex;
  justify-content: space-between;
  gap: 20px;
  margin-bottom: 40px;
}

.card {
  flex: 1;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.card-background {
  background-color: #2c3e50; /* 사이드바 색상 */
}

.card:hover {
  transform: translateY(-5px);
}

.card-content {
  display: flex;
  justify-content: space-around; /* 항목 간격 조정 */
}

.stat-item {
  text-align: center; /* 항목 텍스트 중앙 정렬 */
  color: white;
}

.stat-item h3 {
  margin: 0; /* 제목 여백 제거 */
  font-size: 2rem; /* 제목 크기 조정 */
}

.stat-item p {
  margin: 0; /* 텍스트 여백 제거 */
  font-size: 1rem; /* 텍스트 크기 조정 */
}
</style>




