package devweb.servlets.connecte;

import devweb.entities.Membre;
import devweb.managers.MembreLibrary;
import devweb.servlets.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/classement2")
public class Classement2Servlet extends GenericServlet { //crée une servlet generique

    @Override
    // Requête qui permet de récupérer des infos
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Membre> listOfMembres = MembreLibrary.getInstance().listMembres();
        context.setVariable("membresList",listOfMembres);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/connecte/classement2", context, resp.getWriter());
    }
}
