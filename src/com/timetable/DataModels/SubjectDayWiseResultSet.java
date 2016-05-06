package com.timetable.DataModels;

import java.util.ArrayList;

public class SubjectDayWiseResultSet {

	
	int day_id;
	ArrayList<SubjectDayWiseWrapper> entries;
	public int getDay_id() {
		return day_id;
	}
	public void setDay_id(int day_id) {
		this.day_id = day_id;
	}
	public ArrayList<SubjectDayWiseWrapper> getEntries() {
		return entries;
	}
	public void setEntries(ArrayList<SubjectDayWiseWrapper> entries) {
		this.entries = entries;
	}	
	
}
