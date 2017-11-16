/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.common.utils.database.mysql;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yee.yide.common.utils.excel.ImportExcel;
import com.yee.yide.llas.jcxx.entity.DataimpRule;
import com.yee.yide.llas.jcxx.service.DataimpRuleService;

/**
 * 导入数据到数据库
 * @author ThinkGem
 * @version 2013-03-10
 */
public class ImportDB {
	
	private static Logger log = LoggerFactory.getLogger(ImportDB.class);
			
	
	/**
	 * 
	 * @param path 导入文件，读取第一个工作表
	 * @param headerNum 标题行号，数据行号=标题行号+1
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 */
	@ModelAttribute
	public   void importDB(DataimpRule dataimpRule, int headerNum) 
			throws  IOException, InvalidFormatException {
	//首先获取数据源，要分别处理接口、文件以及各种数据库作为源。
		String sourceType = dataimpRule.getSourceType();
		String sourcePath = dataimpRule.getSource();
		//Execel文件作为数据源。
		if(sourceType.equals("excel")) {
			ImportExcel ei = new ImportExcel(sourcePath, headerNum);
			for (int i = ei.getDataRowNum(); i < ei.getLastDataRowNum(); i++) {
				Row row = ei.getRow(i);
				for (int j = 0; j < ei.getLastCellNum(); j++) {
					Object val = ei.getCellValue(row, j);
					System.out.print(val+", ");
				}
				System.out.print("\n");
			}
			
		}
		}
	}
	

