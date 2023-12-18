<template>
  <form class="form-horizontal" role="form" style="margin-top: 100px;margin-left: 300px">
    <div class="form-group" style="margin-top: 20px">
      <label style="float: left;width: 200px">App名称：</label>
      <input type="text" class="form-control" v-model="beanConfigVo.appName" style="width: 300px;" disabled="disabled"/>
    </div>
    <div class="form-group" style="margin-top: 20px">
      <label style="float: left;width: 200px">beanName：</label>
      <input type="text" class="form-control" v-model="beanConfigVo.beanName" style="width: 300px;"
             disabled="disabled"/>
    </div>

    <div class="form-group" style="margin-top: 20px">
      <label style="float: left;width: 200px">类名：</label>
      <input type="text" class="form-control" v-model="beanConfigVo.className" style="width: 300px;"
             disabled="disabled"/>
    </div>
    <div class="form-group" style="margin-top: 20px">
      <label style="float: left;width: 200px">方法名称：</label>
      <input type="text" class="form-control" v-model="beanConfigVo.methodName" style="width: 300px;"
             disabled="disabled"/>
    </div>
    <div class="form-group" style="margin-top: 20px">
      <label style="float: left;width: 200px">mock bean类型：</label>
      <input type="text" class="form-control" :value="getReturnTxt" style="width: 300px;" disabled="disabled"/>
    </div>
    <div class="form-group" style="margin-top: 20px">
      <label style="float: left;width: 200px">mock是否开启：</label>
      <b-form-radio-group
        id="radio-slots"
        v-model="beanConfigVo.useMock"
        name="radio-options-slots"
      >
        <b-form-radio :value="0" style="float: left; width: 50px;">否</b-form-radio>
        <b-form-radio :value="1">是</b-form-radio>
      </b-form-radio-group>
    </div>
    <div class="form-group" style="margin-top: 20px" v-if="beanConfigVo.useMock == 1">
      <b-form-select :options="mockOption" v-if="beanConfigVo.returnType == 0"
                     v-model="beanConfigVo.mockType" style="float: left;width: 200px">
      </b-form-select>

      <div id="objDiv" v-if="beanConfigVo.returnType == 0 && beanConfigVo.mockType == 0">
        <table class="table table-hover" style="width: 600px;">
          <thead>
          <tr>
            <th>参数名称</th>
            <th>参数类型</th>
            <th>参数值</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(mockValue,index) in beanConfigVo.mockValues" :key="index">
            <td>{{mockValue.parameterName}}</td>
            <td>{{mockValue.parameterType}}</td>
            <td><input type="text" class="form-control" v-model="beanConfigVo.mockValues[index].parameterValue"
                       style="width: 150px;"/></td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="form-group" style="margin-top: 20px" v-if="beanConfigVo.returnType == 0 && beanConfigVo.mockType == 1">
        <textarea class="form-control" v-model="beanConfigVo.jsonMock" style="width: 500px;" rows="5"/>
      </div>
      <div id="otherDiv" v-if="beanConfigVo.returnType == 1 || beanConfigVo.returnType == 2">
        <label style="float: left;width: 200px">mock 参数：</label>
        <input type="text" class="form-control" v-model="beanConfigVo.mockValue" style="width: 150px;"/>
      </div>
    </div>
    <div class="form-group" style="margin-top: 20px">
      <label style="float: left;width: 200px">最新请求(关闭mock生效)：</label>
      <textarea class="form-control" :value="beanConfigVo.latestValue" style="width: 300px;" rows="3"
             disabled="disabled"/>
    </div>
    <div class="form-group" style="margin-top: 20px;margin-left: 200px">
      <div class="col-sm-offset-2 col-sm-10">
        <b-button variant="success" @click="onSubmit">提交</b-button>
        <b-button variant="danger" @click="goBack">返回</b-button>
      </div>
    </div>
  </form>
</template>

<script>
  import home from '@/common/api/home'

  export default {
    name: "BeanConfigDetail",
    data() {
      return {
        beanConfigVo: {
          mockValues: [],
          mockType: 0
        },
        mockOption: [
          {value: 0, text: '参数'},
          {value: 1, text: 'JSON'},
        ],
        configId: this.$route.params.beanConfigId,
      }
    },
    mounted() {
      home.get(this.configId).then(resp => {
        if (resp.data.data) {
          this.$data.beanConfigVo = resp.data.data
        }
      }).catch(err => {
        console.log(err)
      });
    },
    computed: {
      getReturnTxt: function () {
        if (this.beanConfigVo.returnType == 0) {
          return '对象'
        }
        if (this.beanConfigVo.returnType == 1) {
          return '字符串'
        }
        if (this.beanConfigVo.returnType == 2) {
          return 'Integer'
        }
      }
    },
    methods: {
      onSubmit() {
        home.post(this.beanConfigVo).then(resp => this.goBack()).catch(err => {
          console.log(err)
        });
      },
      goBack() {
        this.$router.push({name: 'Header', params: {pageNum: "1"}})
      },
    }
  }
</script>

<style scoped>

</style>
