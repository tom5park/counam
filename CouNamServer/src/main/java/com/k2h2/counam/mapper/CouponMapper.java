package com.k2h2.counam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.k2h2.counam.entity.Coupon;

public interface CouponMapper {
	 @Select("SELECT * FROM Coupon")
	 List<Coupon> listCoupon();
	 
	 @Insert("INSERT INTO COUPON (`id`, `type`, `title`, `created`, `status`, `from`, `to`) VALUES (#{id}, #{type}, #{title}, #{created}, #{status}, #{from}, #{to})")
	 void createCoupon(Coupon coupon);
}
