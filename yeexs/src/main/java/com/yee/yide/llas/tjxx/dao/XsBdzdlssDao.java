/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.tjxx.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.tjxx.entity.XsBdzdlss;

/**
 * 变电站电量损失DAO接口
 * @author evay
 * @version 2017-04-19
 */
@MyBatisDao
public interface XsBdzdlssDao extends CrudDao<XsBdzdlss> {
    public void  deleteQsrqToJsrq(XsBdzdlss xsBdzdlss);
}