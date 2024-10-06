<template>
  <header class="main-header">
    <div class="left-side">
      <h2 class="title">{{ title }}</h2>
      <button v-if="showSearch" @click="findBang" class="menu-button">
        <font-awesome-icon icon="magnifying-glass" />
      </button>
      <button v-if="showAdd" @click="addBang" class="menu-button">
        <font-awesome-icon icon="comment-medical" />
      </button>
      <button v-if="showSettings" @click="setting" class="menu-button">
        <font-awesome-icon icon="gear" />
      </button>
    </div>
  </header>
</template>

<script>

import { mapGetters } from 'vuex';
export default {
    data() {
        return {
          title: '',
          showSearch: false,
          showAdd: false,
          showSettings: false,
        };
    },
    name: 'AppHeader',
    methods: {

        updateHeader(route) {
          // Customize this logic based on your routes
          console.log("헤더 라우터정보:",route);
          switch (route.name) {
            case 'Home':
              this.title = '';
              this.showSearch = false;
              this.showAdd = false;
              this.showSettings = false;
              break;
            case 'ChatRoom':
              this.title = '';
              this.showSearch = false;
              this.showAdd = false;
              this.showSettings = true;
              break;
            case 'FriendList':
              this.title = '친구';
              this.showSearch = false;
              this.showAdd = false;
              this.showSettings = true;
              break;
            case 'ChatList':
              this.title = '채팅';
              this.showSearch = false;
              this.showAdd = false;
              this.showSettings = true;
              break;
          }
        },
        findBang() {
          // Your find logic here
        },
        addBang() {
          // Your add logic here
        },
        setting() {
          // Your settings logic here
        },

    },
    watch: {
      $route(to) {
        this.updateHeader(to);
      },
    },
    computed: {
        // Vuex 상태를 참조하여 로그인 여부를 가져옴
        ...mapGetters(['isLoggedIn']),
    },

};
</script>

<style scoped>
.main-header {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 10px;
  background-color: white;
  border-bottom: 1px solid lightgray;
}
.left-side {
  display: flex;
  align-items: center;
}
.title {
  font-size: 20px;
  font-weight: bold;
  margin-right: 10px;
}
.menu-button {
  background: none;
  border: none;
  font-size: 20px;
  margin-left: 10px;
  cursor: pointer;
}
</style>
