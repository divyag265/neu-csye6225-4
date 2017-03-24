aws iam create-policy --policy-name CodeDeploy-EC2-S3 --policy-document file://CodeDeployEC2.json
aws iam create-policy --policy-name Travis-Upload-To-S3 --policy-document file://TravisUploadToS3.json
aws iam create-policy --policy-name Travis-Code-Deploy --policy-document file://TravisCodeDeploy.json
echo "\n-------------End of script\n-------------"