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
import com.yee.yide.llas.jcxx.entity.DataimpRule;
import com.yee.yide.llas.jcxx.service.DataimpRuleService;

/**
 * 数据导入规则Controller
 * @author evay_leec
 * @version 2017-11-13
 */
@Controller
@RequestMapping(value = "${adminPath}/jcxx/dataimpRule")
public class DataimpRuleController extends BaseController {

	@Autowired
	private DataimpRuleService dataimpRuleService;
	
	@ModelAttribute
	public DataimpRule get(@RequestParam(required=false) String id) {
		DataimpRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataimpRuleService.get(id);
		}
		if (entity == null){
			entity = new DataimpRule();
		}
		return entity;
	}
	
	@RequiresPermissions("jcxx:dataimpRule:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataimpRule dataimpRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataimpRule> page = dataimpRuleService.findPage(new Page<DataimpRule>(request, response), dataimpRule); 
		model.addAttribute("page", page);
		return "llas/jcxx/dataimpRuleList";
	}

	@RequiresPermissions("jcxx:dataimpRule:view")
	@RequestMapping(value = "form")
	public String form(DataimpRule dataimpRule, Model model) {
		model.addAttribute("dataimpRule", dataimpRule);
		return "llas/jcxx/dataimpRuleForm";
	}

	@RequiresPermissions("jcxx:dataimpRule:edit")
	@RequestMapping(value = "save")
	public String save(DataimpRule dataimpRule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataimpRule)){
			return form(dataimpRule, model);
		}
		dataimpRuleService.save(dataimpRule);
		addMessage(redirectAttributes, "保存数据导入规则成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/dataimpRule/?repage";
	}
	
	@RequiresPermissions("jcxx:dataimpRule:edit")
	@RequestMapping(value = "delete")
	public String delete(DataimpRule dataimpRule, RedirectAttributes redirectAttributes) {
		dataimpRuleService.delete(dataimpRule);
		addMessage(redirectAttributes, "删除数据导入规则成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/dataimpRule/?repage";
	}

}