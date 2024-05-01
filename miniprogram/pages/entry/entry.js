import { Entry, Comment } from '@utils/api'
import showMessage from '~/components/showMessage'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    entry: {},
    sources: {
      videoList: [],
      imageList: [],
      audioList: [],
      fileList: []
    },
    fileList: [],
    tabPanelstyle: 'display:flex;flex-direction: column;justify-content:center;align-items:center;',
    current: 1,
    pageSize: 10,
    commentList: [],
    commentVisiable: false,
    bottom: 0,
    form: {
      commentType: 1,
      content: '',
      entryId: '',
      replyId: null
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      entryId: options.id
    })
    this.getEntry(options.id)
    this.getComments(1, 10)
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
      current: this.data.current + 1
    })
    this.getComments(this.data.current, this.data.pageSize)
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },
  getEntry(id) {
    Entry.getEntryById(id).then(res => {
      this.setData({
        entry: res.data.data
      })
      this.getSourceList()
      console.log(res.data);
    })
  },
  getSourceList() {
    let sources = this.data.entry.sources
    let source = {
      videoList: [],
      imageList: [],
      audioList: [],
      fileList: []
    }
    sources.forEach((e) => {
      switch (e.sourceType) {
        case 'image':
          source.imageList.push(e)
          break;
        case 'video':
          source.videoList.push(e)
          break;
        case 'audio':
          source.audioList.push(e)
          break;
        case 'file':
          source.fileList.push(e)
          break;
      }
    })
    this.setData({
      sources: source
    })
  },
  downloadFile(e) {
    const url = e.currentTarget.dataset.url
    wx.downloadFile({
      url: url,
      success(res) {
        console.log("download: ", res.tempFilePath);
        const filePath = res.tempFilePath
        wx.openDocument({
          filePath: filePath,
          success(res) {
            console.log('打开文档成功', res);
          },
          fail(err) {
            console.log('打开文档失败', err);
          }
        })
      }
    })
  },
  getComments(current, pageSize) {
    Comment.getCommentList({
      current,
      pageSize,
      entryId: this.data.entryId
    }).then((res) => {
      console.log(res.data);
      this.setData({
        commentList: [...this.data.commentList, ...res.data.data.records]
      })
    })
  },
  showComment(e) {
    let commentId = e.currentTarget.dataset.comment_id
    if (commentId) {
      this.setData({
        [`form.commentType`]: 2,
        [`form.replyId`]: commentId
      })
    } else {
      this.setData({
        [`form.commentType`]: 1
      })
    }
    this.setData({
      [`form.entryId`]: this.data.entryId,
      commentVisiable: true
    })
  },
  closeMask() {
    this.setData({
      commentVisiable: false,
      comment_form: {}
    })
  },
  onbindfocus(e) {
    this.setData({
      bottom: e.detail.height,
    })
  },
  onbindblur() {
    this.setData({
      bottom: 0
    })
  },
  changeContent(e) {
    this.setData({
      [`form.content`]: e.detail.value
    })
  },
  submitComment() {
    Comment.addComment(this.data.form).then(res => {
      if (res.data.code == 0) {
        showMessage.success('评论成功')
        this.setData({
          commentList: [],
          current: 1,
          pageSize: 10,
          commentVisiable: false
        })
        this.getComments(1,10)
      }
    })
  }
})