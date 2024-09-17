import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // 라우터를 불러옴
import store from './store';
createApp(App)
.use(router) // 라우터 사용 설정
.use(store) // vuex 사용 설정
.mount('#app')
