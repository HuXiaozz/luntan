import request from '@/utils/request'

export function userRegister(userDTO) {
  return request({
    url: '/ums/user/register',
    method: 'post',
    data: userDTO
  })
}

export function login(data) {
    return request({
      url: '/ums/user/login',
      method: 'post',
      data 
    })
}


export function getUserInfo(){
  return request({
    url: '/ums/user/info',
    method: 'get'
  })
}


// 前台用户注销
export function logout() {
  return request({
    url: '/ums/user/logout'
  })
}