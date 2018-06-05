package devweb.servlets.admin;

import devweb.entities.Article;
import devweb.services.ArticleService;
import devweb.servlets.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deltournoi")

    public class DeleteTournoiServlet extends HttpServlet { //crée une servlet

        @Override
        // Requête qui permet d'envoyer des infos
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Integer id = Integer.parseInt(req.getParameter("id-Tournoi"));

            try {
                ArticleService.getInstance().delArticle(id);
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("delTournoiErrorMessage", e.getMessage());
            }
            resp.sendRedirect("accueiladmin");
        }



}
