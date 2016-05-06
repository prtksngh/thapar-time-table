package com.timetable.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.timetable.DataModels.Room;
import com.timetable.DataModels.Subject;
import com.timetable.DataModels.Teacher;
import com.timetable.DataModels.Teacher;
import com.timetable.DataModels.Timeslots;
import com.timetable.DataModels.TutGroup;
import com.timetable.DataModels.TutGroup;

public class InfoPopulator {

	public  ArrayList<Subject> subList=new ArrayList<>();
	public  ArrayList<Timeslots> timeSlotList=new ArrayList<>();
	public  ArrayList<Room> roomList=new ArrayList<>();
	public  ArrayList<Teacher> teacherList=new ArrayList<>();
	public  ArrayList<TutGroup> tutGroupList=new ArrayList<>();
	public  void populateSubjects() {
		Connection conn = DBHandler.getConnection();
		String query = "SELECT * FROM SUBJECT_INFO";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Subject sub = null;
			while (rs.next()) {
				sub = new Subject();
				sub.setSubject_id(rs.getString("SUBJECT_ID"));
				sub.setSubject_name(rs.getString("SUBJECT_NAME"));
				sub.setL_credits(rs.getDouble("L_CR"));
				sub.setP_credits(rs.getDouble("P_CR"));
				sub.setT_credits(rs.getDouble("T_CR"));
				subList.add(sub);
			}

		} catch (SQLException e) {
			return;
		}

	}

	public  void populateTeachers() {
		Connection conn = DBHandler.getConnection();
		String query = "SELECT * FROM TEACHER_RECORD";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Teacher teacher = null;
			while (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getString("ID"));
				teacher.setName(rs.getString("NAME"));
				teacherList.add(teacher);
			}

		} catch (SQLException e) {
			return;
		}

	}

	public void populateTutGroups() {
		Connection conn = DBHandler.getConnection();
		String query = "SELECT * FROM TUTGROUPS ORDER BY GROUPID";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			TutGroup grp = null;
			while (rs.next()) {
				grp = new TutGroup();
				grp.setId(Integer.parseInt(rs.getString("GROUPID")));
				grp.setPrefix(rs.getString("PREFIX"));
				grp.setNum(rs.getInt("NUM"));
				tutGroupList.add(grp);
			}
		} catch (SQLException e) {
			return;
		}

	}

	public  void populateRooms() {
		Connection conn = DBHandler.getConnection();
		String query = "SELECT * FROM ROOM";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Room room = null;
			while (rs.next()) {
				room = new Room();
				room.setRoom_no(rs.getString("ROOM_NO"));
				room.setId(Integer.parseInt(rs.getString("ROOM_ID")));
				roomList.add(room);
			}
		} catch (SQLException e) {
			return;
		}
	}

	public void populateTimeSlots() {
		Connection conn = DBHandler.getConnection();
		String query = "SELECT * FROM TIMESLOTS ORDER BY TIME_ID";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Timeslots slot = null;
			while (rs.next()) {
				slot = new Timeslots();
				slot.setId(Integer.parseInt(rs.getString("TIME_ID")));
				slot.setStart_time(Integer.parseInt(rs.getString("START_TIME")));
				slot.setEnd_time(Integer.parseInt(rs.getString("END_TIME")));
				timeSlotList.add(slot);
			}
		} catch (SQLException e) {
			return;
		}
	}
	
	public void populateAll()
	{
		populateRooms();
		populateSubjects();
		populateTimeSlots();
		populateTutGroups();
		populateTeachers();
				
	}
	public String getTeacherName(String teacherId)
	{
		String teacherName=" ";
		for(Teacher t:teacherList)
		{
			if(t.getId().equals(teacherId))
			{
				teacherName=t.getName();
				break;
			}
			
		}
		
		return teacherName;
	}

}
