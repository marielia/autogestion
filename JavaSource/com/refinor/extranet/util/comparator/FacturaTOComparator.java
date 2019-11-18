package com.refinor.extranet.util.comparator;

import java.util.Comparator;

import com.refinor.extranet.to.MFacturaVTO;

public class FacturaTOComparator implements Comparator {

	
	
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		MFacturaVTO ob1 = (MFacturaVTO) o1;
		MFacturaVTO ob2 = (MFacturaVTO) o2;
        return ob2.getFecha().compareTo(ob1.getFecha());
	}

}
