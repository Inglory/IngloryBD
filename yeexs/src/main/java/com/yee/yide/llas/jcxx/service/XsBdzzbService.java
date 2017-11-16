/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yee/yide">yide</a> All rights reserved.
 */
package com.yee.yide.llas.jcxx.service;

import com.yee.yide.common.persistence.Page;
import com.yee.yide.common.service.CrudService;
import com.yee.yide.llas.jcxx.dao.XsBdzzbDao;
import com.yee.yide.llas.jcxx.entity.XsBdzzb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 变电站主变信息Service
 * @author evay
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class XsBdzzbService extends CrudService<XsBdzzbDao, XsBdzzb> {

	public XsBdzzb get(String id) {
		return super.get(id);
	}
	
	public List<XsBdzzb> findList(XsBdzzb xsBdzzb) {
		return super.findList(xsBdzzb);
	}
	
	public Page<XsBdzzb> findPage(Page<XsBdzzb> page, XsBdzzb xsBdzzb) {
		return super.findPage(page, xsBdzzb);
	}
	
	@Transactional(readOnly = false)
	public void save(XsBdzzb xsBdzzb) {
		super.save(xsBdzzb);
	}
	
	@Transactional(readOnly = false)
	public void delete(XsBdzzb xsBdzzb) {
		super.delete(xsBdzzb);
	}

	public List<String> findAllListDropdown() {
		return dao.findAllListDropdown();
	}
}