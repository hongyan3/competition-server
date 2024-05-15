<template>
  <div class="home-container">
    <a-row :gutter="20" :style="{ marginBottom: '20px' }">
      <a-col :span="8">
        <a-card :bordered="false" :style="{ width: '100%' }" title="Arco Card">
          <template #extra>
            <a-link>More</a-link>
          </template>
          Card content
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card :bordered="false" :style="{ width: '100%' }" title="Arco Card">
          <template #extra>
            <a-link>More</a-link>
          </template>
          Card content
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card :bordered="false" :style="{ width: '100%' }" title="Arco Card">
          <template #extra>
            <a-link>More</a-link>
          </template>
          Card content
        </a-card>
      </a-col>
    </a-row>
    <a-row :gutter="20">
      <a-col :span="16">
        <a-card :bordered="false" :style="{ width: '100%' }" title="Arco Card">
          <template #extra>
            <a-link>More</a-link>
          </template>
          Card content
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card :bordered="false" :style="{ width: '100%' }" title="Arco Card">
          <template #extra>
            <a-link>More</a-link>
          </template>
          Card content
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>
<style lang="scss" scoped>
.home-container {
  width: 100%;
  max-height: 95%;
  height: 95%;
  margin-top: 20px;
  border-radius: 5px;
}
</style>