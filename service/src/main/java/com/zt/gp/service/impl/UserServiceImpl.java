package com.zt.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.gp.bean.PageResponse;
import com.zt.gp.entity.User;
import com.zt.gp.mapper.UserMapper;
import com.zt.gp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Integer login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        return this.count(queryWrapper);
    }

    @Override
    public User selectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        return this.getOne(queryWrapper);
    }

    @Override
    public void add(User user) {
        this.save(user);
    }

    @Override
    public PageResponse getPageList() {
        List<User> list = list();
        PageResponse response = new PageResponse();
        response.setList(list);
        return response;
    }
}
