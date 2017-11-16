/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.operator.web;

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
import com.yee.yide.llas.operator.entity.XsOperator;
import com.yee.yide.llas.operator.service.XsOperatorService;

/**
 * 制表人员、用电小时、统计区间设置与管理Controller
 * @author evay_leec
 * @version 2017-10-20
 */
@Controller
@RequestMapping(value = "${adminPath}/operator/xsOperator")
public class XsOperatorController extends BaseController {

	@Autowired
	private XsOperatorService xsOperatorService;
	
	@ModelAttribute
	public XsOperator get(@RequestParam(required=false) String id) {
		XsOperator entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsOperatorService.get(id);
		}
		if (entity == null){
			entity = new XsOperator();
		}
		return entity;
	}
	
	@RequiresPermissions("operator:xsOperator:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsOperator xsOperator, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsOperator> page = xsOperatorService.findPage(new Page<XsOperator>(request, response), xsOperator); 
		model.addAttribute("page", page);
		return "llas/operator/xsOperatorList";
	}

	@RequiresPermissions("operator:xsOperator:view")
	@RequestMapping(value = "form")
	public String form(XsOperator xsOperator, Model model) {
		model.addAttribute("xsOperator", xsOperator);
		return "llas/operator/xsOperatorForm";
	}

	@RequiresPermissions("operator:xsOperator:edit")
	@RequestMapping(value = "save")
	public String save(XsOperator xsOperator, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsOperator)){
			return form(xsOperator, model);
		}
		xsOperatorService.save(xsOperator);
		addMessage(redirectAttributes, "保存制表人员信息成功");
		return "redirect:"+Global.getAdminPath()+"/operator/xsOperator/?repage";
	}
	
	@RequiresPermissions("operator:xsOperator:edit")
	@RequestMapping(value = "delete")
	public String delete(XsOperator xsOperator, RedirectAttributes redirectAttributes) {
		xsOperatorService.delete(xsOperator);
		addMessage(redirectAttributes, "删除制表人员信息成功");
		return "redirect:"+Global.getAdminPath()+"/operator/xsOperator/?repage";
	}

}