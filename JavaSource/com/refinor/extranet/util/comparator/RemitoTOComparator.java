package com.refinor.extranet.util.comparator;

import java.util.Comparator;


import com.refinor.extranet.to.RemitoTO;

public class RemitoTOComparator implements Comparator {

	public RemitoTOComparator() {
		// TODO Auto-generated constructor stub
	}

	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		RemitoTO ob1 = (RemitoTO) o1;
		RemitoTO ob2 = (RemitoTO) o2;
        return ob2.getFecha().compareTo(ob1.getFecha());
	}

}
