/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.jcxx.entity.XsLineHis;

/**
 * 历史线路信息查询DAO接口
 * @author evay_leec
 * @version 2017-10-29
 */
@MyBatisDao
public interface XsLineHisDao extends CrudDao<XsLineHis> {
	public void insertHisData() ;
}