package mm223kk_assignment_2;

import java.util.Random;
import java.util.ArrayList;


/*
 * File:	RandomScheduling.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*Sami Mwanje, mm223kk*
 * Date: 	November 2020
 * Just created a seperate class-file for the first part of the code to make it more clear.
 */


public class RandomScheduling {
	
	
	private int  current; 				// Current;
	private int tick; 					// Global tick.
	private int executeID;				// Executed ID.
	private int loops;					// Counting loops.
	private int complete;
	//Array for scheduled processesScheduled.
	ArrayList<ScheduledProcess> processesScheduled;

	
	// Simulation Timer
	private long simulationTime;		// Used to count total simulation time
	private long startTime;				// Used to for holding simulation start time.

	
	public static class ScheduledProcess {
		
		
		private int processId;			// Processor ID to, ID processor.
		private int burstTime;			// Burst time for each processor.	
		private int arrivalMoment;		// Moment when processor arrived in queue.
		
		
		// The total time the process has waited since its arrival
		private int totalWaitingTime;
		
		// The total CPU time the process has used so far
		// (when equal to burstTime -> the process is complete!)
			
				int allocatedCpuTime;
		
	
		
		public ScheduledProcess(int processId, int burstTime, int arrivalMoment) {
		
			this.processId = processId;							// Set ID.
			this.burstTime = burstTime;							// Set BurstTime
			this.arrivalMoment = arrivalMoment;					// Set ArrivalMoment
			this.allocatedCpuTime = 0;  						// Set Allocated CpuTime.
			
		}

		
		// ... add further fields and methods, if necessary

		
		private int  totalWaitingTime(int tick) {
		
			totalWaitingTime = tick - arrivalMoment - allocatedCpuTime; 	// I suppose that waiting time is the time when the thread is not used by the CPU.
			
			return totalWaitingTime;	
			
		}
		
		
		private void execute(){
			
			// Increase time used on CPU. 
			if(complete() == false)											// Check first if it is complete executing. If no...
			allocatedCpuTime++;												// Each time the thread gets at time with the cpu. This will be increased.
			
		} 
		
		
		public boolean complete() {
		
			return (allocatedCpuTime == burstTime) ? true : false;			// Check If thread is complete with executing. When equal to bursTime.
			
		}
		
		
	}
	

	
	private void processesArrival(int numProcesses, int minBurstTime, int maxBurstTime, int maxArrivalsPerTick, double probArrival, boolean isPreemptive, int timeQuantum) {
	
		
	while(true) {
		
		
		
		if( processesScheduled.size() < numProcesses) {
		for(int i = 0; i < maxArrivalsPerTick; i++) {																									// Check if max arrivals are reached.	
																																						// Check if current process should be scheduled.		
		if( rng.nextDouble() <= probArrival ) {								    																		// 3/4 change of scheduling process.	
																																						// Create a random burst time value from min burst to max burst.
		ScheduledProcess newProcess = new ScheduledProcess(processesScheduled.size(), this.rng.nextInt(maxBurstTime - minBurstTime) + 2 , tick);		// Create process.	
		processesScheduled.add(newProcess);																												// Add the newly scheduled process to array of scheduled, 
		if(processesScheduled.size() == numProcesses);																									// Check if scheduled list is full.
		break;		
		
					}
		
				}
		
		}
		
		
			
		if(processesScheduled.size() == numProcesses && checkIfComplete(numProcesses) == true)											// Check if all processesScheduled are scheduled
			break;
		
		
		if(processesScheduled.isEmpty() == false) 																						// Check if any process have arrived.
			loadToCpu(isPreemptive, timeQuantum);																						// Load random of available process into CPU.
		
		tick++;																															// Increase tick.
		
	}
		
	}
	

	private void loadToCpu(boolean isPreemptive, int timeQuantum) {
	
			// Selects which processesScheduled that is going to be loaded to the CPU. Number between 0-9
			// Depends on how many threads that are scheduled.
		
		
			if(isPreemptive == false) {
				
				
				if( complete == 1 ) {																				// Flag to signal that execute is done.
					complete = 0;																					// Ready to execute a new processes.
					
					
					
					executeID = this.rng.nextInt( processesScheduled.size() ); 	 									// Chooses a new random thread to execute next. 
					while(processesScheduled.get(executeID).complete() == true)										// Checks if this processes is already scheduled.
					executeID = this.rng.nextInt( processesScheduled.size() );
					
				}
				
				if(complete == 0 ) {
						
					
				if(processesScheduled.get(executeID).complete() == false)
					processesScheduled.get(executeID).execute(); 													// Execute on this tick.	
					
				if(processesScheduled.get(executeID).complete() == true) {											// Check if execution is complete.				
					complete = 1;	
				
					}
				
									
				} 
			
					
			
			} else if(isPreemptive == true) {																		// Check if preemptive is enabled.
				
			 
				
				if(complete  == 1) {																				// Choose a new process to execute for two loops.
					
				complete = 0;																						// Set complete to 0.
				
				executeID = this.rng.nextInt( processesScheduled.size() );
				while(processesScheduled.get(executeID).complete() == true && executeID != current )				// Check if this process is already done with execute for two loops.
				executeID = this.rng.nextInt( processesScheduled.size() ); 
				
				current  = executeID;																				// Save currently executed ID.
				 
				}
							 
							 
				if(loops <= timeQuantum && processesScheduled.get(executeID).complete() == false) {					// If execution of thread is not complete and less than 3 iterations.
				processesScheduled.get(executeID).execute();														
				loops ++;
				} 
				
				if(loops > timeQuantum) {
					loops = 1;
					complete = 1;
				}
				
				
				if(processesScheduled.get(executeID).complete() == true) {											// Check if execution is complete.
					complete = 1;
					loops = 1;			
				}
				
				
			}
			
	
	}
	
	
	
	
		
	
	private boolean checkIfComplete(int numProcesses) {
	
		int check = 0;					// Flag used to check if all threads have been executed.
		
		for(int x = 0; x < processesScheduled.size(); x++ ) 			// 0 to needed size (0)
			if(processesScheduled.get(x).allocatedCpuTime > 0 && processesScheduled.get(x).complete() == true)	//Check if started executing, then check if complete.
				check++; 					// If yes, increase flag.
			
	
		if(check >= numProcesses) 	// If check-flag is larger than numProcesses. Return true, else false. Execution not complete.
		return true;
			else
				return false; 
	}

	
	// Random number generator that must be used for the simulation
	Random rng;

	// ... add further fields and methods, if necessary
		
	public RandomScheduling(long rngSeed) {
			
		current = 100;
		loops = 1;													// Set loops to 1.
		processesScheduled = new ArrayList<ScheduledProcess>(); 	// Initialize array.
		this.tick = 0;												// Set tick to 0.	
		this.rng = new Random(rngSeed);								// Create random with seed.
		complete = 1;
		
	}
		
	
	
	public void reset() {
		
		current = 100;
		loops = 1;												// Set loops to one.
		complete = 1;											// Set completet to one ( Ready to execute).
		tick = 0;												// Set tick to 0.
		processesScheduled.clear(); 							// Clear processesScheduled array.

		
	}
	
	public void runNewSimulation(final boolean isPreemptive, final int timeQuantum,
	    final int numProcesses,
		final int minBurstTime, final int maxBurstTime,
		final int maxArrivalsPerTick, final double probArrival) {
			
		
		startTime = System.nanoTime(); 	// Start simulation time.
			
		processesArrival(numProcesses, minBurstTime, maxBurstTime, maxArrivalsPerTick, probArrival, isPreemptive, timeQuantum);
		
		simulationTime = System.nanoTime() - startTime;			// End simulation time.
	
		
		// TODO:
		// 1. Run a simulation as a loop, with one iteration considered as one unit of time (tick)
		// 2. The simulation should involve the provided number of processesScheduled "numProcesses"
		// 3. The simulation loop should finish after the all of the processesScheduled have completed
		// 4. On each tick, a new process might arrive with the given probability (chance)
		// 5. However, the max number of new processesScheduled per tick is limited
		// by the given value "maxArrivalsPerTick"
		// 6. The burst time of the new process is chosen randomly in the interval
		// between the values "minBurstTime" and "maxBurstTime" (inclusive)

		// 7. When the CPU is idle and no process is running, the scheduler
		// should pick one of the available processesScheduled *at random* and start its execution
		// 8. If the preemptive version of the scheduling algorithm is invoked, then it should 
		// allow up to "timeQuantum" time units (loop iterations) to the process,
		// and then preempt the process (pause its execution and return it to the queue)
		
		// If necessary, add additional fields (and methods) to this class to
		// accomodate your solution

		// Note: for all of the random number generation purposes in this method,
		// use "this.rng" !
	}
	
	public void printResults() {
		// TODO:
		// 1. For each process, print its ID, burst time, arrival time, and total waiting time
		// 2. Afterwards, print the complete execution time of the simulation
		// and the average process waiting time
		
		double avg = 0;
		String results = "";
		
		for(int x = 0; x < processesScheduled.size(); x++) {
			
			 results = "Process  ID: " +processesScheduled.get(x).processId +", Burst time: " +processesScheduled.get(x).burstTime +", Waiting time: "
			 		+ " " +processesScheduled.get(x).totalWaitingTime(tick) +", Arrival time: " +processesScheduled.get(x).arrivalMoment +" (ticks)"; 
			 System.out.println(results);
			 avg += processesScheduled.get(x).totalWaitingTime;
			 
		 }
		
		System.out.println( "Total simulation time: " +simulationTime/1000.00 +" ms.");
		System.out.println( "Average waiting time: " +avg/10.0 +" (ticks)");
		reset();
	}
	
}
		