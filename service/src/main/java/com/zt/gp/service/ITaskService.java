package com.zt.gp.service;

import com.zt.gp.bean.PageResponse;
import com.zt.gp.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zt
 * @since 2020-05-26
 */
public interface ITaskService extends IService<Task> {

    PageResponse getPageList();

}
