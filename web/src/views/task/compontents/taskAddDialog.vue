<template>
  <el-dialog
    v-loading="loading"
    :title="type === 'add'?'添加':'更新'"
    :visible.sync="dialogVisible"
    @open="destroy = true"
    @closed="destroy = false">
    <el-form :model="dialogData" label-width="160px" :rules="rules" ref="form" v-if="destroy">
      <el-form-item
        label="任务名称">
        <el-input v-model="dialogData.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="option">新增</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
  import { fetchPost } from '@/utils/request'

  export default {
    name: 'taskAddDialog',
    data(){
      return{
        dialogVisible:false,
        type:'add',
        deactivated:true,
        dialogData:{
          name:null
        },
        destroy:true
      }
    },
    methods:{
      add(){
        fetchPost('/task/add',this.dialogData).then(res=>{
          this.$emit('update')
        })
      },option(){
        this.add()
        this.dialogVisible = false
      }
    }
  }
</script>

<style scoped>

</style>
