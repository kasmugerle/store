import Vue from "vue";
import VueRouter from "vue-router";
import store from './store.js';
import Login from './auth/Login.vue';
import Register from './auth/Register.vue';
import App from './App.vue'

Vue.use(VueRouter);

export const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'App',
            meta: {
                requiresAuth: false,
                requiresAdmin: false
            },
            component: App
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/register',
            name: 'Register',
            component: Register
        }
    ]
});

router.beforeEach((to, from, next) => {
    const authRequired = to.matched.some((route) => route.meta.requiresAuth)
    const adminRequired = to.matched.some((route) => route.meta.requiresAdmin)
    const authed = store.getters.isLoggedIn
    const role = store.getters.role
    if(authRequired) {
        if(!authed) {
            next('/login')
        }
        else if(adminRequired && role == "ROLE_USER") {
            next('/login')
        }
        else {
            next()
        }
    }
    else {
        next()
    }
})