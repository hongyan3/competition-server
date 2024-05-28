<template>
  <div class="login-form">
    <div class="title">竞赛展示系统管理后台</div>
    <div class="form">
      <a-form :model="form" :style="{ width: '600px' }" @submit="handleLogin">
        <a-form-item field="name" label="用户名">
          <a-input
            v-model="form.userAccount"
            :style="{ width: '250px' }"
            placeholder="请输入用户名"
          />
        </a-form-item>
        <a-form-item field="post" label="密码">
          <a-input
            v-model="form.userPassword"
            :style="{ width: '250px' }"
            placeholder="请输入密码"
            type="password"
          />
        </a-form-item>
        <a-form-item>
          <a-button html-type="submit" type="primary">登录</a-button>
          <RouterLink class="register-tip" to="/register"
            >还没有账号？去注册</RouterLink
          >
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
import { reactive } from "vue";
import { UserControllerService as userService } from "@/api";
import { message } from "@/utils/message";
import router from "@/router";

const form = reactive({
  userAccount: "",
  userPassword: "",
});

const handleLogin = () => {
  userService.userLoginUsingPost(form).then((res) => {
    if (res.code == 0) {
      message.success("欢迎回来, " + res.data.userName);
      localStorage.setItem("userInfo", res.data);
      setTimeout(() => {
        router.push({
          path: "/",
        });
      }, 1500);
    } else {
      message.error(res.message);
    }
  });
};
</script>