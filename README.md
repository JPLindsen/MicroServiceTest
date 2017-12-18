# General steps to include H2O pojo in microservice
- download H2O pojo and genmodel.jar

- include genmodel.jar as follows:

**sudo mvn install:install-file -Dfile=/home/<USER>/micro_service_test/simpleservice/repo/com/h2o/h2o-genmodel/0.1/h2o-genmodel.jar -DgroupId=com.h2o -DartifactId=h2o-genmodel -Dversion=0.1 -Dpackaging=jar**

And add these lines to the project's pom.xml file:

```xml
<dependency>
	<groupId>com.h2o</groupId>
	<artifactId>h2o-genmodel</artifactId>
	<version>0.1</version>
</dependency>
```

- Add this line:

**package com.thoughtmechanix.simpleservice;**

to the top of the H2O pojo file

- In main application file, update model name and align input-definition.
