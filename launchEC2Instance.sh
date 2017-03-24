webId=$(aws ec2 describe-security-groups --group-name web | jq -r '.SecurityGroups[].GroupId')
aws ec2 run-instances --image-id ami-5ac2cd4d --count 1 --instance-type t2.micro --associate-public-ip-address --security-group-ids $webId --block-device-mappings '[{"DeviceName":"/dev/sdb","Ebs":{"VolumeSize":10,"DeleteOnTermination":true,"VolumeType":"standard"}}]' --key-name MyKey
echo -n "\n\n\n\n Sleeping for 36 seconds"
sleep 3 
echo -n "." 
sleep 3 
echo -n "." 
sleep 3 
echo -n "." 
sleep 3 
echo -n "."
sleep 3 
echo -n "." 
sleep 3 
echo -n "." 
sleep 3 
echo -n "." 
sleep 3 
echo -n "."
echo -n "."
sleep 3 
echo -n "." 
sleep 3 
echo -n "." 
sleep 3 
echo -n "." 
sleep 3 
echo -n "."
clear

aws ec2 describe-instances --filters "Name=instance-state-name,Values=running" | jq -r ".Reservations[0].Instances[0].PublicIpAddress"
instanceId=$(aws ec2 describe-instances --filters "Name=instance-state-name,Values=running" | jq -r ".Reservations[0].Instances[0].InstanceId")

aws iam create-role --role-name CodeDeployEC2ServiceRole --assume-role-policy-document file://MyRole.json

# aws iam attach-role-policy --role-name CodeDeployEC2ServiceRole --policy-arn arn:aws:iam::aws:policy/service-role/AWSCodeDeployRole


aws iam attach-role-policy --role-name CodeDeployEC2ServiceRole --policy-arn arn:aws:iam::381691501957:policy/CodeDeploy-EC2-S3

aws iam create-instance-profile --instance-profile-name CodeDeployEC2InstanceProfile
aws iam add-role-to-instance-profile --instance-profile-name CodeDeployEC2InstanceProfile --role-name CodeDeployEC2ServiceRole

aws ec2 associate-iam-instance-profile --instance-id $instanceId --iam-instance-profile Name=CodeDeployEC2InstanceProfile

aws ec2 create-tags --resources $instanceId --tags Key=RaseswariKey,Value=Assignment7

# echo "Command To get the name of the IAM instance profile you created for the IAM role named CodeDeployEC2ServiceRole"
# aws iam list-instance-profiles-for-role --role-name CodeDeployEC2ServiceRole --query "InstanceProfiles[0].InstanceProfileName" --output text


