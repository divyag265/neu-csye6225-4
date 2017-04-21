aws ec2 run-instances --image-id ami-49c9295f --user-data file://UserData.sh --count 1 --instance-type t2.micro --associate-public-ip-address --enable-api-termination --block-device-mappings '[{"DeviceName":"/dev/sdb","Ebs":{"VolumeSize":10,"DeleteOnTermination":true,"VolumeType":"standard"}}]' --key-name MyKey
echo -n "\n\n\n\n Sleeping for 20 seconds"
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "."
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "."
echo -n "."
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "."
clear
echo "Your Public IP Address : "
aws ec2 describe-instances --filters "Name=instance-state-name,Values=running" | jq -r ".Reservations[0].Instances[0].PublicIpAddress"

=======
aws ec2 run-instances --image-id ami-49c9295f --count 1 --instance-type t2.micro --associate-public-ip-address --enable-api-termination --block-device-mappings '[{"DeviceName":"/dev/sdb","Ebs":{"VolumeSize":10,"DeleteOnTermination":true,"VolumeType":"standard"}}]' --key-name MyKey
echo -n "\n\n\n\n Sleeping for 20 seconds"
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "."
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "."
echo -n "."
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "." 
sleep 2 
echo -n "."
clear
echo "Your Public IP Address : "
aws ec2 describe-instances --filters "Name=instance-state-name,Values=running" | jq -r ".Reservations[0].Instances[0].PublicIpAddress"
echo "Europe/Dublin" > /etc/timezone 
sudo ln -sf /usr/share/zoneinfo/UTC /etc/localtime
