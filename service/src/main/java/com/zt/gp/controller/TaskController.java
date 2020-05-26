package com.zt.gp.controller;


import com.zt.gp.bean.BaseResult;
import com.zt.gp.bean.PageResponse;
import com.zt.gp.entity.Task;
import com.zt.gp.entity.User;
import com.zt.gp.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zt
 * @since 2020-05-26
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    ITaskService taskService;

    @PostMapping("add")
    public BaseResult add(@RequestBody Task task, @SessionAttribute("user") User user){
        task.setId(null);
        task.setUserId(user.getId());
        task.setStatus(0);
        taskService.save(task);
        return BaseResult.ok("任务添加成功!");
    }

    @PostMapping("list")
    public BaseResult list(){
        PageResponse response = taskService.getPageList();
        return BaseResult.data(response);
    }

    @GetMapping("delete")
    public BaseResult delete(@RequestParam Integer id){
        taskService.removeById(id);
        return BaseResult.ok("删除成功！");
    }
}

