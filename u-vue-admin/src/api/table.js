import request from '@/utils/request'

export function getProductList(params) {
  return request({
    url: 'http://106.14.239.125:8081/product/list',
    method: 'post',
    params
  })
}

export function getOrderList(params) {
  return request({
    url: 'http://106.14.239.125:8081/order/list',
    method: 'post',
    params
  })
}

export function modifyOrderStatus(params) {
  return request({
    url: 'http://106.14.239.125:8081/order/modifyStatus',
    method: 'post',
    params
  })
}
