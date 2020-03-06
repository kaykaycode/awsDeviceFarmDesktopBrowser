# AWS DeviceFarm for Desktop Browsers
AWS Device Farm Desktop Browser using Selenium, TestNG Java

AWS Device Farm for Browser Testing with Selenium using aws-sdk-java
Selenium + Java + AWS SDK

# Tools:
Java 1.8
Maven 
Selenium 
TestNG

# AWS Setup:
1. Create AWS Free Tier account 
2. Create an IAM user and follow best practices by creating multi-factor authentication, follow this https://docs.aws.amazon.com/directoryservice/latest/admin-guide/setting_up_create_iam_user.html
3. Give access to Device Farm Service for your IAM user
4. Install AWS CLI, follow this https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html

        # Why AWS CLI ?
        In-order to connect and consume AWS Devicefarm Service via REST or HTTP protocol secretly its required to use AWS STS. 
        For authentication AWS has 3 credentials for its clients to use : 
        i. Access Key 
        ii. Secret Key 
        iii. Session token

AWS STS stands for AWS Security Token Service

5. Login as root user, Access Key and Secret Key can be generated from AWS Console under IAM service, please see here : https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_access-keys.html#Using_CreateAccessKey 
6. To create Session token the AWS CLI is required. So, how to generate Session Token from AWS CLI:
7. Run aws configure command, you will be prompted to enter Access Key ID and Secret Key ID (follow this: https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_access-keys.html#Using_CreateAccessKey)
8. aws configure command will creates config and credentials file in profile folder (C:\Users\user\.aws\). Credentials file holds Access Key and Secret Key 
9. Now, run aws sts get-session-token command which will generate Access Key, Secret Key and Session Token 
              Note: get-session-token sub-command has options, they are 
              [ - duration-seconds <value>]
              [ - serial-number <value>]
              [ - token-code <value>]
              [ - cli-input-json <value>]
              [ - generate-cli-skeleton <value>]
Please refer : https://docs.aws.amazon.com/cli/latest/reference/sts/get-session-token.html

10. aws sts get-session-token command will create a Session Token 
11. Copy and paste Access Key ID, Secret Key and Session Token into ./src/test/java/resources/awscredentials/AwsCredentails.properties file
12. Login as IAM user and go to Device Farm, Select Desktop Browser Project then enter the project name and click on Create Project.
13. Create a Desktop Browser Project and ensure to note down the Project ARN and pasteit into  ./src/test/java/resources/awscredentials/AwsCredentails.properties file

ARN stands for Amazon Resource Name, more info on ARN: https://docs.aws.amazon.com/general/latest/gr/aws-arns-and-namespaces.html


