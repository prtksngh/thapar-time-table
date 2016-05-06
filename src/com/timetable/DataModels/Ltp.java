package com.timetable.DataModels;

public enum Ltp {
    LECTURE(1),TUTORIAL(2),PRACTICAL(3);
	
    public final int id;
    
    Ltp(int id)
    {
    	this.id=id;
    }
}	

