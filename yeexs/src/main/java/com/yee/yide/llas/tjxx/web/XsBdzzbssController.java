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
import com.yee.yide.llas.tjxx.entity.XsBdzzbss;
import com.yee.yide.llas.tjxx.service.XsBdzzbssService;

/**
 * 主变电量损失统计Controller
 * @author evay
 * @version 2017-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/tjxx/xsBdzzbss")
public class XsBdzzbssController extends BaseController {

	@Autowired
	private XsBdzzbssService xsBdzzbssService;
	
	@ModelAttribute
	public XsBdzzbss get(@RequestParam(required=false) String id) {
		XsBdzzbss entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsBdzzbssService.get(id);
		}
		if (entity == null){
			entity = new XsBdzzbss();
		}
		return entity;
	}
	
	@RequiresPermissions("tjxx:xsBdzzbss:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsBdzzbss xsBdzzbss, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsBdzzbss> page = xsBdzzbssService.findPage(new Page<XsBdzzbss>(request, response), xsBdzzbss); 
		model.addAttribute("page", page);
		return "llas/tjxx/xsBdzzbssList";
	}

	@RequiresPermissions("tjxx:xsBdzzbss:view")
	@RequestMapping(value = "form")
	public String form(XsBdzzbss xsBdzzbss, Model model) {
		model.addAttribute("xsBdzzbss", xsBdzzbss);
		return "llas/tjxx/xsBdzzbssForm";
	}

	@RequiresPermissions("tjxx:xsBdzzbss:edit")
	@RequestMapping(value = "save")
	public String save(XsBdzzbss xsBdzzbss, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsBdzzbss)){
			return form(xsBdzzbss, model);
		}
		xsBdzzbssService.save(xsBdzzbss);
		addMessage(redirectAttributes, "保存主变电量损失统计成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsBdzzbss/?repage";
	}
	
	@RequiresPermissions("tjxx:xsBdzzbss:edit")
	@RequestMapping(value = "delete")
	public String delete(XsBdzzbss xsBdzzbss, RedirectAttributes redirectAttributes) {
		xsBdzzbssService.delete(xsBdzzbss);
		addMessage(redirectAttributes, "删除主变电量损失统计成功");
		return "redirect:"+Global.getAdminPath()+"/tjxx/xsBdzzbss/?repage";
	}

}