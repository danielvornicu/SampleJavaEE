JSF (WebApplication - JSF basic with Jetty Server):

Il faut enlever le commentaire sur la dependence jetty-annotations dans pom.xml et mettre en commentaire les dependences Glassfish Embedded(conflit)
Dependences necessaires for Jetty:
 - javax.servlet-api(3.1)
 - jsf-api et jsf-impl(2.2.10)
 - jetty-jsp, jetty-webapp
http://localhost:8090/crm/bonjour.xhtml

JPA test with EclipseLink provider in Glassfish Embedded Java EE container:

Dependences necessaires:
org.eclipse.persistence.jpa (2.7.0)
glassfish-embedded-all(4.0)
h2 (Database H2 Embedded) (1.4.196)
Fichier persistence: persistenceGlassfish.xml
Maven pom: pomGlassfishEmbedded.xml

Glassfish Embedded(Application) - deploy on Glassfish Embedded et call ClientService bean in main() function
Glassfish Embedded Test(ClientJpaDAOTest) - test ClientService in a test class

Wildfly Java EE:
Dependences necessaires:
Fichier persistence: persistenceWildfly.xml
Maven pom: pomWildfly.xml

IntelliJ -> Project Structure -> Artifacts:
1. SampleJavaEE:war(type Web application: archive) with output directory: D:\java\projects.intellij\SampleJavaEE\target
   - used to create a .war archive
2. SampleJavaEE:war exploded(type Web application: exploded) with output directory: D:\java\projects.intellij\SampleJavaEE\target\SampleJavaEE-1.0.0.war
   - in Output Layout add WEB-INF forder(with classes and lib sub-folder)
   - used to create a directory for deploy
3. wildfly exploded deploy(type Web application: exploded) with output directory: D:\java\apps\jboss\wildfly-12.0.0.Final\standalone\deployments\SampleJavaEE-1.0.0.war
   - used to copy the create directory in wildfly deployments folder
   - also used in server configuration in Deployment tab to deploy the application


JSF:
http://localhost:8090/SampleJavaEE/bonjour.xhtml  - page de test JSF sur WildFly

Datasource connection url in Wildfly (standalone.xml)
<datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:h2:~/test;DB_CLOSE_ON_EXIT=FALSE</connection-url>
    <driver>h2</driver>
    <security>
        <user-name>sa</user-name>
        <password>sa</password>
    </security>
</datasource>

The file is saved in: C:/Users/d.vornicu/test.mv.db

Others options:
<connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url> - in memory
<connection-url>jdbc:h2:file:test;INIT=RUNSCRIPT FROM 'classpath:insertData.sql';DB_CLOSE_ON_EXIT=FALSE</connection-url>
<connection-url>jdbc:h2:tcp://localhost/~/test</connection-url>  - for server mode (remote connections) using TCP/IP (don't work for me)

IntelliJ database access: using Databases panel, + , Datasource from URL: jdbc:h2:~/test, User: sa, Pass: sa
Properties->button Test Connnection - acces exclusiv to test.mv.db file(Wildfly use a lock)
Then Open console: select * from client;

H2 Web console database acces: d:\java\apps\jboss\wildfly-12.0.0.Final\modules\system\layers\base\com\h2database\h2\main\
Click h2-1.4.193.jar Generic H2(Server), URL JDBC: jdbc:h2:~/test, user=sa, pass: sa

JSF + Managed bean + Service + entity:
http://localhost:8090/SampleJavaEE/   - liste
http://localhost:8090/SampleJavaEE/pages/clientFiche.xhtml  -new
http://localhost:8090/SampleJavaEE/pages/clientConsult.xhtml -consult
http://localhost:8090/SampleJavaEE/pages/clientFiche.xhtml  -edit


REST API:
http://localhost:8090/SampleJavaEE/api/clients
http://localhost:8090/SampleJavaEE/api/clients/1 -consult
http://localhost:8090/SampleJavaEE/api/clients/1/delete
http://localhost:8090/SampleJavaEE/api/clients/new          create POST
http://localhost:8090/SampleJavaEE/api/clients/1/edit       update POST


A simple client for REST api(client jQueryversion web):
http://localhost:8090/SampleJavaEE/pages/rspages/rsclientjQuery.xhtml


http://jean-luc.massat.perso.luminy.univ-amu.fr/ens/arch-app/tp-JSF.html