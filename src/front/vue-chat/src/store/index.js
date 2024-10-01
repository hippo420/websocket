import { createStore } from 'vuex';


const store = createStore({
    state: {
        isLoggedIn: false, // 로그인 여부를 관리하는 상태
        userData : {
            USER_ID :'',
            USER_NAME:'',
            IMG_CTT:null,
            COMMENT : ''
        }
    },
    mutations: {
        login(state,payload) {
            console.log('login ->',payload.userData.USER_ID);
            state.isLoggedIn = true;
            state.userData.USER_ID = payload.userData.USER_ID;
            state.userData.USER_NAME = payload.userData.USER_NAME;
            state.userData.IMG_CTT = payload.userData.IMG_CTT;
            state.userData.COMMENT = payload.userData.COMMENT;
        },
        logout(state) {
            state.isLoggedIn = false;
            state.userData = null;
        },
        setLogInState(state, isLogin) {
            state.isLoggedIn = isLogin;
        },
    },
    actions: {
        login({ commit }, userData) {
            // 실제 로그인 로직을 여기에 작성한 뒤 commit
            commit('login',{ userData  });
        },
        logout({ commit }) {
            // 실제 로그아웃 로직을 여기에 작성한 뒤 commit
            commit('logout');
        },
        setLogInState({ commit }, isLogin) {
            commit('setLogInState', isLogin);
        }
    },
    getters: {
        isLoggedIn: (state) => state.isLoggedIn,
        getUserData: (state) => state.userData,
        getUserId: (state) => state.userData.USER_ID,
    },
});

export default store;