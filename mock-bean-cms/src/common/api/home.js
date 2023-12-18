import http from '@/common/util/http'

const create = (activity) => {
    return http.post('/activity/create', activity)
}

const update = (activity) => {
    return http.post('/activity/update', activity)
}

const get = (id) => {
    return http.get('/activity/select', {
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
    update,
    get,
    getAllByPage,
    online,
    offline,
    getSignChannels
}
