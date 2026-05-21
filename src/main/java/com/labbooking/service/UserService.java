package com.labbooking.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labbooking.entity.User;
import com.labbooking.mapper.UserMapper;
import com.labbooking.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public User login(String username, String password) {
        User user = baseMapper.selectByUsername(username);
        if (user == null) return null;
        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!md5Pwd.equals(user.getPassword())) return null;
        return user;
    }

    public boolean register(User user) {
        if (baseMapper.selectByUsername(user.getUsername()) != null) return false;
        user.setPassword(DigestUtils.md5DigestAsHex(
            user.getPassword().getBytes(StandardCharsets.UTF_8)));
        user.setRole("STUDENT");
        return save(user);
    }

    public String generateToken(User user) {
        return JwtUtil.createToken(user.getId(), user.getUsername(), user.getRole());
    }
}