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
