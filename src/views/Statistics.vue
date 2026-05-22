<template>
  <div>
    <el-menu :default-active="$route.path" router mode="horizontal" background-color="#545c64" text-color="#fff">
      <el-menu-item index="/equipment">设备管理</el-menu-item>
      <el-menu-item index="/booking">我的预约</el-menu-item>
      <el-menu-item index="/statistics">统计报表</el-menu-item>
    </el-menu>
    <div style="padding:20px">
      <el-row :gutter="20">
        <el-col :span="8"><el-card><div style="text-align:center"><h3>设备总数</h3><h1>{{ stats.equipmentCount }}</h1></div></el-card></el-col>
        <el-col :span="8"><el-card><div style="text-align:center"><h3>预约总数</h3><h1>{{ stats.bookingCount }}</h1></div></el-card></el-col>
        <el-col :span="8"><el-card><div style="text-align:center"><h3>待审批</h3><h1>{{ stats.pendingCount }}</h1></div></el-card></el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '../api/request.js'

const stats = ref({ equipmentCount: 0, bookingCount: 0, pendingCount: 0 })

onMounted(async () => {
  const eq = await request.get('/equipment/list')
  const bk = await request.get('/booking/my')
  stats.value.equipmentCount = eq.data.length
  stats.value.bookingCount = bk.data.length
  stats.value.pendingCount = bk.data.filter(b => b.status === 'PENDING').length
})
</script>