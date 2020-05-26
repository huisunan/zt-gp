<template>
  <div style="margin-top:10px;">
    <el-form :model="formData"
             label-width="120px">
      <el-form-item
        label="用户名">
        <el-input v-model="formData.username" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item
        label="密码">
        <el-input type="password" v-model="formData.password" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item
        label="角色">
        <el-select v-model="formData.role" placeholder="选择角色" style="width: 200px">
          <el-option
            v-for="item in [{label:'管理员',value:0},{label:'普通用户',value:1}]"
            :key="item.value"
            :value="item.value"
            :label="item.label">

          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" round @click="option">
          {{type === 'add'?'添 加':'修 改'}}
        </el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
  import { fetchPost } from '@/utils/request'

  export default {
    name: 'userEdit',
    data() {
      return {
        formData: {
          username: null,
          password: null,
          role: null
        },
        type:'add'
      }
    },
    created() {
      let data = this.$route.params.data
      if (data){
        this.formData = data
        this.type = 'update'
      }
    },
    methods: {
      add() {
        fetchPost('/user/add', this.formData).then(res => {
          this.$router.push({
            name: 'User'
          })
        })
      },update(){
        fetchPost('/user/update',this.formData).then(res => {
          this.$router.push({
            name: 'User'
          })
        })
      },
      option(){
        if (this.type === 'add'){
          this.add()
        }else {
          this.update()
        }
      }
    }
  }
</script>

<style scoped>

</style>
