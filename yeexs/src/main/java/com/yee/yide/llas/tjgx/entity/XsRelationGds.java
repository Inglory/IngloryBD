/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 供电所损失关系Entity
 * @author evay
 * @version 2017-04-16
 */
public class XsRelationGds extends DataEntity<XsRelationGds> {
	
	private static final long serialVersionUID = 1L;
	private String businessid;		// 供电所
	private String gdsMc;		// 供电所
	private String lineId;		// 计算线路
	private String lineName;		// 计算线路
	private String isDirectsupply;		// 直供标志
	
	public XsRelationGds() {
		super();
	}

	public XsRelationGds(String id){
		super(id);
	}

	@Length(min=1, max=64, message="供电所长度必须介于 1 和 64 之间")
	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}
	
	@Length(min=1, max=64, message="计算线路长度必须介于 1 和 64 之间")
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
	@Length(min=0, max=5, message="直供标志长度必须介于 0 和 5 之间")
	public String getIsDirectsupply() {
		return isDirectsupply;
	}

	public void setIsDirectsupply(String isDirectsupply) {
		this.isDirectsupply = isDirectsupply;
	}

	public String getGdsMc() {
		return gdsMc;
	}

	public void setGdsMc(String gdsMc) {
		this.gdsMc = gdsMc;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
}