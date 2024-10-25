import { createApp } from 'vue';
import App from './App.vue';
import router from './scripts/router';


// Bootstrap CSS & JS
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js'; // Bootstrap의 JS

// Materialize CSS
import 'materialize-css/dist/css/materialize.min.css';
import 'materialize-css/dist/js/materialize.min.js';
import store from "@/scripts/store"; // Materialize JS
require('vue-ionicons/ionicons.css');

createApp(App)
    .use(store)
    .use(router)
    .mount('#app');

