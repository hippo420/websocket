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
                USER_ID:  this.$store.state.userId
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
.chat-list-container {
padding: 20px;
background-color: rgb(234, 241, 233);
}
</style>
