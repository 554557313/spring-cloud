package com.woasis.consulconsumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woasis.consulconsumer.mapper.UserMapper;
import com.woasis.consulconsumer.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper mapper;
	
	@Transactional
	public void addUser(User u) {
		mapper.insert(u);
	}
	
	public User selectUser(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<User> selectAll(){
		return mapper.selectAll();
	}
	@Transactional
	public void updateUser() {
		
		try {
			User u1 = new User();
			u1.setAge("123");
			u1.setName("test1");
			u1.setEmail("test@g.com");
			//Thread.sleep(3000);
			mapper.updateByNameAndEmail(u1);
			
			User u2 = new User();
			u2.setAge("454");
			u2.setName("test2");
			u2.setEmail("test@g.com");
			//Thread.sleep(3000);
			mapper.updateByEmailAndName(u2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Transactional
	public void updateUser2() {
		
		try {
			User u2 = new User();
			u2.setAge("2");
			u2.setName("test2");
			u2.setEmail("test@g.com");
			//Thread.sleep(3000);
			mapper.updateByEmailAndName(u2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	

}
