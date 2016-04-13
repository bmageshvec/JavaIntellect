package com.smf.pft.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Level3UserDataUtil {
	
	
	public static void main(String[] args) throws IOException {
		long mobileno=7211412347L;
		StringBuffer tempbuf=null;
		String firstName="L3SMFUser";
		String lnameappender=",ChennaiOne,ECR,";
		String email="L3SMFUsermailId";
		String address=",Customer,Chennai,600094,TN,IN";
		FileWriter fwr= new FileWriter("D://Performance_Testing//SVN//SMF-Performance//Utility//Config//L3UserCreate.csv");
		BufferedWriter bwr = new BufferedWriter(fwr);
		for(int i=11001;i<=12000;i++)
		{
			tempbuf=new StringBuffer("Level 3,");
			tempbuf.append(firstName+i);
			tempbuf.append(lnameappender).append(email+i).append("@gmail.com,");
			tempbuf.append(mobileno++).append(address);
			bwr.write(tempbuf.toString());
			bwr.newLine();
		}
		bwr.flush();
		bwr.close();
		System.out.println(++mobileno);
	}

}
