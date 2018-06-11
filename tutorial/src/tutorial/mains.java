package tutorial;

import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;



public class mains {	
	public static void main(String args[]) throws Exception
	{
		
		Logger logger = Logger.getLogger("GLOBAL");
		logger.setUseParentHandlers(false);
		String filename = readfile.filename;
	    FileHandler filehandler = new FileHandler(filename,true);
		logger.addHandler(filehandler);
		logger.setLevel(Level.ALL);
		SimpleFormatter formatter = new SimpleFormatter();  
	    filehandler.setFormatter(formatter);
	    
	    
	    try {
	    	readfile.read(args[0]);
	    }catch(Exception e) {
	    	logger.severe(e.getMessage());
	    }finally {
	    	logger.info("End program");
	    }
	}
	

}


	class readfile {
		static String filename = "D:/logfiles/logs.txt";
		
		public static void read(String s) throws IOException {
		
			
			Configuration conf = new Configuration();
			Path path = new Path(s);
			conf.addResource(new Path("/hdc/hadoop-2.7.3/hadoop-2.7.3/etc/hadoop/core-site.xml"));
			conf.addResource(new Path("/hdc/hadoop-2.7.3/hadoop-2.7.3/etc/hadoop/hdfs-site.xml"));
			FileSystem fileSystem = FileSystem.get(conf);
	
			FSDataInputStream in = fileSystem.open(path);
			OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filename),true));
    
			byte[] b = new byte[1024];
			int numBytes = 0;
			while ((numBytes = in.read(b)) > 0) {
				out.write(b, 0, numBytes);
			}
			in.close();
			out.close();
			fileSystem.close();
    
	}
}


















/*try {
fileSystem.exists(path); 
logger.info("File does exist");
}catch(Exception e) {
	logger.log(Level.SEVERE, e.getMessage(), e);
	logger.info("ERROR HAS BEEN LOGGED, CHECK LOG FILE");
}*/