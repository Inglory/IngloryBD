/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.Arithmetic;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.entity.XsLine;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.tjgx.entity.XsRelation35;
import com.yee.yide.llas.tjgx.service.XsRelation35Service;
import com.yee.yide.llas.tjxx.entity.XsStatistics35;
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
 * 35kV线损统计结果Controller
 * @author evay_leec
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/tjxx/xsStatistics35")
public class XsStatistics35Controller extends BaseController {

	@Autowired
	private XsStatistics35Service xsStatistics35Service;
	@Autowired
	private XsLineService xsLineService;
	@Autowired
	private XsRelation35Service xsRelation35Service;
	
	@ModelAttribute
	public XsStatistics35 get(@RequestParam(required=false) String id) {
		XsStatistics35 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsStatistics35Service.get(id);
		}
		if (entity == null){
			entity = new XsStatistics35();
		}
		return entity;
	}
	
	@RequiresPermissions("tjxx:xsStatistics35:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsStatistics35 xsStatistics35, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		Page<XsStatistics35> page = xsStatistics35Service.findPage(new Page<XsStatistics35>(request, response), xsStatistics35); 
		model.addAttribute("page", page);
		return "llas/tjxx/xsStatistics35List";
	}

	@RequiresPermissions("tjxx:xsStatistics35:view")
	@RequestMapping(value = "form")
	public String form(XsStatistics35 xsStatistics35, Model model) {
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		model.addAttribute("xsStatistics35", xsStatistics35);
		return "llas/tjxx/xsStatistics35Form";
	}

	@RequiresPermissions("tjxx:xsStatistics35:edit")
	@RequestMapping(value = "save")
	public String save(XsStatistics35 xsStatistics35, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsStatistics35)){
			return form(xsStatistics35, model);
		}
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		xsStatistics35Service.save(xsStatistics35);
		addMessage(redirectAttributes, "保存35kV线损统计结果成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatistics35/?repage";
	}
	
	@RequiresPermissions("tjxx:xsStatistics35:edit")
	@RequestMapping(value = "delete")
	public String delete(XsStatistics35 xsStatistics35, RedirectAttributes redirectAttributes) {
		xsStatistics35Service.delete(xsStatistics35);
		addMessage(redirectAttributes, "删除35kV线损统计结果成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatistics35/?repage";
	}

	@RequiresPermissions("tjxx:xsStatistics35:view")
	@RequestMapping(value = {"tongji"})
	public String tongji(Date qsrq, Date jsrq, HttpServletRequest request, HttpServletResponse response, Model model) {

		String msg="";
		double zygdl,zwgdl,zxsdl,zbsdl,zbsdl_sdh,zxsdl_sdh ,ssdl,ssdl_sdh;
		double ygdl,wgdl,xsdl,xsdl_sdh,bsdl,bsdl_sdh;
		double xsl,zhxsl,ll,xsl_sdh,zhxsl_sdh;
		String tjxl , lineCode , lineName ;
		//删除当前时间段的统计结果
		XsStatistics35 xsStatistics35=new XsStatistics35();
		xsStatistics35.setQsrq(qsrq);
		xsStatistics35.setJsrq(jsrq);
		xsStatistics35Service.deleteQsrqToJsrq(xsStatistics35);
		//2.开始统计：查询35kV统计线路，然后对每一条线路，在Xs_relation_35中查询其统计关系，逐条处理
		XsLine xsLine35 = new XsLine();
		xsLine35.setVoltage("35kV");
		xsLine35.setIsparline("0"); //非母线
		List<XsLine> list35 = xsLineService.findList(xsLine35);
		for(XsLine xsLine : list35){
			zygdl=0;zwgdl=0;zxsdl = 0;zbsdl = 0;zbsdl_sdh = 0;zxsdl_sdh = 0;ssdl= 0;ssdl_sdh= 0;
			ygdl= 0;wgdl= 0;xsdl= 0;xsdl_sdh= 0;bsdl= 0;bsdl_sdh= 0;
			xsl= 0;zhxsl= 0;ll= 0;xsl_sdh= 0;zhxsl_sdh= 0;
			tjxl=xsLine.getId(); //统计的线路ID
			lineCode = xsLine.getLineCode();
			lineName = xsLine.getLineName();
			//对每一条35kV线路，查询其关系
			XsRelation35 xsRelation = new XsRelation35();
			xsRelation.setParlineId(tjxl);
			List<XsRelation35> listR35 = xsRelation35Service.findList(xsRelation);
			for(XsRelation35 xsr35 : listR35){
				String quakind=xsr35.getQuaKind();
				String lineid=xsr35.getLineId();
				String calkind=xsr35.getCalKind();
				XsLine xsLineJs=xsLineService.get(lineid);
				ygdl=xsLineJs.getpTotalqua();
				wgdl=xsLineJs.getUpTotalqua();
				if("ygdl".equals(quakind)){                  //统计有功电量

					if(calkind.equals("add")){
						zygdl= Arithmetic.add(zygdl, ygdl);
						zwgdl=Arithmetic.add(zwgdl,wgdl);
					}
					else{
						zygdl=Arithmetic.sub(zygdl, ygdl);
						zwgdl=Arithmetic.sub(zwgdl,wgdl);
					}
				}
				if(quakind.equals("xsdl")){                //统计销售电量

					if(calkind.equals("add")){       // jia
						zxsdl=Arithmetic.add(zxsdl, ygdl);
						zxsdl_sdh=Arithmetic.add(zxsdl_sdh,ygdl);
					}
					else{
						zxsdl=Arithmetic.sub(zxsdl, ygdl);
						zxsdl_sdh=Arithmetic.sub(zxsdl_sdh,ygdl);
					}
				}
			}
			ssdl=Arithmetic.sub(zygdl,zxsdl);
			ssdl_sdh=ssdl;
			if(zygdl==0){
				xsl=0;
				zhxsl=0;
				xsl_sdh=0;
				zhxsl_sdh=0;
			}
			else{
				xsl=Arithmetic.div(ssdl, zygdl,4)*100;
				zhxsl=Arithmetic.div(Arithmetic.add(ssdl,zbsdl),zygdl,4)*100;
				xsl_sdh=xsl;
				zhxsl_sdh=zhxsl;
			}
			//计算力率
			if(zygdl*zygdl+zwgdl*zwgdl==0){
				ll=0;
			}
			else{
				ll=Arithmetic.div(zygdl,(Math.sqrt(zygdl*zygdl+zwgdl*zwgdl)),2);
			}


			//插入数据到数据库

			xsStatistics35.setYm(qsrq.toString().substring(0,7));
			xsStatistics35.setLineId(tjxl);
			xsStatistics35.setPowerQua(zygdl);
			xsStatistics35.setNonpowerQua(zwgdl);
			xsStatistics35.setSaleQua(zxsdl);
			xsStatistics35.setSalequaInsdh(zxsdl_sdh);
			xsStatistics35.setTrwasteQua(zbsdl);
			xsStatistics35.setTrwastequaInsdh(zbsdl_sdh);
			xsStatistics35.setWasteQua(ssdl);
			xsStatistics35.setWastequaInsdh(ssdl_sdh);
			xsStatistics35.setWasteRate(Arithmetic.round(xsl,2));
			xsStatistics35.setWasterateInsdh(Arithmetic.round(xsl_sdh,2));
			xsStatistics35.setCompWrate(Arithmetic.round(zhxsl,2));
			xsStatistics35.setCompwrateInsdh(Arithmetic.round(zhxsl_sdh,2));
			xsStatistics35.setPowerrate(Arithmetic.round(ll,2));
			xsStatistics35.setLineCode(lineCode);
			xsStatistics35.setLinename(lineName);
			xsStatistics35.setQsrq(qsrq);
			xsStatistics35.setJsrq(jsrq);


			xsStatistics35Service.save(xsStatistics35);

			/**
			 * 下面计算累计
			 * 累计计算方式：首先从Xs_Operator中找到本次计算紧挨着的上一个时间段pre_date：例如 20120317-20120413的上一个时间段为：20120206-20120317
			 * 如果有多个相同的紧挨日期，选择任何一个均可以：但是首先保证以前所有的电量计算正确。
			 * 然后从统计结果表中查询出上一个时间段的累计，从本次统计中找到本次电量合计。二者相加即可。
			 */

			XsStatistics35 xsStatistics35Pre=new XsStatistics35();
			xsStatistics35Pre.setJsrq(qsrq);
			List<XsStatistics35> list35Pre = xsStatistics35Service.findList(xsStatistics35Pre);
			if(list35Pre.isEmpty()){
				msg=msg+"没有查询到35kV统计中与线路编号为"+lineCode+"相邻的上一个时间段的统计结果，请先统计上一个阶段的数据！</br>";
			}
			else{
				xsStatistics35Pre=((XsStatistics35)list35Pre.get(0));
				double yglj=xsStatistics35Pre.getYglj();
				double sclj=xsStatistics35Pre.getYglj();
				double sslj=xsStatistics35Pre.getYglj();
				double wglj=xsStatistics35Pre.getWglj();
				yglj=Arithmetic.add(yglj,zygdl);
				wglj=Arithmetic.add(wglj,zwgdl);
				sslj=Arithmetic.add(sslj,ssdl);
				sclj=Arithmetic.add(sclj,zxsdl);

				xsStatistics35.setYglj(yglj);
				xsStatistics35.setSclj(sclj);
				xsStatistics35.setSslj(sslj);
				xsStatistics35.setWglj(wglj);
				this.xsStatistics35Service.save(xsStatistics35);
			}

		}

		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		Page<XsStatistics35> page = xsStatistics35Service.findPage(new Page<XsStatistics35>(request, response), xsStatistics35);
		model.addAttribute("page", page);
		return "llas/tjxx/xsStatistics35List";
	}
}