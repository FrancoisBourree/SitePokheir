package devweb.servlets;

import devweb.services.ArticleService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accueil2")
public class Accueil2Servlet extends GenericServlet { //crée une servlet generique

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("articles", ArticleService.getInstance().listArticles());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("index2", context, resp.getWriter());
    }
}

