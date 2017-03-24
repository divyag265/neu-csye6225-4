bucketName="code-deploy.neu-csye6225-spring2017-team-2.com"
aws deploy create-application --application-name CRMMavericksProject
aws deploy create-deployment-group --application-name CRMMavericksProject \
--deployment-group-name Mavericks --deployment-config-name CodeDeployDefault.OneAtATime \
--deployment-style deploymentType=IN_PLACE,deploymentOption=WITHOUT_TRAFFIC_CONTROL \
--ec2-tag-filters Key=RaseswariKey,Value=Assignment7,Type=KEY_AND_VALUE \
--service-role-arn arn:aws:iam::381691501957:role/CodeDeployServiceRole

aws deploy push \
  --application-name CRMMavericksProject \
  --description "This is a revision for the application CRMMavericksProject" \
  --ignore-hidden-files \
  --s3-location s3://code-deploy.neu-csye6225-spring2017-team-2.com/CRMMavericksProject.zip \
  --source .

aws deploy create-deployment --application-name CRMMavericksProject \
  --s3-location bucket=code-deploy.neu-csye6225-spring2017-team-2.com, \
  key=CRMMavericksProject.zip,bundleType=zip,eTag=1139909163df74b0d359da9bece8145e,version=rOtfksRsvP.1cKdAKlNpz8Wv.e2L6Bl. \
  --deployment-group-name Mavericks --deployment-config-name CodeDeployDefault.OneAtATime --description "<description>"

aws deploy list-application-revisions --application-name CRMMavericksProject

aws deploy register-application-revision --application-name CRMMavericksProject \
  --description "Revised CRMMavericksProject application" --s3-location \
  bucket=code-deploy.neu-csye6225-spring2017-team-2.com,key=CRMMavericksProject.zip,bundleType=zip,eTag=1139909163df74b0d359da9bece8145e

echo "\n-------------End of script\n-------------"