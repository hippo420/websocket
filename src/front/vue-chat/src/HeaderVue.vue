<template>
    <header class="header">
        <div class="header-content">
            <table>
                <tbody>
                    <tr>
                        <td >
                            <img alt="Vue logo" src="./assets/logo.png" class="logo">
                        </td>
                        <td>
                            <h1  class="title">My Chat App</h1>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div v-if="isLoggedIn">
                <div class="search-bar">
                <input type="text" v-model="searchQuery" placeholder="Search..." />
                <button @click="toggleMenu">☰</button>
                </div>
            </div>
            <div v-else>
                <button @click="navigate('login')">Login</button>
            </div>
        </div>
        <div class="slide-menu" :class="{ 'slide-menu-open': menuOpen }">
            <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Logout</a></li>
            </ul>
        </div>
    </header>
</template>

<script>

import { mapGetters } from 'vuex';
export default {
    data() {
        return {
        searchQuery: '',
        menuOpen: false,
        };
    },
    name: 'AppHeader',
    methods: {
        toggleMenu() {
            this.menuOpen = !this.menuOpen;
        },
        navigate(page) {
            this.$router.push(`/${page}`);
        },
    },
    computed: {
        // Vuex 상태를 참조하여 로그인 여부를 가져옴
        ...mapGetters(['isLoggedIn']),
    },

};
</script>

<style scoped>
.header {
background-color: #8eef91;
color: rgb(0, 0, 0);
padding: 0.5px 0;
position: fixed;
width: 100%;
top: 0;
left: 0;
z-index: 1000;

}

.header-content {
display: flex;
justify-content: space-between;
align-items: center;
padding: 0 20px;
}

.search-bar {
display: flex;
align-items: center;
}

.search-bar input {
padding: 5px;
border: none;
border-radius: 4px;
}

.search-bar button {
background: none;
border: none;
color: white;
font-size: 20px;
cursor: pointer;
margin-left: 10px;
}

.slide-menu {
position: fixed;
top: 0;
right: -250px; /* 메뉴가 기본적으로 숨겨져 있음 */
width: 250px;
height: 100%;
background-color: #333;
color: white;
transition: right 0.3s ease;
z-index: 1000;
display: flex;
align-items: center;
justify-content: center;
}

.slide-menu-open {
right: 0;
}

.slide-menu ul {
list-style-type: none;
padding: 0;
margin: 0;
text-align: center;
}

.slide-menu li {
margin: 20px 0;
}

.slide-menu a {
color: white;
text-decoration: none;
font-size: 18px;
}

.logo {
    width: 25px;
    height: 25px;
}
.title {
    padding: 0;
    margin: 0;
    left: 0;
}
</style>
