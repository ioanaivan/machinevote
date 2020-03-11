import Vue from 'vue'
import Router from 'vue-router'

import EnregistrementVoteMachine from '@/components/EnregistrementVoteMachine'
import ReponseEnregistrement from '@/components/ReponseEnregistrement'
import Accueil from "@/components/Accueil.vue"

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'EnregistrementVoteMachine',
      component: EnregistrementVoteMachine
    },
    {
      path: '/:pathurl',
      name: 'Accueil',
      component: Accueil
    },
    {
      path: '/:pathurl',
      name: 'ReponseEnregistrement',
      component: ReponseEnregistrement
    }
  ]
})

