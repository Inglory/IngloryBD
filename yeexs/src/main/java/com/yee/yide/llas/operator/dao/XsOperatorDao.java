/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.operator.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.operator.entity.XsOperator;

/**
 * 制表人员、用电小时、统计区间设置与管理DAO接口
 * @author evay_leec
 * @version 2017-10-20
 */
@MyBatisDao
public interface XsOperatorDao extends CrudDao<XsOperator> {
	/*
	 * 以下为自定义的方法
	 */
	public int getMaxSerialNumber();
}