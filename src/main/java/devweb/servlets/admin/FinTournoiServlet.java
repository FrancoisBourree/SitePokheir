package devweb.servlets.admin;

import devweb.managers.MembreLibrary;
import devweb.services.TournoiService;
import devweb.servlets.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/finTournoi")

public class FinTournoiServlet extends GenericServlet {

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(); // création de la session

        String tournoiEnCours_date = null;
        Boolean tournoiEnCours_classe = null;
        Integer tournoiEnCours_placesTable = null;

        req.getSession().setAttribute("tournoiEnCours_date", tournoiEnCours_date);
        req.getSession().setAttribute("tournoiEnCours_classe", tournoiEnCours_classe);
        req.getSession().setAttribute("tournoiEnCours_placesTable", tournoiEnCours_placesTable);


        try {
            MembreLibrary.getInstance().desinscrireTous();
            resp.sendRedirect("/tournoisAdmin");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("finTournoiErrorMessage", e.getMessage());
            resp.sendRedirect("error");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
