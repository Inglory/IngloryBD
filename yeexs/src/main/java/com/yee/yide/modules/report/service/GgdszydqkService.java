package com.yee.yide.modules.report.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.modules.report.entity.Ggdszydqk;

@Service
@Transactional(readOnly = true)
public class GgdszydqkService {
	
	public List<Ggdszydqk> getGgdszydqkData(){
		List<Ggdszydqk> list = new LinkedList<Ggdszydqk>();
		return list;
	}

}
