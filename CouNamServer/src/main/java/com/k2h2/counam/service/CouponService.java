package com.k2h2.counam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k2h2.counam.entity.Coupon;
import com.k2h2.counam.mapper.CouponMapper;

@Controller
public class CouponService {
	
	@Autowired
	CouponMapper couponMapper;
	
	@RequestMapping(value="/coupon/listCoupon.json")
	@ResponseBody
	public List<Coupon> listCoupon() {
		return this.couponMapper.listCoupon();
	}
}
