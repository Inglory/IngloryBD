package com.yee.yide.llas.dljs.web;

import java.util.Date;
import java.util.List;

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
import com.yee.yide.common.utils.Arithmetic;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.llas.dljs.entity.XsLinewastage;
import com.yee.yide.llas.dljs.service.XsLinewastageHisService;
import com.yee.yide.llas.dljs.service.XsLinewastageService;
import com.yee.yide.llas.jcxx.entity.XsLine;
import com.yee.yide.llas.jcxx.service.XsLineHisService;
import com.yee.yide.llas.jcxx.service.XsLineService;
import com.yee.yide.llas.operator.entity.XsOperator;
import com.yee.yide.llas.operator.service.XsOperatorService;

/**
 * 
 * @author evay_leec
 * @version 2017-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/llas/tjqjManage")
public class TjqjManageController extends BaseController {

	@Autowired
	private XsOperatorService xsOperatorService;
	@Autowired
	private XsLinewastageService xsLinewastageService;
	@Autowired
	private XsLineService xsLineService;
	@Autowired
	private XsLineHisService xsLineHisService;
	@Autowired
	private XsLinewastageHisService xsLinewastageHisService;

	@ModelAttribute
	public XsOperator get(@RequestParam(required = false) String id) {
		XsOperator entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = xsOperatorService.get(id);
		}
		if (entity == null) {
			entity = new XsOperator();
		}
		return entity;
	}

	/**
	 * 统计区间管理列表
	 * 
	 * @param xsOperator
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("llas:tjqjManage:view")
	@RequestMapping(value = { "list", "" })
	public String tjqjlist(XsOperator xsOperator, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<XsOperator> page = xsOperatorService.findPage(new Page<XsOperator>(request, response), xsOperator);
		model.addAttribute("page", page);
		return "llas/dljs/tjqjManageList";
	}

	@RequiresPermissions("llas:tjqjManage:edit")
	@RequestMapping(value = "form")
	public String form(XsOperator xsOperator, Model model) {
		model.addAttribute("xsOperator", xsOperator);
		return "llas/dljs/tjqjManageForm";
	}

	@RequiresPermissions("llas:tjqjManage:edit")
	@RequestMapping(value = "newtjqj")
	public String newTjqj(XsOperator xsOperator, Model model, RedirectAttributes redirectAttributes) {
		// 如果有未统计的区间，不能新建，可以删除未统计的区间
		XsOperator wtjOp = new XsOperator();
		wtjOp.setJcbz("wtj");
		List wtjList = xsOperatorService.findList(wtjOp);
		// 如果有未结存的区间，不能新建，必须首先结存数据
		XsOperator ytjOp = new XsOperator();
		ytjOp.setJcbz("ytj");
		List ytjList = xsOperatorService.findList(ytjOp);
		if (wtjList != null && !wtjList.isEmpty()) {
			addMessage(redirectAttributes, "存在未统计的区间，不能新建新的统计区间，您需要先完成统计和结存，或者删除未统计的区间！");
			return "redirect:" + Global.getAdminPath() + "/llas/tjqjManage/?repage&number=Math.random()";
		} else if (ytjList != null && !ytjList.isEmpty()) {
			addMessage(redirectAttributes, "存在已统计未结存的区间，不能新建新的统计区间，您必须首先将已统计的数据进行结存！");
			return "redirect:" + Global.getAdminPath() + "/llas/tjqjManage/?repage&number=Math.random()";
		} else {
			// 获取当前处理的时间段
			Date qsrq = xsOperator.getQsrq();
			Date jsrq = xsOperator.getJsrq();
			XsOperator newTjqj = new XsOperator();
			newTjqj.setSerialNumber(xsOperator.getSerialNumber() + 1);
			newTjqj.setLister(xsOperator.getLister());
			newTjqj.setPrincipal(xsOperator.getPrincipal());
			newTjqj.setDay(xsOperator.getDay());
			newTjqj.setHours(xsOperator.getHours());
			newTjqj.setYm(xsOperator.getYm());
			newTjqj.setQsrq(xsOperator.getJsrq());
			newTjqj.setJcbz("wtj");

			model.addAttribute("xsOperator", newTjqj);
			return "llas/dljs/tjqjManageNewTjqj";
		}
	}

	@RequiresPermissions("llas:tjqjManage:edit")
	@RequestMapping(value = "save")
	public String save(XsOperator xsOperator, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xsOperator)) {
			return form(xsOperator, model);
		}

		// 获取当前处理的时间段
		Date qsrq = xsOperator.getQsrq();
		Date jsrq = xsOperator.getJsrq();
		// 查询是否已经有同一时间段的统计区间
		List listOperator = xsOperatorService.findList(xsOperator);
		if (!listOperator.isEmpty() && listOperator != null) {
			addMessage(redirectAttributes, "已存在相同的统计区间，保存统计区间信息失败！");
			return "redirect:" + Global.getAdminPath() + "/llas/tjqjManage/?repage";
		} else {
			XsLinewastage xsLinewastage = new XsLinewastage();
			xsLinewastage.setQsrq(qsrq);
			xsLinewastage.setJsrq(jsrq);
			xsLinewastageService.deleteQsrqtoJsrq(xsLinewastage);
			// 从xs_line表中取出所有非换表线路 插入到xs_linewastage中
			xsLinewastageService.insertFromLine(xsLinewastage);
			xsOperatorService.save(xsOperator);
			// 更新xs_line 的起止码时间
			XsLine xsLine = new XsLine();
			xsLine.setQmdate(qsrq);
			xsLine.setZmdate(jsrq);
			xsLineService.updateAll(xsLine);
			addMessage(redirectAttributes, "保存统计区间信息成功");
			return "redirect:" + Global.getAdminPath() + "/llas/tjqjManage/?repage";
		}
	}

	@RequiresPermissions("llas:tjqjManage:edit")
	@RequestMapping(value = "delete")
	public String delete(XsOperator xsOperator, RedirectAttributes redirectAttributes) {
		// 获取当前处理的时间段
			Date qsrq = xsOperator.getQsrq();
			Date jsrq = xsOperator.getJsrq();
		if (xsOperator.getJcbz().equals("wtj")) {
			// 删除xs_linewastage的之前新建该统计区间段的插入数据
			XsLinewastage xsLinewastage = new XsLinewastage();
			xsLinewastage.setQsrq(qsrq);
			xsLinewastage.setJsrq(jsrq);
			xsLinewastageService.deleteQsrqtoJsrq(xsLinewastage);

			xsOperatorService.delete(xsOperator);
			addMessage(redirectAttributes, "删除统计区间信息成功");
		} else {
			addMessage(redirectAttributes, "该时间段已经进行了线损统计，不能删除！");
		}
		return "redirect:" + Global.getAdminPath() + "/llas/tjqjManage/?repage";
	}

	/**
	 * 历史数据处理，需要处理的数据和步骤包括： 1、结存，
	 * 
	 * @param xsLinewastage
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequiresPermissions("llas:tjqjManage:edit")
	@RequestMapping(value = "sjjc")
	public String sjjc(XsOperator xsOperator, Model model, RedirectAttributes redirectAttributes) {
		// 获取当前处理的时间段
		Date qsrq = xsOperator.getQsrq();
		Date jsrq = xsOperator.getJsrq();
		// 以下是将当前统计完成的数据放到历史表中，涉及到的表有：线路表（xs_line）,线路电量表（xs_linewastage）
		// 处理xs_line:直接将当前xs_line拷贝即可，将变电站的名称查询出来放进去
		xsLineHisService.insertHisData();
		// 处理xs_linewastage：
		xsLinewastageHisService.insertHisData();
		// 处理Xs_operator
		xsOperator.setJcbz("yjc");
		xsOperatorService.save(xsOperator);
		addMessage(redirectAttributes, "本统计区间数据结存成功");
		return "redirect:" + Global.getAdminPath() + "/llas/tjqjManage/?repage";
	}

	/**
	 * 计算抄见电量与合计电量
	 *
	 * @author Leecheng 日期：2008-9-4 下午01:22:26
	 */
	public void quaCal() {
		// 首先计算正向抄见电量，再次计算防止取数过来的不准确 等
		double fp_svalue = 0, fp_evalue = 0, fp_excerpqua = 0, fp_totalqua = 0, fp_subjoinqua = 0, fp_chmter = 0,
				intefactor = 0, maxcapacity = 0;
		double fup_svalue = 0, fup_evalue = 0, fup_excerpqua = 0, fup_totalqua = 0, fup_subjoinqua = 0, fup_chmter = 0;

		double rp_svalue = 0, rp_evalue = 0, rp_excerpqua = 0, rp_totalqua = 0, rp_subjoinqua = 0, rp_chmter = 0;
		double rup_svalue = 0, rup_evalue = 0, rup_excerpqua = 0, rup_totalqua = 0, rup_subjoinqua = 0, rup_chmter = 0;

		List<XsLine> list = xsLineService.findList(new XsLine());
		for (XsLine xsLine : list) {
			fp_svalue = xsLine.getFpSvalue();
			fp_evalue = xsLine.getFpEvalue();
			fp_subjoinqua = xsLine.getFpSubjoinqua();
			fp_chmter = xsLine.getFpChmeter();

			fup_svalue = xsLine.getFupSvalue();
			fup_evalue = xsLine.getFupEvalue();
			fup_subjoinqua = xsLine.getFupSubjoinqua();
			fup_chmter = xsLine.getFupChmeter();

			rp_svalue = xsLine.getRpSvalue();
			rp_evalue = xsLine.getRpEvalue();
			rp_subjoinqua = xsLine.getRpSubjoinqua();
			rp_chmter = xsLine.getRpChmeter();

			rup_svalue = xsLine.getRupSvalue();
			rup_evalue = xsLine.getRupEvalue();
			rup_subjoinqua = xsLine.getRupSubjoinqua();
			rup_chmter = xsLine.getRupChmeter();

			intefactor = xsLine.getIntefactor();
			maxcapacity = xsLine.getMaxcapacity();
			// 下面计算正向电量
			if (fp_evalue >= fp_svalue) {
				fp_excerpqua = Arithmetic.mul(Arithmetic.sub(fp_evalue, fp_svalue), intefactor); // 抄见电量
				// =（
				// 止码-起码
				// ）*
				// 倍率
			} else {
				double tempSum = Arithmetic.add(Arithmetic.add(fp_evalue, maxcapacity), 1.0);
				double tempSub = Arithmetic.sub(tempSum, fp_svalue);
				fp_excerpqua = Arithmetic.mul(tempSub, intefactor);
			}
			fp_totalqua = Arithmetic.add(Arithmetic.add(fp_excerpqua, fp_subjoinqua), fp_chmter);

			if (fup_evalue >= fup_svalue) {
				fup_excerpqua = Arithmetic.mul(Arithmetic.sub(fup_evalue, fup_svalue), intefactor); // 抄见电量
				// =（
				// 止码-起码
				// ）*
				// 倍率
			} else {
				double tempSum = Arithmetic.add(Arithmetic.add(fup_evalue, maxcapacity), 1.0);
				double tempSub = Arithmetic.sub(tempSum, fup_svalue);
				fup_excerpqua = Arithmetic.mul(tempSub, intefactor);
			}
			fup_totalqua = Arithmetic.add(Arithmetic.add(fup_excerpqua, fup_subjoinqua), fup_chmter);

			// 下面计算反向电量
			if (rp_evalue >= rp_svalue) {
				rp_excerpqua = Arithmetic.mul(Arithmetic.sub(rp_evalue, rp_svalue), intefactor); // 抄见电量
				// =（
				// 止码-起码
				// ）*
				// 倍率
			} else {
				double tempSum = Arithmetic.add(Arithmetic.add(rp_evalue, maxcapacity), 1.0);
				double tempSub = Arithmetic.sub(tempSum, rp_svalue);
				rp_excerpqua = Arithmetic.mul(tempSub, intefactor);
			}
			rp_totalqua = Arithmetic.add(Arithmetic.add(rp_excerpqua, rp_subjoinqua), rp_chmter);

			if (rup_evalue >= rup_svalue) {
				rup_excerpqua = Arithmetic.mul(Arithmetic.sub(rup_evalue, rup_svalue), intefactor); // 抄见电量
				// =（
				// 止码-起码
				// ）*
				// 倍率
			} else {
				double tempSum = Arithmetic.add(Arithmetic.add(rup_evalue, maxcapacity), 1.0);
				double tempSub = Arithmetic.sub(tempSum, rup_svalue);
				rup_excerpqua = Arithmetic.mul(tempSub, intefactor);
			}
			rup_totalqua = Arithmetic.add(Arithmetic.add(rup_excerpqua, rup_subjoinqua), rup_chmter);
			// 计算有功合计和武功合计
			double p_total = 0, up_total = 0;
			p_total = Arithmetic.sub(fp_totalqua, rp_totalqua);
			up_total = Arithmetic.sub(fup_totalqua, rup_totalqua);
			// 更新到数据库
			xsLine.setFpTotalqua(fp_totalqua);
			xsLine.setFupTotalqua(fup_totalqua);
			xsLine.setRpTotalqua(rp_totalqua);
			xsLine.setRupTotalqua(rup_totalqua);
			xsLine.setpTotalqua(p_total);
			xsLine.setUpTotalqua(up_total);

			xsLine.setFpExcerpqua(fp_excerpqua);
			xsLine.setFupExcerpqua(fup_excerpqua);
			xsLine.setRpExcerpqua(rp_excerpqua);
			xsLine.setRupExcerpqua(rup_excerpqua);

			xsLineService.save(xsLine);
		}
	}

	/**
	 * 将xs_line中的合计电量取给到xs_linewastage中
	 *
	 * @author Leecheng 日期：2008-9-2 上午06:40:37
	 */
	public void getTotalQua() {
		/*
		 * b. 以xs_linewastage为标准， c. 母线的输出电量： 等于该母线所有出线的有功电量之和
		 */
		double powerqua = 0, unpowerqua = 0, qua1 = 0, qua2 = 0;

		List<XsLinewastage> list = xsLinewastageService.findList(new XsLinewastage());// 查询出所有记录
		for (XsLinewastage xsLinewastage : list) {
			String lineId = xsLinewastage.getLineId();
			XsLine xsLine = xsLineService.get(lineId);
			qua1 = xsLine.getpTotalqua();// 有功合计赋值给qua1
			qua2 = xsLine.getUpTotalqua(); // 无功合计赋值给qua2
			// 更新到数据库
			xsLinewastage.setQua1(qua1);
			xsLinewastage.setQua2(qua2);
			xsLinewastageService.save(xsLinewastage);
		}

	}
}
