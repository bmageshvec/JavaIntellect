package com.smf.rpt.util;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import com.smf.rpt.constants.SMFPFTReportConstants;

import jdk.net.NetworkPermission;


public class ReportGenerator {
	
	static String ARG_INPUT_DIR_ARG="D:/Performance_Testing/SVN/SMF-Performance/Utility/build/Results";
	static String ARG_BATCH_CMD_PATH="D:/Software/apache-jmeter-2.13/lib/ext/JMeterPluginsCMD.bat";
	private static final Logger LOGGER = Logger.getLogger(ReportGenerator.class.getName());

	//Prg args: D:/Performance_Testing/SVN/SMF-Performance/Utility/build/Results/archive/ D:/Software/apache-jmeter-2.13/lib/ext/JMeterPluginsCMD.bat
	
	
	public static void main(String[] args) throws Exception {
		
			
			if(args.length>=2)
			{
				LOGGER.info("Printing Arguments Passed length"+args.length);
				for(String tempargs:args)
					LOGGER.info("tempargs"+tempargs);
				ARG_INPUT_DIR_ARG=args[0];
				ARG_BATCH_CMD_PATH=args[1];
			}
			else
			{
				//throw new  Exception ("Please pass input Directory path and command path");
			}
			
			ExecutorService executorService = Executors.newFixedThreadPool(SMFPFTReportConstants.THREAD_POOL_SIZE);
			//ExecutorService executorService = Executors.newSingleThreadExecutor();
			CompletionService<String> taskCompletionService=new ExecutorCompletionService<String>(executorService);
			try {
				List<Callable<String>> callables = createCallableReportList();
				long entryTime=System.currentTimeMillis();
				for (Callable<String> callable : callables) {
					taskCompletionService.submit(callable);
				}
				Thread.sleep(5000);
				for (int i = 0; i < callables.size(); i++) {
					Future<String> result =  taskCompletionService.take();	
					LOGGER.info("Finished Executing Task "+(i+1)+" with Exit Status "+result.get()); 
				}
				LOGGER.info("Total Time Take to complete "+(System.currentTimeMillis()-entryTime));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			executorService.shutdown();
	}
	
	
	public static List<Callable<String>> createCallableReportList()
	{
		List<String> fileList=listFiles(ARG_INPUT_DIR_ARG);
		List<Callable<String>> callables=new ArrayList<>();
		String OUTPUT_TYPE=SMFPFTReportConstants.PNG_REPORT_TYPE;
		String OUTPUT_FORMAT=SMFPFTReportConstants.PNG_REPORT_FORMAT;
		for(String tempInpFileName:fileList)
		{
			for(String temp_rptname:SMFPFTReportConstants.ALL_REPORTS)
			{
				OUTPUT_TYPE=SMFPFTReportConstants.PNG_REPORT_TYPE;
				OUTPUT_FORMAT=SMFPFTReportConstants.PNG_REPORT_FORMAT;
				if(Arrays.asList(SMFPFTReportConstants.CSVREPORTS_FULLLIST).contains(temp_rptname))
				{
					OUTPUT_TYPE=SMFPFTReportConstants.CSV_REPORT_TYPE;
					OUTPUT_FORMAT=SMFPFTReportConstants.CSV_REPORT_FORMAT;
				}
				CreatepProcessTask tempTask =new CreatepProcessTask(ARG_INPUT_DIR_ARG, tempInpFileName, temp_rptname, OUTPUT_TYPE, OUTPUT_FORMAT);
				callables.add(tempTask);
			}
		}
		LOGGER.info("Generated callables =====================================================================>"+callables.size());
		return callables;
	}
	
	 public static List<String> listFiles(String directoryName){
	        File directory = new File(directoryName);
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	        List<String> fileList=new ArrayList<String>();
	        for (File file : fList){
	            if (file.isFile() && "jtl".equals(getExtension(file.getName()))){
	                System.out.println(file.getName());
	                fileList.add(file.getName());
	            }
	        }
	        return fileList;
	    }
	 
	 public static String getExtension(String fileName)
	 {
		 if(fileName.lastIndexOf(".")!=-1 && fileName.lastIndexOf(".")!=0)
			 return fileName.substring(fileName.lastIndexOf(".")+1);
		 return "";
	 }

}
