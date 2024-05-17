<template>
  <div class="enrty-list-conatiner">
    <div class="button-area">
      <a-button type="primary" @click="addModalVisible = true">创建学院</a-button>
    </div>
    <a-table :columns="columns" :data="data.collegeList" :pagination="{
      total: data.totle,
      pageSize: data.size,
      showTotal: true
    }" @page-change="pageChangeHandle">
      <template #optional="{ record }">
        <a-button style="margin-left: 5px;" type="primary" @click="showUpdateModal(record)">修改</a-button>
        <a-button status="danger" style="margin-left: 5px;" type="primary" @click="deleteCollegeHandle(record.id)">删除
        </a-button>
      </template>
    </a-table>
    <!-- 新建模态框 -->
    <a-modal v-model:visible="addModalVisible" title="创建学院" @cancel="addModalVisible = false" @ok="addEntryHandle">
      <a-form :model="data.collegeForm">
        <a-form-item field="collegeName" label="学院名称">
          <a-input v-model="data.collegeForm.collegeName" placeholder="请输入学院名称" />
        </a-form-item>
      </a-form>
    </a-modal>
    <!-- 修改模态框 -->
    <a-modal v-model:visible="updateModalVisible" title="修改学院" @cancel="updateModalVisible = false"
      @ok="updateCollegeHandle">
      <a-form :model="data.collegeForm">
        <a-form-item field="collegeName" label="学院名称">
          <a-input v-model="data.collegeForm.collegeName" placeholder="请输入学院名称" />
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
import { CollegeAdminControllerService as collegeAdminService, type EntryVO, OpenAPI, CollegeControllerService as collegeService, type CollegeVO } from '@/api'
import { message } from '@/utils/message';
import router from '@/router';
import { request } from '@/api/core/request';
import type { TableColumnData, TableData } from '@arco-design/web-vue';

const columns = [
  {
    title: '学院ID',
    dataIndex: 'id',
    width: 100
  },
  {
    title: '学院名称',
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
    width: 200
  }
] as any[];
const data = reactive({
  collegeList: [] as CollegeVO[],
  collegeForm: {} as CollegeVO,
  totle: 0 as number,
  current: 1 as number,
  size: 10 as number
});
let addModalVisible = ref(false)
let updateModalVisible = ref(false)
const getCollegeList = async () => {
  return request(OpenAPI, {
    method: 'GET',
    url: '/api/college',
    query: {
      current: data.current,
      pageSize: data.size
    }
  }).then((res: any) => {
    data.collegeList = res.data.records
    data.current = res.data?.current as number
    data.size = res.data?.size as number
    data.totle = res.data?.total as number
  })
}
const addEntryHandle = () => {
  collegeAdminService.addCollegeUsingPost(data.collegeForm).then((res) => {
    if (res.code == 0) {
      message.success('添加作品成功')
      getCollegeList()
      addModalVisible.value = false
    } else {
      message.error(res.message)
    }
  })
}
const updateCollegeHandle = () => {
  collegeAdminService.updateCollegeUsingPut(data.collegeForm).then((res) => {
    if (res.code == 0) {
      message.success('更新作品成功')
      getCollegeList()
      addModalVisible.value = false
    } else {
      message.error(res.message)
    }
  })
}
const showUpdateModal = (entry: EntryVO) => {
  updateModalVisible.value = true
  data.collegeForm = entry
}
const pageChangeHandle = (page: number) => {
  data.current = page
  getCollegeList()
}
const deleteCollegeHandle = (id: number) => {
  collegeAdminService.deleteCollegeUsingDelete(id).then((res) => {
    if (res.code == 0) {
      message.success('删除成功')
      getCollegeList()
    } else {
      message.error(res.message as string)
    }
  })
}
onMounted(() => {
  getCollegeList()
})
</script>