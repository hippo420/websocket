<template>
    <footer class="footer">
        
        <div v-if="isLoggedIn">
            <button @click="navigate('chat-list')">Chat</button>
            <button @click="navigate('settings')">Settings</button>
            <button @click="navigate('friends')">Friends</button>
        </div>
        <div v-else>
            <button @click="navigate('login')">Login</button>
        </div>
    </footer>
</template>

<script>

import { mapGetters } from 'vuex';

export default {
    name: 'AppFooter',
    methods: {
        navigate(page) {
        this.$router.push(`/${page}`);
        },
    },
    computed: {
        // Vuex 상태를 참조하여 로그인 여부를 가져옴
        ...mapGetters(['isLoggedIn']),
    },
};
</script>

<style scoped>
.footer {
background-color: #f5f5f5;
border-top: 1px solid #ddd;
padding: 10px 0;
display: flex;
justify-content: space-around;
position: fixed;
width: 100%;
bottom: 0;
left: 0;
}

.footer button {
background: none;
border: none;
font-size: 16px;
color: #333;
cursor: pointer;
}

.footer button:hover {
color: #4CAF50;
}
</style>
