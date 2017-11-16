/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.entity.XsLine;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.jcxx.service.XsTrsubstationService;
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
 * 线路信息Controller
 * @author evay_leec
 * @version 2017-04-13
 */
@Controller
@RequestMapping(value = "${adminPath}/jcxx/xsLine")
public class XsLineController extends BaseController {

	@Autowired
	private XsLineService xsLineService;
	@Autowired
	private XsTrsubstationService xsTrsubstationService;


	@ModelAttribute
	public XsLine get(@RequestParam(required=false) String id) {
		XsLine entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsLineService.get(id);
		}
		if (entity == null){
			entity = new XsLine();
		}
		return entity;
	}
	
	@RequiresPermissions("jcxx:xsLine:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsLine xsLine, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		Page<XsLine> page = xsLineService.findPage(new Page<XsLine>(request, response), xsLine); 
		model.addAttribute("page", page);
		return "llas/jcxx/xsLineList";
	}

	@RequiresPermissions("jcxx:xsLine:view")
	@RequestMapping(value = "form")
	public String form(XsLine xsLine, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		model.addAttribute("xsLine", xsLine);
		return "llas/jcxx/xsLineForm";
	}

	@RequiresPermissions("jcxx:xsLine:edit")
	@RequestMapping(value = "save")
	public String save(XsLine xsLine, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsLine)){
			return form(xsLine, model);
		}
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		xsLineService.save(xsLine);
		addMessage(redirectAttributes, "保存线路成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsLine/?repage";
	}
	
	@RequiresPermissions("jcxx:xsLine:edit")
	@RequestMapping(value = "delete")
	public String delete(XsLine xsLine, RedirectAttributes redirectAttributes) {
		xsLineService.delete(xsLine);
		addMessage(redirectAttributes, "删除线路成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsLine/?repage";
	}

}