import requester from "./request";

function get(uri, params) {
    return requester({
        method: 'get',
        url: uri,
        params: params
    })
}

function post(uri, params) {
    return requester({
        method: 'post',
        url: uri,
        data: params
    })
}

function put(uri, params) {
    return requester({
        method: 'put',
        url: uri,
        data: params
    })
}

function del(uri, params) {
    return requester({
        method: 'delete',
        url: uri,
        params: params
    })
}

export default {get, post, put, del}