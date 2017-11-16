/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

import java.util.Date;

/**
 * 电量计算Entity
 * @author evay
 * @version 2017-04-16
 */
public class XsLinewastage extends DataEntity<XsLinewastage> {
	
	private static final long serialVersionUID = 1L;
	private String lineId;		// 线路
	private String lineCode;		// 线路编号
	private String linename;		// 线路名称
	private Date qsrq;		// 起始日期
	private Date jsrq;		// 结束日期
	private Double saleQua;		// 销售电量
	private Double salequaInsdh;		// 销售电量（含四到户）
	private Double actExcerpqua;		// 实抄电量
	private Double trwasteExsdh;		// 变损电量
	private Double trwasteInsdh;		// 变损电量（含四到户）
	private Double sdhTrwaste;		// 四到户变损电量
	private Double comTrwaste;		// 综损电量
	private Double comtrwasteInsdh;		// 综损电量（含四到户）
	private Double sdhComtrwaste;		// 四到户综损电量
	private Double spelineWaste;		// 专线线损
	private Double qua1;		// 电量一
	private Double qua2;		// 电量二
	private Double wasteQua;		// 损失电量
	private Double theoryTrwaste;		// 理论变损率
	private Double theoryWrate;		// 理论损失率
	private Double theoryCompwrate;		// 理论综损率
	private Double actWrate;		// 实际损失率
	private Double compWrate;		// 综合损失率
	private Double wratePrscore;		// 损失考核得分
	private Double wrateScore;		// 损失得分
	private Double actPowerrate;		// 实际力率
	private Double powerPrscore;		// 力率考核得分
	private Double powerScore;		// 力率得分
	private Double burgle;		// burgle
	private Double copyerror;		// copyerror
	private Double finalscore;		// 最终得分
	private Double others;		// 其他得分
	
	public XsLinewastage() {
		super();
	}

	public XsLinewastage(String id){
		super(id);
	}

	@Length(min=1, max=64, message="线路长度必须介于 1 和 64 之间")
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
	@Length(min=0, max=64, message="线路编号长度必须介于 0 和 64 之间")
	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	
	@Length(min=0, max=200, message="线路名称长度必须介于 0 和 200 之间")
	public String getLinename() {
		return linename;
	}

	public void setLinename(String linename) {
		this.linename = linename;
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

	public Double getSaleQua() {
		return saleQua;
	}

	public void setSaleQua(Double saleQua) {
		this.saleQua = saleQua;
	}

	public Double getSalequaInsdh() {
		return salequaInsdh;
	}

	public void setSalequaInsdh(Double salequaInsdh) {
		this.salequaInsdh = salequaInsdh;
	}

	public Double getActExcerpqua() {
		return actExcerpqua;
	}

	public void setActExcerpqua(Double actExcerpqua) {
		this.actExcerpqua = actExcerpqua;
	}

	public Double getTrwasteExsdh() {
		return trwasteExsdh;
	}

	public void setTrwasteExsdh(Double trwasteExsdh) {
		this.trwasteExsdh = trwasteExsdh;
	}

	public Double getTrwasteInsdh() {
		return trwasteInsdh;
	}

	public void setTrwasteInsdh(Double trwasteInsdh) {
		this.trwasteInsdh = trwasteInsdh;
	}

	public Double getSdhTrwaste() {
		return sdhTrwaste;
	}

	public void setSdhTrwaste(Double sdhTrwaste) {
		this.sdhTrwaste = sdhTrwaste;
	}

	public Double getComTrwaste() {
		return comTrwaste;
	}

	public void setComTrwaste(Double comTrwaste) {
		this.comTrwaste = comTrwaste;
	}

	public Double getComtrwasteInsdh() {
		return comtrwasteInsdh;
	}

	public void setComtrwasteInsdh(Double comtrwasteInsdh) {
		this.comtrwasteInsdh = comtrwasteInsdh;
	}

	public Double getSdhComtrwaste() {
		return sdhComtrwaste;
	}

	public void setSdhComtrwaste(Double sdhComtrwaste) {
		this.sdhComtrwaste = sdhComtrwaste;
	}

	public Double getSpelineWaste() {
		return spelineWaste;
	}

	public void setSpelineWaste(Double spelineWaste) {
		this.spelineWaste = spelineWaste;
	}

	public Double getQua1() {
		return qua1;
	}

	public void setQua1(Double qua1) {
		this.qua1 = qua1;
	}

	public Double getQua2() {
		return qua2;
	}

	public void setQua2(Double qua2) {
		this.qua2 = qua2;
	}

	public Double getWasteQua() {
		return wasteQua;
	}

	public void setWasteQua(Double wasteQua) {
		this.wasteQua = wasteQua;
	}

	public Double getTheoryTrwaste() {
		return theoryTrwaste;
	}

	public void setTheoryTrwaste(Double theoryTrwaste) {
		this.theoryTrwaste = theoryTrwaste;
	}

	public Double getTheoryWrate() {
		return theoryWrate;
	}

	public void setTheoryWrate(Double theoryWrate) {
		this.theoryWrate = theoryWrate;
	}

	public Double getTheoryCompwrate() {
		return theoryCompwrate;
	}

	public void setTheoryCompwrate(Double theoryCompwrate) {
		this.theoryCompwrate = theoryCompwrate;
	}

	public Double getActWrate() {
		return actWrate;
	}

	public void setActWrate(Double actWrate) {
		this.actWrate = actWrate;
	}

	public Double getCompWrate() {
		return compWrate;
	}

	public void setCompWrate(Double compWrate) {
		this.compWrate = compWrate;
	}

	public Double getWratePrscore() {
		return wratePrscore;
	}

	public void setWratePrscore(Double wratePrscore) {
		this.wratePrscore = wratePrscore;
	}

	public Double getWrateScore() {
		return wrateScore;
	}

	public void setWrateScore(Double wrateScore) {
		this.wrateScore = wrateScore;
	}

	public Double getActPowerrate() {
		return actPowerrate;
	}

	public void setActPowerrate(Double actPowerrate) {
		this.actPowerrate = actPowerrate;
	}

	public Double getPowerPrscore() {
		return powerPrscore;
	}

	public void setPowerPrscore(Double powerPrscore) {
		this.powerPrscore = powerPrscore;
	}

	public Double getPowerScore() {
		return powerScore;
	}

	public void setPowerScore(Double powerScore) {
		this.powerScore = powerScore;
	}

	public Double getBurgle() {
		return burgle;
	}

	public void setBurgle(Double burgle) {
		this.burgle = burgle;
	}

	public Double getCopyerror() {
		return copyerror;
	}

	public void setCopyerror(Double copyerror) {
		this.copyerror = copyerror;
	}

	public Double getFinalscore() {
		return finalscore;
	}

	public void setFinalscore(Double finalscore) {
		this.finalscore = finalscore;
	}

	public Double getOthers() {
		return others;
	}

	public void setOthers(Double others) {
		this.others = others;
	}
}