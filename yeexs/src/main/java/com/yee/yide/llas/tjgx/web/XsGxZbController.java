/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.service.XsBdzzbService;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.tjgx.entity.XsGxZb;
import com.yee.yide.llas.tjgx.service.XsGxZbService;
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
 * 主变关系Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsGxZb")
public class XsGxZbController extends BaseController {

	@Autowired
	private XsGxZbService xsGxZbService;

	@Autowired
	private XsBdzzbService xsBdzzbService;
	@Autowired
	private XsLineService xsLineService;
	@ModelAttribute
	public XsGxZb get(@RequestParam(required=false) String id) {
		XsGxZb entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsGxZbService.get(id);
		}
		if (entity == null){
			entity = new XsGxZb();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsGxZb:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsGxZb xsGxZb, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> zbList = xsBdzzbService.findAllListDropdown();
		model.addAttribute("zbList", zbList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		Page<XsGxZb> page = xsGxZbService.findPage(new Page<XsGxZb>(request, response), xsGxZb); 
		model.addAttribute("page", page);
		return "llas/tjgx/xsGxZbList";
	}

	@RequiresPermissions("tjgx:xsGxZb:view")
	@RequestMapping(value = "form")
	public String form(XsGxZb xsGxZb, Model model) {
		List<String> zbList = xsBdzzbService.findAllListDropdown();
		model.addAttribute("zbList", zbList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		model.addAttribute("xsGxZb", xsGxZb);
		return "llas/tjgx/xsGxZbForm";
	}

	@RequiresPermissions("tjgx:xsGxZb:edit")
	@RequestMapping(value = "save")
	public String save(XsGxZb xsGxZb, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsGxZb)){
			return form(xsGxZb, model);
		}
		List<String> zbList = xsBdzzbService.findAllListDropdown();
		model.addAttribute("zbList", zbList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		xsGxZbService.save(xsGxZb);
		addMessage(redirectAttributes, "保存主变关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsGxZb/?repage";
	}
	
	@RequiresPermissions("tjgx:xsGxZb:edit")
	@RequestMapping(value = "delete")
	public String delete(XsGxZb xsGxZb, RedirectAttributes redirectAttributes) {
		xsGxZbService.delete(xsGxZb);
		addMessage(redirectAttributes, "删除主变关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsGxZb/?repage";
	}

}