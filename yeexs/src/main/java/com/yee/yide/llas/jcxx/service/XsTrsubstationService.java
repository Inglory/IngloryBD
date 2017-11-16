/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.jcxx.dao.XsTrsubstationDao;
import com.yee.yide.llas.jcxx.entity.XsTrsubstation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 变电站信息Service
 * @author evay_leec
 * @version 2017-03-25
 */
@Service
@Transactional(readOnly = true)
public class XsTrsubstationService extends CrudService<XsTrsubstationDao, XsTrsubstation> {

	public XsTrsubstation get(String id) {
		return super.get(id);
	}
	
	public List<XsTrsubstation> findList(XsTrsubstation xsTrsubstation) {
		return super.findList(xsTrsubstation);
	}
	
	public Page<XsTrsubstation> findPage(Page<XsTrsubstation> page, XsTrsubstation xsTrsubstation) {
		return super.findPage(page, xsTrsubstation);
	}
	
	@Transactional(readOnly = false)
	public void save(XsTrsubstation xsTrsubstation) {
		super.save(xsTrsubstation);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsTrsubstation xsTrsubstation) {
		super.delete(xsTrsubstation);
	}

	public List<String> findBdzList(){
		return dao.findBdzList();
	}
	 public XsTrsubstation getByCode(String bdzCode) {
		 return dao.getByCode(bdzCode);
	 }
}