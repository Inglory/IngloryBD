/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjxx.entity.XsStatisticsBdz;
import com.yee.yide.llas.tjxx.dao.XsStatisticsBdzDao;

/**
 * 变电站损失信息Service
 * @author evay_leec
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class XsStatisticsBdzService extends CrudService<XsStatisticsBdzDao, XsStatisticsBdz> {

	public XsStatisticsBdz get(String id) {
		return super.get(id);
	}
	
	public List<XsStatisticsBdz> findList(XsStatisticsBdz xsStatisticsBdz) {
		return super.findList(xsStatisticsBdz);
	}
	
	public Page<XsStatisticsBdz> findPage(Page<XsStatisticsBdz> page, XsStatisticsBdz xsStatisticsBdz) {
		return super.findPage(page, xsStatisticsBdz);
	}
	
	@Transactional(readOnly = false)
	public void save(XsStatisticsBdz xsStatisticsBdz) {
		super.save(xsStatisticsBdz);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsStatisticsBdz xsStatisticsBdz) {
		super.delete(xsStatisticsBdz);
	}
	
}