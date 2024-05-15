import {File, User} from '@utils/api'
import showMessage from '~/components/showMessage'

Page({

    /**
     * 页面的初始数据
     */
    data: {
        genderVisible: false,
        genderList: [
            {value: 1, label: '男'},
            {value: 2, label: '女'}
        ],
        genderText: '',
        form: {
            gender: '',
            userAvatar: ''
        },
        fileList: [],
        defaultGender: '',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.getLocalInfo()
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
    onGenderPicker() {
        this.setData({
            genderVisible: true
        })
    },
    onPickerConfirm(e) {
        this.setData({
            [`form.gender`]: e.detail.value[0],
            genderText: e.detail.label[0]
        })
    },
    handleAdd(e) {
        File.uploadFile(e.detail.files[0].url).then(res => {
            if (res.code == 0) {
                this.setData({
                    [`form.userAvatar`]: res.data
                })
            } else {
                showMessage.warning(res.message)
            }
        })
        this.setData({
            fileList: e.detail.files
        })
    },
    handleRemove(e) {
        this.setData({
            fileList: []
        })
    },
    submit() {
        User.updateUser(this.data.form).then(res => {
            if (res.data.code == 0) {
                showMessage.success('更新成功')
            } else {
                showMessage.warning(res.data.message)
                console.log(res.data);
            }
        })
        console.log(this.data.form);
    },
    getLocalInfo() {
        let info = wx.getStorageSync('userInfo')
        this.setData({
            defaultGender: info.gender,
            fileList: [
                {url: info.userAvatar}
            ]
        })
    }
})