<template>
  <div class="user-center-container">
    <div class="inner-conatinter">
      <a-form :model="userForm" :style="{ width: '400px' }" @submit="handleSubmit">
        <a-form-item field="userName" label="昵称">
          <a-input v-model="userForm.userName" placeholder="请输入昵称"/>
        </a-form-item>
        <a-form-item field="gender" label="性别">
          <a-select v-model="userForm.gender">
            <a-option :value="1">男</a-option>
            <a-option :value="2">女</a-option>
            <a-option :value="3">保密</a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="userAvatar" label="头像">
          <a-upload :action="`${OpenAPI.BASE}/api/file/upload`" :data="{ business: 'user_avatar' }"
                    :file-list="fileList.list" :limit="1"
                    :with-credentials="true" image-preview list-type="picture-card" @success="uploadSuccessHandle"/>
        </a-form-item>
        <a-form-item>
          <a-button html-type="submit" type="primary">提交</a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>
<script lang="ts" setup>
import {type UserVO, UserControllerService as userService, OpenAPI} from '@/api';
import {message} from '@/utils/message';
import type {FileItem} from '@arco-design/web-vue';
import {onMounted, reactive} from 'vue';

const userForm = reactive({} as UserVO)
const fileList = reactive({
  list: []
})
const handleSubmit = () => {
  userService.updateUserUsingPut1(userForm).then((res) => {
    if (res.code == 0) {
      message.success('更新成功')
      getUserInfo()
    } else {
      message.error(res.message)
    }
  })
}
onMounted(() => {
  getUserInfo()
})
const getUserInfo = () => {
  userService.getLoginUserUsingGet().then((res) => {
    if (res.code == 0) {
      userForm.gender = res.data?.gender
      userForm.userName = res.data?.userName
      userForm.userAvatar = res.data?.userAvatar
      fileList.list = [{
        uid: '1',
        name: 'avatar.jpg',
        url: res.data?.userAvatar
      }] as any
    }
  })
}
const uploadSuccessHandle = (file: FileItem) => {
  const url = file.response.data
  userForm.userAvatar = url
}
</script>
<style lang="scss" scoped>
.user-center-container {
  width: 100%;
  height: 95%;
  max-height: 95%;
  margin-top: 20px;

  .inner-conatinter {
    width: 100%;
    height: 100%;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: rgba(0, 0, 0, 0.05) 0px 1px 2px 0px;
    padding-top: 40px;
  }
}
</style>