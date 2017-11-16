/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.jcxx.entity.DataimpRule;

/**
 * 数据导入规则DAO接口
 * @author evay_leec
 * @version 2017-11-13
 */
@MyBatisDao
public interface DataimpRuleDao extends CrudDao<DataimpRule> {
	
}