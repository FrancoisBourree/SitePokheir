package devweb.servlets.admin;


import devweb.services.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/delarticle")
    public class DeleteArticleServlet extends HttpServlet { //crée une servlet

        @Override
        // Requête qui permet d'envoyer des infos
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Integer id = Integer.parseInt(req.getParameter("id-Article"));

            try {
                ArticleService.getInstance().delArticle(id);
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("delArticleErrorMessage", e.getMessage());
            }
            resp.sendRedirect("accueiladmin");
        }

}
