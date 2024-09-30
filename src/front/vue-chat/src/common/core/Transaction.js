import axios from 'axios';

// 헤더 생성 함수
const getHeaders = () => {
    const headers = {
        'Content-Type': 'application/json',
        // 'USER_ID': store.state.userData.userid, // 필요 시 추가
    };
    return headers;
};

// axios를 사용한 gfnTrx 함수
const gfnTrx = async (url, method, data, callback) => {
    let IP = 'localhost';
    const PORT = 8081;

    // full URL 구성
    const fullUrl = `http://${IP}:${PORT}${url}`;
    console.log(fullUrl);

    // axios 설정
    const config = {
        url: fullUrl,
        method: method,
        headers: getHeaders(),
        withCredentials: true, // 쿠키를 포함한 요청
    };

    // GET 요청일 경우, data를 쿼리 파라미터로 변환
    if (method.toUpperCase() === 'GET' && data) {
        console.log('GET request with params:', data);
        config.params = data;  // 쿼리 파라미터로 변환
    } else if (data) {
        config.data = JSON.stringify(data);  // POST, PUT 등의 요청에서는 data를 본문으로 처리
    }

    try {
        const response = await axios(config);
        const responseData = response.data;
        console.log('gfnTrx response data:', responseData);

        // callback 함수 실행
        callback(responseData);

    } catch (error) {
        console.error('API Error:', error);

        // 추가적인 에러 처리 로직이 필요하다면 여기에 작성
    }
};

export default {
    gfnTrx
};
