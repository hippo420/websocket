<template>
    <div class="login-container">
        <h2>Login</h2>
        <form @submit.prevent="login">
            <input v-model="userId" type="text" placeholder="Username" required />
            <input v-model="password" type="password" placeholder="Password" required />
            <button type="submit">Login</button>
        </form>
    </div>
</template>

<script>
import Transaction from '@/common/core/Transaction';
export default {
    data() {
        return {
            userId: '',
            password: '',
        };
    },
    
    methods: {
        login(){
            let member ={
                userId:  this.userId,
                password: this.password
            };
            Transaction.gfnTrx("/api/member/login","POST",member ,this.fnLoginCallback);
        },
        fnLoginCallback(data){
            console.log('setLoginStore -> ',data);
            //this.$store.commit('login', data);
            this.$store.dispatch('login', data.userId);
            //this.$router.push('main');
            this.$router.push('chat-list');
        },
    },
};
</script>

<style scoped>
.login-container {
display: flex;
flex-direction: column;
align-items: center;
justify-content: center;
height: 100vh;
}
</style>
