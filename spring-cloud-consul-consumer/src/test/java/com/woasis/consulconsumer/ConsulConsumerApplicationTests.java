package com.woasis.consulconsumer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.woasis.consulconsumer.model.User;
import com.woasis.consulconsumer.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ConsulConsumerApplicationTests {

	@Autowired
	private UserService userService;
    @Test
    public void contextLoads() {
    }
    @Test
    @Transactional
    @Rollback(false)
    public void testUser(){
    	try {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
						userService.updateUser();
				}
			}).start();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
						userService.updateUser();
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Test
    @Transactional
    @Rollback(false)
    public void insertUser(){
    	for(int i = 0;i < 1000000;i ++){
    		User u = new User();
    		u.setAge(i+"");
    		u.setEmail("Test"+i);
    		u.setName("Test"+i);
    		userService.addUser(u);
    	}
    }

}
