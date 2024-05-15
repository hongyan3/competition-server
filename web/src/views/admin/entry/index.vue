<template>
  <div class="enrty-list-conatiner">
    <div class="button-area">
      <a-button type="primary" @click="addModalVisible = true">创建作品</a-button>
    </div>
    <a-table :columns="columns" :data="data.entryList" :pagination="{
            total: data.totle,
            pageSize: data.size,
            showTotal: true
        }" @page-change="pageChangeHandle">
      <template #optional="{ record }">
        <a-button type="primary" @click="toFileSourceHandle(record.id)">附件</a-button>
        <a-button style="margin-left: 5px;" type="primary" @click="toMemberHandle(record.id)">成员</a-button>
        <a-button status="danger" style="margin-left: 5px;" type="primary"
                  @click="deleteEntryHandle(record.id)">删除
        </a-button>
      </template>
    </a-table>
    <!--  -->
    <a-modal v-model:visible="addModalVisible" title="创建作品" @cancel="addModalVisible = false" @ok="addEntryHandle">
      <a-form :model="entryForm">
        <a-form-item field="entryName" label="作品名称">
          <a-input v-model="entryForm.entryName"/>
        </a-form-item>
        <a-form-item field="description" label="作品描述">
          <a-textarea v-model="entryForm.description"/>
        </a-form-item>
        <a-form-item field="college" label="参赛学院">
          <a-input v-model="entryForm.college"/>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<style lang="scss" scoped>
.enrty-list-conatiner {
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
<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue';
import {EntryAdminControllerService as entryAdminService, type EntryVO, OpenAPI,} from '@/api'
import {message} from '@/utils/message';
import router from '@/router';
import {request} from '@/api/core/request';

const columns = [
  {
    title: '作品ID',
    dataIndex: 'id',
  },
  {
    title: '作品名称',
    dataIndex: 'entryName',
  },
  {
    title: '作品描述',
    dataIndex: 'description',
  },
  {
    title: '参赛学院',
    dataIndex: 'college',
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
  },
  {
    title: '更新时间',
    dataIndex: 'updateTime',
  },
  {
    title: '操作',
    slotName: 'optional'
  }
];
const data = reactive({
  entryList: [] as EntryVO[],
  totle: 0 as number,
  current: 1 as number,
  size: 10 as number
});
const entryForm = reactive({} as EntryVO)
const addModalVisible = ref(false)
const getEntryList = async () => {

  return request(OpenAPI, {
    method: 'GET',
    url: '/api/entry',
    query: {
      current: data.current,
      pageSize: data.size
    }
  }).then((res: any) => {
    data.entryList = res.data.records
    data.current = res.data?.current as number
    data.size = res.data?.size as number
    data.totle = res.data?.total as number
  })
}
const addEntryHandle = () => {
  entryAdminService.addEntryUsingPost(entryForm).then((res) => {
    if (res.code == 0) {
      message.success('添加作品成功')
      getEntryList()
      addModalVisible.value = false
    } else {
      message.error(res.message)
    }
  })
}
const pageChangeHandle = (page: number) => {
  data.current = page
  getEntryList()
}
const toFileSourceHandle = (id: number) => {
  router.push({
    path: '/entry_source',
    query: {
      id,
    }
  })
}
const toMemberHandle = (id: number) => {
  router.push({
    path: '/entry_member',
    query: {
      id,
    }
  })
}
const deleteEntryHandle = (id: number) => {
  entryAdminService.deleteEntryUsingDelete(id).then((res) => {
    if (res.code == 0) {
      getEntryList()
      message.success('删除成功')
    } else {
      message.error(res.message as string)
    }
  })
}
onMounted(() => {
  getEntryList()
})
</script>