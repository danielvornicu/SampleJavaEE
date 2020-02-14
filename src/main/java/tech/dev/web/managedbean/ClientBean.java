package tech.dev.web.managedbean;

import tech.dev.service.ClientService;
import tech.dev.to.ClientTO;
import tech.dev.web.common.base.AbstractSearchEditManagedBean;
import tech.dev.web.formulaires.ClientForm;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Managed bean pour la gestion de clients(echivalent du controlleur Spring)
 * <p>
 * Date: 16/04/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@ManagedBean
@SessionScoped
public class ClientBean extends AbstractSearchEditManagedBean<ClientTO, ClientForm> implements Serializable {
    private static final long serialVersionUID = 1L;

    // sert comme racine pour la vue, on ajout Consult, Fiche, Liste apres
    private static final String ROOT_VUE = "client";

    public static final String CLIENT= "client";
    // formulaire pour la fiche client
    public static final String CLIENT_FORM = "clientForm";

    @EJB
    private ClientService clientService;

    @Override
    protected String getRootView() {
        return ROOT_VUE;
    }

    @Override
    protected List<ClientTO> initializeIndexTO() {
        //liste des TO
        List<ClientTO> clients = clientService.findAllFillTO();
        return clients;
    }

    @Override
    protected void initializeShowTO(Long id) {
        ClientTO client = clientService.findByIdFillTO(id);
        sessionMap.put(CLIENT, client);
    }

    @Override
    protected void initializeNewTO() {
        ClientForm form = new ClientForm();
        fillModel(form, null);
    }

    @Override
    protected void initializeEditTO(Long id) {
        ClientTO client = clientService.findByIdFillTO(id);

        ClientForm form = new ClientForm();
        fillModel(form, client);
    }

    private void fillModel(ClientForm form, ClientTO to) {
        form.initForm(to);
        sessionMap.put(CLIENT_FORM, form);
    }

    @Override
    protected boolean saveTO(ClientTO to, boolean isCreation) {
        clientService.saveTO(to, isCreation);
        return true;
    }

    @Override
    protected void deleteTO(Long id) {
        clientService.deleteClientByClientId(id);
    }
}