SIMPLE CHATTING SYSTEM
======================

Technologies: JSP, Servlet, AJAX/fetch, JDBC, MySQL, HTML/CSS
Target: Apache Tomcat 10.1+ and Java 11+

DATABASE
--------
Host: db01.dbhost.dev
Port: 5051
Database: db_454pzrx5k
Username: user_454pzrx5k

Run database.sql against the supplied database. The SQL creates users/messages tables and two demo accounts:
user1 / 1234
user2 / 1234

ECLIPSE
-------
1. Install JDK 11+ and Apache Tomcat 10.1+.
2. Eclipse -> File -> Import -> Maven -> Existing Maven Projects.
3. Select this ChatSystem folder.
4. Maven should download MySQL Connector/J automatically.
5. Add the project to the Tomcat server.
6. Start Tomcat.
7. Open: http://localhost:8080/ChatSystem/login.jsp

If your Eclipse project is not using Maven, run `mvn package` and deploy target/ChatSystem.war to Tomcat/webapps.

DEMO
----
Open two browser windows:
Window 1: user1 / 1234, receiver user2
Window 2: user2 / 1234, receiver user1
Messages are saved in MySQL and retrieved through AJAX without refreshing the page.

NOTE
----
The sample connection credentials are included because they were supplied for this project. Do not commit database credentials to a public repository.
