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
import com.yee.yide.llas.tjxx.entity.XsStatisticsBdz;
import com.yee.yide.llas.tjxx.service.XsStatisticsBdzService;

/**
 * 变电站损失信息Controller
 * @author evay_leec
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/tjxx/xsStatisticsBdz")
public class XsStatisticsBdzController extends BaseController {

	@Autowired
	private XsStatisticsBdzService xsStatisticsBdzService;
	
	@ModelAttribute
	public XsStatisticsBdz get(@RequestParam(required=false) String id) {
		XsStatisticsBdz entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsStatisticsBdzService.get(id);
		}
		if (entity == null){
			entity = new XsStatisticsBdz();
		}
		return entity;
	}
	
	@RequiresPermissions("tjxx:xsStatisticsBdz:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsStatisticsBdz xsStatisticsBdz, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsStatisticsBdz> page = xsStatisticsBdzService.findPage(new Page<XsStatisticsBdz>(request, response), xsStatisticsBdz); 
		model.addAttribute("page", page);
		return "llas/tjxx/xsStatisticsBdzList";
	}

	@RequiresPermissions("tjxx:xsStatisticsBdz:view")
	@RequestMapping(value = "form")
	public String form(XsStatisticsBdz xsStatisticsBdz, Model model) {
		model.addAttribute("xsStatisticsBdz", xsStatisticsBdz);
		return "llas/tjxx/xsStatisticsBdzForm";
	}

	@RequiresPermissions("tjxx:xsStatisticsBdz:edit")
	@RequestMapping(value = "save")
	public String save(XsStatisticsBdz xsStatisticsBdz, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsStatisticsBdz)){
			return form(xsStatisticsBdz, model);
		}
		xsStatisticsBdzService.save(xsStatisticsBdz);
		addMessage(redirectAttributes, "保存变电站损失信息成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatisticsBdz/?repage";
	}
	
	@RequiresPermissions("tjxx:xsStatisticsBdz:edit")
	@RequestMapping(value = "delete")
	public String delete(XsStatisticsBdz xsStatisticsBdz, RedirectAttributes redirectAttributes) {
		xsStatisticsBdzService.delete(xsStatisticsBdz);
		addMessage(redirectAttributes, "删除变电站损失信息成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsStatisticsBdz/?repage";
	}

}