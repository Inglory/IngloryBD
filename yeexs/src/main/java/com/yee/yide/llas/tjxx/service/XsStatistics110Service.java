/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjxx.dao.XsStatistics110Dao;
import com.yee.yide.llas.tjxx.entity.XsStatistics110;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 110kV线损统计结果信息Service
 * @author evay_leec
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class XsStatistics110Service extends CrudService<XsStatistics110Dao, XsStatistics110> {

	public XsStatistics110 get(String id) {
		return super.get(id);
	}
	
	public List<XsStatistics110> findList(XsStatistics110 xsStatistics110) {
		return super.findList(xsStatistics110);
	}
	
	public Page<XsStatistics110> findPage(Page<XsStatistics110> page, XsStatistics110 xsStatistics110) {
		return super.findPage(page, xsStatistics110);
	}
	
	@Transactional(readOnly = false)
	public void save(XsStatistics110 xsStatistics110) {
		super.save(xsStatistics110);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsStatistics110 xsStatistics110) {
		super.delete(xsStatistics110);
	}
	@Transactional(readOnly = false)
	public void deleteQsrqToJsrq(XsStatistics110 xsStatistics110) {
		dao.deleteQsrqToJsrq(xsStatistics110);
	}

}