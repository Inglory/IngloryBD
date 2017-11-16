/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 变电站信息Entity
 * @author evay_leec
 * @version 2017-03-25
 */
public class XsTrsubstation extends DataEntity<XsTrsubstation> {
	
	private static final long serialVersionUID = 1L;
	private String trsubstationCode;		// 变电站编号
	private String name;		// 变电站名称
	private String shortname;		// 简称
	private String voltargrade;		// 电压等级
	
	public XsTrsubstation() {
		super();
	}

	public XsTrsubstation(String id){
		super(id);
	}

	@Length(min=1, max=20, message="变电站编号长度必须介于 1 和 20之间")
	public String getTrsubstationCode() {
		return trsubstationCode;
	}

	public void setTrsubstationCode(String trsubstationCode) {
		this.trsubstationCode = trsubstationCode;
	}
	
	@Length(min=1, max=60, message="变电站名称长度必须介于 1 和 60 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="简称长度必须介于 0 和 20 之间")
	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	
	@Length(min=0, max=20, message="电压等级长度必须介于 0 和 20 之间")
	public String getVoltargrade() {
		return voltargrade;
	}

	public void setVoltargrade(String voltargrade) {
		this.voltargrade = voltargrade;
	}
	
}