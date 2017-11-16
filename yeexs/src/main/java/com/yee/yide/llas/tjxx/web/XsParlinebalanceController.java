/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.web;

import com.yee.yide.common.config.Global;
import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.utils.Arithmetic;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.llas.jcxx.entity.XsLine;
import com.yee.yide.llas.jcxx.entity.XsTrsubstation;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.jcxx.service.XsTrsubstationService;
import com.yee.yide.llas.tjgx.entity.XsRelationParline;
import com.yee.yide.llas.tjgx.service.XsRelationParlineService;
import com.yee.yide.llas.tjxx.entity.XsParlinebalance;
import com.yee.yide.llas.tjxx.service.XsParlinebalanceService;
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
import java.util.Date;
import java.util.List;

/**
 * 母线平衡统计Controller
 * @author evay
 * @version 2017-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/tjxx/xsParlinebalance")
public class XsParlinebalanceController extends BaseController {

	@Autowired
	private XsParlinebalanceService xsParlinebalanceService;
	@Autowired
	private XsLineService xsLineService;
	@Autowired
	private XsTrsubstationService xsTrsubstationService;
	@Autowired
	private XsRelationParlineService xsRelationParlineService;

	@ModelAttribute
	public XsParlinebalance get(@RequestParam(required = false) String id) {
		XsParlinebalance entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = xsParlinebalanceService.get(id);
		}
		if (entity == null) {
			entity = new XsParlinebalance();
		}
		return entity;
	}

	@RequiresPermissions("tjxx:xsParlinebalance:view")
	@RequestMapping(value = {"list", ""})
	public String list(XsParlinebalance xsParlinebalance, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> ListMuxian = xsLineService.findALLListMuxian();
		model.addAttribute("ListMuxian", ListMuxian);
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		Page<XsParlinebalance> page = xsParlinebalanceService.findPage(new Page<XsParlinebalance>(request, response), xsParlinebalance);
		model.addAttribute("page", page);
		return "llas/tjxx/xsParlinebalanceList";
	}

	@RequiresPermissions("tjxx:xsParlinebalance:view")
	@RequestMapping(value = "form")
	public String form(XsParlinebalance xsParlinebalance, Model model) {
		List<String> ListMuxian = xsLineService.findALLListMuxian();
		model.addAttribute("ListMuxian", ListMuxian);
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		model.addAttribute("xsParlinebalance", xsParlinebalance);
		return "llas/tjxx/xsParlinebalanceForm";
	}

	@RequiresPermissions("tjxx:xsParlinebalance:edit")
	@RequestMapping(value = "save")
	public String save(XsParlinebalance xsParlinebalance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsParlinebalance)) {
			return form(xsParlinebalance, model);
		}
		List<String> ListMuxian = xsLineService.findALLListMuxian();
		model.addAttribute("ListMuxian", ListMuxian);
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		xsParlinebalanceService.save(xsParlinebalance);
		addMessage(redirectAttributes, "保存母线平衡统计成功");
		return "redirect:" + Global.getAdminPath() + "/tjxx/xsParlinebalance/?repage";
	}

	@RequiresPermissions("tjxx:xsParlinebalance:edit")
	@RequestMapping(value = "delete")
	public String delete(XsParlinebalance xsParlinebalance, RedirectAttributes redirectAttributes) {
		xsParlinebalanceService.delete(xsParlinebalance);
		addMessage(redirectAttributes, "删除母线平衡统计成功");
		return "redirect:" + Global.getAdminPath() + "/tjxx/xsParlinebalance/?repage";
	}


	@RequiresPermissions("tjxx:xsParlinebalance:view")
	@RequestMapping(value = {"tongji"})
	public String tongji(Date qsrq, Date jsrq, HttpServletRequest request, HttpServletResponse response, Model model) {
// step1:删除当前数据
		XsParlinebalance xsParlinebalance = new XsParlinebalance();
		xsParlinebalance.setQsrq(qsrq);
		xsParlinebalance.setJsrq(jsrq);
		xsParlinebalanceService.deleteQsrqToJsrq(xsParlinebalance);
		// step2:按变电站进行统计每一个变电站下的所有线路按照关系循环累计。
		List<XsTrsubstation> listBdz = xsTrsubstationService.findList(new XsTrsubstation());
		for (XsTrsubstation mx : listBdz) {
			double zsrdl = 0, zscdl = 0;
			String mxid = mx.getId();
			String voltage = mx.getVoltargrade();
			XsRelationParline xsRelationParline = new XsRelationParline();
			xsRelationParline.setParlineId(mxid);
			List<XsRelationParline> listMxgx = xsRelationParlineService.findList(xsRelationParline);
			for (XsRelationParline mxgx : listMxgx) {
				String lineId = mxgx.getLineId();
				String calkind = mxgx.getCalKind();
				String quakind = mxgx.getQuaKind();
				XsLine xsLine = xsLineService.get(lineId);

				if (null != xsLine) {
					if ("srdl".equals(quakind)) { // 输入电量
						double inqua = xsLine.getpTotalqua();
						if ("add".equals(calkind)) { // 加
							zsrdl = Arithmetic.add(zsrdl, inqua);
						}
						if ("sub".equals(calkind)) {
							zsrdl = Arithmetic.sub(zsrdl, inqua);
						}
					}
					if ("scdl".equals(quakind)) { // 输出电量
						double outqua = xsLine.getpTotalqua();
						if ("add".equals(calkind)) { // 加
							zscdl = Arithmetic.add(zscdl, outqua);
						}
						if ("sub".equals(calkind)) {
							zscdl = Arithmetic.sub(zscdl, outqua);
						}

					}

				}
			}
			double ssdl = Arithmetic.sub(zsrdl, zscdl); // 不平衡电量
			double phl; // 平衡率
			if (zsrdl == 0) {
				phl = 0;
			} else {
				phl = Arithmetic.div(Arithmetic.mul(
						Arithmetic.div(ssdl, zsrdl), 100), 1, 2);
			}

			// 插入数据到数据库
			XsParlinebalance mxphSave = new XsParlinebalance();
			mxphSave.setYm(qsrq.toString().substring(0, 7));
			mxphSave.setVoltage(voltage);
			mxphSave.setLineId(mxid);
			mxphSave.setPowerQua(zsrdl);
			mxphSave.setSaleQua(zscdl);
			mxphSave.setWasteQua(ssdl);
			mxphSave.setNonbalRate(Arithmetic.round(phl, 2));
			mxphSave.setNonbalQua(ssdl);
			mxphSave.setTrsubstationId(mxid);
			xsParlinebalanceService.save(mxphSave);

			XsParlinebalance mxphPre = new XsParlinebalance();
			mxphPre.setJsrq(qsrq);
			mxphPre.setLineId(mxid);
			xsParlinebalanceService.get(mxphPre);
			XsParlinebalance xsParlinebalancePre = new XsParlinebalance();

			double yglj = xsParlinebalancePre.getYglj();
			double sclj = xsParlinebalancePre.getYglj();
			double sslj = xsParlinebalancePre.getYglj();
			double wglj = xsParlinebalancePre.getWglj();
			yglj = Arithmetic.add(yglj, zsrdl);
			sslj = Arithmetic.add(sslj, ssdl);
			sclj = Arithmetic.add(sclj, zscdl);

			mxphSave.setYglj(yglj);
			mxphSave.setSclj(sclj);
			mxphSave.setSslj(sslj);
			mxphSave.setWglj(wglj);
			xsParlinebalanceService.save(mxphSave);
		}

		List<String> ListMuxian = xsLineService.findALLListMuxian();
		model.addAttribute("ListMuxian", ListMuxian);
		List<String> bdzList = xsTrsubstationService.findBdzList();
		model.addAttribute("bdzList", bdzList);
		Page<XsParlinebalance> page = xsParlinebalanceService.findPage(new Page<XsParlinebalance>(request, response), xsParlinebalance);
		model.addAttribute("page", page);
		return "llas/tjxx/xsParlinebalanceList";
	}

}