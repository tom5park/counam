package com.k2h2.counam.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.k2h2.counam.entity.User;

public interface UserMapper {
	 @Select("SELECT * FROM user u WHERE u.id=#{userId}")
	 User getUser(String userId);
	 /**
	  * `id` char(36) NOT NULL,
  `name` varchar(16) NOT NULL,
  `status` varchar(32) NOT NULL,
  `authType` varchar(16) NOT NULL,
  `authId` varchar(128) NOT NULL,
  `agreement` int NOT NULL,
  `accToken` varchar(128) NOT NULL,
	  * @param user
	  * @return
	  */
	 @Insert("INSERT INTO user(id, name, status, authType, authId, agreement, accToken) "
	 		+ "VALUES(#{id}, #{name}, #{status}, #{authType}, #{authId}, #{agreement}, #{accToken})")
	 void createUser(User user);
}
