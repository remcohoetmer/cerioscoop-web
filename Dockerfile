FROM websphere-liberty:webProfile7
COPY target/cerioscoop-web-0.0.1-SNAPSHOT.war /config/apps/cerioscoop-web.war
COPY docker-resources/server.xml /config/server.xml
COPY docker-resources/mysql-connector-java-5.1.39-bin.jar /config/resources/mysql-connector-java-5.1.39-bin.jar
