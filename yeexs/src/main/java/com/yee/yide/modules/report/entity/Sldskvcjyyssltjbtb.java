package com.yee.yide.modules.report.entity;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/*
 * @ author 李亮亮
 * 10kV线路、0.4kV台区抄见、营业损失率统计表(同步） entity
 */

public class Sldskvcjyyssltjbtb {
	
	private String gdz;//供电站
	private String xlmc;//线路名称
	//本月数据
	private BigDecimal bygkbdl;//本月关口表电量
	private BigDecimal bycjdl;//本月抄见电量
	private BigDecimal bycjssl;//本月抄见损失率
	private BigDecimal byyysdl;//本月营业售电量
	private BigDecimal byyyssl;//本月营业损失率
	private BigDecimal bybxs;//本月变线损
	//累计数据
    private BigDecimal ljgkbdl;//累计关口表电量
    private BigDecimal ljcjdl;//累计抄见电量
    private BigDecimal ljcjssl;//累计抄见损失率
    private BigDecimal ljyysdl;//累计营业售电量
    private BigDecimal ljyyssl;//累计营业损失率
    private BigDecimal ljbxs;//累计变线损
    //表尾合计10kv变量属性
    private BigDecimal totalbygkbdls;//本月关口表电量
	private BigDecimal totalbycjdls;//本月抄见电量
	private BigDecimal totalbycjssls;//本月抄见损失率
	private BigDecimal totalbyyysdls;//本月营业售电量
	private BigDecimal totalbyyyssls;//本月营业损失率
	private BigDecimal totalbybxss;//本月变线损
	private BigDecimal totalljgkbdls;//累计关口表电量
	private BigDecimal totalljcjdls;//累计抄见电量
	private BigDecimal totalljcjssls;//累计抄见损失率
	private BigDecimal totalljyysdls;//累计营业售电量
	private BigDecimal totalljyyssls;//累计营业损失率
    private BigDecimal totalljbxss;//累计变线损
    //表尾合计0.4kv变量属性
    private BigDecimal totalbygkbdllds;//本月关口表电量
   	private BigDecimal totalbycjdllds;//本月抄见电量
   	private BigDecimal totalbycjssllds;//本月抄见损失率
   	public BigDecimal getTotalbygkbdls() {
		return totalbygkbdls;
	}
	public void setTotalbygkbdls(BigDecimal totalbygkbdls) {
		this.totalbygkbdls = totalbygkbdls;
	}
	public BigDecimal getTotalbycjdls() {
		return totalbycjdls;
	}
	public void setTotalbycjdls(BigDecimal totalbycjdls) {
		this.totalbycjdls = totalbycjdls;
	}
	//10kv合计本月抄见损失率计算方式
	public BigDecimal getTotalbycjssls() {
		if(this.totalbygkbdls!=null&&this.totalbycjdls!=null){
			if(this.totalbygkbdls.equals(BigDecimal.ZERO)){
			  this.totalbycjssls=null;
			}else{
			  this.totalbycjssls=((this.getTotalbygkbdls().subtract(this.getTotalbycjdls())).divide(this.getTotalbygkbdls(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));

			}
		}else{
			this.totalbycjssls=null;
		}	
		return this.totalbycjssls;
	}
	public void setTotalbycjssls(BigDecimal totalbycjssls) {
		this.totalbycjssls = totalbycjssls;
	}
	public BigDecimal getTotalbyyysdls() {
		return totalbyyysdls;
	}
	public void setTotalbyyysdls(BigDecimal totalbyyysdls) {
		this.totalbyyysdls = totalbyyysdls;
	}
	//10kv合计本月营业损失率计算
	public BigDecimal getTotalbyyyssls() {
		if(this.getTotalbyyysdls()!=null&&this.getTotalbygkbdls()!=null){
			if(this.totalbygkbdls.equals(BigDecimal.ZERO)){
				this.totalbyyyssls=null;
			}else{
				totalbyyyssls=((this.getTotalbygkbdls().subtract(this.getTotalbyyysdls())).divide(this.getTotalbygkbdls(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}else{
			this.totalbyyyssls=null;
		}
		return totalbyyyssls;
	}
	public void setTotalbyyyssls(BigDecimal totalbyyyssls) {
		this.totalbyyyssls = totalbyyyssls;
	}
	//10kv合计本月变线损计算
	public BigDecimal getTotalbybxss() {
		if(this.totalbyyysdls!=null&&this.totalbycjdls!=null){
			this.totalbybxss=this.totalbyyysdls.subtract(this.totalbycjdls);
		}else{
			this.totalbybxss=null;
		}
		return totalbybxss;
	}
	public void setTotalbybxss(BigDecimal totalbybxss) {
		this.totalbybxss = totalbybxss;
	}
	public BigDecimal getTotalljgkbdls() {
		return totalljgkbdls;
	}
	public void setTotalljgkbdls(BigDecimal totalljgkbdls) {
		this.totalljgkbdls = totalljgkbdls;
	}
	public BigDecimal getTotalljcjdls() {
		return totalljcjdls;
	}
	public void setTotalljcjdls(BigDecimal totalljcjdls) {
		this.totalljcjdls = totalljcjdls;
	}
	//10kv合计累计抄见损失率
	public BigDecimal getTotalljcjssls() {
		if(this.totalljgkbdls!=null&&this.totalljcjdls!=null){
			if(this.totalljgkbdls.equals(BigDecimal.ZERO)){
			  this.totalljcjssls=null;
			}else{
			  this.totalljcjssls=((this.getTotalljgkbdls().subtract(this.getTotalljcjdls())).divide(this.getTotalljgkbdls(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));

			}
		}else{
			this.totalljcjssls=null;
		}	
		return totalljcjssls;
	}
	public void setTotalljcjssls(BigDecimal totalljcjssls) {
		this.totalljcjssls = totalljcjssls;
	}
	
	public BigDecimal getTotalljyysdls() {
		return totalljyysdls;
	}
	public void setTotalljyysdls(BigDecimal totalljyysdls) {
		this.totalljyysdls = totalljyysdls;
	}
	//10kv合计累计营业损失率
	public BigDecimal getTotalljyyssls() {
		if(this.getTotalljyysdls()!=null&&this.getTotalljgkbdls()!=null){
			if(this.totalljgkbdls.equals(BigDecimal.ZERO)){
				this.totalljyyssls=null;
			}else{
				totalljyyssls=((this.getTotalljgkbdls().subtract(this.getTotalljyysdls())).divide(this.getTotalljgkbdls(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}else{
			this.totalljyyssls=null;
		}
		return totalljyyssls;
	}
	public void setTotalljyyssls(BigDecimal totalljyyssls) {
		this.totalljyyssls = totalljyyssls;
	}
	//10kv合计累计变线损
	public BigDecimal getTotalljbxss() {
		if(this.totalljyysdls!=null&&this.totalljcjdls!=null){
			this.totalljbxss=this.totalljyysdls.subtract(this.totalljcjdls);
		}else{
			this.totalljbxss=null;
		}
		return totalljbxss;
	}
	public void setTotalljbxss(BigDecimal totalljbxss) {
		this.totalljbxss = totalljbxss;
	}
	public BigDecimal getTotalbygkbdllds() {
		return totalbygkbdllds;
	}
	public void setTotalbygkbdllds(BigDecimal totalbygkbdllds) {
		this.totalbygkbdllds = totalbygkbdllds;
	}
	public BigDecimal getTotalbycjdllds() {
		return totalbycjdllds;
	}
	public void setTotalbycjdllds(BigDecimal totalbycjdllds) {
		this.totalbycjdllds = totalbycjdllds;
	}
	//0.4kv合计本月抄见损失率
	public BigDecimal getTotalbycjssllds() {
		if(this.totalbygkbdllds!=null&&this.totalbycjdllds!=null){
			if(this.totalbygkbdllds.equals(BigDecimal.ZERO)){
			  this.totalbycjssllds=null;
			}else{
			  this.totalbycjssllds=((this.getTotalbygkbdllds().subtract(this.getTotalbycjdllds())).divide(this.getTotalbygkbdllds(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));

			}
		}else{
			this.totalbycjssllds=null;
		}	
		return this.totalbycjssllds;
	}
	public void setTotalbycjssllds(BigDecimal totalbycjssllds) {
		this.totalbycjssllds = totalbycjssllds;
	}
	public BigDecimal getTotalbyyysdllds() {
		return totalbyyysdllds;
	}
	public void setTotalbyyysdllds(BigDecimal totalbyyysdllds) {
		this.totalbyyysdllds = totalbyyysdllds;
	}
	//0.4kv合计本月营业损失率
	public BigDecimal getTotalbyyyssllds() {
		if(this.getTotalbyyysdllds()!=null&&this.getTotalbygkbdllds()!=null){
			if(this.totalbygkbdllds.equals(BigDecimal.ZERO)){
				this.totalbyyyssllds=null;
			}else{
				totalbyyyssllds=((this.getTotalbygkbdllds().subtract(this.getTotalbyyysdllds())).divide(this.getTotalbygkbdllds(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}else{
			this.totalbyyyssllds=null;
		}
		return totalbyyyssllds;
	}
	public void setTotalbyyyssllds(BigDecimal totalbyyyssllds) {
		this.totalbyyyssllds = totalbyyyssllds;
	}
	//0.4kv合计本月变线损
	public BigDecimal getTotalbybxslds() {
		if(this.totalbyyysdllds!=null&&this.totalbycjdllds!=null){
			this.totalbybxslds=this.totalbyyysdllds.subtract(this.totalbycjdllds);
		}else{
			this.totalbybxslds=null;
		}
		return totalbybxslds;
	}
	
	public void setTotalbybxslds(BigDecimal totalbybxslds) {
		this.totalbybxslds = totalbybxslds;
	}
	public BigDecimal getTotalljgkbdllds() {
		return totalljgkbdllds;
	}
	public void setTotalljgkbdllds(BigDecimal totalljgkbdllds) {
		this.totalljgkbdllds = totalljgkbdllds;
	}
	public BigDecimal getTotalljcjdllds() {
		return totalljcjdllds;
	}
	public void setTotalljcjdllds(BigDecimal totalljcjdllds) {
		this.totalljcjdllds = totalljcjdllds;
	}
	//0.4kv合计累计抄见损失率
	public BigDecimal getTotalljcjssllds() {
		if(this.totalljgkbdllds!=null&&this.totalljcjdllds!=null){
			if(this.totalljgkbdllds.equals(BigDecimal.ZERO)){
			  this.totalljcjssllds=null;
			}else{
			  this.totalljcjssllds=((this.getTotalljgkbdllds().subtract(this.getTotalljcjdllds())).divide(this.getTotalljgkbdllds(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));

			}
		}else{
			this.totalljcjssllds=null;
		}	
		return this.totalljcjssllds;
	}
	public void setTotalljcjssllds(BigDecimal totalljcjssllds) {
		this.totalljcjssllds = totalljcjssllds;
	}
	public BigDecimal getTotalljyysdllds() {
		return totalljyysdllds;
	}
	public void setTotalljyysdllds(BigDecimal totalljyysdllds) {
		this.totalljyysdllds = totalljyysdllds;
	}
	//0.4kv合计累计营业损失率
	public BigDecimal getTotalljyyssllds() {
		if(this.getTotalljyysdllds()!=null&&this.getTotalljgkbdllds()!=null){
			if(this.totalljgkbdllds.equals(BigDecimal.ZERO)){
				this.totalljyyssllds=null;
			}else{
				totalljyyssllds=((this.getTotalljgkbdllds().subtract(this.getTotalljyysdllds())).divide(this.getTotalljgkbdllds(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}else{
			this.totalljyyssllds=null;
		}
		return totalljyyssllds;
	}
	public void setTotalljyyssllds(BigDecimal totalljyyssllds) {
		this.totalljyyssllds = totalljyyssllds;
	}
	//0.4kv合计累计变线损
	public BigDecimal getTotalljbxslds() {
		if(this.totalljyysdllds!=null&&this.totalljcjdllds!=null){
			this.totalljbxslds=this.totalljyysdllds.subtract(this.totalljcjdllds);
		}else{
			this.totalljbxslds=null;
		}
		return totalljbxslds;
	}
	public void setTotalljbxslds(BigDecimal totalljbxslds) {
		this.totalljbxslds = totalljbxslds;
	}
	private BigDecimal totalbyyysdllds;//本月营业售电量
   	private BigDecimal totalbyyyssllds;//本月营业损失率
   	private BigDecimal totalbybxslds;//本月变线损
   	private BigDecimal totalljgkbdllds;//累计关口表电量
   	private BigDecimal totalljcjdllds;//累计抄见电量
   	private BigDecimal totalljcjssllds;//累计抄见损失率
   	private BigDecimal totalljyysdllds;//累计营业售电量
   	private BigDecimal totalljyyssllds;//累计营业损失率
    private BigDecimal totalljbxslds;//累计变线损
    
    
	public String getGdz() {
		return gdz;
	}
	public void setGdz(String gdz) {
		this.gdz = gdz;
	}
	public String getXlmc() {
		return xlmc;
	}
	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}
	public BigDecimal getBygkbdl() {
		return bygkbdl;
	}
	public void setBygkbdl(BigDecimal bygkbdl) {
		this.bygkbdl = bygkbdl;
	}
	public BigDecimal getBycjdl() {
		return bycjdl;
	}
	public void setBycjdl(BigDecimal bycjdl) {
		this.bycjdl = bycjdl;
	}
	//本月抄见损失率计算
	public BigDecimal getBycjssl() {
		if(this.bygkbdl!=null&&this.bycjdl!=null){
			if(this.bygkbdl.equals(BigDecimal.ZERO)){
			  this.bycjssl=null;
			}else{
			  this.bycjssl=((this.getBygkbdl().subtract(this.getBycjdl())).divide(this.getBygkbdl(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));

			}
		}else{
			this.bycjssl=null;
		}	
		return this.bycjssl;
	}
	public void setBycjssl(BigDecimal bycjssl) {
		this.bycjssl = bycjssl;
	}
	public BigDecimal getByyysdl() {
		return byyysdl;
	}
	public void setByyysdl(BigDecimal byyysdl) {
		this.byyysdl = byyysdl;
	}
	//本月营业损失率计算
	public BigDecimal getByyyssl() {
		
		if(this.getByyysdl()!=null&&this.getBygkbdl()!=null){
			if(this.bygkbdl.equals(BigDecimal.ZERO)){
				this.byyyssl=null;
			}else{
				byyyssl=((this.getBygkbdl().subtract(this.getByyysdl())).divide(this.getBygkbdl(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}else{
			this.byyyssl=null;
		}
		return this.byyyssl;
	}
	public void setByyyssl(BigDecimal byyyssl) {
		this.byyyssl = byyyssl;
	}
	//本月变线损计算方式
	public BigDecimal getBybxs() {
		if(this.byyysdl!=null&&this.bycjdl!=null){
			this.bybxs=this.byyysdl.subtract(this.bycjdl);
		}else{
			this.bybxs=null;
		}
		return this.bybxs;
	}
	public void setBybxs(BigDecimal bybxs) {
		this.bybxs = bybxs;
	}
	public BigDecimal getLjgkbdl() {
		return ljgkbdl;
	}
	public void setLjgkbdl(BigDecimal ljgkbdl) {
		this.ljgkbdl = ljgkbdl;
	}
	public BigDecimal getLjcjdl() {
		return ljcjdl;
	}
	public void setLjcjdl(BigDecimal ljcjdl) {
		this.ljcjdl = ljcjdl;
	}
	//累计抄见损失率计算方式
	public BigDecimal getLjcjssl() {
		if(this.ljgkbdl!=null&&this.ljcjdl!=null){
			if(this.ljgkbdl.equals(BigDecimal.ZERO)){
			  this.ljcjssl=null;
			}else{
			  this.ljcjssl=((this.getLjgkbdl().subtract(this.getLjcjdl())).divide(this.getLjgkbdl(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));

			}
		}else{
			this.ljcjssl=null;
		}	
		return this.ljcjssl;
	}
	public void setLjcjssl(BigDecimal ljcjssl) {
		this.ljcjssl = ljcjssl;
	}
	public BigDecimal getLjyysdl() {
		return ljyysdl;
	}
	public void setLjyysdl(BigDecimal ljyysdl) {
		this.ljyysdl = ljyysdl;
	}
	//累计营业损失率
	public BigDecimal getLjyyssl() {
		if(this.getLjyysdl()!=null&&this.getLjgkbdl()!=null){
			if(this.ljgkbdl.equals(BigDecimal.ZERO)){
				this.ljyyssl=null;
			}else{
				ljyyssl=((this.getLjgkbdl().subtract(this.getLjyysdl())).divide(this.getLjgkbdl(),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}else{
			this.ljyyssl=null;
		}
		return this.ljyyssl;
	}
	public void setLjyyssl(BigDecimal ljyyssl) {
		this.ljyyssl = ljyyssl;
	}
	public BigDecimal getLjbxs() {
		if(this.ljyysdl!=null&&this.ljcjdl!=null){
			this.ljbxs=this.ljyysdl.subtract(this.ljcjdl);
		}else{
			this.ljbxs=null;
		}
		return this.ljbxs;
	}
	public void setLjbxs(BigDecimal ljbxs) {
		this.ljbxs = ljbxs;
	}
    
    
	public static List<Sldskvcjyyssltjbtb> t(){
		List<Sldskvcjyyssltjbtb> list= new LinkedList<Sldskvcjyyssltjbtb>();
		Sldskvcjyyssltjbtb s = new Sldskvcjyyssltjbtb();
		
		list.add(s);
		return list;
		
	}
	public static void main(String[] args){
		List<Sldskvcjyyssltjbtb> list=t();
		System.out.println(list.get(0).getLjyyssl());
	}
	
	

}
