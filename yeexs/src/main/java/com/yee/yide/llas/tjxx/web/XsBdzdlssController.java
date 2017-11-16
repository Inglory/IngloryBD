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
import com.yee.yide.llas.jcxx.entity.XsTrsubstation;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.jcxx.service.XsTrsubstationService;
import com.yee.yide.llas.tjgx.entity.XsGxBdzdl;
import com.yee.yide.llas.tjgx.service.XsGxBdzdlService;
import com.yee.yide.llas.tjxx.entity.XsBdzdlss;
import com.yee.yide.llas.tjxx.service.XsBdzdlssService;
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
 * 变电站电量损失Controller
 * @author evay
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/tjxx/xsBdzdlss")
public class XsBdzdlssController extends BaseController {

	@Autowired
	private XsBdzdlssService xsBdzdlssService;
	@Autowired
	private XsTrsubstationService xsTrsubstationService;
	@Autowired
	private XsGxBdzdlService xsGxBdzdlService;
	@Autowired
	private XsLineService xsLineService;

	@ModelAttribute
	public XsBdzdlss get(@RequestParam(required=false) String id) {
		XsBdzdlss entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsBdzdlssService.get(id);
		}
		if (entity == null){
			entity = new XsBdzdlss();
		}
		return entity;
	}
	
	@RequiresPermissions("tjxx:xsBdzdlss:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsBdzdlss xsBdzdlss, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		Page<XsBdzdlss> page = xsBdzdlssService.findPage(new Page<XsBdzdlss>(request, response), xsBdzdlss); 
		model.addAttribute("page", page);
		return "llas/tjxx/xsBdzdlssList";
	}

	@RequiresPermissions("tjxx:xsBdzdlss:view")
	@RequestMapping(value = "form")
	public String form(XsBdzdlss xsBdzdlss, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		model.addAttribute("xsBdzdlss", xsBdzdlss);
		return "llas/tjxx/xsBdzdlssForm";
	}

	@RequiresPermissions("tjxx:xsBdzdlss:edit")
	@RequestMapping(value = "save")
	public String save(XsBdzdlss xsBdzdlss, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsBdzdlss)){
			return form(xsBdzdlss, model);
		}
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		xsBdzdlssService.save(xsBdzdlss);
		addMessage(redirectAttributes, "保存变电站电量损失成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsBdzdlss/?repage";
	}
	
	@RequiresPermissions("tjxx:xsBdzdlss:edit")
	@RequestMapping(value = "delete")
	public String delete(XsBdzdlss xsBdzdlss, RedirectAttributes redirectAttributes) {
		xsBdzdlssService.delete(xsBdzdlss);
		addMessage(redirectAttributes, "删除变电站电量损失成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsBdzdlss/?repage";
	}

	@RequiresPermissions("tjxx:xsBdzdlss:view")
	@RequestMapping(value = {"tongji"})
	public String tongji(Date qsrq, Date jsrq,  HttpServletRequest request, HttpServletResponse response, Model model) {


		double bysr = 0, bysc = 0, byssdl = 0, byssl = 0;
		double ljsr = 0, ljsc = 0, ljssdl = 0, ljssl = 0 ,ljwg= 0 ,ljglys = 0;
		double jsdl = 0;  //参与计算的电量
		double wgdl = 0 , zwgdl = 0;   //无功电量
		double bsdl = 0;   //变损电量
		String s_item = "";
		int i_item = 0;

		//首先删除变电站主变损失统计表当月数据
		XsBdzdlss xsBdzdlss = new XsBdzdlss();
		xsBdzdlss.setJsrq(jsrq);
		xsBdzdlss.setQsrq(qsrq);
		xsBdzdlssService.deleteQsrqToJsrq(xsBdzdlss);
		//电量统计
	  	  /*
	  	   * S1：从XsTrsubstation表中取出当前所有变电站编号；循环处理所有变电站；
	  	   * S2:对每一个变电站，用编号去查找XsGxBdzdl表的该站所有关系，依次处理；
	  	   */
		List<XsTrsubstation> l_bdz = xsTrsubstationService.findList(new XsTrsubstation());
		for(XsTrsubstation bdz:l_bdz){   //循环处理每一个变电站
			bysr = 0; bysc = 0; byssdl = 0; byssl = 0;zwgdl = 0;wgdl = 0;
			ljsr = 0; ljsc = 0; ljssdl = 0; ljssl = 0;ljwg = 0;ljglys = 0;

			String bdzid = bdz.getId();

			XsGxBdzdl xsGxBdzdl = new XsGxBdzdl();
			xsGxBdzdl.setTjdx(bdzid);
			List<XsGxBdzdl> l_zbgx = xsGxBdzdlService.findList(xsGxBdzdl);
			for(XsGxBdzdl xsgxzb:l_zbgx){
					String jldid = xsgxzb.getJld();   //首先查找到该条关系的计量点
					s_item=xsgxzb.getJsdl();
					XsLine jld = xsLineService.get(jldid);
					if(xsLineService.get(jldid)== null||xsLineService.get(jldid).equals("")){

					}
					else{
						if(s_item.equals("ygdl")) {
							jsdl = jld.getpTotalqua();
						}
						if(s_item.equals("wgdl")) {
							wgdl = jld.getUpTotalqua();
						}
						if(s_item.equals("xsdl")) {
							jsdl = jld.getpTotalqua();
						}
						if(s_item.equals("bsdl")) {
							jsdl = jld.getpTotalqua();
						}
						}
					if(xsgxzb.getTjdllx().equals("srdl")){
						if(xsgxzb.getJsfs().equals("add")){  //计算关系是加
							bysr = bysr + jsdl;
							zwgdl = zwgdl + wgdl;
						}
						if(xsgxzb.getJsfs().equals("sub")){ //计算关系是减
							bysr = bysr - jsdl;
							zwgdl = zwgdl - wgdl;
						}
					}
					if(xsgxzb.getTjdllx().equals("scdl")){
						if(xsgxzb.getJsfs().equals("add")){  //计算关系是加
							bysc = bysc + jsdl;
						}
						if(xsgxzb.getJsfs().equals("sub")){ //计算关系是减
							bysc = bysc - jsdl;
						}
					}
				}
			byssdl = bysr - bysc;
			//ToolBean.system("线损统计之变电站电量损失1：byssdl="+byssdl+"bysr="+bysr);
			if(bysr == 0){
				//ToolBean.system("线损统计之变电站电量损失2：byssdl="+byssdl+"bysr="+bysr);
				byssl = 0;
			}
			else{
				//ToolBean.system("线损统计之变电站电量损失3：byssdl="+byssdl+"bysr="+bysr);
				byssl = Arithmetic.round(byssdl/bysr*100,2);
				//ToolBean.system("线损统计之变电站电量损失4：byssdl="+byssdl+"bysr="+bysr+" byssl="+byssl);
			}
			double glys = 0;
			if(bysr*bysr+zwgdl*zwgdl ==0){
				glys = 0;
			}
			else{
				glys=Arithmetic.div(bysr, (Math.sqrt(bysr * bysr+zwgdl * zwgdl)),2);
			}
			XsBdzdlss xsBdzdlssPre = new XsBdzdlss();
			xsBdzdlssPre.setJsrq(qsrq);
			xsBdzdlssPre = xsBdzdlssService.get(xsBdzdlssPre);
			double yglj=xsBdzdlssPre.getLjsr();
			double sclj=xsBdzdlssPre.getLjsc();
			double sslj=xsBdzdlssPre.getLjss();
			double wglj=xsBdzdlssPre.getLjwg();
			yglj=Arithmetic.add(yglj,bysr);
			wglj=Arithmetic.add(wglj,wgdl);
			sslj=Arithmetic.add(sslj,byssdl);
			sclj=Arithmetic.add(sclj,bysc);


			//往xs_bdzzbss插入一条记录
			XsBdzdlss xsBdzdlssSave = new XsBdzdlss();
			xsBdzdlssSave.setBdzid(bdz.getId());
			xsBdzdlssSave.setBdzbh(bdz.getTrsubstationCode());
			xsBdzdlssSave.setBdzmc(bdz.getName());
			xsBdzdlssSave.setYm(qsrq.toString().substring(0,7));
			xsBdzdlssSave.setBysr(bysr);
			xsBdzdlssSave.setBysc(bysc);
			xsBdzdlssSave.setByss(byssdl);
			xsBdzdlssSave.setByssl(Arithmetic.round(byssl,2));
			xsBdzdlssSave.setWgdl(wgdl);
			xsBdzdlssSave.setGlys(Arithmetic.round(glys,2));
			xsBdzdlssSave.setQsrq(qsrq);
			xsBdzdlssSave.setJsrq(jsrq);
			xsBdzdlssSave.setLjsr(yglj);
			xsBdzdlssSave.setLjsc(sclj);
			xsBdzdlssSave.setLjss(sslj);
			xsBdzdlssSave.setLjwg(wglj);

			xsBdzdlssService.save(xsBdzdlssSave);
			}
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		Page<XsBdzdlss> page = xsBdzdlssService.findPage(new Page<XsBdzdlss>(request, response), xsBdzdlss);
		model.addAttribute("page", page);
		return "llas/tjxx/xsBdzdlssList";
	}
}