package devweb.servlets.admin;

import devweb.entities.Article;
import devweb.entities.Tournoi;
import devweb.services.ArticleService;
import devweb.services.TournoiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addtournoi")
@MultipartConfig
public class AddTournoiServlet  extends HttpServlet { //crée une servlet generique

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String datetournoi = req.getParameter("datetournoi");
        Boolean classe = Boolean.parseBoolean(req.getParameter("classe"));
        Integer nombreInscrits = 0;
        Integer placesTable = 0;

        Tournoi tournoi = new Tournoi(null, datetournoi, nombreInscrits, placesTable,classe);
        try {
            TournoiService.getInstance().addTournoi(tournoi);
            resp.sendRedirect("tournois2");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("addArticleErrorMessage", e.getMessage());
            resp.sendRedirect("error");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }





}
