package tech.dev.web.common.base;

import tech.dev.commons.to.base.TransferObject;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

/**
 * Managed bean abstrait pour gérer la recherche et la modification d'un TO
 * <p>
 * Date: 13/05/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public abstract class AbstractSearchEditManagedBean<T extends TransferObject,  F extends BaseForm<T>> {

    // session Map pour stocker des objets en session
    protected Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    // root racine de l'arbre des composants graphiques
    protected UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();

    //configuration de l'application
    protected Application app = FacesContext.getCurrentInstance().getApplication();

    public List<T> index() {
        return initializeIndexTO();
    }

    public String show(Long id) {
        initializeShowTO(id);
        return getShowView();
    }

    public String newObject() {
        initializeNewTO();
        return getEditView();
    }

    public String createOrUpdate(T to) {
        boolean isCreation = new Boolean(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("isCreation"));

        saveTO(to, isCreation);
        return getListView();
    }

    public String edit(Long id) {
        initializeEditTO(id);
        return getEditView();
    }

    public String delete(Long id) {
        deleteTO(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete successful"));
        return getListView();
    }

    /**
     * Initialise les valeurs pour la liste totale des TO
     */
    protected abstract List<T> initializeIndexTO();

    /**
     * Initialise les valeurs pour la consultation d'un TO
     */
    protected abstract void initializeShowTO(Long id);

    /**
     * Initialise les valeurs pour la création d'un nouveau TO
     */
    protected abstract void initializeNewTO();

    /**
     * Initialise les valeurs pour la modification d'un TO
     * @param id l'id du TO à modifier
     */
    protected abstract void initializeEditTO(Long id);

    /**
     * Appelle le service pour créer ou modifier le TO
     * @param to le TO à mettre à jour
     * @param isCreation true si on est en création
     * @return true si tout s'est bien passé et false sinon
     */
    protected abstract boolean saveTO(T to, boolean isCreation);

    /**
     * Supprime un TO
     * @param id l'id du TO à supprimer
     */
    protected abstract void deleteTO(Long id);

    /**
     * Retourne la chaine de caractère correspondant à la base des vues du controller
     * @return la chaine de caractères correspondant à la base des vues
     */
    protected abstract String getRootView();

    /**
     * Retourne la vue utilisée pour la liste
     * @return la chaine de caractères correspondant à la vue
     */
    protected String getListView() {
        return getRootView() + "Liste" + ".xhtml?faces-redirect=true";
    }

    /**
     * Retourne la vue utilisée pour la consultation
     * @return la chaine de caractères correspondant à la vue
     */
    protected String getShowView() {
        return getRootView() + "Consult" + ".xhtml?faces-redirect=true";
    }

    /**
     * Retourne la vue utilisée pour la modification
     * @return la chaine de caractères correspondant à la vue
     */
    protected String getEditView() {
        return getRootView() + "Fiche" + ".xhtml?faces-redirect=true";
    }
}
