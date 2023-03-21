package mm223kk_groupassignment_1;


/*
 * File:	ProcessBuilderHelp.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*Sami Mwanje, mm223kk*
 * Date: 	November 2020
 * Just created a seperate class-file for the first part of the code to make it more clear.
 */


import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ProcessBuilderHelp {
	
	
	private ProcessBuilder PB;
	
	
	/**
	 * 
	 * Creating a new processBuilder object when called.
	 * 
	 */
	ProcessBuilderHelp(){
		PB = new ProcessBuilder();
	
	}
	
	
	
	
	/**
	 * This Method is used to invoke one or more lines on the console/terminal. It also prints the
	 * <br> output from the console using the printLine() method.
	 * @param line - This will be the command that is going to be invoked in the terminal/console.
	 * <br> Their can be multiple string added to this function. (string0, string1, string...).
	 * @throws InterruptedException  If something goes wrong a an InterruptedException is thrown.
	 
	 */
	public void newLine(String... line) throws InterruptedException{
		
		PB.command(line);
		printLine(line);
	
	}
	
	/**
	 * This Method is used to print/read the output of the console so the user can view it.
 	 * It is also called by the newLine() Method with the inputed line.
 	 * 
	 * @param currentLine - The input used by the process is saved as a string and can later on be printed. 
	 * @throws InterruptedException - If an error occur while the process tries to read th current output.
	 */
	public void printLine(String... currentLine) throws InterruptedException {
		
		
		// Used for the output of current command.
		System.out.print("Command");
		for(String x: currentLine)
		System.out.print( " " +x );
		System.out.println(":");
		
		try {
			
			
			Process process = PB.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line;
			
			while( ( line = reader.readLine() ) != null) 
				System.out.println(line);
				
			
			int exitCode = process.waitFor();
				System.out.println("\nExited with error code: " +exitCode +"\n");
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
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
