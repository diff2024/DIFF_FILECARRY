import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios'
import VueSession from 'vue-session'

Vue.use(Router)
var sessionOptions = {
  persist: true
}

Vue.use(VueSession, sessionOptions)

const Main = () => {
	return import("./views/Main.vue")
}

export const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes : [
    {
      path: '/',
      name: 'main',
      component: Main,
    },
  ]
});

router.beforeEach(function (to, from, next) {
  next()
});