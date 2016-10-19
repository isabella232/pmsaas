package ch.zhaw.init.walj.projectmanagement.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.zhaw.init.walj.projectmanagement.util.DBConnection;
import ch.zhaw.init.walj.projectmanagement.util.HTMLHeader;

/**
 * Projectmanagement tool, Page to delete tasks
 * 
 * @author Janine Walther, ZHAW
 * 
 */

@SuppressWarnings("serial")
@WebServlet("/Projects/deleteEffort")
public class DeleteEffort extends HttpServlet {

	// Database access information
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "projectmanagement";
	String userName = "Janine";
	String password = "test123";

	// connection to database
	private DBConnection con = new DBConnection(url, dbName, userName, password);

	@Override
	// method to handle get-requests
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF8");

		// get project given from get-parameter projectID
		int effortID = Integer.parseInt(request.getParameter("effortID"));
		int projectID = Integer.parseInt(request.getParameter("projectID"));
		
		String message = "";

		// delete project from database
		try {
			con.deleteEffort(effortID);
			// success message
			message = "<div class=\"row\">"
					+ "<div class=\"small-12 columns align-center\">" 
				    + "<h2>The booking has sucessfully been deleted.</h2>"
					+ "<a href=\"/Projektverwaltung/Projects/Edit?projectID=" + projectID + "#effort\">Click here to go back to the edit page</a>"
					+ "<br>"
					+ "<a href=\"/Projektverwaltung/Projects/Overview/Project?id=" + projectID + "\">Click here to go to the project overview</a>"
					+ "</div>"
					+ "</div>";
		} catch (SQLException e) {
			// error message
			message = "<div class=\"row\">"
					+ "<div class=\"small-12 columns align-center\">" 
				    + "<h2>The booking could not be deleted</h2>"
					+ "<a href=\"/Projektverwaltung/Projects/Edit?projectID=" + projectID + "#effort\">Click here to go back to the edit page</a>"
					+ "<br>"
					+ "<a href=\"/Projektverwaltung/Projects/Overview/Project?id=" + projectID + "\">Click here to go to the project overview</a>"
					+ "</div>"
					+ "</div>";			
		}
		
		PrintWriter out = response.getWriter();

		// Print HTML head and header
		out.println(HTMLHeader.getInstance().getHeader("Delete Effort", "../", "Delete Effort")
				  + "<section>"
				  + message
				  + "</section>"
				  + "</div>"
				  + "<script src=\"../js/vendor/jquery.js\"></script>"
				  + "<script src=\"../js/vendor/foundation.min.js\"></script>"
				  + "<script>$(document).foundation();</script>"
				  + "</body>"
				  + "</html>");
	}
}
