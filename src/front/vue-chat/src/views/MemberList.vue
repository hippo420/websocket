<template>
    <div class="chat-list-container">
        <h2>Chat List</h2>
        <ChatCard
            v-for="chat in chatList"
            :key="chat.id"
            :title="chat.name"
            :lastMessage="chat.lastMessage"
            :lastMessageDate="chat.lastMessageDate"
            :imageUrl="chat.imageUrl"
            @click="openChat(chat.id)"
        />
    </div>
</template>

<script>
import ChatCard from '../components/ChatCard.vue';

export default {
components: {
    ChatCard,
},
data() {
    return {
    chatList: [
        {
        id: 1,
        name: 'Chat Room 1',
        lastMessage: 'Hello there!',
        lastMessageDate: '2024-09-01',
        imageUrl: 'https://via.placeholder.com/50', // 채팅방 이미지
        },
        {
        id: 2,
        name: 'Chat Room 2',
        lastMessage: 'How are you?',
        lastMessageDate: '2024-09-02',
        imageUrl: 'https://via.placeholder.com/50',
        },
        // 추가 채팅방 데이터
    ],
    };
},
methods: {
    openChat(chatId) {
    this.$router.push(`/chat/${chatId}`);
    },
},
};
</script>

<style scoped>
.chat-list-container {
padding: 20px;
background-color: rgb(234, 241, 233);
}

</style>
