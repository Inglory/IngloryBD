/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yee.yide.common.config.Global;
import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 线路信息Entity
 * @author evay_leec
 * @version 2017-04-13
 */
public class XsLine extends DataEntity<XsLine> {
	
	private static final long serialVersionUID = 1L;
	private String lineCode;		// 线路编号
	private String lineName;		// 线路名称
	private String trsubstationId;		// 变电站
	private String trsubstationName;		// 变电站
	private String isparline;		// 母线标志
	private String voltage;		// 电压等级
	private String isspecline;		// 专线标志
	private Double fpSvalue;		// 正向有功起码
	private Double fpEvalue;		// 正向有功止码
	private Double fpSubjoinqua;		// 正向有功追补
	private Double fpChmeter;		// 正向有功换表
	private Double fpExcerpqua;		// 正向有功抄见
	private Double fupSvalue;		// 正向无功起码
	private Double fupEvalue;		// 正向无功止码
	private Double fupSubjoinqua;		// 正向无功追补
	private Double fupChmeter;		// 正向无功换表
	private Double fupExcerpqua;		// 正向无功抄见
	private Double fpTotalqua;		// 正向有功合计
	private Double fupTotalqua;		// 正向无功合计
	private Double rpSvalue;		// 反向有功起码
	private Double rpEvalue;		// 反向有功止码
	private Double rpSubjoinqua;		// 反向有功追补
	private Double rpChmeter;		// 反向有功换表
	private Double rpExcerpqua;		// 反向有功抄见
	private Double rpTotalqua;		// 反向有功合计
	private Double rupSvalue;		// 反向无功起码
	private Double rupEvalue;		// 反向无功止码
	private Double rupSubjoinqua;		// 反向无功追补
	private Double rupChmeter;		// 反向无功换表
	private Double rupExcerpqua;		// 反向无功抄见
	private Double rupTotalqua;		// 反向无功合计
	private Double pTotalqua;		// 有功合计
	private Double upTotalqua;		// 无功合计
	private Double resistance;		// 线路电阻
	private Double unloadWaste;		// 空载损耗
	private Double cirWaste;		// 短路损耗
	private Integer temperature;		// 温度
	private Double k1;		// K1系数
	private Double k;		// K系数
	private Double hours;		// 用电小时数
	private Double intefactor;		// 倍率
	private Double maxcapacity;		// 计数容量
	private Integer capacity;		// 总容量
	private String powerfactor;		// 执行力率标准
	private Double scheWrate;		// 计划线损率
	private String changesign;		// 换表标志
	private String prflag;		// 正反向标志
	private Date qmdate;		// 起码日期
	private Date zmdate;		// 止码日期
	private String rptvissign;		// 报表显示标志
	
	public XsLine() {
		super();
		this.isparline = Global.NO;
		this.isspecline = Global.NO;
		this.changesign = Global.NO;
		this.prflag = Global.NO;
	}

	public XsLine(String id){
		super(id);
	}

	@Length(min=1, max=20, message="线路编号长度必须介于 1 和 20之间")
	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	
	@Length(min=1, max=40, message="线路名称长度必须介于 1 和 40 之间")
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	@Length(min=1, max=64, message="变电站长度必须介于 1 和 64 之间")
	public String getTrsubstationId() {
		return trsubstationId;
	}

	public void setTrsubstationId(String trsubstationId) {
		this.trsubstationId = trsubstationId;
	}

	public String getTrsubstationName() {
		return trsubstationName;
	}

	public void setTrsubstationName(String trsubstationName) {
		this.trsubstationName = trsubstationName;
	}

	@Length(min=1, max=1, message="母线标志必选选择")
	public String getIsparline() {
		return isparline;
	}

	public void setIsparline(String isparline) {
		this.isparline = isparline;
	}
	
	@Length(min=1, max=20, message="电压等级必须选择")
	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	
	@Length(min=1, max=1, message="专线标志必须选择")
	public String getIsspecline() {
		return isspecline;
	}

	public void setIsspecline(String isspecline) {
		this.isspecline = isspecline;
	}
	
	public Double getFpSvalue() {
		return fpSvalue;
	}

	public void setFpSvalue(Double fpSvalue) {
		this.fpSvalue = fpSvalue;
	}
	
	public Double getFpEvalue() {
		return fpEvalue;
	}

	public void setFpEvalue(Double fpEvalue) {
		this.fpEvalue = fpEvalue;
	}
	
	public Double getFpSubjoinqua() {
		return fpSubjoinqua;
	}

	public void setFpSubjoinqua(Double fpSubjoinqua) {
		this.fpSubjoinqua = fpSubjoinqua;
	}
	
	public Double getFpChmeter() {
		return fpChmeter;
	}

	public void setFpChmeter(Double fpChmeter) {
		this.fpChmeter = fpChmeter;
	}
	
	public Double getFpExcerpqua() {
		return fpExcerpqua;
	}

	public void setFpExcerpqua(Double fpExcerpqua) {
		this.fpExcerpqua = fpExcerpqua;
	}
	
	public Double getFupSvalue() {
		return fupSvalue;
	}

	public void setFupSvalue(Double fupSvalue) {
		this.fupSvalue = fupSvalue;
	}
	
	public Double getFupEvalue() {
		return fupEvalue;
	}

	public void setFupEvalue(Double fupEvalue) {
		this.fupEvalue = fupEvalue;
	}
	
	public Double getFupSubjoinqua() {
		return fupSubjoinqua;
	}

	public void setFupSubjoinqua(Double fupSubjoinqua) {
		this.fupSubjoinqua = fupSubjoinqua;
	}
	
	public Double getFupChmeter() {
		return fupChmeter;
	}

	public void setFupChmeter(Double fupChmeter) {
		this.fupChmeter = fupChmeter;
	}
	
	public Double getFupExcerpqua() {
		return fupExcerpqua;
	}

	public void setFupExcerpqua(Double fupExcerpqua) {
		this.fupExcerpqua = fupExcerpqua;
	}
	
	public Double getFpTotalqua() {
		return fpTotalqua;
	}

	public void setFpTotalqua(Double fpTotalqua) {
		this.fpTotalqua = fpTotalqua;
	}
	
	public Double getFupTotalqua() {
		return fupTotalqua;
	}

	public void setFupTotalqua(Double fupTotalqua) {
		this.fupTotalqua = fupTotalqua;
	}
	
	public Double getRpSvalue() {
		return rpSvalue;
	}

	public void setRpSvalue(Double rpSvalue) {
		this.rpSvalue = rpSvalue;
	}
	
	public Double getRpEvalue() {
		return rpEvalue;
	}

	public void setRpEvalue(Double rpEvalue) {
		this.rpEvalue = rpEvalue;
	}
	
	public Double getRpSubjoinqua() {
		return rpSubjoinqua;
	}

	public void setRpSubjoinqua(Double rpSubjoinqua) {
		this.rpSubjoinqua = rpSubjoinqua;
	}
	
	public Double getRpChmeter() {
		return rpChmeter;
	}

	public void setRpChmeter(Double rpChmeter) {
		this.rpChmeter = rpChmeter;
	}
	
	public Double getRpExcerpqua() {
		return rpExcerpqua;
	}

	public void setRpExcerpqua(Double rpExcerpqua) {
		this.rpExcerpqua = rpExcerpqua;
	}
	
	public Double getRpTotalqua() {
		return rpTotalqua;
	}

	public void setRpTotalqua(Double rpTotalqua) {
		this.rpTotalqua = rpTotalqua;
	}
	
	public Double getRupSvalue() {
		return rupSvalue;
	}

	public void setRupSvalue(Double rupSvalue) {
		this.rupSvalue = rupSvalue;
	}
	
	public Double getRupEvalue() {
		return rupEvalue;
	}

	public void setRupEvalue(Double rupEvalue) {
		this.rupEvalue = rupEvalue;
	}
	
	public Double getRupSubjoinqua() {
		return rupSubjoinqua;
	}

	public void setRupSubjoinqua(Double rupSubjoinqua) {
		this.rupSubjoinqua = rupSubjoinqua;
	}
	
	public Double getRupChmeter() {
		return rupChmeter;
	}

	public void setRupChmeter(Double rupChmeter) {
		this.rupChmeter = rupChmeter;
	}
	
	public Double getRupExcerpqua() {
		return rupExcerpqua;
	}

	public void setRupExcerpqua(Double rupExcerpqua) {
		this.rupExcerpqua = rupExcerpqua;
	}
	
	public Double getRupTotalqua() {
		return rupTotalqua;
	}

	public void setRupTotalqua(Double rupTotalqua) {
		this.rupTotalqua = rupTotalqua;
	}

	public Double getpTotalqua() {
		return pTotalqua;
	}

	public void setpTotalqua(Double pTotalqua) {
		this.pTotalqua = pTotalqua;
	}

	public Double getUpTotalqua() {
		return upTotalqua;
	}

	public void setUpTotalqua(Double upTotalqua) {
		this.upTotalqua = upTotalqua;
	}
	
	public Double getResistance() {
		return resistance;
	}

	public void setResistance(Double resistance) {
		this.resistance = resistance;
	}
	
	public Double getUnloadWaste() {
		return unloadWaste;
	}

	public void setUnloadWaste(Double unloadWaste) {
		this.unloadWaste = unloadWaste;
	}
	
	public Double getCirWaste() {
		return cirWaste;
	}

	public void setCirWaste(Double cirWaste) {
		this.cirWaste = cirWaste;
	}
	
	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
	
	public Double getK1() {
		return k1;
	}

	public void setK1(Double k1) {
		this.k1 = k1;
	}
	
	public Double getK() {
		return k;
	}

	public void setK(Double k) {
		this.k = k;
	}
	
	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}
	@NotNull(message="倍率不能为空")
	public Double getIntefactor() {
		return intefactor;
	}

	public void setIntefactor(Double intefactor) {
		this.intefactor = intefactor;
	}
	
	public Double getMaxcapacity() {
		return maxcapacity;
	}

	public void setMaxcapacity(Double maxcapacity) {
		this.maxcapacity = maxcapacity;
	}
	
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
//	@Length(min=0, max=2, message="执行力率标准长度必须介于 0 和 2 之间")
	public String getPowerfactor() {
		return powerfactor;
	}

	public void setPowerfactor(String powerfactor) {
		this.powerfactor = powerfactor;
	}
	
	public Double getScheWrate() {
		return scheWrate;
	}

	public void setScheWrate(Double scheWrate) {
		this.scheWrate = scheWrate;
	}
	
//	@Length(min=0, max=1, message="换表标志长度必须介于 0 和 1 之间")
	public String getChangesign() {
		return changesign;
	}

	public void setChangesign(String changesign) {
		this.changesign = changesign;
	}
	
	@Length(min=1, max=1, message="正反向标志必须选择")
	public String getPrflag() {
		return prflag;
	}

	public void setPrflag(String prflag) {
		this.prflag = prflag;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getQmdate() {
		return qmdate;
	}

	public void setQmdate(Date qmdate) {
		this.qmdate = qmdate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getZmdate() {
		return zmdate;
	}

	public void setZmdate(Date zmdate) {
		this.zmdate = zmdate;
	}

	@Length(min=0, max=20, message="报表显示标志长度必须介于 0 和 20 之间")
	public String getRptvissign() {
		return rptvissign;
	}

	public void setRptvissign(String rptvissign) {
		this.rptvissign = rptvissign;
	}
	
}