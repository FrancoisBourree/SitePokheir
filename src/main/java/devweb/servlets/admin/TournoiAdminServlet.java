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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
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

        //Integer placesParTable = TournoiService.getInstance().trouverplacesTable();
        //context.setVariable("placesParTable",placesParTable);

        List<Membre> listParticipantClasse = MembreLibrary.getInstance().listParticipantClasse();
        context.setVariable("tournoiParticipantClasse",listParticipantClasse);

        List<Membre> listParticipantRandom = MembreLibrary.getInstance().listParticipantRandom();
        context.setVariable("tournoiParticipantRandom",listParticipantRandom);

        Integer nbInscrits = MembreLibrary.getInstance().compterLesInscrits();
        context.setVariable("nombreInscrits",nbInscrits);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/admin/tournoisAdmin", context, resp.getWriter());

        //int placesParTable = (int) req.getSession().getAttribute("placesParTable");

        Integer placesParTable = (Integer) req.getSession().getAttribute("placesParTable");
        context.setVariable("placesParTable",placesParTable);

        //Integer idTournoisEnCours =  (Integer) req.getSession().getAttribute("idTournoisEnCours");
        //context.setVariable("idTournoisEnCours",idTournoisEnCours);

        //INFORMATIONS DU TOURNOI EN COURS
        Tournoi tournoiEnCours = (Tournoi) req.getSession().getAttribute("tournoiEnCours");
        context.setVariable("tournoiEnCours",tournoiEnCours);

        String tournoiEnCours_date = tournoiEnCours.getDate();
        context.setVariable("tournoiEnCours_date",tournoiEnCours_date);

        Boolean tournoiEnCours_classe = tournoiEnCours.getClasse();
        context.setVariable("tournoiEnCours_classe",tournoiEnCours_classe);

        Integer tournoiEnCours_nbInscrits = tournoiEnCours.getNombreInscrit();
        context.setVariable("tournoiEnCours_nbInscrits",tournoiEnCours_nbInscrits);

        Integer tournoiEnCours_placesTable = tournoiEnCours.getPlacesTable();
        context.setVariable("tournoiEnCours_placesTable",tournoiEnCours_placesTable);

        if(tournoiEnCours_classe){
            List<Membre> listParticipantEnCours =  listParticipantClasse;
            context.setVariable("listParticipantEnCours",listParticipantEnCours);
        }else{
            List<Membre> listParticipantEnCours =  listParticipantRandom;
            context.setVariable("listParticipantRandom",listParticipantRandom);
        }


        /*
        Integer placesParTable = (Integer) req.getSession().getAttribute("placesParTable");
        context.setVariable("placesParTable",placesParTable);
        Integer placesParTable = (Integer) req.getSession().getAttribute("placesParTable");
        context.setVariable("placesParTable",placesParTable);
        Integer placesParTable = (Integer) req.getSession().getAttribute("placesParTable");
        context.setVariable("placesParTable",placesParTable);
        Integer placesParTable = (Integer) req.getSession().getAttribute("placesParTable");
        context.setVariable("placesParTable",placesParTable);
        */

    }

    /*
    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(); // création de la session

        int placesParTable = Integer.parseInt(req.getParameter("placesParTable"));
        req.getSession().setAttribute("placesParTable", placesParTable);
        //WebContext context = new WebContext(req, resp, req.getServletContext());
        //context.setVariable("placesParTable",placesParTable);

        //Integer idTournoisEnCours = Integer.parseInt(req.getParameter("id-Tournoi"));
        //context.setVariable("idTournoisEnCours",idTournoisEnCours);
        //req.getSession().setAttribute("idTournoisEnCours", idTournoisEnCours);

        resp.sendRedirect("/tournoisAdmin");

    }
    */
}