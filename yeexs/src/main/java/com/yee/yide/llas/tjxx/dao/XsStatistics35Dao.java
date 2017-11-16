/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.tjxx.entity.XsStatistics35;

/**
 * 35kV线损统计结果DAO接口
 * @author evay_leec
 * @version 2017-04-19
 */
@MyBatisDao
public interface XsStatistics35Dao extends CrudDao<XsStatistics35> {
   public void deleteQsrqToJsrq(XsStatistics35 xsStatistics35);
}