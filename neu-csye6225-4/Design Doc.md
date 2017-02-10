# neu-csye6225-4
Cloud Computing- Team-6

#Assignment 2

###Team Members:
Sno. | Name | Email
---- | ------------ | -------------
1 | Naveenramkumar Varadarajan | varadarajan.n@husky.neu.edu
2 | Rakshitha Balakrishna | balakrishna.r@husky.neu.edu
3 | Divya Gawade | gawade.d@husky.neu.edu
4 | Mayank kumar Choudhary | choudhary.m@husky.neu.edu

###Prerequisites:
•	Eclipse Oxygen
•	STS 3.83
•	Gradle
•	Testng boot client

###Instructions to build and deploy web aplication:
1.  Use Catalina  homebase for all buiding purposes.
2.  Gradle TestNG must be turned on for running the unit tests as well as integration tests.
3.	Copy the application’s directory from its location into $CATALINA_HOME\webapps directory.
4.	Restart the server, the application is deployed with the context path is name of the directory you copied

###Instructions to run unit and/or integration tests:
1.	All tests are written in Employeedirectorytests.java
2.	Run the application using gradle.
3.	Use testNG to run the tests locally.

###Link to TravisCI setup.
https://travis-ci.com/divyag265/neu-csye6225-4/jobs/63410749/config


###Domain Name.
neu-csye6225-spring2017-team-6.com.


###Link to IAM console.
https://neu-csye6225-spring2017-team-6.signin.aws.amazon.com/console

###IAM Users:
•	Graders are instructor and ta.
•	Admins are our team memebers.

###Billing alarm has been set.

###EC2 instance has been created.

###Route53 with a type A record set to ec2.neu-csye6225-spring2017-team-6.com
