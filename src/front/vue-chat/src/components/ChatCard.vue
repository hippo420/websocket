<template>
    <div class="chat-card">
        <div class="chat-image-container">
            <img :src="imageData" alt="Chat Room Image" class="chat-image" />
        </div>
        <div class="chat-info">
            <h3>{{ ROOM_NAME }}<span class="join-count">{{ JOIN_CNT }}</span></h3>
            <p>{{ MESSAGE }}</p>
        </div>
        <div class="chat-date">
            <span>{{ this.fetchDTM(SND_DTM) }}</span>
        </div>
    </div>
</template>

<script>
import DateUtil from '@/common/util/DateUtil';
export default {
  name: 'ChatCard',
  data() {
    return {
      imageData: '', // Base64 이미지 데이터
    };
  },
  props: {
      ROOM_NAME: {
        type: String,
        required: true,
      },
      MESSAGE: {
        type: String,
        required: true,
      },
      SND_DTM: {
        type: String,
        required: true,
      },
      IMG_CTT: {
        type: Object,
        required: false,
      },
      ROOM_TYCD: {
        type: String,
        required: true,
      },
      JOIN_CNT: {
        type: String,
        required: true,
      },
  },
  methods: {
    fetchImage() {
      if (this.IMG_CTT) {
        const byteCharacters = new Uint8Array(this.IMG_CTT);
        let binaryString = '';
        byteCharacters.forEach((byte) => {
          binaryString += String.fromCharCode(byte);
        });

        this.imageData = `data:;base64,${binaryString}`;
      } else {
        // 기본 이미지 설정 (이미지 없을 때)
        this.imageData = 'DefaultChat.png'; // 기본 이미지 경로
      }
    },
    fetchDTM(time){
      DateUtil.fnGetDateString(time);
    }
  },
  mounted() {
    this.fetchImage();
    this.fetchDTM();
  },
};
</script>

<style scoped>
.chat-card {
display: flex;
justify-content: space-between;
align-items: center;
background-color: #f5f5f5;
border: 1px solid #ddd;
border-radius: 8px;
padding: 15px;
margin-bottom: 10px;
}

.chat-image-container {
width: 50px;
height: 50px;
border-radius: 10%;
overflow: hidden;
}

.chat-image {
width: 100%;
height: 100%;
object-fit: cover;
}

.chat-info {
flex-grow: 1;
margin-left: 15px;
}

.chat-info h3 {
  text-align: left;
  margin: 0;
  font-size: 12px;
}

.chat-info p {
  text-align: left;
  margin: 5px 0 0;
  font-size: 8px;
  color: #777;
}

.chat-date {
white-space: nowrap;
color: #555;
font-size: 10px;
}
.join-count {
  font-size: 11px; /* 친구 수 작은 글씨 크기 */
  margin-left: 5px; /* 친구 수와 제목 간격 조정 */
  color: #777; /* 친구 수 색상 */
  align-self: flex-end; /* 친구 수를 아래로 맞춤 */
}
</style>
