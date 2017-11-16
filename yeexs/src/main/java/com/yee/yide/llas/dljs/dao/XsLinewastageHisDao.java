/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.dljs.entity.XsLinewastageHis;

/**
 * 历史线损计算电量DAO接口
 * @author evay_leec
 * @version 2017-10-29
 */
@MyBatisDao
public interface XsLinewastageHisDao extends CrudDao<XsLinewastageHis> {
	public void insertHisData();
}