<template>
  <div style="margin: 10px">
    <el-button type="primary" size="small" round @click="$refs.dialog.dialogVisible = true">添加</el-button>
    <el-table :data="list">
      <el-table-column
        type="expand">
        <template slot-scope="{row}">

          <div v-if="row.pictures.length > 0">
            <el-image style="width: 100px; height: 100px" v-for="item in row.pictures" :src="item.url">
            </el-image>
          </div>
          <div v-else style="text-align: center">
            暂无图片，请上传!
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="id"
        label="ID">

      </el-table-column>
      <el-table-column
        prop="name"
        label="任务名称">

      </el-table-column>
      <el-table-column
        prop="userId"
        label="用户ID">
      </el-table-column>
      <el-table-column
        label="状态">
        <template slot-scope="{row}">
          <span v-if="row.status === 0">创建</span>
          <span v-else-if="row.status === 1">运行中</span>
          <span v-else-if="row.status === 2">完成</span>
        </template>
      </el-table-column>
      <el-table-column
        min-width="180"
        label="操作">
        <template slot-scope="{row}">
          <!--          :disabled="row.status !== 0"   -->
          <el-button type="primary" round size="small" @click="start(row.id)">开始</el-button>
          <el-button type="info" round size="small" @click="result(row.id)">查看结果</el-button>
          <el-button type="success" round size="small" @click="showPicture(row.id)">上传图片</el-button>
          <el-button type="danger" round size="small" @click="del(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <task-add-dialog ref="dialog" @update="getList()"/>
    <task-picture-dialog ref="picture" @update="getList()"/>
    <task-result-dialog ref="result"/>
  </div>
</template>

<script>
  import { fetchGet, fetchPost } from '@/utils/request'
  import TaskAddDialog from '@/views/task/compontents/taskAddDialog'
  import TaskPictureDialog from '@/views/task/compontents/taskPictureDialog'
  import TaskResultDialog from '@/views/task/compontents/taskResultDialog'

  export default {
    name: 'list',
    components: { TaskResultDialog, TaskPictureDialog, TaskAddDialog },
    data() {
      return {
        list: []
      }
    }, created() {
      this.getList()
    }, methods: {
      getList() {
        fetchPost('/task/list').then(res => {
          this.list = res.data.list
        })
      }, del(id) {
        fetchGet('/task/delete', { id }).then(res => {
          this.getList()
        })
      }, showPicture(id) {
        this.$refs.picture.id = id
        this.$refs.picture.dialogVisible = true
      }, start(id) {
        fetchGet('/task/start', { id }).then(res => {
            this.getList()
          }
        )
      }, result(id) {
        fetchGet('/task/result', { id }).then(res => {
          this.$refs.result.list = res.data
          this.$refs.result.dialogVisible = true

        })
      }
    }
  }
</script>

<style scoped>

</style>
