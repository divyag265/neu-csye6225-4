echo "Beginning of script/Creating IAM Roles"

echo "Creating Role CodeDeployEC2ServiceRole"
aws iam create-role --role-name CodeDeployEC2ServiceRole --assume-role-policy-document file://MyRole.json

echo "Attaching policy CodeDeploy-EC2-S3 to CodeDeployEC2ServiceRole"
aws iam attach-role-policy --role-name CodeDeployEC2ServiceRole --policy-arn arn:aws:iam::381691501957:policy/CodeDeploy-EC2-S3


echo "Creating Role CodeDeployServiceRole"
aws iam create-role --role-name CodeDeployServiceRole --assume-role-policy-document file://MyRole.json

echo "Attaching policy AWSCodeDeployRole to CodeDeployServiceRole"
aws iam attach-role-policy --role-name CodeDeployServiceRole --policy-arn arn:aws:iam::aws:policy/service-role/AWSCodeDeployRole


echo "Creating required policy AssumeContinuousDeliveryRoles"
aws iam create-policy --policy-name AssumeContinuousDeliveryRoles --policy-document file://AssumeRoles.json

echo "End of Script"