/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsRelationParline;
import com.yee.yide.llas.tjgx.dao.XsRelationParlineDao;

/**
 * 母线平衡关系Service
 * @author evay
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class XsRelationParlineService extends CrudService<XsRelationParlineDao, XsRelationParline> {

	public XsRelationParline get(String id) {
		return super.get(id);
	}
	
	public List<XsRelationParline> findList(XsRelationParline xsRelationParline) {
		return super.findList(xsRelationParline);
	}
	
	public Page<XsRelationParline> findPage(Page<XsRelationParline> page, XsRelationParline xsRelationParline) {
		return super.findPage(page, xsRelationParline);
	}
	
	@Transactional(readOnly = false)
	public void save(XsRelationParline xsRelationParline) {
		super.save(xsRelationParline);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsRelationParline xsRelationParline) {
		super.delete(xsRelationParline);
	}
	
}