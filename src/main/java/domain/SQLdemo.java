package domain;

import java.sql.*;

public class SQLdemo {

	public static void main(String[] args) {
		
		
		try{
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("Select * from authors;");
			
			while(rs.next()){
				System.out.println(rs.getString("id") + " " + rs.getString("name"));
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
