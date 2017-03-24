userName="travis"
accessKeyList=$(aws iam list-access-keys --user-name $userName | jq -r ".AccessKeyMetadata[].AccessKeyId")
for accessKey in $accessKeyList
do
    aws iam delete-access-key --access-key-id $accessKey --user-name $userName
done

aws iam delete-login-profile --user-name $userName

policyList=$(aws iam list-attached-user-policies --user-name $userName | jq -r ".AttachedPolicies[].PolicyArn")
for policy in $policyList
do
    aws iam detach-user-policy --user-name $userName --policy-arn $policy
done

userGroupList=$(aws iam list-groups-for-user --user-name $userName | jq -r ".Groups[].GroupName")
for groupName in $userGroupList
do
    aws iam remove-user-from-group --group-name $groupName --user-name $userName
done

aws iam delete-user --user-name $userName

echo "\n-------------End of script\n-------------"