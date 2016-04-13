package com.smf.pft.util;

import java.io.File;
import java.io.IOException;

public class BatchExe {
	
	public static void main(String[] args) {
		Process p = null;
		try {
			File pbFIle= new File("D:/Software/apache-jmeter-2.13/lib/ext/JMeterPluginsCMD.bat");
			System.out.println(""+pbFIle.getAbsolutePath());
			ProcessBuilder pb = new ProcessBuilder(pbFIle.getAbsolutePath());
			pb.directory(new File("D:/Software/apache-jmeter-2.13/lib/ext/"));
			p = pb.start();
			int exitStatus = p.waitFor();
			System.out.println("Exited System "+exitStatus);
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
