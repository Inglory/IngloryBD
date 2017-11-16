/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 历史线损计算电量Entity
 * @author evay_leec
 * @version 2017-10-29
 */
public class XsLinewastageHis extends DataEntity<XsLinewastageHis> {
	
	private static final long serialVersionUID = 1L;
	private String lineId;		// 线路
	private String lineCode;		// 线路编号
	private String linename;		// 线路名称
	private Date qsrq;		// 起始日期
	private Date jsrq;		// 结束日期
	private String saleQua;		// 销售电量
	private String salequaInsdh;		// 销售电量（含四到户）
	private String actExcerpqua;		// 实抄电量
	private String trwasteExsdh;		// 变损电量
	private String trwasteInsdh;		// 变损电量（含四到户）
	private String sdhTrwaste;		// 四到户变损电量
	private String comTrwaste;		// 综损电量
	private String comtrwasteInsdh;		// 综损电量（含四到户）
	private String sdhComtrwaste;		// 四到户综损电量
	private String spelineWaste;		// 专线线损
	private String qua1;		// 有功合计
	private String qua2;		// 无功合计
	private String wasteQua;		// 损失电量
	private String theoryTrwaste;		// 理论变损率
	private String theoryWrate;		// 理论损失率
	private String theoryCompwrate;		// 理论综损率
	private String actWrate;		// 实际损失率
	private String compWrate;		// 综合损失率
	private String wratePrscore;		// 损失考核得分
	private String wrateScore;		// 损失得分
	private String actPowerrate;		// 实际力率
	private String powerPrscore;		// 力率考核得分
	private String powerScore;		// 力率得分
	private String burgle;		// burgle
	private String copyerror;		// 抄标错误
	private String finalscore;		// 最终得分
	private String others;		// 其他得分
	
	public XsLinewastageHis() {
		super();
	}

	public XsLinewastageHis(String id){
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
	
	public String getSaleQua() {
		return saleQua;
	}

	public void setSaleQua(String saleQua) {
		this.saleQua = saleQua;
	}
	
	public String getSalequaInsdh() {
		return salequaInsdh;
	}

	public void setSalequaInsdh(String salequaInsdh) {
		this.salequaInsdh = salequaInsdh;
	}
	
	public String getActExcerpqua() {
		return actExcerpqua;
	}

	public void setActExcerpqua(String actExcerpqua) {
		this.actExcerpqua = actExcerpqua;
	}
	
	public String getTrwasteExsdh() {
		return trwasteExsdh;
	}

	public void setTrwasteExsdh(String trwasteExsdh) {
		this.trwasteExsdh = trwasteExsdh;
	}
	
	public String getTrwasteInsdh() {
		return trwasteInsdh;
	}

	public void setTrwasteInsdh(String trwasteInsdh) {
		this.trwasteInsdh = trwasteInsdh;
	}
	
	public String getSdhTrwaste() {
		return sdhTrwaste;
	}

	public void setSdhTrwaste(String sdhTrwaste) {
		this.sdhTrwaste = sdhTrwaste;
	}
	
	public String getComTrwaste() {
		return comTrwaste;
	}

	public void setComTrwaste(String comTrwaste) {
		this.comTrwaste = comTrwaste;
	}
	
	public String getComtrwasteInsdh() {
		return comtrwasteInsdh;
	}

	public void setComtrwasteInsdh(String comtrwasteInsdh) {
		this.comtrwasteInsdh = comtrwasteInsdh;
	}
	
	public String getSdhComtrwaste() {
		return sdhComtrwaste;
	}

	public void setSdhComtrwaste(String sdhComtrwaste) {
		this.sdhComtrwaste = sdhComtrwaste;
	}
	
	public String getSpelineWaste() {
		return spelineWaste;
	}

	public void setSpelineWaste(String spelineWaste) {
		this.spelineWaste = spelineWaste;
	}
	
	public String getQua1() {
		return qua1;
	}

	public void setQua1(String qua1) {
		this.qua1 = qua1;
	}
	
	public String getQua2() {
		return qua2;
	}

	public void setQua2(String qua2) {
		this.qua2 = qua2;
	}
	
	public String getWasteQua() {
		return wasteQua;
	}

	public void setWasteQua(String wasteQua) {
		this.wasteQua = wasteQua;
	}
	
	public String getTheoryTrwaste() {
		return theoryTrwaste;
	}

	public void setTheoryTrwaste(String theoryTrwaste) {
		this.theoryTrwaste = theoryTrwaste;
	}
	
	public String getTheoryWrate() {
		return theoryWrate;
	}

	public void setTheoryWrate(String theoryWrate) {
		this.theoryWrate = theoryWrate;
	}
	
	public String getTheoryCompwrate() {
		return theoryCompwrate;
	}

	public void setTheoryCompwrate(String theoryCompwrate) {
		this.theoryCompwrate = theoryCompwrate;
	}
	
	public String getActWrate() {
		return actWrate;
	}

	public void setActWrate(String actWrate) {
		this.actWrate = actWrate;
	}
	
	public String getCompWrate() {
		return compWrate;
	}

	public void setCompWrate(String compWrate) {
		this.compWrate = compWrate;
	}
	
	public String getWratePrscore() {
		return wratePrscore;
	}

	public void setWratePrscore(String wratePrscore) {
		this.wratePrscore = wratePrscore;
	}
	
	public String getWrateScore() {
		return wrateScore;
	}

	public void setWrateScore(String wrateScore) {
		this.wrateScore = wrateScore;
	}
	
	public String getActPowerrate() {
		return actPowerrate;
	}

	public void setActPowerrate(String actPowerrate) {
		this.actPowerrate = actPowerrate;
	}
	
	public String getPowerPrscore() {
		return powerPrscore;
	}

	public void setPowerPrscore(String powerPrscore) {
		this.powerPrscore = powerPrscore;
	}
	
	public String getPowerScore() {
		return powerScore;
	}

	public void setPowerScore(String powerScore) {
		this.powerScore = powerScore;
	}
	
	public String getBurgle() {
		return burgle;
	}

	public void setBurgle(String burgle) {
		this.burgle = burgle;
	}
	
	public String getCopyerror() {
		return copyerror;
	}

	public void setCopyerror(String copyerror) {
		this.copyerror = copyerror;
	}
	
	public String getFinalscore() {
		return finalscore;
	}

	public void setFinalscore(String finalscore) {
		this.finalscore = finalscore;
	}
	
	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}
	
}