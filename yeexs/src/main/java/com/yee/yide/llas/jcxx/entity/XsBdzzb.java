/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 变电站主变信息Entity
 * @author evay
 * @version 2017-04-16
 */
public class XsBdzzb extends DataEntity<XsBdzzb> {
	
	private static final long serialVersionUID = 1L;
	private String zbbh;		// 主变编号
	private String zbmc;		// 主变名称
	private String ssbdz;		// 所属变电站
	private String trsubstationName;		// 所属变电站
	private String zt;		// 状态
	
	public XsBdzzb() {
		super();
	}

	public XsBdzzb(String id){
		super(id);
	}

	@Length(min=0, max=50, message="主变编号长度必须介于 0 和 50 之间")
	public String getZbbh() {
		return zbbh;
	}

	public void setZbbh(String zbbh) {
		this.zbbh = zbbh;
	}
	
	@Length(min=0, max=100, message="主变名称长度必须介于 0 和 100 之间")
	public String getZbmc() {
		return zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}
	
	@Length(min=0, max=64, message="所属变电站长度必须介于 0 和 64 之间")
	public String getSsbdz() {
		return ssbdz;
	}

	public void setSsbdz(String ssbdz) {
		this.ssbdz = ssbdz;
	}

	public String getTrsubstationName() {
		return trsubstationName;
	}

	public void setTrsubstationName(String trsubstationName) {
		this.trsubstationName = trsubstationName;
	}

	@Length(min=0, max=4, message="状态长度必须介于 0 和 4 之间")
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
	
}