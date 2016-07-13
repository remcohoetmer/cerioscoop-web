[![Build Status](https://travis-ci.org/Cerios/cerioscoop-web.svg?branch=develop)](https://travis-ci.org/Cerios/cerioscoop-web)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4e4dbb7c808b49ae83ab0ba8d45bc96c)](https://www.codacy.com/app/Cerios/cerioscoop-web?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=CeriosDev/cerioscoop-web&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/4e4dbb7c808b49ae83ab0ba8d45bc96c)](https://www.codacy.com/app/Cerios/cerioscoop-web?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=CeriosDev/cerioscoop-web&amp;utm_campaign=Badge_Coverage)


# cerioscoop-web
We're Cerios! A young company with about 60 employees that helps organisations to successfully realize complex IT project. Cerios started educating Java software developers and came up with the idea for them to create a cinema web application. It is intended that the web application will be gradually expanded further over time. In the near future there will also be testers participating in this project to optimally stimulate the learning curve and collaboration.

## Installation
* Download eclipse from https://eclipse.org
* Install and setup [Liberty Profile Server](https://developer.ibm.com/wasdev/getstarted/), click [here](https://developer.ibm.com/wasdev/docs/developing-applications-wdt-liberty-profile/) for more information about Liberty Profile.
* Instal the Eclipse tools for [WebSphere Liberty](https://developer.ibm.com/wasdev/downloads/)
* '_Import_' > '_Maven_' > '_Checkout Maven Projects from SCM_' and select git with [this](https://github.com/RonSanders/cerioscoop-web.git) URL
* '_Properties_' > '_Project facets_', check option '_Dynamic Web Module_' __and then__ click '_Further configuration available..._' to set _Content directory_ to `src/main/webapp`.
* Install and setup [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
* Run the script `src/main/scripts/mysql-schema.sql`
* Run the script `src/main/scripts/mysql-testdata.sql`
* Manage your database with [HeidiSQL](http://www.heidisql.com/download.php)
* Visualize and manage your repositories with [SourceTree](https://www.atlassian.com/software/sourcetree) or use [Github Desktop](https://desktop.github.com)
* Run automated browser testscenario's with [Selenium Client & Webdriver](http://www.seleniumhq.org/download/) or add [Selenium Maven dependency](Maven dependency) and check the specific "third party" browser drivers for [Google Chrome](http://chromedriver.storage.googleapis.com/index.html?path=2.22/)

##### Configure DataSource
In WebSphere Liberty Profile `server.xml` add this (chck and adjust properties!):
```xml
<library id="MySQL_lib">
  <fileset dir="C:/" includes="mysql-connector-java-5.1.39-bin.jar"/>
</library>

<dataSource jndiName="jdbc/cerioscoop" type="javax.sql.DataSource">
  <jdbcDriver libraryRef="MySQL_lib"/>
  <properties serverName="localhost" portNumber="3306" databaseName="cerioscoop_db" user="root" password="{xor}MjcnaW9vZmY="/>
</dataSource>
```

## Contributors
* Ron Sanders
* Rutger van Velzen
* Marcel Groothuis
* Friso Schutte
* Remco Hoetner
* Gijsbert Peijs
* Rob Bosman
* Bob van Zeist
