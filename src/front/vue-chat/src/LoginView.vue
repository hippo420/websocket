<template>
  <div class="login-container">
    <div class="login-box">
      <div class="logo">
        <img src="MOHE.jpg" alt="logo" />
      </div>
     <div>
      <input
          v-model="USER_ID"
          type="text"
          placeholder="Username"
          class="input-field"
      />
      <input
          v-model="PASSWORD"
          type="password"
          placeholder="Password"
          class="input-field"
      />
      <button class="login-button" @click="login">로그인</button>

      <div class="auto-login-section">
        <input type="checkbox" id="auto-login" v-model="autoLogin" />
        <label for="auto-login">자동로그인</label>
      </div>
      <div class="footer-links">
        <a href="#">계정 찾기</a>
        <span>|</span>
        <a href="#">비밀번호 재설정</a>
      </div>
     </div>
    </div>
  </div>
</template>

<script>
import Transaction from '@/common/core/Transaction';
export default {
    data() {
        return {
            USER_ID: '',
            PASSWORD: '',
            autoLogin: false,
        };
    },
    
    methods: {
        login(){
            let member ={
                USER_ID:  this.USER_ID,
                PASSWORD: this.PASSWORD
            };
            console.log('member->',member);
            Transaction.gfnTrx("/api/member/login","POST",member ,this.fnLoginCallback);
        },
        fnLoginCallback(data){

            this.$emit('login-success');
            this.$store.dispatch('login', data);
            localStorage.setItem('token',this.$store.state.isLoggedIn);
            localStorage.setItem('userId',this.$store.state.userData.USER_ID);
            console.log('loginData:',data);
            this.$router.push('chat-list');

        },
    },
  mounted() {
      if(this.$store.state.isLoggedIn === true)
      {
        this.$router.push('chat-list');
      }
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f2f2f2;
}

.login-box {
  background-color: #4d9ddb;
  padding: 30px;
  border-radius: 10px;
  width: 300px;
  text-align: center;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

.logo img {
  width: 60px;
  margin-bottom: 20px;
}

.input-field {
  width: 70%;
  padding: 10px;
  margin-bottom: 15px;
  border: none;
  border-radius: 5px;
}

.login-button {
  width: 100px;
  height: 30px;
  padding: 5px;
  background-color: #fff;
  color: rgb(6, 89, 134);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 13px;
}

.login-button:hover {
  background-color: rgba(255, 255, 255, 0.8);
}


.auto-login-section {
  margin: 10px 0;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
}

.footer-links {
  margin-top: 20px;
  color: #fff;
}

.footer-links a {
  color: #fff;
  text-decoration: none;
  font-size: 12px;
}

.footer-links span {
  margin: 0 5px;
}
</style>
