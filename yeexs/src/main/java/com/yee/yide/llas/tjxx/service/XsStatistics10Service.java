/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjxx.entity.XsStatistics10;
import com.yee.yide.llas.tjxx.dao.XsStatistics10Dao;

/**
 * 10kV线损统计结果Service
 * @author evay_leec
 * @version 2017-11-14
 */
@Service
@Transactional(readOnly = true)
public class XsStatistics10Service extends CrudService<XsStatistics10Dao, XsStatistics10> {

	public XsStatistics10 get(String id) {
		return super.get(id);
	}
	
	public List<XsStatistics10> findList(XsStatistics10 xsStatistics10) {
		return super.findList(xsStatistics10);
	}
	
	public Page<XsStatistics10> findPage(Page<XsStatistics10> page, XsStatistics10 xsStatistics10) {
		return super.findPage(page, xsStatistics10);
	}
	
	@Transactional(readOnly = false)
	public void save(XsStatistics10 xsStatistics10) {
		super.save(xsStatistics10);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsStatistics10 xsStatistics10) {
		super.delete(xsStatistics10);
	}
	
}