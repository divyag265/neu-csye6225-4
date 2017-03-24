aws iam create-user --user-name travis
aws iam create-access-key --user-name travis
aws iam attach-user-policy --policy-arn arn:aws:iam::381691501957:policy/Travis-Upload-To-S3 --user-name travis
aws iam attach-user-policy --policy-arn arn:aws:iam::381691501957:policy/Travis-Code-Deploy --user-name travis
aws iam attach-user-policy --policy-arn arn:aws:iam::381691501957:policy/AssumeContinuousDeliveryRoles --user-name travis
echo "\n-------------End of script\n-------------"