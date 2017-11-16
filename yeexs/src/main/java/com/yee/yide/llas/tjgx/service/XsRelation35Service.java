/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsRelation35;
import com.yee.yide.llas.tjgx.dao.XsRelation35Dao;

/**
 * 35kV线路统计关系Service
 * @author evay
 * @version 2017-04-15
 */
@Service
@Transactional(readOnly = true)
public class XsRelation35Service extends CrudService<XsRelation35Dao, XsRelation35> {

	public XsRelation35 get(String id) {
		return super.get(id);
	}
	
	public List<XsRelation35> findList(XsRelation35 xsRelation35) {
		return super.findList(xsRelation35);
	}
	
	public Page<XsRelation35> findPage(Page<XsRelation35> page, XsRelation35 xsRelation35) {
		return super.findPage(page, xsRelation35);
	}
	
	@Transactional(readOnly = false)
	public void save(XsRelation35 xsRelation35) {
		super.save(xsRelation35);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsRelation35 xsRelation35) {
		super.delete(xsRelation35);
	}
	
}