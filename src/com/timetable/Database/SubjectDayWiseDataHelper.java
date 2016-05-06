package com.timetable.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.timetable.DataModels.Subject;
import com.timetable.DataModels.SubjectDayWiseResultSet;
import com.timetable.DataModels.SubjectDayWiseWrapper;
import com.timetable.DataModels.SubjectWiseResultSet;

public class SubjectDayWiseDataHelper {

	public static ArrayList<SubjectDayWiseResultSet> populateSubjectDayWiseData(
			String subCode) {
		Connection conn = DBHandler.getConnection();
		HashMap<Integer, SubjectDayWiseResultSet> map = new HashMap<>();
		ArrayList<SubjectDayWiseResultSet> resultList = new ArrayList<>();
		String query = "SELECT DAY.DAY_ID,TEACHER_RECORD.NAME as teacher,LTP.NAME as ltp,ROOM.ROOM_NO,TIMESLOTS.START_TIME,TIMESLOTS.END_TIME "
				+ "FROM ((((((MASTER "
				+ "JOIN SUBJECT_INFO ON MASTER.SUB_ID=SUBJECT_INFO.SUBJECT_ID) "
				+ "JOIN TEACHER_RECORD ON MASTER.TID=TEACHER_RECORD.ID) "
				+ "JOIN DAY ON MASTER.DID=DAY.DAY_ID) "
				+ "JOIN ROOM ON MASTER.RID=ROOM.ROOM_ID) "
				+ "JOIN LTP ON MASTER.LTP=LTP.ID) "
				+ "JOIN TIMESLOTS ON MASTER.TIME_ID=TIMESLOTS.TIME_ID) "
				+ "WHERE SUB_ID=? "
				+ "group by DAY.DAY_ID,TEACHER_RECORD.NAME,LTP.NAME,ROOM.ROOM_NO,TIMESLOTS.START_TIME,TIMESLOTS.END_TIME "
				+ "ORDER BY DAY.DAY_ID ";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, subCode);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int day_id = rs.getInt("DAY_ID");
				SubjectDayWiseWrapper sd = new SubjectDayWiseWrapper();
				sd.setTeacher(rs.getString("TEACHER"));
				sd.setRoom_no(rs.getString("ROOM_NO"));
				sd.setLtp(rs.getString("LTP"));
				sd.setStart_time(rs.getString("START_TIME"));
				sd.setEnd_time(rs.getString("END_TIME"));

				if (map.containsKey(day_id)) {
					SubjectDayWiseResultSet temp = map.get(day_id);
					temp.getEntries().add(sd);
				} else {
					SubjectDayWiseResultSet temp = new SubjectDayWiseResultSet();
					temp.setDay_id(day_id);
					ArrayList<SubjectDayWiseWrapper> element = new ArrayList<>();
					element.add(sd);
					temp.setEntries(element);
					map.put(day_id, temp);
				}

			}
			for (SubjectDayWiseResultSet value : map.values()) {
			    resultList.add(value);
			}

			return resultList;

		} catch (SQLException e) {
			String msg = e.getMessage();
			return null;
		}

	}
}
