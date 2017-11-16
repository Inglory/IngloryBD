package com.yee.yide.modules.report.entity;
/*
 * @author 李亮亮
 * 线损分压考核表（营业）entity
 */


import java.math.BigDecimal;

public class Xsfykhb {
	private String xl;//线路选项：线路，主变，综合等
	private String dy;//电压：110kv，35kv，10kv，
	private String jld; //计量点
	private BigDecimal bygdl;//本月供电量
	private BigDecimal bysdl;//本月售电量
	private BigDecimal byssdl;//本月损失电量
	private BigDecimal byssl;//本月ssl
	private BigDecimal bywgdl;//本月无功电量
	private BigDecimal byll;//笨鱼力率
	private BigDecimal ljgdl;//累计供电量
	private BigDecimal ljsdl;//累计售电量
	private BigDecimal ljssdl;//累计损失电量
	private BigDecimal ljssl;//累计损失率
	private BigDecimal ljwgdl;//累计无功电量
	private BigDecimal ljll;//累计力率
	
	//表头：高低压综合gdyzh
	private BigDecimal gdyzhbygdl;//本月供电量
	private BigDecimal gdyzhbysdl;//本月售电量
	private BigDecimal gdyzhbyssdl;//本月损失电量
	private BigDecimal gdyzhbyssl;//本月ssl
	private BigDecimal gdyzhbywgdl;//本月无功电量
	private BigDecimal gdyzhbyll;//笨鱼力率
	private BigDecimal gdyzhljgdl;//累计供电量
	private BigDecimal gdyzhljsdl;//累计售电量
	private BigDecimal gdyzhljssdl;//累计损失电量
	private BigDecimal gdyzhljssl;//累计损失率
	private BigDecimal gdyzhljwgdl;//累计无功电量
	private BigDecimal gdyzhljll;//累计力率
	
	//表头：高压损失率gyssl
	private BigDecimal gysslgdyzhbygdl;//本月供电量
	private BigDecimal gysslgdyzhbysdl;//本月售电量
	private BigDecimal gysslgdyzhbyssdl;//本月损失电量
	private BigDecimal gysslgdyzhbyssl;//本月ssl
	private BigDecimal gysslgdyzhbywgdl;//本月无功电量
	private BigDecimal gysslgdyzhbyll;//笨鱼力率
	private BigDecimal gysslgdyzhljgdl;//累计供电量
	private BigDecimal gysslgdyzhljsdl;//累计售电量
	private BigDecimal gysslgdyzhljssdl;//累计损失电量
	private BigDecimal gysslgdyzhljssl;//累计损失率
	private BigDecimal gysslgdyzhljwgdl;//累计无功电量
	private BigDecimal gysslgdyzhljll;//累计力率
	
	
   	
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getDy() {
		return dy;
	}
	public void setDy(String dy) {
		this.dy = dy;
	}
	public BigDecimal getGdyzhbygdl() {
		return gdyzhbygdl;
	}
	public void setGdyzhbygdl(BigDecimal gdyzhbygdl) {
		this.gdyzhbygdl = gdyzhbygdl;
	}
	public BigDecimal getGdyzhbysdl() {
		return gdyzhbysdl;
	}
	public void setGdyzhbysdl(BigDecimal gdyzhbysdl) {
		this.gdyzhbysdl = gdyzhbysdl;
	}
	public BigDecimal getGdyzhbyssdl() {
		return gdyzhbyssdl;
	}
	public void setGdyzhbyssdl(BigDecimal gdyzhbyssdl) {
		this.gdyzhbyssdl = gdyzhbyssdl;
	}
	public BigDecimal getGdyzhbyssl() {
		return gdyzhbyssl;
	}
	public void setGdyzhbyssl(BigDecimal gdyzhbyssl) {
		this.gdyzhbyssl = gdyzhbyssl;
	}
	public BigDecimal getGdyzhbywgdl() {
		return gdyzhbywgdl;
	}
	public void setGdyzhbywgdl(BigDecimal gdyzhbywgdl) {
		this.gdyzhbywgdl = gdyzhbywgdl;
	}
	public BigDecimal getGdyzhbyll() {
		return gdyzhbyll;
	}
	public void setGdyzhbyll(BigDecimal gdyzhbyll) {
		this.gdyzhbyll = gdyzhbyll;
	}
	public BigDecimal getGdyzhljgdl() {
		return gdyzhljgdl;
	}
	public void setGdyzhljgdl(BigDecimal gdyzhljgdl) {
		this.gdyzhljgdl = gdyzhljgdl;
	}
	public BigDecimal getGdyzhljsdl() {
		return gdyzhljsdl;
	}
	public void setGdyzhljsdl(BigDecimal gdyzhljsdl) {
		this.gdyzhljsdl = gdyzhljsdl;
	}
	public BigDecimal getGdyzhljssdl() {
		return gdyzhljssdl;
	}
	public void setGdyzhljssdl(BigDecimal gdyzhljssdl) {
		this.gdyzhljssdl = gdyzhljssdl;
	}
	public BigDecimal getGdyzhljssl() {
		return gdyzhljssl;
	}
	public void setGdyzhljssl(BigDecimal gdyzhljssl) {
		this.gdyzhljssl = gdyzhljssl;
	}
	public BigDecimal getGdyzhljwgdl() {
		return gdyzhljwgdl;
	}
	public void setGdyzhljwgdl(BigDecimal gdyzhljwgdl) {
		this.gdyzhljwgdl = gdyzhljwgdl;
	}
	public BigDecimal getGdyzhljll() {
		return gdyzhljll;
	}
	public void setGdyzhljll(BigDecimal gdyzhljll) {
		this.gdyzhljll = gdyzhljll;
	}
	public BigDecimal getGysslgdyzhbygdl() {
		return gysslgdyzhbygdl;
	}
	public void setGysslgdyzhbygdl(BigDecimal gysslgdyzhbygdl) {
		this.gysslgdyzhbygdl = gysslgdyzhbygdl;
	}
	public BigDecimal getGysslgdyzhbysdl() {
		return gysslgdyzhbysdl;
	}
	public void setGysslgdyzhbysdl(BigDecimal gysslgdyzhbysdl) {
		this.gysslgdyzhbysdl = gysslgdyzhbysdl;
	}
	public BigDecimal getGysslgdyzhbyssdl() {
		return gysslgdyzhbyssdl;
	}
	public void setGysslgdyzhbyssdl(BigDecimal gysslgdyzhbyssdl) {
		this.gysslgdyzhbyssdl = gysslgdyzhbyssdl;
	}
	public BigDecimal getGysslgdyzhbyssl() {
		return gysslgdyzhbyssl;
	}
	public void setGysslgdyzhbyssl(BigDecimal gysslgdyzhbyssl) {
		this.gysslgdyzhbyssl = gysslgdyzhbyssl;
	}
	public BigDecimal getGysslgdyzhbywgdl() {
		return gysslgdyzhbywgdl;
	}
	public void setGysslgdyzhbywgdl(BigDecimal gysslgdyzhbywgdl) {
		this.gysslgdyzhbywgdl = gysslgdyzhbywgdl;
	}
	public BigDecimal getGysslgdyzhbyll() {
		return gysslgdyzhbyll;
	}
	public void setGysslgdyzhbyll(BigDecimal gysslgdyzhbyll) {
		this.gysslgdyzhbyll = gysslgdyzhbyll;
	}
	public BigDecimal getGysslgdyzhljgdl() {
		return gysslgdyzhljgdl;
	}
	public void setGysslgdyzhljgdl(BigDecimal gysslgdyzhljgdl) {
		this.gysslgdyzhljgdl = gysslgdyzhljgdl;
	}
	public BigDecimal getGysslgdyzhljsdl() {
		return gysslgdyzhljsdl;
	}
	public void setGysslgdyzhljsdl(BigDecimal gysslgdyzhljsdl) {
		this.gysslgdyzhljsdl = gysslgdyzhljsdl;
	}
	public BigDecimal getGysslgdyzhljssdl() {
		return gysslgdyzhljssdl;
	}
	public void setGysslgdyzhljssdl(BigDecimal gysslgdyzhljssdl) {
		this.gysslgdyzhljssdl = gysslgdyzhljssdl;
	}
	public BigDecimal getGysslgdyzhljssl() {
		return gysslgdyzhljssl;
	}
	public void setGysslgdyzhljssl(BigDecimal gysslgdyzhljssl) {
		this.gysslgdyzhljssl = gysslgdyzhljssl;
	}
	public BigDecimal getGysslgdyzhljwgdl() {
		return gysslgdyzhljwgdl;
	}
	public void setGysslgdyzhljwgdl(BigDecimal gysslgdyzhljwgdl) {
		this.gysslgdyzhljwgdl = gysslgdyzhljwgdl;
	}
	public BigDecimal getGysslgdyzhljll() {
		return gysslgdyzhljll;
	}
	public void setGysslgdyzhljll(BigDecimal gysslgdyzhljll) {
		this.gysslgdyzhljll = gysslgdyzhljll;
	}
	public String getJld() {
		return jld;
	}
	public void setJld(String jld) {
		this.jld = jld;
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
	

}
