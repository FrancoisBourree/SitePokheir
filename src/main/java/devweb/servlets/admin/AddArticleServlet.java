package devweb.servlets.admin;

import devweb.entities.Article;
import devweb.services.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addarticle")
@MultipartConfig
public class AddArticleServlet extends HttpServlet{ //crée une servlet generique

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String titre = req.getParameter("titre");
        String file = req.getParameter("image");
        String texte = req.getParameter("texte");
        System.out.println(file);

        Article article = new Article(null,titre, file, texte);
        try {
            ArticleService.getInstance().addArticle(article);
            resp.sendRedirect("accueil2#portfolio");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("addArticleErrorMessage", e.getMessage());
            resp.sendRedirect("error");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

