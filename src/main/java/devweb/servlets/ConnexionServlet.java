package devweb.servlets;

import devweb.managers.MembreLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class ConnexionServlet extends GenericServlet{ //crée une servlet generique

    @Override
    // Requête qui permet de récupérer des infos
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String identifiantUtilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte"); // récupère "utilisateurConnecte" de la session sous le nom "identifiantUtilisateurConnecte"
        if(identifiantUtilisateurConnecte == null || "".equals(identifiantUtilisateurConnecte)) { // si "identifiantUtilisateurConnecte" est null ou vide
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("login", context, resp.getWriter());
        }else{
            resp.sendRedirect("compte"); // sinon on le redirige vers la servlet : compte
        }

    }

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email=req.getParameter("email"); // récupère "email" du formulaire

        String password1= MembreLibrary.getInstance().getMdp(email); // récupère "email" de la base de données
        String password2= req.getParameter("password"); // récupère "password" du formulaire

        if (password1==null){
            password1="";
        }

        if (password1.equals(password2)) { // si les mdp saisi correspond à celui de la bdd
            req.getSession().setAttribute("utilisateurConnecte",email); // enregistre "email" sous le nom "utilisateurConnecte" pour la session
            resp.sendRedirect("accueil2"); // on redirige vers la servlet : accueil2 (accueil pour connectés)
        } else{
            resp.sendRedirect("compte"); // sinon on le redirige vers la servlet : compte
        }

    }
}

