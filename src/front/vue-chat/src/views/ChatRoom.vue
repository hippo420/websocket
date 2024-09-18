<template>
    <div class="chat-room-container">
        <header class="header">
            <h2> {{ roomId }}</h2>
        </header>
        
    
        <!-- 채팅 메시지 목록 -->
        <main class="messages">
        <BubbleMessage
            v-for="(msg, index) in messages"
            :key="index"
            :profileImage="msg.profileImage"
            :sender="msg.snd_ID"
            :message="msg.message"
            :isOwnMessage="isOwnMessage(msg.snd_ID)"
            :sndDTM="msg.snd_DTM"
        />
        </main>
    
        <footer class="message-input">
            
            <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" />
            <button @click="sendMessage">
                <font-awesome-icon icon="paper-plane" />
            </button>
        </footer>
    </div>
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
            roomId: this.$route.params.id, // 라우트 파라미터에서 채팅방 ID 가져옴
            messages: [], // 채팅 메시지 배열
            newMessage: '', // 입력 중인 새 메시지
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
                this.stompClient.subscribe(`/topic/messages/${this.roomId}`, (message) => {
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
                    ROOM_ID: this.roomId,
                    SND_ID: this.getUserId,
                    MESSAGE: this.newMessage,
                };
                console.log(message);
                // STOMP 서버로 메시지 전송
                this.stompClient.publish({
                    destination: `/app/chat/${this.roomId}`, // 메시지를 보낼 서버 엔드포인트
                    body: JSON.stringify(message),
                });

                this.newMessage = ''; // 메시지 입력창 초기화
            }
        },
        isOwnMessage(senderId) {
            return senderId === this.getUserId; // senderId가 userId와 같으면 true
        },
        messageAll(){
            let data ={
                ROOM_ID:  this.roomId
            };
            Transaction.gfnTrx("/findMsgAll","POST",data ,this.fnMsgCallback);
            
        },

        fnMsgCallback(data) {
            console.log(data);
            this.messages = [...data];
            //this.messages.push();
        }
    },
    mounted() {
        // 컴포넌트가 마운트되면 WebSocket에 연결
        this.messageAll();
        this.connect();
    },
    beforeUnmount() {
        // 컴포넌트가 해제되기 전에 WebSocket 연결 해제
        if (this.socket) {
        this.socket.close();
        }
    },
    computed: {
        // Vuex 상태를 참조하여 로그인 여부를 가져옴
        ...mapGetters(['getUserId']),
        userId() {
            return this.getUserId;
        },
    },
};
</script>

<style scoped>
.chat-room-container {
padding: 20px;
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
</style>
