import http from '@/common/util/http'

const create = (activity) => {
  return http.post('/activity/create', activity)
}

const post = (mockBeanConfig) => {
  return http.post('/config/post', mockBeanConfig)
}

const get = (id) => {
  return http.get('/config/detail', {
    'id': id
  })
}

const getAllByPage = (pageSize, pageNum) => {
  return http.get('/config/list', {
    'pageSize': pageSize,
    'pageNum': pageNum
  })
}

const online = (id) => {
  return http.post(`/activity/online?id=${id}`)
}

const offline = (id) => {
  return http.post(`/activity/offline?id=${id}`)
}

const getSignChannels = (id) => {
  return http.get('/activity/signChannels')
}

export default {
  create,
  post,
  get,
  getAllByPage,
  online,
  offline,
  getSignChannels
}
