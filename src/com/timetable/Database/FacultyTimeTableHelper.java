package com.timetable.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.timetable.DataModels.DaySlotWiseResultSet;
import com.timetable.DataModels.FacultyTimeTableResultSet;

public class FacultyTimeTableHelper {

	public static FacultyTimeTableResultSet[][] populateFacultyData(
			String teacherid) {
		Connection conn = DBHandler.getConnection();
		FacultyTimeTableResultSet[][] result = new FacultyTimeTableResultSet[7][10];
		String query = "SELECT DAY.DAY_ID AS DAY,SUBJECT_INFO.SUBJECT_ID AS SUBJECT,LTP.NAME AS LTP,TIMESLOTS.START_TIME,TIMESLOTS.END_TIME,ROOM.ROOM_NO AS ROOM ,TUTGROUPS.PREFIX ||TUTGROUPS.NUM AS TUTGROUP "
				+ "FROM (((((((MASTER "
				+ "JOIN SUBJECT_INFO ON MASTER.SUB_ID=SUBJECT_INFO.SUBJECT_ID) "
				+ "JOIN TEACHER_RECORD ON MASTER.TID=TEACHER_RECORD.ID) "
				+ "JOIN TUTGROUPS ON MASTER.GROUP_ID=TUTGROUPS.GROUPID) "
				+ "JOIN DAY ON MASTER.DID=DAY.DAY_ID) "
				+ "JOIN ROOM ON MASTER.RID=ROOM.ROOM_ID) "
				+ "JOIN LTP ON MASTER.LTP=LTP.ID) "
				+ "JOIN TIMESLOTS ON MASTER.TIME_ID=TIMESLOTS.TIME_ID) "
				+ "WHERE TEACHER_RECORD.ID=? " + "ORDER BY DAY.DAY_ID,TUTGROUPS.NUM ";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, teacherid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String subject = rs.getString("SUBJECT");
				String group = rs.getString("TUTGROUP");
				String ltp = rs.getString("LTP");
				String room = rs.getString("ROOM");
				int start_time = rs.getInt("START_TIME");
				int end_time = rs.getInt("END_TIME");
				int day_id = rs.getInt("DAY");
				int rowIndex = getRowIndex(day_id);
				int colIndex = getColIndex(start_time);

					if (result[rowIndex][colIndex] == null) {
						FacultyTimeTableResultSet temp = new FacultyTimeTableResultSet();
						temp.setGroupstart(group);
						temp.setLtp(ltp);
						temp.setRoom(room);
						temp.setSubject(subject);
						result[rowIndex][colIndex]=temp;
						if(end_time-start_time>1)
							result[rowIndex][colIndex+1]=temp;
					}
					else
					{
						FacultyTimeTableResultSet temp=result[rowIndex][colIndex];
						temp.setGroupend(group);	
					}
				}

			

		} catch (SQLException e) {
			
			String msg=e.getMessage();
			
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
