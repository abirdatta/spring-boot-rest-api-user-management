This is a spring boot restful webservices application, dependant on mysql DB.

Steps to run the application - 
docker-compose up

Need to have docker and docker-compose installed to run the app easily. Otherwise you have to generate the jar(mvn clean install) and install mysql separately and give the proper mysql connection properties as JVM arguments while running the jar.

The restful apis can be obtained from the swagger ui - http://localhost:9000/swagger-ui.html#/user-controller

Some user type data is is already there in the mysql db image. So the get request -  http://localhost:9000/user/roles should return a json.

There is a basic authentication, which currently has hardcoded username and password in properties files - abir/password

Spring actuator is enabled and running on port 9001 - http://localhost:9000/health (/metrics, /env, /logfile etc.etc.)
