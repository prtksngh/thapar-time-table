package com.timetable.admin;

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

import com.timetable.DataModels.Subject;
import com.timetable.Database.DBHandler;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {

		Connection conn = DBHandler.getConnection();
		String query = "INSERT INTO AUTH(USER_ID,TID,PASSWORD) VALUES (?,?,?)";
		String userid = request.getParameter("userid");
		String tid = request.getParameter("tid");
		String password = request.getParameter("password");
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, userid);
			ps.setString(2, tid);
			ps.setString(3, password);
			int status = ps.executeUpdate();
			if (status > 0) {
				request.setAttribute("msg", "success");
			} else {
				request.setAttribute("msg", "failure");						
			}
		}
		
		catch (SQLException e) {
			request.setAttribute("msg", "failure");
		}
String msg=(String) request.getAttribute("msg");
		RequestDispatcher rd=request.getRequestDispatcher("AddUser.jsp");
		rd.forward(request, response);
	}

}
