package com.smf.pft.util;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jdk.net.NetworkPermission;


public class BatchExe {
	
	static String OUTPUT_TYPE="generate-png";
	static String OUTPUT_FORMAT="png";
	static String INPUT_DIR_ARG="D:/Performance_Testing/SVN/SMF-Performance/Utility/build/Results/archive/";
	static String INPUT_FILE_NAME_ARG="Report_L3User_Login_Concurrent.jtl";
	static String INP_RPT_NAME="BytesThroughputOverTime";//BytesThroughputOverTime
	public static void main(String[] args) {
		
			File pbFIle= new File("D:/Software/apache-jmeter-2.13/lib/ext/JMeterPluginsCMD.bat");
			System.out.println(pbFIle.getParent()+"==>"+pbFIle.getAbsolutePath());
			
			if("SynthesisReport".equals(INP_RPT_NAME))
			{
				OUTPUT_TYPE="generate-csv";
				OUTPUT_FORMAT="csv";
			}
			
			ExecutorService executorService = Executors.newFixedThreadPool(10);
			CompletionService<String> taskCompletionService=new ExecutorCompletionService<String>(executorService);
			try {
				List<Callable<String>> callables = createCallableList();
				for (Callable<String> callable : callables) {
					taskCompletionService.submit(callable);
				}
				for (int i = 0; i < callables.size(); i++) {
					Future<String> result =  taskCompletionService.take();	
					System.out.println("Callable Task Returned"+result.get()); 
				}
			} catch (InterruptedException e) {
				// no real error handling. Don't do this in production!
				e.printStackTrace();
			} catch (ExecutionException e) {
				// no real error handling. Don't do this in production!
				e.printStackTrace();
			}
			executorService.shutdown();
			 
	}
	
	
	public static List<Callable<String>> createCallableList()
	{
		List<String> fileList=listFiles(INPUT_DIR_ARG);
		List<Callable<String>> callables=new ArrayList<>();
		for(String tempFileName:fileList)
		{
			CreatepProcessTask tempTask =new CreatepProcessTask(INPUT_DIR_ARG, tempFileName, INP_RPT_NAME, OUTPUT_TYPE, OUTPUT_FORMAT);
			callables.add(tempTask);
		}
		System.out.println("Generated callables"+callables.size());
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
