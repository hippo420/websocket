<template>
  <div class="chat-container">
    <h2>채팅방 입장</h2>
    <input v-model="roomId" placeholder="채팅방 이름 입력" />
    <button @click="enterroomId">채팅방 입장</button>

    <div v-if="connected">
      <h3>채팅방: {{ roomId }}</h3>
      <div class="messages" v-if="messages.length > 0">
        <div
          v-for="(msg, index) in messages"
          :key="index"
          :class="['message', msg.sender === sender ? 'my-message' : 'other-message']"
        >
          <div class="message-content">
            <strong>{{ msg.sender }}</strong>: {{ msg.content }}
          </div>
        </div>
      </div>
      <div class="no-messages" v-else>
        아직 메시지가 없습니다. 첫 메시지를 입력해보세요!
      </div>
      <input v-model="sender" placeholder="이름 입력" />
      <input v-model="message" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요..." />
      <button @click="sendMessage">전송</button>
    </div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";

export default {
  data() {
    return {
      sender: "",
      message: "",
      roomId: "",
      messages: [],
      socket: null,
      connected: false,
    };
  },
  methods: {
    enterroomId() {
      if (this.roomId) {
        this.connect(this.roomId);
        this.connected = true;
      }
    },
    connect(roomId) {
      this.socket = new SockJS(`http://localhost:8080/ws/chat/${roomId}`);
      this.socket.onmessage = (event) => {
        const receivedMessage = JSON.parse(event.data);
        this.messages.push(receivedMessage);
      };
    },
    sendMessage() {
      if (this.message && this.sender) {
        const chatMessage = {
          sender: this.sender,
          content: this.message,
        };
        this.socket.send(JSON.stringify(chatMessage));
        this.messages.push(chatMessage);
        this.message = "";
      }
    },
  },
};
</script>

<style scoped>
/* 기존 CSS 유지, 필요한 추가 스타일만 포함 */
.chat-container {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #f9f9f9;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.messages {
  margin-bottom: 10px;
  max-height: 300px;
  overflow-y: auto;
  width: 100%;
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.no-messages {
  margin-bottom: 10px;
  padding: 20px;
  width: 100%;
  text-align: center;
  font-style: italic;
  color: #999;
  background-color: #fafafa;
  border: 1px dashed #ddd;
  border-radius: 5px;
}

.message {
  display: flex;
  margin-bottom: 10px;
  max-width: 80%;
  padding: 8px;
  border-radius: 10px;
  position: relative;
  word-break: break-word;
}

.my-message {
  align-self: flex-end;
  background-color: #e1ffc7;
  border: 1px solid #a6e18b;
}

.other-message {
  align-self: flex-start;
  background-color: #f1f1f1;
  border: 1px solid #ddd;
}

.message-content {
  display: block;
}

input {
  margin-top: 5px;
  padding: 8px;
  width: 90%;
  border: 1px solid #ddd;
  border-radius: 5px;
}

button {
  margin-top: 10px;
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}
</style>
