# neu-csye6225-4
Cloud Computing- Team-6

#README File

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
•	Node.js
•	Express.js
•	Angular.js
•	Testng boot client
•	Sublime Text

###Instructions to build and deploy web aplication:
1.      Clone the repository from GitHub:

		$ git clone git://github.com/SpringSource/greenhouse.git
2.      Navigate into the cloned repository directory:

		$ cd
3.      This contains mean stack application.

		$ run npm install
4.      This will install the dependencies.

5.      Gradle TestNG must be turned on for running the unit tests as well as integration tests.
6.	Copy the application’s directory from its location into $CATALINA_HOME\webapps directory.
7.	Restart the server, the application is deployed with the context path is name of the directory you copied

###Instructions to run unit and/or integration tests:
1.	All tests are written in Employeeirectorytests.java
2.	Run the application using gradle.
3.	Use testNG to run the tests locally.

###Link to TravisCI setup.
https://travis-ci.com/divyag265/neu-csye6225-4/jobs/63410749/config


###Domain Name.
neu-csye6225-spring2017-team-6.com.

###Step to Create a public hosted zone using Amazon Route 53.
1.  Configured Namecheap to use custom nameservers provided by Amazon Route 53.
2.  Created a type A record for your domain with TTL of 1 minute. Type A record should point to IP address 127.0.0.1.

###Link to IAM console.
https://neu-csye6225-spring2017-team-6.signin.aws.amazon.com/console

###IAM Users:
1.	Created Graders group for instructor and ta and gave READ ONLY permission.
2.	Created Admins group for our team memebers and gave full access for it.

###Billing alarm
•	Created billing alarm so that team members are notified if AWS usage exceeds $25/month.

###EC2 instance creation via shell script with following configuration
1.	Amazon Machine Image (AMI): Ubuntu Server 14.04 LTS (HVM), SSD Volume Type - ami-5ac2cd4d
2. 	Instance Type: t2.micro
3. 	Subnet: us-east-1e
4.	Auto-assign Public IP: Yes
5. 	Protect against accidental termination: Enabled
6. 	Root Volume Size: 10
7. 	Root Volume Type: Magnetic
8.	 Configure Security group to allow SSH (port 22), HTTP (port 80) and HTTPS (port 443) traffic on TCP protocol on their respective default ports from anywhere in the world.

###Route53 with a type A record 
Created A record of ec2.neu-csye6225-spring2017-team-6.com pointing to public IP address with TTL of 300.

###E-Commerce Web Application 
1. Created an e-commerce website for users to login/logout into their account 
2. Provided the functionality of browsing and searching and viewing product
