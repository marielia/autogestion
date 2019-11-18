package com.refinor.extranet.util.comparator;

import java.util.Comparator;

import com.refinor.extranet.to.MReciboTO;

public class ReciboTOComparator implements Comparator {

	public ReciboTOComparator() {
		// TODO Auto-generated constructor stub
	}

	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		MReciboTO ob1 = (MReciboTO) o1;
		MReciboTO ob2 = (MReciboTO) o2;
        return ob1.getNumero().compareTo(ob2.getNumero());
	}

}
