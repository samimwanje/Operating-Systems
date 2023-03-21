package mm223kk_assignment_3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * File:	MultithreadedService.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*Sami Mwanje, mm223kk*
 * Date: 	November 2020
 * Just created a seperate class-file for the first part of the code to make it more clear.
 */


// TODO: put this source code file into a new Java package with meaningful name (e.g., dv512.YourStudentID)!

// You can implement additional fields and methods in code below, but
// you are not allowed to rename or remove any of it!

// Additionally, please remember that you are not allowed to use any third-party libraries

public class MultithreadedService {

    // TODO: implement a nested public class titled Task here
    // which must have an integer ID and specified burst time (duration) in milliseconds,
    // see below
    // Add further fields and methods to it, if necessary
    // As the task is being executed for the specified burst time, 
    // it is expected to simply go to sleep every X milliseconds (specified below)
	
	
	private class Task implements Runnable{
		
		private int ID;	// Current task ID.
		private long burstTime; // Task burst time.
		private long sleepTimeMs;		// Sleep time per loop.
		private long startTime = 0;		// Start time.
		private long endTime = 0;		// End time.
		//private boolean interrupted = false;	// Flag if the task has been interrupted.
		
		/**
		 * 
		 * @param setID - Sets the task ID
		 * @param min	- Lower value, Used to generate random burst time.
		 * @param max - Upper value, Used to generate random burst time.
		 * @param sleepTimeMs - Time to sleep per loop.
		 */
		Task(int setID, long min, long max, long sleepTimeMs){
		
			this.burstTime = rng.nextInt(  (int) ( (max - min) + 1) ) + (int)min;
			this.sleepTimeMs = sleepTimeMs;
			this.ID = setID;
		}
		
		// The methods below are used to return flags outside the class.
		public int getID(){ return this.ID; }
			
		public long getburstTime() { return this.burstTime; }
		
		public long getEndTime() { return this.endTime; }
		
		public long getStartTime() { return this.startTime; }
		
		public boolean complete() { return  (this.endTime - startTime) >= burstTime; }
		
		public boolean interrupted() { return (this.startTime > 0 && (this.endTime - this.startTime) < this.burstTime); }
		
		public boolean executionStarted() { return this.startTime > 0;}
		
		
		/**
		 * Used by executor to run tasks.
		 */
		public void run() {
		
					// Makes sure that the task does not continue when the thread is interrupted.
					this.startTime = System.currentTimeMillis();
					try {				
							// While task is not done executing and no interrupt occurred.
							while( ( (System.currentTimeMillis() - this.startTime) < this.burstTime ) && !Thread.currentThread().isInterrupted() ) 	{
								
								if(Thread.currentThread().isInterrupted()) {			// Check if interrupt routine was missed.
								this.endTime = System.currentTimeMillis(); 			// Saves the current execution interrupt.
								return; 											// Finish the current task run code.
					
								}
								else { Thread.sleep(sleepTimeMs); } 					// Else sleep for the chosen time	
								
								}
							
								this.endTime = System.currentTimeMillis(); 				// Saves the execution end time.
								return;													// To avoid interrupt end time;
								
					} catch (InterruptedException e) {	
						this.endTime = System.currentTimeMillis(); 				// Used to saves the execution end time if an interrupt occurred. 
						return; 												// Used to finish task
					}
					
		}
		
	}


    // Random number generator that must be used for the simulation
	Random rng;
	Task currentTask;					// Object for the current created task.
	private ArrayList<Task> tasks;		// Array that holds all created tasks.
	private ExecutorService executor;	//	Executor used to attach tasks to threads. 
	private long startTime;				// Long variable holding the start time of the simulation.
	private long endTime;				// Long variable holding the end time of the simulation.

    // ... add further fields, methods, and even classes, if necessary
    

	/**
	 * Constructor used to create a new list that will contain thread, and a random number generator. 
	 * @param rngSeed - Indicates where to begin in the seed.
	 */
	public MultithreadedService (long rngSeed) {
		
		tasks = new ArrayList<Task>();
		
        this.rng = new Random(rngSeed);
    }
	
	
	
	/**
	 * Used to create a new task that can be launched by a thread.
	 * @param minBurstTimeMs - Minimum preferred burst time.
	 * @param maxBurstTimeMs - Maximum preferred burst time.
	 * @param numTasks		-  number of tasks to create.
	 * @param sleepTimeMs	-  Task sleep for a certain time on each run.
	 */
	public void createTasks(long minBurstTimeMs,long maxBurstTimeMs,int numTasks, long sleepTimeMs) {
		
			
		for(int x = 0; x < numTasks; x++) {
			currentTask = new Task(x,minBurstTimeMs, maxBurstTimeMs, sleepTimeMs);
			executor.submit(currentTask);	
	    	tasks.add(currentTask);
	    	}

	   	
	}
	
	/**
	 * This methods is used as a timer to stop the thread pool after a certain time.
	 * @param totalSimulationTimeMs - Recives a value of how long the simulation will run.
	 */
	private void waitSimulationTime(long totalSimulationTimeMs){
		
		// Wait in while loop for simulation time.
		while(System.currentTimeMillis() - startTime < totalSimulationTimeMs) 
			if(totalSimulationTimeMs - (System.currentTimeMillis() - startTime) < 1) // Quit if there is less then one ms left.
				break;		
			

				shutdownExe();					// Starts the shutdown routine.
		
	}
	
	
	/**
	 * 
	 * This method is used to shutdown the running threads and the thread executor.
	 * 
	 */
	public void shutdownExe(){
		
		executor.shutdown();
		executor.shutdownNow();
    
	}



	public void reset() {
		
		
		
    }
    

    // If the implementation requires your code to throw some exceptions, 
    // you are allowed to add those to the signature of this method
    public void runNewSimulation(final long totalSimulationTimeMs,
        final int numThreads, final int numTasks,
        final long minBurstTimeMs, final long maxBurstTimeMs, final long sleepTimeMs) {
    	
    	startTime = System.currentTimeMillis();				// Start counting.
    	tasks = new ArrayList<Task>();						// Clear the array for upcoming tasks.
    	executor = Executors.newFixedThreadPool(numThreads); // Creating 4 thread pools.
    	createTasks( minBurstTimeMs, maxBurstTimeMs,numTasks, sleepTimeMs); // Jumping to the launch task method.
    	waitSimulationTime(totalSimulationTimeMs); // Waits for simulation time to pass.
    	endTime = System.currentTimeMillis();	  // Sets the totalt time of the simulation.
    	System.out.println("Simulation time passed: " +( endTime-startTime )/1000F +"s");
        reset();
        
        // TODO:
        // 1. Run the simulation for the specified time, totalSimulationTimeMs
        // 2. While the simulation is running, use a fixed thread pool with numThreads
        // (see https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/Executors.html#newFixedThreadPool(int) )
        // to execute Tasks (implement the respective class, see above!)
        // 3. The total maximum number of tasks is numTasks, 
        // and each task has a burst time (duration) selected randomly
        // between minBurstTimeMs and maxBurstTimeMs (inclusive)
        // 4. The implementation should assign sequential task IDs to the created tasks (0, 1, 2...)
        // and it should assign them to threads in the same sequence (rather any other scheduling approach)
        // 5. When the simulation time is up, it should make sure to stop all of the currently executing
        // and waiting threads!
        

    }


    public void printResults() {
        // TODO:
        
       
        
        // 1. For each *completed* task, print its ID, burst time (duration),
        // its startTime time (moment since the startTime of the simulation), and finish time
        
       
    	System.out.println("Completed tasks:");
        
        for(int x = 0; x < tasks.size(); x++) 
        	if(tasks.get(x).complete())
        	System.out.println("Task ID: " +tasks.get(x).getID() +", burst time: " +tasks.get(x).getburstTime()/1000F 
    		+"s , start time: " +( tasks.get(x).getStartTime()-this.startTime )/1000F +"s , finished time: " +(tasks.get(x).getEndTime()-this.startTime)/1000F +"s " );
    	
    	
       
        
        // 2. Afterwards, print the list of tasks IDs for the tasks which were currently
        // executing when the simulation was finished/interrupted
        
     
        System.out.println("Interrupted tasks:");
        
        for(int x = 0; x < tasks.size(); x++) 
        	if(tasks.get(x).interrupted() == true)
        	System.out.println("Task ID: " +tasks.get(x).getID() +", burst time: "   +tasks.get(x).getburstTime()/1000F  
    		+", start time: " +( tasks.get(x).getStartTime()-this.startTime )/1000F +"s , interrupted time: " +(tasks.get(x).getEndTime()-this.startTime)/1000F +"s " );
        
        
        
        
        
        // 3. Finally, print the list of tasks IDs for the tasks which were waiting for execution,
        // but were never started as the simulation was finished/interrupted
        
        System.out.println("Waiting tasks:");
        
        for(int x = 0; x < tasks.size(); x++)
        	if(!tasks.get(x).executionStarted())	
        	System.out.println("Task ID: " +tasks.get(x).getID() +", burst time: " +(tasks.get(x).getburstTime())/1000F 
    		+"s , start time: waiting" );
     
        
        
        
	}

}
