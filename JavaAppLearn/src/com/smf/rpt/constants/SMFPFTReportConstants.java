package com.smf.rpt.constants;

public interface SMFPFTReportConstants 
{
	public static final String CSVREPORTS_FULLLIST[] ={"SynthesisReport","AggregateReport"};
	//public static final String ALL_REPORTS[]={"ResponseTimesDistribution"};
	public static final String ALL_REPORTS[]={"SynthesisReport","TimesVsThreads","HitsPerSecond","ThroughputVsThreads","TransactionsPerSecond","ThreadsStateOverTime","ResponseTimesDistribution"};
	public static final String PNGREPORTS[]={"TimesVsThreads","HitsPerSecond","LatenciesOverTime","ThroughputVsThreads","TransactionsPerSecond","ThreadsStateOverTime","ResponseTimesDistribution","ResponseTimesOverTime"};
	String PNG_REPORT_TYPE="generate-png";
	String PNG_REPORT_FORMAT="png";
	String CSV_REPORT_TYPE="generate-csv";
	String CSV_REPORT_FORMAT="csv";
	int THREAD_POOL_SIZE=10;

}
