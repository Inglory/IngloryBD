/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yee.yide.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 母线平衡统计Entity
 * @author evay
 * @version 2017-04-19
 */
public class XsParlinebalance extends DataEntity<XsParlinebalance> {
	
	private static final long serialVersionUID = 1L;
	private String ym;		// 年月
	private Date qsrq;		// 起始日期
	private Date jsrq;		// 结束日期
	private String lineId;		// 线路
	private String lineCode;		// 线路编号
	private String lineName;		// 线路名称
	private String trsubstationId;		// 变电站
	private Double powerQua;		// 有功电量
	private Double saleQua;		// 销售电量
	private Double wasteQua;		// 损失电量
	private Double nonbalQua;		// 不平衡电量
	private Double nonbalRate;		// 不平衡率
	private String voltage;		// 电压等级
	private Double yglj;		// 有功累计
	private Double wglj;		// 无功累计
	private Double sslj;		// 损失累计
	private Double sclj;		// 销售累计
	
	public XsParlinebalance() {
		super();
	}

	public XsParlinebalance(String id){
		super(id);
	}

	@Length(min=1, max=40, message="年月长度必须介于 1 和 40 之间")
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
	
	@Length(min=1, max=64, message="线路长度必须介于 1 和 64 之间")
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
	@Length(min=0, max=100, message="线路编号长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=64, message="变电站长度必须介于 0 和 64 之间")
	public String getTrsubstationId() {
		return trsubstationId;
	}

	public void setTrsubstationId(String trsubstationId) {
		this.trsubstationId = trsubstationId;
	}
	
	public Double getPowerQua() {
		return powerQua;
	}

	public void setPowerQua(Double powerQua) {
		this.powerQua = powerQua;
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
	
	public Double getNonbalQua() {
		return nonbalQua;
	}

	public void setNonbalQua(Double nonbalQua) {
		this.nonbalQua = nonbalQua;
	}
	
	public Double getNonbalRate() {
		return nonbalRate;
	}

	public void setNonbalRate(Double nonbalRate) {
		this.nonbalRate = nonbalRate;
	}
	
	@Length(min=0, max=10, message="电压等级长度必须介于 0 和 10 之间")
	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
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