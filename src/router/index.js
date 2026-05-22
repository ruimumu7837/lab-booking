import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Equipment from '../views/Equipment.vue'
import Booking from '../views/Booking.vue'
import Statistics from '../views/Statistics.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/register', component: () => import('../views/Register.vue') },
  { path: '/equipment', component: Equipment, meta: { auth: true } },
  { path: '/booking', component: Booking, meta: { auth: true } },
  { path: '/statistics', component: Statistics, meta: { auth: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.auth && !localStorage.getItem('token')) {
    next('/login')
  } else {
    next()
  }
})

export default router