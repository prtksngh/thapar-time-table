package com.timetable.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBHandler {
    static Connection connection;
    static final String DB_Username = "chirag";
	static final String DB_Password = "chirag";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
    public  ArrayList<String> getSubjectList() throws SQLException {
		ArrayList<String> subList = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement ps = conn
				.prepareStatement("SELECT subject_id FROM SUBJECT_INFO");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			String subject = rs.getString("SUBJECT_ID");
			subList.add(subject);
		}

		return subList;

	}
	public static Connection getConnection() {
		
			
		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return null;

		}

		System.out.println("Oracle JDBC Driver Registered!");


		try {

			connection = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;
	}
	
	public static void closeConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
