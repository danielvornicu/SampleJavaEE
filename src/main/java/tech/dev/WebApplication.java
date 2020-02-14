package tech.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);
//Pas besoin pour Wildfly
/*
	public static void main(String[] args) throws Exception {
		//v1
		Server server = new Server(8090);

		//v2
//		Server server = new Server();
//		ServerConnector httpConnector = new ServerConnector(server);
//		httpConnector.setPort(8090);
//		httpConnector.setHost("localhost");
//		httpConnector.setIdleTimeout(5000);
//		server.addConnector(httpConnector);

		// Web context
		WebAppContext webAppContext = new AliasEnhancedWebAppContext();
		webAppContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		webAppContext.setContextPath("/crm");
		webAppContext.setWelcomeFiles(new String[] { "bonjour.xhtml" });

		//for JSF only
		//First of all the output folder of the application must be added to the WebAppContext's resource collection.
		//In case of a Maven-based project we have to add the "target" directory
		webAppContext.setBaseResource(
				new ResourceCollection(
						new String[] { "./src/main/webapp",  "./target" }));

		//The scan for annotated @ManagedBean classes takes place in the directory "WEB-INF/classes"
		//As this folder doesn't exist in our case, no single managed bean will be detected
		//The concept of resource aliases which provides a neat solution to our problem.
		// To define an alias for "WEB-INF/classes" just do the following:
		webAppContext.setResourceAlias("/WEB-INF/classes/", "/classes/");
		//end for JSF only

		// Start JSF programmatically( or declaratively in web.xml )...
		//initializeJSF(webAppContext);

		//not for JSF
		//webAppContext.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".[^/]*.*\\.jar$");
		//4. Enabling the Annotation based configuration

		//org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
		//classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
		//classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");


		// This webapp will use jsps and jstl. We need to enable the
		// AnnotationConfiguration in order to correctly
		// set up the jsp container
		Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
		classlist.addBefore(
				"org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration" );

		// Set the ContainerIncludeJarPattern so that jetty examines these
		// container-path jars for tlds, web-fragments etc.
		// If you omit the jar that contains the jstl .tlds, the jsp engine will
		// scan for them instead.
		webAppContext.setAttribute(
				"org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				".*//*[^/]*servlet-api-[^/]*\\.jar$|.*//*javax.servlet.jsp.jstl-.*\\.jar$|.*//*[^/]*taglibs.*\\.jar$" );


		// Server
		server.setHandler(webAppContext);
		server.setStopAtShutdown(true);
		server.start();
		LOGGER.debug("Web application ready");
		server.join();
	}

	public static void initializeJSF(WebAppContext context) {
		LOGGER.info("Initializing JSF programmatically ...");

		context.setDisplayName("Primefaces 6.1 on Jetty Embedded 9 Example");
		context.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");

		// JSF parameters ...
		context.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
		context.setInitParameter("com.sun.faces.enableRestoreView11Compatibility", "true");
		context.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		context.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
		context.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
		context.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		context.setInitParameter("defaultHtmlEscape", "true");

		//context.setInitParameter("primefaces.THEME", "redmond");
		context.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "false");

		// Add JSF Listener for initialization ...
		context.addEventListener(new ConfigureListener());

		// JSF Servlet ...
		ServletHolder jsfServlet = new ServletHolder(FacesServlet.class);
		jsfServlet.setDisplayName("Faces Servlet");
		jsfServlet.setName("faces-servlet");
		jsfServlet.setInitOrder(0);

		// Add to web context ...
		context.addServlet(jsfServlet, "*.xhtml");
		context.setWelcomeFiles(new String[] { "bonjour.xhtml" });

		//Pas besoin pour Wildfly
	} */
}
