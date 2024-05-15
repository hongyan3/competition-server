<script lang="ts" setup>
import {RouterView} from 'vue-router'
</script>

<template>
  <div id="app">
    <RouterView/>
  </div>
</template>
<style>
#app {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0;
  width: 100%;
  height: 100%;
}
</style>