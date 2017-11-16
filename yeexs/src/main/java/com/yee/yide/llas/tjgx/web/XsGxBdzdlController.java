/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.jcxx.service.XsTrsubstationService;
import com.yee.yide.llas.tjgx.entity.XsGxBdzdl;
import com.yee.yide.llas.tjgx.service.XsGxBdzdlService;
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
 * 变电站电量关系Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsGxBdzdl")
public class XsGxBdzdlController extends BaseController {

	@Autowired
	private XsGxBdzdlService xsGxBdzdlService;
	@Autowired
	private XsTrsubstationService xsTrsubstationService;
	@Autowired
	private XsLineService xsLineService;

	
	@ModelAttribute
	public XsGxBdzdl get(@RequestParam(required=false) String id) {
		XsGxBdzdl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsGxBdzdlService.get(id);
		}
		if (entity == null){
			entity = new XsGxBdzdl();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsGxBdzdl:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsGxBdzdl xsGxBdzdl, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		Page<XsGxBdzdl> page = xsGxBdzdlService.findPage(new Page<XsGxBdzdl>(request, response), xsGxBdzdl);
		model.addAttribute("page", page);
		return "llas/tjgx/xsGxBdzdlList";
	}

	@RequiresPermissions("tjgx:xsGxBdzdl:view")
	@RequestMapping(value = "form")
	public String form(XsGxBdzdl xsGxBdzdl, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		model.addAttribute("xsGxBdzdl", xsGxBdzdl);
		return "llas/tjgx/xsGxBdzdlForm";
	}

	@RequiresPermissions("tjgx:xsGxBdzdl:edit")
	@RequestMapping(value = "save")
	public String save(XsGxBdzdl xsGxBdzdl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsGxBdzdl)){
			return form(xsGxBdzdl, model);
		}
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		xsGxBdzdlService.save(xsGxBdzdl);
		addMessage(redirectAttributes, "保存变电站电量关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsGxBdzdl/?repage";
	}
	
	@RequiresPermissions("tjgx:xsGxBdzdl:edit")
	@RequestMapping(value = "delete")
	public String delete(XsGxBdzdl xsGxBdzdl, RedirectAttributes redirectAttributes) {
		xsGxBdzdlService.delete(xsGxBdzdl);
		addMessage(redirectAttributes, "删除变电站电量关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsGxBdzdl/?repage";
	}

}