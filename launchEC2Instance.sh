webId=$(aws ec2 describe-security-groups --group-name web | jq -r '.SecurityGroups[].GroupId')
aws ec2 run-instances --image-id ami-5ac2cd4d --count 1 --instance-type t2.micro --associate-public-ip-address --security-group-ids $webId --block-device-mappings '[{"DeviceName":"/dev/sdb","Ebs":{"VolumeSize":10,"DeleteOnTermination":true,"VolumeType":"standard"}}]' --key-name MyKey
echo -n "\n\n\n\n Sleeping for 20 seconds"
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
echo "Your Public IP Address : "
aws ec2 describe-instances --filters "Name=instance-state-name,Values=running" | jq -r ".Reservations[0].Instances[0].PublicIpAddress"

