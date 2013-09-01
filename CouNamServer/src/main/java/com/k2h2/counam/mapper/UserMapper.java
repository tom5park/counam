package com.k2h2.counam.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.k2h2.counam.constant.AuthType;
import com.k2h2.counam.entity.User;

public interface UserMapper {
	@Insert("INSERT INTO user(id, name, status, authType, authId, agreement, accToken, mobile, eMail) "
			+ "VALUES(#{id}, #{name}, #{status}, #{authType}, #{authId}, #{agreement}, #{accToken}, #{mobile}, #{eMail})")
	void createUser(User user);

	@Select("SELECT * FROM user u WHERE u.id=#{userId}")
	User getUser(String userId);

	@Select("SELECT * FROM user u WHERE u.authType=#{0} AND u.authId=#{1} AND u.status='ACTIVE'")
	User getUserByAuthId(AuthType authType, String authId);

	@Update("UPDATE user SET name=#{name}, status=#{status}, authType=#{authType}, authId=#{authId}, agreement=#{agreement}, accToken=#{accToken}, mobile=#{mobile}, eMail=#{eMail} WHERE id=#{id}")
	int updateUser(User user);
}
