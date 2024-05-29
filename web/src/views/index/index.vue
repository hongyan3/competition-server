<template>
  <div class="container">
    <a-layout :style="{ height: '100%' }">
      <a-layout-sider :collapsed="collapsed" :width="220" breakpoint="lg" collapsible @collapse="onCollapse">

        <a-menu :defaultOpenKeys="['1']" :defaultSelectedKeys="['0_2']" @menuItemClick="onClickMenuItem">
          <!-- <a-menu-item key="/home">
            <IconHome />
            首页
          </a-menu-item> -->
          <a-menu-item key="/entry_list">
            <IconCodeSquare/>
            作品管理
          </a-menu-item>
          <a-menu-item key="/user_center">
            <IconUser/>
            个人中心
          </a-menu-item>
          <a-sub-menu v-if="isAdmin" key="/admin">
            <template #icon>
              <IconPublic/>
            </template>
            <template #title>管理</template>
            <a-menu-item key="/admin/user">用户管理</a-menu-item>
            <a-menu-item key="/admin/entry">作品管理</a-menu-item>
            <a-menu-item key="/admin/review">作品审核</a-menu-item>
            <a-menu-item key="/admin/college">学院管理</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>
      <a-layout>
        <a-layout-header>
          <a-row class="navigation" justify="end">
            <a-col :span="10">
              <div class="logon">
                软件2001 陈铭
              </div>
            </a-col>
            <a-dropdown>
              <a-col :span="3">
                <div class="user-avatar">
                  <img :src="userInfo.userAvatar"/>
                </div>
                <div class="user-name">{{ userInfo.userName }}</div>
              </a-col>
              <template #content>
                <a-doption @click="$router.push({ path: '/user_center' })">
                  <template #icon>
                    <IconUser/>
                  </template>
                  <template #default>个人中心</template>
                </a-doption>
                <a-doption @click="$router.push({ path: '/user_center' })">
                  <template #icon>
                    <IconSettings/>
                  </template>
                  <template #default>用户设置</template>
                </a-doption>
                <a-doption @click="logoutHandle">
                  <template #icon>
                    <IconExport/>
                  </template>
                  <template #default>退出登录</template>
                </a-doption>
              </template>
            </a-dropdown>

          </a-row>
        </a-layout-header>
        <a-layout style="padding: 0 24px">
          <a-layout-content>
            <RouterView/>
          </a-layout-content>
        </a-layout>
      </a-layout>
    </a-layout>
  </div>
</template>
<style lang="scss" scoped>
.container {
  width: 100vw;
  height: 100vh;
  background-color: #f6f6f6;
}

.logo {
  height: 32px;
  margin: 12px 18px;
  display: flex;
  align-items: center;
}

.navigation {
  background-color: #ffffff;
  height: 50px;

  .arco-col {
    display: flex;
    height: 100%;
    width: 100%;

    .user-avatar {
      width: 35%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;

      img {
        height: 70%;
        border-radius: 50%;
      }
    }

    .user-name {
      width: 65%;
      height: 100%;
      display: flex;
      align-items: center;
    }
  }
}
</style>
<script lang="ts" setup>
import {IconCodeSquare, IconExport, IconPublic, IconSettings, IconUser} from '@arco-design/web-vue/es/icon'
import {message} from '@/utils/message';
import {onMounted, reactive, ref} from 'vue';
import {UserControllerService as userService, type UserVO} from '@/api';
import router from '@/router';

const userInfo = reactive({} as UserVO)
const isAdmin = ref(false)
const collapsed = ref(false);
const onCollapse = (val: boolean, type: string) => {
  const content = type === 'responsive' ? '触发响应式收缩' : '点击触发收缩';
  message.info(content);
  collapsed.value = val;
}
const logoutHandle = () => {
  userService.userLogoutUsingPost().then((res) => {
    message.success('退出登录成功')
    setTimeout(() => {
      router.push({
        path: '/login'
      })
    }, 1000);
  })
}
const onClickMenuItem = (path: string) => {
  router.push({
    path,
  })
}
onMounted(() => {
  userService.getLoginUserUsingGet().then((res) => {
    userInfo.userAvatar = res.data?.userAvatar
    userInfo.userName = res.data?.userName
    if (res.data?.role == 2) {
      isAdmin.value = true
    }
  })
})
</script>