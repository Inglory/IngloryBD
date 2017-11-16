/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 历史线路信息查询Entity
 * @author evay_leec
 * @version 2017-10-29
 */
public class XsLineHis extends DataEntity<XsLineHis> {
	
	private static final long serialVersionUID = 1L;
	private String lineCode;		// 线路编号
	private String lineName;		// 线路名称
	private String trsubstationId;		// 变电站编号
	private String trsubstationName;		// 变电站名称
	private String isparline;		// 母线标志
	private String voltage;		// 电压等级
	private String isspecline;		// 专线标志
	private String fpSvalue;		// 正向有功起码
	private String fpEvalue;		// 正向有功止码
	private String fpSubjoinqua;		// 正向有功追补
	private String fpChmeter;		// 正向有功换表
	private String fpExcerpqua;		// 正向有功抄见
	private String fupSvalue;		// 正向无功起码
	private String fupEvalue;		// 正向无功止码
	private String fupSubjoinqua;		// 正向无功追补
	private String fupChmeter;		// 正向无功换表
	private String fupExcerpqua;		// 正向无功抄见
	private String fpTotalqua;		// 正向有功合计
	private String fupTotalqua;		// 正向无功合计
	private String rpSvalue;		// 反向有功起码
	private String rpEvalue;		// 反向有功止码
	private String rpSubjoinqua;		// 反向有功追补
	private String rpChmeter;		// 反向有功换表
	private String rpExcerpqua;		// 反向有功抄见
	private String rpTotalqua;		// 反向有功合计
	private String rupSvalue;		// 反向无功起码
	private String rupEvalue;		// 反向无功止码
	private String rupSubjoinqua;		// 反向无功追补
	private String rupChmeter;		// 反向无功换表
	private String rupExcerpqua;		// 反向无功抄见
	private String rupTotalqua;		// 反向无功合计
	private String pTotalqua;		// 有功合计
	private String upTotalqua;		// 无功合计
	private String resistance;		// 线路电阻
	private String unloadWaste;		// 空载损耗
	private String cirWaste;		// 短路损耗
	private String temperature;		// 温度
	private String k1;		// K1系数
	private String k;		// K系数
	private String hours;		// 用电小时数
	private String intefactor;		// 倍率
	private String maxcapacity;		// 计数容量
	private String capacity;		// 总容量
	private String powerfactor;		// 执行力率标准
	private String scheWrate;		// 计划线损率
	private String changesign;		// 换表标志
	private String prflag;		// 正反向标志
	private Date qmdate;		// 起码日期
	private Date zmdate;		// 止码日期
	private String rptvissign;		// 报表显示标志
	
	public XsLineHis() {
		super();
	}

	public XsLineHis(String id){
		super(id);
	}

	@Length(min=0, max=12, message="线路编号长度必须介于 0 和 12 之间")
	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	
	@Length(min=0, max=40, message="线路名称长度必须介于 0 和 40 之间")
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	@Length(min=0, max=64, message="变电站编号长度必须介于 0 和 64 之间")
	public String getTrsubstationId() {
		return trsubstationId;
	}

	public void setTrsubstationId(String trsubstationId) {
		this.trsubstationId = trsubstationId;
	}
	
	@Length(min=0, max=64, message="变电站名称长度必须介于 0 和 64 之间")
	public String getTrsubstationName() {
		return trsubstationName;
	}

	public void setTrsubstationName(String trsubstationName) {
		this.trsubstationName = trsubstationName;
	}
	
	@Length(min=0, max=1, message="母线标志长度必须介于 0 和 1 之间")
	public String getIsparline() {
		return isparline;
	}

	public void setIsparline(String isparline) {
		this.isparline = isparline;
	}
	
	@Length(min=0, max=20, message="电压等级长度必须介于 0 和 20 之间")
	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	
	@Length(min=0, max=1, message="专线标志长度必须介于 0 和 1 之间")
	public String getIsspecline() {
		return isspecline;
	}

	public void setIsspecline(String isspecline) {
		this.isspecline = isspecline;
	}
	
	public String getFpSvalue() {
		return fpSvalue;
	}

	public void setFpSvalue(String fpSvalue) {
		this.fpSvalue = fpSvalue;
	}
	
	public String getFpEvalue() {
		return fpEvalue;
	}

	public void setFpEvalue(String fpEvalue) {
		this.fpEvalue = fpEvalue;
	}
	
	public String getFpSubjoinqua() {
		return fpSubjoinqua;
	}

	public void setFpSubjoinqua(String fpSubjoinqua) {
		this.fpSubjoinqua = fpSubjoinqua;
	}
	
	public String getFpChmeter() {
		return fpChmeter;
	}

	public void setFpChmeter(String fpChmeter) {
		this.fpChmeter = fpChmeter;
	}
	
	public String getFpExcerpqua() {
		return fpExcerpqua;
	}

	public void setFpExcerpqua(String fpExcerpqua) {
		this.fpExcerpqua = fpExcerpqua;
	}
	
	public String getFupSvalue() {
		return fupSvalue;
	}

	public void setFupSvalue(String fupSvalue) {
		this.fupSvalue = fupSvalue;
	}
	
	public String getFupEvalue() {
		return fupEvalue;
	}

	public void setFupEvalue(String fupEvalue) {
		this.fupEvalue = fupEvalue;
	}
	
	public String getFupSubjoinqua() {
		return fupSubjoinqua;
	}

	public void setFupSubjoinqua(String fupSubjoinqua) {
		this.fupSubjoinqua = fupSubjoinqua;
	}
	
	public String getFupChmeter() {
		return fupChmeter;
	}

	public void setFupChmeter(String fupChmeter) {
		this.fupChmeter = fupChmeter;
	}
	
	public String getFupExcerpqua() {
		return fupExcerpqua;
	}

	public void setFupExcerpqua(String fupExcerpqua) {
		this.fupExcerpqua = fupExcerpqua;
	}
	
	public String getFpTotalqua() {
		return fpTotalqua;
	}

	public void setFpTotalqua(String fpTotalqua) {
		this.fpTotalqua = fpTotalqua;
	}
	
	public String getFupTotalqua() {
		return fupTotalqua;
	}

	public void setFupTotalqua(String fupTotalqua) {
		this.fupTotalqua = fupTotalqua;
	}
	
	public String getRpSvalue() {
		return rpSvalue;
	}

	public void setRpSvalue(String rpSvalue) {
		this.rpSvalue = rpSvalue;
	}
	
	public String getRpEvalue() {
		return rpEvalue;
	}

	public void setRpEvalue(String rpEvalue) {
		this.rpEvalue = rpEvalue;
	}
	
	public String getRpSubjoinqua() {
		return rpSubjoinqua;
	}

	public void setRpSubjoinqua(String rpSubjoinqua) {
		this.rpSubjoinqua = rpSubjoinqua;
	}
	
	public String getRpChmeter() {
		return rpChmeter;
	}

	public void setRpChmeter(String rpChmeter) {
		this.rpChmeter = rpChmeter;
	}
	
	public String getRpExcerpqua() {
		return rpExcerpqua;
	}

	public void setRpExcerpqua(String rpExcerpqua) {
		this.rpExcerpqua = rpExcerpqua;
	}
	
	public String getRpTotalqua() {
		return rpTotalqua;
	}

	public void setRpTotalqua(String rpTotalqua) {
		this.rpTotalqua = rpTotalqua;
	}
	
	public String getRupSvalue() {
		return rupSvalue;
	}

	public void setRupSvalue(String rupSvalue) {
		this.rupSvalue = rupSvalue;
	}
	
	public String getRupEvalue() {
		return rupEvalue;
	}

	public void setRupEvalue(String rupEvalue) {
		this.rupEvalue = rupEvalue;
	}
	
	public String getRupSubjoinqua() {
		return rupSubjoinqua;
	}

	public void setRupSubjoinqua(String rupSubjoinqua) {
		this.rupSubjoinqua = rupSubjoinqua;
	}
	
	public String getRupChmeter() {
		return rupChmeter;
	}

	public void setRupChmeter(String rupChmeter) {
		this.rupChmeter = rupChmeter;
	}
	
	public String getRupExcerpqua() {
		return rupExcerpqua;
	}

	public void setRupExcerpqua(String rupExcerpqua) {
		this.rupExcerpqua = rupExcerpqua;
	}
	
	public String getRupTotalqua() {
		return rupTotalqua;
	}

	public void setRupTotalqua(String rupTotalqua) {
		this.rupTotalqua = rupTotalqua;
	}
	
	public String getPTotalqua() {
		return pTotalqua;
	}

	public void setPTotalqua(String pTotalqua) {
		this.pTotalqua = pTotalqua;
	}
	
	public String getUpTotalqua() {
		return upTotalqua;
	}

	public void setUpTotalqua(String upTotalqua) {
		this.upTotalqua = upTotalqua;
	}
	
	public String getResistance() {
		return resistance;
	}

	public void setResistance(String resistance) {
		this.resistance = resistance;
	}
	
	public String getUnloadWaste() {
		return unloadWaste;
	}

	public void setUnloadWaste(String unloadWaste) {
		this.unloadWaste = unloadWaste;
	}
	
	public String getCirWaste() {
		return cirWaste;
	}

	public void setCirWaste(String cirWaste) {
		this.cirWaste = cirWaste;
	}
	
	@Length(min=0, max=11, message="温度长度必须介于 0 和 11 之间")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	public String getK1() {
		return k1;
	}

	public void setK1(String k1) {
		this.k1 = k1;
	}
	
	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}
	
	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}
	
	public String getIntefactor() {
		return intefactor;
	}

	public void setIntefactor(String intefactor) {
		this.intefactor = intefactor;
	}
	
	public String getMaxcapacity() {
		return maxcapacity;
	}

	public void setMaxcapacity(String maxcapacity) {
		this.maxcapacity = maxcapacity;
	}
	
	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	@Length(min=0, max=2, message="执行力率标准长度必须介于 0 和 2 之间")
	public String getPowerfactor() {
		return powerfactor;
	}

	public void setPowerfactor(String powerfactor) {
		this.powerfactor = powerfactor;
	}
	
	public String getScheWrate() {
		return scheWrate;
	}

	public void setScheWrate(String scheWrate) {
		this.scheWrate = scheWrate;
	}
	
	@Length(min=0, max=1, message="换表标志长度必须介于 0 和 1 之间")
	public String getChangesign() {
		return changesign;
	}

	public void setChangesign(String changesign) {
		this.changesign = changesign;
	}
	
	@Length(min=0, max=1, message="正反向标志长度必须介于 0 和 1 之间")
	public String getPrflag() {
		return prflag;
	}

	public void setPrflag(String prflag) {
		this.prflag = prflag;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getQmdate() {
		return qmdate;
	}

	public void setQmdate(Date qmdate) {
		this.qmdate = qmdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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