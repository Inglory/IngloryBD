/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.llas.dljs.entity.XsLinewastageHis;
import com.yee.yide.llas.dljs.service.XsLinewastageHisService;

/**
 * 历史线损计算电量Controller
 * @author evay_leec
 * @version 2017-10-29
 */
@Controller
@RequestMapping(value = "${adminPath}/dljs/xsLinewastageHis")
public class XsLinewastageHisController extends BaseController {

	@Autowired
	private XsLinewastageHisService xsLinewastageHisService;
	
	@ModelAttribute
	public XsLinewastageHis get(@RequestParam(required=false) String id) {
		XsLinewastageHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsLinewastageHisService.get(id);
		}
		if (entity == null){
			entity = new XsLinewastageHis();
		}
		return entity;
	}
	
	@RequiresPermissions("dljs:xsLinewastageHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsLinewastageHis xsLinewastageHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsLinewastageHis> page = xsLinewastageHisService.findPage(new Page<XsLinewastageHis>(request, response), xsLinewastageHis); 
		model.addAttribute("page", page);
		return "llas/dljs/xsLinewastageHisList";
	}

	@RequiresPermissions("dljs:xsLinewastageHis:view")
	@RequestMapping(value = "form")
	public String form(XsLinewastageHis xsLinewastageHis, Model model) {
		model.addAttribute("xsLinewastageHis", xsLinewastageHis);
		return "llas/dljs/xsLinewastageHisForm";
	}

	@RequiresPermissions("dljs:xsLinewastageHis:edit")
	@RequestMapping(value = "save")
	public String save(XsLinewastageHis xsLinewastageHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsLinewastageHis)){
			return form(xsLinewastageHis, model);
		}
		xsLinewastageHisService.save(xsLinewastageHis);
		addMessage(redirectAttributes, "保存历史线损计算电量成功");
		return "redirect:"+Global.getAdminPath()+"/dljs/xsLinewastageHis/?repage";
	}
	
	@RequiresPermissions("dljs:xsLinewastageHis:edit")
	@RequestMapping(value = "delete")
	public String delete(XsLinewastageHis xsLinewastageHis, RedirectAttributes redirectAttributes) {
		xsLinewastageHisService.delete(xsLinewastageHis);
		addMessage(redirectAttributes, "删除历史线损计算电量成功");
		return "redirect:"+Global.getAdminPath()+"/dljs/xsLinewastageHis/?repage";
	}

}