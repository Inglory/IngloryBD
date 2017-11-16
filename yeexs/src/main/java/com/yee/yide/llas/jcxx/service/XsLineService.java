/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.jcxx.dao.XsLineDao;
import com.yee.yide.llas.jcxx.entity.XsLine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 线路信息Service
 * @author evay_leec
 * @version 2017-04-13
 */
@Service
@Transactional(readOnly = true)
public class XsLineService extends CrudService<XsLineDao, XsLine> {

	public XsLine get(String id) {
		return super.get(id);
	}
	
	public List<XsLine> findList(XsLine xsLine) {
		return super.findList(xsLine);
	}
	
	public Page<XsLine> findPage(Page<XsLine> page, XsLine xsLine) {
		return super.findPage(page, xsLine);
	}
	
	@Transactional(readOnly = false)
	public void save(XsLine xsLine) {
		super.save(xsLine);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsLine xsLine) {
		super.delete(xsLine);
	}

	public List<String> findListLineByDydj( String voltage) {
		return dao.findListLineByDydj(voltage);
	}
	public List<String> findALLListLineByDydj()  {
		return dao.findALLListLineByDydj();
	}
	public List<String> findALLListMuxian()  {
		return dao.findALLListMuxian();
	}
	
	/**
	 * 更新表里所有信息
	 */
	@Transactional(readOnly = false)
	public void updateAll(XsLine xsLine) {
		dao.updateAll(xsLine);
	}
	
}