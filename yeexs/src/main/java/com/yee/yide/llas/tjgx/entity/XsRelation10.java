/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 10kV线损统计关系Entity
 * @author evay
 * @version 2017-04-24
 */
public class XsRelation10 extends DataEntity<XsRelation10> {
	
	private static final long serialVersionUID = 1L;
	private String lineCode;		// 线路编号
	private String lineId;		// 线路名称（不计算）
	
	public XsRelation10() {
		super();
	}

	public XsRelation10(String id){
		super(id);
	}

	@Length(min=0, max=100, message="线路编号长度必须介于 0 和 100 之间")
	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	
	@Length(min=1, max=64, message="线路名称（不计算）长度必须介于 1 和 64 之间")
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
}