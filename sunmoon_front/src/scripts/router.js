import Home from "@/pages/Home.vue";
import Login from "@/pages/Login.vue";
import BasicInfo from "@/pages/BasicInfo.vue"; // 추가
import FacilityManagement from "@/pages/FacilityManagement.vue"; // 추가
import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {path:'/', component: Home},
    {path:'/login', component: Login},
    { path: '/basic-info', component: BasicInfo },
    { path: '/facility-management', component: FacilityManagement }
]

const router = createRouter({

    history: createWebHistory(),
    routes

})

export default router;