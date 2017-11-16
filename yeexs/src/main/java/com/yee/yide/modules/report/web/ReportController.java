package com.yee.yide.modules.report.web;
/*
 * @author 李亮亮
 * 报表预览和导出的入口
 */

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yee.yide.common.utils.SystemPath;
import com.yee.yide.common.web.BaseController;
import com.yee.yide.modules.report.entity.Bdzydtjb;
import com.yee.yide.modules.report.entity.GdsDyxstjb;
import com.yee.yide.modules.report.entity.Ggdszydqk;
import com.yee.yide.modules.report.entity.Lsdybysfzsdltjb;
import com.yee.yide.modules.report.entity.Sldskvcjyyssltjbtb;
import com.yee.yide.modules.report.entity.Xsfykhb;
import com.yee.yide.modules.report.entity.Xsfzkhb;
import com.yee.yide.modules.report.service.GgdszydqkService;
import com.yee.yide.modules.report.service.LsdybysfzxdltjbService;
import com.yee.yide.modules.report.service.SldskvcjyyssltjbtbService;
import com.yee.yide.modules.report.service.XsfzkhbService;
@Controller
@RequestMapping(value = "${adminPath}/report")
public class ReportController extends BaseController {
	//jasper文件路径
	private String baseFilePath=File.separatorChar+SystemPath.getSysPath()+File.separatorChar+"reportJasperFile"+File.separatorChar;
	private String jasperFilePath="";
	@Autowired
	private SldskvcjyyssltjbtbService sldskvcjyyssltjbtbService;
	@Autowired
	private LsdybysfzxdltjbService lsdybysfzxdltjbService;
	@Autowired
	private XsfzkhbService xsfzkhbService;
	@Autowired
	private GgdszydqkService ggdszydqkService;
	//工具类预览报表
	public void previewReportUtil(String filePath,HttpServletResponse response,JRDataSource dataSource) throws IOException{
		File reportFile = new File(filePath);
		response.setCharacterEncoding("UTF-8"); 
   	    JasperReport jasperReport=null;
   	    JasperPrint  jasperPrint=null;
	try {
		jasperReport = (JasperReport)JRLoader.loadObject(reportFile);
		jasperPrint = JasperFillManager.fillReport(jasperReport, null,dataSource);
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER,response.getWriter());
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "utf-8");
		exporter.exportReport();
	} catch (JRException e) {
		e.printStackTrace();
	}	
	}
	//10kV线路、0.4kV台区抄见、营业损失率统计表(同步）报表预览
	@RequestMapping(value="/preview/sldskvcjyyssltjbtb")
	public void previewReportForsldskvcjyyssltjbtb(HttpServletResponse response){
		jasperFilePath=baseFilePath+"sldskvcjyyssltjbtb.jasper";
		List<Sldskvcjyyssltjbtb> list = sldskvcjyyssltjbtbService.getSldskvcjyyssltjbtbData();
		JRDataSource dataSource = new JRBeanCollectionDataSource(list);
		try {
			this.previewReportUtil(jasperFilePath, response, dataSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//6kV-110kV专线电量统计表预览
	@RequestMapping(value="/preview/lsdybysfzxdltjb")
	public void previewReportForLsdybysfzsdltjb(HttpServletResponse response){
		jasperFilePath=baseFilePath+"lsdybysfzxdltjb.jasper";
		List<Lsdybysfzsdltjb> list = lsdybysfzxdltjbService.getLsdybysfzsdltjbData();
		JRDataSource dataSource = new JRBeanCollectionDataSource(list);
		try {
			this.previewReportUtil(jasperFilePath, response, dataSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//35kV变电站母线变量不平衡率
	@RequestMapping(value="/preview/35kvbdzmxdlbphl")
	public void previewReportFor35kvbdzmxdlbphl(HttpServletResponse response){
			jasperFilePath=baseFilePath+"35kvbdzmxdlbphl.jasper";
			List<Xsfzkhb> list = xsfzkhbService.getXsfzkhbDataFor35KV();
			JRDataSource dataSource = new JRBeanCollectionDataSource(list);
			try {
				this.previewReportUtil(jasperFilePath, response, dataSource);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	//110kv变电站母线变量不平衡率
	@RequestMapping(value="/preview/110kvbdzmxdlbphl")
	public void previewReportFor110kvbdzmxdlbphl(HttpServletResponse response){
		jasperFilePath=baseFilePath+"110kvbdzmxdlbphl.jasper";
		List<Xsfzkhb> list = xsfzkhbService.getXsfzkhbDataFor110KV();
		JRDataSource dataSource = new JRBeanCollectionDataSource(list);
		try {
			this.previewReportUtil(jasperFilePath, response, dataSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//各供电所自用电情况
	@RequestMapping(value="/preview/ggdszydqk")
	public void previewReportForggdszydqk(HttpServletResponse response){
		jasperFilePath=baseFilePath+"ggdszydqk.jasper";
		List<Ggdszydqk> list = ggdszydqkService.getGgdszydqkData();
		JRDataSource dataSource = new JRBeanCollectionDataSource(list);
		try {
			this.previewReportUtil(jasperFilePath, response, dataSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//供电所低压线损统计表
	@RequestMapping(value="/preview/tqxstjb")
	public void previewReportFortqxstjb(HttpServletResponse response){
		jasperFilePath=baseFilePath+"tqxstjb.jasper";
		List<GdsDyxstjb> list = new LinkedList<GdsDyxstjb>();
		JRDataSource dataSource = new JRBeanCollectionDataSource(list);
		try {
			this.previewReportUtil(jasperFilePath, response, dataSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//送电线路线损分线考核表
	@RequestMapping(value="/preview/sdxlxsfxkhb")
	public void previewReportForsdxlxsfxkhb(HttpServletResponse response){
		jasperFilePath=baseFilePath+"sdxlxsfxkhb.jasper";
		List<Xsfzkhb> list = new LinkedList<Xsfzkhb>();
		JRDataSource dataSource = new JRBeanCollectionDataSource(list);
		try {
			this.previewReportUtil(jasperFilePath, response, dataSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//线损分压考核表（营业）
	@RequestMapping(value="/preview/xsfykhbyy")
	public void previewReportForxsfykhbyy(HttpServletResponse response){
			jasperFilePath=baseFilePath+"xsfykhbyy.jasper";
			List<Xsfykhb> list = new LinkedList<Xsfykhb>();
			JRDataSource dataSource = new JRBeanCollectionDataSource(list);
			try {
				this.previewReportUtil(jasperFilePath, response, dataSource);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	//线损分战考核表
	@RequestMapping(value="/preview/xsfzkhb")
	public void previewReportForxsfzkhb(HttpServletResponse response){
				jasperFilePath=baseFilePath+"xsfzkhb.jasper";
				List<Xsfykhb> list = new LinkedList<Xsfykhb>();
				JRDataSource dataSource = new JRBeanCollectionDataSource(list);
				try {
					this.previewReportUtil(jasperFilePath, response, dataSource);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}	
	//变电站用电统计表
		@RequestMapping(value="/preview/bdzydtjb")
		public void previewReportForbdzydtjb(HttpServletResponse response){
					jasperFilePath=baseFilePath+"bdzydtjb.jasper";
					List<Bdzydtjb> list = new LinkedList<Bdzydtjb>();
					JRDataSource dataSource = new JRBeanCollectionDataSource(list);
					try {
						this.previewReportUtil(jasperFilePath, response, dataSource);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}	
	
	//跳转主页面
    @RequestMapping(value="/main")
	public String viewReportMain(@RequestParam("reportName") String reportName,Model model){
    	System.out.println(reportName);
    	String chReportName="";
    	if(reportName.equals("sldskvcjyyssltjbtb")){
    		chReportName="各所10kV0.4kV损失率统计表(同步)";
    	}
    	else if(reportName.equals("lsdybysfzxdltjb")){
    		chReportName="6kV-110kV专线电量统计表";
    	}else if(reportName.equals("35kvbdzmxdlbphl")){
    		chReportName="35kV变电站母线变量不平衡率";
    	}
    	else if(reportName.equals("110kvbdzmxdlbphl")){
    		chReportName="110kV变电站母线变量不平衡率";
    	}
    	else if(reportName.equals("ggdszydqk")){
    		chReportName="各供电所自用电情况";
    	}
    	else if(reportName.equals("tqxstjb")){
    		chReportName="供电所低压线损统计表";
    	}
    	else if(reportName.equals("sdxlxsfxkhb")){
    		chReportName="送电线路线损分线考核表";
    	}
    	else if(reportName.equals("xsfykhbyy")){
    		chReportName="线损分压考核表（营业）";
    	}
    	else if(reportName.equals("xsfzkhb")){
    		chReportName="线损分站考核表";
    	}
    	else if(reportName.equals("bdzydtjb")){
    		chReportName="站用电";
    	}
    	model.addAttribute("chreportName", chReportName);
    	model.addAttribute("reportName", reportName);
		return "modules/report/viewReport";
	}

}
