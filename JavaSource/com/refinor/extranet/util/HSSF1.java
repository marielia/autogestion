package com.refinor.extranet.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.dev.HSSF;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HSSF1 {

	private HSSFWorkbook workbook=new HSSFWorkbook();
	private FileInputStream inputfile;
	private FileOutputStream outputfile;
	
	public HSSF1(){}

		public HSSFWorkbook getWorkbook(String s){
			try{
				inputfile=new FileInputStream(s);
				workbook=new HSSFWorkbook(inputfile);
			}catch(Exception e ){
				System.out.println("error!!!!!!!!!!!!!!!!"+e.getMessage());
			}
			return workbook;
		}

		public FileInputStream getInputfile() {
			return inputfile;
		}

		public void setInputfile(FileInputStream inputfile) {
			this.inputfile = inputfile;
		}

		public FileOutputStream getOutputfile() {
			return outputfile;
		}

		public void setOutputfile(FileOutputStream outputfile) {
			this.outputfile = outputfile;
		}
		
		

		public HSSFWorkbook getWorkbook() {
			return workbook;
		}

		public void setWorkbook(HSSFWorkbook workbook) {
			this.workbook = workbook;
		}

		


}
