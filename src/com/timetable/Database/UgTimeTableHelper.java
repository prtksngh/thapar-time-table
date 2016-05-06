package com.timetable.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.timetable.DataModels.FacultyTimeTableResultSet;
import com.timetable.DataModels.UgTimeTableResultSet;

public class UgTimeTableHelper {

	public static UgTimeTableResultSet[][] populateUgData(int groupid,
			int year) {
		Connection conn = DBHandler.getConnection();
		UgTimeTableResultSet[][] result = new UgTimeTableResultSet[7][10];
		String query = "SELECT DAY.DAY_ID AS DAY,TEACHER_RECORD.ID AS TEACHER,SUBJECT_INFO.SUBJECT_ID AS SUBJECT,LTP.NAME AS LTP,TIMESLOTS.START_TIME,TIMESLOTS.END_TIME,ROOM.ROOM_NO AS ROOM "
				+ "FROM (((((((MASTER "
				+ "JOIN SUBJECT_INFO ON MASTER.SUB_ID=SUBJECT_INFO.SUBJECT_ID) "
				+ "JOIN TEACHER_RECORD ON MASTER.TID=TEACHER_RECORD.ID) "
				+ "JOIN TUTGROUPS ON MASTER.GROUP_ID=TUTGROUPS.GROUPID) "
				+ "JOIN DAY ON MASTER.DID=DAY.DAY_ID) "
				+ "JOIN ROOM ON MASTER.RID=ROOM.ROOM_ID) "
				+ "JOIN LTP ON MASTER.LTP=LTP.ID) "
				+ "JOIN TIMESLOTS ON MASTER.TIME_ID=TIMESLOTS.TIME_ID) "
				+ "WHERE TUTGROUPS.GROUPID=? AND SUBJECT_INFO.YEAR=? "
				+ "ORDER BY DAY.DAY_ID";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, groupid);
			ps.setInt(2, year);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String subject = rs.getString("SUBJECT");
				String teacher = rs.getString("TEACHER");
				String ltp = rs.getString("LTP");
				String room = rs.getString("ROOM");
				int start_time = rs.getInt("START_TIME");
				int end_time = rs.getInt("END_TIME");
				int day_id = rs.getInt("DAY");
				int rowIndex = getRowIndex(day_id);
				int colIndex = getColIndex(start_time);

				if (result[rowIndex][colIndex] == null) {
					UgTimeTableResultSet temp = new UgTimeTableResultSet();
					temp.setTeacher(teacher);
					temp.setLtp(ltp);
					temp.setRoom(room);
					temp.setSubject(subject);
					result[rowIndex][colIndex] = temp;
					if (end_time - start_time > 1)
						result[rowIndex][colIndex + 1] = temp;
				}

			}

		} catch (SQLException e) {

			String msg = e.getMessage();

			return null;
		}

		return result;

	}

	private static int getRowIndex(int day_id) {

		return day_id - 1;

	}

	private static int getColIndex(int start_time) {

		return start_time - 8;
	}

}
