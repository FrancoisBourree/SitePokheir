package devweb.servlets;

import devweb.managers.MembreLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

@WebServlet("/register")
public class InscriptionServlet extends GenericServlet { //crée une servlet generique


    @Override
    // Requête qui permet de récupérer des infos
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identifiantUtilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
        if (identifiantUtilisateurConnecte == null || "".equals(identifiantUtilisateurConnecte)) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("inscription", context, resp.getWriter());
        }else
    { resp.sendRedirect("compte");}
    }

    @Override
    // Requête qui permet d'envoyer des infos
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email"); // récupère "email" du formulaire d'inscription
        String nom = req.getParameter("nom"); // récupère "nom" du formulaire d'inscription
        String prenom = req.getParameter("prenom"); // récupère "prenom" du formulaire d'inscription
        String classe = req.getParameter("classe"); // récupère "classe" du formulaire d'inscription
        String mdp = req.getParameter("password"); // récupère "mdp" du formulaire d'inscription
        try {
            MembreLibrary.getInstance().addMembre(email,nom,prenom,classe,mdp); // Crée une requete SQL grace à la méthode addMembre avec les infos récupérées
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("error");
            showMessageDialog(null,"Erreur d'inscription");
            return;
        }
        resp.sendRedirect("login");
    }
}