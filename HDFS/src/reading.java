import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.mapred.FileInputFormat;
//import org.apache.hadoop.mapred.FileOutputFormat;
//import org.apache.hadoop.mapred.JobConf;


public class reading{
	
	public static void hvbj(String args[]) throws IOException
	{
		Configuration conf = new Configuration();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the file path");
		String filepath = br.readLine();
		
		Path path = new Path(filepath);
	    FileSystem fs = path.getFileSystem(conf);
	    FSDataInputStream inputStream = fs.open(path);
	    System.out.println(inputStream.available());
	    fs.close();	
		
	}
	

}
