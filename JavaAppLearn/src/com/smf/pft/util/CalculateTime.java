package com.smf.pft.util;

import java.util.concurrent.TimeUnit;

public class CalculateTime { 
	
public static void main(String[] args) {
	long startTime=1460627292428L;
	long endTime=1460627509243L;
	//System.out.println("Diiff"+(endTime-startTime));
	long millis=endTime-startTime;
	String timeop=String.format("%d min, %d sec", 
		    TimeUnit.MILLISECONDS.toMinutes(millis),
		    TimeUnit.MILLISECONDS.toSeconds(millis) - 
		    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
		);
	System.out.println("Timeop"+timeop);
}
	
}
