package com.k2h2.counam.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k2h2.counam.entity.Coupon;
import com.k2h2.counam.mapper.CouponMapper;

@Controller
//@SessionAttributes({"currentUserID", "currentUserAccessToken"})
public class CouponService {
	
	@Autowired
	CouponMapper couponMapper;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
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
