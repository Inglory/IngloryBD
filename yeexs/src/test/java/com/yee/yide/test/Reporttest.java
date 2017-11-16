package com.yee.yide.test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.yee.yide.modules.report.entity.Lsdybysfzsdltjb;

public class Reporttest {
	public static Collection<?> getBeanCollection() {
	    List<Lsdybysfzsdltjb> list = new LinkedList<Lsdybysfzsdltjb>();
        Lsdybysfzsdltjb l1 = new Lsdybysfzsdltjb();
        l1.setDydj("110kv");
        l1.setXlmc("祥梁线");
        Lsdybysfzsdltjb l2 = new Lsdybysfzsdltjb();
        l2.setDydj("110kv");
        l2.setXlmc("110kV肥祥线");
        Lsdybysfzsdltjb l3 = new Lsdybysfzsdltjb();
        l3.setDydj("110kv");
        l3.setXlmc("110kV张梁线");
        Lsdybysfzsdltjb l4 = new Lsdybysfzsdltjb();
        l4.setDydj("35kv");
        l4.setXlmc("郭马线");
        Lsdybysfzsdltjb l5 = new Lsdybysfzsdltjb();
        l5.setDydj("35kv");
        l5.setXlmc("萌新线");
        list.add(l1);
        list.add(l2);
        list.add(l3);
        list.add(l4);
        list.add(l5);
        return list;
        
    }

}
