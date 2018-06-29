package devweb.servlets.connecte;

import devweb.entities.Membre;
import devweb.dao.impl.MembreDaoImpl;
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

@WebServlet("/compte")
public class CompteServlet extends GenericServlet { //crée une servlet generique

    @Override
    // Requête qui permet de récupérer des infos
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Tournoi> listOfTournoi = TournoiService.getInstance().listTournois();
        context.setVariable("tournoiList",listOfTournoi);

        String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
        Membre UtilisateurEnCours = MembreLibrary.getInstance().getMembre(utilisateurConnecte);

        Boolean participe = UtilisateurEnCours.getParticipe();

        String inscription;

        if (participe){
            inscription = "Vous êtes inscrit au prochain tournoi !";
        }else{
            inscription = "Vous n'êtes pas inscrit au prochain tournoi";
        }

        context.setVariable("inscription",inscription);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("/connecte/compte", context, resp.getWriter());
    }
}
