/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsGxBs;
import com.yee.yide.llas.tjgx.dao.XsGxBsDao;

/**
 * 变电站损失关系Service
 * @author evay
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class XsGxBsService extends CrudService<XsGxBsDao, XsGxBs> {

	public XsGxBs get(String id) {
		return super.get(id);
	}
	
	public List<XsGxBs> findList(XsGxBs xsGxBs) {
		return super.findList(xsGxBs);
	}
	
	public Page<XsGxBs> findPage(Page<XsGxBs> page, XsGxBs xsGxBs) {
		return super.findPage(page, xsGxBs);
	}
	
	@Transactional(readOnly = false)
	public void save(XsGxBs xsGxBs) {
		super.save(xsGxBs);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsGxBs xsGxBs) {
		super.delete(xsGxBs);
	}
	
}