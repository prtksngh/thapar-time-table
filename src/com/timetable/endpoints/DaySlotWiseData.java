package com.timetable.endpoints;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.timetable.DataModels.Day;
import com.timetable.DataModels.DaySlotWiseResultSet;
import com.timetable.DataModels.FacultyTimeTableResultSet;
import com.timetable.DataModels.Subject;
import com.timetable.DataModels.Timeslots;
import com.timetable.Database.DaySlotWiseDataHelper;
import com.timetable.Database.FacultyTimeTableHelper;

/**
 * Servlet implementation class DaySlotWiseData
 */
@WebServlet("/DaySlotWiseData")
public class DaySlotWiseData extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ArrayList<Timeslots> timeSlotList = (ArrayList<Timeslots>) session
				.getAttribute("timeSlotList");
		String dayid = request.getParameter("dayid");
		String slotid = request.getParameter("slotid");
		Timeslots slot = getTimeSlot(timeSlotList, Integer.parseInt(slotid));

		ArrayList<DaySlotWiseResultSet> result = DaySlotWiseDataHelper
				.populateDaySlotWiseData(slot.getStart_time(),
						slot.getEnd_time(), Integer.parseInt(dayid));

		JSONObject daySlot = new JSONObject();
		JSONArray entries = new JSONArray();

		for (DaySlotWiseResultSet ds : result) {
			JSONObject entry = new JSONObject();

			entry.put("ltp", ds.getLtp());
			entry.put("room", ds.getRoom());
			entry.put("slot", ds.getSlot());
			entry.put("teacher", ds.getTeacher());
			entry.put("group", ds.getGroup());
			entries.add(entry);
		}

		daySlot.put("entries", entries);

		out.print(daySlot);

	}

	private Timeslots getTimeSlot(ArrayList<Timeslots> timeSlotList, int slot) {

		for (Timeslots t : timeSlotList) {
			if (t.getId() == slot) {
				return t;
			}
		}

		return null;
	}

}
