/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.web;

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
import com.yee.yide.llas.jcxx.entity.XsGds;
import com.yee.yide.llas.jcxx.service.XsGdsService;

/**
 * 供电所基本信息Controller
 * @author evay_leec
 * @version 2017-03-25
 */
@Controller
@RequestMapping(value = "${adminPath}/jcxx/xsGds")
public class XsGdsController extends BaseController {

	@Autowired
	private XsGdsService xsGdsService;
	
	@ModelAttribute
	public XsGds get(@RequestParam(required=false) String id) {
		XsGds entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsGdsService.get(id);
		}
		if (entity == null){
			entity = new XsGds();
		}
		return entity;
	}
	
	@RequiresPermissions("jcxx:xsGds:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsGds xsGds, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsGds> page = xsGdsService.findPage(new Page<XsGds>(request, response), xsGds); 
		model.addAttribute("page", page);
		return "llas/jcxx/xsGdsList";
	}

	@RequiresPermissions("jcxx:xsGds:view")
	@RequestMapping(value = "form")
	public String form(XsGds xsGds, Model model) {
		model.addAttribute("xsGds", xsGds);
		return "llas/jcxx/xsGdsForm";
	}

	@RequiresPermissions("jcxx:xsGds:edit")
	@RequestMapping(value = "save")
	public String save(XsGds xsGds, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsGds)){
			return form(xsGds, model);
		}
		xsGdsService.save(xsGds);
		addMessage(redirectAttributes, "保存供电所信息成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsGds/?repage";
	}
	
	@RequiresPermissions("jcxx:xsGds:edit")
	@RequestMapping(value = "delete")
	public String delete(XsGds xsGds, RedirectAttributes redirectAttributes) {
		xsGdsService.delete(xsGds);
		addMessage(redirectAttributes, "删除供电所信息成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsGds/?repage";
	}

}