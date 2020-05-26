<template>
    <div style="margin: 10px">
      <el-table :data="list">
        <el-table-column
          label="id"
          prop="id">

        </el-table-column>
        <el-table-column
          label="用户名"
          prop="username">

        </el-table-column>
        <el-table-column
          label="角色">
          <template slot-scope="{row}">
            <span>{{row.role === 0 ? '管理员':'普通用户' }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="{row}">
            <el-button type="success" round @click="update(row)" size="small">修改</el-button>
            <el-button type="danger" round @click="del(row.id)" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
  import { fetchDelete, fetchGet, fetchPost } from '@/utils/request'

  export default {
    name: 'user',
    data(){
      return{
        list:[]
      }
    },
    created() {
      this.getList()
    },methods:{
      getList(){
        fetchPost('/user/list').then(res=>{
          this.list =  res.data.list
        })
      },
      update(row){
        this.$router.push({
          name:'UserEdit',
          params:{
            data:row
          }
        })
      },
      del(id){
        fetchGet('/user/delete', { id }).then(()=>{
          this.getList()
        })
      }
    }
  }
</script>

<style scoped>

</style>
