package com.k2h2.counam.manager;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface CouponManager {
	 @Select("SELECT * FROM Coupon")
	 Map listCoupon();
}
