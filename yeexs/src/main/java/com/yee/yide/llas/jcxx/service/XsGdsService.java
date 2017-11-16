/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.jcxx.dao.XsGdsDao;
import com.yee.yide.llas.jcxx.entity.XsGds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 供电所基本信息Service
 * @author evay_leec
 * @version 2017-03-25
 */
@Service
@Transactional(readOnly = true)
public class XsGdsService extends CrudService<XsGdsDao, XsGds> {

	public XsGds get(String id) {
		return super.get(id);
	}
	
	public List<XsGds> findList(XsGds xsGds) {
		return super.findList(xsGds);
	}
	
	public Page<XsGds> findPage(Page<XsGds> page, XsGds xsGds) {
		return super.findPage(page, xsGds);
	}
	
	@Transactional(readOnly = false)
	public void save(XsGds xsGds) {
		super.save(xsGds);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsGds xsGds) {
		super.delete(xsGds);
	}

	public List<String> findAllListDropdown() {
		return dao.findAllListDropdown();
	}
}