package domain;

import java.sql.*;

public class SQLdemo {

	public static void main(String[] args) {
		
		String select = "Select * from authors";
		String insert = "Insert into authors " 
				+ " (name) "
				+ " values ('John Deep')";

		try{
					
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			Statement stm = myConn.createStatement();
		
			stm.executeUpdate(insert);
			System.out.println("Insert complete! Look at this, what is changed? ");

			ResultSet rs  = stm.executeQuery(select);
			
			while(rs.next()){
				System.out.println(rs.getString("id") + " " + rs.getString("name"));
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
