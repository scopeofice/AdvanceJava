package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtils;

@WebServlet("/candidate_list")
public class CandidateList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        try (PrintWriter pw = response.getWriter()) {
            Connection cn = null;
            PreparedStatement pst = null;
            ResultSet rst = null;

            try {
                cn = DBUtils.openConnection();
                pst = cn.prepareStatement("SELECT * FROM candidates");
                rst = pst.executeQuery();

                pw.println("<table style=\"border-collapse: collapse; margin: auto;\">");
                pw.println("<tr><th>ID</th><th>Name</th><th>Party</th><th>Votes</th></tr>");

                while (rst.next()) {
                    pw.println("<tr>");
                    pw.println("<td>" + rst.getInt("id") + "</td>");
                    pw.println("<td>" + rst.getString("name") + "</td>");
                    pw.println("<td>" + rst.getString("party") + "</td>");
                    pw.println("<td>" + rst.getInt("votes") + "</td>");
                    pw.println("</tr>");
                }

                pw.println("</table>");
            } catch (SQLException e) {
                throw new ServletException("Error retrieving candidate list", e);
            } finally {
                if (rst != null)
                    rst.close();
                if (pst != null)
                    pst.close();
                DBUtils.closeConnection();
            }
        } catch (SQLException e) {
            throw new ServletException("Error establishing database connection", e);
        }
    }
}
