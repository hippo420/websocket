<template>
  <header class="main-header">
    <!-- 상단 헤더 구현 -->
    <button @click="goBack" class="back-button">
      <font-awesome-icon icon="angle-left" />
    </button>
    <h2 class="room-name">{{ ROOM_NAME }}<span class="join-count">{{ JOIN_CNT }}</span></h2>
    <button @click="toggleSlideMenu" class="menu-button">
      <font-awesome-icon icon="bars" />
    </button>
  </header>
    <div class="chat-room-container">


      <!-- 채팅 메시지 목록 -->
      <main class="messages">
      <BubbleMessage
          v-for="(msg, index) in messages"
          :key="index"
          :profileImage="msg.IMG_CTT"
          :sender="msg.SND_ID"
          :message="msg.MESSAGE"
          :isOwnMessage="isOwnMessage(msg.SND_ID)"
          :sndDTM="msg.SND_DTM"
      />
      </main>

      <footer class="message-input">

          <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" />
          <button @click="sendMessage">
              <font-awesome-icon icon="paper-plane" />
          </button>
      </footer>
    </div>
    <!-- 슬라이드 메뉴 -->
    <aside v-if="isSlideMenuOpen" class="slide-menu">
      <!-- 메뉴 내용 -->
      <ul>
        <li><a href="#">Option 1</a></li>
        <li><a href="#">Option 2</a></li>
        <li><a href="#">Option 3</a></li>
      </ul>
    </aside>
</template>

<script>
import Transaction from '@/common/core/Transaction';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { mapGetters } from 'vuex';
import BubbleMessage from '../components/BubbleMessage.vue';
export default {
name: 'ChatRoom',
    components:{
        BubbleMessage,
    },
    data() {
        return {
            stompClient: null, // WebSocket 연결 객체
            ROOM_ID: this.$route.params.id, // 라우트 파라미터에서 채팅방 ID 가져옴
            ROOM_NAME:  this.$route.query.ROOM_NAME,
            JOIN_CNT :this.$route.query.JOIN_CNT,
            messages: [], // 채팅 메시지 배열
            newMessage: '', // 입력 중인 새 메시지
            isSlideMenuOpen : false
        };
    },
    methods: {
        connect() {
            // STOMP 클라이언트 설정
            const socket = new SockJS('http://localhost:8081/ws-stomp'); // 서버 WebSocket 엔드포인트
            this.stompClient = new Client({
                webSocketFactory: () => socket,
                reconnectDelay: 5000, // 재연결 설정
                heartbeatIncoming: 4000,
                heartbeatOutgoing: 4000,
            });

            this.stompClient.onConnect = (frame) => {
                console.log('Connected: ' + frame);

                // 채팅 메시지를 받을 구독 설정
                this.stompClient.subscribe(`/topic/messages/${this.ROOM_ID}`, (message) => {
                    const msg = JSON.parse(message.body);
                    console.log('받은 메시지: ',msg);
                    this.messages.push(msg);
                });
            };

            this.stompClient.onStompError = (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
            };

            // WebSocket 연결 시작
            this.stompClient.activate();
        },
        sendMessage() {
            if (this.newMessage.trim() !== '') {
                const message = {
                    ROOM_ID: this.ROOM_ID,
                    SND_ID: this.getUserData.USER_ID,
                    MESSAGE: this.newMessage,
                };
                console.log(message);
                // STOMP 서버로 메시지 전송
                this.stompClient.publish({
                    destination: `/app/chat/${this.ROOM_ID}`, // 메시지를 보낼 서버 엔드포인트
                    body: JSON.stringify(message),
                });

                this.newMessage = ''; // 메시지 입력창 초기화
            }
        },
        isOwnMessage(senderId) {
          console.log('senderId: ',senderId,' loginUser: ',this.getUserData.USER_ID);
            return senderId === this.getUserData.USER_ID; // senderId가 userId와 같으면 true
        },
        messageAll(){
            let data ={
                ROOM_ID:  this.ROOM_ID
            };
            Transaction.gfnTrx("/findMsgAll","GET",data ,this.fnMsgCallback);
            
        },

        fnMsgCallback(data) {
            console.log(data);
            this.messages = [...data];
            //this.messages.push();
        },
        goBack() {
          this.$router.go(-1); // 이전 페이지로 돌아가기
        },
        toggleSlideMenu() {
          console.log('isSlideMenuOpen:',this.isSlideMenuOpen);
          this.isSlideMenuOpen = !this.isSlideMenuOpen; // 슬라이드 메뉴 열고 닫기
        },
        handleOutsideClick(event) {
          // 슬라이드 메뉴 바깥쪽을 클릭한 경우 닫기
          const slideMenu = this.$refs.slideMenu;
          if (slideMenu && !slideMenu.contains(event.target)) {
            this.isSlideMenuOpen = false;
          }
        }
    },
    mounted() {
        // 컴포넌트가 마운트되면 WebSocket에 연결
        this.messageAll();
        this.connect();
        console.log(this.$route.params);

    },
    beforeUnmount() {
        // 컴포넌트가 해제되기 전에 WebSocket 연결 해제
        if (this.socket) {
          this.socket.close();
        }
        // 컴포넌트가 제거될 때 이벤트 리스너 해제
        document.removeEventListener('click', this.handleOutsideClick);
    },
    computed: {
        // Vuex 상태를 참조하여 로그인 여부를 가져옴
        ...mapGetters(['getUserData']),
        userId() {
            return this.getUserId;
        },
    },
};
</script>

<style scoped>
.main-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px;
  background-color: #f5f5f5;
  border-bottom: 1px solid #ddd;
}

.back-button, .menu-button {
  background: none;
  border: none;
  font-size: 15px;
  cursor: pointer;
}

.room-name {
  font-size: 20px;
  font-weight: bold;
}

.chat-room-container {
padding: 5px;
  height: 100%;
}
.messages {
height: 400px;
overflow-y: scroll;
border: 1px solid #ddd;
padding: 10px;
margin-bottom: 10px;
}
.message {
margin-bottom: 10px;
}
.message-input {
display: flex;
}
.message-input input {
flex-grow: 1;
padding: 10px;
}
.message-input button {
padding: 10px 20px;
margin-left: 10px;
}
.slide-menu {
  position: fixed;
  top: 0;
  right: 0;
  width: 250px;
  height: 100%;
  background-color: #fff;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
  z-index: 1000;
}

.slide-menu ul {
  list-style-type: none;
  padding: 20px;
}

.slide-menu ul li {
  margin-bottom: 15px;
}

.slide-menu ul li a {
  text-decoration: none;
  color: #333;
}
.join-count {
  font-size: 11px; /* 친구 수 작은 글씨 크기 */
  margin-left: 5px; /* 친구 수와 제목 간격 조정 */
  color: #777; /* 친구 수 색상 */
  align-self: flex-end; /* 친구 수를 아래로 맞춤 */
}
</style>
