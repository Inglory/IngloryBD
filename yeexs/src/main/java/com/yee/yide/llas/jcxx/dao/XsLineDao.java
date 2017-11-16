/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.dao;

import com.yee.yide.common.persistence.CrudDao;
import com.yee.yide.common.persistence.annotation.MyBatisDao;
import com.yee.yide.llas.jcxx.entity.XsLine;

import java.util.List;

/**
 * 线路信息DAO接口
 * @author evay_leec
 * @version 2017-04-13
 */
@MyBatisDao
public interface XsLineDao extends CrudDao<XsLine> {
    public List<String> findListLineByDydj(String voltage) ;
    public List<String> findALLListLineByDydj() ;
    public List<String> findALLListMuxian() ;
    public void updateAll(XsLine xsLine) ;
}