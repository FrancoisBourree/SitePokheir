package devweb.servlets.admin;

import devweb.entities.Tournoi;
import devweb.services.TournoiService;
import devweb.servlets.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/compteadmin")
public class CompteAdminServlet extends GenericServlet { //crée une servlet generique

    @Override
    // Requête qui permet de récupérer des infos
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Tournoi> listOfTournoi = TournoiService.getInstance().listTournois();
        context.setVariable("tournoiList",listOfTournoi);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/admin/compteadmin", context, resp.getWriter());
    }
}
