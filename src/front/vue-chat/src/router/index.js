import { createRouter, createWebHistory } from 'vue-router';
import ChatList from '../views/ChatList.vue';
import LoginPage from '../views/LoginPage.vue';

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
    },
    {
        path: '/chat/:id',
        name: 'ChatRoom',
        component: () => import('../views/ChatRoom.vue'), // 동적 라우팅으로 채팅방
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
