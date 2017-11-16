package com.yee.yide.modules.report.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yee.yide.modules.report.entity.Sldskvcjyyssltjbtb;

@Service
@Transactional(readOnly = true)
public class SldskvcjyyssltjbtbService {
	//为该list追加合计数据0.4kv，10kv
	public List<Sldskvcjyyssltjbtb> getSldskvcjyyssltjbtbData(){
		BigDecimal totalbygkbdls=new BigDecimal(0);
		BigDecimal totalbycjdls=new BigDecimal(0);
		BigDecimal totalbyyysdls=new BigDecimal(0);

	    BigDecimal totalljgkbdls=new BigDecimal(0);
	    BigDecimal totalljcjdls=new BigDecimal(0);
	    BigDecimal totalljyysdls=new BigDecimal(0);
	    
	    BigDecimal totalbygkbdllds=new BigDecimal(0);
		BigDecimal totalbycjdllds=new BigDecimal(0);
		BigDecimal totalbyyysdllds=new BigDecimal(0);

	    BigDecimal totalljgkbdllds=new BigDecimal(0);
	    BigDecimal totalljcjdllds=new BigDecimal(0);
	    BigDecimal totalljyysdllds=new BigDecimal(0);
		
		List<Sldskvcjyyssltjbtb> resultList = testdata();
		List<Sldskvcjyyssltjbtb> list = this.addGroupTotalData(resultList);
		Sldskvcjyyssltjbtb total = new Sldskvcjyyssltjbtb();
		for(int i=0;i<list.size();i++){
			
			if(list.get(i).getXlmc().startsWith("10kV合计")){
				if(list.get(i).getBygkbdl()!=null){
					totalbygkbdls= totalbygkbdls.add(list.get(i).getBygkbdl());
				}
				if(list.get(i).getBycjdl()!=null){
					totalbycjdls=totalbycjdls.add(list.get(i).getBycjdl());
				}
				if(list.get(i).getByyysdl()!=null){
					totalbyyysdls=totalbyyysdls.add(list.get(i).getByyysdl());
				}
				if(list.get(i).getLjgkbdl()!=null){
					totalljgkbdls= totalljgkbdls.add(list.get(i).getLjgkbdl());
				}
				if(list.get(i).getLjcjdl()!=null){
					totalljcjdls=totalljcjdls.add(list.get(i).getLjcjdl());
				}
				if(list.get(i).getLjyysdl()!=null){
					totalljyysdls=totalljyysdls.add(list.get(i).getLjyysdl());
				}
			}
			if(list.get(i).getXlmc().startsWith("0.4kV合计")){
				if(list.get(i).getBygkbdl()!=null){
					totalbygkbdllds= totalbygkbdllds.add(list.get(i).getBygkbdl());
				}
				if(list.get(i).getBycjdl()!=null){
					totalbycjdllds=totalbycjdllds.add(list.get(i).getBycjdl());
				}
				if(list.get(i).getByyysdl()!=null){
					totalbyyysdllds=totalbyyysdllds.add(list.get(i).getByyysdl());
				}
				if(list.get(i).getLjgkbdl()!=null){
					totalljgkbdllds= totalljgkbdllds.add(list.get(i).getLjgkbdl());
				}
				if(list.get(i).getLjcjdl()!=null){
					totalljcjdllds=totalljcjdllds.add(list.get(i).getLjcjdl());
				}
				if(list.get(i).getLjyysdl()!=null){
					totalljyysdllds=totalljyysdllds.add(list.get(i).getLjyysdl());
				}
				
			}
			//10kv
			
		}
		if(list.get(0)!=null){
		list.get(0).setTotalbygkbdls(totalbygkbdls);
		list.get(0).setTotalbycjdls(totalbycjdls);
		list.get(0).setTotalbyyysdls(totalbyyysdls);
		list.get(0).setTotalljgkbdls(totalljgkbdls);
		list.get(0).setTotalljcjdls(totalljcjdls);
		list.get(0).setTotalljyysdls(totalljyysdls);
		//0.4kv
		list.get(0).setTotalbygkbdllds(totalbygkbdllds);
		list.get(0).setTotalbycjdllds(totalbycjdllds);
		list.get(0).setTotalbyyysdllds(totalbyyysdllds);
		list.get(0).setTotalljgkbdllds(totalljgkbdllds);
		list.get(0).setTotalljcjdllds(totalljcjdllds);
		list.get(0).setTotalljyysdllds(totalljyysdllds);
		}
		return list;
	}
	/*
	 * 添加根据供电站不同分组合计的10kV合计数据
	 */
	
	public List<Sldskvcjyyssltjbtb> addGroupTotalData(List<Sldskvcjyyssltjbtb> list){
		List<Sldskvcjyyssltjbtb> groupdataList = new ArrayList<Sldskvcjyyssltjbtb>();
		//存放供电站名称的set，使用set是为了去重
		Set<String> gdzSet= new HashSet<String>();
		//遍历list将供电站名称取出放入set当中
		for(int i=0;i<list.size();i++){
			gdzSet.add(list.get(i).getGdz());
		}
		for(String gdz:gdzSet){
			BigDecimal bygkbdl=new BigDecimal(0);
			BigDecimal bycjdl=new BigDecimal(0);
			BigDecimal byyysdl=new BigDecimal(0);

		    BigDecimal ljgkbdl=new BigDecimal(0);
		    BigDecimal ljcjdl=new BigDecimal(0);
		    BigDecimal ljyysdl=new BigDecimal(0);
		    //循环累加
		    for(int i=0;i<list.size();i++){
		    	if(list.get(i).getGdz().equals(gdz)&&!list.get(i).getXlmc().startsWith("0.4kV")){
		    		if(list.get(i).getBygkbdl()!=null){
		    			bygkbdl=bygkbdl.add(list.get(i).getBygkbdl());
		    		}
		    		if(list.get(i).getBycjdl()!=null){
		    			bycjdl=bycjdl.add(list.get(i).getBycjdl());
		    		}
		    		if(list.get(i).getByyysdl()!=null){
		    			byyysdl=byyysdl.add(list.get(i).getByyysdl());
		    		}
		    		if(list.get(i).getLjgkbdl()!=null){
		    			ljgkbdl=ljgkbdl.add(list.get(i).getLjgkbdl());
		    		}
		    		if(list.get(i).getLjcjdl()!=null){
		    			ljcjdl=ljcjdl.add(list.get(i).getLjcjdl());
		    		}
		    		if(list.get(i).getLjyysdl()!=null){
		    			ljyysdl=ljyysdl.add(list.get(i).getLjyysdl());
		    		}
		    	}
		    }
		    Sldskvcjyyssltjbtb groupTotal = new Sldskvcjyyssltjbtb();
		    groupTotal.setGdz(gdz);
		    groupTotal.setXlmc("10kV合计");
		    groupTotal.setBygkbdl(bygkbdl);
		    groupTotal.setBycjdl(bycjdl);
		    groupTotal.setByyysdl(byyysdl);
		    groupTotal.setLjgkbdl(ljgkbdl);
		    groupTotal.setLjcjdl(ljcjdl);
		    groupTotal.setLjyysdl(ljyysdl);
		    groupdataList.add(groupTotal);
		}
		list.addAll(groupdataList);
		return list;
		
	}
	
	public static List<Sldskvcjyyssltjbtb> testdata(){
		List<Sldskvcjyyssltjbtb> list = new LinkedList<Sldskvcjyyssltjbtb>();
		Sldskvcjyyssltjbtb s1 = new Sldskvcjyyssltjbtb();
		s1.setGdz("城区所");
		s1.setXlmc("兖兰西线");
		s1.setBygkbdl(new BigDecimal(348120));
		s1.setBycjdl(new BigDecimal(341430));
		s1.setByyysdl(new BigDecimal(349922));
		s1.setLjgkbdl(new BigDecimal(2406120));
		s1.setLjcjdl(new BigDecimal(2315009));
		s1.setLjyysdl(new BigDecimal(2365417));
		Sldskvcjyyssltjbtb s2 = new Sldskvcjyyssltjbtb();
		s2.setGdz("城区所");
		s2.setXlmc("兖兰东线");
		s2.setBygkbdl(new BigDecimal(1114320));
		s2.setBycjdl(new BigDecimal(1088355));
		Sldskvcjyyssltjbtb s3 = new Sldskvcjyyssltjbtb();
		s3.setGdz("金屯所");
		s3.setXlmc("缺屯线");
		s3.setBygkbdl(new BigDecimal(348120));
		s3.setBycjdl(new BigDecimal(341430));
		Sldskvcjyyssltjbtb s4 = new Sldskvcjyyssltjbtb();
		s4.setGdz("金屯所");
		s4.setXlmc("土山桥线");
		s4.setBygkbdl(new BigDecimal(1114320));
		s4.setBycjdl(new BigDecimal(1088355));
		
		Sldskvcjyyssltjbtb s5 = new Sldskvcjyyssltjbtb();
		s5.setGdz("城区所");
		s5.setXlmc("0.4kV合计");
		s5.setBygkbdl(new BigDecimal(348520));
		s5.setBycjdl(new BigDecimal(341430));
		s5.setByyysdl(new BigDecimal(349922));
		s5.setLjgkbdl(new BigDecimal(2406120));
		s5.setLjcjdl(new BigDecimal(341430));
		s5.setLjyysdl(new BigDecimal(349922));
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		return list;
		
	}
	public static List<Sldskvcjyyssltjbtb> test(){
		SldskvcjyyssltjbtbService rs = new SldskvcjyyssltjbtbService();
		List<Sldskvcjyyssltjbtb> alist =rs.getSldskvcjyyssltjbtbData();
		return alist;
	}

}
