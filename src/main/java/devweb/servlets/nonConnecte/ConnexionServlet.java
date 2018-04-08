package devweb.servlets.nonConnecte;

import devweb.managers.MembreLibrary;
import devweb.servlets.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class ConnexionServlet extends GenericServlet { //crée une servlet generique

    @Override
    // Requête qui permet de récupérer des infos
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String identifiantUtilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte"); // récupère "utilisateurConnecte" de la session sous le nom "identifiantUtilisateurConnecte"
        if(identifiantUtilisateurConnecte == null || "".equals(identifiantUtilisateurConnecte)) { // si "identifiantUtilisateurConnecte" est null ou vide
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("nonConnecte/login", context, resp.getWriter());
        }else{
            resp.sendRedirect("../compte"); // sinon on le redirige vers la servlet : compte
        }

    }

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mdp = req.getParameter("password"); // récupère "password" du questionnaire et le stock dans "mdp"
        req.getSession().setAttribute("mdp", mdp); // crée un attribut "emailConnecte" pour la session avec comme valeur celle de "emailConnexion"
        String email = req.getParameter("email"); // récupère "email" du formulaire et le stock dans "email"
        req.getSession().setAttribute("utilisateurConnecte", email); // crée un attribut "emailConnecte" pour la session avec comme valeur celle de "emailConnexion"

        String password1= MembreLibrary.getInstance().getMdp(email); // récupère "email" de la base de données

        if (password1==null){
            password1="";
        }

        if (password1.equals(mdp)) { // si les mdp session correspond à celui de la bdd
            req.getSession().setAttribute("utilisateurConnecte",email); // enregistre "email" sous le nom "utilisateurConnecte" pour la session
            resp.sendRedirect("accueil2"); // on redirige vers la servlet : accueil2 (accueil pour connectés)
        } else{
            resp.sendRedirect("compte"); // sinon on le redirige vers la servlet : compte
        }

    }
}

