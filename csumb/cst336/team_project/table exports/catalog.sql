-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 02, 2015 at 08:33 PM
-- Server version: 5.5.31-cll
-- PHP Version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ullo4940`
--

-- --------------------------------------------------------

--
-- Table structure for table `catalog`
--

CREATE TABLE IF NOT EXISTS `catalog` (
  `CourseID` varchar(10) NOT NULL DEFAULT '',
  `CourseDesc` text DEFAULT NULL,
  `Subject` varchar(25) NOT NULL,
  `Units` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `catalog`
--

INSERT INTO `catalog` (`CourseID`, `CourseDesc`, `Subject`, `Units`) VALUES
('CISC 300', 'Computer Familiarization: An introductory course to provide general knowledge on how computers work, computer terminology, and the impact of computers on society and the work environment. This is a beginning level hands-on instruction using an operating system, word processing, spreadsheet, and Internet software. Students will be reading/interpreting written and oral instructions technical in nature.', 'Computer Science', 1),
('CISC 305', 'Introduction to the Internet: Introduction to the Internet with emphasis on using the World Wide Web to locate, transfer, and publish information. Survey of emerging technologies on the Internet. Students will use and configure web browsers; use the Internet to locate, transfer, and publish information; create a basic HTML document; use e-mail services; and explain issues in choosing an Internet service provider', 'Computer Science', 1),
('CISC 310', 'Introduction to computers and information science. Includes computer terminology, computer hardware and software, networks, telecommunications, Internet access and security issues, common operating systems, data representation, computer ethics, and beginning programming in Visual Basic and/or HTML, introduction to research processes and methods through online tools and sources. Covers topics motivated by current issues and events. Examines such issues as privacy, intellectual property, cloud computing, and copyright infringement.', 'Computer Science', 3),
('CISC 320', 'Operating Systems: This course is an introductory course to operating systems. Operating systems are an essential part of any computer system. Operating systems vary significantly, but their fundamental principles remain the same. In this course you will be introduced to the basic concepts of operating systems, see how they manage resources such as memory, peripherals, and schedule CPU time, learn how to use the system call interface and how to create processes and synchronize them, learn how applications communicate, understand the memory hierarchy and see how virtual memory is managed, understand how files are managed and stored, and much more.', 'Computer Science', 1),
('CISC 323', 'Linux Operating System: This course introduces the Linux operating system for desktop computers. Concepts include kernels, file structures, Daemons, shells, GUIs, procedures for installing software, creation of user accounts, shell commands, scripts, and file security.', 'Computer Science', 1),
('CISC 324', 'Intermediate Linux Operating System: This course covers the Linux operating system for desktop computers. It covers advanced shell scripting, C Shell, K Shell, and BASH. Other topics covered in this course include decision-making logic, looping, and nesting. Consult the class schedule for specific operating system offered.', 'Computer Science', 1),
('ENGWR 101', 'College Writing: The purpose of the course is to develop students ability to edit their own writing and to identify high-frequency non-idiomatic uses of English. Intensive, individualized practice will be provided for ', 'English', 4),
('ENGWR 300', 'College Composition:  This course is the first of a two-semester sequence of college-level composition courses. See College Composition and Literature for a full description. ', 'English', 3),
('ENGWR 301', 'College Composition and Literature: This Course guides students through the expository writing process through close reading of contemporary critical discourse and teaches the rhetorical arts of argument and persuasion through critical thinking, reading and research. Students will develop an understanding of themselves as readers and writers of culture as they participate in public discourse about writing; examine the relationship among writer, audience, and purpose; and practice writing prose through a recursive process.   Students completing this course should be able to write persuasive, researched and documented essays (of at least 1,000 words) demonstrating the conventions of standard written English and manuscript presentation. ', 'English', 3),
('ENGWR 302', 'Advanced Composition and Critical Thinking: An advanced course in expository and argumentative writing that continues the work of Effective College English. While college composition 1 & 2 focus on essay formulation and research, Advanced Composition emphasizes textual analysis. Students continue to practice the construction of arguments based on analytical reasoning, critical thinking and research skills. ', 'English', 3),
('MATH 100', 'Elementary Algebra: This is a basic course in algebra covering the following topics: operations on integers, rational numbers, scientific notation, polynomials and exponents; solving linear equations; applications of linear equations in geometry, mixture, percent’s and motion; graphing in the coordinate plane with analysis of equations and graphs; solving quadratic equations by factoring, applications of linear equations in geometry, mixture, percent’s and motion. The class meets five hours weekly. It will not transfer to a major four year college or university.', 'Mathematics', 5),
('MATH 110', 'Elementary Geometry: The main focus of geometry is on plane and solid figures and their properties. A major emphasis is triangle properties and relationships. Coordinate graphing, slope, and linear equations are also presented', 'Mathematics', 5),
('MATH 120', 'Intermediate Algebra: Intermediate Algebra is designed to broaden and expand the concepts of Elementary Algebra/Algebra l. This course covers all the essential topics needed to be successful in College Algebra or Precalculus. Topics include: algebraic techniques with polynomials, rational expressions, exponents, radical expressions and equations, factoring, linear and quadratic equations, inequalities, logarithmic and exponential functions, solving systems of two or more linear equations, mathematical modeling, complex numbers, counting, sequences and series, functions and their graphs. Upon completion, students will be able to solve real world problems and use appropriate models for analysis.', 'Mathematics', 5),
('MATH 335', 'Trigonometry with College Algebra: A continuation course in intermediate algebra. Examines higher degree polynomials, rational functions, trigonometry and matrix algebra needed for more specialized study in mathematics, computer science, engineering and other related fields.', 'Mathematics', 5),
('BUS 300', 'Introduction to Business: This course provides a survey of the business world. Topics include the basic principles and practices of contemporary business. Upon completion, students should be able to demonstrate an understanding of business concepts as a foundation for studying other business subjects','Business', 3),
('BUS 100', 'English for the Professional: Designed to meet the needs of students in business fields, this course teaches the preparation of written reports, case studies, and other forms of professional writing.', 'Business', 3),
('BUS 105', 'Business Mathematics: Business Mathematics is course of which students learn to use mathematics effectively as a tool in their personal and business lives. After students have completed this course, they will be able to apply mathematical concepts in various personal and business situations. This course may be used to meet the mathematics requirements for graduation.', 'Business', 3),
('BUS 260', 'Communicating with Customers: An introduction to the fundamental components of the human communication process, emphasizing selected concepts, methods, and practice in dyadic, small group, and presentational settings.', 'Business', 1),
('BUS 265', 'Stress Management in the Workplace: This stress management training course helps employees better manage stress. Too much stress is one of the most common causes of health problems. It can also cause mental distress that leads to serious illness and to distractions that can jeopardize safety on and off the job. This course helps trainees identify the causes of stress, recognize the different types of stress, understand how stress affects them, and manage stress effectively both on and off the job. The benefits to you, the employer, are numerous, from lower healthcare costs to increased employee productivity.', 'Business', 1),
('BUS 266', 'Time Management in the Workplace: This course will provide you with a set of proven time management tools and techniques to help you make decisions regarding the use of your time that will lead to more effective, efficient results in all areas of your life.', 'Business', 1),
('BUS 267', 'Dealing with Conflict in the Workplace: This class will focus on those interpersonal skills that are essential in establishing and maintaining mutual satisfying and rewarding professional and personal relationships. This course will identify a number of approaches effective in both resolving and managing conflict. Participants will learn the importance of understanding their behavioral preferences by taking the DiSC profile evaluation. They will then have the opportunity to relate these insights to the ways in which they might modify their behaviors to achieve greater trust and rapport in their workplace relationships. Class generated real world conflict situations will be analyzed through a role-play and debriefing process. These exercises will allow participants to develop action plans.', 'Business', 1),
('BUS 269', 'Organizational Change: In this course, we will analyze the forces that drive organizations to change, examine impediments to change, and survey a range of approaches for making organizational change more effective.', 'Business', 1),
('BUS 310', 'Business Communications: This course will provide an introduction to business writing and speaking with a particular emphasis on grammar, sentence structure, thought formation, and presentation skills. Class activities will emphasize communication in real-world business situations and enable students to begin developing their ability to write and speak effectively in the workplace', 'Business', 3),
('BUS 320', 'Concepts in Personal Finance: Understand the following concepts in general, and some in specific, terms:  Income Tax; Credit; Budget; Financial Statements; Buying a Car; Buying a house; Buying real estate; Insurance (health, life, home, auto, disability, homeowners & renters); Banking; Investing; retirement; and the Time Value of Money.', 'Business', 3),
('BUS 330', 'Managing Diversity in the Workplace: Gain an understanding of diversity issues in the workplace. Examine the impact of a multicultural society on organizations and individuals within the organizations. Incorporating both theory and practice, analyze critical issues from multiple perspectives including historical, legal, economic and sociological frameworks.', 'Business', 3),
('BUS 340', 'Business Law: Law and legal institutions in society, emphasizing areas of law relevant to business operations, general legal, and social environment with emphasis on business ethics; role of contracts in business; and employment obligations', 'Business', 3),
('BUS 345', 'Law and Society: This course examines the social, political and economic processes that affect the creation and implementation of law. It focuses on history and fundamental principles of American legal system and compares it to other legal systems existing in a todays world. In assessing how judicial opinions and legislative efforts affect social relations and institutional arrangements, inquiry is centered on: the ways in which social problems become defined as legal issues, the forces which shape the initiation and ultimate formulation of legislative acts designed to affect public policy, the role which cultural values and assumptions play in framing legal arguments and influencing judicial opinions and remedial programs, and the strengths and limitations of the law as a means of achieving special social policy objectives.', 'Business', 3),
('BUS 350', 'Small Business Management/Entrepreneurship: Introduces the fundamentals of business management, including planning, raising capital, using business information, managing employees, and marketing products and services. The course is oriented toward principles needed to operate a small business and is designed for those who may eventually have their own businesses or for those who desire to upgrade their skills in their present businesses.', 'Business', 3),
('BUS 498', 'Work Experience in Business: Supervised work experience in business. Students will be expected to relate course material to work experience in an analysis of a firms operations.', 'Business', 4),
('ART 300', 'Elementary Drawing and Composition I: Using selected studio experiences in drawing, painting, and crafts, and reading about viewing works of art, students consider human development in the visual arts, and learn fundamental criteria for making judgments about the quality and value of works of art. The history of art education is presented, and the student is made familiar with the various methodologies used in art education', 'Art', 3),
('ART 302', 'Elementary Drawing and Composition II: A continuation of Elementary Drawing and Composition I. ', 'Art', 3);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
