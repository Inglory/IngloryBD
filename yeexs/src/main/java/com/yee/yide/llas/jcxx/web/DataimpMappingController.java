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
import com.yee.yide.llas.jcxx.entity.DataimpMapping;
import com.yee.yide.llas.jcxx.service.DataimpMappingService;

/**
 * 数据导入规则Controller
 * @author evay_leec
 * @version 2017-11-10
 */
@Controller
@RequestMapping(value = "${adminPath}/jcxx/dataimpMapping")
public class DataimpMappingController extends BaseController {

	@Autowired
	private DataimpMappingService dataimpMappingService;
	
	@ModelAttribute
	public DataimpMapping get(@RequestParam(required=false) String id) {
		DataimpMapping entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataimpMappingService.get(id);
		}
		if (entity == null){
			entity = new DataimpMapping();
		}
		return entity;
	}
	
	@RequiresPermissions("jcxx:dataimpMapping:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataimpMapping dataimpMapping, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataimpMapping> page = dataimpMappingService.findPage(new Page<DataimpMapping>(request, response), dataimpMapping); 
		model.addAttribute("page", page);
		return "llas/jcxx/dataimpMappingList";
	}

	@RequiresPermissions("jcxx:dataimpMapping:view")
	@RequestMapping(value = "form")
	public String form(DataimpMapping dataimpMapping, Model model) {
		model.addAttribute("dataimpMapping", dataimpMapping);
		return "llas/jcxx/dataimpMappingForm";
	}

	@RequiresPermissions("jcxx:dataimpMapping:edit")
	@RequestMapping(value = "save")
	public String save(DataimpMapping dataimpMapping, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataimpMapping)){
			return form(dataimpMapping, model);
		}
		dataimpMappingService.save(dataimpMapping);
		addMessage(redirectAttributes, "保存数据导入规则成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/dataimpMapping/?repage";
	}
	
	@RequiresPermissions("jcxx:dataimpMapping:edit")
	@RequestMapping(value = "delete")
	public String delete(DataimpMapping dataimpMapping, RedirectAttributes redirectAttributes) {
		dataimpMappingService.delete(dataimpMapping);
		addMessage(redirectAttributes, "删除数据导入规则成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/dataimpMapping/?repage";
	}

}