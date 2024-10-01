<template>
    <div class="message-container" :class="{'own-message': isOwnMessage}">
        <!-- 타인 메시지일 때만 프로필 사진을 표시 -->
        <div v-if="!isOwnMessage" class="profile-picture">
                <img :src="this.fnGetImage(profileImage)" alt="Profile" />
        </div>
    
        <!-- 메시지 본문 -->
        <div class="message-bubble-wrapper">
            <div v-if="!isOwnMessage" class="sender-name">
                <span class="sender-name">{{ sender }}</span>
            </div>
            <div class = "message-bubble">
                <div class="message-content" :class="{'own-message': isOwnMessage}">
                        <p>{{ message }}</p>
                </div>
                <div class="message-time-wrapper">
                    <span class="message-time">{{ formattedTime }}</span>
                </div>

            </div>
        </div>
    </div>
</template>

<script>
import FileUtil from '@/common/util/FileUtil';
import DateUtil from '@/common/util/DateUtil';
export default {
    props: {
        profileImage: {
          type: Object,
        required: false, // 프로필 사진
        },
        sender: {
        type: String,
        required: true, // 보낸 사람의 이름
        },
        message: {
        type: String,
        required: true, // 메시지 내용
        },
        isOwnMessage: {
        type: Boolean,
        default: false, // 자신이 보낸 메시지 여부
        },
        sndDTM: {
        type: String,
        required: true, // 보낸 시각
        },
    },
    methods: {
        fnGetImage(data){
          if(data != null)
            return FileUtil.fnGetProfileImageUrl(data);

        }
    },
    computed: {
        formattedTime() {
            // snd_DTM 값을 '오후 10:30' 형식으로 변환

          if(this.sndDTM != null)
          {
            return DateUtil.fnGetDateString(this.sndDTM);

          }
          else return this.sndDTM;
        },
    }
};
</script>

<style scoped>
.message-container {
display: flex;
flex-direction: row;
align-items: flex-start;
margin-bottom: 15px;
}

/* 자신이 보낸 메시지일 경우 메시지 배치 변경 */
.own-message {
justify-content: flex-end;
}

/* 프로필 사진 스타일 */
.profile-picture {
width: 40px;
height: 40px;
border-radius: 50%;
overflow: hidden;
margin-right: 10px;
}

.profile-picture img {
width: 100%;
height: 100%;
object-fit: cover;
}
.message-bubble-wrapper {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
.own-message .message-bubble-wrapper {
    align-items: flex-end;
}

/* 메시지 말풍선 스타일 */
.message-bubble {
    display: flex;
    flex-direction: row; /* 수평 배치 */
    align-items: flex-end; /* 하단 정렬 */
    max-width: 90%;
    padding: 10px;
    border-radius: 15px;
    word-wrap: break-word;
}

/* 자신이 보낸 메시지일 경우 색상 변경 및 배치 */
.own-message .message-bubble {
    color: white;
}

/* 메시지 헤더(이름) 스타일 */
.message-header {
font-size: 12px;
color: #0c0101;
margin-bottom: 5px;
text-align: left;
}

/* 자신의 메시지일 경우 이름의 위치 변경 */
.own-message .message-header {
text-align: right;
}

/* 메시지 내용 */
.message-content p {
background-color: #56bbee;
margin: 0;
font-size: 14px;
color: #333;
word-wrap: break-word;
border-radius: 10px;
padding: 10px;
max-width: 90%;
position: relative;
}
.own-message.message-content p {
background-color: #d5e964;
}
.sender-name{
    font-size: 11px;
    color: gray;
    margin-left: 10px;
    
}
.message-time {
    font-size: 9px;
    color: gray;
    margin-left: 10px;
}


.own-message .message-time-wrapper {
  justify-content: space-between; /* 자신의 메시지일 때 좌우 끝에 배치 */
}
</style>
