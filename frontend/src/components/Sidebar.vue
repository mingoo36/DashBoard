<template>
  <ul id="slide-out" class="sidenav sidenav-fixed" v-if="showSidebar">
    <li>
      <div class="user-view">
        <router-link to="/home" class="logo">
          <img src="@/assets/logo2.png" alt="선문대학교 로고" style="width: 150px; margin-left: 18px;" />
        </router-link>
      </div>
    </li>

    <!-- 사용자 정보 표시 -->
    <li v-if="$store.state.account.id">
      <div class="user-info">
        <span style="color: white;">{{ username }}님 환영합니다!</span>
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
import { defineComponent, computed } from 'vue';
import router from "@/scripts/router";
import store from "@/scripts/store";

export default defineComponent({
  name: 'Sidebar',

  setup(){
    const logout = () => {
      store.commit('setAccount', 0);
      sessionStorage.removeItem("id");
      sessionStorage.removeItem("name"); // name도 세션 스토리지에서 제거
      router.push({path: "/"});
    }

    const showSidebar = computed(() => {
      const route = router.currentRoute.value;
      return route.meta.layout !== 'blank';
    });

    const username = computed(() => {
      return sessionStorage.getItem("name") || ''; // 세션 스토리지에서 이름 가져오기
    });

    return { logout, showSidebar, username };
  },

  data() {
    return {
      activeMenu: null,
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
            { title: '체크리스트', link: '/work-permit' }
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
  }
});
</script>

<style scoped>
.sidenav {
  background-color: #2c3e50;
  position: relative;
  height: 100vh;
  width: 250px;
  padding-bottom: 60px;
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

/* 사용자 정보 스타일 */
.user-info {
  padding: 10px 20px;
  text-align: center;
  font-size: 16px;
  color: white; /* 사용자 정보 색상 */
}

/* 로그인/로그아웃 섹션 스타일 */
.login-section {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 10px 0;
  border-top: 1px solid rgba(255, 255, 255, 0.3);
  text-align: center;
}
</style>

