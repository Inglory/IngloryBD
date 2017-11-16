/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 110kV线路统计关系维护Entity
 * @author evay_leec
 * @version 2017-04-14
 */
public class XsRelation110 extends DataEntity<XsRelation110> {
	
	private static final long serialVersionUID = 1L;
	private String parlineId;		// 统计线路
	private String parlineName;		// 统计线路
	private String lineId;		// 计算线路
	private String lineName;		// 计算线路
	private String quaKind;		// 电量类型
	private String calKind;		// 计算方式
	
	public XsRelation110() {
		super();
	}

	public XsRelation110(String id){
		super(id);
	}

	@Length(min=1, max=64, message="统计线路长度必须介于 1 和 64之间")
	public String getParlineId() {
		return parlineId;
	}

	public void setParlineId(String parlineId) {
		this.parlineId = parlineId;
	}

	public String getParlineName() {
		return parlineName;
	}

	public void setParlineName(String parlineName) {
		this.parlineName = parlineName;
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