import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            redirect: '/entry_list',
            name: 'index',
            component: () => import('@/views/index/index.vue'),
            children: [
                {
                    path: '/home',
                    name: 'home',
                    meta: {
                        title: '首页'
                    },
                    component: () => import('@/views/home/index.vue')
                },
                {
                    path: '/entry_list',
                    name: 'entry_list',
                    meta: {
                        title: '我的作品'
                    },
                    component: () => import('@/views/entry/entry_list/index.vue')
                },
                {
                    path: '/entry_source',
                    name: 'entry_source',
                    meta: {
                        title: '管理附件'
                    },
                    component: () => import('@/views/entry/entry_source/index.vue')
                },
                {
                    path: '/entry_member',
                    name: 'entry_member',
                    meta: {
                        title: '管理成员'
                    },
                    component: () => import('@/views/entry/entry_member/index.vue')
                },
                {
                    path: '/entry_info',
                    name: 'entry_info',
                    meta: {
                        title: '作品详情'
                    },
                    component: () => import('@/views/entry/entry_info/index.vue')
                },
                {
                    path: '/user_center',
                    name: 'user_center',
                    meta: {
                        title: '个人中心'
                    },
                    component: () => import('@/views/user_center/index.vue')
                },
                {
                    path: '/admin',
                    name: 'admin',
                    meta: {
                        title: '管理'
                    },
                    redirect: '/admin/user',
                    children: [
                        {
                            path: '/admin/user',
                            name: '用户管理',
                            meta: {
                                title: '用户管理'
                            },
                            component: () => import('@/views/admin/user/index.vue')
                        },
                        {
                            path: '/admin/entry',
                            name: '作品管理',
                            meta: {
                                title: '作品管理'
                            },
                            component: () => import('@/views/admin/entry/index.vue')
                        },
                        {
                            path: '/admin/review',
                            name: '作品审核',
                            meta: {
                                title: '作品审核'
                            },
                            component: () => import('@/views/admin/review/index.vue')
                        },
                        {
                            path: '/admin/college',
                            name: '学院管理',
                            meta: {
                                title: '学院管理'
                            },
                            component: () => import('@/views/admin/college/index.vue')
                        }
                    ]
                }
            ]
        },
        {
            path: '/login',
            name: 'login',
            meta: {
                title: '用户登录'
            },
            component: () => import('@/views/login/index.vue')
        },
        {
            path: '/register',
            name: 'register',
            meta: {
                title: '用户注册'
            },
            component: () => import('@/views/register/index.vue')
        },
        {
            path: '/:catchAll(.*)',
            name: 'not-found',
            component: () => import('@/components/404.vue')
        },
    ]
})
router.beforeEach((to, from, next) => {
    const title = to.meta.title as string
    document.title = title
    next()
})
export default router
