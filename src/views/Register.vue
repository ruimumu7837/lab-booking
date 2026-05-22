<template>
  <div class="login-container">
    <el-card class="login-box">
      <h2>用户注册</h2>
      <el-form :model="form">
        <el-form-item><el-input v-model="form.username" placeholder="用户名" /></el-form-item>
        <el-form-item><el-input v-model="form.password" type="password" placeholder="密码" /></el-form-item>
        <el-form-item><el-input v-model="form.realName" placeholder="真实姓名" /></el-form-item>
        <el-form-item><el-input v-model="form.phone" placeholder="手机号" /></el-form-item>
        <el-form-item>
          <el-button type="primary" style="width:100%" @click="submit">注册</el-button>
        </el-form-item>
        <el-form-item>
          <el-button style="width:100%" @click="$router.push('/login')">返回登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import request from '../api/request.js'
import { ElMessage } from 'element-plus'

const router = useRouter()
const form = reactive({ username: '', password: '', realName: '', phone: '' })

const submit = async () => {
  await request.post('/user/register', form)
  ElMessage.success('注册成功')
  router.push('/login')
}
</script>

<style scoped>
.login-container { height: 100vh; display: flex; justify-content: center; align-items: center; background: #f0f2f5; }
.login-box { width: 400px; }
h2 { text-align: center; margin-bottom: 20px; }
</style>