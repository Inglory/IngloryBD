/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsRelation110;
import com.yee.yide.llas.tjgx.dao.XsRelation110Dao;

/**
 * 110kV线路统计关系维护Service
 * @author evay_leec
 * @version 2017-04-14
 */
@Service
@Transactional(readOnly = true)
public class XsRelation110Service extends CrudService<XsRelation110Dao, XsRelation110> {

	public XsRelation110 get(String id) {
		return super.get(id);
	}
	
	public List<XsRelation110> findList(XsRelation110 xsRelation110) {
		return super.findList(xsRelation110);
	}
	
	public Page<XsRelation110> findPage(Page<XsRelation110> page, XsRelation110 xsRelation110) {
		return super.findPage(page, xsRelation110);
	}
	
	@Transactional(readOnly = false)
	public void save(XsRelation110 xsRelation110) {
		super.save(xsRelation110);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsRelation110 xsRelation110) {
		super.delete(xsRelation110);
	}
	
}