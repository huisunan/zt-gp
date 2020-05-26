<template>
  <el-dialog
    title="图片处理结果"
    :visible.sync="dialogVisible">
    <el-image v-for="item in list" style="width: 100px;height: 100px;margin-left: 5px" :src="item">

    </el-image>
  </el-dialog>
</template>

<script>
  export default {
    name: 'taskResultDialog',
    data(){
      return{
        list:[],
        dialogVisible:false
      }
    }
  }
</script>

<style scoped>

</style>
