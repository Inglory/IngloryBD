/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjgx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjgx.entity.XsGxZb;
import com.yee.yide.llas.tjgx.dao.XsGxZbDao;

/**
 * 主变关系Service
 * @author evay
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class XsGxZbService extends CrudService<XsGxZbDao, XsGxZb> {

	public XsGxZb get(String id) {
		return super.get(id);
	}
	
	public List<XsGxZb> findList(XsGxZb xsGxZb) {
		return super.findList(xsGxZb);
	}
	
	public Page<XsGxZb> findPage(Page<XsGxZb> page, XsGxZb xsGxZb) {
		return super.findPage(page, xsGxZb);
	}
	
	@Transactional(readOnly = false)
	public void save(XsGxZb xsGxZb) {
		super.save(xsGxZb);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsGxZb xsGxZb) {
		super.delete(xsGxZb);
	}
	
}