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
import com.yee.yide.llas.tjgx.entity.XsRelation110;
import com.yee.yide.llas.tjgx.service.XsRelation110Service;
import com.yee.yide.llas.tjxx.entity.XsStatistics110;
import com.yee.yide.llas.tjxx.service.XsStatistics110Service;
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
 * 110kV线损统计结果信息Controller
 * @author evay_leec
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/tjxx/xsStatistics110")
public class XsStatistics110Controller extends BaseController {

	@Autowired
	private XsStatistics110Service xsStatistics110Service;
	@Autowired
	private XsLineService xsLineService;
	@Autowired
	private XsRelation110Service xsRelation110Service;

	@ModelAttribute
	public XsStatistics110 get(@RequestParam(required=false) String id) {
		XsStatistics110 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsStatistics110Service.get(id);
		}
		if (entity == null){
			entity = new XsStatistics110();
		}
		return entity;
	}
	
	@RequiresPermissions("tjxx:xsStatistics110:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsStatistics110 xsStatistics110, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> Listline110 = xsLineService.findListLineByDydj("110kV");
		model.addAttribute("Listline110", Listline110);
		Page<XsStatistics110> page = xsStatistics110Service.findPage(new Page<XsStatistics110>(request, response), xsStatistics110); 
		model.addAttribute("page", page);
		return "llas/tjxx/xsStatistics110List";
	}

	@RequiresPermissions("tjxx:xsStatistics110:view")
	@RequestMapping(value = "form")
	public String form(XsStatistics110 xsStatistics110, Model model) {
		List<String> Listline110 = xsLineService.findListLineByDydj("110kV");
		model.addAttribute("Listline110", Listline110);
		model.addAttribute("xsStatistics110", xsStatistics110);
		return "llas/tjxx/xsStatistics110Form";
	}

	@RequiresPermissions("tjxx:xsStatistics110:edit")
	@RequestMapping(value = "save")
	public String save(XsStatistics110 xsStatistics110, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsStatistics110)){
			return form(xsStatistics110, model);
		}
		List<String> Listline110 = xsLineService.findListLineByDydj("110kV");
		model.addAttribute("Listline110", Listline110);
		xsStatistics110Service.save(xsStatistics110);
		addMessage(redirectAttributes, "保存110kV线损统计结果信息成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatistics110/?repage";
	}
	
	@RequiresPermissions("tjxx:xsStatistics110:edit")
	@RequestMapping(value = "delete")
	public String delete(XsStatistics110 xsStatistics110, RedirectAttributes redirectAttributes) {
		xsStatistics110Service.delete(xsStatistics110);
		addMessage(redirectAttributes, "删除110kV线损统计结果信息成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatistics110/?repage";
	}
	@RequiresPermissions("tjxx:xsStatistics110:view")
	@RequestMapping(value = {"tongji"})
	public String tongji(Date qsrq, Date jsrq, HttpServletRequest request, HttpServletResponse response, Model model) {

		String msg="";
		double zygdl,zwgdl,zxsdl,zbsdl,zbsdl_sdh,zxsdl_sdh ,ssdl,ssdl_sdh;
		double ygdl,wgdl,xsdl,xsdl_sdh,bsdl,bsdl_sdh;
		double xsl,zhxsl,ll,xsl_sdh,zhxsl_sdh;
		String tjxl , lineCode , lineName ;
		//删除当前时间段的统计结果
		XsStatistics110 xsStatistics110=new XsStatistics110();
		xsStatistics110.setQsrq(qsrq);
		xsStatistics110.setJsrq(jsrq);
		xsStatistics110Service.deleteQsrqToJsrq(xsStatistics110);
		//2.开始统计：查询110kV统计线路，然后对每一条线路，在Xs_relation_110中查询其统计关系，逐条处理
		XsLine xsLine110 = new XsLine();
		xsLine110.setVoltage("110kV");
		xsLine110.setIsparline("0"); //非母线
		List<XsLine> list110 = xsLineService.findList(xsLine110);
		for(XsLine xsLine : list110){
			zygdl=0;zwgdl=0;zxsdl = 0;zbsdl = 0;zbsdl_sdh = 0;zxsdl_sdh = 0;ssdl= 0;ssdl_sdh= 0;
			ygdl= 0;wgdl= 0;xsdl= 0;xsdl_sdh= 0;bsdl= 0;bsdl_sdh= 0;
			xsl= 0;zhxsl= 0;ll= 0;xsl_sdh= 0;zhxsl_sdh= 0;
			tjxl=xsLine.getId(); //统计的线路ID
			lineCode = xsLine.getLineCode();
			lineName = xsLine.getLineName();
			//对每一条110kV线路，查询其关系
			XsRelation110 xsRelation = new XsRelation110();
			xsRelation.setParlineId(tjxl);
			List<XsRelation110> listR110 = xsRelation110Service.findList(xsRelation);
			for(XsRelation110 xsr110 : listR110){
				String quakind=xsr110.getQuaKind();
				String lineid=xsr110.getLineId();
				String calkind=xsr110.getCalKind();
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

			xsStatistics110.setYm(qsrq.toString().substring(0,7));
			xsStatistics110.setLineId(tjxl);
			xsStatistics110.setPowerQua(zygdl);
			xsStatistics110.setNonpowerQua(zwgdl);
			xsStatistics110.setSaleQua(zxsdl);
			xsStatistics110.setSalequaInsdh(zxsdl_sdh);
			xsStatistics110.setTrwasteQua(zbsdl);
			xsStatistics110.setTrwastequaInsdh(zbsdl_sdh);
			xsStatistics110.setWasteQua(ssdl);
			xsStatistics110.setWastequaInsdh(ssdl_sdh);
			xsStatistics110.setWasteRate(Arithmetic.round(xsl,2));
			xsStatistics110.setWasterateInsdh(Arithmetic.round(xsl_sdh,2));
			xsStatistics110.setCompWrate(Arithmetic.round(zhxsl,2));
			xsStatistics110.setCompwrateInsdh(Arithmetic.round(zhxsl_sdh,2));
			xsStatistics110.setPowerrate(Arithmetic.round(ll,2));
			xsStatistics110.setLineCode(lineCode);
			xsStatistics110.setLinename(lineName);
			xsStatistics110.setQsrq(qsrq);
			xsStatistics110.setJsrq(jsrq);


			xsStatistics110Service.save(xsStatistics110);

			/**
			 * 下面计算累计
			 * 累计计算方式：首先从Xs_Operator中找到本次计算紧挨着的上一个时间段pre_date：例如 20120317-20120413的上一个时间段为：20120206-20120317
			 * 如果有多个相同的紧挨日期，选择任何一个均可以：但是首先保证以前所有的电量计算正确。
			 * 然后从统计结果表中查询出上一个时间段的累计，从本次统计中找到本次电量合计。二者相加即可。
			 */

			XsStatistics110 xsStatistics110Pre=new XsStatistics110();
			xsStatistics110Pre.setJsrq(qsrq);
			List<XsStatistics110> list110Pre = xsStatistics110Service.findList(xsStatistics110Pre);
			if(list110Pre.isEmpty()){
				msg=msg+"没有查询到110kV统计中与线路编号为"+lineCode+"相邻的上一个时间段的统计结果，请先统计上一个阶段的数据！</br>";
			}
			else{
				xsStatistics110Pre=((XsStatistics110)list110Pre.get(0));
				double yglj=xsStatistics110Pre.getYglj();
				double sclj=xsStatistics110Pre.getYglj();
				double sslj=xsStatistics110Pre.getYglj();
				double wglj=xsStatistics110Pre.getWglj();
				yglj=Arithmetic.add(yglj,zygdl);
				wglj=Arithmetic.add(wglj,zwgdl);
				sslj=Arithmetic.add(sslj,ssdl);
				sclj=Arithmetic.add(sclj,zxsdl);

				xsStatistics110.setYglj(yglj);
				xsStatistics110.setSclj(sclj);
				xsStatistics110.setSslj(sslj);
				xsStatistics110.setWglj(wglj);
				this.xsStatistics110Service.save(xsStatistics110);
			}

		}

		List<String> Listline110 = xsLineService.findListLineByDydj("110kV");
		model.addAttribute("Listline110", Listline110);
		Page<XsStatistics110> page = xsStatistics110Service.findPage(new Page<XsStatistics110>(request, response), xsStatistics110);
		model.addAttribute("page", page);
		return "llas/tjxx/xsStatistics110List";
	}
}