/*
	VANESSA ULLOA
	DANIEL KUSHNER
	CST 363
	FINAL PROJECT
	CREATE TABLE SQL FILE
*/

set echo off
set heading off
set verify off

SPOOL V:\Desktop\FP_CreateTables.txt

-- drop tables

DROP TABLE FP_COURSE_DETAILS cascade constraints;
DROP TABLE FP_STUDENT cascade constraints;
DROP TABLE FP_STU_ENROLL cascade constraints;
DROP TABLE FP_COURSE cascade constraints;
DROP TABLE FP_CLASS cascade constraints;
DROP TABLE FP_PROFESSOR cascade constraints;


--	begin create tables

CREATE TABLE FP_STUDENT (
	student_id NUMBER(3),
	f_name CHAR(25),
	l_name CHAR(25),
	CONSTRAINT pk_s_student_id PRIMARY KEY(student_id)
);

CREATE TABLE FP_COURSE (
	course_name CHAR(10),
	course_subject CHAR(20),
	CONSTRAINT pk_course_name PRIMARY KEY(course_name)
);

CREATE TABLE FP_CLASS (
	section_number CHAR(6),
	course_name CHAR(10),
	class_day CHAR(10),
	class_time CHAR(20),
	CONSTRAINT pk_c_section_number PRIMARY KEY(section_number),
	CONSTRAINT fk_course_name FOREIGN KEY(course_name) REFERENCES FP_COURSE(course_name)
);

CREATE TABLE FP_PROFESSOR (
	professor_id CHAR(3),
	professor_fname CHAR(25),
	professor_lname CHAR(25),
	CONSTRAINT pk_professor_id PRIMARY KEY(professor_id)
);

CREATE TABLE FP_COURSE_DETAILS (
	section_number CHAR(6),
	professor_id CHAR(3),
	CONSTRAINT pk_section_number PRIMARY KEY(section_number),
	CONSTRAINT fk_professor_id FOREIGN KEY(professor_id) REFERENCES FP_PROFESSOR(professor_id)
);

CREATE TABLE FP_STU_ENROLL (
	student_id NUMBER(3),
	section_number CHAR(6),
	CONSTRAINT fk_section_number FOREIGN KEY(section_number) REFERENCES FP_CLASS(section_number),
	CONSTRAINT fk_student_id FOREIGN KEY(student_id) REFERENCES FP_STUDENT(student_id)
);

--	data to tables with sequences

CREATE SEQUENCE student_num_seq
	MINVALUE 0
	START WITH 0
	INCREMENT BY 1;

INSERT INTO FP_STUDENT VALUES(student_num_seq.NEXTVAL,'Vanessa','Ulloa');
INSERT INTO FP_STUDENT VALUES(student_num_seq.NEXTVAL,'Daniel','Kushner');
INSERT INTO FP_STUDENT VALUES(student_num_seq.NEXTVAL,'John','Doe');
INSERT INTO FP_STUDENT VALUES(student_num_seq.NEXTVAL,'Betty','White');
INSERT INTO FP_STUDENT VALUES(student_num_seq.NEXTVAL,'Stevie','Wonder');

DROP SEQUENCE student_num_seq;



CREATE SEQUENCE prof_num_seq
	MINVALUE 0
	START WITH 0
	INCREMENT BY 10;

INSERT INTO FP_PROFESSOR VALUES(prof_num_seq.NEXTVAL,'James','Kirk');
INSERT INTO FP_PROFESSOR VALUES(prof_num_seq.NEXTVAL,'Leonard','McCoy');
INSERT INTO FP_PROFESSOR VALUES(prof_num_seq.NEXTVAL,'Montgomery','Scott');
INSERT INTO FP_PROFESSOR VALUES(prof_num_seq.NEXTVAL,'Spock','');
INSERT INTO FP_PROFESSOR VALUES(prof_num_seq.NEXTVAL,'Nyota','Uhura');

DROP SEQUENCE prof_num_seq;

SELECT * FROM FP_STUDENT;
SELECT * FROM FP_PROFESSOR;
SELECT * FROM FP_STU_ENROLL;
SELECT * FROM FP_CLASS;
SELECT * FROM FP_COURSE_DETAILS;
SELECT * FROM FP_COURSE;


SPOOL OFF