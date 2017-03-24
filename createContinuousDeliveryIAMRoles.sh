

aws iam create-role --role-name  codeDeployEC2ServiceRole --assume-role-policy-document file://Role-Trust-Policy.json
aws iam attach-role-policy --role-name CodeDeployEC2ServiceRole --policy-arn arn:aws:iam::647187754786:policy/CodeDeploy-EC2-S3
aws iam create-role --role-name CodeDeployServiceRole --assume-role-policy-document file://Role-Trust-Policy.json

aws iam attach-role-policy --role-name CodeDeployServiceRole --policy-arn arn:aws:iam::aws:policy/service-role/AWSCodeDeployRole
aws iam create-policy --policy-name AssumeContinuousDeliveryRoles --policy-document file://AssumeRoles.json