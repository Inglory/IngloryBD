/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.entity;

import org.hibernate.validator.constraints.Length;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 数据导入规则Entity
 * @author evay_leec
 * @version 2017-11-13
 */
public class DataimpMapping extends DataEntity<DataimpMapping> {
	
	private static final long serialVersionUID = 1L;
	private DataimpRule dataimpRule;		// 规则 父类
	private String sourceField;		// 源字段
	private Integer excelColumn;		// excel字段所在列号
	private Integer excelRow;		// excel表头所在行号
	private String destField;		// 目标字段
	
	public DataimpMapping() {
		super();
	}

	public DataimpMapping(String id){
		super(id);
	}

	public DataimpMapping(DataimpRule dataimpRule){
		this.dataimpRule = dataimpRule;
	}

	@Length(min=1, max=64, message="规则长度必须介于 1 和 64 之间")
	public DataimpRule getDataimpRule() {
		return dataimpRule;
	}

	public void setDataimpRule(DataimpRule dataimpRule) {
		this.dataimpRule = dataimpRule;
	}
	
	@Length(min=1, max=100, message="源字段长度必须介于 1 和 100 之间")
	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}
	
	public Integer getExcelColumn() {
		return excelColumn;
	}

	public void setExcelColumn(Integer excelColumn) {
		this.excelColumn = excelColumn;
	}
	
	public Integer getExcelRow() {
		return excelRow;
	}

	public void setExcelRow(Integer excelRow) {
		this.excelRow = excelRow;
	}
	
	@Length(min=1, max=100, message="目标字段长度必须介于 1 和 100 之间")
	public String getDestField() {
		return destField;
	}

	public void setDestField(String destField) {
		this.destField = destField;
	}
	
}