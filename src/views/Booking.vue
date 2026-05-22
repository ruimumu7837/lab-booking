<template>
  <div>
    <el-menu :default-active="$route.path" router mode="horizontal" background-color="#545c64" text-color="#fff">
      <el-menu-item index="/equipment">设备管理</el-menu-item>
      <el-menu-item index="/booking">我的预约</el-menu-item>
      <el-menu-item index="/statistics">统计报表</el-menu-item>
    </el-menu>
    <div style="padding:20px">
      <el-table :data="list">
        <el-table-column prop="id" label="预约ID" />
        <el-table-column prop="equipmentId" label="设备ID" />
        <el-table-column prop="purpose" label="用途" />
        <el-table-column prop="startTime" label="开始时间" />
        <el-table-column prop="endTime" label="结束时间" />
        <el-table-column prop="status" label="状态">
          <template #default="{row}">
            <el-tag :type="row.status==='APPROVED'?'success':row.status==='PENDING'?'warning':'danger'">
              {{ row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="{row}">
            <el-button v-if="row.status==='PENDING'||row.status==='APPROVED'" size="small" @click="cancel(row.id)">取消</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '../api/request.js'
import { ElMessage } from 'element-plus'

const list = ref([])
const load = async () => { list.value = (await request.get('/booking/my')).data }
const cancel = async (id) => { await request.put(`/booking/cancel/${id}`); ElMessage.success('已取消'); load() }
onMounted(load)
</script>