package tech.dev.commons.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Implement a servlet filter to do the UTF-8 encoding of  the requests and responses:
 * <p>
 * Date: 07/05/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public class UTF8CharacterEncodingFilter implements Filter
{
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}