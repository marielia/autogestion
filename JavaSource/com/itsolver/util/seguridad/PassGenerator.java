package com.itsolver.util.seguridad;

import java.util.Random;

public class PassGenerator {

                final	String[]    UPPER	= {"A","B","C","D","E","F","G","H","I","J","K",
"L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
                final	String[]    LOWER	= {"a","b","c","d","e","f","g","h","i","j","k",
"l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
                final	String[]    DIGIT	= {"0","1","2","3","4","5","6","7","8","9"};
                private Random      genNum 	= new Random();
                private int         arrTam	= 0;
                private int         contL	= 0;
                private int         contU	= 0;
                private int         contD	= 0;
                private String      res         = "";
                private String      pass;
                private int         iGeRaNu ;
                private String      sTam ;
                private String      sChar;

        public PassGenerator() {
        }

        public String generateIt() {
                pass	= "";
                for (int i=0; i<10; i++){
                        if ((contL == 2) || (contU) == 1) {
                                arrTam = 9;
                                //Adding a digit to password
                                pass = pass + getDigit(genNum());
                                contL = 0;
                                contU = 0;
                        }else {
                                arrTam = 26;
                                if(genNum() % 2 == 0) {
                                        //Adding a Upper character to password
                                        pass = pass + getUpper(genNum());
                                        contU ++;
                                } else {
                                        //Adding a Lower character to password
                                        pass = pass + getLower(genNum());
                                        contL ++;
                                }
                        }
                }

                return pass.toLowerCase();

        }

        int genNum() {
                iGeRaNu = genNum.nextInt() % arrTam;
                sTam = new Integer(iGeRaNu).toString();
                sChar = new String(String.valueOf(sTam.charAt(0)));
                //Converting the possible negative number to positive
                if ( sChar.equals("-")){iGeRaNu = iGeRaNu * -1;}
                return iGeRaNu;
        }

        String getUpper(int val) {
                res = UPPER[val];
                return res;

        }

        String getLower(int val) {
                res = LOWER[val];
                return res;
        }

        String getDigit(int val) {
                res = DIGIT[val];
                return res;
        }

}
