/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 数据导入规则Entity
 * @author evay_leec
 * @version 2017-11-13
 */
public class DataimpRule extends DataEntity<DataimpRule> {
	
	private static final long serialVersionUID = 1L;
	private String ruleName;		// 导入规则名称
	private String sourceType;		// 源类型
	private String source;		// 导出源
	private String destinationType;		// 目的表类型
	private String destination;		// 导入目的表
	private List<DataimpMapping> dataimpMappingList = Lists.newArrayList();		// 子表列表
	
	public DataimpRule() {
		super();
	}

	public DataimpRule(String id){
		super(id);
	}

	@Length(min=1, max=100, message="导入规则名称长度必须介于 1 和 100 之间")
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	@Length(min=1, max=20, message="源类型长度必须介于 1 和 20 之间")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	@Length(min=1, max=100, message="导出源长度必须介于 1 和 100 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=1, max=20, message="目的表类型长度必须介于 1 和 20 之间")
	public String getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	
	@Length(min=1, max=100, message="导入目的表长度必须介于 1 和 100 之间")
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public List<DataimpMapping> getDataimpMappingList() {
		return dataimpMappingList;
	}

	public void setDataimpMappingList(List<DataimpMapping> dataimpMappingList) {
		this.dataimpMappingList = dataimpMappingList;
	}
}