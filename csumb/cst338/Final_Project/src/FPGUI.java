/*
	FPGUI()
	class used to create visual elements for the GUI
	also, controls Button Action Listeners to add/remove classNames
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class FPGUI extends JFrame
{
	/* variables	*/
		static final int WIDTH = 900;
		static final int HEIGHT = 300;
		static final String FNAME = "Vanessa";
		static final String LNAME = "Ulloa";
		
	/*	instantiate	*/
	public static JPanel
		mainPanel,userPanel,schedulePanel,viewPanel;
		
	public static JButton
		registerBtn,dropBtn;
	
	public static JLabel
		userLbl,registerLbl,viewLbl,registerStatusLbl,viewStatusLbl;
	
	/*	for userPanel	*/
	public static JLabel
		fnameLbl,lnameLbl,fnameUserLbl,lnameUserLbl;
	
	@SuppressWarnings("rawtypes")
	public JList scheduleList, studentList;
	
	/*	schedule variables	*/
	ScheduleOfClasses theSchedule = new ScheduleOfClasses();
	StudentSchedule newStudentSchedule = new StudentSchedule();

	/*
		FPGUI()
		default constructor that creates the panel
	*/
	public FPGUI()
	{
		this.setTitle("Final Project Community College");
		this.setSize(WIDTH,HEIGHT);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		GridBagConstraints c = new GridBagConstraints();
		
		// instantiations
		mainPanel = new JPanel();
		userPanel = new JPanel();
		schedulePanel = new JPanel();;
		viewPanel = new JPanel();
		
		registerBtn = new JButton("Register");
		dropBtn = new JButton("Drop");
		
		// for main panel
		userLbl = new JLabel("User Profile");
		registerLbl = new JLabel("Schedule - Register");
		viewLbl = new JLabel("View Schedule");
		
		// set layouts
		mainPanel.setLayout(new GridLayout(1,3));
		this.add(mainPanel);
		
		//userPanel.setLayout(new GridLayout(3,2));
		userPanel.setLayout(new GridBagLayout());
		schedulePanel.setLayout(new FlowLayout());
		viewPanel.setLayout(new FlowLayout());
		
		// add other panels
		mainPanel.add(userPanel);
		mainPanel.add(schedulePanel);
		mainPanel.add(viewPanel);
		
		/*	MAIN PANEL COLORS	*/
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setForeground(Color.BLACK);
		
		// add to the panels
		createUserPanel();
		createSchedulePanel();
		createViewSchedulePanel();
		
	}
	

	/*	Methods to create Panels	*/
	
	/*
		createUserPanel()
		creates the UserPanel(left) and its elements
	*/
	public void createUserPanel()
	{	
		/*	userPanel	*/
		fnameLbl = new JLabel("First Name:");
		lnameLbl = new JLabel("Last Name:");
		fnameUserLbl = new JLabel(FNAME);
		lnameUserLbl= new JLabel(LNAME);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		userLbl.setFont(new Font("Serif",Font.BOLD,24));
		userPanel.add(userLbl,c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		userPanel.add(fnameLbl, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		userPanel.add(fnameUserLbl,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		userPanel.add(lnameLbl, c);
		
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		userPanel.add(lnameUserLbl, c);
		
	}

	/*
		createSchedulePanel()
		adds schedule panel (middle) and its elements
		RegisterBtn listener addes classes to the studentSchedule
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createSchedulePanel()
	{	
		/*	schedulePanel	*/
		scheduleList = new JList(theSchedule.scheduleOfClasses.toArray());
		scheduleList.setPreferredSize(new Dimension(WIDTH/4,HEIGHT/2));
		scheduleList.setSelectedIndex(0);
		scheduleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// status label
		registerStatusLbl = new JLabel();
		
		// schedulePanel
		registerLbl.setFont(new Font("Serif",Font.BOLD,24));
		schedulePanel.add(registerLbl);
		schedulePanel.add(new JScrollPane(scheduleList));
		schedulePanel.add(registerBtn);
		schedulePanel.add(registerStatusLbl);
		
		// add action listener to register btn
		registerBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// add class to Student Schedule
				newStudentSchedule.addClassToSchedule(scheduleList.getSelectedValue().toString());
				updateViewPanel();
			}
		});
				
	}
	
	/*
		createViewSchedulePanel()
		adds viewPanel (right)
		has dropBtn tht will remove/drop classes from the StudentSchedule
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createViewSchedulePanel()
	{
		/*	viewPanel	*/
		viewLbl = new JLabel("View Your Schedule");
		viewStatusLbl = new JLabel();
		viewLbl.setFont(new Font("Serif",Font.BOLD,24));
		viewPanel.add(viewLbl);
		
		/*	JList	*/
		studentList = new JList(newStudentSchedule.studentClassSchedule.toArray());
		studentList.setPreferredSize(new Dimension(WIDTH/4,HEIGHT/2));
		studentList.setSelectedIndex(0);
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		viewPanel.add(new JScrollPane(studentList));
		viewPanel.add(dropBtn);
		viewPanel.add(viewStatusLbl);
		
		// action listener for dropBtn
		dropBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// remove class from view schedule
				if(studentList.getModel().getSize() < 1)
				{
					viewStatusLbl.setText("Your schedule is empty!");
					viewStatusLbl.setForeground(Color.red);
				}
				else
					newStudentSchedule.dropClassFromSchedule(studentList.getSelectedValue().toString());
				updateViewPanel();				
			}
		});
	}
	
	/*
		updateViewPanel()
		method that updates the viewPanel JList after classes have been dropped or added
	*/
	@SuppressWarnings("unchecked")
	public void updateViewPanel()
	{
		studentList.setListData(newStudentSchedule.studentClassSchedule.toArray());
		viewPanel.repaint();
	}
	
}	//	FPGUI()
