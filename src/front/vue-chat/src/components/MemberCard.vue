<template>
    <div class="member-card">
        <div class="member-image-container">
            <img :src="imageUrl" alt="Chat Room Image" class="member-image" />
        </div>
        <div class="member-info">
            <h3>{{ title }}</h3>
            <p>{{ lastMessage }}</p>
        </div>
        <div class="member-date">
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
.member-card {
display: flex;
justify-content: space-between;
align-items: center;
background-color: #f5f5f5;
border: 1px solid #ddd;
border-radius: 8px;
padding: 15px;
margin-bottom: 10px;
}

.member-image-container {
width: 50px;
height: 50px;
border-radius: 50%;
overflow: hidden;
}

.member-image {
width: 100%;
height: 100%;
object-fit: cover;
}

.member-info {
flex-grow: 1;
margin-left: 15px;
}

.member-info h3 {
margin: 0;
font-size: 18px;
}

.member-info p {
margin: 5px 0 0;
color: #777;
}

.member-date {
white-space: nowrap;
color: #555;
font-size: 14px;
}
</style>
