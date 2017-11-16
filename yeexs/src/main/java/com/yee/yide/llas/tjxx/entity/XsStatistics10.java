/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 10kV线损统计结果Entity
 * @author evay_leec
 * @version 2017-11-14
 */
public class XsStatistics10 extends DataEntity<XsStatistics10> {
	
	private static final long serialVersionUID = 1L;
	private String ym;		// 年月
	private String lineId;		// 线路
	private String lineCode;		// 线路编号
	private String lineName;		// 线路名称
	private Date qsrq;		// 起始日期
	private Date jsrq;		// 结束日期
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
	private Double wasterateInsdh;		// 损失率（含四到户）
	private Double compwrateInsdh;		// 综合损失率（含四到户）
	private Double powerrate;		// 力率
	private Double spelinewaste;		// 专线线损
	private Double theoryTrwaste;		// 理论变损
	private Double theoryWrate;		// 理论损失率
	private Double theoryCompwrate;		// 理论综合损失率
	private Double wratePrscore;		// 线损奖罚分
	private Double wrateScore;		// 线损得分
	private Double powerPrscore;		// 力率奖罚分
	private Double powerScore;		// 力率得分
	private Double finalscore;		// 最终得分
	private Double theoryWaste;		// 理论线损率
	private Double wrateCheckscore;		// 线损得分
	private Double finalcheckscore;		// 最后得分
	private Double yglj;		// 有功累计
	private Double wglj;		// 无功累计
	private Double sslj;		// 损失累计
	private Double sclj;		// 销售累计
	
	public XsStatistics10() {
		super();
	}

	public XsStatistics10(String id){
		super(id);
	}

	@Length(min=1, max=40, message="年月长度必须介于 1 和 40 之间")
	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
	
	@Length(min=1, max=64, message="线路长度必须介于 1 和 64 之间")
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
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
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
	
	public Double getSpelinewaste() {
		return spelinewaste;
	}

	public void setSpelinewaste(Double spelinewaste) {
		this.spelinewaste = spelinewaste;
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
	
	public Double getFinalscore() {
		return finalscore;
	}

	public void setFinalscore(Double finalscore) {
		this.finalscore = finalscore;
	}
	
	public Double getTheoryWaste() {
		return theoryWaste;
	}

	public void setTheoryWaste(Double theoryWaste) {
		this.theoryWaste = theoryWaste;
	}
	
	public Double getWrateCheckscore() {
		return wrateCheckscore;
	}

	public void setWrateCheckscore(Double wrateCheckscore) {
		this.wrateCheckscore = wrateCheckscore;
	}
	
	public Double getFinalcheckscore() {
		return finalcheckscore;
	}

	public void setFinalcheckscore(Double finalcheckscore) {
		this.finalcheckscore = finalcheckscore;
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