<template>
  <el-dialog
    v-loading="loading"
    :title="type === 'add'?'添加图片':'更新'"
    :visible.sync="dialogVisible"
    @open="destroy = true"
    @closed="destroy = false">
    <el-form :model="dialogData" label-width="160px" :rules="rules" ref="form" v-if="destroy">
      <el-upload
        action="http://localhost:8080/picture/add"
        :on-preview="handlePreview"
        ref="upload"
        :on-remove="handleRemove"
        :file-list="fileList"
        :data="{id}"
        :auto-upload="false"
        list-type="picture">
        <el-button slot="trigger" size="small" type="primary">选取图片</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
      </el-upload>
    </el-form>
  </el-dialog>
</template>

<script>
  export default {
    name: 'taskPictureDialog',
    data(){
      return{
        id:null,
        dialogVisible:false,
        loading:true,
        type:'add',
        deactivated:true,
        dialogData:{
          name:null
        },
        destroy:true,
        fileList:[]
      }
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },submitUpload() {
        this.$refs.upload.submit();
        this.$message.success("上传成功!")
        this.$emit('update')
        this.dialogVisible = false
      }
    }
  }
</script>

<style scoped>

</style>
