/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.dljs.entity.XsLinewastageHis;
import com.yee.yide.llas.dljs.dao.XsLinewastageHisDao;

/**
 * 历史线损计算电量Service
 * @author evay_leec
 * @version 2017-10-29
 */
@Service
@Transactional(readOnly = true)
public class XsLinewastageHisService extends CrudService<XsLinewastageHisDao, XsLinewastageHis> {

	public XsLinewastageHis get(String id) {
		return super.get(id);
	}
	
	public List<XsLinewastageHis> findList(XsLinewastageHis xsLinewastageHis) {
		return super.findList(xsLinewastageHis);
	}
	
	public Page<XsLinewastageHis> findPage(Page<XsLinewastageHis> page, XsLinewastageHis xsLinewastageHis) {
		return super.findPage(page, xsLinewastageHis);
	}
	
	@Transactional(readOnly = false)
	public void save(XsLinewastageHis xsLinewastageHis) {
		super.save(xsLinewastageHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsLinewastageHis xsLinewastageHis) {
		super.delete(xsLinewastageHis);
	}
	@Transactional(readOnly = false)
	public void insertHisData() {
		dao.insertHisData();
	}
}