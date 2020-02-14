package tech.dev.web.common.base;

/*
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; */

import tech.dev.commons.to.base.TransferObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Controlleur abstrait pour gérer la recherche et la modification d'un TO en mode REST
 * <p>
 * Date: 12/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public abstract class AbstractRESTController<T extends TransferObject> {

    /**
     * Affiche la liste des objects
     * @return la reponse HTTP avec la liste des objets et le status
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {;
        List<T> list = initializeIndexTO();
        return Response.ok().entity(list).build();
    }

    /**
     * Retourne la reponse HTTP avec l'object TO et le status
     * @param id l'id de l'objet à consulter ( :.+ permet de ne pas tronquer l'url finissant par un .)
     * @return la reponse HTTP avec l'object TO et le status
     */
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam("id") Long id) {
        try {
            T to = initializeShowTO(id);
            return Response.ok().entity(to).build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for id: " + id).build();
        }
    }

    /**
     * Supprime un objet.
     * @param id l'id de l'object à supprimer
     * @return la reponse HTTP avec la liste des TO apres supression et le status
     */

    @Path("/{id}/delete")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        List<T> list = deleteTO(id);
        return Response.ok().entity(list).build();
    }

    /**
     * Crée un objet.
     * @param to l'object à creer
     * @return la reponse HTTP avec la liste des TO apres insertion et le status
     */
    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(T to) {
        List<T> list = saveTO(to, true);
        return Response.ok().entity(list).build();
    }

    /**
     * Modifie un objet.
     * @param to l'object à modifier
     * @return la reponse HTTP avec la liste des TO apres modification et le status
     */

    @Path("/{id}/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update( T to) {
        List<T> list = saveTO(to, false);
        return Response.ok().entity(list).build();
    }

    /**
     * Initialise les valeurs pour la liste totale des TO
     * @return la liste des to
     */
    protected abstract List<T> initializeIndexTO();

    /**
     * Initialise les valeurs pour la consultation d'un TO
     * @param id l'id du TO a consulter
     * @return le TO a consulter
     */

    protected abstract T initializeShowTO(Long id);

    /**
     * Appelle le service pour créer ou modifier le TO
     * @param to le TO à mettre à jour
     * @param isCreation true si on est en création
     * @return la liste des to apres creation/modification
     */
    protected abstract List<T> saveTO(T to, boolean isCreation);

    /**
     * Supprime un TO
     * @param id l'id du TO a supprimer
     * @return la liste des to apres suppresion
     */
    protected abstract List<T> deleteTO(Long id);
}
