package com.smf.rpt.util;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class CreatepProcessTask  implements Callable<String>
{
	private static final Logger LOGGER = Logger.getLogger(ReportGenerator.class.getName());
	
	String INPUT_DIR_ARG;
	String INPUT_FILE_NAME_ARG;
	String INP_RPT_NAME;
	String OUTPUT_TYPE ;
	String OUTPUT_FORMAT;
	String OUTPUT_FOLDER_NAME;
	
	
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
    	this.OUTPUT_FOLDER_NAME=INPUT_FILE_NAME_ARG.replaceAll(".jtl", "");
	}


	@Override
	public String call() throws Exception {
		
		try
    	{
			LOGGER.info("Inside call OUTPUT_FOLDER_NAME========================>"+OUTPUT_FOLDER_NAME);
		    File pbFIle= new File(ReportGenerator.ARG_BATCH_CMD_PATH);
		    Process p = null;
			ProcessBuilder pb = new ProcessBuilder(pbFIle.getAbsolutePath(),INPUT_DIR_ARG,INPUT_FILE_NAME_ARG,INP_RPT_NAME,OUTPUT_TYPE,OUTPUT_FORMAT,OUTPUT_FOLDER_NAME);
			// Redirect any output (including error) to a file. This avoids deadlocks
		    // when the buffers get full. 
		    pb.redirectErrorStream(true);
		    pb.redirectOutput(Redirect.INHERIT);
			pb.directory(new File(pbFIle.getParent()));
			p = pb.start();
			int exitStatus = p.waitFor();
			LOGGER.info("Exited System "+exitStatus);
			return String.valueOf(exitStatus);
    	}
		catch (IOException | InterruptedException ex) {
			LOGGER.severe("Exception Caught"+ex.getMessage()+"with root cause"+ex.getCause());
			ex.printStackTrace();
		}
		return "-1";
	}
	

}
