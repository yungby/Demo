package com.andy.vs.controller;

import com.andy.vs.entity.User;
import com.andy.vs.service.UserService;
import com.andy.vs.service.impl.IndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户操作类")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private IndicatorService indicatorService;


    @RequestMapping(value = "/select/{userId}/{userName}",method = RequestMethod.GET)
//    @Cacheable(value = "UserController:Cache:User",key = "\"UserController_Cache_User \"+ #userId")
    @ApiOperation(value = "根据用户Id和用户名字查找用户信息",notes = "remark")
    public User findUserByIdAndName(@PathVariable("userId") @ApiParam("用户Id") String userId, @PathVariable("userName") @ApiParam("用户名字") String userName){
       log.info("---------------------------------------------------------"+this.getClass().getName());
       return userService.selectByNameAndId(userId,userName);
    }

    @RequestMapping(value = "/send/{topicName}/{data}",method = RequestMethod.GET)
    public String  sendKafkaMsg(@PathVariable("topicName") String topicName, @PathVariable("data") String data){
        indicatorService.sendMessage(topicName,data);
        return "ok";
    }
}
