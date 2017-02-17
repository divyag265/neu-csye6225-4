# neu-csye6225-4
Cloud Computing- Team-6

###Assignment 5: Design Document 

###Team Members:
Sno. | Name | Email
---- | ------------ | -------------
1 | Naveenramkumar Varadarajan | varadarajan.n@husky.neu.edu
2 | Rakshitha Balakrishna | balakrishna.r@husky.neu.edu
3 | Divya Gawade | gawade.d@husky.neu.edu
4 | Mayank kumar Choudhary | choudhary.m@husky.neu.edu

###Prerequisites:
•	Sublime Text

###Instructions to build and deploy web aplication:
1.      Clone the repository from GitHub:

		$ git clone git://github.com/SpringSource/greenhouse.git
2.      Navigate into the cloned repository directory:

		$ cd
3.      The project uses [Maven](http://maven.apache.org/) to build:

		$ mvn clean install
4.      Use Catalina homebase for all buiding purposes.
5.      Gradle TestNG must be turned on for running the unit tests as well as integration tests.
6.	Copy the application’s directory from its location into $CATALINA_HOME\webapps directory.
7.	Restart the server, the application is deployed with the context path is name of the directory you copied

###Instructions to run unit and/or integration tests:
1.	All tests are written in Employeeirectorytests.java
2.	Run the application using gradle.
3.	Use testNG to run the tests locally.

###E-Commerce Web Application 
1. Created an e-commerce website for users to login/logout into their account 
2. Provided the functionality of browsing and searching and view product.

