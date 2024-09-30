<template>
  <div class="footer">
    <div v-if="isLoggedIn" class="footer-buttons">
      <div class="icon-container">
        <button @click="navigate('chat-list')" class="footer-button">
          <font-awesome-icon icon="comments" />
          <p>채팅방</p>
          <span class="badge">73</span>
        </button>
      </div>
      <div class="icon-container">
        <button @click="navigate('friend-list')" class="footer-button">
          <font-awesome-icon icon="user" />
          <p>친구</p>
        </button>
      </div>
      <div class="icon-container">
        <button @click="navigate('settings')" class="footer-button">
          <font-awesome-icon icon="cog" />
          <p>설정</p>
        </button>
      </div>
    </div>
    <div v-else>
      <button @click="navigate('login')" class="footer-button">Login</button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'AppFooter',
  methods: {
    navigate(page) {
      this.$router.push(`/${page}`);
    },
  },
  computed: {
    ...mapGetters(['isLoggedIn']),
  },
};
</script>

<style scoped>
.footer {
  background-color: #ffffff;
  border-top: 1px solid #ddd;
  padding: 10px 0;
  display: flex;
  justify-content: center;
  position: fixed;
  width: 100%;
  bottom: 0;
  left: 0;
  box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
}

.footer-buttons {
  display: flex;
  justify-content: space-around;
  width: 100%;
}

.icon-container {
  position: relative; /* relative 포지션을 통해 배지의 기준을 설정 */
}

.footer-button {
  background: none;
  border: none;
  font-size: 24px;
  color: #333;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
}

.footer-button p {
  font-size: 10px;
  margin: 0;
}

.footer-button:hover {
  color: #c8cd38;
}

.badge {
  background-color: #ff5e00;
  color: white;
  border-radius: 50%;
  padding: 2px 6px;
  font-size: 12px;
  position: absolute; /* absolute 포지션으로 아이콘의 상단에 배치 */
  top: -5px; /* 상단으로 이동 */
  right: -10px; /* 우측으로 이동 */
}
</style>
