/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.Arithmetic;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.dljs.entity.XsLinewastage;
import com.yee.yide.llas.dljs.service.XsLinewastageService;
import com.yee.yide.llas.jcxx.entity.XsLine;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.operator.entity.XsOperator;
import com.yee.yide.llas.operator.service.XsOperatorService;
import com.yee.yide.llas.tjgx.service.*;
import com.yee.yide.llas.tjxx.service.XsStatistics35Service;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 电量计算Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/dljs/xsLinewastage")
public class XsLinewastageController extends BaseController {

	@Autowired
	private XsLinewastageService xsLinewastageService;
	@Autowired
	private XsOperatorService xsOperatorService;
	@Autowired
	private XsLineService xsLineService;
	@Autowired
	private XsStatistics35Service xsStatistics35Service;
	@Autowired
	private XsRelation35Service xsRelation35Service;
	@Autowired
	private XsRelation110Service xsRelation110Service;
	@Autowired
	private XsRelationAllService xsRelationAllService;
	@Autowired
	private XsRelationParlineService xsRelationParlineService;
	@Autowired
	private XsGxZbService xsGxZbService;

	@ModelAttribute
	public XsLinewastage get(@RequestParam(required=false) String id) {
		XsLinewastage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsLinewastageService.get(id);
		}
		if (entity == null){
			entity = new XsLinewastage();
		}
		return entity;
	}
	
	@RequiresPermissions("dljs:xsLinewastage:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsLinewastage xsLinewastage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsLinewastage> page = xsLinewastageService.findPage(new Page<XsLinewastage>(request, response), xsLinewastage); 
		model.addAttribute("page", page);
		return "llas/dljs/xsLinewastageList";
	}

	@RequiresPermissions("dljs:xsLinewastage:view")
	@RequestMapping(value = "form")
	public String form(XsLinewastage xsLinewastage, Model model) {
		model.addAttribute("xsLinewastage", xsLinewastage);
		return "llas/dljs/xsLinewastageForm";
	}

	@RequiresPermissions("dljs:xsLinewastage:edit")
	@RequestMapping(value = "save")
	public String save(XsLinewastage xsLinewastage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsLinewastage)){
			return form(xsLinewastage, model);
		}
		xsLinewastageService.save(xsLinewastage);
		addMessage(redirectAttributes, "保存电量计算成功");
		return "redirect:"+Global.getAdminPath()+"/dljs/xsLinewastage/?repage";
	}
	
	@RequiresPermissions("dljs:xsLinewastage:edit")
	@RequestMapping(value = "delete")
	public String delete(XsLinewastage xsLinewastage, RedirectAttributes redirectAttributes) {
		xsLinewastageService.delete(xsLinewastage);
		addMessage(redirectAttributes, "删除电量计算成功");
		return "redirect:"+Global.getAdminPath()+"/dljs/xsLinewastage/?repage";
	}

	
	
	/**
	 * 电量计算
	 * @param xsLinewastage
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequiresPermissions("dljs:xsLinewastage:edit")
	@RequestMapping(value = "jisuan")
	public String jisuan(XsLinewastage xsLinewastage, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		quaCal();  //计算抄见电量和合计电量
		getTotalQua(); 
		Page<XsLinewastage> page = xsLinewastageService.findPage(new Page<XsLinewastage>(request, response), xsLinewastage);
		model.addAttribute("page", page);
		addMessage(redirectAttributes, "电量计算完成");
		return "redirect:"+Global.getAdminPath()+"/dljs/xsLinewastage/?repage";
	}

	/**
	 * 计算抄见电量与合计电量
	 *
	 * @author Leecheng 日期：2008-9-4 下午01:22:26
	 */
	public void quaCal() {
		// 首先计算正向抄见电量，再次计算防止取数过来的不准确 等
		double fp_svalue = 0, fp_evalue = 0, fp_excerpqua = 0, fp_totalqua = 0, fp_subjoinqua = 0, fp_chmter = 0, intefactor = 0, maxcapacity = 0;
		double fup_svalue = 0, fup_evalue = 0, fup_excerpqua = 0, fup_totalqua = 0, fup_subjoinqua = 0, fup_chmter = 0;

		double rp_svalue = 0, rp_evalue = 0, rp_excerpqua = 0, rp_totalqua = 0, rp_subjoinqua = 0, rp_chmter = 0;
		double rup_svalue = 0, rup_evalue = 0, rup_excerpqua = 0, rup_totalqua = 0, rup_subjoinqua = 0, rup_chmter = 0;

		List<XsLine> list= xsLineService.findList(new XsLine());
		for(XsLine xsLine : list){
			fp_svalue = setObjTo0(xsLine.getFpSvalue());
			fp_evalue = setObjTo0(xsLine.getFpEvalue());
			fp_subjoinqua = setObjTo0(xsLine.getFpSubjoinqua());
			fp_chmter = setObjTo0(xsLine.getFpChmeter());

			fup_svalue = setObjTo0(xsLine.getFupSvalue());
			fup_evalue = setObjTo0(xsLine.getFupEvalue());
			fup_subjoinqua = setObjTo0(xsLine.getFupSubjoinqua());
			fup_chmter = setObjTo0(xsLine.getFupChmeter());

			rp_svalue = setObjTo0(xsLine.getRpSvalue());
			rp_evalue = setObjTo0(xsLine.getRpEvalue());
			rp_subjoinqua = setObjTo0(xsLine.getRpSubjoinqua());
			rp_chmter = setObjTo0(xsLine.getRpChmeter());

			rup_svalue = setObjTo0(xsLine.getRupSvalue());
			rup_evalue = setObjTo0(xsLine.getRupEvalue());
			rup_subjoinqua = setObjTo0(xsLine.getRupSubjoinqua());
			rup_chmter = setObjTo0(xsLine.getRupChmeter());

			intefactor = setObjTo0(xsLine.getIntefactor());
			maxcapacity = setObjTo0(xsLine.getMaxcapacity());
			// 下面计算正向电量
			if (fp_evalue >= fp_svalue) {
				fp_excerpqua = Arithmetic .mul(Arithmetic.sub(fp_evalue,
						fp_svalue), intefactor); // 抄见电量=（止码-起码 ）*倍率
			} else {
				double tempSum = Arithmetic.add(Arithmetic.add(fp_evalue,
						maxcapacity), 1.0);
				double tempSub = Arithmetic.sub(tempSum, fp_svalue);
				fp_excerpqua = Arithmetic.mul(tempSub, intefactor);
			}
			fp_totalqua = Arithmetic.add(Arithmetic.add(fp_excerpqua,
					fp_subjoinqua), fp_chmter);

			if (fup_evalue >= fup_svalue) {
				fup_excerpqua = Arithmetic.mul(Arithmetic.sub(fup_evalue,
						fup_svalue), intefactor); 
			} else {
				double tempSum = Arithmetic.add(Arithmetic.add(fup_evalue,
						maxcapacity), 1.0);
				double tempSub = Arithmetic.sub(tempSum, fup_svalue);
				fup_excerpqua = Arithmetic.mul(tempSub, intefactor);
			}
			fup_totalqua = Arithmetic.add(Arithmetic.add(fup_excerpqua,
					fup_subjoinqua), fup_chmter);

			// 下面计算反向电量
			if (rp_evalue >= rp_svalue) {
				rp_excerpqua = Arithmetic.mul(Arithmetic.sub(rp_evalue,
						rp_svalue), intefactor); 
			} else {
				double tempSum = Arithmetic.add(Arithmetic.add(rp_evalue,
						maxcapacity), 1.0);
				double tempSub = Arithmetic.sub(tempSum, rp_svalue);
				rp_excerpqua = Arithmetic.mul(tempSub, intefactor);
			}
			rp_totalqua = Arithmetic.add(Arithmetic.add(rp_excerpqua,
					rp_subjoinqua), rp_chmter);

			if (rup_evalue >= rup_svalue) {
				rup_excerpqua = Arithmetic.mul(Arithmetic.sub(rup_evalue,
						rup_svalue), intefactor); 
			} else {
				double tempSum = Arithmetic.add(Arithmetic.add(rup_evalue,
						maxcapacity), 1.0);
				double tempSub = Arithmetic.sub(tempSum, rup_svalue);
				rup_excerpqua = Arithmetic.mul(tempSub, intefactor);
			}
			rup_totalqua = Arithmetic.add(Arithmetic.add(rup_excerpqua,
					rup_subjoinqua), rup_chmter);
			// 计算有功合计和武功合计
			double p_total = 0, up_total = 0;
			p_total = Arithmetic.sub(fp_totalqua, rp_totalqua);
			up_total = Arithmetic.sub(fup_totalqua, rup_totalqua);
			// 更新到数据库
			xsLine.setFpTotalqua(fp_totalqua);
			xsLine.setFupTotalqua(fup_totalqua);
			xsLine.setRpTotalqua(rp_totalqua);
			xsLine.setRupTotalqua(rup_totalqua);
			xsLine.setpTotalqua(p_total);
			xsLine.setUpTotalqua(up_total);

			xsLine.setFpExcerpqua(fp_excerpqua);
			xsLine.setFupExcerpqua(fup_excerpqua);
			xsLine.setRpExcerpqua(rp_excerpqua);
			xsLine.setRupExcerpqua(rup_excerpqua);

			xsLineService.save(xsLine);
		}
	}
//
//	/**
//	 * 将xs_line中的合计电量取给到xs_linewastage中
//	 *
//	 * @author Leecheng 日期：2008-9-2 上午06:40:37
//	 */
	public  void getTotalQua() {
		/*
		 * b. 以xs_linewastage为标准， c. 母线的输出电量： 等于该母线所有出线的有功电量之和
		 */
		double powerqua = 0, unpowerqua = 0, qua1 = 0, qua2 = 0;

		List<XsLinewastage> list = xsLinewastageService.findList(new XsLinewastage());// 查询出所有记录
		for(XsLinewastage xsLinewastage : list){
			String lineId = xsLinewastage.getLineId();
			XsLine xsLine = xsLineService.get(lineId);
			qua1 = xsLine.getpTotalqua();// 有功合计赋值给qua1
			qua2 = xsLine.getUpTotalqua(); // 无功合计赋值给qua2
			// 更新到数据库
			xsLinewastage.setQua1(qua1);
			xsLinewastage.setQua2(qua2);
			xsLinewastageService.save(xsLinewastage);
		}

	}

//	public void xsStatistics(Map map, IMessageHandler messageHandler) {
//		String msg = "";
//		String[] items = (String[]) map.get("itemsSearch"); // 从前端传过来的数组中取得需要进行统计的分类
//		String[] month = (String[]) map.get("month");
//		String ym = month[0];
//		for (int i = 0; i < items.length; i++) {
//			int item = Integer.parseInt(items[i]);
//			switch (item) {
//				case 1: // 如果选中‘1’，则统计全网综合高压线损；
//					statForAll(ym);
//					msg =msg+ "全网综合线损统计结束!!</br>";
//					break;
//				case 2: // 如果选中‘2’，则统计110kv高压线损；
//					msg=statFor110(ym);
//					msg = msg+"110kV分线线损统计结束!!</br>";
//					break;
//				case 3: // 如果选中‘3’，则统计35KV输电线路线损；
//					msg=statFor35(ym);
//					msg =msg+ "35kV分线线损统计结束!!</br>";
//					break;
//				case 4: // 如果选中‘4’，则统计6-10KV输电线路线损；
//					msg=statFor10(ym);
//					msg = msg+"10kV分线线损统计结束!!</br>";
//					break;
//				case 5: // 如果选中‘5’，则统计供电所高压线损；
//					//statForGds(ym);
//					break;
//				case 6: // 如果选中‘6’，则统计变电站高压线损；
//					msg=statDlss(ym);
//					msg =msg+ "变电站电量损失统计结束!!</br>";
//					break;
//				case 7: // 如果选中‘7’，则统计母线平衡统计；
//
//					msg=parLineBalance110(ym);
//					msg=parLineBalance35(ym);
//					msg=parLineBalance10(ym);
//					msg=parLineBalance6(ym);
//					msg =msg+ "母线平衡统计结束!!</br>";
//					break;
//				case 8: // 如果选中‘8’， 变电站主变损失；
//					statZbss(ym);
//					break;
//				case 9: // 如果选中‘9’，则统计变损；
//					//statForBs(ym,"bs");
//					break;
//				case 10: // 如果选中‘10’，则统计供电所节电奖分值考核一览；
//					break;
//				default: // 默认，没有操作
//
//			}
//		}
//		IMessage message = new Message("workflow", msg);
//		messageHandler.addMessage("workflow", message);
//	}
	/**
	 * @author Leecheng  日期：2008-7-24 上午10:27:43
	 * 6-10KV统计
	 * 金乡需要：直接在xs_line表中查询所有6-10kV的线路，然后在xs_linewastage里查询相应的线路，找到其有功（qua1）、无功(qua2)和销售电量saleQuaInSdh,变损电量 ，进行相应的计算
	 * 分国网口径和营业 口径：两种口径的损失电量区分在于：
	 */
//	public static String statFor10(String ym){
//		String msg="";
//		double ssdl,ssdl_sdh;
//		double ygdl,wgdl,cjdl,bsdl,xsundl,xshoudl_sdh,bsdl_sdh;  //cjdl ：ACT_EXCERPQUA 营业口径的抄见售电量
//		double xsl,zhxsl,ll,xsl_sdh,zhxsl_sdh;
//
//		/*
//		 * 1.删除当前时间的数据
//		 */
//
//		//删除所有当前统计时间的数据 如果有
//		Map map = new HashMap();
//		map.put("YM_SEARCH", new String[] { ym });
//		List list = XsglBeanFactory.getIXsStatistics10Domain().queryNoPage(
//				map);
//		Iterator it = list.iterator();
//		while (it.hasNext()) {
//			XsStatistics10 xp = (XsStatistics10) it.next();
//			XsglBeanFactory.getIXsStatistics10Domain().delete(xp.getRelationid());
//		}
//		/*
//		 * 2.从XsLine表中查找6-10KV的线路
//		 */
//		Map map610= new HashMap();
//		map610.put("voltageSearch", new String []{"610"});
//		List list_line = XsglBeanFactory.getIXsLineDomain().getAllXsLineNoPage(map610);
//		Iterator it_6to10=list_line.iterator();
//		while(it_6to10.hasNext()){
//			XsLine xsLine=(XsLine)it_6to10.next();
//			ssdl=0;ssdl_sdh=0;
//			ygdl=0;wgdl=0;cjdl=0;bsdl=0;xsundl=0;xshoudl_sdh=0;bsdl_sdh=0;
//			xsl=0;zhxsl=0;ll=0;xsl_sdh=0;zhxsl_sdh=0;
//			String lineId=xsLine.getline_id();
//			String mxbz= xsLine.getisparline(); //母线标志
//			if(mxbz.equals("1")){  //母线不统计
//				continue;
//			}
//			String lineCode=xsLine.getline_code();
//			XsLinewastage xslw = XsglBeanFactory.getIXsLinewastageDomain().get(lineId);
//			ygdl=xsLine.getp_totalqua();
//			wgdl=xsLine.getup_totalqua();
//			xshoudl_sdh=convertDouble(xslw.getSalequaInsdh()); //营业销售电量
//			bsdl=convertDouble(xslw.getTrwasteInsdh());	  //从郎新导入时，放在XsLinewastage的	TrwasteInsdh中了
//			cjdl = Arithmetic.sub(xshoudl_sdh ,bsdl);//国网口径
//			ssdl=Arithmetic.sub(ygdl,cjdl);  //  国网口径损失电量
//			ssdl_sdh=Arithmetic.sub(ygdl,xshoudl_sdh); //营业口径
//			if(ygdl==0){
//				xsl=0;
//				zhxsl=0;
//				xsl_sdh=0;
//				zhxsl_sdh=0;
//			}
//			else{
//				xsl=Arithmetic.div(ssdl,ygdl,4)*100;  //  国网口径
//				zhxsl=Arithmetic.div(Arithmetic.add(ssdl,bsdl),ygdl,4)*100;
//				xsl_sdh=Arithmetic.div(ssdl_sdh,ygdl,4)*100; //营业口径
//				zhxsl_sdh=Arithmetic.div(Arithmetic.add(ssdl_sdh,bsdl_sdh),ygdl,4)*100;
//			}
//			if(ygdl*ygdl+wgdl*wgdl==0){
//				ll=0;
//			}
//			else{
//				ll=Arithmetic.div(ygdl/(Math.sqrt(ygdl*ygdl+wgdl*wgdl)),1.0, 2);
//			}
//			//插入数据到XsStatistics10表中
//
//			XsStatistics10 xs=new XsStatistics10();
//			xs.setRelationid(ym+lineId);
//			xs.setPowerQua(ygdl));
//			xs.setNonpowerQua(wgdl));
//			xs.setSaleQua(cjdl)); //国网口径:抄见售电量
//			xs.setSalequaInsdh(xshoudl_sdh)); //营业口径
//			xs.setTrwasteQua(bsdl));
//			xs.setWasteQua(ssdl));//国网口径
//			xs.setWastequaInsdh(ssdl_sdh)); //营业口径
//			xs.setWasteRate(xsl).setScale(2,BigDecimal.ROUND_HALF_UP));
//			xs.setWasterateInsdh(xsl_sdh).setScale(2,BigDecimal.ROUND_HALF_UP));
//			xs.setLineCode(lineCode);
//			xs.setLineId(lineId);
//			xs.setPowerrate(ll).setScale(2,BigDecimal.ROUND_HALF_UP));
//			xs.setYglj(0));
//			xs.setYm(ym);
//			xs.setWglj(0));
//			xs.setSclj(0));
//			xs.setSslj(0));
//			XsglBeanFactory.getIXsStatistics10Domain().insert(xs);
//
//			/**
//			 * 下面计算累计
//			 * 累计计算方式：首先从Xs_Operator_HIS中找到本次计算紧挨着的上一个时间段pre_date：例如 20120317-20120413的上一个时间段为：20120206-20120317
//			 * 如果有多个相同的紧挨日期，选择任何一个均可以：但是首先保证以前所有的电量计算正确。
//			 * 然后从统计结果表中查询出上一个时间段的累计，从本次统计中找到本次电量合计。二者相加即可。
//			 */
//			String pre_date=ToolBean.getPreRiqi(ym);
//			ToolBean.system("全网统计pre_date ="+pre_date);
//			Map map_lj=new HashMap();
//			map_lj.put("YM_SEARCH", new String[]{pre_date});
//			map_lj.put("LINE_ID_SEARCH",  new String[]{lineId});
//			List list_lj=XsglBeanFactory.getIXsStatistics10Domain().queryNoPage(map_lj);
//			XsStatistics10 xsStatisticsAllPre=new XsStatistics10();
//			if(list_lj.isEmpty()){
//				//msg=msg+"没有查询到10kV统计中与"+ym+"线路号为"+lineCode+"相邻的上一个时间段的统计结果，请先统计上一个阶段的数据！</br>";
//			}
//			else{
//				xsStatisticsAllPre=((XsStatistics10)list_lj.get(0));
//				BigDecimal yglj=convert(xsStatisticsAllPre.getYglj());
//				BigDecimal sclj=convert(xsStatisticsAllPre.getYglj());
//				BigDecimal sslj=convert(xsStatisticsAllPre.getYglj());
//				BigDecimal wglj=convert(xsStatisticsAllPre.getWglj());
//				BigDecimal bslj=convert(xsStatisticsAllPre.getTrwastequaInsdh());  //变损累计 ：放在Trwastequa_Insdh 中
//				yglj=yglj.add(ygdl));
//				wglj=wglj.add(wgdl));
//				sslj=sslj.add(ssdl_sdh));
//				sclj=sclj.add(xshoudl_sdh));
//				bslj=bslj.add(bsdl));
//				XsStatistics10 xsStatisticsAllUpdate=XsglBeanFactory.getIXsStatistics10Domain().get(ym+lineId);
//				xsStatisticsAllUpdate.setYglj(yglj);
//				xsStatisticsAllUpdate.setSclj(sclj);
//				xsStatisticsAllUpdate.setSslj(sslj);
//				xsStatisticsAllUpdate.setWglj(wglj);
//				xsStatisticsAllUpdate.setTrwastequaInsdh(bslj);  //放置变损电量的合计了
//				XsglBeanFactory.getIXsStatistics10Domain().update(xsStatisticsAllUpdate);
//			}
//
//		}
//		//删除不需要统计的线路
//		List list_r10=XsglBeanFactory.getIXsRelation10Domain().getAllXsRelation10NoPage(new HashMap());
//		Iterator it10=list_r10.iterator();
//		while(it10.hasNext()){
//			XsRelation10 xsRelation10=(XsRelation10)it10.next();
//			String relationid=xsRelation10.getRelationid();
//			XsglBeanFactory.getIXsRelation10Domain().deleteXsRelation10(relationid);
//		}
//
//		return msg;
//	}
	// 线损统计

	/**
	 * 全网综合线损统计
	 */
//	public static  String statForAll(String ym){
//
//		String msg="";
//		XsStatisticsAll xsStatisticsAll=new XsStatisticsAll();
//			/*
//			 * 全网综合线损统计报表就是按照全网综合,110KV,35KV,6-10KV分类进行统计,
//			 * 统计出各自的以下指标:有功电量, 销售电量,损失电量,线损率,变损电量,综损率,无功电量.
//			 */
//		//删除所有当前统计时间的数据 如果有
//		Map map = new HashMap();
//		map.put("YM_SEARCH", new String[] { ym });
//		List list = XsglBeanFactory.getIXsStatisticsAllDomain().queryNoPage(
//				map);
//		Iterator it = list.iterator();
//		while (it.hasNext()) {
//			XsStatisticsAll xp = (XsStatisticsAll) it.next();
//			XsglBeanFactory.getIXsStatisticsAllDomain().delete(xp.getRelationid());
//		}
//		//
//		List list_item=new ArrayList();
//		list_item.add(0, "00");
//		list_item.add(1, "01");
//		list_item.add(2, "02");
//		list_item.add(3, "03");
//		list_item.add(4, "04");
//		list_item.add(5, "05");
//		list_item.add(6, "06");
//		list_item.add(7, "07");
//		list_item.add(8, "08");
//		list_item.add(9, "09");
//		list_item.add(10, "10");
//		Iterator it_item=list_item.iterator();
//		while(it_item.hasNext()){
//			String item=it_item.next().toString();
//			System.out.println("item "+item);
//			//以下依次统计各个item。
//
//			Map map_qwgx=new HashMap();
//			map_qwgx.put("itemSearch", new String[]{item});
//			List list_xsrelaall=XsglBeanFactory.getIXsRelationAllDomain().getAllXsRelationAllNoPage(map_qwgx);
//			Iterator it_relaall=list_xsrelaall.iterator();
//			int count = 0;
//			//以下是计算结果变量
//			double t_powerqua=0,t_nonpowerqua=0,t_salequa=0,t_salequainsdh=0,t_trlosequaexsdh=0,t_trlosequainsdh=0;
//			double waste_qua=0,wastequa_insdh=0;        //损失电量 ，损失电量(含四到户)
//			double lwaste_rate=0,comp_wrate=0,wasterate_insdh=0,compwrate_insdh=0;   //线损率
//			double powerrate=0;       //力率
//
//			//以下是取自数据库
//			double qua1=0,qua2=0;  //有无功电量
//			double sale_qua=0,salequa_insdh=0;  //销售电量
//			double trwaste_insdh=0,trwaste_exsdh=0;  //变损电量
//			while(it_relaall.hasNext()){
//				XsRelationAll xsrelation=(XsRelationAll)it_relaall.next();
//				String quakind=xsrelation.getqua_kind();
//				String lineid=xsrelation.getline_id();
//
//				String calkind=xsrelation.getcal_kind();
//				XsLinewastage xslw=XsglBeanFactory.getIXsLinewastageDomain().get(lineid);
//				XsLine xsLine=XsglBeanFactory.getIXsLineDomain().getXsLine(lineid);
//
//				if(quakind.equals("0")){                //当需要的是有功电量
//					if(xsLine!=null){
//						qua1=Change(xsLine.getp_totalqua());
//						qua2=Change(xsLine.getup_totalqua());
//					}
//					if(calkind.equals("0")){            //当计算关系为加“+”
//						t_powerqua=Arithmetic.add(t_powerqua,qua1);
//						t_nonpowerqua=Arithmetic.add(t_nonpowerqua,qua2);
//					}
//					else{                               //当计算关系为减 “-”
//						t_powerqua=Arithmetic.sub(t_powerqua, qua1);
//						t_nonpowerqua=Arithmetic.sub(t_nonpowerqua,qua2);
//					}
//				}
//				if(xslw!=null){
//					if(quakind.equals("1")){                //当电量为销售电量
//
//						sale_qua=Change(xsLine.getp_totalqua());
//						salequa_insdh=Change(xsLine.getp_totalqua());
//						if(calkind.equals("0")){
//							t_salequa=Arithmetic.add(t_salequa,sale_qua);
//							t_salequainsdh=Arithmetic.add(t_salequainsdh,salequa_insdh);
//						}
//						else{
//							t_salequa=Arithmetic.sub(t_salequa,sale_qua);
//							t_salequainsdh=Arithmetic.sub(t_salequainsdh,salequa_insdh);
//						}
//					}
//					if(quakind.equals("2")){               //当电量为变损电量
//						trwaste_insdh=Change(xslw.getTrwasteInsdh());         //含四到户变损
//						trwaste_exsdh=Change(xslw.getTrwasteExsdh());    //变损电量（不含四到户）
//						if(calkind.equals("0")){
//							t_trlosequainsdh=Arithmetic.add(t_trlosequainsdh,trwaste_insdh);
//							t_trlosequaexsdh=Arithmetic.add(t_trlosequaexsdh,trwaste_exsdh);
//						}
//						else{
//							t_trlosequainsdh=Arithmetic.sub(t_trlosequainsdh,trwaste_insdh);
//							t_trlosequaexsdh=Arithmetic.sub(t_trlosequaexsdh,trwaste_exsdh);
//						}
//					}
//				}
//			}
//			////计算线损率
//			waste_qua=Arithmetic.sub(t_powerqua,t_salequa);                //总损失电量
//			wastequa_insdh=Arithmetic.sub(t_powerqua,t_salequainsdh);	   //总损失电量(含四到户)
//			if(t_powerqua==0){
//				lwaste_rate=0;
//				comp_wrate=0;
//				wasterate_insdh=0;
//				compwrate_insdh=0;
//			}
//			else{           //按公式计算
//				lwaste_rate=Arithmetic.mul(Arithmetic.div(waste_qua, t_powerqua,4), 100);
//				double tempa=Arithmetic.add(waste_qua, t_trlosequaexsdh);
//				double tempb=Arithmetic.div(tempa, t_powerqua,4);
//				comp_wrate=Arithmetic.mul(tempb, 100);
//				wasterate_insdh=Arithmetic.mul(Arithmetic.div(wastequa_insdh,t_powerqua,4),100);
//				double tempc=Arithmetic.add(wastequa_insdh,t_trlosequainsdh);
//				double tempd=Arithmetic.div(tempc, t_powerqua,4);
//				compwrate_insdh=Arithmetic.mul(tempd, 100);
//			}
//			////计算力率
//			if(t_powerqua*t_powerqua+t_nonpowerqua*t_nonpowerqua==0){
//				powerrate=0;
//			}
//			else{
//				powerrate=Arithmetic.div(t_powerqua, Math.sqrt(t_powerqua*t_powerqua+t_nonpowerqua*t_nonpowerqua),2);
//			}
//			//插入相应数据到全网综合统计表中 包括：年月，项目名称，有功电量，无功电量，销售电量，损失电量，变损电量，线损率，综损率的含四到户与不含 四到户值
//			xsStatisticsAll.setRelationid(ym+item);
//			xsStatisticsAll.setYm(ym);
//			xsStatisticsAll.setItem(item);
//			xsStatisticsAll.setPowerQua(t_powerqua));
//			xsStatisticsAll.setNonpowerQua(t_nonpowerqua));
//			xsStatisticsAll.setSaleQua(t_salequa));
//			xsStatisticsAll.setWasteQua(waste_qua));
//			xsStatisticsAll.setTrwasteQua(t_trlosequaexsdh));
//			xsStatisticsAll.setWasteRate(lwaste_rate).setScale(2,BigDecimal.ROUND_HALF_UP));
//			xsStatisticsAll.setCompWrate(comp_wrate).setScale(2,BigDecimal.ROUND_HALF_UP));
//
//			xsStatisticsAll.setSalequaInsdh(t_salequainsdh));
//			xsStatisticsAll.setWastequaInsdh(wastequa_insdh));
//			xsStatisticsAll.setTrwastequaInsdh(t_trlosequainsdh));
//			xsStatisticsAll.setWasterateInsdh(wasterate_insdh).setScale(2,BigDecimal.ROUND_HALF_UP));
//			xsStatisticsAll.setCompwrateInsdh(compwrate_insdh).setScale(2,BigDecimal.ROUND_HALF_UP));
//			xsStatisticsAll.setPowerrate(powerrate).setScale(2,BigDecimal.ROUND_HALF_UP));
//			XsglBeanFactory.getIXsStatisticsAllDomain().insert(xsStatisticsAll);
//
//			/**
//			 * 下面计算累计
//			 * 累计计算方式：首先从Xs_Operator_HIS中找到本次计算紧挨着的上一个时间段pre_date：例如 20120317-20120413的上一个时间段为：20120206-20120317
//			 * 如果有多个相同的紧挨日期，选择任何一个均可以：但是首先保证以前所有的电量计算正确。
//			 * 然后从统计结果表中查询出上一个时间段的累计，从本次统计中找到本次电量合计。二者相加即可。
//			 */
//			String pre_date=ToolBean.getPreRiqi(ym);
//			ToolBean.system("全网统计pre_date ="+pre_date);
//			Map map_lj=new HashMap();
//			map_lj.put("YM_SEARCH", new String[]{pre_date});
//			map_lj.put("ITEM_SEARCH",  new String[]{item});
//			List list_lj=XsglBeanFactory.getIXsStatisticsAllDomain().queryNoPage(map_lj);
//			XsStatisticsAll xsStatisticsAllPre=new XsStatisticsAll();
//			if(list_lj.isEmpty()){
//				msg=msg+"没有查询到全网统计中与"+ym+"行号为"+item+"相邻的上一个时间段的统计结果，请先统计上一个阶段的数据！";
//			}
//			else{
//				xsStatisticsAllPre=((XsStatisticsAll)list_lj.get(0));
//				BigDecimal yglj=convert(xsStatisticsAllPre.getYglj());
//				BigDecimal sclj=convert(xsStatisticsAllPre.getYglj());
//				BigDecimal sslj=convert(xsStatisticsAllPre.getYglj());
//				BigDecimal wglj=convert(xsStatisticsAllPre.getWglj());
//				yglj=yglj.add(t_powerqua));
//				wglj=wglj.add(t_nonpowerqua));
//				sslj=sslj.add(waste_qua));
//				sclj=sclj.add(t_salequa));
//				XsStatisticsAll xsStatisticsAllUpdate=XsglBeanFactory.getIXsStatisticsAllDomain().get(ym+item);
//				xsStatisticsAllUpdate.setYglj(yglj);
//				xsStatisticsAllUpdate.setSclj(sclj);
//				xsStatisticsAllUpdate.setSslj(sslj);
//				xsStatisticsAllUpdate.setWglj(wglj);
//				XsglBeanFactory.getIXsStatisticsAllDomain().update(xsStatisticsAllUpdate);
//			}
//
//		}
//		return msg;
//	}


	/**
	 * 描述：变电站主变损失统计
	 *@author Leecheng
	 *@date 2011-4-11 下午04:44:59
	 *@version V1.0
	 *@Copyright @ 1997-2011 山东重信通用软件有限责任公司 All Rights Reserved.

	 */
//	public static String statZbss(String ym){
//		String msg = "";
//		double bysr = 0, bysc = 0, byssdl = 0, byssl = 0 ,glys = 0;
//		double ljsr = 0, ljsc = 0, ljssdl = 0, ljssl = 0 ,ljwg = 0, ljglys=0;
//		double jsdl = 0;  //参与计算的电量
//		double wgdl = 0 , zwgdl = 0;   //无功电量
//		double bsdl = 0;   //变损电量
//		String s_item = "";
//		int i_item = 0;
//		Map map = new HashMap();
//		map.put("ym_SEARCH", new String[]{ym});
//		//首先删除变电站主变损失统计表当月数据
//		List list = XsglBeanFactory.getIXsBdzzbssDomain().queryNoPage(map);
//		Iterator it = list.iterator();
//
//		while (it.hasNext()){
//			XsglBeanFactory.getIXsBdzzbssDomain().delete(((XsBdzzbss)it.next()).getId());
//		}
//		//电量统计
//		  /*
//		   * S1：从xs_bdzzb表中取出当前所有主变编号；循环处理所有主变；
//		   * S2:对每一个主变，用编号去查找xs_gx_zb表的该站所有关系，依次处理；
//		   */
//		List l_zb = XsglBeanFactory.getIXsBdzzbDomain().queryNoPage(new HashMap());
//		Iterator it_zb = l_zb.iterator();
//		while (it_zb.hasNext()){
//			bysr = 0; bysc = 0; byssdl = 0; byssl = 0;zwgdl = 0; glys = 0;
//			ljsr = 0; ljsc = 0; ljssdl = 0; ljssl = 0;ljwg = 0; ljglys=0;
//			XsBdzzb zb = (XsBdzzb)it_zb.next();
//			String zbid = zb.getZbid();
//			Map m_zbgx = new HashMap();
//			m_zbgx.put("TJDX_SEARCH", new String[]{zbid});
//			List l_zbgx = XsglBeanFactory.getIXsGxZbDomain().queryNoPage(m_zbgx);
//			Iterator it_zbgx = l_zbgx.iterator();
//			if(it_zbgx.hasNext()){
//				while (it_zbgx.hasNext()){  //处理每一条关系
//					XsGxZb xsgxzb = (XsGxZb)it_zbgx.next();
//					String jldid = xsgxzb.getJld();   //首先查找到该条关系的计量点
//					s_item=xsgxzb.getJsdl();
//					if(s_item.equals("I")){
//						s_item = "5";
//					}
//					if(s_item.equals("O")){
//						s_item = "6";
//					}
//					i_item=Integer.parseInt(s_item);
//					XsLine jld = XsglBeanFactory.getIXsLineDomain().getXsLine(jldid);
//					if(XsglBeanFactory.getIXsLineDomain().getXsLine(jldid) == null||XsglBeanFactory.getIXsLineDomain().getXsLine(jldid).equals("")){
//						msg = msg + "【"+zb.getZbbh()+"】的一条关系涉及的计量点不存在,请检查！</br>";
//						//continue;
//					}
//					else{
//
//						switch(i_item){
//							case 0: jsdl = convertDouble(jld.getp_totalqua()));break;
//							case 5:  jsdl = convertDouble(jld.getp_totalqua()));wgdl = convertDouble(jld.getup_totalqua()));break;
//							case 6: jsdl = convertDouble(jld.getp_totalqua()));break;
//							case 1:  jsdl = convertDouble(jld.getp_totalqua()));break;
//
//						}
//					}
//					if(xsgxzb.getTjdllx().equals("I")){
//						if(xsgxzb.getJsgx().equals("0")){  //计算关系是加
//							bysr = bysr + jsdl;
//							zwgdl = zwgdl + wgdl;
//						}
//						if(xsgxzb.getJsgx().equals("1")){ //计算关系是减
//							bysr = bysr - jsdl;
//							zwgdl = zwgdl + wgdl;
//						}
//					}
//					if(xsgxzb.getTjdllx().equals("O")){
//						if(xsgxzb.getJsgx().equals("0")){  //计算关系是加
//							bysc = bysc + jsdl;
//						}
//						if(xsgxzb.getJsgx().equals("1")){ //计算关系是减
//							bysc = bysc - jsdl;
//						}
//					}
////
//				}
//			}
//			else{
//
//			}
//
//			byssdl = bysr - bysc;
//			if(bysr == 0){
//				byssl = 0;
//			}
//			else{
//				byssl = Arithmetic.round(byssdl/bysr*100,2);
//			}
//			if(bysr*bysr+zwgdl*zwgdl ==0){
//				glys = 0;
//			}
//			else{
//				glys=Arithmetic.div(bysr, (Math.sqrt(bysr * bysr+zwgdl * zwgdl)),2);
//			}
//			//往XsBdzzbss插入一条记录
//			XsBdzzbss result = new XsBdzzbss();
//			result.setId(zbid+ym);
//			result.setBdzbh(zbid);
//			result.setYm(ym);
//			result.setBysr(bysr));
//			result.setBysc(bysc));
//			result.setByss(byssdl));
//			result.setByssl(byssl));
//			result.setWgdl(wgdl));
//			result.setGlys(glys));
//			XsglBeanFactory.getIXsBdzzbssDomain().insert(result);
//
//
//
//		}
//		return msg;
//	}

	/**
	 * 描述：变电站电量损失统计
	 *@author Leecheng
	 *@date 2011-4-11 下午04:44:59
	 *@version V1.0
	 *@Copyright @ 1997-2011 山东重信通用软件有限责任公司 All Rights Reserved.

	 */
//	public static String statDlss(String ym){
//		String msg="";
//		double bysr = 0, bysc = 0, byssdl = 0, byssl = 0;
//		double ljsr = 0, ljsc = 0, ljssdl = 0, ljssl = 0 ,ljwg= 0 ,ljglys = 0;
//		double jsdl = 0;  //参与计算的电量
//		double wgdl = 0 , zwgdl = 0;   //无功电量
//		double bsdl = 0;   //变损电量
//		String s_item = "";
//		int i_item = 0;
//		Map map = new HashMap();
//		map.put("ym_SEARCH", new String[]{ym});
//		//首先删除变电站主变损失统计表当月数据
//		List list = XsglBeanFactory.getIXsBdzdlssDomain().queryNoPage(map);
//		Iterator it = list.iterator();
//		while (it.hasNext()){
//			XsglBeanFactory.getIXsBdzdlssDomain().delete(((XsBdzdlss)it.next()).getId());
//		}
//		//电量统计
//	  	  /*
//	  	   * S1：从jc_transf表中取出当前所有变电站编号；循环处理所有变电站；
//	  	   * S2:对每一个变电站，用编号去查找xs_relation_bdzzb表的该站所有关系，依次处理；
//	  	   */
//		List l_bdz = XsglBeanFactory.getIXsTrsubstationDomain().getAllXsTrsubstationNoPage(new HashMap());
//		Iterator it_bdz = l_bdz.iterator();
//		while (it_bdz.hasNext()){   //循环处理每一个变电站
//			bysr = 0; bysc = 0; byssdl = 0; byssl = 0;zwgdl = 0;wgdl = 0;
//			ljsr = 0; ljsc = 0; ljssdl = 0; ljssl = 0;ljwg = 0;ljglys = 0;
//
//			XsTrsubstation bdz = (XsTrsubstation)it_bdz.next();
//			String bdzbh = bdz.gettrsubstation_id();
//			Map m_zbgx = new HashMap();
//			m_zbgx.put("tjdx_SEARCH", new String[]{bdzbh});
//			List l_zbgx = XsglBeanFactory.getIXsGxBdzdlDomain().queryNoPage(m_zbgx);
//			Iterator it_zbgx = l_zbgx.iterator();
//			if(it_zbgx.hasNext()){
//				while (it_zbgx.hasNext()){  //处理每一条关系
//					XsGxBdzdl xsgxzb = (XsGxBdzdl)it_zbgx.next();
//					String jldid = xsgxzb.getJld();   //首先查找到该条关系的计量点
//					s_item=xsgxzb.getJsdl();
//					if(s_item.equals("I")){
//						s_item = "5";
//					}
//					if(s_item.equals("O")){
//						s_item = "6";
//					}
//					i_item=Integer.parseInt(s_item);
//					XsLine jld = XsglBeanFactory.getIXsLineDomain().getXsLine(jldid);
//					if(XsglBeanFactory.getIXsLineDomain().getXsLine(jldid)== null||XsglBeanFactory.getIXsLineDomain().getXsLine(jldid).equals("")){
//						msg = msg + "【"+bdz.getname()+"】的一条关系涉及的计量点不存在,请检查！</br>";
//						//continue;
//					}
//					else{
//
//						switch(i_item){
//							case 0: jsdl = convertDouble(jld.getp_totalqua()));break;
//							case 5:  jsdl = convertDouble(jld.getp_totalqua()));wgdl = convertDouble(jld.getup_totalqua()));break;
//							case 6: jsdl = convertDouble(jld.getp_totalqua()));break;
//							case 1:  jsdl = convertDouble(jld.getp_totalqua()));break;
//
//						}
//					}
//
//					if(xsgxzb.getTjdllx().equals("I")){
//						if(xsgxzb.getJsgx().equals("0")){  //计算关系是加
//							bysr = bysr + jsdl;
//							zwgdl = zwgdl + wgdl;
//						}
//						if(xsgxzb.getJsgx().equals("1")){ //计算关系是减
//							bysr = bysr - jsdl;
//							zwgdl = zwgdl - wgdl;
//						}
//					}
//					if(xsgxzb.getTjdllx().equals("O")){
//						if(xsgxzb.getJsgx().equals("0")){  //计算关系是加
//							//ToolBean.system("线损统计之变电站电量损失 本月输出：jsdl="+jsdl);
//							bysc = bysc + jsdl;
//							//  ToolBean.system("线损统计之变电站电量损失 本月输出：bysc="+bysc);
//						}
//						if(xsgxzb.getJsgx().equals("1")){ //计算关系是减
//							bysc = bysc - jsdl;
//						}
//					}
////					  if(xsgxzb.getTjdllx().equals("3")){
////						  if(xsgxzb.getJsgx().equals("0")){  //计算关系是加
////							  zwgdl = zwgdl + wgdl;
////						  }
////						  if(xsgxzb.getJsgx().equals("1")){ //计算关系是减
////							  zwgdl = zwgdl - wgdl;
////						  }
////					  }
//				}
//			}
//			else{
//				msg=msg+"没有与变电站【"+bdz.getname()+"】对应的关系，请先维护关系！</br>";
//				////continue;
//			}
//
//			byssdl = bysr - bysc;
//			//ToolBean.system("线损统计之变电站电量损失1：byssdl="+byssdl+"bysr="+bysr);
//			if(bysr == 0){
//				//ToolBean.system("线损统计之变电站电量损失2：byssdl="+byssdl+"bysr="+bysr);
//				byssl = 0;
//			}
//			else{
//				//ToolBean.system("线损统计之变电站电量损失3：byssdl="+byssdl+"bysr="+bysr);
//				byssl = Arithmetic.round(byssdl/bysr*100,2);
//				//ToolBean.system("线损统计之变电站电量损失4：byssdl="+byssdl+"bysr="+bysr+" byssl="+byssl);
//			}
//			double glys = 0;
//			if(bysr*bysr+zwgdl*zwgdl ==0){
//				glys = 0;
//			}
//			else{
//				glys=Arithmetic.div(bysr, (Math.sqrt(bysr * bysr+zwgdl * zwgdl)),2);
//			}
//			//往xs_bdzzbss插入一条记录
//			XsBdzdlss xsBdzdlss = new XsBdzdlss();
//			xsBdzdlss.setId(bdzbh+ym);
//			xsBdzdlss.setBdzbh(bdzbh);
//			xsBdzdlss.setYm(ym);
//			xsBdzdlss.setBysr(bysr));
//			xsBdzdlss.setBysc(bysc));
//			xsBdzdlss.setByss(byssdl));
//			xsBdzdlss.setByssl(byssl).setScale(2,BigDecimal.ROUND_HALF_UP));
//			xsBdzdlss.setWgdl(wgdl));
//			xsBdzdlss.setGlys(glys).setScale(2,BigDecimal.ROUND_HALF_UP));
//			ToolBean.system("线损统计之变电站电量损失5：byssdl="+byssdl+"bysr="+bysr+" byssl="+byssl);
//			XsglBeanFactory.getIXsBdzdlssDomain().insert(xsBdzdlss);
//			/**
//			 * 下面计算累计
//			 * 累计计算方式：首先从Xs_Operator_HIS中找到本次计算紧挨着的上一个时间段pre_date：例如 20120317-20120413的上一个时间段为：20120206-20120317
//			 * 如果有多个相同的紧挨日期，选择任何一个均可以：但是首先保证以前所有的电量计算正确。
//			 * 然后从统计结果表中查询出上一个时间段的累计，从本次统计中找到本次电量合计。二者相加即可。
//			 */
//			String pre_date=ToolBean.getPreRiqi(ym);
//			Map map_lj=new HashMap();
//			map_lj.put("YM_SEARCH", new String[]{pre_date});
//			map_lj.put("BDZBH_SEARCH",  new String[]{bdzbh});
//			ToolBean.system("BDZDLSS***pre_date"+pre_date+"bdzbh="+bdzbh);
//			List list_lj=XsglBeanFactory.getIXsBdzdlssDomain().queryNoPage(map_lj);
//			ToolBean.system("BDZDLSS***list_lj"+list_lj.size()+"list_lj="+list_lj.isEmpty());
//			XsBdzdlss xsBdzdlssPre=new XsBdzdlss();
//			if(list_lj.isEmpty()){
//				msg=msg+"没有查询到变电站统计中与"+ym+"变电站编号为"+bdzbh+"相邻的上一个时间段的统计结果，请先统计上一个阶段的数据！</br>";
//			}
//			else{
//				xsBdzdlssPre=((XsBdzdlss)list_lj.get(0));
//				BigDecimal yglj=convert(xsBdzdlssPre.getLjsr());
//				BigDecimal sclj=convert(xsBdzdlssPre.getLjsc());
//				BigDecimal sslj=convert(xsBdzdlssPre.getLjss());
//				BigDecimal wglj=convert(xsBdzdlssPre.getLjwg());
//				yglj=yglj.add(bysr));
//				wglj=wglj.add(0));
//				sslj=sslj.add(byssdl));
//				sclj=sclj.add(bysc));
//				XsBdzdlss xsStatisticsAllUpdate=XsglBeanFactory.getIXsBdzdlssDomain().get(bdzbh+ym);
//				xsStatisticsAllUpdate.setLjsr(yglj);
//				xsStatisticsAllUpdate.setLjsc(sclj);
//				xsStatisticsAllUpdate.setLjss(sslj);
//				xsStatisticsAllUpdate.setLjwg(wglj);
//				XsglBeanFactory.getIXsBdzdlssDomain().update(xsStatisticsAllUpdate);
//			}
//
//		}
//		return msg;
//	}


	@RequiresPermissions("dljs:xsLinewastage:edit")
	@RequestMapping(value = "saveRemoteData")
	public String saveRemoteData(XsLinewastage xsLinewastage, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model,String switchname, double qua1, double qua2,
							   double qua3, double qua4) {
//		 String msg = "";
//		 Map map = new HashMap();
//		 map.put("remotenameSearch", new String[] { switchname });
//		 List list = XsglBeanFactory.getIXsRemotemirDomain()
//		 .getAllXsRemotemirNoPage(map);
//		 if (list.isEmpty()) {
//		 msg = "没有查询到与开关名称为" + switchname + "对应的线路，请输入其对应关系后再进行取数！";
//		 } else {
//		 Iterator it = list.iterator();
//		 while (it.hasNext()) {
//		 XsRemotemir xsRemotemir = (XsRemotemir) it.next();
//		 String line_id = xsRemotemir.getxs_lineid();
//		 XsLine xsLine = XsglBeanFactory.getIXsLineDomain().getXsLine(
//		 line_id);
//		 if (xsLine != null) {
//		 String prflag = xsLine.getPrflag();
//		 if ("0".equals(prflag)) {
//		 xsLine.setp_evalue(qua1);
//		 xsLine.setup_evalue(qua3);
//		 }
//		 if ("1".equals(prflag)) {
//		 xsLine.setp_evalue(qua2);
//		 xsLine.setup_evalue(qua4);
//		 }
//		 }
//		 XsglBeanFactory.getIXsLineDomain().updateXsLine(xsLine);
//		 }
//		 }

		Page<XsLinewastage> page = xsLinewastageService.findPage(new Page<XsLinewastage>(request, response), xsLinewastage);
		model.addAttribute("page", page);
		addMessage(redirectAttributes, "电量计算完成");
		return "llas/dljs/xsLinewastageList";
	}
	/**
	 * 更新止码到起码，清除换表，合计等电量
	 */
	@RequiresPermissions("dljs:xsLinewastage:edit")
	@RequestMapping(value = "refreshData")
	public void refreshData() {
		double a = 0;
		List<XsLine> list=this.xsLineService.findList(new XsLine());
		for(XsLine line : list){
			line.setFpSvalue(line.getFpEvalue());
			line.setFupSvalue(line.getFupEvalue());
			line.setRpSvalue(line.getRpEvalue());
			line.setRupSvalue(line.getRupEvalue());
			line.setFpChmeter(a);
			line.setFpExcerpqua(a);
			line.setFpSubjoinqua(a);
			line.setFpTotalqua(a);

			line.setFupChmeter(a);
			line.setFupExcerpqua(a);
			line.setFupSubjoinqua(a);
			line.setFupTotalqua(a);

			line.setRpChmeter(a);
			line.setRpExcerpqua(a);
			line.setRpSubjoinqua(a);
			line.setRpTotalqua(a);
			line.setRupChmeter(a);
			line.setRupExcerpqua(a);
			line.setRupSubjoinqua(a);
			line.setRupTotalqua(a);

			line.setpTotalqua(a);
			line.setUpTotalqua(a);
			this.xsLineService.save(line);
		}
	}

	/**
	 * 数据结存
	 */
	public String processHistoricalData() {
		String msg = "";
		//首先处理xs_line
		//1.删除历史表当月记录（如果有）
//		XsOperator xsOperator=XsglBeanFactory.getIXsOperatorDomain().get("00");
//		String qmdate=xsOperator.getQsrq();
//		String zmdate=xsOperator.getJsrq();
//		String date =ToolBean.contactRiQi();
//		Map map=new HashMap();
//		map.put("YM_SEARCH", new String[]{date});
//		List list=XsglBeanFactory.getIXsLineHisDomain().queryNoPage(map);
//		Iterator it_lineHis=list.iterator();
//		while(it_lineHis.hasNext()){
//			XsLineHis xlh=(XsLineHis)it_lineHis.next();
//			XsglBeanFactory.getIXsLineHisDomain().delete(xlh.getId());
//		}
//
//		List list_line=XsglBeanFactory.getIXsLineDomain().getAllXsLineNoPage(new HashMap());
//		Iterator it_line=list_line.iterator();
//		while(it_line.hasNext()){
//			XsLine xsLine=(XsLine)it_line.next();
//			XsLineHis xsLineHis=new XsLineHis();
//			xsLineHis.setId(date.concat(xsLine.getline_id()));
//
//			xsLineHis.setYm(date);
//
//			xsLineHis.setLineId(xsLine.getline_id());
//
//			xsLineHis.setLineCode(xsLine.getline_code());
//
//			xsLineHis.setLineName(xsLine.getline_name());
//
//			xsLineHis.setTrsubstationId(xsLine.gettrsubstation_id());
//
//			xsLineHis.setIsparline(xsLine.getisparline());
//
//			xsLineHis.setVoltage(xsLine.getvoltage());
//
//			xsLineHis.setIsspecline(xsLine.getisspecline());
//
//			xsLineHis.setFpSvalue(xsLine.getFp_svalue()));
//
//			xsLineHis.setFpEvalue(xsLine.getFp_evalue()));
//
//			xsLineHis.setFpSubjoinqua(xsLine.getFp_subjoinqua()));
//
//			xsLineHis.setFpChmeter(xsLine.getFp_chmeter()));
//
//			xsLineHis.setFpExcerpqua(xsLine.getFp_excerpqua()));
//
//			xsLineHis.setPtotalqua(xsLine.getp_totalqua()));
//
//			xsLineHis.setFupSvalue(xsLine.getFup_svalue()));
//
//			xsLineHis.setFupEvalue(xsLine.getFup_evalue()));
//
//			xsLineHis.setFupSubjoinqua(xsLine.getFp_subjoinqua()));
//
//			xsLineHis.setFupChmeter(xsLine.getFup_chmeter()));
//
//			xsLineHis.setFupExcerpqua(xsLine.getFup_excerpqua()));
//
//			xsLineHis.setUpTotalqua(xsLine.getup_totalqua()));
//
//			xsLineHis.setIntefactor(xsLine.getintefactor()));
//
//			xsLineHis.setMaxcapacity(xsLine.getmaxcapacity()));
//
//			xsLineHis.setK(xsLine.getk()));
//
//			xsLineHis.setK1(xsLine.getk1()));
//
//			xsLineHis.setResistance(xsLine.getresistance()));
//
//			xsLineHis.setUnloadWaste(xsLine.getunload_waste()));
//
//			xsLineHis.setCirWaste(xsLine.getcir_waste()));
//
//			xsLineHis.setTemperature(xsLine.gettemperature()));
//
//			xsLineHis.setHours(xsLine.gethours()));
//
//			xsLineHis.setCapacity(xsLine.getcapacity()));
//
//			xsLineHis.setPowerfactor(xsLine.getpowerfactor());
//
//			xsLineHis.setScheWrate(xsLine.getsche_wrate()));
//
//			xsLineHis.setChangesign(xsLine.getChangesign());
//
//			xsLineHis.setPrflag(xsLine.getPrflag());
//
//			xsLineHis.setRptvissign(xsLine.getRptvissign());
//
//			xsLineHis.setFpTotalqua(xsLine.getFp_totalqua()));
//
//			xsLineHis.setFupTotalqua(xsLine.getFup_totalqua()));
//
//			xsLineHis.setRpSvalue(xsLine.getRp_svalue()));
//
//			xsLineHis.setRpEvalue(xsLine.getRp_evalue()));
//
//			xsLineHis.setRpSubjoinqua(xsLine.getRp_subjoinqua()));
//
//			xsLineHis.setRpChmeter(xsLine.getRp_chmeter()));
//
//			xsLineHis.setRpExcerpqua(xsLine.getRp_excerpqua()));
//
//			xsLineHis.setRpTotalqua(xsLine.getRp_totalqua()));
//
//			xsLineHis.setRupSvalue(xsLine.getRup_svalue()));
//
//			xsLineHis.setRupEvalue(xsLine.getRup_evalue()));
//
//			xsLineHis.setRupSubjoinqua(xsLine.getRup_subjoinqua()));
//
//			xsLineHis.setRupChmeter(xsLine.getRup_chmeter()));
//
//			xsLineHis.setRupExcerpqua(xsLine.getRup_excerpqua()));
//
//			xsLineHis.setRupTotalqua(xsLine.getRup_totalqua()));
//
//			xsLineHis.setQmdate(qmdate);
//
//			xsLineHis.setZmdate(zmdate);
//
//			XsglBeanFactory.getIXsLineHisDomain().insert(xsLineHis);
//		}
//
//		//将换表的起止码赋值给原表，删除换表（这步骤很重要）
//		Map map_hb=new HashMap();
//		map_hb.put("changesignSearch", new String[]{"1"});
//		List list_newline=XsglBeanFactory.getIXsLineDomain().getAllXsLineNoPage(map_hb);
//		ToolBean.system("历史数据处理"+list_newline.size());
//		Iterator it_newline=list_newline.iterator();
//		while(it_newline.hasNext()){
//			XsLine xsLineNew=(XsLine)it_newline.next();
//			String lineCode=xsLineNew.getline_code();
//			double fp_svalue=xsLineNew.getFp_svalue();
//			double fp_evalue=xsLineNew.getFp_evalue();
//			double fup_svalue=xsLineNew.getFup_svalue();
//			double fup_evalue=xsLineNew.getFup_evalue();
//			double rp_svalue=xsLineNew.getRp_svalue();
//			double rp_evalue=xsLineNew.getRp_evalue();
//			double rup_svalue=xsLineNew.getRup_svalue();
//			double rup_evalue=xsLineNew.getRup_evalue();
//			//查找该linecode并且changesign='0'的
//			Map map_hb1=new HashMap();
//			map_hb1.put("changesignSearch", new String[]{"0"});
//			map_hb1.put("line_codeSearch", new String[]{lineCode});
//			List list_oldline=XsglBeanFactory.getIXsLineDomain().getAllXsLineNoPage(map_hb1);
//			Iterator it_oldline=list_oldline.iterator();
//			while(it_oldline.hasNext()){
//				XsLine xsLineOld=(XsLine)it_oldline.next();
//				xsLineOld.setFp_svalue(fp_svalue);
//				xsLineOld.setFp_evalue(fp_evalue);
//				xsLineOld.setFup_svalue(fup_svalue);
//				xsLineOld.setFup_evalue(fup_evalue);
//
//				xsLineOld.setRp_svalue(rp_svalue);
//				xsLineOld.setRp_evalue(rp_evalue);
//				xsLineOld.setRup_svalue(rup_svalue);
//				xsLineOld.setRup_evalue(rup_evalue);
//				XsglBeanFactory.getIXsLineDomain().updateXsLine(xsLineOld);
//			}
////				删除changesign='1'的表
//			XsglBeanFactory.getIXsLineDomain().deleteXsLine(xsLineNew.getline_id());
//		}
//
//
////		在Xs_Operator表中新建一条的数据
//		XsglBeanFactory.getIXsOperatorHisDomain().delete(xsOperator.getQsrq().concat(xsOperator.getJsrq()).concat(xsOperator.getYm()));
//		XsOperatorHis  xsOperatorHis = new XsOperatorHis();
//		xsOperatorHis.setId(xsOperator.getQsrq().concat(xsOperator.getJsrq()).concat(xsOperator.getYm()));
//		xsOperatorHis.setDay(xsOperator.getDay());
//		xsOperatorHis.setHours(xsOperator.getHours());
//		xsOperatorHis.setJsrq(xsOperator.getJsrq());
//		xsOperatorHis.setLister(xsOperator.getLister());
//		xsOperatorHis.setPrincipal(xsOperator.getPrincipal());
//		xsOperatorHis.setQsrq(xsOperator.getQsrq());
//		xsOperatorHis.setYm(xsOperator.getYm());
//		XsglBeanFactory.getIXsOperatorHisDomain().insert(xsOperatorHis);
//		//处理完后将Xs_Operator 表中的 qsrq换成 jsrq;
//
//		xsOperator.setQsrq(zmdate);
//		XsglBeanFactory.getIXsOperatorDomain().update(xsOperator);



		return msg;
	}

	/**
	 * 保存导入的电量数据
	 */
//	public String saveElec(String month, String lineid, double cjdl,
//						   double xiaosdl, double xiansdl, double bsdl,
//						   IMessageHandler messageHandler, String flagStr) {
//		String msg = "";
//		Map map = new HashMap();
//
//		//map.put("lxbhSearch", new String[] { lineid });
//		//List list = XsglBeanFactory.getIXsLxmirDomain().getAllXsLxmirNoPage(map);
//		System.out.println("DpDomain="+lineid+"--"+xiaosdl+"--"+bsdl);
//		map.put("line_nameSearch", new String[] { lineid });  //其实传过来的是郎新的线路名称
//		List list = XsglBeanFactory.getIXsLineDomain().getAllXsLineNoPage(map);
//		System.out.println("DpDomain="+lineid+"--"+xiaosdl+"--"+list.size());
//		if (list.isEmpty()) {
//			msg = "没有查询到与线路为【" + lineid + "】同名的线路，请检查导入文件或者线路基础信息！</br>";
//		} else {
//			Iterator it = list.iterator();
//			while (it.hasNext()) {
//				XsLine xsLine = (XsLine) it.next();
//				String lineId = xsLine.getline_id();
//				System.out.println("DpDomain="+lineid+"--"+lineId+"--"+xiaosdl);
//				XsLinewastage xsLinewastage = XsglBeanFactory
//						.getIXsLinewastageDomain().get(lineId);
//				if (xsLinewastage != null) {
//					if (flagStr.equals("sdl")) { // 获取售电量
//						xsLinewastage.setSalequaInsdh(xiaosdl)); //营业口径售电量
//						XsglBeanFactory.getIXsLinewastageDomain()
//								.update(xsLinewastage);
//					}
//
//					if (flagStr.equals("bsdl")) { // 获取变损电量
//						xsLinewastage.setTrwasteInsdh(bsdl));
//						XsglBeanFactory.getIXsLinewastageDomain()
//								.update(xsLinewastage);
//					}
//				}
//
//			}
//
//		}
//
//		return msg;
//	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public double setObjTo0(Object obj) {
		if(null == obj) {
			return 0;
		}
		else {
			return Double.valueOf(obj.toString());
		}
	}
	public double setObjTo0(Double obj) {
		if(null == obj) {
			return 0;
		}
		else {
			return obj;
		}
	}
}