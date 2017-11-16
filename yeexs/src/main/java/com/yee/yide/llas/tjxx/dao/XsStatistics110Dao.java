/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.tjxx.entity.XsStatistics110;

/**
 * 110kV线损统计结果信息DAO接口
 * @author evay_leec
 * @version 2017-04-19
 */
@MyBatisDao
public interface XsStatistics110Dao extends CrudDao<XsStatistics110> {
    public void deleteQsrqToJsrq(XsStatistics110 xsStatistics110);
}