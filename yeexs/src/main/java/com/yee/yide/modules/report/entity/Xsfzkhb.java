package com.yee.yide.modules.report.entity;
/*
 * @author 李亮亮
 * 线损分站考核表，送电线路线损分线考核表共用该entity，
 * 35kv 变电站母线变量不平衡率，110kV变电站母线电量不平衡率
 */

import java.math.BigDecimal;

public class Xsfzkhb {
	private String sdxlmc;//送电线路名称
	private String bdzmc;//变电站名称
	private BigDecimal bygdl;//本月供电量
	private BigDecimal bysdl;//本月售电量
	private BigDecimal byssdl;//本月损失电量
	private BigDecimal byssl;//本月ssl
	private BigDecimal bywgdl;//本月无功电量
	private BigDecimal byll;//本月力率
	private BigDecimal ljgdl;//累计供电量
	private BigDecimal ljsdl;//累计售电量
	private BigDecimal ljssdl;//累计损失电量
	private BigDecimal ljssl;//累计损失率
	private BigDecimal ljwgdl;//累计无功电量
	private BigDecimal ljll;//累计力率
	
	//110kV变电站母线电量不平衡率的专用属性
	private String dymx;//电压母线：110KV，35Kv，10kv
	public String getDymx() {
		return dymx;
	}
	public void setDymx(String dymx) {
		this.dymx = dymx;
	}
	public String getBdzmc() {
		return bdzmc;
	}
	public void setBdzmc(String bdzmc) {
		this.bdzmc = bdzmc;
	}
	public BigDecimal getBygdl() {
		return bygdl;
	}
	public void setBygdl(BigDecimal bygdl) {
		this.bygdl = bygdl;
	}
	public BigDecimal getBysdl() {
		return bysdl;
	}
	public void setBysdl(BigDecimal bysdl) {
		this.bysdl = bysdl;
	}
	public BigDecimal getByssdl() {
		return byssdl;
	}
	public void setByssdl(BigDecimal byssdl) {
		this.byssdl = byssdl;
	}
	public BigDecimal getByssl() {
		return byssl;
	}
	public void setByssl(BigDecimal byssl) {
		this.byssl = byssl;
	}
	public BigDecimal getBywgdl() {
		return bywgdl;
	}
	public void setBywgdl(BigDecimal bywgdl) {
		this.bywgdl = bywgdl;
	}
	public BigDecimal getByll() {
		return byll;
	}
	public void setByll(BigDecimal byll) {
		this.byll = byll;
	}
	public BigDecimal getLjgdl() {
		return ljgdl;
	}
	public void setLjgdl(BigDecimal ljgdl) {
		this.ljgdl = ljgdl;
	}
	public BigDecimal getLjsdl() {
		return ljsdl;
	}
	public void setLjsdl(BigDecimal ljsdl) {
		this.ljsdl = ljsdl;
	}
	public BigDecimal getLjssdl() {
		return ljssdl;
	}
	public void setLjssdl(BigDecimal ljssdl) {
		this.ljssdl = ljssdl;
	}
	public BigDecimal getLjssl() {
		return ljssl;
	}
	public void setLjssl(BigDecimal ljssl) {
		this.ljssl = ljssl;
	}
	public BigDecimal getLjwgdl() {
		return ljwgdl;
	}
	public void setLjwgdl(BigDecimal ljwgdl) {
		this.ljwgdl = ljwgdl;
	}
	public BigDecimal getLjll() {
		return ljll;
	}
	public void setLjll(BigDecimal ljll) {
		this.ljll = ljll;
	}
	public String getSdxlmc() {
		return sdxlmc;
	}
	public void setSdxlmc(String sdxlmc) {
		this.sdxlmc = sdxlmc;
	}
	

}
