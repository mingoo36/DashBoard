<template>
  <div class="basic-info">
    <div class="container">
      <header>
        <h4>기본 정보</h4>
      </header>
      <div class="row justify-content-center"> <!-- 가운데 정렬 -->
        <div class="col-md-8"> <!-- 컨텐츠의 너비 조정 -->
          <div class="card shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
              <title>Placeholder</title>
              <rect width="100%" height="100%" fill="#55595c"/>
              <text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
            </svg>
            <div class="card-body">
              <p class="card-text">이것은 아래에 지원 텍스트가 있는 더 넓은 카드입니다. 이 컨텐츠는 조금 더 깁니다.</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">보기</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">편집</button>
                </div>
                <small class="text-body-secondary">9분 전</small>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BasicInfo',
};
</script>

<style scoped>
.basic-info {
  padding: 20px;
  margin-left: 320px; /* 사이드바 너비만큼 마진 추가 */
}

.container {
  margin-top: 20px;
}
</style>
