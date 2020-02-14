package tech.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.dev.entites.Client;
import tech.dev.service.ClientService;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe principale
 * <p>
 * Date: 25/06/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public class Application {

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    //injection pas posible parce que la classe Main est une application autonome qui s'execute
    //a l'exterior du conteneur Glassfish et l'annotation EJB a besoin d'un conteneur pour injecter une reference de ClientService
    //la classe Main doit s'executer dans un conteneur client d'application (ACC) pour pouvoir utiliser @EJB
    //Glassfish furnit l'utilitaire appclient pour ca: appclient -client fichier.jar
    //@EJB
    //private static ClientService clientService;

    public static void main(String[] args) {
        LOGGER.debug("Startup");
        //Persistence.generateSchema("crm-pu", null);

        // en commentaire si on deploy sur Wildfly

        //set the container properties
        Map<String, Object> properties = new HashMap<String, Object>();
        //properties.put(EJBContainer.APP_NAME, "CRM");
        //properties.put(EJBContainer.PROVIDER, "org.glassfish.ejb.embedded.EJBContainerProviderImpl");

        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root",
                "./src/test/resources/glassfish");

        properties.put(EJBContainer.MODULES, new File("target/classes"));

        //create a Embedded Container and get the JNDI context
        EJBContainer ejbContainer = EJBContainer.createEJBContainer(properties);
        LOGGER.debug("Container Opening");

        Context appContext = ejbContainer.getContext();

        try {
            //look up the EJB
            //ClientJpaDAO dao = (ClientJpaDAO) appContext.lookup("java:global/CRM/ClientJpaDAO");

            //lookup
            ClientService clientService = (ClientService) appContext.lookup("java:global/classes/ClientService");

            List<Client> clients = clientService.findAll();
            LOGGER.debug("Liste des clients:");
            for (Client client : clients) {
                LOGGER.info(client.toString());
            }

        } catch (NamingException e) {e.printStackTrace();}

        try{
          appContext.close();
        } catch (Exception e) {e.printStackTrace();}

        ejbContainer.close();


        LOGGER.debug("Container Closing" );

    }
}