package com.k2h2.counam.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.k2h2.counam.entity.User;

public interface UserMapper {
	@Insert("INSERT INTO user(id, name, status, authType, authId, agreement, accToken) "
	 		+ "VALUES(#{id}, #{name}, #{status}, #{authType}, #{authId}, #{agreement}, #{accToken})")
	 void createUser(User user);
	 
	 @Select("SELECT * FROM user u WHERE u.id=#{userId}")
	 User getUser(String userId);
	 
	 @Select("SELECT * FROM user u WHERE u.authId=#{authId}")
	 User getUserByAuthId(String authId);
}
