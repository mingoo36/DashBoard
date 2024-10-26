<template>
  <div class="chart-container">
    <div class="notice-list">
      <div class="notice-item"
           v-for="building in state.buildings"
           :key="building.id"
           @click="openModal(building)"
           :style="{ background: getCardBackgroundColor(building) }"> <!-- 배경 색 적용 -->
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
            <th>점검여부</th> <!-- 점검여부로 변경 -->
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
            <td>{{ asset.c7 }}</td> <!-- 점검여부 표시 -->
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
import { reactive, computed } from "vue";
import axios from "axios";

export default {
  name: 'Workplace',
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
        case '공학관': return 'gonghakcheck';
        case '보건의료관': return 'bogeoncheck';
        case '스포츠과학관': return 'sportscheck';
        case '원화관': return 'wonhwacheck';
        case '인문관': return 'inmuncheck';
        case '자연과학관': return 'jayeoncheck';
        case '본관': return 'bongwancheck';
        default: return '';
      }
    };

    const getAssets = (buildingName) => {
      return state.assets[buildingName] || [];
    };

    const totalAssets = computed(() => {
      return getAssets(state.selectedBuilding?.buildingName).length;
    });

    const checkedAssets = computed(() => {
      return getAssets(state.selectedBuilding?.buildingName).filter(asset => asset.c7 === '점검').length;
    });

    const uncheckedAssets = computed(() => {
      return totalAssets.value - checkedAssets.value; // 전체 수 - 점검 수
    });

    const checkRate = computed(() => {
      if (totalAssets.value > 0) {
        return ((checkedAssets.value / totalAssets.value) * 100).toFixed(2);
      }
      return 0;
    });

    const getCardBackgroundColor = (building) => {
      const rate = calculateCheckRate(building);
      const color = rate >= 50 ? '#4caf50' : '#ffeb3b'; // 점검률에 따라 색상 결정
      return `linear-gradient(to right, ${color} ${rate}%, #ddd ${rate}%)`; // 점검률에 따라 색상 비율 조정
    };


    const calculateCheckRate = (building) => {
      const assets = getAssets(building.buildingName);
      const total = assets.length;
      const checked = assets.filter(asset => asset.c7 === '점검').length; // c7: 점검 여부
      return total > 0 ? (checked / total) * 100 : 0; // 점검률 계산
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

    return { state, modalOpen, openModal, confirmAction, getAssets, totalAssets, checkedAssets, uncheckedAssets, checkRate, getCardBackgroundColor };
  }
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
  flex-direction: row;
  gap: 10px;
}

.notice-item {
  min-width: 250px;
  background-color: white;
  border-radius: 5px;
  padding: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: background 0.3s;
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

.table th, .table td {
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
  font-size: 1em; /* 글씨 크기를 키우기 위해 1.2em으로 변경 */
  color: #555;
}

</style>
