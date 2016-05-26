/*
	ScheduleOfClasses()
	Will build a Schedule of Classes
	An ArrayList of ClassNames
*/

import java.util.ArrayList;
public class ScheduleOfClasses 
{
	/*	VARIABLES	*/
	public static final int MAXSCHEDULECLASSES = 25;
	ArrayList<String> scheduleOfClasses = new ArrayList<String>();

	
	/*	
		ScheduleOfClasses()
		default constructor
		only used for schedule of classes - full
	 */
	public ScheduleOfClasses()	{	generateClassList();	}

	/*
		generateClassList()
		method that creates X number of ClassName objects
		and adds them to the arraylist
	 */
	private void generateClassList()
	{
		for(int i = 0 ; i < MAXSCHEDULECLASSES ; i++)
			scheduleOfClasses.add(new ClassName().getClassScheduleName());
	}

}	//	end ScheduleOfClasses()
