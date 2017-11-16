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
import com.yee.yide.llas.jcxx.entity.DataimpRule;
import com.yee.yide.llas.jcxx.dao.DataimpRuleDao;
import com.yee.yide.llas.jcxx.entity.DataimpMapping;
import com.yee.yide.llas.jcxx.dao.DataimpMappingDao;

/**
 * 数据导入规则Service
 * @author evay_leec
 * @version 2017-11-13
 */
@Service
@Transactional(readOnly = true)
public class DataimpRuleService extends CrudService<DataimpRuleDao, DataimpRule> {

	@Autowired
	private DataimpMappingDao dataimpMappingDao;
	
	public DataimpRule get(String id) {
		DataimpRule dataimpRule = super.get(id);
		dataimpRule.setDataimpMappingList(dataimpMappingDao.findList(new DataimpMapping(dataimpRule)));
		return dataimpRule;
	}
	
	public List<DataimpRule> findList(DataimpRule dataimpRule) {
		return super.findList(dataimpRule);
	}
	
	public Page<DataimpRule> findPage(Page<DataimpRule> page, DataimpRule dataimpRule) {
		return super.findPage(page, dataimpRule);
	}
	
	@Transactional(readOnly = false)
	public void save(DataimpRule dataimpRule) {
		super.save(dataimpRule);
		for (DataimpMapping dataimpMapping : dataimpRule.getDataimpMappingList()){
			if (dataimpMapping.getId() == null){
				continue;
			}
			if (DataimpMapping.DEL_FLAG_NORMAL.equals(dataimpMapping.getDelFlag())){
				if (StringUtils.isBlank(dataimpMapping.getId())){
					dataimpMapping.setDataimpRule(dataimpRule);
					dataimpMapping.preInsert();
					dataimpMappingDao.insert(dataimpMapping);
				}else{
					dataimpMapping.preUpdate();
					dataimpMappingDao.update(dataimpMapping);
				}
			}else{
				dataimpMappingDao.delete(dataimpMapping);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DataimpRule dataimpRule) {
		super.delete(dataimpRule);
		dataimpMappingDao.delete(new DataimpMapping(dataimpRule));
	}
	
}