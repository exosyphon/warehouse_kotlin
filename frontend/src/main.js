import 'babel-polyfill'
import Vue from 'vue'
import App from './App.vue'
import store from './store/store'
import VueRouter from 'vue-router'
import OtherWarehousesAgain from './components/OtherWarehousesAgain.vue'
import OtherWarehouses from './components/OtherWarehouses.vue'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(VueMaterial)

const routes = [
  { path: '/', component: OtherWarehousesAgain },
  { path: '/other-warehouses', component: OtherWarehouses }
]

const router = new VueRouter({
  routes
})

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
