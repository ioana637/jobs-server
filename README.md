## Jobs-server 
Spring boot based server for jobs application using maven for dependencies management

## Prerequisites
Make sure that you a mysql service running on port 3306 and that it holds a database/schema named jobs(you can use mysql from xampp) and that you have JAVA 8 installed.
## Run 
Open maven projects from within your IDE if it's not already opened, (ctrl+shift+a, type maven and select maven projects for intellij).
Select lifecycle from login module and select clean, run maven build, then select install, make sure to disable tests, and run maven build again, after it finishes you can run the project. You can use postman to test the endpoints.

## API documentation

Please update the postman collection (Proiect_Colectiv.postman_collection.json) after every request you make.
This way we can provide some knowledge about the requests to our colleagues doing the frontend.
