package devweb.servlets.admin;

import devweb.managers.MembreLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addpoint")
public class AddPointsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String emailParticipant = req.getParameter("emailParticipant");
        Integer points = Integer.parseInt(req.getParameter("nbPoints"));

        try {
            MembreLibrary.getInstance().addPoint(emailParticipant,points);
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("addarticleErrorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("tournoisAdmin");

    }
}
