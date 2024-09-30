import { createRouter, createWebHistory } from 'vue-router';
import store from '@/store'; // Vuex store를 가져옴
import ChatList from '../views/ChatList.vue';
import FriendList from '../views/FriendList.vue';
import LoginPage from '../LoginView.vue';

const routes = [
    {
        path: '/',
        name: 'Login',
        component: LoginPage,
    },
    {
        path: '/chat-list',
        name: 'ChatList',
        component: ChatList,
        meta: { requiresAuth: true }, // 로그인 필요 페이지에 meta 설정
    },
    {
        path: '/friend-list',
        name: 'FriendList',
        component: FriendList,
        meta: { requiresAuth: true }, // 로그인 필요 페이지에 meta 설정
    },
    {
        path: '/chat/:id',
        name: 'ChatRoom',
        component: () => import('../views/ChatRoom.vue'), // 동적 라우팅으로 채팅방
        meta: { requiresAuth: true }, // 로그인 필요 페이지에 meta 설정
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

// beforeEach 가드 추가
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token');
    //const userId = localStorage.getItem('userId');
    if (token) {
        // 토큰이 있으면 로그인 상태로 Vuex에 반영
        //store.dispatch('setLoggedIn', true);
        //store.dispatch('login', userId);
    }

    // 로그인 필요한 페이지일 때, 로그인 안 되어 있으면 로그인 페이지로 이동
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!store.getters.isLoggedIn) {
            next({ name: 'Login' }); // 로그인 안 되어 있으면 리다이렉트
        } else {
            next(); // 로그인 되어 있으면 계속 진행
        }
    } else {
        next(); // 로그인 필요하지 않은 페이지는 계속 진행
    }
});

export default router;
