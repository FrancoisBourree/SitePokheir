package devweb.filtres;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthentificationAdmin implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    // redirige vers "compteadmin" si on va sur "compte" alors qu'on est admin
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte"); // récupère "utilisateurConnecte" de la session sous le nom "identifiant"
        System.out.println(identifiant);
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(identifiant.equals("admin@hei.yncrea.fr")) {
            System.out.println("Vous etes admin !");
            httpResponse.sendRedirect("../compteadmin");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}