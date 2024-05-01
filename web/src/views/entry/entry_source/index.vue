<template>
    <div class="enrty-source-conatiner">
        <div class="button-area">
            <a-button type="primary" @click="addModalVisible = true">上传附件</a-button>
        </div>
        <a-table :columns="columns as any" :data="data.sourceList" :pagination="false" @page-change="pageChangeHandle">
            <template #optional="{ record }">
                <a-button type="primary" status="danger" @click="deleteSourceHandle(record.id)">删除</a-button>
            </template>
        </a-table>
        <!--  -->
        <a-modal v-model:visible="addModalVisible" title="上传附件" @cancel="addModalVisible = false" @ok="addSourceHandle">
            <a-form :model="sourceForm">
                <a-form-item field="sourceName" label="附件名称">
                    <a-input v-model="sourceForm.sourceName" />
                </a-form-item>
                <a-form-item field="sourceType" label="附件类型">
                    <a-select v-model="sourceForm.sourceType">
                        <a-option value="image">图片</a-option>
                        <a-option value="video">视频</a-option>
                        <a-option value="audio">音频</a-option>
                        <a-option value="file">文件</a-option>
                    </a-select>
                </a-form-item>
                <a-form-item field="sourceUrl" label="附件上传">
                    <a-space direction="vertical" :style="{ width: '100%' }">
                        <a-upload :action="`${OpenAPI.BASE}/api/file/upload`" :with-credentials="true"
                            :data="{ business: `entry_${sourceForm.sourceType}` }" :limit="1"
                            @success="uploadSuccessHandle" multiple />
                    </a-space>
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>
<script setup lang="ts">
import { EntryControllerService as entryService, type EntrySource, OpenAPI } from '@/api';
import { message } from '@/utils/message';
import type { FileItem, TableColumnData, TableData } from '@arco-design/web-vue';
import { onMounted, reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
const query = useRoute().query
const addModalVisible = ref(false)
const data = reactive({
    sourceList: [] as EntrySource[],
    totle: 0 as number,
    current: 1 as number,
    size: 10 as number
});
const sourceForm = reactive({
    entryId: query.id as any
} as EntrySource)
const columns = [
    {
        title: '附件ID',
        dataIndex: 'id',
        width: 120
    },
    {
        title: '附件名称',
        dataIndex: 'sourceName',
        width: 200
    },
    {
        title: '附件类型',
        dataIndex: 'sourceType',
        width: 120,
        render: (
            record: TableData,
            column: TableColumnData,
            rowIndex: number
        ) => {
            const sourceType = record.record.sourceType
            switch (sourceType) {
                case 'image':
                    return '图片'
                case 'video':
                    return '视频'
                case 'file':
                    return '文件'
                case 'audio':
                    return '音频'
            }
        }
    },
    {
        title: '附件url',
        dataIndex: 'sourceUrl',
    },
    {
        title: '操作',
        slotName: 'optional'
    }
];
onMounted(() => {
    getSourceList()
})
const pageChangeHandle = (page: number) => {
    data.current = page
    getSourceList()
}
const getSourceList = () => {
    entryService.getEntryByIdUsingGet(sourceForm.entryId as number).then((res) => {
        if (res.code == 0) {
            data.sourceList = res.data?.sources as EntrySource[]
        }
    })
}
const addSourceHandle = () => {
    entryService.addEntrySourceUsingPost(sourceForm).then((res) => {
        if (res.code == 0) {
            getSourceList()
            message.success('添加附件成功')
        }
    })
}
const uploadSuccessHandle = (file: FileItem) => {
    const url = file.response.data
    sourceForm.sourceUrl = url
    message.success('上传成功')
}
const deleteSourceHandle = (id: number) => {
    entryService.removeEntrySourceUsingDelete(id).then((res) => {
        if (res.code == 0) {
            getSourceList()
            message.success('删除成功')
        } else {
            message.error(res.message as string)
        }
    })

}
</script>
<style scoped lang="scss">
.enrty-source-conatiner {
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