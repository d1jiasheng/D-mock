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
        <table class="table table-hover">
          <thead>
          <tr>
            <th>AppName</th>
            <th>BeanId</th>
            <th>Bean名称</th>
            <th>所属类名称</th>
            <th>方法名称</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(beanConfig,index) in configList" :key="index">
            <td>{{beanConfig.appName}}</td>
            <td>{{beanConfig.beanId}}</td>
            <td>{{beanConfig.beanName}}</td>
            <td>{{beanConfig.className}}</td>
            <td>{{beanConfig.methodName}}</td>
          </tr>
          </tbody>
        </table>
        <div class="overflow-auto">
          <b-pagination-nav v-model="pageNum" :link-gen="pageLinkGen" :number-of-pages="totalPage" use-router/>
        </div>
        <router-view></router-view>
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
  import home from '@/common/api/home'

  export default {
    name: "header",
    data() {
      return {
        // fields:[{key: 'config.id', label: '活动ID'},
        //   {key: 'config.activityKey', label: '活动key'},],
        configList: [],
        pageNum: 1,
        pageSize: 10,
        totalPage: 100,
      }
    },
    mounted() {
      this.refresh()
    },
    watch: {
      pageNum: function () {
        if (this.isFirstLoad) {
          this.isFirstLoad = false
          return
        }
        this.refresh()
      }
    },
    methods: {
      refresh() {
        home.getAllByPage(this.pageSize, this.pageNum).then(resp => {
          this.totalPage = resp.data.totalPage
          this.configList = resp.data.data
          // newCardConfigs.forEach(newCardConfig => {
          //   newCardConfig._rowVariant = this.calcRowVariant(newCardConfig)
          // })
        }).catch(err => {
          console.log(err)
        });
      },
      pageLinkGen(pageNum) {
        return {
          name: 'Header',
          params: {pageNum: pageNum}
        }
      },
    }

  }
</script>

<style scoped>

</style>
