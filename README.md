# cerioscoop-web
We're Cerios! A young company with about 60 employees that helps organisations to successfully realize complex IT project. Cerios started educating Java software developers and came up with the idea for them to create a cinema web application. It is intended that the web application will be gradually expanded further over time. In the near future there will also be testers participating in this project to optimally stimulate the learning curve and collaboration.

## Installation
* Download eclipse from https://eclipse.org
* Install and setup [Liberty Profile Server](https://developer.ibm.com/wasdev/getstarted/)
click [here](https://developer.ibm.com/wasdev/docs/developing-applications-wdt-liberty-profile/) for more information about Liberty Profile. 
* Make sure you have installed the eclipse tools for websphere liberty (https://developer.ibm.com/wasdev/downloads/)!!  This will also incluse the maven plugin etc.
* 'Import' > 'projects from git' > 'clone url' (use [this link](https://github.com/RonSanders/cerioscoop-web.git)) > 'import as general project' > 'finish'
* 'Configure' > 'convert to maven project'
* Instead of the previous two steps: Import > Maven > Checkout Maven Projects from SCM and select git and the git link https://github.com/RonSanders/cerioscoop-web.git. (if necessary install the egit m2e connector from the link in the lower right corner)
* 'Properties' > 'project facets' and add 'dynamic web module'
* ~~'Build path' > 'configure buildpath' > 'add external JARs' > 'JRE system library' or make sure it is present (for example C:\Program Files\Java\jdk1.8.0_71)~~
Eclipse maven plugin is smart enough to extract correct settings for the pom.xml (and if you change your pom.xml file, just do a rightclick>maven->update project)

* Install and setup [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
* Install and setup [HeidiSQL](http://www.heidisql.com/download.php), useful and reliable tool designed for web developers using the popular MySQL server
* Visualize and manage your repositories through [SourceTree](https://www.atlassian.com/software/sourcetree)'s simple interface or simply use github's own visualisation tool [Github Desktop](https://desktop.github.com)
* Make sure you have the Maven plugin for Eclipse installed (this comes with the wasdev plugin)


## Contributors
* Ron Sanders
* Rutger van Velzen
* Marcel Groothuis
* Friso Schutte
* Remco Hoetner
* Gijsbert Peijs
* Rob Bosman
* Bob van Zeist
