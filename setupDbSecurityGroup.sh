securityGroups=$(aws ec2 describe-security-groups | jq -r '.SecurityGroups[].GroupName' )
echo $securityGroups
for var in $securityGroups
do
	if test "$var" = "db"
	then
		echo "\n\n------------------------------------\nSecurity Group \"DB\" already exists, \n     Deleting and Creating again\n------------------------------------\n"
		aws ec2 delete-security-group --group-name db
	fi		
done

webId=$(aws ec2 describe-security-groups --group-name web | jq -r '.SecurityGroups[].GroupId')
vpcId=$(aws ec2 describe-vpcs| jq -r '.Vpcs[0].VpcId')
GroupId=$(aws ec2 create-security-group --group-name db --description db --vpc-id $vpcId | jq -r '.GroupId')
aws ec2 authorize-security-group-ingress --group-id $GroupId --protocol tcp --port 3306 --source-group $GroupId
aws ec2 authorize-security-group-ingress --group-id $GroupId --protocol all --source-group $webId

