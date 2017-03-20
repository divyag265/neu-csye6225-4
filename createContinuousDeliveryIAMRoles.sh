#!/bin/bash


JAVA_HOME=/usr/lib/jvm/jre

export JAVA_HOME

aws iam create-role --codeDeployEC2ServiceRole --assume-role-policy-document file://Role-Trust-Policy.json


