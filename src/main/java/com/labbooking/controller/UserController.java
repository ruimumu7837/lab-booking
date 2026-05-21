package com.labbooking.controller;

import com.labbooking.dto.Result;
import com.labbooking.entity.User;
import com.labbooking.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        boolean ok = userService.register(user);
        return ok ? Result.success("注册成功") : Result.error("用户名已存在");
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User loginUser) {
        User user = userService.login(loginUser.getUsername(), loginUser.getPassword());
        if (user == null) return Result.error("用户名或密码错误");
        String token = userService.generateToken(user);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", user);
        return Result.success(map);
    }
}