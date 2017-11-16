/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.tjgx.entity.XsRelation35;
import com.yee.yide.llas.tjgx.service.XsRelation35Service;
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
 * 35kV线路统计关系Controller
 * @author evay
 * @version 2017-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsRelation35")
public class XsRelation35Controller extends BaseController {

	@Autowired
	private XsRelation35Service xsRelation35Service;
	@Autowired
	private XsLineService xsLineService;
	@ModelAttribute
	public XsRelation35 get(@RequestParam(required=false) String id) {
		XsRelation35 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsRelation35Service.get(id);
		}
		if (entity == null){
			entity = new XsRelation35();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsRelation35:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsRelation35 xsRelation35, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> Listline610 = xsLineService.findListLineByDydj("6-10kV");
		model.addAttribute("Listline610", Listline610);
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		Page<XsRelation35> page = xsRelation35Service.findPage(new Page<XsRelation35>(request, response), xsRelation35);
		model.addAttribute("page", page);
		return "llas/tjgx/xsRelation35List";
	}

	@RequiresPermissions("tjgx:xsRelation35:view")
	@RequestMapping(value = "form")
	public String form(XsRelation35 xsRelation35, Model model) {
		List<String> Listline610 = xsLineService.findListLineByDydj("6-10kV");
		model.addAttribute("Listline610", Listline610);
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		model.addAttribute("xsRelation35", xsRelation35);
		return "llas/tjgx/xsRelation35Form";
	}

	@RequiresPermissions("tjgx:xsRelation35:edit")
	@RequestMapping(value = "save")
	public String save(XsRelation35 xsRelation35, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsRelation35)){
			return form(xsRelation35, model);
		}
		List<String> Listline610 = xsLineService.findListLineByDydj("6-10kV");
		model.addAttribute("Listline610", Listline610);
		List<String> Listline35 = xsLineService.findListLineByDydj("35kV");
		model.addAttribute("Listline35", Listline35);
		xsRelation35Service.save(xsRelation35);
		addMessage(redirectAttributes, "保存35kV线路统计关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelation35/?repage";
	}
	
	@RequiresPermissions("tjgx:xsRelation35:edit")
	@RequestMapping(value = "delete")
	public String delete(XsRelation35 xsRelation35, RedirectAttributes redirectAttributes) {
		xsRelation35Service.delete(xsRelation35);
		addMessage(redirectAttributes, "删除35kV线路统计关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelation35/?repage";
	}

}