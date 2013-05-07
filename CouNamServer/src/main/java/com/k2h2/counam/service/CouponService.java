package com.k2h2.counam.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k2h2.counam.manager.CouponManager;

@Controller
public class CouponService {
	
	@Autowired
	CouponManager couponManager;
	
	@RequestMapping(value="/Coupon/listCoupon.json")
	@ResponseBody
	public Map listCoupon() {
		return this.couponManager.listCoupon();
	}
}
