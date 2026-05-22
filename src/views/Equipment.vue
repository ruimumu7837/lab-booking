<template>
  <div>
    <el-menu :default-active="$route.path" router mode="horizontal" background-color="#545c64" text-color="#fff">
      <el-menu-item index="/equipment">设备管理</el-menu-item>
      <el-menu-item index="/booking">我的预约</el-menu-item>
      <el-menu-item index="/statistics">统计报表</el-menu-item>
      <el-menu-item style="float:right" @click="logout">退出</el-menu-item>
    </el-menu>
    
    <div style="padding:20px">
      <el-button type="primary" @click="dialogVisible=true">新增设备</el-button>
      <el-table :data="list" style="margin-top:15px">
        <el-table-column prop="name" label="设备名称" />
        <el-table-column prop="category" label="分类" />
        <el-table-column prop="location" label="位置" />
        <el-table-column prop="status" label="状态">
          <template #default="{row}">
            <el-tag :type="row.status==='AVAILABLE'?'success':row.status==='IN_USE'?'warning':'danger'">
              {{ row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="{row}">
            <el-button size="small" @click="book(row)">预约</el-button>
            <el-button size="small" type="danger" @click="del(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    
    <el-dialog v-model="dialogVisible" title="新增设备">
      <el-form :model="form">
        <el-form-item label="名称"><el-input v-model="form.name" /></el-form-item>
        <el-form-item label="分类"><el-input v-model="form.category" /></el-form-item>
        <el-form-item label="位置"><el-input v-model="form.location" /></el-form-item>
        <el-form-item label="描述"><el-input v-model="form.description" type="textarea" /></el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </template>
    </el-dialog>
    
    <el-dialog v-model="bookVisible" title="预约设备">
      <el-form :model="bookForm">
        <el-form-item label="用途"><el-input v-model="bookForm.purpose" /></el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker v-model="bookForm.startTime" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker v-model="bookForm.endTime" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="bookVisible=false">取消</el-button>
        <el-button type="primary" @click="submitBook">提交预约</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import request from '../api/request.js'
import { ElMessage } from 'element-plus'

const router = useRouter()
const list = ref([])
const dialogVisible = ref(false)
const bookVisible = ref(false)
const form = ref({ name:'', category:'', location:'', description:'' })
const bookForm = ref({ equipmentId:null, purpose:'', startTime:'', endTime:'' })

const load = async () => { list.value = (await request.get('/equipment/list')).data }
const save = async () => { await request.post('/equipment', form.value); dialogVisible.value=false; load() }
const del = async (id) => { await request.delete(`/equipment/${id}`); load() }
const book = (row) => { bookForm.value.equipmentId=row.id; bookVisible.value=true }
const submitBook = async () => {
  await request.post('/booking', bookForm.value)
  bookVisible.value=false
  ElMessage.success('预约成功，等待审批')
  router.push('/booking')
}
const logout = () => { localStorage.clear(); router.push('/login') }

onMounted(load)
</script>