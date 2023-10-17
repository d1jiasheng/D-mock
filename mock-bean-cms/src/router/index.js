import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Header from '@/components/Header'
import Project from '@/components/Project'
import Bean from '@/components/Bean'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Header',
      component: Header,
      children :[
        { path: 'project', component: Project },
        { path: 'bean', component: Bean },
      ]
    }
  ]
})
