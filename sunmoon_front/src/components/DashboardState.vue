<!-- src/components/DashboardStats.vue -->
<template>
  <div class="dashboard-stats">
    <!-- 방문자 수 카드 -->
    <div class="card teal lighten-2 white-text">
      <div class="card-content">
        <span class="card-title">content1</span>

      </div>
    </div>

    <!-- 수익 카드 -->
    <div class="card green lighten-2 white-text">
      <div class="card-content">
        <span class="card-title">content2</span>

      </div>
    </div>

    <!-- 팔로워 카드 -->
    <div class="card blue lighten-2 white-text">
      <div class="card-content">
        <span class="card-title">content3</span>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DashboardStats'
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
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.card:hover {
  transform: translateY(-5px);
}

.card-content {
  padding: 20px;
}
</style>
