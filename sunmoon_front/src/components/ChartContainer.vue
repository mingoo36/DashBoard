<!-- src/components/ChartContainer.vue -->
<template>
  <div class="chart-container">
    <h5>그래프 통계</h5>
    <p>그래프가 여기에 표시됩니다.</p>
    <!-- 실제 그래프를 표시하려면 Chart.js나 다른 라이브러리를 사용할 수 있습니다 -->
  </div>
</template>

<script>
export default {
  name: 'ChartContainer'
};
</script>

<style scoped>
.chart-container {
  height: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #f9f9f9;
  margin-bottom: 40px;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>
