# Oracle jdbc driver

Run the following command to add the driver to your local maven repository:

    mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 \
    -Dversion=12.1.0.1 -Dpackaging=jar -Dfile=ojdbc7-12.1.0.1.jar -DgeneratePom=true

Then your can add to your pom.xml:

    <dependency>
        <groupId>com.oracle</groupId>
        <artifactId>ojdbc7</artifactId>
        <version>12.1.0.1</version>
    </dependency>
