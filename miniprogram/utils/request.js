const baseUrl = "http://127.0.0.1:8080"
import showMessage from '@components/showMessage'
const request = (options) => {
  options.url = options.url || ''
  options.method = options.method || 'GET'
  options.data = options.data || {}
  options.header = options.header || 'application/json'
  return new Promise((resolve, reject) => {
    wx.request({
      url: `${baseUrl}${options.url}`,
      method: options.method,
      data: options.data,
      header: {
        'Cookie': wx.getStorageSync('Cookie') || '',
        'Content-Type': options.header
      },
      success: (res) => {
        if (res.data.code == 40100) {
          showMessage.warning('登录失效')
          setTimeout(()=> {
            wx.navigateTo({
              url: '/pages/login/login',
            })
          },1000)
        } else if (res.data.code !== 0) {
          showMessage.warning(res.data.message)
        }
        resolve(res)
      },
      fail: (err) => {
        showMessage.error('请求失败')
        reject(err)
      },
      complete: () => {

      }
    })
  })
}

export default request