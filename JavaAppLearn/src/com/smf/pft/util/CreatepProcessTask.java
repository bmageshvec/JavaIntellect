package com.smf.pft.util;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.Callable;

public class CreatepProcessTask  implements Callable<String>
{
	String INPUT_DIR_ARG;
	String INPUT_FILE_NAME_ARG;
	String INP_RPT_NAME;
	String OUTPUT_TYPE ;
	String OUTPUT_FORMAT;
	String OUTPUT_FILENAME;
	
	public CreatepProcessTask()
	{
		
	}
	
	public CreatepProcessTask(String INPUT_DIR_ARG,String INPUT_FILE_NAME_ARG,String INP_RPT_NAME,String OUTPUT_TYPE,String OUTPUT_FORMAT)
	{
		this.INPUT_DIR_ARG=INPUT_DIR_ARG;
    	this.INPUT_FILE_NAME_ARG=INPUT_FILE_NAME_ARG;
    	this.INP_RPT_NAME=INP_RPT_NAME;
    	this.OUTPUT_TYPE=OUTPUT_TYPE;
    	this.OUTPUT_FORMAT=OUTPUT_FORMAT;
    	this.OUTPUT_FILENAME=INPUT_FILE_NAME_ARG.replaceAll(".jtl", "");
	}


	@Override
	public String call() throws Exception {
		
		try
    	{
			System.out.println("Inside call ========================>"+OUTPUT_FILENAME);
		    final File outputFile = new File(String.format("output\\output_%tY%<tm%<td_%<tH%<tM%<tS.txt", System.currentTimeMillis()));
		    File pbFIle= new File("D:/Software/apache-jmeter-2.13/lib/ext/JMeterPluginsCMD.bat");
		    Process p = null;
			ProcessBuilder pb = new ProcessBuilder(pbFIle.getAbsolutePath(),INPUT_DIR_ARG,INPUT_FILE_NAME_ARG,INP_RPT_NAME,OUTPUT_TYPE,OUTPUT_FORMAT,OUTPUT_FILENAME);
			// Redirect any output (including error) to a file. This avoids deadlocks
		    // when the buffers get full. 
		    pb.redirectErrorStream(true);
		    pb.redirectOutput(Redirect.INHERIT);
			pb.directory(new File(pbFIle.getParent()));
			p = pb.start();
			int exitStatus = p.waitFor();
			System.out.println("Exited System "+exitStatus);
			return String.valueOf(exitStatus);
    	}
		catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
		return "-1";
	}
	

}
