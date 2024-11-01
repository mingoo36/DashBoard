import Home from "@/pages/Home.vue";
import Login from "@/pages/Login.vue";
import BasicInfo from "@/pages/BasicInfo.vue";
import FacilityManagement from "@/pages/FacilityManagement.vue";
import JasanManagement from "@/pages/JasanManagement.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
    { path: '/', component: Login, meta: { layout: 'blank' } }, // 로그인 페이지는 blank 레이아웃
    { path: '/home', component: Home, meta: { layout: 'default' } }, // 홈 페이지는 기본 레이아웃
    { path: '/basic-info', component: BasicInfo, meta: { layout: 'default' } },
    { path: '/facility-management', component: FacilityManagement, meta: { layout: 'default' } },
    { path: '/jasan-management', component: JasanManagement, meta: { layout: 'default' } }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;

