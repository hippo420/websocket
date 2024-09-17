import { createStore } from 'vuex';


const store = createStore({
    state: {
        isLoggedIn: false, // 로그인 여부를 관리하는 상태
        userId:''
    },
    mutations: {
        login(state,payload) {
            state.isLoggedIn = true;
            state.userId = payload.userId;
        },
        logout(state) {
            state.isLoggedIn = false;
            state.userId = '';
        },
    },
    actions: {
        login({ commit }, userId) {
            // 실제 로그인 로직을 여기에 작성한 뒤 commit
            commit('login',{ userId  });
        },
        logout({ commit }) {
            // 실제 로그아웃 로직을 여기에 작성한 뒤 commit
            commit('logout');
        },
    },
    getters: {
        isLoggedIn: (state) => state.isLoggedIn,
        getUserId: (state) => state.userId,
    },
});

export default store;