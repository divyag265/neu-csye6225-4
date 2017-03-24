roleList=('CodeDeployEC2ServiceRole' 'CodeDeployServiceRole')
for roleName in ${roleList[@]}
do
	policyList=$(aws iam list-attached-role-policies --role-name $roleName | jq -r ".AttachedPolicies[].PolicyArn")
	for policy in $policyList
	do
	    aws iam detach-role-policy --role-name $roleName --policy-arn $policy
	done

	aws iam delete-role --role-name $roleName
done

policyArn='arn:aws:iam::381691501957:policy/AssumeContinuousDeliveryRoles'
policyName='AssumeContinuousDeliveryRoles'
userList=$(aws iam list-entities-for-policy --policy-arn $policyArn | jq -r ".PolicyUsers[].UserName")
for userName in $userList
do
    aws iam detach-user-policy --user-name $userName --policy-arn $policyArn
done
aws iam delete-policy --policy-arn $policyArn

echo -e "\n-------------Ending of the script\n-------------"