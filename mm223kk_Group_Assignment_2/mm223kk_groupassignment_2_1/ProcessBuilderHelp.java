package mm223kk_groupassignment_2_1;

/*
 * File:	ProcessBuilderHelp.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*Sami Mwanje, mm223kk*
 * Date: 	November 2020
 * Just created a seperate class-file for the first part of the code to make it more clear.
 */


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;

public class ProcessBuilderHelp {
	
	private ProcessBuilder PB;
	
	
	/** 
	 * Creating a new processBuilder object when called.
	 */
	ProcessBuilderHelp(){
		PB = new ProcessBuilder();
	}
	
	
	
	/**
	 * This method reads and prints a pipe-file line by line. It also prints the caller PID and the time. 
	 * @param currentProcess - Takes in a ProcessHandle.current() method from process which called this method.
	 * @param dir	- The path of the pipe that is going to be opened.
	 * @throws IOException - If an read error occurs.
	 */
	public void readPipe(ProcessHandle currentProcess,String dir) throws IOException {
		
		 
        BufferedReader pipe = new BufferedReader(new FileReader(dir));
        String line = null;
        
        
        while ((line = pipe.readLine()) != null) {
        	
                if( line == null || line == "null" || line == "" ) {}
                
                else{
          
                                System.out.print(line +" ");
                                processInfo(currentProcess, "this is text read from the pipe."); 
                                
                }
                
  
                }

             pipe.close();

		}
	

	/**
	 * This method is used to print "<PID XXX> <hour:minute:second.ms> Process started" of process in parameters."
	 * @param currentProcess Takes in a ProcessHandle.current() function that is later used to display info of process.
	 * @throws IOException 
	 * 
	 */
	
	public void processInfo(ProcessHandle currentProcess, String task){	
		
		System.out.print("<PID " + currentProcess.pid() +"> ");
		
		String date = ( new SimpleDateFormat( "hh:mm:ss.SSS" ) ).format( new Date() ).toString();
        
	     System.out.println( "<" +date +"> " +task +"\n");
			
	}
	
	
	
	/**
	 * This method is used to change direcoty on the terminal.
	 * @param directory the input is a string that is later on used to create a new file object.
	 * <br> the object is then loaded into the processBuilder object.
	 */
	public void setDirectory(String directory){
			
		PB.directory(new File(directory));	
	}
	
	

}
