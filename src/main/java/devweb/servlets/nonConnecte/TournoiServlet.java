package devweb.servlets.nonConnecte;

import devweb.entities.Membre;
import devweb.servlets.GenericServlet;
import devweb.services.TournoiService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tournois")
public class TournoiServlet extends GenericServlet { //crée une servlet generique

    @Override
    // Requête qui permet de récupérer des infos
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("tournois", TournoiService.getInstance().listTournois());

        @SuppressWarnings("unchecked")
        List<Membre> listParticipantEnCours = (List<Membre>) req.getSession().getAttribute("listParticipantEnCours");
        context.setVariable("listParticipantEnCours", listParticipantEnCours);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/nonConnecte/tournois", context, resp.getWriter());
    }
}
