package domain;

import java.sql.*;

public class SQLdemo {

	public static void main(String[] args) {

		String select = "Select * from authors";
		String insert = "Insert into authors " + " (name) "
				+ " values ('John Deep')";
		String update = "Update authors set name='John Marks' where id=11";

		try {

			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			Statement stm = myConn.createStatement();

			// Update Query
			stm.executeUpdate(update);
			System.out.println("Update complete! Take a look at the result. ");
			
			// Insert Query
			/* stm.executeUpdate(insert);
			System.out.println("Insert complete! Look at this, what is changed? "); */
			 
			// Select Query
			ResultSet rs = stm.executeQuery(select);

			while (rs.next()) {
				System.out.println(rs.getString("id") + " "
						+ rs.getString("name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
