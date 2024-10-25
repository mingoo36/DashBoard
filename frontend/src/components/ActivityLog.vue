<!-- src/components/ActivityLog.vue -->
<template>
  <div class="activity-log">
    <h5>최근 활동</h5>
    <ul>
      <li>사용자 A가 새로운 게시물을 작성했습니다.</li>
      <li>사용자 B가 댓글을 남겼습니다.</li>
      <li>사용자 C가 프로필을 업데이트했습니다.</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'ActivityLog'
};
</script>

<style scoped>
.activity-log {
  margin-bottom: 40px;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: white;
}

.activity-log ul {
  list-style: none;
  padding: 0;
}

.activity-log li {
  padding: 10px;
  margin-bottom: 10px;
  background-color: #f4f4f4;
  border-radius: 4px;
}
</style>
