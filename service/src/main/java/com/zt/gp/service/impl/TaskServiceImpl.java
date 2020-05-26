package com.zt.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.gp.bean.PageResponse;
import com.zt.gp.entity.Picture;
import com.zt.gp.entity.Task;
import com.zt.gp.mapper.TaskMapper;
import com.zt.gp.service.IPictureService;
import com.zt.gp.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zt
 * @since 2020-05-26
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {
    @Value("${zt.url-prefix}")
    public String prefix;

    @Autowired
    IPictureService pictureService;

    @Override
    public PageResponse getPageList() {
        List<Task> list = list();
        for (Task task : list) {
            QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Picture::getTaskId,task.getId());
            List<Picture> pictures = pictureService.list(queryWrapper);
            for (Picture picture : pictures) {
                String t = prefix + File.separator + task.getId() + File.separator + picture.getId() + "." + picture.getSuffix();
                picture.setUrl(t);
            }
            task.setPictures(pictures);
        }
        PageResponse response = new PageResponse();
        response.setList(list);
        return response;
    }
}
