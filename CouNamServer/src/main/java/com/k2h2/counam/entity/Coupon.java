package com.k2h2.counam.entity;

import java.util.Date;
import java.util.UUID;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.k2h2.counam.util.JsonDateSerializer;

public class Coupon {
	
	String id = UUID.randomUUID().toString();
	String title = "밥 쿠폰";
	String type = "TEMP";
	String description = "조만간 밥 쏜다. 날짜 잡아라.";
	
	@JsonSerialize(using = JsonDateSerializer.class)
	Date created = new Date();
	String status = "READY";
	String from = "123";
	String to = "0o90sdf";
	
	@JsonSerialize(using = JsonDateSerializer.class)
	Date validFrom = new Date();
	@JsonSerialize(using = JsonDateSerializer.class)
	Date validTo = new Date();
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

}
