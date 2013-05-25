package com.k2h2.counam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.k2h2.counam.entity.Coupon;
import com.k2h2.counam.mapper.CouponMapper;

@Controller
//@SessionAttributes({"currentUserID", "currentUserAccessToken"})
public class CouponService {
	
	@Autowired
	CouponMapper couponMapper;
	
	@RequestMapping(value="/coupon/listCoupon.json")
	@ResponseBody
	public List<Coupon> listCoupon() {
		return this.couponMapper.listCoupon();
	}
	
	@RequestMapping(value="/coupon/listCouponToMe.json")
	@ResponseBody
	public List<Coupon> listCouponToMe(String userId) {
		return this.couponMapper.listCouponToMe(userId);
	}
	
	@RequestMapping(value="/coupon/listCouponFromMe.json")
	@ResponseBody
	public List<Coupon> listCouponFromMe(String userId) {
		return this.couponMapper.listCouponFromMe(userId);
	}
	
	@RequestMapping(value="/coupon/sendCoupon.json")
	@ResponseBody
	public void sendCoupon(Coupon coupon) {
		this.couponMapper.createCoupon(coupon);
	}
}
