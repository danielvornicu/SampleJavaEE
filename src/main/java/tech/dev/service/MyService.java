package tech.dev.service;

import javax.ejb.Stateless;

/**
 * Description de la classe
 * <p>
 * Date: 01/03/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@Stateless
public class MyService {
    public String getMessage() {
        return "Hello!";
    }
}
