# java-for-hadoop-basics
Some basic java programs for simple operations on Hadoop , like read, write , word count, etc.

The package writing contains classes which help write any text file on your system to hdfs and also maintain a log file for success or errors that may arrive during the execution of the porgram. Logging is done using log4j.

The package tutorial contains a class mains which reads any file present on hdfs and can store the read content into a file on the system. Here the file to which data is read is the same as the log file. Here logging has been implemented using the logger package of java.

The package worcount is a conventional implementation of wordocount using mapreduce.
