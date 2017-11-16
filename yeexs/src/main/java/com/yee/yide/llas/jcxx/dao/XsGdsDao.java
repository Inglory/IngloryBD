/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.jcxx.entity.XsGds;

import java.util.List;

/**
 * 供电所基本信息DAO接口
 * @author evay_leec
 * @version 2017-03-25
 */
@MyBatisDao
public interface XsGdsDao extends CrudDao<XsGds> {
    public List<String> findAllListDropdown() ;
}