/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.web;

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
import com.yee.yide.llas.tjgx.entity.XsRelation10;
import com.yee.yide.llas.tjgx.service.XsRelation10Service;

/**
 * 10kV线损统计关系Controller
 * @author evay
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsRelation10")
public class XsRelation10Controller extends BaseController {

	@Autowired
	private XsRelation10Service xsRelation10Service;
	
	@ModelAttribute
	public XsRelation10 get(@RequestParam(required=false) String id) {
		XsRelation10 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsRelation10Service.get(id);
		}
		if (entity == null){
			entity = new XsRelation10();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsRelation10:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsRelation10 xsRelation10, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsRelation10> page = xsRelation10Service.findPage(new Page<XsRelation10>(request, response), xsRelation10); 
		model.addAttribute("page", page);
		return "llas/tjgx/xsRelation10List";
	}

	@RequiresPermissions("tjgx:xsRelation10:view")
	@RequestMapping(value = "form")
	public String form(XsRelation10 xsRelation10, Model model) {
		model.addAttribute("xsRelation10", xsRelation10);
		return "llas/tjgx/xsRelation10Form";
	}

	@RequiresPermissions("tjgx:xsRelation10:edit")
	@RequestMapping(value = "save")
	public String save(XsRelation10 xsRelation10, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsRelation10)){
			return form(xsRelation10, model);
		}
		xsRelation10Service.save(xsRelation10);
		addMessage(redirectAttributes, "保存10kV线损统计关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelation10/?repage";
	}
	
	@RequiresPermissions("tjgx:xsRelation10:edit")
	@RequestMapping(value = "delete")
	public String delete(XsRelation10 xsRelation10, RedirectAttributes redirectAttributes) {
		xsRelation10Service.delete(xsRelation10);
		addMessage(redirectAttributes, "删除10kV线损统计关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelation10/?repage";
	}

}