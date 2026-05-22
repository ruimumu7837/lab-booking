import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Equipment from '../views/Equipment.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/register', component: () => import('../views/Register.vue') },
  { path: '/equipment', component: Equipment, meta: { auth: true } },
  // 后面再加 booking 和 statistics
]
const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router