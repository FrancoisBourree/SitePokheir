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

@WebServlet("/addtounoi")
@MultipartConfig
public class AddTournoiServlet  extends HttpServlet { //crée une servlet generique

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String datetournoiStr = req.getParameter("datetournoi");
        Integer nombreInscrits = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(datetournoiStr);
        java.sql.Date datetournoi = new java.sql.Date(date.getTime());

        Date datetournoi = null;
        try {
            datetournoi = sdf.parse(datetournoiStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Boolean classe = Boolean.parseBoolean(req.getParameter("classe"));

        Tournoi tournoi = new Tournoi(null, datetournoi, nombreInscrits, classe);
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