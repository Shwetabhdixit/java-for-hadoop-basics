package kam;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class readfile {
	
	public static void readd(String uri) throws IOException {
		
		
		
		 
		Configuration conf = new Configuration();
		 
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FSDataInputStream in = null;
		 
		try {
		in = fs.open(new Path(uri));
		
		IOUtils.copyBytes(in, System.out, 4096, false);
		 
		System.out.println("End Of file: HDFS file read complete");
		 
		} finally {
		IOUtils.closeStream(in);
		}
	}

}
