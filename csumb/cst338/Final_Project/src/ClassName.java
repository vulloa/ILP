/*
 	Class to create Classes for The schedule
 	Class wil pull randomly from String arrays to generate a new Class every time
 */

import java.util.Random;

public class ClassName 
{
	/*	VARIABLES	*/
	private static String classScheduleName;
	
	/*	ARRAYS	*/
	String[] classID = {
			"BUS 340","BUS 345","CISC 320","CISC 310","BUS 498","BUS 350","BUS 330","CISC 305","CISC 323",
			"CISC 324","BUS 269","BUS 310","BUS 320","BUS 105","CISC 300","BUS 300","BUS 260","BUS 265",
			"BUS 266","BUS 267","MATH 100","MATH 120","BUS 100","MATH 110","MATH 335","ART 300","ART 302",
			"ENGWR 101","ENGWR 300","ENGWR 301","ENGWR 302",
	};
	String[] scheduleDays = { "TTh","MW","W","TBA","T","Th","F"	};
	String[] scheduleTimes = {
			"09:00AM-10:20AM",
			"10:30AM-11:50AM",
			"01:00PM-02:50PM",
			"TBA",
			"01:00PM-02:20PM",
			"05:30PM-06:50PM",
			"06:00PM-08:50PM",
			"10:00AM-10:50AM",
			"07:30AM-08:50AM",
			"02:00PM-04:20PM",
			"02:30PM-04:50PM",
			"02:30PM-03:50PM",
			"12:00PM-02:20PM",
			"03:00PM-05:20PM",
			"12:00PM-01:50PM",
			"03:30PM-05:35PM",
			"08:00AM-09:20AM",
			"09:30AM-10:50AM",
			"02:00PM-03:20PM"
	};
	
	/*	
		ClassName(int)
		constructor that gets passed a section ID and creates class details
		generates a new combination everytime
	 */
	public ClassName()
	{
		classScheduleName = generateClassID().toString() + " " + generateScheduleDays() + " " + generateScheduleTimes();
	}
	
	/*	Accessors for private varibles	*/
	public String getClassScheduleName()	{ return classScheduleName;	}
	

	/*	methods	*/
	
	/*
		generateClassID()
		use Random int to pull random value from classID Array
	*/
	private String generateClassID()
	{
		Random rand = new Random();
		int randomInt = rand.nextInt(classID.length);
		
		return classID[randomInt];
	}
	
	/*
		generateScheduleDays()
		use Random int to pull random value from scheduleDays Array
	*/
	private String generateScheduleDays()
	{
		Random rand = new Random();
		int randomInt = rand.nextInt(scheduleDays.length);
		
		return scheduleDays[randomInt];
	}
	
	/*
		generateScheduleTimes()
		use Random int to pull random value from scheduleTimes aray
	*/
	private String generateScheduleTimes()
	{
		Random rand = new Random();
		int randomInt = rand.nextInt(scheduleTimes.length);
		
		return scheduleTimes[randomInt];
	}
	
}	//	ClassName()
