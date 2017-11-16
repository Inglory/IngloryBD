/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.tjgx.entity.XsRelation110;
import com.yee.yide.llas.tjgx.service.XsRelation110Service;
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
 * 110kV线路统计关系维护Controller
 * @author evay_leec
 * @version 2017-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsRelation110")
public class XsRelation110Controller extends BaseController {

	@Autowired
	private XsRelation110Service xsRelation110Service;
	@Autowired
	private XsLineService xsLineService;
	
	@ModelAttribute
	public XsRelation110 get(@RequestParam(required=false) String id) {
		XsRelation110 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsRelation110Service.get(id);
		}
		if (entity == null){
			entity = new XsRelation110();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsRelation110:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsRelation110 xsRelation110, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> Listline110 = xsLineService.findListLineByDydj("110kV");
		model.addAttribute("Listline110", Listline110);
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		Page<XsRelation110> page = xsRelation110Service.findPage(new Page<XsRelation110>(request, response), xsRelation110); 
		model.addAttribute("page", page);
		return "llas/tjgx/xsRelation110List";
	}

	@RequiresPermissions("tjgx:xsRelation110:view")
	@RequestMapping(value = "form")
	public String form(XsRelation110 xsRelation110, Model model) {
		List<String> Listline110 = xsLineService.findListLineByDydj("110kV");
		model.addAttribute("Listline110", Listline110);
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		model.addAttribute("xsRelation110", xsRelation110);
		return "llas/tjgx/xsRelation110Form";
	}

	@RequiresPermissions("tjgx:xsRelation110:edit")
	@RequestMapping(value = "save")
	public String save(XsRelation110 xsRelation110, Model model, RedirectAttributes redirectAttributes) {
		List<String> Listline110 = xsLineService.findListLineByDydj("110kV");
		model.addAttribute("Listline110", Listline110);
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		if (!beanValidator(model, xsRelation110)){
			return form(xsRelation110, model);
		}
		xsRelation110Service.save(xsRelation110);
		addMessage(redirectAttributes, "保存110kV线路统计关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelation110/?repage";
	}
	
	@RequiresPermissions("tjgx:xsRelation110:edit")
	@RequestMapping(value = "delete")
	public String delete(XsRelation110 xsRelation110, RedirectAttributes redirectAttributes) {
		xsRelation110Service.delete(xsRelation110);
		addMessage(redirectAttributes, "删除110kV线路统计关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelation110/?repage";
	}

}