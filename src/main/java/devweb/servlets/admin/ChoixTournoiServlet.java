package devweb.servlets.admin;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/choixTournoi")

public class ChoixTournoiServlet extends HttpServlet {

    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(); // création de la session

        int placesTable = Integer.parseInt(req.getParameter("placesTable"));
        req.getSession().setAttribute("placesTable", placesTable);

        resp.sendRedirect("tournoisAdmin");
    }

}
