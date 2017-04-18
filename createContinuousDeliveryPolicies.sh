aws iam create-policy --policy-name CodeDeploy-EC2-S3 --policy-document file://PolicyServerEC2.json

aws iam create-policy --policy-name Travis-Upload-To-S3 --policy-document file://PolicyTravisS3.json

aws iam create-policy --policy-name Travis-Code-Deploy --policy-document file://PolicyTravisCodeDeploy.json

echo "created new policies"

