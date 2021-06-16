import Vue from 'vue'
import axios from 'axios'

const URL = createInstance("http://localhost:5000");

function createInstance(baseURL) {
    return axios.create({
        baseURL,
        headers: {
        'Content-Type': 'application/json',
        'Authorization': `${localStorage.tokenType}${localStorage.token}`
        }
    })
}

export default {
    install() {
        Vue.prototype.$http = URL
    }
}