<template>
  <div>
    <b-card title="Card Title" no-body>
      <b-card-header header-tag="nav">
        <b-nav card-header tabs>
          <b-nav-item to="/" exact exact-active-class="active">首页</b-nav-item>
          <b-nav-item to="/project" exact exact-active-class="active">项目</b-nav-item>
          <b-nav-item to="/bean" exact exact-active-class="active">Bean</b-nav-item>
        </b-nav>
      </b-card-header>

      <b-card-body>
        <router-view></router-view>
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
  export default {
    name: "header",
    data() {
      return {
      }
    }
  }
</script>

<style scoped>

</style>
