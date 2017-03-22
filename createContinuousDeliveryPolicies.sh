PolicyServerEC2=$(aws iam create-policy --policy-name CodeDeploy-EC2-S3 --policy-document file://PolicyServerEC2.json)

PolicyTravisS3=$(aws iam create-policy --policy-name Travis-Upload-To-S3 --policy-document file://PolicyTravisS3.json)

PolicyTravisCodeDeploy=$(aws iam create-policy --policy-name Travis-Code-Deploy --policy-document file://PolicyTravisCodeDeploy.json)
