import { createStore } from 'vuex';
import Cookies from 'js-cookie';

const store = createStore({
    state: {
        isLoggedIn: false, // 로그인 여부를 관리하는 상태
        userData: {
            USER_ID: '',
            USER_NAME: '',
            IMG_CTT: null,
            COMMENT: '',
        },
    },
    mutations: {
        login(state, payload) {
            console.log('login ->', payload.userData.USER_ID);
            state.isLoggedIn = true;
            state.userData.USER_ID = payload.userData.USER_ID;
            state.userData.USER_NAME = payload.userData.USER_NAME;
            state.userData.IMG_CTT = payload.userData.IMG_CTT;
            state.userData.COMMENT = payload.userData.COMMENT;

            // 토큰이 없으면 쿠키에서 토큰을 가져와 설정
            let token = Cookies.get('token');
            if (!token) {
                console.log('쿠키 없음 -> 획득 시도');
                token = this._getCookie('token'); // _getCookie 헬퍼 메서드 사용
                if (token) {
                    Cookies.set('token', token, { expires: 1 }); // 1일간 유효한 토큰 설정
                    console.log('토큰 설정 완료:', token);
                } else {
                    console.log('토큰 없음 -> 쿠키에서 찾지 못함');
                }
            } else {
                console.log('쿠키 있음 ->', token);
            }
        },
        logout(state) {
            state.isLoggedIn = false;
            state.userData = {
                USER_ID: '',
                USER_NAME: '',
                IMG_CTT: null,
                COMMENT: '',
            };

            // 쿠키 삭제
            Cookies.remove('token');
            console.log('로그아웃 -> 토큰 삭제');
        },
        setLogInState(state, isLogin) {
            state.isLoggedIn = isLogin;
        },
    },
    actions: {
        login({ commit }, userData) {
            // 로그인 로직 실행 후, commit
            commit('login', { userData });
        },
        logout({ commit }) {
            // 로그아웃 로직 실행 후, commit
            commit('logout');
        },
        setLogInState({ commit }, isLogin) {
            commit('setLogInState', isLogin);
        },
    },
    getters: {
        isLoggedIn: (state) => state.isLoggedIn,
        getUserData: (state) => state.userData,
        getUserId: (state) => state.userData.USER_ID,
    },
    methods: {
        // 헬퍼 메서드: 쿠키에서 토큰을 파싱하여 반환
        _getCookie(name) {
            const value = `; ${document.cookie}`;
            const parts = value.split(`; ${name}=`);
            if (parts.length === 2) {
                return parts.pop().split(';').shift();
            }
            return null;
        },
    },
});

export default store;