/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.Arithmetic;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.dljs.entity.XsLinewastage;
import com.yee.yide.llas.dljs.service.XsLinewastageService;
import com.yee.yide.llas.jcxx.entity.XsLine;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.operator.entity.XsOperator;
import com.yee.yide.llas.operator.service.XsOperatorService;
import com.yee.yide.llas.tjgx.service.*;
import com.yee.yide.llas.tjxx.service.XsStatistics35Service;
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
import java.util.Date;
import java.util.List;

/**
 * 电量计算Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/dljs/powerquamanage")
public class PowerQuaManageController extends BaseController {

	@Autowired
	private XsLinewastageService xsLinewastageService;
	@Autowired
	private XsOperatorService xsOperatorService;
	@Autowired
	private XsLineService xsLineService;
	@Autowired
	private XsStatistics35Service xsStatistics35Service;
	@Autowired
	private XsRelation35Service xsRelation35Service;
	@Autowired
	private XsRelation110Service xsRelation110Service;
	@Autowired
	private XsRelationAllService xsRelationAllService;
	@Autowired
	private XsRelationParlineService xsRelationParlineService;
	@Autowired
	private XsGxZbService xsGxZbService;

	@ModelAttribute
	public XsLinewastage get(@RequestParam(required=false) String id) {
		XsLinewastage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsLinewastageService.get(id);
		}
		if (entity == null){
			entity = new XsLinewastage();
		}
		return entity;
	}
	
	@RequiresPermissions("dljs:powerquamanage:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsLinewastage xsLinewastage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsLinewastage> page = xsLinewastageService.findPage(new Page<XsLinewastage>(request, response), xsLinewastage); 
		model.addAttribute("page", page);
		return "llas/dljs/powerquamanage";
	}
	
	@RequiresPermissions("dljs:powerquamanage:edit")
	@RequestMapping(value = "save")
	public String save(XsLinewastage xsLinewastage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsLinewastage)){
			return form(xsLinewastage, model);
		}
		xsLinewastageService.save(xsLinewastage);
		addMessage(redirectAttributes, "保存电量计算成功");
		return "redirect:"+Global.getAdminPath()+"/dljs/powerquamanage/?repage";
	}
	@RequiresPermissions("dljs:powerquamanage:view")
	@RequestMapping(value = "form")
	public String form(XsLinewastage xsLinewastage, Model model) {
		model.addAttribute("xsLinewastage", xsLinewastage);
		return "llas/dljs/xsLinewastageForm";
	}
}