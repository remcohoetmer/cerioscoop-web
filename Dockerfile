FROM websphere-liberty:webProfile7
ADD target/cerioscoop-web-0.0.1-SNAPSHOT.war /config/apps/cerioscoop-web.war
ADD docker-resources/server.xml /config/server.xml
ADD docker-resources/mysql-connector-java-5.1.39-bin.jar /config/resources/mysql-connector-java-5.1.39-bin.jar
