/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.service.XsGdsService;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.tjgx.entity.XsRelationGds;
import com.yee.yide.llas.tjgx.service.XsRelationGdsService;
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
import java.util.List;

/**
 * 供电所损失关系Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsRelationGds")
public class XsRelationGdsController extends BaseController {

	@Autowired
	private XsRelationGdsService xsRelationGdsService;
	@Autowired
	private XsGdsService xsGdsService;
	@Autowired
	private XsLineService xsLineService;
	
	@ModelAttribute
	public XsRelationGds get(@RequestParam(required=false) String id) {
		XsRelationGds entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsRelationGdsService.get(id);
		}
		if (entity == null){
			entity = new XsRelationGds();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsRelationGds:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsRelationGds xsRelationGds, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> gdsList = xsGdsService.findAllListDropdown();
		model.addAttribute("gdsList", gdsList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		Page<XsRelationGds> page = xsRelationGdsService.findPage(new Page<XsRelationGds>(request, response), xsRelationGds);
		model.addAttribute("page", page);
		return "llas/tjgx/xsRelationGdsList";
	}

	@RequiresPermissions("tjgx:xsRelationGds:view")
	@RequestMapping(value = "form")
	public String form(XsRelationGds xsRelationGds, Model model) {
		List<String> gdsList = xsGdsService.findAllListDropdown();
		model.addAttribute("gdsList", gdsList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		model.addAttribute("xsRelationGds", xsRelationGds);
		return "llas/tjgx/xsRelationGdsForm";
	}

	@RequiresPermissions("tjgx:xsRelationGds:edit")
	@RequestMapping(value = "save")
	public String save(XsRelationGds xsRelationGds, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsRelationGds)){
			return form(xsRelationGds, model);
		}
		List<String> gdsList = xsGdsService.findAllListDropdown();
		model.addAttribute("gdsList", gdsList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		xsRelationGdsService.save(xsRelationGds);
		addMessage(redirectAttributes, "保存供电所损失关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelationGds/?repage";
	}
	
	@RequiresPermissions("tjgx:xsRelationGds:edit")
	@RequestMapping(value = "delete")
	public String delete(XsRelationGds xsRelationGds, RedirectAttributes redirectAttributes) {
		xsRelationGdsService.delete(xsRelationGds);
		addMessage(redirectAttributes, "删除供电所损失关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelationGds/?repage";
	}

}