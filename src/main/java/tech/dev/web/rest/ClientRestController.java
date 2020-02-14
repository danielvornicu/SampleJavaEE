package tech.dev.web.rest;

import tech.dev.service.ClientService;
import tech.dev.to.ClientTO;
import tech.dev.web.common.base.AbstractRESTController;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(ClientRestController.REQUEST_MAPPING)
@Produces(MediaType.APPLICATION_JSON)
public class ClientRestController extends AbstractRESTController<ClientTO> {

    public static final String REQUEST_MAPPING = "/clients";

    @Inject
    private ClientService clientService;

    @Override
    protected List<ClientTO> initializeIndexTO() {
        //returne la liste des TO
        List<ClientTO> clients = clientService.findAllFillTO();
        return clients;
    }

    @Override
    protected ClientTO initializeShowTO(Long id) {
        //with TO
        ClientTO client = clientService.findByIdFillTO(id);
        return client;
    }



    @Override
    protected List<ClientTO> saveTO(ClientTO to, boolean isCreation) {
        clientService.saveTO(to, isCreation);
        //returne la liste des TO apres la creation/modification
        List<ClientTO> clients = clientService.findAllFillTO();
        return clients;
    }

    @Override
    protected List<ClientTO> deleteTO(Long id) {
        clientService.deleteClientByClientId(id);
        //returne la liste des TO apres la suppression
        List<ClientTO> clients = clientService.findAllFillTO();
        return clients;
    }
}