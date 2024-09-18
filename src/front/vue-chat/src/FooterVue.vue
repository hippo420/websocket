<template>
    <div  class="footer">
        
        <div v-if="isLoggedIn">
            <button @click="navigate('chat-list')" >
                <font-awesome-icon icon="comments" />
                <p>채팅방</p>
            </button>
            <button @click="navigate('friends')" >
                <font-awesome-icon icon="user" />
                <p>친구</p>
            </button>
            <button @click="navigate('settings')" >
                <font-awesome-icon icon="cog" />
                <p>설정</p>
            </button>
        </div>
        <div v-else>
            <button @click="navigate('login')">Login</button>
        </div>
    </div >
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
background-color: #333;
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
background: #333;
border: none;
font-size: 30px;
color: #ddd;
cursor: pointer;
border-color: #f5f5f5;
}

.footer button p{
    font-size: 9px;
}

.footer button:hover {
color: #c8cd38;
}

</style>
