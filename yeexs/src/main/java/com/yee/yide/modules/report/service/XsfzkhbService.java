package com.yee.yide.modules.report.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.modules.report.entity.Xsfzkhb;

@Service
@Transactional(readOnly = true)
public class XsfzkhbService {
    //35kV变电站母线变量不平衡率
	public List<Xsfzkhb> getXsfzkhbDataFor35KV(){
		List<Xsfzkhb> list = new LinkedList<Xsfzkhb>();
		return list;
		
	}
	//110kV变电站母线变量不平衡率
	public List<Xsfzkhb> getXsfzkhbDataFor110KV(){
		List<Xsfzkhb> list = new LinkedList<Xsfzkhb>();
		return list;
		
	}
	
	
}
