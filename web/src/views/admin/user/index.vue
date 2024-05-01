<template>
    <div class="enrty-member-conatiner">
        <div class="button-area">
            <a-button type="primary" @click="addModalVisible = true">添加用户</a-button>
        </div>
        <a-table :columns="columns" :data="data.userList" :pagination="{
            pageSize: data.size,
            total: data.total
        }">
            <template #optional="{ record }">
                <a-button type="primary" status="danger" @click="deleteUserHandle(record.id)">删除</a-button>
            </template>
        </a-table>
        <!--  -->
        <a-modal v-model:visible="addModalVisible" title="添加成员" @cancel="addModalVisible = false" @ok="addUserHandle">
            <a-form :model="userForm">
                <a-form-item field="userName" label="昵称">
                    <a-input placeholder="请输入昵称" v-model="userForm.userName"></a-input>
                </a-form-item>
                <a-form-item field="userAccount" label="账号">
                    <a-input placeholder="请输入账号" v-model="userForm.userAccount"></a-input>
                </a-form-item>
                <a-form-item field="password" label="密码">
                    <a-input placeholder="请输入密码" v-model="userForm.password" type="password"></a-input>
                </a-form-item>
                <a-form-item field="role" label="角色">
                    <a-select v-model="userForm.role">
                        <a-option value="1">普通用户</a-option>
                        <a-option value="2">管理员</a-option>
                    </a-select>
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>
<script setup lang="ts">
import {
    EntryControllerService as entryService,
    OpenAPI, type UserVO,
    UserAdminControllerService as userService,
} from '@/api';
import { message } from '@/utils/message';
import type { TableColumnData, TableData } from '@arco-design/web-vue/es/table';
import { onMounted, reactive, ref } from 'vue';
const addModalVisible = ref(false)
const data = reactive({
    userList: [] as UserVO[],
    total: 0 as number,
    current: 1 as number,
    size: 10 as number
});
const userForm = reactive({} as (UserVO & { password: string }))
const columns = [
    {
        title: '用户ID',
        dataIndex: 'id',
        width: 100
    },
    {
        title: '用户昵称',
        dataIndex: 'userName',
        width: 150
    },
    {
        title: '用户账户',
        dataIndex: 'userAccount',
        width: 150,
    },
    {
        title: '用户头像',
        dataIndex: 'userAvatar',
        width: 300,
    },
    {
        title: '用户性别',
        dataIndex: 'gender',
        width: 100,
        render: (
            record: TableData,
            column: TableColumnData,
            rowIndex: number
        ) => {
            const gender = record.record.gender
            switch (gender) {
                case 1:
                    return '男'
                case 2:
                    return '女'
                case 3:
                    return '保密'
                default:
                    return '未填写';
            }
        }
    },
    {
        title: '用户状态',
        dataIndex: 'status',
        width: 100,
        render: (
            record: TableData,
            column: TableColumnData,
            rowIndex: number
        ) => {
            const status = record.record.status
            switch (status) {
                case 0:
                    return '禁用'
                case 1:
                    return '正常'
            }
        }
    },
    {
        title: '用户角色',
        dataIndex: 'role',
        width: 100,
        render: (
            record: TableData,
            column: TableColumnData,
            rowIndex: number
        ) => {
            const role = record.record.role
            switch (role) {
                case 1:
                    return '普通用户'
                case 2:
                    return '管理员'
            }
        }
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        width: 200
    },
    {
        title: '更新时间',
        dataIndex: 'updateTime',
        width: 200
    },
    {
        title: '操作',
        slotName: 'optional'
    }
] as any[];
onMounted(() => {
    getUserList()
})
const getUserList = () => {
    userService.userListUsingGet().then((res) => {
        if (res.code == 0) {
            data.userList = res.data?.records as UserVO[]
            data.total = res.data?.total as number
        }
    })
}
const addUserHandle = () => {
    userService.addUserUsingPost(userForm).then((res) => {
        if (res.code == 0) {
            getUserList()
            message.success('添加成功')
        } else {
            message.error(res.message)
        }
    })
}
const deleteUserHandle = (id: number) => {
    // todo 
}
</script>
<style scoped lang="scss">
.enrty-member-conatiner {
    width: 100%;
    max-height: 95%;
    margin-top: 20px;

    .button-area {
        width: 100%;
        height: 50px;
        display: flex;
        align-items: center;
    }
}
</style>