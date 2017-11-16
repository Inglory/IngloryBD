/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.tjgx.entity.XsRelationAll;
import com.yee.yide.llas.tjgx.service.XsRelationAllService;
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
 * 全网综合统计关系Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/tjgx/xsRelationAll")
public class XsRelationAllController extends BaseController {

	@Autowired
	private XsRelationAllService xsRelationAllService;
	@Autowired
	private XsLineService xsLineService;
	
	@ModelAttribute
	public XsRelationAll get(@RequestParam(required=false) String id) {
		XsRelationAll entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsRelationAllService.get(id);
		}
		if (entity == null){
			entity = new XsRelationAll();
		}
		return entity;
	}
	
	@RequiresPermissions("tjgx:xsRelationAll:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsRelationAll xsRelationAll, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		Page<XsRelationAll> page = xsRelationAllService.findPage(new Page<XsRelationAll>(request, response), xsRelationAll);
		model.addAttribute("page", page);
		return "llas/tjgx/xsRelationAllList";
	}

	@RequiresPermissions("tjgx:xsRelationAll:view")
	@RequestMapping(value = "form")
	public String form(XsRelationAll xsRelationAll, Model model) {
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		model.addAttribute("xsRelationAll", xsRelationAll);
		return "llas/tjgx/xsRelationAllForm";
	}

	@RequiresPermissions("tjgx:xsRelationAll:edit")
	@RequestMapping(value = "save")
	public String save(XsRelationAll xsRelationAll, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsRelationAll)){
			return form(xsRelationAll, model);
		}
		List<String> ListlineAll = xsLineService.findALLListLineByDydj();
		model.addAttribute("ListlineAll", ListlineAll);
		xsRelationAllService.save(xsRelationAll);
		addMessage(redirectAttributes, "保存全网综合统计关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelationAll/?repage";
	}
	
	@RequiresPermissions("tjgx:xsRelationAll:edit")
	@RequestMapping(value = "delete")
	public String delete(XsRelationAll xsRelationAll, RedirectAttributes redirectAttributes) {
		xsRelationAllService.delete(xsRelationAll);
		addMessage(redirectAttributes, "删除全网综合统计关系成功");
		return "redirect:"+Global.getAdminPath()+"/tjgx/xsRelationAll/?repage";
	}

}