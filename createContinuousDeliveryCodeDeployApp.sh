

bucketName="code-deploy.neu-csye6225-spring2017-team6.com"


aws deploy create-application --application-name HotelApplication


aws deploy create-deployment-group --application-name HotelApplication \
--deployment-group-name Mavericks --deployment-config-name CodeDeployDefault.OneAtATime \
--deployment-style deploymentType=IN_PLACE,deploymentOption=WITHOUT_TRAFFIC_CONTROL \
--ec2-tag-filters Key=my-key-pair,Value=Assignment7,Type=KEY_AND_VALUE \
--service-role-arn arn:aws:iam::647187754786:role/CodeDeployServiceRole

aws deploy push \
  --application-name HotelApplication \
  --description "This is a revision for the application HotelApplication" \
  --ignore-hidden-files \
  --s3-location s3://code-deploy.neu-csye6225-spring2017-team6.com/HotelApplication.zip \
  --source .

aws deploy create-deployment --application-name HotelApplication \
  --s3-location bucket=code-deploy.neu-csye6225-spring2017-team-6.com, \
  key=HotelApplication.zip,bundleType=zip,eTag=1139909163df74b0d359da9bece8145e,version=rOtfksRsvP.1cKdAKlNpz8Wv.e2L6Bl. \
  --deployment-group-name Mavericks --deployment-config-name CodeDeployDefault.OneAtATime --description "<description>"

aws deploy list-application-revisions --application-name HotelApplication

aws deploy register-application-revision --application-name HotelApplication \
  --description "Revised HotelApplication application" --s3-location \
  bucket=code-deploy.neu-csye6225-spring2017-team6.com,key=HotelApplication.zip,bundleType=zip,eTag=1139909163df74b0d359da9bece8145e
