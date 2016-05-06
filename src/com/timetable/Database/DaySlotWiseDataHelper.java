package com.timetable.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.timetable.DataModels.DaySlotWiseResultSet;
import com.timetable.DataModels.SubjectDayWiseResultSet;
import com.timetable.DataModels.SubjectDayWiseWrapper;

public class DaySlotWiseDataHelper {
	public static ArrayList<DaySlotWiseResultSet> populateDaySlotWiseData(
			int start_time, int end_time, int day_id) {
		Connection conn = DBHandler.getConnection();
		HashMap<String, DaySlotWiseResultSet> map = new HashMap<>();
		ArrayList<DaySlotWiseResultSet> resultList = new ArrayList<>();
		String query = "SELECT TEACHER_RECORD.NAME,LTP.NAME as ltp,TIMESLOTS.START_TIME || '-' || TIMESLOTS.END_TIME AS SLOT,TUTGROUPS.PREFIX || '-' || TUTGROUPS.NUM AS TUTGROUP,ROOM.ROOM_NO AS ROOM "
				+ "FROM (((((((MASTER "
				+ "JOIN SUBJECT_INFO ON MASTER.SUB_ID=SUBJECT_INFO.SUBJECT_ID) "
				+ "JOIN TEACHER_RECORD ON MASTER.TID=TEACHER_RECORD.ID) "
				+ "JOIN DAY ON MASTER.DID=DAY.DAY_ID) "
				+ "JOIN ROOM ON MASTER.RID=ROOM.ROOM_ID) "
				+ "JOIN LTP ON MASTER.LTP=LTP.ID) "
				+ "JOIN TIMESLOTS ON MASTER.TIME_ID=TIMESLOTS.TIME_ID) "
				+ "JOIN TUTGROUPS ON MASTER.GROUP_ID=TUTGROUPS.GROUPID)"
				+ "WHERE (TIMESLOTS.START_TIME=? OR TIMESLOTS.END_TIME=? ) AND DAY.DAY_ID=? "
				+ "ORDER BY TEACHER_RECORD.ID,TUTGROUPS.NUM";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, start_time);
			ps.setInt(2, end_time);
			ps.setInt(3, day_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String teacher = rs.getString("NAME");
				String slot = rs.getString("SLOT");
				String group = rs.getString("TUTGROUP");
				String ltp = rs.getString("LTP");
				String room = rs.getString("ROOM");
				if (map.containsKey(teacher + slot)) {
					DaySlotWiseResultSet temp = map.get(teacher+slot);
					temp.setGroup(temp.getGroup() + "," + group);
				} else {
					DaySlotWiseResultSet temp = new DaySlotWiseResultSet();
					temp.setSlot(slot);
					temp.setLtp(ltp);
					temp.setRoom(room);
					temp.setTeacher(teacher);
					temp.setGroup(group);
					map.put(teacher + slot, temp);
				}

			}
			for (DaySlotWiseResultSet value : map.values()) {
				resultList.add(value);
			}

			return resultList;

		} catch (SQLException e) {
			String msg = e.getMessage();
			return null;
		}

	}

}
