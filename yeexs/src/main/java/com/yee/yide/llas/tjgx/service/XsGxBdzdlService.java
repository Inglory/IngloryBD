/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsGxBdzdl;
import com.yee.yide.llas.tjgx.dao.XsGxBdzdlDao;

/**
 * 变电站电量关系Service
 * @author evay
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class XsGxBdzdlService extends CrudService<XsGxBdzdlDao, XsGxBdzdl> {

	public XsGxBdzdl get(String id) {
		return super.get(id);
	}
	
	public List<XsGxBdzdl> findList(XsGxBdzdl xsGxBdzdl) {
		return super.findList(xsGxBdzdl);
	}
	
	public Page<XsGxBdzdl> findPage(Page<XsGxBdzdl> page, XsGxBdzdl xsGxBdzdl) {
		return super.findPage(page, xsGxBdzdl);
	}
	
	@Transactional(readOnly = false)
	public void save(XsGxBdzdl xsGxBdzdl) {
		super.save(xsGxBdzdl);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsGxBdzdl xsGxBdzdl) {
		super.delete(xsGxBdzdl);
	}
	
}