import {Entry} from '@utils/api'
Page({

    /**
     * 页面的初始数据
     */
    data: {
      entryList: [],
      current: 1,
      pageSize: 10,
      searchValue: ''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
      this.getEntryList(this.data.current,this.data.pageSize)
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
      this.setData({
        current: this.data.current+1
      })
      this.getEntryList(this.data.current,this.data.pageSize)
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    },
    getEntryList(current,pageSize) {
      Entry.getEntryList({
        current,
        pageSize,
        searchValue: this.data.searchValue
      }).then((res) => {
        this.setData({
          entryList: [...this.data.entryList,...res.data.data.records]
        })
      })
    },
    toEntry(e) {
      wx.navigateTo({
        url: '/pages/entry/entry?id='+e.currentTarget.dataset.id,
      })
    },
    search(e) {
      this.setData({
        current: 1,
        pageSize: 10,
        entryList: []
      })
      this.setData({
        searchValue: e.detail.value
      })
      this.getEntryList(1,10)
    },
    clearSearch() {
      this.setData({
        searchValue: '',
        entryList: []
      })
      this.getEntryList(1,10)
    }
})