/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjxx.dao.XsStatistics35Dao;
import com.yee.yide.llas.tjxx.entity.XsStatistics35;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 35kV线损统计结果Service
 * @author evay_leec
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class XsStatistics35Service extends CrudService<XsStatistics35Dao, XsStatistics35> {

	public XsStatistics35 get(String id) {
		return super.get(id);
	}
	
	public List<XsStatistics35> findList(XsStatistics35 xsStatistics35) {
		return super.findList(xsStatistics35);
	}
	
	public Page<XsStatistics35> findPage(Page<XsStatistics35> page, XsStatistics35 xsStatistics35) {
		return super.findPage(page, xsStatistics35);
	}
	
	@Transactional(readOnly = false)
	public void save(XsStatistics35 xsStatistics35) {
		super.save(xsStatistics35);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsStatistics35 xsStatistics35) {
		super.delete(xsStatistics35);
	}

	@Transactional(readOnly = false)
	public void deleteQsrqToJsrq(XsStatistics35 xsStatistics35) {
	dao.deleteQsrqToJsrq(xsStatistics35);
	}

}