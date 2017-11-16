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
import com.yee.yide.llas.tjxx.entity.XsStatisticsGds;
import com.yee.yide.llas.tjxx.service.XsStatisticsGdsService;

/**
 * 供电所电量损失统计Controller
 * @author evay
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/tjxx/xsStatisticsGds")
public class XsStatisticsGdsController extends BaseController {

	@Autowired
	private XsStatisticsGdsService xsStatisticsGdsService;
	
	@ModelAttribute
	public XsStatisticsGds get(@RequestParam(required=false) String id) {
		XsStatisticsGds entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsStatisticsGdsService.get(id);
		}
		if (entity == null){
			entity = new XsStatisticsGds();
		}
		return entity;
	}
	
	@RequiresPermissions("tjxx:xsStatisticsGds:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsStatisticsGds xsStatisticsGds, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsStatisticsGds> page = xsStatisticsGdsService.findPage(new Page<XsStatisticsGds>(request, response), xsStatisticsGds); 
		model.addAttribute("page", page);
		return "llas/tjxx/xsStatisticsGdsList";
	}

	@RequiresPermissions("tjxx:xsStatisticsGds:view")
	@RequestMapping(value = "form")
	public String form(XsStatisticsGds xsStatisticsGds, Model model) {
		model.addAttribute("xsStatisticsGds", xsStatisticsGds);
		return "llas/tjxx/xsStatisticsGdsForm";
	}

	@RequiresPermissions("tjxx:xsStatisticsGds:edit")
	@RequestMapping(value = "save")
	public String save(XsStatisticsGds xsStatisticsGds, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsStatisticsGds)){
			return form(xsStatisticsGds, model);
		}
		xsStatisticsGdsService.save(xsStatisticsGds);
		addMessage(redirectAttributes, "保存供电所电量损失统计成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatisticsGds/?repage";
	}
	
	@RequiresPermissions("tjxx:xsStatisticsGds:edit")
	@RequestMapping(value = "delete")
	public String delete(XsStatisticsGds xsStatisticsGds, RedirectAttributes redirectAttributes) {
		xsStatisticsGdsService.delete(xsStatisticsGds);
		addMessage(redirectAttributes, "删除供电所电量损失统计成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatisticsGds/?repage";
	}

}