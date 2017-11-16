/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 主变电量损失统计Entity
 * @author evay
 * @version 2017-04-23
 */
public class XsBdzzbss extends DataEntity<XsBdzzbss> {
	
	private static final long serialVersionUID = 1L;
	private String ym;		// 年月
	private Date qsrq;		// 起始日期
	private Date jsrq;		// 结束日期
	private String zbid;		// 主变
	private String zbbh;		// 主变编号
	private String zbmc;		// 主变名称
	private String ssbdz;		// 所属变电站
	private Double bysr;		// 本月输入
	private Double bysc;		// 本月输出
	private Double byss;		// 本月损失
	private Double byssl;		// 损失率
	private Double ljsr;		// 累计输入
	private Double ljsc;		// 累计输出
	private Double ljss;		// 累计损失
	private Double ljssl;		// 累计损失率
	private Double wgdl;		// 无功电量
	private Double ljwg;		// 累计无功
	private Double glys;		// 力率
	private Double ljglys;		// 累计力率
	
	public XsBdzzbss() {
		super();
	}

	public XsBdzzbss(String id){
		super(id);
	}

	@Length(min=0, max=40, message="年月长度必须介于 0 和 40 之间")
	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
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
	
	@Length(min=0, max=64, message="主变长度必须介于 0 和 64 之间")
	public String getZbid() {
		return zbid;
	}

	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	
	@Length(min=0, max=64, message="主变编号长度必须介于 0 和 64 之间")
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
	
	public Double getBysr() {
		return bysr;
	}

	public void setBysr(Double bysr) {
		this.bysr = bysr;
	}
	
	public Double getBysc() {
		return bysc;
	}

	public void setBysc(Double bysc) {
		this.bysc = bysc;
	}
	
	public Double getByss() {
		return byss;
	}

	public void setByss(Double byss) {
		this.byss = byss;
	}
	
	public Double getByssl() {
		return byssl;
	}

	public void setByssl(Double byssl) {
		this.byssl = byssl;
	}
	
	public Double getLjsr() {
		return ljsr;
	}

	public void setLjsr(Double ljsr) {
		this.ljsr = ljsr;
	}
	
	public Double getLjsc() {
		return ljsc;
	}

	public void setLjsc(Double ljsc) {
		this.ljsc = ljsc;
	}
	
	public Double getLjss() {
		return ljss;
	}

	public void setLjss(Double ljss) {
		this.ljss = ljss;
	}
	
	public Double getLjssl() {
		return ljssl;
	}

	public void setLjssl(Double ljssl) {
		this.ljssl = ljssl;
	}
	
	public Double getWgdl() {
		return wgdl;
	}

	public void setWgdl(Double wgdl) {
		this.wgdl = wgdl;
	}
	
	public Double getLjwg() {
		return ljwg;
	}

	public void setLjwg(Double ljwg) {
		this.ljwg = ljwg;
	}
	
	public Double getGlys() {
		return glys;
	}

	public void setGlys(Double glys) {
		this.glys = glys;
	}
	
	public Double getLjglys() {
		return ljglys;
	}

	public void setLjglys(Double ljglys) {
		this.ljglys = ljglys;
	}
	
}