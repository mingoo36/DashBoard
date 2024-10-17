<template>
  <div class="dashboard-stats">
    <div class="card card-background">
      <div class="card-content">
        <div class="stat-item">
          <h3>2</h3>
          <p>Total</p>
        </div>
        <div class="stat-item">
          <h3>0</h3>
          <p>Check</p>
        </div>
        <div class="stat-item">
          <h3>2</h3>
          <p>Not checked</p>
        </div>
        <div class="stat-item">
          <h3>0%</h3>
          <p>Check Rate(%)</p>
        </div>
      </div>
    </div>
  </div>
</template>

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


