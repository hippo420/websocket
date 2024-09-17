<template>
    <div class="chat-card">
        <div class="chat-image-container">
            <img :src="imageUrl" alt="Chat Room Image" class="chat-image" />
        </div>
        <div class="chat-info">
            <h3>{{ title }}</h3>
            <p>{{ lastMessage }}</p>
        </div>
        <div class="chat-date">
            <span>{{ lastMessageDate }}</span>
        </div>
    </div>
</template>

<script>
export default {
name: 'ChatCard',
props: {
    title: {
    type: String,
    required: true,
    },
    lastMessage: {
    type: String,
    required: true,
    },
    lastMessageDate: {
    type: String,
    required: true,
    },
    imageUrl: {
    type: String,
    required: true,
    },
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
border-radius: 50%;
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
margin: 0;
font-size: 18px;
}

.chat-info p {
margin: 5px 0 0;
color: #777;
}

.chat-date {
white-space: nowrap;
color: #555;
font-size: 14px;
}
</style>
