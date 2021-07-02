package Reports;

import java.io.IOException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * contains all the methods to show the logs on console 
 * and save the logs in LogFile.txt of each run.

 */
public class Log {
		//Initialize Log4j instance
		private static final Logger Log =  LogManager.getLogger(Log.class);
		//Info Level Logs
		public static void info (String message) {
			Log.info(message);
		}
		//Warn Level Logs
		public static void warn (String message) {
			Log.warn(message);
		}
		//Error Level Logs
		public static void error (String message) {
			Log.error(message);
		}
		//Fatal Level Logs
		public static void fatal (String message) {
			Log.fatal(message);
		}
		//Debug Level Logs
		public static void debug (String message) {
			Log.debug(message);
		}

}
