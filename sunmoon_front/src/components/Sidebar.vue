<template>
  <ul id="slide-out" class="sidenav sidenav-fixed">
    <li>
      <div class="user-view">
        <router-link to="/" class="logo">
          <img src="@/assets/logo.svg" alt="선문대학교 로고" style="width: 150px; margin-left: 40px; margin-top: 10px;" />
        </router-link>
      </div>
    </li>

    <!-- 드롭다운 메뉴들 -->
    <li v-for="(item, index) in menuItems" :key="index">
      <a @click="toggleSubmenu(index)" href="#!" class="SMN_effect-21">
        <span class="menu-icon" :class="{ 'rotate-icon': activeMenu === index }">▶</span>{{ item.title }}
      </a>
      <ul v-show="activeMenu === index" class="submenu">
        <li v-for="(subItem, subIndex) in item.submenu" :key="subIndex">
          <router-link :to="subItem.link" class="SMN_effect-21">
            <span class="submenu-icon"></span>{{ subItem.title }}
          </router-link>
        </li>
      </ul>
    </li>

    <!-- 로그인/로그아웃 섹션 - 사이드바 맨 아래에 고정 -->
    <li class="login-section">
      <router-link to="/login" class="text-white" v-if="!$store.state.account.id">로그인</router-link>
      <a to="/login" class="text-white" @click="logout()" v-else>로그아웃</a>
    </li>

  </ul>
</template>

<script>
import { defineComponent } from 'vue';
import router from "@/scripts/router";
import store from "@/scripts/store";

export default defineComponent({
  name: 'Sidebar',

  setup(){
    const logout = ()=>{
      store.commit('setAccount', 0);
      sessionStorage.removeItem("id");
      router.push({path: "/"});

    }
    return {logout};
  },

  data() {
    return {
      activeMenu: null,
      isLoggedIn: false, // 로그인 상태 변수
      menuItems: [
        {
          title: '기준정보',
          submenu: [
            { title: '공지사항', link: '/basic-info' },
            { title: '사업장관리', link: '/facility-management' }
          ]
        },
        {
          title: '자산관리',
          submenu: [
            { title: '자산정보관리', link: '/Jasan-Management' },
            { title: '시설물관리', link: '/facility-mant' }
          ]
        },
        {
          title: '수행',
          submenu: [
            { title: '점검', link: '/inspection' },
            { title: '작업허가', link: '/work-permit' }
          ]
        },
        {
          title: '위험성평가',
          submenu: [
            { title: '위험성평가(빈도,강도법)', link: '/risk-evaluation-frequency' },
            { title: '위험성평가(3단계)', link: '/risk-evaluation-3steps' }
          ]
        }
      ]
    };
  },
  methods: {
    toggleSubmenu(index) {
      this.activeMenu = this.activeMenu === index ? null : index;
    }
    // logout() {
    //   this.isLoggedIn = false;
    // }
  }


  // mounted() {
  //   // 로그인 상태를 확인하는 로직 (예시)
  //   this.isLoggedIn = false; // 실제 로그인 상태 확인 로직 추가
  // }

});
</script>

<style scoped>
.sidenav {
  background-color: #2c3e50;
  position: relative; /* position 설정 */
  height: 100vh; /* 전체 높이 설정 */
  padding-bottom: 60px; /* 하단 여백 확보 */
}

.sidenav a {
  color: white;
  font-weight: bold;
  position: relative;
}

.sidenav a:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.submenu {
  list-style: none;
  padding-left: 20px;
}

.menu-icon {
  display: inline-block;
  margin-right: 10px;
  transition: transform 0.3s ease;
}

.rotate-icon {
  transform: rotate(90deg);
}

.submenu-icon::before {
  content: "•";
  margin-right: 10px;
}

.submenu a:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.SMN_effect-21 {
  position: relative;
}ㅋ

.SMN_effect-21:before,
.SMN_effect-21:after {
  content: "";
  width: 0;
  height: 2px;
  background: rgba(255, 255, 255, 0.4);
  position: absolute;
  top: 0;
  left: 50%;
  transition: all 0.3s ease 0s;
}

.SMN_effect-21:after {
  top: auto;
  bottom: 0;
}

.SMN_effect-21:hover:before,
.SMN_effect-21:hover:after {
  width: 100%;
  left: 0;
}

/* 로그인/로그아웃 섹션 스타일 - 고정 */
.login-section {
  position: absolute;
  bottom: 0; /* 사이드바 하단에 고정 */
  width: 100%;
  padding: 10px 0;
  border-top: 1px solid rgba(255, 255, 255, 0.3);
  text-align: center;
}
</style>
