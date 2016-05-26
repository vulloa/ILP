/*
	StudentSchedule()
	creates a List (like Schedule of Classes) but smaller for invidudal students
	will hold the classes they Register and Drop (add and remove)
*/

import java.awt.Color;
import java.util.ArrayList;

public class StudentSchedule 
{
	/*	VARIABLES	*/
	ArrayList<String> studentClassSchedule = new ArrayList<String>();
	private boolean flag;
	
	/*
		StudentSchedule()
		empty constructor
	*/
	public StudentSchedule() {}
	
	/*
		addClassToSchedule(String)
		takes value from JList and adds it to the ArrayList
		first it will check if it was already added (already exists)
	*/
	public void addClassToSchedule(String selectedClass)
	{
		FPGUI.viewStatusLbl.setText("");
		
		if(isValidRegister(selectedClass))
		{
			studentClassSchedule.add(selectedClass);
			FPGUI.registerStatusLbl.setText("Class Registered!");
			FPGUI.registerStatusLbl.setForeground(Color.blue);
		}
		else
		{
			FPGUI.registerStatusLbl.setText("You are already registered for this course!");
			FPGUI.registerStatusLbl.setForeground(Color.red);
		}
	}
	
	/*	
		dropClassFromSchedule(String)
		check is not needed in this method
		because valuesare selected from valid list in JList Panel
	 */
	public void dropClassFromSchedule(String selectedClass)
	{	
		if(studentClassSchedule.size() == 1)
			studentClassSchedule.clear();
		else
			studentClassSchedule.remove(selectedClass);
		
		FPGUI.viewStatusLbl.setText("Class Successfully Dropped!");
		FPGUI.registerStatusLbl.setText("");
		FPGUI.viewStatusLbl.setForeground(Color.blue);
	}
	
	/*
		isValidRgister(String)
		method used to check if value already exits in ArrayList
	*/
	private boolean isValidRegister(String selectedClass)
	{
		if(studentClassSchedule.contains(selectedClass) == true)
			return false;
		else
			return true;
	}
	
}	//	end StudentSchedule()
