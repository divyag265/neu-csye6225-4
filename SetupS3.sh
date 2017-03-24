bucketlist=$(aws s3api list-buckets | jq -r '.Buckets[].Name')
for var in $bucketlist
do
	if test "$var" = "s3.neu-csye6225-spring2017-team-6.com"
	then
		echo "Name Already Exists in your Account, Please Delete that instance before creating this Bucket again. \n\n\t----------------------\n\tScript will exit now!!\n\t----------------------\n"
		exit
	fi
done
output=$(aws s3api create-bucket --bucket s3.neu-csye6225-spring2017-team-6.com --region us-east-1 2>/dev/null)
if [ $? -ne 0 ]
then
	echo "The name has been chosen by another user, Please choose another name. \n\n\t----------------------\n\tScript will exit now!!\n\t----------------------\n"
	exit
fi
aws s3api put-bucket-versioning --versioning-configuration Status=Enabled --bucket s3.neu-csye6225-spring2017-team-6.com
aws s3api put-bucket-tagging --bucket s3.neu-csye6225-spring2017-team-6.com --tagging file://Tags.json
aws s3api put-bucket-acl --bucket s3.neu-csye6225-spring2017-team-6.com --grant-read 'uri="http://acs.amazonaws.com/groups/global/AllUsers"'

echo "\n\nBucket succesfully created! \n\n\t--------------\n\tEND OF SCRIPT!\n\t--------------\n"
