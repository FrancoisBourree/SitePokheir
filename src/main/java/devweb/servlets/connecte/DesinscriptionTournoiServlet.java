package devweb.servlets.connecte;

import devweb.managers.MembreLibrary;
import devweb.servlets.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/desinscriretournoi")
public class DesinscriptionTournoiServlet extends GenericServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");

        try {
            MembreLibrary.getInstance().desinscrire(identifiant);
            resp.sendRedirect("/compte");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ErrorMessage", e.getMessage());
        }

    }
}