/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 供电所电量损失统计Entity
 * @author evay
 * @version 2017-04-24
 */
public class XsStatisticsGds extends DataEntity<XsStatisticsGds> {
	
	private static final long serialVersionUID = 1L;
	private String ym;		// 年月
	private String businessid;		// 供电所
	private String gdsbh;		// 供电所编号
	private String gdsmc;		// 供电所名称
	private Date qsrq;		// 起始日期
	private Date jsrq;		// 结束日期
	private String isIncludeds;		// 是否直供
	private String powerQua;		// 有功电量
	private String nonpowerQua;		// 无功电量
	private String saleQua;		// 销售电量
	private String wasteQua;		// 损失电量
	private String trwasteQua;		// 变损电量
	private String powerrate;		// 力率
	private String wasteRate;		// 损失率
	private String compWrate;		// 综合损失率
	private String salequaInsdh;		// 销售电量（含四到户）
	private String wastequaInsdh;		// 损失电量（含四到户）
	private String trwastequaInsdh;		// 变损电量（含四到户）
	private String wasterateInsdh;		// 损失率（含四到户）
	private String compwrateInsdh;		// 综合损失率（含四到户）
	
	public XsStatisticsGds() {
		super();
	}

	public XsStatisticsGds(String id){
		super(id);
	}

	@Length(min=1, max=40, message="年月长度必须介于 1 和 40 之间")
	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
	
	@Length(min=1, max=64, message="供电所长度必须介于 1 和 64 之间")
	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}
	
	@Length(min=0, max=100, message="供电所编号长度必须介于 0 和 100 之间")
	public String getGdsbh() {
		return gdsbh;
	}

	public void setGdsbh(String gdsbh) {
		this.gdsbh = gdsbh;
	}
	
	@Length(min=0, max=100, message="供电所名称长度必须介于 0 和 100 之间")
	public String getGdsmc() {
		return gdsmc;
	}

	public void setGdsmc(String gdsmc) {
		this.gdsmc = gdsmc;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getQsrq() {
		return qsrq;
	}

	public void setQsrq(Date qsrq) {
		this.qsrq = qsrq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJsrq() {
		return jsrq;
	}

	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}
	
	@Length(min=1, max=4, message="是否直供长度必须介于 1 和 4 之间")
	public String getIsIncludeds() {
		return isIncludeds;
	}

	public void setIsIncludeds(String isIncludeds) {
		this.isIncludeds = isIncludeds;
	}
	
	public String getPowerQua() {
		return powerQua;
	}

	public void setPowerQua(String powerQua) {
		this.powerQua = powerQua;
	}
	
	public String getNonpowerQua() {
		return nonpowerQua;
	}

	public void setNonpowerQua(String nonpowerQua) {
		this.nonpowerQua = nonpowerQua;
	}
	
	public String getSaleQua() {
		return saleQua;
	}

	public void setSaleQua(String saleQua) {
		this.saleQua = saleQua;
	}
	
	public String getWasteQua() {
		return wasteQua;
	}

	public void setWasteQua(String wasteQua) {
		this.wasteQua = wasteQua;
	}
	
	public String getTrwasteQua() {
		return trwasteQua;
	}

	public void setTrwasteQua(String trwasteQua) {
		this.trwasteQua = trwasteQua;
	}
	
	public String getPowerrate() {
		return powerrate;
	}

	public void setPowerrate(String powerrate) {
		this.powerrate = powerrate;
	}
	
	public String getWasteRate() {
		return wasteRate;
	}

	public void setWasteRate(String wasteRate) {
		this.wasteRate = wasteRate;
	}
	
	public String getCompWrate() {
		return compWrate;
	}

	public void setCompWrate(String compWrate) {
		this.compWrate = compWrate;
	}
	
	public String getSalequaInsdh() {
		return salequaInsdh;
	}

	public void setSalequaInsdh(String salequaInsdh) {
		this.salequaInsdh = salequaInsdh;
	}
	
	public String getWastequaInsdh() {
		return wastequaInsdh;
	}

	public void setWastequaInsdh(String wastequaInsdh) {
		this.wastequaInsdh = wastequaInsdh;
	}
	
	public String getTrwastequaInsdh() {
		return trwastequaInsdh;
	}

	public void setTrwastequaInsdh(String trwastequaInsdh) {
		this.trwastequaInsdh = trwastequaInsdh;
	}
	
	public String getWasterateInsdh() {
		return wasterateInsdh;
	}

	public void setWasterateInsdh(String wasterateInsdh) {
		this.wasterateInsdh = wasterateInsdh;
	}
	
	public String getCompwrateInsdh() {
		return compwrateInsdh;
	}

	public void setCompwrateInsdh(String compwrateInsdh) {
		this.compwrateInsdh = compwrateInsdh;
	}
	
}