/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 变电站损失信息Entity
 * @author evay_leec
 * @version 2017-04-24
 */
public class XsStatisticsBdz extends DataEntity<XsStatisticsBdz> {
	
	private static final long serialVersionUID = 1L;
	private String ym;		// ym
	private String trsubstationId;		// trsubstation_id
	private String powerQua;		// power_qua
	private String nonpowerQua;		// nonpower_qua
	private String saleQua;		// sale_qua
	private String wasteQua;		// waste_qua
	private String trwasteQua;		// trwaste_qua
	private String powerrate;		// powerrate
	private String wasteRate;		// waste_rate
	private String compWrate;		// comp_wrate
	private String salequaInsdh;		// salequa_insdh
	private String wastequaInsdh;		// wastequa_insdh
	private String trwastequaInsdh;		// trwastequa_insdh
	private String wasterateInsdh;		// wasterate_insdh
	private String compwrateInsdh;		// compwrate_insdh
	private String yglj;		// yglj
	private String wglj;		// wglj
	private String sslj;		// sslj
	private String sclj;		// sclj
	
	public XsStatisticsBdz() {
		super();
	}

	public XsStatisticsBdz(String id){
		super(id);
	}

	@Length(min=1, max=40, message="ym长度必须介于 1 和 40 之间")
	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
	
	@Length(min=1, max=8, message="trsubstation_id长度必须介于 1 和 8 之间")
	public String getTrsubstationId() {
		return trsubstationId;
	}

	public void setTrsubstationId(String trsubstationId) {
		this.trsubstationId = trsubstationId;
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
	
	public String getYglj() {
		return yglj;
	}

	public void setYglj(String yglj) {
		this.yglj = yglj;
	}
	
	public String getWglj() {
		return wglj;
	}

	public void setWglj(String wglj) {
		this.wglj = wglj;
	}
	
	public String getSslj() {
		return sslj;
	}

	public void setSslj(String sslj) {
		this.sslj = sslj;
	}
	
	public String getSclj() {
		return sclj;
	}

	public void setSclj(String sclj) {
		this.sclj = sclj;
	}
	
}