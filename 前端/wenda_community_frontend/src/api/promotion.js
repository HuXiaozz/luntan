import request from '@/utils/request'

export function getPromotion(){
    return request({
        url: '/promotion/all',
        method: 'get'
    })
}