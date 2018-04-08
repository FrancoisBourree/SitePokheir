package devweb.servlets;

import devweb.managers.MembreLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifmdp")
public class ModifMdpServlet extends GenericServlet { //crée une servlet generique

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String mdp = req.getParameter("password");
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");

        try {
            MembreLibrary.getInstance().modifMdp(identifiant,mdp);
            resp.sendRedirect("/accueil");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ModifMdpErrorMessage", e.getMessage());
        }

    }
}
