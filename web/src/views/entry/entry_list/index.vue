<template>
  <div class="enrty-list-conatiner">
    <div class="button-area">
      <a-button type="primary" @click="addModalVisible = true">创建作品</a-button>
    </div>
    <a-table :columns="columns" :data="data.entryList" :pagination="{
      total: data.totle,
      pageSize: data.size,
      showTotal: true
    }" @page-change="pageChangeHandle" @row-dblclick="toEntryInfoPage">
      <template #optional="{ record }">
        <a-button type="primary" @click="toFileSourceHandle(record.id)">附件</a-button>
        <a-button style="margin-left: 5px;" type="primary" @click="toMemberHandle(record.id)">成员</a-button>
        <a-button style="margin-left: 5px;" type="primary" @click="showUpdateModal(record)">修改</a-button>
        <a-button status="danger" style="margin-left: 5px;" type="primary" @click="deleteEntryHandle(record.id)">删除
        </a-button>
      </template>
    </a-table>
    <!--  -->
    <a-modal v-model:visible="addModalVisible" title="创建作品" @cancel="addModalVisible = false" @ok="addEntryHandle">
      <a-form :model="data.entryForm">
        <a-form-item field="entryName" label="作品名称">
          <a-input v-model="data.entryForm.entryName" />
        </a-form-item>
        <a-form-item field="description" label="作品描述">
          <a-textarea v-model="data.entryForm.description" />
        </a-form-item>
        <a-form-item field="collegeId" label="参赛学院">
          <a-select v-model="data.entryForm.collegeId" placeholder="请选择参赛学院" @click="getCollegeList">
            <a-option v-for="college in data.collegeList" :value="college.id">{{ college.collegeName }}</a-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
    <!-- 修改模态框 -->
    <a-modal v-model:visible="updateModalVisible" title="修改作品" @cancel="updateModalVisible = false"
      @ok="updateEntryHandle">
      <a-form :model="data.entryForm">
        <a-form-item field="entryName" label="作品名称">
          <a-input v-model="data.entryForm.entryName" placeholder="请输入作品名称" />
        </a-form-item>
        <a-form-item field="description" label="作品描述">
          <a-textarea v-model="data.entryForm.description" placeholder="请输入作品描述" />
        </a-form-item>
        <a-form-item field="collegeId" label="参赛学院">
          <a-select v-model="data.entryForm.collegeId" placeholder="请选择参赛学院" @click="getCollegeList">
            <a-option v-for="college in data.collegeList" :value="college.id">{{ college.collegeName }}</a-option>
          </a-select>
          <!-- <a-input v-model="entryForm.collegeName"/> -->
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
import { EntryControllerService as entryService, type EntryVO, UserControllerService as userService, CollegeControllerService as collegeService, type CollegeVO } from '@/api'
import { message } from '@/utils/message';
import router from '@/router';
import { title } from 'process';
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
const getEntryList = () => {
  userService.getUserEntryListUsingGet(
    data.current,
    data.size
  ).then((res) => {
    if (res.code == 0) {
      data.entryList = res.data?.records as EntryVO[]
      data.current = res.data?.current as number
      data.size = res.data?.size as number
      data.totle = res.data?.total as number
    }
  })
}
const getCollegeList = async () => {
  collegeService.getCollegeListUsingGet().then((res) => {
    if (res.code == 0) {
      data.collegeList = res.data?.records as CollegeVO[]
    }
  })
}
const updateEntryHandle = () => {
  entryService.editEntryUsingPut(data.entryForm).then((res) => {
    if (res.code == 0) {
      message.success('编辑作品成功')
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
const addEntryHandle = () => {
  entryService.addEntryUsingPost1(data.entryForm).then((res) => {
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
const toEntryInfoPage = (record: TableData, ev: Event) => {
  const entryId = record.id
  router.push({
    path: '/entry_info',
    query: {
      entryId,
    }
  })
}
const deleteEntryHandle = (id: number) => {
  entryService.removeEntryUsingDelete(id).then((res) => {
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