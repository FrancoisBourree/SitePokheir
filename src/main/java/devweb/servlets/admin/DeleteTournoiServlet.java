package devweb.servlets.admin;

import devweb.services.TournoiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deltournoi")

public class DeleteTournoiServlet extends HttpServlet { //créé une servlet

        @Override
        // Requête qui permet d'envoyer des infos
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            Integer id = Integer.parseInt(req.getParameter("id-Tournoi"));

            try {
                TournoiService.getInstance().delTournoi(id);
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("delTournoiErrorMessage", e.getMessage());
            }
            resp.sendRedirect("compteadmin");
        }



}
