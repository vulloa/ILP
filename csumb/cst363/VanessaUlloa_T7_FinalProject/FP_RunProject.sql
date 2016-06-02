/*
	VANESSA ULLOA
	DANIEL KUSHNER
	CST 363
	FINAL PROJECT
	QUERIES FOR PROJECT
		VIEW SCHEDULE
		VIEW COURSE DETAILS
*/

set echo off
set heading off
set verify off

SPOOL V:\Desktop\FP_RunProject.txt
--SPOOL C:\Users\ulloav\Desktop\FP_RunProject.txt

PROMPT
PROMPT ***	USS ENTERPRISE COMMUNITY COLLEGE	***
PROMPT

--	VIEW STUDENT SCHEDULE
	--	GET USER STUDENT ID TO DISPLAY SCHEDULE

	
--	QUERY FOR SCHEDULE

PROMPT	
PROMPT ***	To View Schedule, Please enter Student ID [1/2/3/4/5]
PROMPT
ACCEPT user_stu_id PROMPT "Student ID: ";
PROMPT
--PROMPT TO CHECK USER INPUT
--PROMPT '&user_stu_id'


SELECT
	'***	SCHEDULE FOR ' || TRIM(f_name) || ' ' || TRIM(l_name) 
FROM FP_STUDENT
WHERE student_id = TRIM('&user_stu_id');

SELECT
	LPAD(RTRIM(C.course_name),10,' ') || ' ' ||
	LPAD(RTRIM(C.class_day),4,' ') || ' ' ||
	LPAD(RTRIM(C.class_time),10,' ') || ' ' ||
	LPAD(RTRIM(P.professor_fname) || ' ' ||
	RTRIM(P.professor_lname),15,' ')
FROM FP_STU_ENROLL E, FP_CLASS C, FP_COURSE_DETAILS CD, FP_PROFESSOR P 
WHERE E.section_number = C.section_number 
	AND C.section_number = CD.section_number
	AND CD.professor_id = P.professor_id
	AND E.student_id = '&user_stu_id';
--	END SCHEDULE

PROMPT
SPOOL OFF