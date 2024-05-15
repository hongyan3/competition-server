<template>
  <div class="enrty-member-conatiner">
    <div class="button-area">
      <a-button type="primary" @click="addModalVisible = true">添加成员</a-button>
    </div>
    <a-table :columns="columns" :data="data.memberList" :pagination="false">
      <template #optional="{ record }">
        <a-button status="danger" type="primary" @click="deleteSourceHandle(record.id)">删除</a-button>
      </template>
    </a-table>
    <!--  -->
    <a-modal v-model:visible="addModalVisible" title="添加成员" @cancel="addModalVisible = false" @ok="addMemberHandle">
      <a-form :model="memberForm">
        <a-form-item field="sourceName" label="昵称/账户">
          <a-dropdown>
            <a-input v-model="memberForm.memberId" :style="{ width: '320px' }" allow-clear
                     placeholder="请输入用户昵称" @input="searchUser"/>
            <template #content>
              <a-doption v-for="(item, index) in data.userList" :style="{width: '320px'}"
                         @click="selectUserHandle(item.id as number)">
                <span>{{ item.userName }}</span>
                <span style="margin-left: 15px;">{{ item.userAccount }}</span>
              </a-doption>
            </template>
          </a-dropdown>

        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script lang="ts" setup>
import {EntryControllerService as entryService, OpenAPI, type UserVO,} from '@/api';
import {request} from '@/api/core/request';
import {message} from '@/utils/message';
import type {TableColumnData, TableData} from '@arco-design/web-vue/es/table';
import {onMounted, reactive, ref} from 'vue';
import {useRoute} from 'vue-router';

const query = useRoute().query
const addModalVisible = ref(false)
const data = reactive({
  memberList: [] as UserVO[],
  userList: [] as UserVO[]
});
const memberForm = reactive({
  entryId: query.id as any
} as {
  entryId: string,
  memberId: string
})
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
    title: '用户性别',
    dataIndex: 'gender',
    width: 150,
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
    title: '创建时间',
    dataIndex: 'createTime',
    width: 300
  },
  {
    title: '更新时间',
    dataIndex: 'updateTime',
    width: 300
  },
  {
    title: '操作',
    slotName: 'optional'
  }
] as any[];
onMounted(() => {
  getMemberList()
})
const getMemberList = () => {
  entryService.getEntryByIdUsingGet(parseInt(memberForm.entryId)).then((res) => {
    if (res.code == 0) {
      data.memberList = res.data?.members as UserVO[]
    }
  })
}
const addMemberHandle = () => {
  entryService.addEntryMemberUsingPost({
    entryId: parseInt(memberForm.entryId),
    memberId: parseInt(memberForm.memberId)
  }).then((res) => {
    if (res.code == 0) {
      getMemberList()
      message.success('添加成员成功')
    } else {
      message.error(res.message)
    }
  })
}
const deleteSourceHandle = (id: number) => {
  // todo 修改删除参数
  entryService.removeEntryMemberUsingDelete({
    memberId: id,
    entryId: parseInt(memberForm.entryId)
  }).then((res) => {
    if (res.code == 0) {
      getMemberList()
      message.success('删除成功')
    } else {
      message.error(res.message as string)
    }
  })

}

const searchUser = (value: string) => {
  return request(OpenAPI, {
    method: 'GET',
    url: '/api/user',
    query: {
      userName: value,
    }
  }).then((res: any) => {
    data.userList = res.data.records
  })
}
const selectUserHandle = (value: number) => {
  memberForm.memberId = value.toString()
}
</script>
<style lang="scss" scoped>
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