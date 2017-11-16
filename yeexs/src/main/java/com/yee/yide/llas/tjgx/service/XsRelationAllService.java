/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsRelationAll;
import com.yee.yide.llas.tjgx.dao.XsRelationAllDao;

/**
 * 全网综合统计关系Service
 * @author evay
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class XsRelationAllService extends CrudService<XsRelationAllDao, XsRelationAll> {

	public XsRelationAll get(String id) {
		return super.get(id);
	}
	
	public List<XsRelationAll> findList(XsRelationAll xsRelationAll) {
		return super.findList(xsRelationAll);
	}
	
	public Page<XsRelationAll> findPage(Page<XsRelationAll> page, XsRelationAll xsRelationAll) {
		return super.findPage(page, xsRelationAll);
	}
	
	@Transactional(readOnly = false)
	public void save(XsRelationAll xsRelationAll) {
		super.save(xsRelationAll);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsRelationAll xsRelationAll) {
		super.delete(xsRelationAll);
	}
	
}