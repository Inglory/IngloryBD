/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 全网综合统计关系Entity
 * @author evay
 * @version 2017-04-16
 */
public class XsRelationAll extends DataEntity<XsRelationAll> {
	
	private static final long serialVersionUID = 1L;
	private String item;		// 统计项目
	private String lineId;		// 计算线路
	private String lineName;		// 计算线路
	private String quaKind;		// 电量类型
	private String calKind;		// 计算方式
	
	public XsRelationAll() {
		super();
	}

	public XsRelationAll(String id){
		super(id);
	}

	@Length(min=1, max=10, message="统计项目长度必须介于 1 和 10 之间")
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	@Length(min=1, max=64, message="计算线路长度必须介于 1 和 64 之间")
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Length(min=1, max=5, message="电量类型长度必须介于 1 和 5 之间")
	public String getQuaKind() {
		return quaKind;
	}

	public void setQuaKind(String quaKind) {
		this.quaKind = quaKind;
	}
	
	@Length(min=1, max=5, message="计算方式长度必须介于 1 和 5 之间")
	public String getCalKind() {
		return calKind;
	}

	public void setCalKind(String calKind) {
		this.calKind = calKind;
	}
	
}