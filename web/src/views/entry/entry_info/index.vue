<template>
  <div class="enrty-info-conatiner">
    <a-row :gutter="20" :style="{ marginTop: '30px' }">
      <a-col :span="14">
        <a-card title="作品信息" :bordered="false">
          <a-typography>
            <a-typography-title :heading="5">
              {{ data.entry?.entryName }}
              <a-tag v-if="data.entry?.status == 0" color="arcoblue">审核中</a-tag>
              <a-tag v-if="data.entry?.status == 2" color="red">审核不通过</a-tag>
              <a-tag v-if="data.entry?.status == 1" color="green">审核通过</a-tag>
            </a-typography-title>
            <a-typography-text style="white-space: pre-wrap;">
              {{ data.entry?.description }}
            </a-typography-text>
            <div class="creator-info">
              <div>第一作者：{{ data.entry?.creatorInfo?.userName }}</div>
              <div>参赛学院：{{ data.entry?.collegeName }}</div>
              <div>提交时间：{{ data.entry?.createTime }}</div>
              <div>更新时间：{{ data.entry?.updateTime }}</div>
            </div>
          </a-typography>
        </a-card>
        <a-card title="评论列表" :bordered="false" :style="{ width: '100%' }">
          <template #extra>
            <a-button type="primary" @click="showCommentModal(undefined)">发表评论</a-button>
          </template>
          <a-comment v-for="comment in data.commentList" :author="comment.userInfo?.userName" :content="comment.content"
            :datetime="comment.createTime">
            <template #actions>
              <span class="action" key="reply" @click="showCommentModal(comment)">
                <IconMessage /> 回复
              </span>
              <span class="action" key="delete" @click="deleteComment(comment.id)"
                v-if="comment.userInfo?.id == data.userInfo.id">
                <IconDelete /> 删除
              </span>
            </template>
            <template #avatar>
              <a-avatar>
                <img alt="avatar" :src="comment.userInfo?.userAvatar" />
              </a-avatar>
            </template>
            <a-comment v-for="reply in comment.replyList" :author="reply.userInfo?.userName" :content="reply.content"
              :datetime="reply.createTime">
              <template #actions>
                <span class="action" key="reply" @click="showCommentModal(comment)">
                  <IconMessage /> 回复
                </span>
                <span class="action" key="delete" @click="deleteComment(reply.id)"
                  v-if="reply.userInfo?.id == data.userInfo.id">
                  <IconDelete /> 删除
                </span>
              </template>
              <template #avatar>
                <a-avatar>
                  <img alt="avatar" :src="reply.userInfo?.userAvatar" />
                </a-avatar>
              </template>
            </a-comment>
          </a-comment>
          <div class="loadingtext" @click="loadMoreHandle" v-if="commentPage.totle > data.commentList.length">加载更多</div>
        </a-card>
      </a-col>
      <a-col :span="10">
        <a-card title="附件列表" :bordered="false" :style="{ width: '100%' }">
          <a-list :bordered="false" v-if="source.imageList.length != 0">
            <template #header>
              <div style="font-size: 14px;font-weight: 600;">图片附件</div>
            </template>
            <a-list-item v-for="image in source.imageList">
              <div class="sourceName">{{ image.sourceName }}</div>
              <img alt="image" :src="image.sourceUrl" style="width: 70%;"/>
            </a-list-item>
          </a-list>
          <a-list :bordered="false" v-if="source.videoList.length != 0">
            <template #header>
              <div style="font-size: 14px;font-weight: 600;">视频附件</div>
            </template>
            <a-list-item v-for="video in source.videoList">
              <div class="sourceName">{{ video.sourceName }}</div>
              <video :src="video.sourceUrl" style="width: 70%;" controls></video>
            </a-list-item>
          </a-list>
          <a-list :bordered="false" v-if="source.audioList.length != 0">
            <template #header>
              <div style="font-size: 14px;font-weight: 600;">音频附件</div>
            </template>
            <a-list-item v-for="audio in source.audioList">
              <div class="sourceName">{{ audio.sourceName }}</div>
              <audio ref="audioPlayer" :src="audio.sourceUrl" controls></audio>
            </a-list-item>
          </a-list>
          <a-list :bordered="false" v-if="source.fileList.length != 0">
            <template #header>
              <div style="font-size: 14px;font-weight: 600;">文件附件</div>
            </template>
            <a-list-item v-for="file in source.fileList">
              <a-link :href="file.sourceUrl" icon>{{file.sourceName}}</a-link>
            </a-list-item>
          </a-list>
        </a-card>
        <a-card title="成员列表" :bordered="false" :style="{ width: '100%' }">
          <a-list :bordered="false">
            <a-list-item v-for="user in data.entry?.members" :key="user.id">
              <a-list-item-meta :title="user.userName"
                :description="user.id == data.entry?.creatorInfo?.id ? '第一作者' : '成员'">
                <template #avatar>
                  <a-avatar shape="square">
                    <img alt="avatar" :src="user.userAvatar" />
                  </a-avatar>
                </template>
              </a-list-item-meta>
            </a-list-item>
          </a-list>
        </a-card>
      </a-col>
    </a-row>
    <!-- 评论Modal -->
    <a-modal v-model:visible="commentModalVisible" title="评论" @cancel="commentModalVisible = false"
      @ok="addCommentHandle">
      <a-form :model="data.commentForm">
        <a-form-item field="content" label="评论内容">
          <a-textarea v-model="data.commentForm.content" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<style lang="scss" scoped>
.enrty-inof-conatiner {
  width: 100%;
  max-height: 95%;
  margin-top: 20px;
}

.creator-info {
  margin-top: 20px;
}

.action {
  display: inline-block;
  padding: 0 4px;
  color: var(--color-text-1);
  line-height: 24px;
  background: transparent;
  border-radius: 2px;
  cursor: pointer;
  transition: all 0.1s ease;
}

.action:hover {
  background: var(--color-fill-3);
}

.loadingtext {
  width: 100%;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #507EF7;
  cursor: pointer;
}
.sourceName {
  margin: 5px 0px;
}
</style>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
import { EntryControllerService as entryService, UserControllerService as userService, type EntryVO, type EntryCommentVO, OpenAPI, type UserVO, type User, type EntrySource } from "@/api";
import { message } from '@/utils/message';
import { IconMessage, IconDelete } from '@arco-design/web-vue/es/icon';
import { request } from '@/api/core/request';

const query = useRoute().query
const commentModalVisible = ref(false)
const data = reactive({
  entryId: query.entryId,
  entry: {} as EntryVO | undefined,
  commentList: [] as EntryCommentVO[],
  commentForm: {} as EntryCommentVO,
  userInfo: {} as UserVO
})
const source = reactive({
  audioList: [] as EntrySource[],
  videoList: [] as EntrySource[],
  imageList: [] as EntrySource[],
  fileList: [] as EntrySource[]
})
const commentPage = reactive({
  totle: 0 as number,
  current: 1 as number,
  size: 10 as number
})
const getEntry = () => {
  const entryId = parseInt(data.entryId as string)
  entryService.getEntryByIdUsingGet(entryId).then((res) => {
    if (res.code == 0) {
      data.entry = res.data
      getEntryCommentList()
      getSourceList()
    } else {
      message.error('获取作品信息失败')
    }
  })
}
const getEntryCommentList = async () => {
  const entryId = parseInt(data.entryId as string)
  return request(OpenAPI, {
    method: 'GET',
    url: '/api/entry/comment',
    query: {
      entryId: entryId,
      commentType: 1,
      current: commentPage.current,
      pageSize: commentPage.size
    }
  }).then((res: any) => {
    data.commentList = [...data.commentList, ...res.data.records]
    commentPage.current = res.data?.current as number
    commentPage.size = res.data?.size as number
    commentPage.totle = res.data?.total as number
  })
}
const loadMoreHandle = () => {
  commentPage.current++
  getEntryCommentList()
}
const showCommentModal = (comment: EntryCommentVO | undefined) => {
  if (comment == undefined) {
    data.commentForm.commentType = 1
  } else {
    data.commentForm.commentType = 2
    data.commentForm.replyId = comment.id
  }
  commentModalVisible.value = true
}
const addCommentHandle = () => {
  data.commentForm.entryId = data.entryId
  entryService.addEntryCommentUsingPost(data.commentForm).then((res) => {
    if (res.code == 0) {
      message.success('评论成功')
      refreshCommentList()
      commentModalVisible.value = false
    } else {
      message.error(res.message)
    }
  })
  console.log(data.commentForm);
}
const getUserInfo = () => {
  userService.getLoginUserUsingGet().then((res) => {
    if (res.code == 0) {
      data.userInfo = res.data as UserVO
    } else {
      message.error(res.message as string)
    }
  })
}

const deleteComment = (id: number) => {
  entryService.removeEntryCommentUsingDelete(id).then((res) => {
    if (res.code == 0) {
      message.success('删除评论成功')
      refreshCommentList()
    } else {
      message.error(res.message as string)
    }
  })
}
const refreshCommentList = () => {
  commentPage.current = 1
  data.commentList = []
  getEntryCommentList()
  data.commentForm = {} as EntryCommentVO
}
const getSourceList = () => {
  let sources = data.entry?.sources
  sources?.forEach((e) => {
    switch (e.sourceType) {
      case 'image':
        source.imageList.push(e)
        break;
      case 'video':
        source.videoList.push(e)
        break;
      case 'audio':
        source.audioList.push(e)
        break;
      case 'file':
        source.fileList.push(e)
        break;
    }
  })
}
onMounted(() => {
  getEntry()
  getUserInfo()
})
</script>