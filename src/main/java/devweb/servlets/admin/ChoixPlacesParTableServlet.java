package devweb.servlets.admin;

import devweb.services.TournoiService;
import devweb.servlets.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/placesParTable")

public class ChoixPlacesParTableServlet extends GenericServlet {

        @Override
        // Requête qui permet d'envoyer des infos
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            Integer placesTable = Integer.parseInt(req.getParameter("placesParTable"));
            Integer id = Integer.parseInt(req.getParameter("id-Tournoi"));

            try {
                TournoiService.getInstance().choosePlacesParTable(id,placesTable);
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("placesTableError", e.getMessage());
            }
            resp.sendRedirect("/tournoisAdmin");
        }

}
