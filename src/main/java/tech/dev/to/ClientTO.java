package tech.dev.to;


import tech.dev.commons.to.base.TransferObject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Description de la classe
 * <p>
 * Date: 10/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@XmlRootElement
public class ClientTO extends TransferObject implements Serializable {

    private Long id;

    @NotNull(message = "{client.validation.prenom.empty}")
    @Size(max=10, message="{client.validation.prenom.maxLength}")
    private String prenom;

    @NotNull(message = "{client.validation.nom.empty}")
    @Size(max=10, message="{client.validation.nom.maxLength}")
    private String nom;

    public ClientTO() {
        this.id = -1L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Client [" +
                "id=" + this.getId() + ", " +
                "prenom='" + this.getPrenom()+ "', " +
                "nom='" + this.getNom() +
                ']';
    }
}
