import Cookies from 'js-cookie';

export default {
    install: (app) => {
        // 쿠키 설정
        app.config.globalProperties.$setCookie = (name, value, days) => {
            Cookies.set(name, value, { expires: days });
        };

        // 쿠키 가져오기
        app.config.globalProperties.$getCookie = (name) => {
            return Cookies.get(name);
        };

        // 쿠키 삭제
        app.config.globalProperties.$deleteCookie = (name) => {
            Cookies.remove(name);
        };
    },
};


