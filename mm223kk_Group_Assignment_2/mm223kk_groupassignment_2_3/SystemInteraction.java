package mm223kk_groupassignment_2_3;

/*
 * File:	SystemInteraction.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*Sami Mwanje, mm223kk*
 * Date: 	November 2020
 * Just created a seperate class-file for the first part of the code to make it more clear.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SystemInteraction {
	
	/**
	 * This method is used to create a new directory. It is called with a string of the directory name.
	 * <br> It also idicates if the direcoty already exists.
	 * @param dir - String used for the name of the directory.
	 * @throws IOException - If something goes wrong
	 */
	public void createDir(String dir) throws IOException {
		
		File directory = new File(dir);
		
		// Check and print if directory already exist.
		if(!directory.mkdirs())
		System.out.println("Directory exists already.");
		
	}
	
	/**
	 * This method is used to create a new file. It is invoked with a string which is the file name.
	 * @param name - String used for the name of the new file.
	 * @throws IOException - If something goes wrong.
	 */
	public void createFile(String name) throws IOException {
		
		// Create new file.
		File file = new File(name);
		file.createNewFile();
		
	}
	
	/**
	 * This method return the current time when called. It return hour,minute,second, millisecond.
	 * @return - Return the current time as a string with the format hh-mm-s-SSS.
	 */
	public String getTime(){	
		
		String date = ( new SimpleDateFormat( "hh-mm-ss-SSS" ) ).format( new Date() ).toString();
	    return date;
			}
	
	
	
	/**
	 * This method is used to write a new line in a file. It uses BufferedWriter with the string Wrtier.
	 * @param text - What the line will contain
	 * @param dir - The path of the file to write a new line in.
	 * @throws IOException - If something goes wrong.
	 */
	public void writeLine(String text, String dir) throws IOException{	
		
		BufferedWriter writeLine = null;
		
		FileWriter stream = new FileWriter(dir, true); 
		
		writeLine = new BufferedWriter(stream);
		writeLine.write(text+"\n");		// Jumps to a new line after the line has been written.
		writeLine.close();				// Closes the writer.
	}
	
	
	/**
	 * This method is used to return how many files that contains inside a directory.
	 * @param dir - A string of the directory location.
	 * @return - Returns the number of files in direcoty as an integer.
	 */
	public int checkFiles(String dir) {	
		return new File(dir).list().length;
		
	}
	
}
