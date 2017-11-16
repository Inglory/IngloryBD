/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.web;

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
import com.yee.yide.llas.tjxx.entity.XsStatistics10;
import com.yee.yide.llas.tjxx.service.XsStatistics10Service;

/**
 * 10kV线损统计结果Controller
 * @author evay_leec
 * @version 2017-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/tjxx/xsStatistics10")
public class XsStatistics10Controller extends BaseController {

	@Autowired
	private XsStatistics10Service xsStatistics10Service;
	
	@ModelAttribute
	public XsStatistics10 get(@RequestParam(required=false) String id) {
		XsStatistics10 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsStatistics10Service.get(id);
		}
		if (entity == null){
			entity = new XsStatistics10();
		}
		return entity;
	}
	
	@RequiresPermissions("tjxx:xsStatistics10:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsStatistics10 xsStatistics10, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsStatistics10> page = xsStatistics10Service.findPage(new Page<XsStatistics10>(request, response), xsStatistics10); 
		model.addAttribute("page", page);
		return "llas/tjxx/xsStatistics10List";
	}

	@RequiresPermissions("tjxx:xsStatistics10:view")
	@RequestMapping(value = "form")
	public String form(XsStatistics10 xsStatistics10, Model model) {
		model.addAttribute("xsStatistics10", xsStatistics10);
		return "llas/tjxx/xsStatistics10Form";
	}

	@RequiresPermissions("tjxx:xsStatistics10:edit")
	@RequestMapping(value = "save")
	public String save(XsStatistics10 xsStatistics10, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsStatistics10)){
			return form(xsStatistics10, model);
		}
		xsStatistics10Service.save(xsStatistics10);
		addMessage(redirectAttributes, "保存10kV线损统计结果成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatistics10/?repage";
	}
	
	@RequiresPermissions("tjxx:xsStatistics10:edit")
	@RequestMapping(value = "delete")
	public String delete(XsStatistics10 xsStatistics10, RedirectAttributes redirectAttributes) {
		xsStatistics10Service.delete(xsStatistics10);
		addMessage(redirectAttributes, "删除10kV线损统计结果成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatistics10/?repage";
	}

}