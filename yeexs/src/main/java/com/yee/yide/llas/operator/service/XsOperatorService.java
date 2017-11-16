/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.operator.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.operator.entity.XsOperator;
import com.yee.yide.llas.operator.dao.XsOperatorDao;

/**
 * 制表人员、用电小时、统计区间设置与管理Service
 * @author evay_leec
 * @version 2017-10-20
 */
@Service
@Transactional(readOnly = true)
public class XsOperatorService extends CrudService<XsOperatorDao, XsOperator> {

	public XsOperator get(String id) {
		return super.get(id);
	}
	
	public List<XsOperator> findList(XsOperator xsOperator) {
		return super.findList(xsOperator);
	}
	
	public Page<XsOperator> findPage(Page<XsOperator> page, XsOperator xsOperator) {
		return super.findPage(page, xsOperator);
	}
	
	@Transactional(readOnly = false)
	public void save(XsOperator xsOperator) {
		super.save(xsOperator);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsOperator xsOperator) {
		super.delete(xsOperator);
	}
	 
	/*
	 * 以下为自定义的方法
	 */
	public int getMaxSerialNumber() {
		return dao.getMaxSerialNumber();
	}
}