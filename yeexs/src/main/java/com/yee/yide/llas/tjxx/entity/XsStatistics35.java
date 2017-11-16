/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yee.yide.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 35kV线损统计结果Entity
 * @author evay_leec
 * @version 2017-04-19
 */
public class XsStatistics35 extends DataEntity<XsStatistics35> {
	
	private static final long serialVersionUID = 1L;
	private String ym;		// 年月
	private Date qsrq;		// 起始日期
	private Date jsrq;		// 结束日期
	private String lineId;		// 线路ID
	private String lineCode;		// 线路编号
	private String linename;		// 线路名称
	private Double powerQua;		// 有功电量
	private Double nonpowerQua;		// 无功电量
	private Double saleQua;		// 销售电量
	private Double wasteQua;		// 损失电量
	private Double trwasteQua;		// 变损电量
	private Double wasteRate;		// 线损率
	private Double compWrate;		// 综合线损率
	private Double salequaInsdh;		// 销售电量（含四到户）
	private Double wastequaInsdh;		// 损失电量（含四到户）
	private Double trwastequaInsdh;		// 变损电量（含四到户）
	private Double wasterateInsdh;		// 线损率（含四到户）
	private Double compwrateInsdh;		// 综合线损率（含四到户）
	private Double powerrate;		// 力率
	private Double yglj;		// 有功累计
	private Double wglj;		// 无功累计
	private Double sslj;		// 损失累计
	private Double sclj;		// 销售累计
	
	public XsStatistics35() {
		super();
	}

	public XsStatistics35(String id){
		super(id);
	}

	@Length(min=0, max=40, message="年月长度必须介于 0 和 40 之间")
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
	
	@Length(min=0, max=64, message="线路ID长度必须介于 0 和 64 之间")
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
	@Length(min=0, max=20, message="线路编号长度必须介于 0 和 20 之间")
	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	
	@Length(min=0, max=100, message="线路名称长度必须介于 0 和 100 之间")
	public String getLinename() {
		return linename;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}
	
	public Double getPowerQua() {
		return powerQua;
	}

	public void setPowerQua(Double powerQua) {
		this.powerQua = powerQua;
	}
	
	public Double getNonpowerQua() {
		return nonpowerQua;
	}

	public void setNonpowerQua(Double nonpowerQua) {
		this.nonpowerQua = nonpowerQua;
	}
	
	public Double getSaleQua() {
		return saleQua;
	}

	public void setSaleQua(Double saleQua) {
		this.saleQua = saleQua;
	}
	
	public Double getWasteQua() {
		return wasteQua;
	}

	public void setWasteQua(Double wasteQua) {
		this.wasteQua = wasteQua;
	}
	
	public Double getTrwasteQua() {
		return trwasteQua;
	}

	public void setTrwasteQua(Double trwasteQua) {
		this.trwasteQua = trwasteQua;
	}
	
	public Double getWasteRate() {
		return wasteRate;
	}

	public void setWasteRate(Double wasteRate) {
		this.wasteRate = wasteRate;
	}
	
	public Double getCompWrate() {
		return compWrate;
	}

	public void setCompWrate(Double compWrate) {
		this.compWrate = compWrate;
	}
	
	public Double getSalequaInsdh() {
		return salequaInsdh;
	}

	public void setSalequaInsdh(Double salequaInsdh) {
		this.salequaInsdh = salequaInsdh;
	}
	
	public Double getWastequaInsdh() {
		return wastequaInsdh;
	}

	public void setWastequaInsdh(Double wastequaInsdh) {
		this.wastequaInsdh = wastequaInsdh;
	}
	
	public Double getTrwastequaInsdh() {
		return trwastequaInsdh;
	}

	public void setTrwastequaInsdh(Double trwastequaInsdh) {
		this.trwastequaInsdh = trwastequaInsdh;
	}
	
	public Double getWasterateInsdh() {
		return wasterateInsdh;
	}

	public void setWasterateInsdh(Double wasterateInsdh) {
		this.wasterateInsdh = wasterateInsdh;
	}
	
	public Double getCompwrateInsdh() {
		return compwrateInsdh;
	}

	public void setCompwrateInsdh(Double compwrateInsdh) {
		this.compwrateInsdh = compwrateInsdh;
	}
	
	public Double getPowerrate() {
		return powerrate;
	}

	public void setPowerrate(Double powerrate) {
		this.powerrate = powerrate;
	}
	
	public Double getYglj() {
		return yglj;
	}

	public void setYglj(Double yglj) {
		this.yglj = yglj;
	}
	
	public Double getWglj() {
		return wglj;
	}

	public void setWglj(Double wglj) {
		this.wglj = wglj;
	}
	
	public Double getSslj() {
		return sslj;
	}

	public void setSslj(Double sslj) {
		this.sslj = sslj;
	}
	
	public Double getSclj() {
		return sclj;
	}

	public void setSclj(Double sclj) {
		this.sclj = sclj;
	}
	
}