/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjxx.dao.XsParlinebalanceDao;
import com.yee.yide.llas.tjxx.entity.XsParlinebalance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 母线平衡统计Service
 * @author evay
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class XsParlinebalanceService extends CrudService<XsParlinebalanceDao, XsParlinebalance> {

	public XsParlinebalance get(String id) {
		return super.get(id);
	}
	
	public List<XsParlinebalance> findList(XsParlinebalance xsParlinebalance) {
		return super.findList(xsParlinebalance);
	}
	
	public Page<XsParlinebalance> findPage(Page<XsParlinebalance> page, XsParlinebalance xsParlinebalance) {
		return super.findPage(page, xsParlinebalance);
	}
	
	@Transactional(readOnly = false)
	public void save(XsParlinebalance xsParlinebalance) {
		super.save(xsParlinebalance);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsParlinebalance xsParlinebalance) {
		super.delete(xsParlinebalance);
	}
	@Transactional(readOnly = false)
	public void deleteQsrqToJsrq(XsParlinebalance xsParlinebalance) {
		dao.deleteQsrqToJsrq(xsParlinebalance);
	}
}