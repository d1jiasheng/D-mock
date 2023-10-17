<template>
  <div>
    <b-navbar
      toggleable="lg"
      type="light"
      variant="light"
    >
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-link
            active-class="active"
            class="nav-link"
            v-for="routes in links"
            :key="routes.id"
            :to="routes.path"
          >
            <b-nav-item>
              {{ routes.name }}
            </b-nav-item>
          </b-link>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      links: [
        {
          id: 0,
          name: '主页11',
          path: '/',
        },
        {
          id: 1,
          name: '项目',
          path: '/RSVP',
        },
        {
          id: 2,
          name: '数据',
          path: '/pictures',
        },
        {
          id: 3,
          name: '其他',
          path: '/contact',
        },
      ],
      msg: 'Welcome to 111 Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
