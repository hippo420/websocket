<template>
    <div class="main-view">
        <router-view /> <!-- 페이지의 메인 컨텐츠를 렌더링합니다. -->
    </div>
</template>

<script>
export default {
name: 'MainView',
};
</script>

<style scoped>
.main-view {
min-height: calc(100vh - 120px); /* 헤더와 푸터를 제외한 최소 높이 설정 */
padding: 20px;
box-sizing: border-box;
}
</style>
