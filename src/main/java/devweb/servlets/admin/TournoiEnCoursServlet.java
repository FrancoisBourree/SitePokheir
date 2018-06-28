package devweb.servlets.admin;

import devweb.entities.Tournoi;
import devweb.services.TournoiService;
import devweb.servlets.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/tournoiEnCours")

public class TournoiEnCoursServlet extends GenericServlet {

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(); // création de la session

        String tournoiEnCours_date = req.getParameter("tournoiEnCours_date");
        Boolean tournoiEnCours_classe = Boolean.parseBoolean(req.getParameter("tournoiEnCours_classe"));
        Integer tournoiEnCours_placesTable = Integer.parseInt(req.getParameter("tournoiEnCours_placesTable"));

        /*

        Tournoi tournoiEnCours = new Tournoi(1000,"",0,0,false);

        tournoiEnCours = (Tournoi) req.getAttribute("tournoiEnCours");

        String tournoiEnCours_date = tournoiEnCours.getDate();
        Boolean tournoiEnCours_classe = tournoiEnCours.getClasse();
        Integer tournoiEnCours_nbInscrits = tournoiEnCours.getNombreInscrit();
        Integer tournoiEnCours_placesTable = tournoiEnCours.getPlacesTable();

        */

        //req.getSession().setAttribute("tournoiEnCours", tournoiEnCours);
        req.getSession().setAttribute("tournoiEnCours_date", tournoiEnCours_date);
        req.getSession().setAttribute("tournoiEnCours_classe", tournoiEnCours_classe);
        //req.getSession().setAttribute("tournoiEnCours_nbInscrits", tournoiEnCours_nbInscrits);
        req.getSession().setAttribute("tournoiEnCours_placesTable", tournoiEnCours_placesTable);

        resp.sendRedirect("/tournoisAdmin");
    }
}
