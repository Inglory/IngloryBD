/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.entity.XsBdzzb;
import com.yee.yide.llas.jcxx.service.XsBdzzbService;
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
 * 变电站主变信息Controller
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/jcxx/xsBdzzb")
public class XsBdzzbController extends BaseController {

	@Autowired
	private XsBdzzbService xsBdzzbService;
	@Autowired
	private XsTrsubstationService xsTrsubstationService;

	@ModelAttribute
	public XsBdzzb get(@RequestParam(required=false) String id) {
		XsBdzzb entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsBdzzbService.get(id);
		}
		if (entity == null){
			entity = new XsBdzzb();
		}
		return entity;
	}
	
	@RequiresPermissions("jcxx:xsBdzzb:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsBdzzb xsBdzzb, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		Page<XsBdzzb> page = xsBdzzbService.findPage(new Page<XsBdzzb>(request, response), xsBdzzb); 
		model.addAttribute("page", page);
		return "llas/jcxx/xsBdzzbList";
	}

	@RequiresPermissions("jcxx:xsBdzzb:view")
	@RequestMapping(value = "form")
	public String form(XsBdzzb xsBdzzb, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		model.addAttribute("xsBdzzb", xsBdzzb);
		return "llas/jcxx/xsBdzzbForm";
	}

	@RequiresPermissions("jcxx:xsBdzzb:edit")
	@RequestMapping(value = "save")
	public String save(XsBdzzb xsBdzzb, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsBdzzb)){
			return form(xsBdzzb, model);
		}
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		xsBdzzbService.save(xsBdzzb);
		addMessage(redirectAttributes, "保存变电站主变信息成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsBdzzb/?repage";
	}
	
	@RequiresPermissions("jcxx:xsBdzzb:edit")
	@RequestMapping(value = "delete")
	public String delete(XsBdzzb xsBdzzb, RedirectAttributes redirectAttributes) {
		xsBdzzbService.delete(xsBdzzb);
		addMessage(redirectAttributes, "删除变电站主变信息成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsBdzzb/?repage";
	}

}