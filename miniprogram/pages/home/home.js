// pages/home/home.js
import {User} from '@utils/api'
import showMessage from '~/components/showMessage'

Page({

    /**
     * 页面的初始数据
     */
    data: {
        userInfo: {}
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.getLoginUser()
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    },
    getLoginUser() {
        User.getLoginUser().then(res => {
            if (res.data.code == 0) {
                this.setData({
                    userInfo: res.data.data
                })
                wx.setStorageSync('userInfo', res.data.data)
            } else {
                showMessage.warning(res.data.message)
            }
        })
    },
    logout() {
        User.logout().then(res => {
            wx.clearStorageSync()
            showMessage.success('退出登录成功')
            setTimeout(() => {
                wx.navigateTo({
                    url: '/pages/login/login',
                })
            }, 1000)
        })
    },
    toUpdateUser() {
        wx.navigateTo({
            url: '/pages/updateUser/updateUser',
        })
    }
})