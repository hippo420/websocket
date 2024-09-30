<template>
  <div class="friend-list-container">
    <h2 class="friends-title">친구 <span class="friend-count">{{ friendList.length }}</span></h2>
    <!-- 사용자 프로필 카드 -->
    <div class="profile-card">
      <ProfileCard
          :USER_NAME="user.USER_NAME"
          :IMG_CTT="user.IMG_CTT"
          :COMMENT="user.COMMENT"
          @dblclick="openProfile()"
      />
    </div>

    <!-- 친구 목록에서 ProfileCard 사용 -->
    <div v-for="friend in friendList" :key="friend.USER_ID">
      <ProfileCard
          :USER_NAME="friend.USER_NAME"
          :IMG_CTT="friend.IMG_CTT"
          :COMMENT="friend.COMMENT"
          @dblclick="openFriendProfile(friend.USER_ID)"
      />
    </div>
  </div>
</template>

<script>
import Transaction from '@/common/core/Transaction';
import ProfileCard from '../components/ProfileCard.vue';

export default {
  components: {
    ProfileCard,
  },
  data() {
    return {
      friendList: [],
      user: {},
    };
  },
  methods: {
    openFriendProfile(friend_id) {
      this.$router.push(`/friend/${friend_id}`);
    },
    openProfile() {
      this.$router.push('/myprofile');
    },
    loadfriendList() {
      let member = {
        USER_ID: this.$store.state.userData.USER_ID,
      };
      console.log('loadfriendList', member);
      Transaction.gfnTrx("/api/member/findFriend", "GET", member, this.fnFriendListCallback);
    },
    fnFriendListCallback(data) {
      console.log(data);
      this.friendList = [...data];
    }
  },
  mounted() {
    this.user = this.$store.state.userData;
    this.loadfriendList();
  }
};
</script>

<style scoped>
.friends-title {
  text-align: left; /* 제목 왼쪽 정렬 */
  font-size: 24px; /* 제목 크기 조정 */
  margin-bottom: 10px; /* 제목과 친구 카드 사이 여백 */
  display: flex; /* 플렉스 박스 사용 */
  align-items: flex-end; /* 맨 아래로 정렬 */
}

.friend-count {
  font-size: 14px; /* 친구 수 작은 글씨 크기 */
  margin-left: 5px; /* 친구 수와 제목 간격 조정 */
  color: #777; /* 친구 수 색상 */
  align-self: flex-end; /* 친구 수를 아래로 맞춤 */
}

.friend-list-container {
  min-height: 800px;
  padding: 10px;
  background-color: #FFFFFF;
}



.profile-card {
  margin-bottom: 20px;
}
</style>
