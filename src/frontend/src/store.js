import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        status: '',
        token: localStorage.getItem('token') || '',
        tokenType: localStorage.getItem('tokenType') || '',
        role: '',
        message: ''
    },
    mutations: {
        auth_request(state) {
            state.status = 'loading'
        },
        auth_success(state, token, tokenType, message, role) {
            state.status = 'success'
            state.token = token
            state.tokenType = tokenType
            state.message = message
            state.role = role
        },
        auth_error(state) {
            state.status = 'error'
        },
        logout(state) {
            state.status = '',
            state.token = '',
            state.tokenType = '',
            state.role = ''
        }
    },
    actions: {
        login({commit}, user) {
            return new Promise((resolve, reject) => {
                commit('auth_request')
                axios({url: 'http://localhost:5000/api/auth/signin', data: user, method: 'POST'}).then(response => {
                    const token = response.data.accessToken
                    const tokenType = response.data.tokenType
                    const message = user.usernameOrEmail + " registered successfully!"
                    const role = response.data.role
                    localStorage.setItem('token', token)
                    localStorage.setItem('tokenType', tokenType)
                    commit('auth_success', token, tokenType, message, role)
                    resolve(response)
                })
                .catch(error => {
                    commit('auth_error')
                    localStorage.removeItem('token')
                    reject(error)
                })
            })
        },

        register({commit}, user) {
            return new Promise((resolve, reject) => {
                commit('auth_request')
                axios({url: 'http://localhost:5000/api/auth/signup', data: user, method: 'POST'}).then(response => {
                    const token = response.data.accessToken
                    const tokenType = response.data.tokenType
                    const message = response.data.message
                    const role = "ROLE_USER"
                    localStorage.setItem('token', token, tokenType)
                    axios.defaults.headers.common['Authorization'] = tokenType + token
                    commit('auth_success', token, tokenType, message, role)
                    resolve(response)
                })
                .catch(error => {
                    commit('auth_error', error)
                    localStorage.removeItem('token')
                    reject(error)
                })
            })
        },
        logout({commit}) {
            return new Promise((resolve) => {
                commit('logout')
                localStorage.removeItem('token')
                delete axios.defaults.headers.common['Authorization']
                resolve()
            })
        }
    },

    getters: {
        isLoggedIn: state => !!state.token,
        authStatus: state => state.status,
        role: state => state.role
    }
})