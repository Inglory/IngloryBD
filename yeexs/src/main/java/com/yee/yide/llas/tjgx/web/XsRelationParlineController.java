/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.tjgx.entity.XsRelationParline;
import com.yee.yide.llas.tjgx.service.XsRelationParlineService;
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
 * 母线平衡关系Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsRelationParline")
public class XsRelationParlineController extends BaseController {

	@Autowired
	private XsRelationParlineService xsRelationParlineService;
	@Autowired
	private XsLineService xsLineService;
	
	@ModelAttribute
	public XsRelationParline get(@RequestParam(required=false) String id) {
		XsRelationParline entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsRelationParlineService.get(id);
		}
		if (entity == null){
			entity = new XsRelationParline();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsRelationParline:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsRelationParline xsRelationParline, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> ListMuxian = xsLineService.findALLListMuxian();
		model.addAttribute("ListMuxian", ListMuxian);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		Page<XsRelationParline> page = xsRelationParlineService.findPage(new Page<XsRelationParline>(request, response), xsRelationParline);
		model.addAttribute("page", page);
		return "llas/tjgx/xsRelationParlineList";
	}

	@RequiresPermissions("tjgx:xsRelationParline:view")
	@RequestMapping(value = "form")
	public String form(XsRelationParline xsRelationParline, Model model) {
		List<String> ListMuxian = xsLineService.findALLListMuxian();
		model.addAttribute("ListMuxian", ListMuxian);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		model.addAttribute("xsRelationParline", xsRelationParline);
		return "llas/tjgx/xsRelationParlineForm";
	}

	@RequiresPermissions("tjgx:xsRelationParline:edit")
	@RequestMapping(value = "save")
	public String save(XsRelationParline xsRelationParline, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsRelationParline)){
			return form(xsRelationParline, model);
		}
		List<String> ListMuxian = xsLineService.findALLListMuxian();
		model.addAttribute("ListMuxian", ListMuxian);
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		xsRelationParlineService.save(xsRelationParline);
		addMessage(redirectAttributes, "保存母线平衡关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelationParline/?repage";
	}
	
	@RequiresPermissions("tjgx:xsRelationParline:edit")
	@RequestMapping(value = "delete")
	public String delete(XsRelationParline xsRelationParline, RedirectAttributes redirectAttributes) {
		xsRelationParlineService.delete(xsRelationParline);
		addMessage(redirectAttributes, "删除母线平衡关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelationParline/?repage";
	}

}