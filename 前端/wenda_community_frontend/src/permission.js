import router from './router'
import store from './store'
import getPageTitle from '@/utils/get-page-title'

import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'
import {getToken} from "@/utils/auth"; // progress bar style

NProgress.configure({showSpinner: false}) // NProgress Configuration
//导航守卫，当从一个页面跳转到另一个页面的时候做一些事情
router.beforeEach(async (to, from, next) => {
    // start progress bar
    NProgress.start()
    // set page title
    document.title = getPageTitle(to.meta.title)
    // determine whether the user has logged in
    const hasToken = getToken();

    if (hasToken) {
        if (to.path === '/login') {
            // 登录，跳转首页
            next({path: '/'})
            NProgress.done()
        } else {
            // 获取用户信息
            await store.dispatch('user/getInfo')
            next()
        }
    } else if (!to.meta.requireAuth)
    {
        next()
    }
    else {
        next('/login')
    }
})

router.afterEach(() => {
    // finish progress bar
    NProgress.done()
})
