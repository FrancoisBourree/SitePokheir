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

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("tournois", TournoiService.getInstance().listTournois());

        //String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
        //context.setVariable("utilisateurConnecte", utilisateurConnecte);

        //int placesTables = TournoiService.getInstance().trouverplacesTable();
        //context.setVariable("placesTables",placesTables);

        List<Tournoi> listOfTournoi = TournoiService.getInstance().listTournois();
        context.setVariable("tournoiList", listOfTournoi);

        //Integer placesParTable = TournoiService.getInstance().trouverplacesTable();
        //context.setVariable("placesParTable",placesParTable);

        List<Membre> listParticipantClasse = MembreLibrary.getInstance().listParticipantClasse();
        context.setVariable("tournoiParticipantClasse", listParticipantClasse);

        List<Membre> listParticipantRandom = MembreLibrary.getInstance().listParticipantRandom();
        context.setVariable("tournoiParticipantRandom", listParticipantRandom);

        Integer nbInscrits = MembreLibrary.getInstance().compterLesInscrits();
        context.setVariable("nombreInscrits", nbInscrits);

        //Integer nbTest = 42;
        //context.setVariable("nbTest", nbTest);

        //int placesParTable = (int) req.getSession().getAttribute("placesParTable");

        Integer placesParTable = (Integer) req.getSession().getAttribute("placesParTable");
        context.setVariable("placesParTable", placesParTable);

        //Integer idTournoisEnCours =  (Integer) req.getSession().getAttribute("idTournoisEnCours");
        //context.setVariable("idTournoisEnCours",idTournoisEnCours);

        //INFORMATIONS DU TOURNOI EN COURS


        //String tournoiEnCours = (String) req.getSession().getAttribute("tournoiEnCours");
        //context.setVariable("tournoiEnCours",tournoiEnCours);

        String tournoiEnCours_date = (String) req.getSession().getAttribute("tournoiEnCours_date");
        context.setVariable("tournoiEnCours_date", tournoiEnCours_date);

        Integer tournoiEnCours_placesTable = (Integer) req.getSession().getAttribute("tournoiEnCours_placesTable");
        context.setVariable("tournoiEnCours_placesTable", tournoiEnCours_placesTable);

        Boolean tournoiEnCours_classe = (Boolean) req.getSession().getAttribute("tournoiEnCours_classe");
        context.setVariable("tournoiEnCours_classe", tournoiEnCours_classe);

        // SI TOURNOI EN COURS VALIDE
        if (tournoiEnCours_date != null && !tournoiEnCours_date.equals("")) {

            /*

            String tournoiEnCours_date = tournoiEnCours.getDate();
            context.setVariable("tournoiEnCours_date", tournoiEnCours_date);

            Boolean tournoiEnCours_classe = tournoiEnCours.getClasse();
            context.setVariable("tournoiEnCours_classe", tournoiEnCours_classe);

            Integer tournoiEnCours_nbInscrits = tournoiEnCours.getNombreInscrit();
            context.setVariable("tournoiEnCours_nbInscrits", tournoiEnCours_nbInscrits);

            Integer tournoiEnCours_placesTable = tournoiEnCours.getPlacesTable();
            context.setVariable("tournoiEnCours_placesTable", tournoiEnCours_placesTable);

            */

            List<Membre> listParticipantEnCours;

                    // CHOIX DU TYPE DE CLASSEMENT
                    if (tournoiEnCours_classe) {
                        listParticipantEnCours = listParticipantClasse;
                        context.setVariable("listParticipantEnCours", listParticipantEnCours);
                        req.getSession().setAttribute("listParticipantEnCours", listParticipantEnCours);
                    } else {
                        listParticipantEnCours = listParticipantRandom;
                        context.setVariable("listParticipantEnCours", listParticipantEnCours);
                        req.getSession().setAttribute("listParticipantEnCours", listParticipantEnCours);
                    }


            Integer compteur = 1;
            Integer numeroDeTable = 1;
            for (Membre participantEnCours : listParticipantEnCours) {

                if (compteur <= tournoiEnCours_placesTable) {
                    participantEnCours.setNumeroTable(numeroDeTable);
                    //créer une fonction qui fait une requête SQL UPDATE du numéro de table du joueur
                }

                if (compteur > tournoiEnCours_placesTable) {
                    //créer une fonction qui fait une requête SQL UPDATE du numéro de table du joueur
                    numeroDeTable += 1;
                    participantEnCours.setNumeroTable(numeroDeTable);
                    compteur = 1;
                }
                compteur++;
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

            templateEngine.process("/admin/tournoisAdmin", context, resp.getWriter());
        }else{
            templateEngine.process("/admin/tournoisAdmin", context, resp.getWriter());
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

}