package com.timetable.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.timetable.DataModels.Subject;
import com.timetable.DataModels.SubjectWiseResultSet;

public class SubjectWiseDataHelper {

	public static ArrayList<SubjectWiseResultSet> populateSubjectWiseData(String subCode) {
		Connection conn = DBHandler.getConnection();
		HashMap<String, SubjectWiseResultSet> map = new HashMap<String, SubjectWiseResultSet>();
        ArrayList<SubjectWiseResultSet> resultList=new ArrayList<>();
		String query = "SELECT TEACHER_RECORD.NAME , COUNT(LTP.NAME) AS \"COUNT\" ,LTP.ID AS \"CLASS\" "
				+ "FROM (((UNIQUE_MASTER "
				+ "JOIN SUBJECT_INFO ON UNIQUE_MASTER.SUB_ID=SUBJECT_INFO.SUBJECT_ID) "
				+ "JOIN TEACHER_RECORD ON UNIQUE_MASTER.TID=TEACHER_RECORD.ID) "
				+ "JOIN LTP ON UNIQUE_MASTER.LTP=LTP.ID) "
				+ "WHERE SUB_ID=? " + "GROUP BY TEACHER_RECORD.NAME,LTP.ID ";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, subCode);
			ResultSet rs = ps.executeQuery();
			Subject sub = null;
			SubjectWiseResultSet temp;
			while (rs.next()) {
				if (map.containsKey(rs.getString("NAME"))) {

					temp = map.get(rs.getString("NAME"));

				} else {
					temp = new SubjectWiseResultSet();
					temp.setTeacher_name(rs.getString("NAME"));
					map.put(rs.getString("NAME"), temp);

				}

				switch (rs.getInt("CLASS")) {

				case 1: {
					temp.setLecture(rs.getInt("COUNT"));
				}
				case 2: {
					temp.setTutorial(rs.getInt("COUNT"));
				}
				case 3: {
					temp.setPractical(rs.getInt("COUNT"));
				}

				}
			}
			
			for (SubjectWiseResultSet value : map.values()) {
			    resultList.add(value);
			}

			return resultList;
			
			

		} catch (SQLException e) {
			String msg=e.getMessage();
			return null;
		}

	}

}
