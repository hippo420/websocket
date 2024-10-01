
import { library } from '@fortawesome/fontawesome-svg-core';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { faCog, faComments, faPaperPlane, faSmile, faUser,faAngleLeft  } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // 라우터를 불러옴
import store from './store';
library.add(faUser
            , faComments
            , faCog
            , faPaperPlane
            , faSmile
            , faBars
            , faAngleLeft
);

const app = createApp(App);

// 전역 컴포넌트로 FontAwesomeIcon 추가
app.component('font-awesome-icon', FontAwesomeIcon);

// 플러그인 사용 설정
app.use(router); // 라우터 사용
app.use(store);  // vuex 사용

app.mount('#app');
