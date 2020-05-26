package com.zt.gp.controller;


import com.zt.gp.bean.BaseResult;
import com.zt.gp.bean.PageResponse;
import com.zt.gp.entity.User;
import com.zt.gp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zt
 * @since 2020-05-26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;


    @PostMapping("login")
    public BaseResult login(@RequestBody User user, HttpSession session) {
        Integer count = userService.login(user);
        if (count > 0) {
            User search = userService.selectByUsername(user.getUsername());
            session.setAttribute("user", search);
            return BaseResult.ok("登录成功！",search.getRole());
        } else {
            return BaseResult.error("用户不存在或密码错误!");
        }
    }

    @PostMapping("list")
    public BaseResult list(){
        PageResponse response = userService.getPageList();
        return BaseResult.data(response);
    }

    @PostMapping("add")
    public BaseResult add(@RequestBody User user){
        User search = userService.selectByUsername(user.getUsername());
        if (search != null){
            return BaseResult.error("用户名已存在!");
        }
        user.setId(null);
        userService.add(user);
        return BaseResult.ok("用户添加成功！");
    }

    @GetMapping("delete")
    public BaseResult delete(@RequestParam Integer id){
        userService.removeById(id);
        return BaseResult.ok("删除成功！");
    }

    @PostMapping("update")
    public BaseResult update(@RequestBody User user){
        userService.updateById(user);
        return BaseResult.ok("修改成功!");
    }
}

