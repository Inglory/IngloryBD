/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.jcxx.entity.XsLineHis;
import com.yee.yide.llas.jcxx.dao.XsLineHisDao;

/**
 * 历史线路信息查询Service
 * @author evay_leec
 * @version 2017-10-29
 */
@Service
@Transactional(readOnly = true)
public class XsLineHisService extends CrudService<XsLineHisDao, XsLineHis> {

	public XsLineHis get(String id) {
		return super.get(id);
	}
	
	public List<XsLineHis> findList(XsLineHis xsLineHis) {
		return super.findList(xsLineHis);
	}
	
	public Page<XsLineHis> findPage(Page<XsLineHis> page, XsLineHis xsLineHis) {
		return super.findPage(page, xsLineHis);
	}
	
	@Transactional(readOnly = false)
	public void save(XsLineHis xsLineHis) {
		super.save(xsLineHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsLineHis xsLineHis) {
		super.delete(xsLineHis);
	}
	@Transactional(readOnly = false)
	public void insertHisData() {
		dao.insertHisData();
	}
}