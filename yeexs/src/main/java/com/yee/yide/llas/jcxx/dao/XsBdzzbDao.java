/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.jcxx.entity.XsBdzzb;

import java.util.List;

/**
 * 变电站主变信息DAO接口
 * @author evay
 * @version 2017-04-16
 */
@MyBatisDao
public interface XsBdzzbDao extends CrudDao<XsBdzzb> {
    public List<String> findAllListDropdown() ;
}