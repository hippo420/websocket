<template>
  
  <HeaderVue/>
  
  <MainView/>
  <FooterVue/>
</template>

<script>

import FooterVue from '@/FooterVue.vue';
import HeaderVue from '@/HeaderVue.vue';
import MainView from '@/MainView.vue';

export default {
  name: 'App',
  components: {
    HeaderVue,
    MainView,
    FooterVue,
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
