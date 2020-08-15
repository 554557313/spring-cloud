package com.woasis.consulconsumer.controller;

import com.woasis.consulconsumer.model.User;
import com.woasis.consulconsumer.service.ConsumerService;
import com.woasis.consulconsumer.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @GetMapping("/getProducer")
    @ResponseBody
    public String getProducer(){

    	List<ServiceInstance> instances = discoveryClient.getInstances("spring-cloud-consul-consumer");
    	System.out.println(instances);
    	
        String consumer = consumerService.consumer();
        System.out.println(consumer);
        return consumer;
    }
    @GetMapping("/get")
    @ResponseBody
    public String get(){

    	redisTemplate.opsForValue().set("key", "4");
    	String key = redisTemplate.opsForValue().get("key");
        return key;
    }
    
    @GetMapping("/addUser")
    @ResponseBody
    public String addUser(){

    	User u = new User();
    	u.setAge("23");
    	u.setEmail("test@g.com");
    	u.setGender("0");
    	u.setName("test2");
    	userService.addUser(u);
    	return "success";
    }
    
    @GetMapping("/selectUser/{id}")
    @ResponseBody
    public User selectUser(@PathVariable Long id){
    	User selectUser = userService.selectUser(id);
    	return selectUser;
    }
    
    @GetMapping("/selectAll")
    @ResponseBody
    public List<User> selectAll(){
    	List<User> selectUser = userService.selectAll();
    	return selectUser;
    }
}
