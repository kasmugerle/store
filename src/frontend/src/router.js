import Vue from "vue";
import VueRouter from "vue-router";
import store from './store.js';
import Category from './components/Category.vue'
import Login from './auth/Login.vue';
import Register from './auth/Register.vue'

Vue.use(VueRouter);

export const router = new VueRouter({
    mode: 'history',
    routes: [

        {
            path: '/',
            name: 'Category',
            meta: {
                requiresAuth: false,
                requiresAdmin: false
            },
            component: Category
        },
        {
            path: "/categories/:categoryId/products",
            name: "Products",
            meta: {
                requiresAuth: false,
                requiresAdmin: false
            },
            component: () => import("./components/Product.vue")  
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
        },
        {
            path: '/user',
            name: 'User',
            meta: {
                requiresAuth: true,
                requiresAdmin: false
            },
            component: () => import("./components/User.vue")
        },
        {
            path: '/carts',
            name: 'Cart',
            meta: {
                requiresAuth: true,
                requiresAdmin: false
            },
            component: () => import("./components/Cart.vue")
        },
        {
            path: '/carts/:cartId/products',
            name: 'CartProducts',
            meta: {
                requiresAuth: true,
                requiresAdmin: false
            },
            component: () => import("./components/CartProducts.vue")
        },
        {
            path: '/admin/categories',
            name: 'AdminCategory',
            meta: {
                requiresAuth: true,
                requiresAdmin: true
            },
            component: () => import("./admin/AdminCategory.vue")
        }
    ],
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
        else if(!adminRequired && role == "ROLE_ADMIN") {
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