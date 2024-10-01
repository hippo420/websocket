<template>
  <div class="friend-card">
    <div class="friend-image-container">
      <img :src="imageData" class="friend-image" />
    </div>
    <div class="friend-info">
      <h3>{{ USER_NAME }}</h3>
      <p>{{ COMMENT }}</p>
    </div>
  </div>
</template>

<script>

import FileUtil from '@/common/util/FileUtil';
export default {
  name: 'ProfileCard',
  data() {
    return {
      imageData: '', // Base64 이미지 데이터
    };
  },
  props: {
    USER_NAME: {
      type: String,
      required: true,
    },
    IMG_CTT: {
      type: Object,
      required: false,
    },
    COMMENT: {
      type: String,
      required: false,
    },
  },
  methods: {
    fetchImage() {
      if (this.IMG_CTT) {
        this.imageData = FileUtil.fnGetProfileImageUrl(this.IMG_CTT)

      } else {
        // 기본 이미지 설정 (이미지 없을 때)
        this.imageData = 'default.png'; // 기본 이미지 경로
      }
    },
  },
  mounted() {
    this.fetchImage();
  },
};
</script>

<style scoped>
.friend-card {
  display: flex; /* Flexbox 사용 */
  align-items: center; /* 수직 중앙 정렬 */
  background-color: #f8f8fb; /* 배경 색상 */
  border: 1px solid #ddd; /* 경계선 */
  border-radius: 8px; /* 모서리 둥글게 */
  padding: 15px; /* 패딩 */
  margin-bottom: 10px; /* 카드 간격 */
}

.friend-image-container {
  width: 50px; /* 이미지 컨테이너 너비 */
  height: 50px; /* 이미지 컨테이너 높이 */
  border-radius: 2%; /* 이미지 모서리 둥글게 */
  background-color: #FFFFFF; /* 기본 배경 색상 */
  overflow: hidden; /* 이미지가 넘치지 않도록 */
}

.friend-image {
  width: 100%; /* 이미지 너비 */
  height: 100%; /* 이미지 높이 */
  object-fit: cover; /* 이미지 비율 유지 */
}

.friend-info {
  flex-grow: 1; /* 남은 공간 차지 */
  margin-left: 10px; /* 텍스트와 이미지 간격 */
}

.friend-info h3 {
  text-align: left;
  margin: 0; /* 제목 마진 제거 */
  font-size: 12px; /* 제목 크기 */
  color: #0c0101; /* 제목 색상 */
}

.friend-info p {
  text-align: left;
  font-size: 10px;
  margin: 5px 0 0; /* 설명 마진 설정 */
  color: #0c0101; /* 설명 색상 */
}
</style>
