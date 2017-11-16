/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.tjxx.entity.XsStatisticsGds;
import com.yee.yide.llas.tjxx.dao.XsStatisticsGdsDao;

/**
 * 供电所电量损失统计Service
 * @author evay
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class XsStatisticsGdsService extends CrudService<XsStatisticsGdsDao, XsStatisticsGds> {

	public XsStatisticsGds get(String id) {
		return super.get(id);
	}
	
	public List<XsStatisticsGds> findList(XsStatisticsGds xsStatisticsGds) {
		return super.findList(xsStatisticsGds);
	}
	
	public Page<XsStatisticsGds> findPage(Page<XsStatisticsGds> page, XsStatisticsGds xsStatisticsGds) {
		return super.findPage(page, xsStatisticsGds);
	}
	
	@Transactional(readOnly = false)
	public void save(XsStatisticsGds xsStatisticsGds) {
		super.save(xsStatisticsGds);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsStatisticsGds xsStatisticsGds) {
		super.delete(xsStatisticsGds);
	}
	
}