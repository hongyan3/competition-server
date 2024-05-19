import request from './request'
import showMessage from '../components/showMessage'

export let User = {
    login(data) {
        return request({
            url: '/api/user/login',
            method: 'POST',
            data
        })
    },
    logout() {
        return request({
            url: '/api/user/logout',
            method: 'POST'
        })
    },
    getLoginUser() {
        return request({
            url: '/api/user/login'
        })
    },
    updateUser(data) {
        return request({
            url: '/api/user',
            method: 'PUT',
            data
        })
    }
}

export let File = {
    uploadFile(filePath) {
        return new Promise((resolve, reject) => {
            wx.uploadFile({
                filePath: filePath,
                name: 'file',
                url: 'http://127.0.0.1:8080/api/file/upload',
                formData: {
                    'business': 'user_avatar'
                },
                header: {
                    'Cookie': wx.getStorageSync('Cookie'),
                    'Content-Type': 'multipart/form-data'
                },
                success: (res) => {
                    res = JSON.parse(res.data)
                    resolve(res)
                },
                fail: (err) => {
                    showMessage.error('请求失败')
                    reject(err)
                }
            })
        })
    }
}

export let Entry = {
    getEntryList(options = {
        pageSize: 10,
        current: 1,
        searchValue: ''
    }) {
        return request({
            url: options.searchValue != '' && options.searchValue != undefined ? `/api/entry?current=${options.current}&pageSize=${options.pageSize}&entryName=${options.searchValue}&description=${options.searchValue}` : `/api/entry?current=${options.current}&pageSize=${options.pageSize}&status=1`,
        })
    },
    getEntryById(id) {
        return request({
            url: '/api/entry/' + id
        })
    }
}
export let Comment = {
    getCommentList(options = {
        entryId: 0,
        current: 1,
        pageSize: 10,
        commentType: 1
    }) {
        return request({
            url: `/api/entry/comment?entryId=${options.entryId}&current=${options.current}&pageSize=${options.pageSize}&sortField=create_time&sortOrer=DESC&commentType=${options.commentType}`,
        })
    },
    addComment(form) {
        return request({
            url: '/api/entry/comment',
            method: 'POST',
            data: form
        })
    }
}