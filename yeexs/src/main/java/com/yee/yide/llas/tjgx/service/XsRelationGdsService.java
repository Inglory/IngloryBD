/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsRelationGds;
import com.yee.yide.llas.tjgx.dao.XsRelationGdsDao;

/**
 * 供电所损失关系Service
 * @author evay
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class XsRelationGdsService extends CrudService<XsRelationGdsDao, XsRelationGds> {

	public XsRelationGds get(String id) {
		return super.get(id);
	}
	
	public List<XsRelationGds> findList(XsRelationGds xsRelationGds) {
		return super.findList(xsRelationGds);
	}
	
	public Page<XsRelationGds> findPage(Page<XsRelationGds> page, XsRelationGds xsRelationGds) {
		return super.findPage(page, xsRelationGds);
	}
	
	@Transactional(readOnly = false)
	public void save(XsRelationGds xsRelationGds) {
		super.save(xsRelationGds);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsRelationGds xsRelationGds) {
		super.delete(xsRelationGds);
	}
	
}