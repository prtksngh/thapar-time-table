package com.timetable.endpoints;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.timetable.DataModels.Day;
import com.timetable.DataModels.Subject;
import com.timetable.DataModels.SubjectDayWiseResultSet;
import com.timetable.DataModels.SubjectDayWiseWrapper;
import com.timetable.DataModels.SubjectWiseResultSet;
import com.timetable.Database.SubjectDayWiseDataHelper;
import com.timetable.Database.SubjectWiseDataHelper;

/**
 * Servlet implementation class SubjectWiseData
 */
@WebServlet("/SubjectWiseData")
public class SubjectWiseData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectWiseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String subCode = request.getParameter("subject");
		ArrayList<Subject> subjects = (ArrayList<Subject>) session
				.getAttribute("subjectList");
		ArrayList<SubjectWiseResultSet> resultList = SubjectWiseDataHelper
				.populateSubjectWiseData(subCode);
		ArrayList<SubjectDayWiseResultSet> dayResultList = SubjectDayWiseDataHelper
				.populateSubjectDayWiseData(subCode);
		Subject s = find(subjects, subCode);
		JSONObject subjectData = new JSONObject();
		subjectData.put("subname", s.getSubject_name().toString());
		subjectData.put("lecture", String.valueOf(s.getL_credits()));
		subjectData.put("tutorial", String.valueOf(s.getT_credits()));
		subjectData.put("practical", String.valueOf(s.getP_credits()));

		JSONArray teachers = new JSONArray();
		for (SubjectWiseResultSet sub : resultList) {
			JSONObject details = new JSONObject();
			details.put("name", sub.getTeacher_name());
			details.put("lecture", String.valueOf(sub.getLecture()));
			details.put("tutorial", String.valueOf(sub.getTutorial()));
			details.put("practical", String.valueOf(sub.getPractical()));
			teachers.add(details);

		}
		subjectData.put("teachers", teachers);
		JSONArray daywise = new JSONArray();

		for (SubjectDayWiseResultSet sd : dayResultList) {
			JSONObject day = new JSONObject();
			day.put("name", getDay(sd.getDay_id()).toString());
			JSONArray entries = new JSONArray();
			for (SubjectDayWiseWrapper temp : sd.getEntries()) {
				JSONObject entry = new JSONObject();
				entry.put("teacher", temp.getTeacher());
				entry.put("ltp", temp.getLtp());
				entry.put("room", temp.getRoom_no());
				entry.put("slot",
						temp.getStart_time() + "-" + temp.getEnd_time());
				entries.add(entry);
			}

			day.put("entries", entries);
			daywise.add(day);
		}
		subjectData.put("daywise", daywise);
		out.write(subjectData.toJSONString());

	}

	private Day getDay(int day_id) {
		for (Day d : Day.values()) {

			if (d.id == day_id) {
				return d;
			}
		}

		return null;
	}

	private Subject find(ArrayList<Subject> subjects, String subCode) {

		for (Subject subject : subjects) {
			if (subject.getSubject_id().equals(subCode)) {
				return subject;
			}
		}
		return null;
	}

}
