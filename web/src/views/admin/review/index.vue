<template>
  <div class="enrty-list-conatiner">
    <a-table :columns="columns" :data="data.entryList" :pagination="{
      total: data.totle,
      pageSize: data.size,
      showTotal: true
    }" @page-change="pageChangeHandle">
      <template #optional="{ record }">
        <a-button type="primary" @click="toFileSourceHandle(record.id)">附件</a-button>
        <a-button style="margin-left: 5px;" type="primary" @click="toMemberHandle(record.id)">成员</a-button>
        <a-button style="margin-left: 5px;" type="primary" @click="showUpdateModal(record)">审核</a-button>
      </template>
    </a-table>
    <!-- 修改模态框 -->
    <a-modal v-model:visible="updateModalVisible" title="审核" @cancel="updateModalVisible = false"
      @ok="updateEntryHandle">
      <a-form :model="data.entryForm">
        <a-form-item field="id" label="作品ID">
          <a-input v-model="data.entryForm.id as any" disabled placeholder="作品ID" />
        </a-form-item>
        <a-form-item field="status" label="审核状态">
          <a-select v-model="data.entryForm.status" placeholder="请选择审核状态" @click="getCollegeList">
            <a-option :value="0">待审核</a-option>
            <a-option :value="1">审核通过</a-option>
            <a-option :value="2">审核不通过</a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="statusRemarks" label="审核备注">
          <a-input v-model="data.entryForm.statusRemarks" placeholder="请输入审核备注" />
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
import { onMounted, reactive, ref } from 'vue';
import { EntryAdminControllerService as entryAdminService, type EntryVO, OpenAPI, CollegeControllerService as collegeService, type CollegeVO } from '@/api'
import { message } from '@/utils/message';
import router from '@/router';
import { request } from '@/api/core/request';
import type { TableColumnData, TableData } from '@arco-design/web-vue';

const columns = [
  {
    title: '作品ID',
    dataIndex: 'id',
    width: 100
  },
  {
    title: '作品名称',
    dataIndex: 'entryName',
    width: 200
  },
  {
    title: '作品描述',
    dataIndex: 'description',
    width: 200,
    ellipsis: true
  },
  {
    title: '审核状态',
    dataIndex: 'status',
    width: 120,
    render: (
      record: TableData,
      column: TableColumnData,
      rowIndex: number
    ) => {
      const status = record.record.status
      switch (status) {
        case 0:
          return '审核中'
        case 1:
          return '审核通过'
        case 2:
          return '审核不通过'
      }
    }
  },
  {
    title: '审核备注',
    dataIndex: 'statusRemarks',
    width: 200
  },
  {
    title: '参赛学院',
    dataIndex: 'collegeName',
    width: 200
  },
  {
    title: '操作',
    slotName: 'optional',
    width: 300
  }
] as any[];
const data = reactive({
  entryList: [] as EntryVO[],
  collegeList: [] as CollegeVO[],
  entryForm: {} as EntryVO,
  totle: 0 as number,
  current: 1 as number,
  size: 10 as number
});
let addModalVisible = ref(false)
let updateModalVisible = ref(false)
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
const getCollegeList = async () => {
  collegeService.getCollegeListUsingGet().then((res) => {
    if (res.code == 0) {
      data.collegeList = res.data?.records as CollegeVO[]
    }
  })
}
const addEntryHandle = () => {
  entryAdminService.addEntryUsingPost(data.entryForm).then((res) => {
    if (res.code == 0) {
      message.success('添加作品成功')
      getEntryList()
      addModalVisible.value = false
    } else {
      message.error(res.message)
    }
  })
}
const updateEntryHandle = () => {
  entryAdminService.updateEntryUsingPut(data.entryForm).then((res) => {
    if (res.code == 0) {
      message.success('更新作品成功')
      getEntryList()
      addModalVisible.value = false
    } else {
      message.error(res.message)
    }
  })
}
const showUpdateModal = (entry: EntryVO) => {
  updateModalVisible.value = true
  data.entryForm = entry
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
  getCollegeList()
})
</script>