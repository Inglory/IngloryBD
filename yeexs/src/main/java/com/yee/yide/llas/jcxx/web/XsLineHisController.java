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
import com.yee.yide.llas.jcxx.entity.XsLineHis;
import com.yee.yide.llas.jcxx.service.XsLineHisService;

/**
 * 历史线路信息查询Controller
 * @author evay_leec
 * @version 2017-10-29
 */
@Controller
@RequestMapping(value = "${adminPath}/jcxx/xsLineHis")
public class XsLineHisController extends BaseController {

	@Autowired
	private XsLineHisService xsLineHisService;
	
	@ModelAttribute
	public XsLineHis get(@RequestParam(required=false) String id) {
		XsLineHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xsLineHisService.get(id);
		}
		if (entity == null){
			entity = new XsLineHis();
		}
		return entity;
	}
	
	@RequiresPermissions("jcxx:xsLineHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsLineHis xsLineHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XsLineHis> page = xsLineHisService.findPage(new Page<XsLineHis>(request, response), xsLineHis); 
		model.addAttribute("page", page);
		return "llas/jcxx/xsLineHisList";
	}

	@RequiresPermissions("jcxx:xsLineHis:view")
	@RequestMapping(value = "form")
	public String form(XsLineHis xsLineHis, Model model) {
		model.addAttribute("xsLineHis", xsLineHis);
		return "llas/jcxx/xsLineHisForm";
	}

	@RequiresPermissions("jcxx:xsLineHis:edit")
	@RequestMapping(value = "save")
	public String save(XsLineHis xsLineHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsLineHis)){
			return form(xsLineHis, model);
		}
		xsLineHisService.save(xsLineHis);
		addMessage(redirectAttributes, "保存历史线路信息成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsLineHis/?repage";
	}
	
	@RequiresPermissions("jcxx:xsLineHis:edit")
	@RequestMapping(value = "delete")
	public String delete(XsLineHis xsLineHis, RedirectAttributes redirectAttributes) {
		xsLineHisService.delete(xsLineHis);
		addMessage(redirectAttributes, "删除历史线路信息成功");
		return "redirect:"+Global.getAdminPath()+"/jcxx/xsLineHis/?repage";
	}

}