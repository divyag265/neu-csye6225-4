#!/bin/bash


JAVA_HOME=/usr/lib/jvm/jre

export JAVA_HOME

aws iam create-role --role-name  codeDeployEC2ServiceRole --assume-role-policy-document file://Role-Trust-Policy.json
aws iam put-role-policy --role-name codeDeployEC2ServiceRole --policy-name CodeDeploy-EC2-S3 --policy-document file://PolicyServerEC2.json 

