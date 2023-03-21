package mm223kk_groupassignment_1;

/*
 * File:	Main.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*Sami Mwanje, mm223kk*
 * Date: 	November 2020
 * Just created a seperate class-file for the first part of the code to make it more clear.
 */

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// Create a new ProcessBuilderHelp object. 
		
		ProcessBuilderHelp  PB = new ProcessBuilderHelp();
		
		// Adding the id line to the terminal.
		PB.newLine("id");
		
		// Change directory to /etc/
		PB.setDirectory("/etc/");
	
		// Admin more lines to the terminal
		PB.newLine("find", ".", "-name", "rc*");
		
		// Checking for changing the host name with terminal.(console).
		PB.newLine("sudo", "hostname", "freebsd-vm-mm223kk-upd");
		
	}

}
