package mm223kk_groupassignment_2_1;


/*
 * File:	Main.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*Sami Mwanje, mm223kk*
 * Date: 	November 2020
 * Just created a seperate class-file for the first part of the code to make it more clear.
 */

import java.io.IOException;


public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		
				// New ProcessBuilderHelp Object
				ProcessBuilderHelp newProcess = new ProcessBuilderHelp();
				
				// Set current process for process info
				ProcessHandle handleProcess = ProcessHandle.current();
				
				
				// Print process started
				newProcess.processInfo(handleProcess, "Process Started");
				
				// Go to home directory
				newProcess.setDirectory(System.getProperty("user.home"));
			
				
				// Read and write to pipe.
				while(true) {
					
				// Print Pipe opened
				newProcess.processInfo(handleProcess, "Pipe opened");
				
				// Read and echo new inforamtion from pipe.
			    newProcess.readPipe(handleProcess, "test-named-pipe");
			    
			    // Print Pipe Closed
			    newProcess.processInfo(handleProcess, "Pipe closed");
				
			    
			    // Sleep for 3 seconds and proced.
			    //Thread.sleep(3000);
			    
				} 
				
				
			    }
			      
	 
		
	}

