package devweb.servlets.admin;

import devweb.entities.Tournoi;
import devweb.services.TournoiService;
import devweb.servlets.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tournoiEnCours")

public class TournoiEnCoursServlet extends GenericServlet {

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Tournoi tournoiEnCours = (Tournoi) req.getAttribute("tournoiEnCours");
        req.getSession().setAttribute("tournoiEnCours", tournoiEnCours);
      //  req.getSession().setAttribute("tournoiEnCours_date", tournoiEnCours.getDate());
      //  req.getSession().setAttribute("tournoiEnCours_classe", tournoiEnCours.getClasse());
      //  req.getSession().setAttribute("tournoiEnCours_nbInscrits", tournoiEnCours.getNombreInscrit());
      //  req.getSession().setAttribute("tournoiEnCours_placesTable", tournoiEnCours.getPlacesTable());

        resp.sendRedirect("/tournoisAdmin");
    }
}
