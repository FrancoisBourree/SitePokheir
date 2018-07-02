package devweb.servlets.admin;

import devweb.managers.MembreLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/eliminer")
public class EliminerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String emailParticipant = req.getParameter("emailParticipant");

        try {
            MembreLibrary.getInstance().desinscrire(emailParticipant);
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("eliminerParticipantErrorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("tournoisAdmin");

    }

}
