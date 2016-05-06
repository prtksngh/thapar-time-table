package com.timetable.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timetable.Database.DBHandler;

/**
 * Servlet implementation class AddTeacher
 */
@WebServlet("/AddTeacher")
public class AddTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBHandler.getConnection();
		String query = "INSERT INTO TEACHER_RECORD(ID,NAME) VALUES (?,?)";
		String tid = request.getParameter("tid");
		String name = request.getParameter("tname");
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tid);
			ps.setString(2, name);
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
		String msg = (String) request.getAttribute("msg");
		RequestDispatcher rd = request.getRequestDispatcher("AddTeacher.jsp");
		rd.forward(request, response);
	}
}
