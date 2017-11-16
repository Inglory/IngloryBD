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
import com.yee.yide.llas.jcxx.entity.XsTrsubstation;
import com.yee.yide.llas.jcxx.service.XsTrsubstationService;

/**
 * 变电站信息Controller
 * @author evay_leec
 * @version 2017-03-25
 */
@Controller
@RequestMapping(value = "${adminPath}/jcxx/xsTrsubstation")
public class XsTrsubstationController extends BaseController {

	@Autowired
	private XsTrsubstationService xsTrsubstationService;
	
	@ModelAttribute
	public XsTrsubstation get(@RequestParam(required=false) String id) {
		XsTrsubstation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsTrsubstationService.get(id);
		}
		if (entity == null){
			entity = new XsTrsubstation();
		}
		return entity;
	}
	
	@RequiresPermissions("jcxx:xsTrsubstation:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsTrsubstation xsTrsubstation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsTrsubstation> page = xsTrsubstationService.findPage(new Page<XsTrsubstation>(request, response), xsTrsubstation); 
		model.addAttribute("page", page);
		return "llas/jcxx/xsTrsubstationList";
	}

	@RequiresPermissions("jcxx:xsTrsubstation:view")
	@RequestMapping(value = "form")
	public String form(XsTrsubstation xsTrsubstation, Model model) {
		model.addAttribute("xsTrsubstation", xsTrsubstation);
		return "llas/jcxx/xsTrsubstationForm";
	}

	@RequiresPermissions("jcxx:xsTrsubstation:edit")
	@RequestMapping(value = "save")
	public String save(XsTrsubstation xsTrsubstation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsTrsubstation)){
			return form(xsTrsubstation, model);
		}
		xsTrsubstationService.save(xsTrsubstation);
		addMessage(redirectAttributes, "保存变电站信息成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsTrsubstation/?repage";
	}
	
	@RequiresPermissions("jcxx:xsTrsubstation:edit")
	@RequestMapping(value = "delete")
	public String delete(XsTrsubstation xsTrsubstation, RedirectAttributes redirectAttributes) {
		xsTrsubstationService.delete(xsTrsubstation);
		addMessage(redirectAttributes, "删除变电站信息成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsTrsubstation/?repage";
	}

}