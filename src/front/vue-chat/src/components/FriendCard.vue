<template>
    <div class="friend-card">
        <div class="friend-image-container">
          <img :src=this.imageData alt="ProfileImage" class="friend-image" />
        </div>
        <div class="friend-info">
            <h3>{{ USER_NAME }}</h3>
            <p>{{ COMMENT }}</p>
        </div>
    </div>
</template>

<script>



export default {
  name: 'FriendCard',
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
        type: Blob,
        required: false,
      },
      COMMENT: {
        type: String,
        required: false,
      },

  },
  methods: {
    fetchImage() {
      try {

        // 바이트 데이터를 Base64로 변환
        const base64Image = btoa(
            new Uint8Array(this.IMG_CTT)
                .reduce((data, byte) => data + String.fromCharCode(byte), '')
        );

        // 이미지 데이터를 Base64 형식의 data URL로 저장
        this.imageData = `data:image/jpeg;base64,${base64Image}`;
        console.log("이미지로드완료");
      } catch (error) {
        console.error('이미지 가져오기 실패:', error);
      }
    },
  },
  mounted() {
    this.fetchImage();
  }
};
</script>

<style scoped>
.friend-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f5f5f5;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 10px;
}

.friend-image-container {
  width: 50px;
  height: 50px;
  border-radius: 10%;
  background-color: #999999;
  overflow: hidden;
}

.friend-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.friend-info {
  flex-grow: 1;
  margin-left: 15px;
}

.friend-info h3 {
  margin: 0;
  font-size: 18px;
}

.friend-info p {
  margin: 5px 0 0;
  color: #777;
}
.chat-date {
  white-space: nowrap;
  color: #555;
  font-size: 14px;
}
</style>
