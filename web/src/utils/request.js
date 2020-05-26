import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken, removeToken } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log("error:"+error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    if (res.status === '-1'){
      removeToken()
      window.href.location("/#/")
    }
    if (res.message !== null && res.message !== ''){
      Message({
        message: res.message,
        type: res.status === '0'?'success':'error',
        duration: 3 * 1000
      })
    }
    if (res.status !== '0'){
      const error =new Error();
      return Promise.reject(error)
    }
    return res
  },
  error => {
    console.log('err' + error) // for debug

    return Promise.reject(error)
  }
)

export const request = service


export const fetchGet = function(url, params = {}) {
  return new Promise((resolve, reject) => {
    service.get(url, {params}).then(res => {
      resolve(res)
    }).catch(error => {
      reject(error)
    })
  })
}
export const fetchPost = function (url, params = {}) {
  return new Promise((resolve, reject) => {
    service.post(url, params).then(res => {
      resolve(res)
    }).catch(error => {
      reject(error)
    })
  })
}

export const  fetchPut =  (url, params = {}) =>{
  return new Promise((resolve, reject) => {
    service.put(url, params).then(res => {
      resolve(res)
    }).catch(error => {
      reject(error)
    })
  })
}

export const  fetchDelete = (url, params = {}) =>{
  return new Promise((resolve, reject) => {
    service.delete(url, params).then(res => {
      resolve(res)
    }).catch(error => {
      reject(error)
    })
  })
}

