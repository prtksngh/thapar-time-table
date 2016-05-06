package com.timetable.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.timetable.DataModels.Subject;
import com.timetable.DataModels.TutGroup;
import com.timetable.Database.DBHandler;

/**
 * Servlet implementation class EntryForm_AddEntry
 */
@WebServlet("/entry")
public class EntryForm_AddEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int rid = 0, did = 0, time_id = 0, grouped_id = 0, groupst_id = 0, ltp = 0;
		request.setAttribute("msg", "Entry Sucessful");
		request.setAttribute("status", "success");
		
		String tid = request.getParameter("teacher");
		try {
			rid = Integer.parseInt(request.getParameter("room"));
			did = Integer.parseInt(request.getParameter("day"));
			time_id = Integer.parseInt(request.getParameter("slot"));
			groupst_id = Integer.parseInt(request.getParameter("groupstart"));
			grouped_id = 0;
			ltp = Integer.parseInt(request.getParameter("ltp"));

			String sub_id = request.getParameter("subject");

			Connection conn;
			if (!request.getParameter("groupend").isEmpty()) {
				grouped_id = Integer.parseInt(request.getParameter("groupend"));
			}

			String insertTableSQL = "INSERT INTO MASTER"
					+ "(TID,RID,DID,TIME_ID,SUB_ID,GROUP_ID,LTP,KEY) VALUES"
					+ "(?,?,?,?,?,?,?,?)";

			if (grouped_id != 0) {
				ArrayList<Integer> grpIdList = getGroupList(session,
						groupst_id, grouped_id);

				for (Integer grp : grpIdList) {
					int group_id = grp.intValue();
					conn = DBHandler.getConnection();
					PreparedStatement ps = conn
							.prepareStatement(insertTableSQL);
					ps.setString(1, tid);
					ps.setInt(2, rid);
					ps.setInt(3, did);
					ps.setInt(4, time_id);
					ps.setString(5, sub_id);
					ps.setInt(6, group_id);
					ps.setInt(7, ltp);
					ps.setTimestamp(8, getCurrentTimeStamp());
					ps.executeUpdate();
					DBHandler.closeConnection();

				}
			} else {
				int group_id = groupst_id;
				conn = DBHandler.getConnection();
				PreparedStatement ps = conn.prepareStatement(insertTableSQL);
				ps.setString(1, tid);
				ps.setInt(2, rid);
				ps.setInt(3, did);
				ps.setInt(4, time_id);
				ps.setString(5, sub_id);
				ps.setInt(6, group_id);
				ps.setInt(7, ltp);
				ps.setTimestamp(8, getCurrentTimeStamp());
				ps.executeUpdate();

			}
		} catch (Exception e) {
			request.setAttribute("msg","Entry unsuccessfull");
			request.setAttribute("status", "failure");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("EntryForm.jsp");
		rd.forward(request, response);

	}

	private ArrayList<Integer> getGroupList(HttpSession session,
			int groupst_id, int grouped_id) {
		ArrayList<Integer> result = new ArrayList<>();

		result.add(groupst_id);
		result.add(grouped_id);

		ArrayList<TutGroup> groups = (ArrayList<TutGroup>) session
				.getAttribute("tutGroupList");

		TutGroup grpStart = getGroupNo(groups, groupst_id);
		TutGroup grpEnd = getGroupNo(groups, grouped_id);

		for (int i = grpStart.getNum() + 1; i < grpEnd.getNum(); i++) {
			int groupId = getGroupId(groups, grpStart.getPrefix(), i);

			result.add(groupId);

		}

		return result;
	}

	private int getGroupId(ArrayList<TutGroup> groups, String prefix, int i) {

		for (TutGroup t : groups) {
			if (t.getPrefix().equals(prefix) && t.getNum() == i) {
				return t.getId();
			}
		}

		return 0;
	}

	private TutGroup getGroupNo(ArrayList<TutGroup> groups, int groupst_id) {

		for (TutGroup g : groups) {
			if (g.getId() == groupst_id) {
				return g;
			}

		}

		return null;
	}

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

}
