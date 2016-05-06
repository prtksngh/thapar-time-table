package com.timetable.Authentication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import com.timetable.Database.DBHandler;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/authenticate")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("userid");
		String password = request.getParameter("userpass");
		String query = "SELECT PASSWORD,ROLE,TID FROM AUTH WHERE USER_ID=? AND PASSWORD=?";
		PreparedStatement ps;
		ResultSet rs;
		String role;
		Connection conn = DBHandler.getConnection();

			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();

				boolean status = rs.next();
				String s;
				if (status) {
					role = rs.getString("ROLE");
					HttpSession session=request.getSession();
					session.setAttribute("tid", rs.getString("TID"));
					session.setAttribute("role", role);
					RequestDispatcher rd = request
							.getRequestDispatcher("Info.jsp");
					rd.forward(request, response);

				} else {

					RequestDispatcher rd = request
							.getRequestDispatcher("Login.jsp");
					request.setAttribute("err", "Invalid username/password");
					rd.forward(request, response);

				}

			} catch (SQLException e) {

				RequestDispatcher rd = request
						.getRequestDispatcher("Login.jsp");
				request.setAttribute("err", "Invalid username/password");
				rd.forward(request, response);

			}

		}

	
}
