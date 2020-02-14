package tech.dev.service;

import tech.dev.commons.service.base.AbstractCRUDServiceBase;
import tech.dev.dao.AdresseJpaDAO;
import tech.dev.dao.ClientJpaDAO;
import tech.dev.entites.Client;
import tech.dev.to.ClientTO;
import tech.dev.to.convertor.ClientTOConvertor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Description de la classe
 * <p>
 * Date: 19/07/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@Stateless
//@LocalBean
//@TransactionManagement(TransactionManagementType.BEAN)
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ClientService extends AbstractCRUDServiceBase<Client, ClientTO>{

    //private static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    ClientJpaDAO clientDAO;
    AdresseJpaDAO adresseDAO;

    public ClientService() {
    }

    @Inject
    public ClientService(ClientJpaDAO clientDAO, AdresseJpaDAO adresseDAO) {
        this.clientDAO  = clientDAO;
        this.adresseDAO = adresseDAO;
    }

    @Override
    protected ClientTOConvertor getConvertor() {
        if (convertor == null) {
            convertor = new ClientTOConvertor();
        }
        return (ClientTOConvertor) convertor;
    }

    public void deleteClientByAdresseId(Long id) throws Exception{
        InitialContext context = new InitialContext();

        UserTransaction transaction = (UserTransaction) context.lookup("UserTransaction");
        try {
            //System.out.println(clientDAO);
            transaction.begin();
            this.clientDAO.deleteByAdreseId(id);
            this.adresseDAO.deleteById(id);
            transaction.commit();
        } catch (Exception up) {
            transaction.rollback();
            throw up;
        }
    }
    public void deleteClientByClientId(Long id){
        //System.out.println(clientDAO);
        this.clientDAO.deleteById(id);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public List<ClientTO> findAllFillTO() {
        List<Client> entityList = clientDAO.findAll();
        //List<Client> entityList = clientDAO.findAllWithFilter();
        //convertor
        List<ClientTO> TOlist = getConvertor().entityList2TOList(entityList, false);

        return TOlist;
    }

    public List<Client> findClientsByAdresseId(Long id){
      return clientDAO.findClientsByAdresseId(id);
    }


    public Client findById(Long id) {
        return clientDAO.findById(id);
    }

    public ClientTO findByIdFillTO(Long id) {
        Client client = clientDAO.findById(id);
        //convertor
        ClientTO to = getConvertor().entity2TO(client, false);
        return to;
    }

    public void save(Client client, boolean isCreation) {
        if (isCreation) {
            this.clientDAO.create(client);
        } else{
            this.clientDAO.update(client);
        }
    }

    public void saveTO(ClientTO to, boolean isCreation) {
        Client client = null;
        // On récupère les données de la base avant de merger pour modifier uniquement ce qui provient du TO
        if (!isCreation) {
            client = findById(to.getId());
        }
        //convertor
        client = getConvertor().fillEntity(to, client);

        if (isCreation) {
            this.clientDAO.create(client);
        } else{
            this.clientDAO.update(client);
        }
    }

}
