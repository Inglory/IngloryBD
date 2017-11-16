/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.dljs.dao.XsLinewastageDao;
import com.yee.yide.llas.dljs.entity.XsLinewastage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 电量计算Service
 * @author evay
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class XsLinewastageService extends CrudService<XsLinewastageDao, XsLinewastage> {

	public XsLinewastage get(String id) {
		return super.get(id);
	}
	
	public List<XsLinewastage> findList(XsLinewastage xsLinewastage) {
		return super.findList(xsLinewastage);
	}
	
	public Page<XsLinewastage> findPage(Page<XsLinewastage> page, XsLinewastage xsLinewastage) {
		return super.findPage(page, xsLinewastage);
	}
	
	@Transactional(readOnly = false)
	public void save(XsLinewastage xsLinewastage) {
		super.save(xsLinewastage);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsLinewastage xsLinewastage) {
		super.delete(xsLinewastage);
	}

	@Transactional(readOnly = false)
	public void deleteQsrqtoJsrq(XsLinewastage xsLinewastage) {
		dao.deleteQsrqtoJsrq(xsLinewastage);
	}
	@Transactional(readOnly = false)
	public void insertFromLine(XsLinewastage xsLinewastage) {
		dao.insertFromLine(xsLinewastage);
	}
	
}