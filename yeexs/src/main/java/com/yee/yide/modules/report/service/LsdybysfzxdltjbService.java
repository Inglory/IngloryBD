package com.yee.yide.modules.report.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.modules.report.entity.Lsdybysfzsdltjb;
/*
 * 6kV-110kV专线电量统计表service
 */

@Service
@Transactional(readOnly = true)
public class LsdybysfzxdltjbService {
	
	public List<Lsdybysfzsdltjb> getLsdybysfzsdltjbData(){
		List<Lsdybysfzsdltjb> list = new LinkedList<Lsdybysfzsdltjb>();
		return list;
	}
	

}
