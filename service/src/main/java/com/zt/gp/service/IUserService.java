package com.zt.gp.service;

import com.zt.gp.bean.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zt.gp.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zt
 * @since 2020-05-26
 */
public interface IUserService extends IService<User> {

    Integer login(User user);

    User selectByUsername(String username);

    void add(User user);

    PageResponse getPageList();

}
