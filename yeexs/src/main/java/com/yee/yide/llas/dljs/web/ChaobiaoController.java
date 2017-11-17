/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.utils.database.mysql.ImportDB;
import com.yee.yide.common.utils.excel.ImportExcel;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.dljs.entity.XsLinewastage;
import com.yee.yide.llas.dljs.service.XsLinewastageHisService;
import com.yee.yide.llas.dljs.service.XsLinewastageService;
import com.yee.yide.llas.jcxx.entity.DataimpMapping;
import com.yee.yide.llas.jcxx.entity.DataimpRule;
import com.yee.yide.llas.jcxx.entity.XsLine;
import com.yee.yide.llas.jcxx.entity.XsTrsubstation;
import com.yee.yide.llas.jcxx.service.DataimpRuleService;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.jcxx.service.XsTrsubstationService;
import com.yee.yide.llas.operator.entity.XsOperator;
import com.yee.yide.llas.operator.service.XsOperatorService;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
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

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 抄表Controller
 * 
 * @author evay
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/dljs/xsLine")
public class ChaobiaoController extends BaseController {

	@Autowired
	private XsLineService xsLineService;
	@Autowired
	private XsTrsubstationService xsTrsubstationService;
	@Autowired
	private DataimpRuleService dataimpRuleService;
	@Autowired
	private XsOperatorService xsOperatorService;
	@Autowired
	private XsLinewastageService xsLinewastageService;
	
	@ModelAttribute
	public XsLine get(@RequestParam(required = false) String id) {
		XsLine entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = xsLineService.get(id);
		}
		if (entity == null) {
			entity = new XsLine();
		}
		return entity;
	}

	@RequiresPermissions("dljs:xsLine:view")
	@RequestMapping(value = { "list", "" })
	public String list(XsLine xsLine, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		Page<XsLine> page = xsLineService.findPage(new Page<XsLine>(request, response), xsLine);
		model.addAttribute("page", page);
		return "llas/dljs/caobiaoList";
	}

	@RequiresPermissions("dljs:xsLine:view")
	@RequestMapping(value = "form")
	public String form(XsLine xsLine, Model model) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		model.addAttribute("xsLine", xsLine);
		return "llas/dljs/caobiaoForm";
	}

	@RequiresPermissions("dljs:xsLine:edit")
	@RequestMapping(value = "save")
	public String save(XsLine xsLine, Model model, RedirectAttributes redirectAttributes) {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		if (!beanValidator(model, xsLine)) {
			return form(xsLine, model);
		}
		xsLineService.save(xsLine);
		addMessage(redirectAttributes, "保存抄表成功");
		return "redirect:" + Global.getAdminPath() + "/dljs/xsLine/?repage";
	}

	@RequiresPermissions("dljs:xsLine:edit")
	@RequestMapping(value = "impData")
	public String impData(Model model, RedirectAttributes redirectAttributes)
			throws InvalidFormatException, IOException {
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		XsOperator xso = new XsOperator();
		xso.setJcbz("wtj");
		List<XsOperator> listXso = xsOperatorService.findList(xso);
		Date qsrq = listXso.get(0).getQsrq();
		Date jsrq = listXso.get(0).getJsrq();
		DataimpRule dataimpRule = dataimpRuleService.get("cd04a3c374c94091a451fec2073d193f");
		// 首先获取数据源，要分别处理接口、文件以及各种数据库作为源。
		String sourcePath = dataimpRule.getSource();
		List<DataimpMapping> list = dataimpRule.getDataimpMappingList();
		// 循环一行，组成一个xsLine 对象，插入先根据导入规则映射表找到excel
		// 字段和数据表字段的映射和源字段的列，依次组装成一个XsLine对象。然后保存。
		int rowNum = list.get(0).getExcelRow();// 表头字段所在的行
		ImportExcel ei = new ImportExcel(sourcePath, rowNum);
		for (int ie = ei.getDataRowNum(); ie < ei.getLastDataRowNum(); ie++) {
			XsLine xsLine = new XsLine();
			Row row = ei.getRow(ie); // 开始处理每一行
			for (int i = 0; i < list.size(); i++) {
				int columnNum = list.get(i).getExcelColumn(); // 源字段所在的列
				String destField = list.get(i).getDestField(); // 目标字段
				Object val = ei.getCellValue(row, columnNum);
				if ("" != val.toString() && null != val.toString()) {
					if (destField.equals("line_code")) {
						xsLine.setLineCode(val.toString());
					}
					if (destField.equals("line_name")) {
						xsLine.setLineName(val.toString());
					}
					if (destField.equals("intefactor")) {
						xsLine.setIntefactor(Double.valueOf(val.toString()));
					}
					if (destField.equals("fp_svalue")) {
						xsLine.setFpSvalue(Double.valueOf(val.toString()));
					}
					if (destField.equals("fp_evalue")) {
						xsLine.setFpEvalue(Double.valueOf(val.toString()));
					}
					if (destField.equals("trsubstation_id")) {
						if (null != xsTrsubstationService.getByCode(val.toString())) {
							XsTrsubstation trsubstation = xsTrsubstationService.getByCode(val.toString());
							xsLine.setTrsubstationId(trsubstation.getId());
						}
					}
					if (destField.equals("voltage")) {
						xsLine.setVoltage(val.toString());
					}
					xsLine.setQmdate(qsrq);
					xsLine.setZmdate(jsrq);
				}
			}
			if ("" != xsLine.getLineCode() && null != xsLine.getLineCode()) {
				xsLineService.save(xsLine);
			}
		}
		
		
		XsLinewastage xsLinewastage = new XsLinewastage();
		xsLinewastage.setQsrq(qsrq);
		xsLinewastage.setJsrq(jsrq);
		// 从xs_line表中取出所有非换表线路 插入到xs_linewastage中
		xsLinewastageService.deleteQsrqtoJsrq(xsLinewastage);
		xsLinewastageService.insertFromLine(xsLinewastage);
		addMessage(redirectAttributes, "导入表码数据完成");
		return "redirect:" + Global.getAdminPath() + "/dljs/xsLine/?repage";
	}

	@RequiresPermissions("dljs:xsLine:edit")
	@RequestMapping(value = "delete")
	public String delete(XsLine xsLine, RedirectAttributes redirectAttributes) {
		xsLineService.delete(xsLine);
		addMessage(redirectAttributes, "删除抄表成功");
		return "redirect:" + Global.getAdminPath() + "/dljs/xsLine/?repage";
	}

}