package com.timetable.endpoints;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.timetable.DataModels.Day;
import com.timetable.DataModels.FacultyTimeTableResultSet;
import com.timetable.DataModels.UgTimeTableResultSet;
import com.timetable.Database.FacultyTimeTableHelper;
import com.timetable.Database.UgTimeTableHelper;

/**
 * Servlet implementation class UndergraduateData
 */
@WebServlet("/UgData")
public class UgData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		//String str=request.getParameter("");
		int groupid = Integer.parseInt(request.getParameter("groupid"));
		int year=Integer.parseInt(request.getParameter("year"));

		UgTimeTableResultSet result[][] = UgTimeTableHelper.populateUgData(groupid, year);
				

		JSONObject timeTable = new JSONObject();

		for (int j = 0; j < 7; j++) {
			JSONArray entries = new JSONArray();
			for (int i = 0; i < 10; i++) {
				JSONObject entry = new JSONObject();
				int start = 8 + i;
				int end = 9 + i;
				entry.put("slot",
						String.valueOf(start) + "-" + String.valueOf(end));
				if (result[j][i] != null) {
					entry.put("teacher", result[j][i].getTeacher());
					entry.put("ltp", result[j][i].getLtp());
					entry.put("room", result[j][i].getRoom());
					entry.put("subject", result[j][i].getSubject());
				} else {
					entry.put("teacher", " ");
					entry.put("ltp", " ");
					entry.put("room", " ");
					entry.put("subject", " ");
				}

				entries.add(entry);
			}
			timeTable.put(getDay(j + 1).toString().toLowerCase(), entries);
		}

		out.write(timeTable.toJSONString());

	}
	private Day getDay(int day_id) {
		for (Day d : Day.values()) {

			if (d.id == day_id) {
				return d;
			}
		}

		return null;
	}


}
