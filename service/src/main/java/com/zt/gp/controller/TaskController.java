package com.zt.gp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.gp.bean.BaseResult;
import com.zt.gp.bean.PageResponse;
import com.zt.gp.entity.Picture;
import com.zt.gp.entity.Task;
import com.zt.gp.entity.User;
import com.zt.gp.service.IPictureService;
import com.zt.gp.service.ITaskService;
import com.zt.gp.util.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    IPictureService pictureService;

    @Value("${zt.py-file}")
    String pyPath;
    @Value("${zt.url-prefix}")
    String urlPrefix;

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

    @GetMapping("start")
    public BaseResult start(@RequestParam Integer id) throws IOException {
        Task task = taskService.getById(id);

        String dir = PathUtil.getPath()+ id + File.separator;
        String source = dir + "source";
        String temp = dir + "temp";
        String target = dir + "target";
        String format = String.format("python %s %s %s %s", pyPath, source, temp, target);
        Process p = Runtime.getRuntime().exec(format);
        task.setStatus(1);
        taskService.updateById(task);
        return BaseResult.ok("启动成功");
    }

    @GetMapping("result")
    public BaseResult result(@RequestParam Integer id ) throws IOException {
        String dir = PathUtil.getPath() + id + File.separator;
        File file = new File(dir + "target");
        List<String> res = new ArrayList<>();
        if (file.exists()){
            File[] files = file.listFiles();
            if (files == null){
                return BaseResult.data(res);
            }
            for (File f : files) {
                String name = f.getName();
                name = urlPrefix + id + File.separator+"target"+File.separator+name;
                res.add(name);
            }
        }
        return BaseResult.data(res);
    }
}

