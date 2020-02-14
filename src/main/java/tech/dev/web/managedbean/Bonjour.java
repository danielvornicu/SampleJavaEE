package tech.dev.web.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Description de la classe
 * <p>
 * Date: 21/02/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

//The scan for annotated @ManagedBean classes takes place in the directory "WEB-INF/classes"

//@Named(value = "bonjour")
@ManagedBean
@SessionScoped
//@ApplicationScoped
//@ViewScoped
//@RequestScoped
public class Bonjour implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message="Bonjour à tous!";


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
