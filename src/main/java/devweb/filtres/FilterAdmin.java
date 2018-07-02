package devweb.filtres;

import devweb.managers.MembreLibrary;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterAdmin implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    // vérifie qu'on est admin
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte"); // récupère "utilisateurConnecte" de la session sous le nom "identifiant"
        String mdp = (String) httpRequest.getSession().getAttribute("mdp"); // récupère "mdp" de la session sous le nom "mdp"
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(!identifiant.equals("admin@hei.yncrea.fr") && !mdp.equals(MembreLibrary.getInstance().getMdp("admin@hei.yncrea.fr"))){ // si pas admin
            System.out.println("Vous n'etes pas admin !");
            httpResponse.sendRedirect("../compte"); // renvoie vers compte
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}