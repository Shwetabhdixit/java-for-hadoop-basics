package com.training.postgreSQL;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.postgresql.*;

public class sql {
	static Connection c = null;
	static Statement stmt = null;
   public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
     
	   try {
    	 
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/orc1?currentSchema=employees",
            "postgres", "Rolta@123");
         stmt = c.createStatement();
         String sql;
         /*String sql = "CREATE TABLE COMPANY10 " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " AGE            INT     NOT NULL, " +
            " ADDRESS        CHAR(50), " +
            " SALARY         REAL)";
         stmt.executeUpdate(sql);*/
         try {/*
         sql = "INSERT INTO COMPANY10 (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (10, 'shwetabh', 32, 'California', 20000.00 );";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO COMPANY10 (ID,NAME,AGE,ADDRESS,SALARY) "+ "VALUES (20, 'Shwet', 23, 'California', 20000.00 );";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO COMPANY10 (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (11, 'sandesh', 12, 'India', 30000.00 );";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO COMPANY10 (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (12, 'supriya', 52, 'England', 40000.00 );";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO COMPANY10 (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (13, 'seema', 34, 'NewJersey', 60000.00 );";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO COMPANY10 (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (14, 'saroj', 62, 'Mumbai', 440000.00 );";
         stmt.executeUpdate(sql);*/
         ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY10");
         String s = "/test/files.csv";
         Configuration conf = new Configuration();

    	 conf.addResource(new Path("/hdc/hadoop-2.7.3/hadoop-2.7.3/etc/hadoop/core-site.xml"));
		 conf.addResource(new Path("/hdc/hadoop-2.7.3/hadoop-2.7.3/etc/hadoop/hdfs-site.xml"));
         FileSystem fs  = FileSystem.get(URI.create(s),conf);
    	
		
		 OutputStream out= fs.append(new Path(s));

		 PrintStream p  = new PrintStream(out);
		 System.setOut(p);
         while(rs.next())
         {
        	 int id = rs.getInt(1);
        	 String name = rs.getString(2);
        	 int age = rs.getInt(3);
        	 String address  = rs.getString(4);
        	 Float salary = rs.getFloat(5);
        	 System.out.println(id + "      "+ name+ "      "+age+ "      "+address+ "      "+salary);
        	 
         }
         stmt.close();}catch(Exception e)
         {
        	 System.out.println(e.getMessage());
         }
         
         c.close();
         
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.out.println("error");
      }
      System.out.println("Opened database successfully");
   }
}