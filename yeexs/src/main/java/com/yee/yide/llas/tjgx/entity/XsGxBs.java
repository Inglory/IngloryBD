/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 变电站损失关系Entity
 * @author evay
 * @version 2017-04-16
 */
public class XsGxBs extends DataEntity<XsGxBs> {
	
	private static final long serialVersionUID = 1L;
	private String tjdx;		// 统计对象
	private String tjdxName;		// 统计对象
	private String tjdllx;		// 统计电量类型
	private String jld;		// 计量点
	private String lineName;		// 计量点、线路名称
	private String jsdl;		// 计算电量
	private String jsfs;		// 计算方式
	
	public XsGxBs() {
		super();
	}

	public XsGxBs(String id){
		super(id);
	}

	@Length(min=1, max=64, message="统计对象长度必须介于 1 和 64 之间")
	public String getTjdx() {
		return tjdx;
	}

	public void setTjdx(String tjdx) {
		this.tjdx = tjdx;
	}
	
	@Length(min=1, max=5, message="统计电量类型长度必须介于 1 和 5 之间")
	public String getTjdllx() {
		return tjdllx;
	}

	public void setTjdllx(String tjdllx) {
		this.tjdllx = tjdllx;
	}
	
	@Length(min=1, max=64, message="计量点长度必须介于 1 和 64 之间")
	public String getJld() {
		return jld;
	}

	public void setJld(String jld) {
		this.jld = jld;
	}
	
	@Length(min=1, max=5, message="计算电量长度必须介于 1 和 5 之间")
	public String getJsdl() {
		return jsdl;
	}

	public void setJsdl(String jsdl) {
		this.jsdl = jsdl;
	}
	
	@Length(min=1, max=5, message="计算方式长度必须介于 1 和 5 之间")
	public String getJsfs() {
		return jsfs;
	}

	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}

	public String getTjdxName() {
		return tjdxName;
	}

	public void setTjdxName(String tjdxName) {
		this.tjdxName = tjdxName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
}