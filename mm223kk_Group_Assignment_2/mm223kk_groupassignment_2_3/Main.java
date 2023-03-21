package mm223kk_groupassignment_2_3;

/*
 * File:	Main.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*Sami Mwanje, mm223kk*
 * Date: 	November 2020
 * Just created a seperate class-file for the first part of the code to make it more clear.
 */

import java.io.IOException;
public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
			SystemInteraction systemInteraction = new SystemInteraction(); // Create new systemInteraction object, used for help.
			
			// Settings :
			String directoryName = "test-directory";		// New directory name 
			String currentTime;			// The current Time. 
			int totalFiles = 500;		// Total files to create.
			int lines = 10000;			// Total lines in file.
			String start = systemInteraction.getTime(); // Sets start time.
			
			
				
				systemInteraction.createDir(directoryName);	// Creates directory
				//Flush to insure that there are X files created.
				while(systemInteraction.checkFiles(directoryName) < totalFiles) {
					   
					   	// Created a new file and adds line to it on each loop.
					   	for(int x = 0; x < totalFiles; x++) {
					
					   		// Get the current time to string.
					   		currentTime = systemInteraction.getTime(); 
							
					   		// Create needed files
					   		systemInteraction.createFile(directoryName+"/"+currentTime+".txt");	
					
					   			// Inner-for-loop used to add lines to file
					   			for(int y = 0; y < lines; y++) 
					   				systemInteraction.writeLine(currentTime,directoryName+"/"+currentTime+".txt" );
					
					   	}
					   	
					   	Thread.sleep(10);
			
				 }
				   
				   // Can be used to see if files where really created since the while loop will be ignored.
				   System.out.print("<Program done started: " +start +" End: " +systemInteraction.getTime() +">" );
			
		
	}

}
