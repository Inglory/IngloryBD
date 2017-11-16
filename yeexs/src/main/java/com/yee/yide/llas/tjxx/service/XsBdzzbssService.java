/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjxx.entity.XsBdzzbss;
import com.yee.yide.llas.tjxx.dao.XsBdzzbssDao;

/**
 * 主变电量损失统计Service
 * @author evay
 * @version 2017-04-23
 */
@Service
@Transactional(readOnly = true)
public class XsBdzzbssService extends CrudService<XsBdzzbssDao, XsBdzzbss> {

	public XsBdzzbss get(String id) {
		return super.get(id);
	}
	
	public List<XsBdzzbss> findList(XsBdzzbss xsBdzzbss) {
		return super.findList(xsBdzzbss);
	}
	
	public Page<XsBdzzbss> findPage(Page<XsBdzzbss> page, XsBdzzbss xsBdzzbss) {
		return super.findPage(page, xsBdzzbss);
	}
	
	@Transactional(readOnly = false)
	public void save(XsBdzzbss xsBdzzbss) {
		super.save(xsBdzzbss);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsBdzzbss xsBdzzbss) {
		super.delete(xsBdzzbss);
	}
	
}