/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjxx.dao.XsBdzdlssDao;
import com.yee.yide.llas.tjxx.entity.XsBdzdlss;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 变电站电量损失Service
 * @author evay
 * @version 2017-04-19
 */
@Service
@Transactional(readOnly = true)
public class XsBdzdlssService extends CrudService<XsBdzdlssDao, XsBdzdlss> {

	public XsBdzdlss get(String id) {
		return super.get(id);
	}
	
	public List<XsBdzdlss> findList(XsBdzdlss xsBdzdlss) {
		return super.findList(xsBdzdlss);
	}
	
	public Page<XsBdzdlss> findPage(Page<XsBdzdlss> page, XsBdzdlss xsBdzdlss) {
		return super.findPage(page, xsBdzdlss);
	}
	
	@Transactional(readOnly = false)
	public void save(XsBdzdlss xsBdzdlss) {
		super.save(xsBdzdlss);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsBdzdlss xsBdzdlss) {
		super.delete(xsBdzdlss);
	}
	@Transactional(readOnly = false)
	public void deleteQsrqToJsrq(XsBdzdlss xsBdzdlss) {
		dao.deleteQsrqToJsrq(xsBdzdlss);
	}

}