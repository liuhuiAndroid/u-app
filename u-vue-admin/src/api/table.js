import request from '@/utils/request'

// const base_url = 'http://106.14.239.125:8081/'
const base_url = 'http://192.168.1.9:8081/'

export function getProductList(params) {
  return request({
    url: base_url + 'product/list',
    method: 'post',
    params
  })
}

export function getOrderList(params) {
  return request({
    url: base_url + 'order/list',
    method: 'post',
    params
  })
}

export function modifyOrderStatus(params) {
  return request({
    url: base_url + 'order/modifyStatus',
    method: 'post',
    params
  })
}
