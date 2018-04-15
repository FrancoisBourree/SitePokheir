package devweb.servlets.connecte;

import devweb.managers.MembreLibrary;
import devweb.servlets.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inscriretournoi")
public class InscriptionTournoiServlet extends GenericServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");

        try {
            MembreLibrary.getInstance().inscrire(identifiant);
            resp.sendRedirect("/tournois2");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ErrorMessage", e.getMessage());
        }

    }
}