import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import globalVar from '../../components/globalVar.vue'

Vue.use(VueAxios, axios)

Vue.prototype.$myGlobalVar = globalVar;

const vm = new Vue()
const requester = axios.create({
  baseURL: vm.$myGlobalVar.data().baseUrl,
  timeout: 5000
})

requester.interceptors.request.use(
  config => {
    // convert data obj to json str
    // if (config.method === 'post' || config.method === 'put') {
    //     config.headers = {
    //         'Content-Type': 'application/json'
    //     }
    //     config.data = JSON.stringify(config.data);
    // }
    return config;
  }, error => {
    console.log(error.response);
    return Promise.reject(error);
  }
)

requester.interceptors.response.use(
  response => {
    if (response.data.code != 200) {
      console.warn(response)
      vm.$bvToast.toast('操作失败，' + response.data.msg, {
        variant: 'danger',
        solid: true
      })
      return new Promise(() => {
      })
    }
    return response
  }, error => {
    vm.$bvToast.toast('操作异常，请重试', {
      variant: 'danger',
      solid: true
    })
    return Promise.reject(error);
  }
)

export default requester
