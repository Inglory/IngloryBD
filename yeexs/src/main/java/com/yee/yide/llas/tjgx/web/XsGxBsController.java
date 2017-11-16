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
import com.yee.yide.llas.tjgx.entity.XsGxBs;
import com.yee.yide.llas.tjgx.service.XsGxBsService;
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
 * 变电站损失关系Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsGxBs")
public class XsGxBsController extends BaseController {

	@Autowired
	private XsGxBsService xsGxBsService;
	@Autowired
	private XsTrsubstationService xsTrsubstationService;
	@Autowired
	private XsLineService xsLineService;
	
	@ModelAttribute
	public XsGxBs get(@RequestParam(required=false) String id) {
		XsGxBs entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsGxBsService.get(id);
		}
		if (entity == null){
			entity = new XsGxBs();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsGxBs:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsGxBs xsGxBs, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		Page<XsGxBs> page = xsGxBsService.findPage(new Page<XsGxBs>(request, response), xsGxBs); 
		model.addAttribute("page", page);
		return "llas/tjgx/xsGxBsList";
	}

	@RequiresPermissions("tjgx:xsGxBs:view")
	@RequestMapping(value = "form")
	public String form(XsGxBs xsGxBs, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		model.addAttribute("xsGxBs", xsGxBs);
		return "llas/tjgx/xsGxBsForm";
	}

	@RequiresPermissions("tjgx:xsGxBs:edit")
	@RequestMapping(value = "save")
	public String save(XsGxBs xsGxBs, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsGxBs)){
			return form(xsGxBs, model);
		}
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		xsGxBsService.save(xsGxBs);
		addMessage(redirectAttributes, "保存变电站损失关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsGxBs/?repage";
	}
	
	@RequiresPermissions("tjgx:xsGxBs:edit")
	@RequestMapping(value = "delete")
	public String delete(XsGxBs xsGxBs, RedirectAttributes redirectAttributes) {
		xsGxBsService.delete(xsGxBs);
		addMessage(redirectAttributes, "删除变电站损失关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsGxBs/?repage";
	}

}