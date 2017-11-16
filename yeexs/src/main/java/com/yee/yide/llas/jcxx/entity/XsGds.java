/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.entity;

import org.hibernate.validator.constraints.Length;
import com.yee.yide.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import com.yee.yide.common.persistence.DataEntity;

/**
 * 供电所基本信息Entity
 * @author evay_leec
 * @version 2017-03-25
 */
public class XsGds extends DataEntity<XsGds> {
	
	private static final long serialVersionUID = 1L;
	private String bh;		// 供电所编号
	private String mc;		// 供电所名称
	private Office office;		// 组织机构名称
	
	public XsGds() {
		super();
	}

	public XsGds(String id){
		super(id);
	}

	@Length(min=1, max=200, message="供电所编号长度必须介于 1 和 200 之间")
	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}
	
	@Length(min=0, max=30, message="供电所名称长度必须介于 0 和 30 之间")
	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}
	
	@NotNull(message="组织机构名称不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}