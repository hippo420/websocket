export default{

    fnGetDateString(time){
        const today = new Date();
        const givenDate = new Date(time);

        // 오늘 날짜와의 차이를 계산
        const diffTime = today.getTime() - givenDate.getTime();
        const diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24)); // 밀리초를 일(day) 단위로 변환

        if (diffDays === 0) {
            // 오늘인 경우 시간 표시
            const hours = givenDate.getHours();
            const minutes = givenDate.getMinutes().toString().padStart(2, '0'); // 2자리로 포맷팅

            const period = hours >= 12 ? '오후' : '오전';
            const formattedHour = hours % 12 || 12; // 12시간 형식으로 변환, 0시를 12시로 표시

            return minutes == '00' ? `${period} ${formattedHour}시` : `${period} ${formattedHour}시 ${minutes}분`;
        } else if (diffDays === 1) {
            return '어제';
        } else {
            // 날짜 형식: 9월 30일
            return `${givenDate.getMonth() + 1}월 ${givenDate.getDate()}일`;
        }
    }
}