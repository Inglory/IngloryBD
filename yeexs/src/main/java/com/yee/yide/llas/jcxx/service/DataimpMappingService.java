/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.common.utils.StringUtils;
import com.yee.yide.llas.jcxx.entity.DataimpMapping;
import com.yee.yide.llas.jcxx.dao.DataimpMappingDao;

/**
 * 数据导入规则Service
 * @author evay_leec
 * @version 2017-11-10
 */
@Service
@Transactional(readOnly = true)
public class DataimpMappingService extends CrudService<DataimpMappingDao, DataimpMapping> {

	
	public DataimpMapping get(String id) {
		DataimpMapping dataimpMapping = super.get(id);
		return dataimpMapping;
	}
	
	public List<DataimpMapping> findList(DataimpMapping dataimpMapping) {
		return super.findList(dataimpMapping);
	}
	
	public Page<DataimpMapping> findPage(Page<DataimpMapping> page, DataimpMapping dataimpMapping) {
		return super.findPage(page, dataimpMapping);
	}
	
	@Transactional(readOnly = false)
	public void save(DataimpMapping dataimpMapping) {
		super.save(dataimpMapping);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataimpMapping dataimpMapping) {
		super.delete(dataimpMapping);
	}
	
}