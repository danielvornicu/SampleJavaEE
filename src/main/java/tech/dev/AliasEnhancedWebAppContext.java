package tech.dev;

//Pas besoin pour Wildfly
//import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Description de la classe
 * <p>
 * Date: 22/02/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */
//extended Jetty's WebAppContext to provide a means of pattern based alias resolution.
// The only overridden method is getResourceAlias()

//Until Mojarra 2.2.10, the JSF implementation scanned the classpath itself to detect annotated classes.
//Since Mojarra 2.2.11, JSF relies on new API: the ServletContainerInitializer and we will use jetty-maven-plugin

//v1
//Pas besoin pour Wildfly

/*
public class AliasEnhancedWebAppContext extends WebAppContext {

    @Override
    public String getResourceAlias(String alias) {

        final Map<String, String> resourceAliases = (Map<String, String>) getResourceAliases();

        if (resourceAliases == null) {
            return null;
        }

        for (Map.Entry<String, String> oneAlias : resourceAliases.entrySet()) {

            if (alias.startsWith(oneAlias.getKey())) {
                return alias.replace(oneAlias.getKey(), oneAlias.getValue());
            }
        }

        return null;
    }

}  */
