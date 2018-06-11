package writing;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class writepart {
	
	static FileSystem fs;
	static InputStream in;
	static OutputStream out;
	
	public static void write(String s,String d) throws IOException  {
		
	Logger logger1 =Logger.getLogger(writepart.class);
	//PropertyConfigurator.configure("log4j.properties");
	Configuration conf = new Configuration();
	//Path pathout = new Path(URI.create(args[1]));
	String localsrc  = s ;
	String dst= d;
	
	conf.addResource(new Path("/hdc/hadoop-2.7.3/hadoop-2.7.3/etc/hadoop/core-site.xml"));
	conf.addResource(new Path("/hdc/hadoop-2.7.3/hadoop-2.7.3/etc/hadoop/hdfs-site.xml"));
	
	
	
	try {
		fs = FileSystem.get(URI.create(dst),conf);
	} catch (IOException e) {
		logger1.error(e.getMessage(),e);
	}
	
	
	try {
		in = new BufferedInputStream(new FileInputStream(localsrc));
	} catch (FileNotFoundException e) {
		logger1.error(e.getMessage(),e);
	}
	
	try {
		out = fs.create(new Path(dst));
	} catch (IOException e) {
		
		logger1.error(e.getMessage(),e);
	}
	
	IOUtils.copyBytes(in, out, 4096,true);
	in.close();
	out.close();
	fs.close();
	
	}
}
