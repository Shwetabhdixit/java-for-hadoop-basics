package writing;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
//import org.apache.log4j.PropertyConfigurator;


public class writetohdfs 
{
	
	
	public static void main(String args[]) throws IOException  {
		
		
		Logger logger =Logger.getLogger(writetohdfs.class);
		String filePath = "D:/logfiles/logs.txt";
	    PatternLayout layout = new PatternLayout("%-5p %d %m%n");
		RollingFileAppender appender = new RollingFileAppender(layout, filePath);
	    appender.setName("myFirstLog");
	    appender.setMaxFileSize("1MB");
	    appender.activateOptions();
	    Logger.getRootLogger().addAppender(appender);
	    
	    
		//PropertyConfigurator.configure("log4j.properties");
		logger.info("Logging has been started, we are using log 4j ");
		
		logger.info("Entering the main method");
		
		try {
			writepart.write(args[0],args[1]);
			logger.info("Exceuted successfully");
		} catch (IOException e) {
			logger.info("IO exception thrown by class writepart");
			logger.error(e.getMessage(),e);
		}
	}	
}
