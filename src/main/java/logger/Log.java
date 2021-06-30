package logger;

import org.apache.log4j.*;

import java.io.IOException;

/**
 * contains all the methods to show the logs on console 
 * and save the logs in LogFile.txt of each run.

 */
public class Log
{
	
	private static final Logger LOGGER = Logger.getLogger("logger");
	private static PatternLayout layout = new PatternLayout("%d{dd MMM yyyy HH:mm:ss} %5p %c{1} - %m%n");
	private static FileAppender appender;
	private static ConsoleAppender consoleAppender;
	
	static
	{
		try 
		{
			consoleAppender = new ConsoleAppender(layout, "System.out");
			appender= new FileAppender(layout,"LogFile.txt",true);
		}
		catch (IOException exception) 
		{
			exception.printStackTrace();
		}
	}
	
	/**
	 * method to display errors in log.
	 * @param className name of class in which error occurred.
	 * @param methodName name of method in which error occurred.
	 * @param exception stack trace of exception
	 */
	public static void error (String className,String methodName,String exception)
	{	
		LOGGER.addAppender(appender);
		LOGGER.setLevel((Level) Level.INFO);
		LOGGER.error("ClassName :"+className);
		LOGGER.error("MethodName :"+methodName );
		LOGGER.error("Exception :" +exception);
		LOGGER.error("-----------------------------------------------------------------------------------");
	}
	
	/**
	 * method to display information in logs
	 * @param message message to be displayed
	 */
	public static void info (String message){
		consoleAppender.setName("Console");
		LOGGER.addAppender(consoleAppender);
		LOGGER.addAppender(appender);
		LOGGER.setLevel((Level) Level.INFO);
		LOGGER.info(message);
	}
	
}