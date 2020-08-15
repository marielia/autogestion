package com.asecor.util.seguridad; 

import java.util.Random;


public class PassUtil {
	
	public String generar() {
		Random rand = new Random();
		int length = rand.nextInt(6) + 6;
		char[] password = new char[length];
		
		for (int x = 0; x < length; x++) {
			int randDecimalAsciiVal = rand.nextInt(93) + 33;
			password[x] = (char) randDecimalAsciiVal;
		}
		return String.valueOf(password);
	}

}
