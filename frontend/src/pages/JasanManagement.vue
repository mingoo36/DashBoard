<template>
  <div class="Jasan-Management">
    <div class="container">
      <header>
        <h4>자산 관리</h4>
      </header>

      <div v-for="building in state.buildings" :key="building.id" class="building-card">
        <div class="building-title" @click="toggleAssets(building.buildingName)">
          <h5>{{ building.buildingName }} <span class="toggle-icon">{{ isOpen(building.buildingName) ? '-' : '+' }}</span></h5>
        </div>
        <div v-if="isOpen(building.buildingName)">
          <table class="table">
            <thead>
            <tr>
              <th>자산명</th>
              <th>관리번호</th>
              <th>자산분류</th>
              <th>자산중분류</th>
              <th>위험성</th>
              <th>중요도</th>
              <th>메이커</th>
              <th>모델</th>
              <th>시리얼번호</th>
              <th>층</th>
              <th>세부위치</th>
              <th>사업장</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="asset in getAssets(building.buildingName)" :key="asset.id">
              <td>{{ asset.name }}</td>
              <td>{{ asset.code }}</td>
              <td>{{ asset.c3 }}</td>
              <td>{{ asset.c4 }}</td>
              <td>{{ asset.c5 }}</td>
              <td>{{ asset.c6 }}</td>
              <td>{{ asset.c7 }}</td>
              <td>{{ asset.c8 }}</td>
              <td>{{ asset.c9 }}</td>
              <td>{{ asset.c10 }}</td>
              <td>{{ asset.c11 }}</td>
              <td>{{ asset.c12 }}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";

export default {
  name: 'JasanManagement',
  setup() {
    const state = reactive({
      buildings: [],
      assets: {},
      openBuildings: new Set() // 열려 있는 건물 이름 저장
    });

    const fetchBuildingsAndAssets = async () => {
      try {
        const buildingResponse = await axios.get("/api/buildings");
        state.buildings = buildingResponse.data;

        for (const building of state.buildings) {
          const dbName = getDatabaseName(building.buildingName);
          const assetResponse = await axios.get(`/api/${dbName}`);
          state.assets[building.buildingName] = assetResponse.data;
        }
      } catch (error) {
        console.error("Error fetching buildings and assets:", error);
      }
    };

    const getDatabaseName = (buildingName) => {
      switch (buildingName) {
        case '공학관': return 'gonghak';
        case '보건의료관': return 'bogeon';
        case '스포츠과학관': return 'sports';
        case '원화관': return 'wonhwa';
        case '인문관': return 'inmun';
        case '자연과학관': return 'jayeon';
        case '본관': return 'bongwan';
        default: return '';
      }
    };

    const getAssets = (buildingName) => {
      return state.assets[buildingName] || [];
    };

    // 자산 목록 열고 닫기 핸들러
    const toggleAssets = (buildingName) => {
      if (state.openBuildings.has(buildingName)) {
        state.openBuildings.delete(buildingName);
      } else {
        state.openBuildings.add(buildingName);
      }
    };

    // 건물이 열려 있는지 확인
    const isOpen = (buildingName) => {
      return state.openBuildings.has(buildingName);
    };

    fetchBuildingsAndAssets();

    return { state, getAssets, toggleAssets, isOpen };
  }
};
</script>

<style scoped>
.Jasan-Management {
  padding: 20px;
  margin-left: 320px; /* 사이드바 너비만큼 마진 추가 */
}

.container {
  margin-top: 20px; /* Add some space between the sidebar and the content */
}

.building-card {
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-bottom: 20px; /* 각 카드 사이의 공간 */
  padding: 10px;
  background-color: #fff; /* 카드 배경색 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 카드 그림자 */
}

.building-title {
  cursor: pointer; /* 클릭 가능한 커서 */ /* 제목 색상 */
  margin: 10px 0; /* 제목 상하 마진 */
}

.toggle-icon {
  margin-left: 5px; /* 아이콘과 제목 간격 */
}

.table {
  width: 100%;
  margin-top: 10px;
  border-collapse: collapse; /* 테이블 경계선 겹침 방지 */
}

.table th, .table td {
  padding: 10px;
  border: 1px solid #ddd;
  text-align: left; /* 텍스트 정렬 왼쪽 */
}

.table th {
  background-color: #f2f2f2; /* 헤더 배경색 */
  font-weight: bold; /* 헤더 텍스트 굵게 */
}

.table tbody tr:hover {
  background-color: #f5f5f5; /* 행에 마우스 오버 시 배경색 변경 */
}
</style>
