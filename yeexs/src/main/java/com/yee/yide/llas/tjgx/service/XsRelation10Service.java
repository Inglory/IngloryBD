/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsRelation10;
import com.yee.yide.llas.tjgx.dao.XsRelation10Dao;

/**
 * 10kV线损统计关系Service
 * @author evay
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class XsRelation10Service extends CrudService<XsRelation10Dao, XsRelation10> {

	public XsRelation10 get(String id) {
		return super.get(id);
	}
	
	public List<XsRelation10> findList(XsRelation10 xsRelation10) {
		return super.findList(xsRelation10);
	}
	
	public Page<XsRelation10> findPage(Page<XsRelation10> page, XsRelation10 xsRelation10) {
		return super.findPage(page, xsRelation10);
	}
	
	@Transactional(readOnly = false)
	public void save(XsRelation10 xsRelation10) {
		super.save(xsRelation10);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsRelation10 xsRelation10) {
		super.delete(xsRelation10);
	}
	
}