# Air-Companies-Management-System

Requirements:

1. MySQL 5 or higher with a schema 'acms'
2. Java 8 or higher
3. Maven 3 or higher
4. Docker 3.3 or higher

How to run in Docker:

In directory src/main/resources/ copy file application-prod.properties.example to the same directory with a name application-prod.properties
1. In application-prod.properties file specify your MySQL username and password
2. Execute '.\mvnw package -DskipTests=true' in the root repository directory. (Windows Env)
3. 'docker image build -t image-name .'
4. 'docker container run -p 3305:3306 --name mysqldb --network acms-mysql -e MYSQL_ROOT_PASSWORD=your_mysql_password -e MYSQL_DATABASE=acms -d mysql:8'
5. 'docker container run -d --name container-name -p 8080:8080 --network acms-mysql --link mysqldb:mysql image-name'
6. Run Postman and import Collection 'Air Companies Management.postman_collection.json' from project root directory