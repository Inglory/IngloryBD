/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.dljs.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.dljs.entity.XsLinewastage;

/**
 * 电量计算DAO接口
 * @author evay
 * @version 2017-04-16
 */
@MyBatisDao
public interface XsLinewastageDao extends CrudDao<XsLinewastage> {
    public void deleteQsrqtoJsrq(XsLinewastage xsLinewastage);
    public void insertFromLine(XsLinewastage xsLinewastage);
}