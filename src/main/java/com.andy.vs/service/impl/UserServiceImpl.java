package com.andy.vs.service.impl;

import com.andy.vs.dao.UserMapper;
import com.andy.vs.entity.User;
import com.andy.vs.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User selectByNameAndId(String id, String name) {
        Map<String, Object> map = new HashMap();
        map.put("user_id", id);
        map.put("user_name", name);
        List<User> users = baseMapper.selectByMap(map);
        ObjectMapper om = new ObjectMapper();
        String s = null;
        try {
            s = om.writeValueAsString(users);
            log.info(" json string --------- "+s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return users != null && users.size() > 0 ? users.get(0) : null;
    }
}
