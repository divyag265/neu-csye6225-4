dbInstanceList=$(aws rds describe-db-instances | jq -r '.DBInstances[].DBInstanceIdentifier')
for var in $dbInstanceList
do
	if test "$var" = "csye6225"
	then
		echo "\n\n---------------------------------------------------\n MySQL RDS Instance named \"csye6225\" already exists\n              Script will now exit\n---------------------------------------------------\n"
		exit	
	fi
done



webId=$(aws ec2 describe-security-groups --group-name web | jq -r '.SecurityGroups[].GroupId')
aws rds create-db-instance --db-name csye6225 --allocated-storage 100 --vpc-security-group-ids $webId --db-instance-identifier csye6225 --no-multi-az --no-publicly-accessible --db-instance-class db.t2.micro --engine mysql --master-username csye6225 --master-user-password csye6225
