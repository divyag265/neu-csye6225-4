aws iam create-user --user-name travis

aws iam create-access-key --user-name travis

aws iam attach-user-policy --policy-arn arn:aws:iam::647187754786:policy/Travis-Upload-To-S3 --user-name travis
aws iam attach-user-policy --policy-arn arn:aws:iam::647187754786:policy/Travis-Code-Deploy --user-name travis

aws iam attach-user-policy --policy-arn arn:aws:iam::647187754786:policy/AssumeContinuousDeliveryRoles --user-name travis
