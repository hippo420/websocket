<template>
    <div class="chat-list-container">
      <h2 class="chat-title">채팅 </h2>
        <ChatCard
            v-for="chat in chatList"
            :key="chat.ROOM_ID"
            :ROOM_NAME="chat.ROOM_NAME"
            :MESSAGE="chat.MESSAGE"
            :SND_DTM="chat.SND_DTM"
            :IMG_CTT="chat.IMG_CTT"
            :ROOM_TYCD="chat.ROOM_TYCD"
            :JOIN_CNT="chat.JOIN_CNT"
            @dblclick="openChat(chat.ROOM_ID)"
        />
    </div>
</template>

<script>
import Transaction from '@/common/core/Transaction';
import ChatCard from '../components/ChatCard.vue';
export default {
    components: {
        ChatCard,
    },
    data() {
        return {
            chatList: [
                
            ],
        };
    },
    methods: {
        openChat(chatId) {
            this.$router.push(`/chat/${chatId}`);
        },
        loadChatList(){
            let data ={
              USER_ID : this.$store.state.userData.USER_ID,
            };
            
            Transaction.gfnTrx("/findBangByUserId","GET",data ,this.fnChatListCallback);
        },
        fnChatListCallback(data) {
            console.log(data);
            this.chatList = [...data];
        }
    },
    mounted(){
        this.loadChatList();
    }
};
</script>

<style scoped>
.chat-title {
  text-align: left; /* 제목 왼쪽 정렬 */
  font-size: 24px; /* 제목 크기 조정 */
  margin-bottom: 10px; /* 제목과 친구 카드 사이 여백 */
  display: flex; /* 플렉스 박스 사용 */
  align-items: flex-end; /* 맨 아래로 정렬 */
  border-bottom: 1px solid lightgray; /* 밑줄 색상과 두께 */
  padding-bottom: 5px;          /* 텍스트와 밑줄 사이 간격 */
}
.chat-list-container {
padding: 20px;
background-color: #FFFFFF;
}
</style>
