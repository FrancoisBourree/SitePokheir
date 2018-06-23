package devweb.servlets.admin;

import devweb.entities.Membre;
import devweb.entities.Tournoi;
import devweb.managers.MembreLibrary;
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

@WebServlet("/tournoisAdmin")
public class TournoiAdminServlet extends GenericServlet { //crée une servlet generique

    @Override
    // Requête qui permet de récupérer des infos
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("tournois", TournoiService.getInstance().listTournois());

        //int placesTables = TournoiService.getInstance().trouverplacesTable();
        //context.setVariable("placesTables",placesTables);

        List<Tournoi> listOfTournoi = TournoiService.getInstance().listTournois();
        context.setVariable("tournoiList",listOfTournoi);

        List<Membre> listParticipantClasse = MembreLibrary.getInstance().listParticipantClasse();
        context.setVariable("tournoiParticipantClasse",listParticipantClasse);

        List<Membre> listParticipantRandom = MembreLibrary.getInstance().listParticipantRandom();
        context.setVariable("tournoiParticipantRandom",listParticipantRandom);

        Integer nbInscrits = MembreLibrary.getInstance().compterLesInscrits();
        context.setVariable("nombreInscrits",nbInscrits);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/admin/tournoisAdmin", context, resp.getWriter());

        int placesTable = (int) req.getSession().getAttribute("placesTable");
    }
}