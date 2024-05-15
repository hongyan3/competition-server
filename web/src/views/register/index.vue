<template>
  <div class="login-form">
    <div class="title">竞赛展示系统管理后台</div>
    <div class="form">
      <a-form :model="form" :style="{ width: '600px' }" @submit="handleRegister">
        <a-form-item field="username" label="昵称">
          <a-input v-model="form.userName" :style="{ width: '250px' }" placeholder="请输入昵称"/>
        </a-form-item>
        <a-form-item field="useraccount" label="账户">
          <a-input v-model="form.userAccount" :style="{ width: '250px' }" placeholder="请输入账户"/>
        </a-form-item>
        <a-form-item field="password" label="密码">
          <a-input v-model="form.userPassword" :style="{ width: '250px' }" placeholder="请输入密码" type="password"/>
        </a-form-item>
        <a-form-item field="confirmpassword" label="确认密码">
          <a-input v-model="form.checkPassword" :style="{ width: '250px' }" placeholder="请输入密码" type="password"/>
        </a-form-item>
        <a-form-item>
          <a-button html-type="submit" type="primary">注册</a-button>
          <RouterLink class="register-tip" to="/login">已有账号？去登录</RouterLink>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login-form {
  width: 700px;
  height: 400px;
  background-color: #ffffff;
  box-shadow: rgba(99, 99, 99, 0.2) 0 2px 8px 0;
  margin-bottom: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  border-radius: 5px;

  .title {
    width: 100%;
    height: 20%;
    background-color: #42b883;
    color: #ffffff;
    display: flex;
    align-items: center;
    font-size: large;
    padding-left: 30px;
    border-radius: 5px;
  }

  .form {
    width: 100%;
    height: 80%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
}

.register-tip {
  margin-left: 15px;
  font-size: smaller;
}
</style>

<script lang="ts" setup>
import {reactive} from 'vue';
import {Message} from "@arco-design/web-vue";
import {UserControllerService as userService} from '@/api'
import router from "@/router";

const form = reactive({
  userAccount: '',
  userPassword: '',
  checkPassword: '',
  userName: ''
})
const handleRegister = () => {
  if (form.userPassword != form.checkPassword) {
    Message.warning('两次输入的密码不一致')
    return
  }
  userService.userRegisterUsingPost(form).then((res) => {
    console.log(res)
    if (res.code == 0) {
      Message.success('注册成功，请登录')
      setTimeout(() => {
        router.push({
          path: '/login'
        })
      }, 1000)
    } else {
      Message.error(res.message)
    }
  })
}
</script>