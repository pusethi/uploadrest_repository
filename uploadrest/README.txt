Project Name: uploadrest
Author: Puneet Kaur Sethi
Date: 08/29/2017

###################
Project Description
###################
The application uploadrest is a rest webservice developed using spring boot. The application has the capability to upload a file in the file system and store the file meta data information in internal H2 DB. The application also provides a webservice to fetch all the records stored in the internal DB. Please find the description of the 2 webservices below.

1) Upload File and Meta data persist Webservice
   Name: uploadFileAndPersistMetaData
   Controller Name: fileController
   URL for testing on Swagger: /file/upload
   Service Type: POST   
   Behavior: This webservice expects the following inputs from the user
			 a) File to be uploaded (mandatory)
			 b) File Name (mandatory)
			 c) Author Name (optional)
			 d) Description (optional)
			 Once these are entered, the code will generate a database entry with the metadata information (b,c,d). It will also create a file_id which is a primary key and an updateTimeStamp field. It will also store the file (a) in the file system. The path for storing the file can be mentioned in the application.xml file (\uploadrest\uploadrestweb\src\main\resources\application.properties). 
			 For every new file name, the application will create a new record in the DB. If user uploads a file with an existing file name, in this case no new record will be generated. The existing record for that filename will be updated.
			 
2) Fetch all file meta data records from the DB
   Name: getAllFileMetaDataRecords
   Controller Name: fileController
   URL for testing on Swagger: /file/upload/all
   Service Type: GET
   Behavior: This webservice expects no input from the user. It will fetch all the file meta record from the database. As we are using an in memory database for this application, this webservice will help the user view the entries and updates made to the database using the uploadFileAndPersistMetaData webservice.

###############################
User Configurations For Testing
###############################
By default the file store path in the code is "C:\\uploadedFiles\\". The files will be uploaded in this path. The user can view the files in this path after uploading it through the rest webservice. If the user is using a non windows operating system or wants to change the file store path, it can be configured in the following way:
1) Open the application.properties file in the following path: "\uploadrest\uploadrestweb\src\main\resources\application.properties".
2) Update the new path in the variable "multipart.loaction".
	Current:
	multipart.location=C:\\uploadedFiles\\
	New Path:
	multipart.location=new\\path\\
3) Compile and Build the code after this change.

#######################
Maven Build Instruction
#######################
1. In the command prompt go to parent project \uploadrest
2. Execute--> "mvn clean install" command
3. Get uploadrestweb.war from Location - \uploadrest\uploadrestweb\target

###################
Application Testing
###################
Application can be tested in 2 ways:
1) By running Junit methods in the code.
2) Deploying the war file on any server (example:tomcat) and testing the webservice using tools like swagger, postmaster, etc.

#################
Tomcat Deployment
#################
1. Drop the war file "uploadrestweb.war"  at the location - \apache-tomcat-8.0.33\webapps
2. In the command prompt go to location - \apache-tomcat-8.0.33\bin
3. Execute--> "catalina run" (to start) and Ctrl + C (to stop) the server.

##########################################
Swagger UI Access - to test the webservice
##########################################
Note - Swagger ui is configured to test and review the service.
1. Deploy the application on tomcat following the steps mentioned above.
2. Access this URL in the browser - http://localhost:8080/uploadrestweb/swagger-ui.html
3. Here the user can view the controller called "file controller" and inside that you can see the following 2 webservices:
	a) /file/upload - POST webservice
	b) /file/upload/all - GET webservice
4. The user can enter the parameters in this and view the output.

###################
Testing using Junit
###################

1. Application can be tested using Junit test cases defined for controller, service and repository method respectively.
2. Junit test cases will consider the file "\uploadrest\uploadrestweb\src\test\resources\testFile\Sample.doc" as sample file for upload.
3. After the Junit for upload operation is run the uploaded file gets stored in C:\uploadedFiles Location.
4. After the upload webservice will return the json object with id, description, authorName, fileName, uploadTimeStamp Metadata.
   For e.g- {
			  "id": 1,
			  "description": "Sample file for Junit Testing.",
			  "authorName": "Puneet Kaur Sethi",
			  "fileName": "Sample",
			  "createdTimeStamp": "2017-08-29T14:58:29-04:00"
			}

#######
Logging
#######
1. By default, the logs will be generated at "C:/uploadLogs" location.
2. The log file name is uploadrestweb.log.
3. If the user wants to change the log location, it can be done by altering the log configuration file located at
   "uploadrest\uploadrestweb\src\main\resources\log4j2.xml" location.
4. Please update the new location in following property in the file:
   <Properties>
        <Property name="log-path">NEW_LOG_PATH</Property>
    </Properties>		








