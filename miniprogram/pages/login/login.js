import {User} from '@utils/api'
import showMessage from '@components/showMessage'

Page({
    data: {
        loginForm: {
            userAccount: '',
            userPassword: ''
        },
        schoolText: ''
    },
    onLoad(options) {

    },
    onReady() {

    },
    onShow() {

    },
    onHide() {

    },
    onUnload() {

    },
    onPullDownRefresh() {

    },
    onReachBottom() {

    },
    onShareAppMessage() {

    },
    login() {
        User.login(this.data.loginForm).then(res => {
            if (res.data.code == 0) {
                showMessage.success('登录成功')
                // 获取Cookie并存储
                let cookie = res.cookies[0]
                let userInfo = res.data.data
                wx.setStorageSync('Cookie', cookie)
                wx.setStorageSync('userInfo', userInfo)
                setTimeout(() => {
                    wx.switchTab({
                        url: '/pages/index/index',
                    })
                }, 1000)
            } else {
                showMessage.warning(res.data.message)
            }
        })
    },
    userAccountChange(e) {
        this.setData({
            [`loginForm.userAccount`]: e.detail.value
        })
    },
    userPasswordChange(e) {
        this.setData({
            [`loginForm.userPassword`]: e.detail.value
        })
    }
})