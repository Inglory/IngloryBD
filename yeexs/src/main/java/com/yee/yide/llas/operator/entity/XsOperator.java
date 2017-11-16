/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.operator.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 制表人员、用电小时、统计区间设置与管理Entity
 * @author evay_leec
 * @version 2017-10-20
 */
public class XsOperator extends DataEntity<XsOperator> {
	
	private static final long serialVersionUID = 1L;
	private Integer serialNumber;		// 序号
	private String principal;		// 负责人
	private String lister;		// 制表人
	private String day;		// 制表日期
	private String hours;		// 用电时间
	private String ym;		// 年月
	private Date qsrq;		// 起始日期
	private Date jsrq;		// 结束日期
	private String jcbz;		// 结存标志
	
	public XsOperator() {
		super();
	}

	public XsOperator(String id){
		super(id);
	}

	@NotNull(message="序号不能为空")
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Length(min=1, max=10, message="负责人长度必须介于 1 和 10 之间")
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	@Length(min=1, max=10, message="制表人长度必须介于 1 和 10 之间")
	public String getLister() {
		return lister;
	}

	public void setLister(String lister) {
		this.lister = lister;
	}
	
	@Length(min=1, max=2, message="制表日期长度必须介于 1 和 2 之间")
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}
	
	@Length(min=0, max=10, message="年月长度必须介于 0 和 10 之间")
	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getQsrq() {
		return qsrq;
	}

	public void setQsrq(Date qsrq) {
		this.qsrq = qsrq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getJsrq() {
		return jsrq;
	}

	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}
	
	
	public String getJcbz() {
		return jcbz;
	}

	public void setJcbz(String jcbz) {
		this.jcbz = jcbz;
	}
	
}