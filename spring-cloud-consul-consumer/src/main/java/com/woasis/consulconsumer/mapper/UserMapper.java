package com.woasis.consulconsumer.mapper;

import java.util.List;

import com.woasis.consulconsumer.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);
    
    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int updateByNameAndEmail(User u);
    
    int updateByEmailAndName(User u);
}