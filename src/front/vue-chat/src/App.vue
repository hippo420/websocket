<template>
  <div id="app">
    <!-- 로그인 상태에 따라 화면을 조건부로 렌더링 -->
    <div v-if="!isLoggedIn">
      <LoginView  @login-success="handleLoginSuccess" />
    </div>
    <div v-else>
      <HeaderVue />
      <MainView />
      <FooterVue />
    </div>
  </div>
</template>

<script>
import HeaderVue from '@/HeaderVue.vue';
import FooterVue from '@/FooterVue.vue';
import MainView from '@/MainView.vue';
import LoginView from '@/LoginView.vue';
export default {
  name: 'App',
  components: {
    HeaderVue,
    MainView,
    FooterVue,
    LoginView,
  },
  data() {
    return {
      isLoggedIn: false, // 기본값: 로그인이 안 된 상태
      userData:null,
    };
  },
  methods: {
    handleLoginSuccess() {
      console.log('handleLoginSuccess');
      this.isLoggedIn = true; // 로그인 성공 시 메인 화면을 표시

    },
  },
  mounted() {
    const token = localStorage.getItem('token');

    if (token) {
      this.$store.dispatch('setLogInState', true);  // 로그인 상태 Vuex에 반영
      this.handleLoginSuccess();
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
</style>
