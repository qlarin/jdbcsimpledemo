package domain;

import java.sql.*;

public class SQLdemo {

	public static void main(String[] args) throws SQLException {

		// List of queries for basic statement
		String select = "Select * from authors";
		String insert = "Insert into authors " + " (name) "
				+ " values ('John Deep')";
		String update = "Update authors set name='John Marks' where id=11";
		String delete = "Delete from authors where id = 9";
		
		// List of queries for prepared statement
		String select2 = "Select * from authors " + " where id >= ?";
		String delete2 = "Delete from authors " + " where id = ?";

		Connection myConn = null;
		PreparedStatement pstm = null;
		ResultSet prs = null;
		
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String[] types = null;
		String columnNamePattern = null;
		
		try {

			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			// Statement stm = myConn.createStatement();

			pstm = myConn.prepareStatement(delete2);
			pstm.setInt(1, 5);
			int rowsAffected = pstm.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
			System.out.println("Delete complete\n");
			pstm.close();
			
			pstm = myConn.prepareStatement(select2);
			pstm.setInt(1, 5);
			prs = pstm.executeQuery();
			display(prs);
			pstm.close();
			System.out.println();
			
			DatabaseMetaData dbmd = myConn.getMetaData();
			
			System.out.println("Product name: " + dbmd.getDatabaseProductName());
			System.out.println("Product version: " + dbmd.getDatabaseProductVersion());
			System.out.println();
			
			System.out.println("JDBC Driver name: " + dbmd.getDriverName());
			System.out.println("JDBC Driver version: " + dbmd.getDriverVersion());
			System.out.println();
			
			System.out.println("Tables in DB:");
			prs = dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
			while(prs.next()){
				System.out.println(prs.getString("TABLE_NAME"));
			}
			System.out.println();
			
			System.out.println("Columns in DB:");
			prs = dbmd.getColumns(catalog, schemaPattern, "authors", columnNamePattern);
			while(prs.next()){
				System.out.println(prs.getString("COLUMN_NAME"));
			}
			System.out.println();
			
			// Delete Query
			/*int rowsAffected = stm.executeUpdate(delete);
			System.out.println("Rows affected: " + rowsAffected);
			System.out.println("Delete complete! And this is the current table");*/
			
			// Update Query
			/*stm.executeUpdate(update);
			System.out.println("Update complete! Take a look at the result. ");*/
			
			// Insert Query
			/*stm.executeUpdate(insert);
			System.out.println("Insert complete! Look at this, what is changed? ");*/
			 
			// Select Query
			/*ResultSet rs = stm.executeQuery(select);
			while (rs.next()) {
				System.out.println(rs.getString("id") + " "
						+ rs.getString("name"));
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(prs != null){
				prs.close();
			}
			if(pstm != null){
				pstm.close();
			}
			if(myConn != null){
				myConn.close();
			}
		}
	}


	private static void display(ResultSet rs) throws SQLException {
		while (rs.next()) {
			System.out.println(rs.getString("id") + " "
					+ rs.getString("name"));
		}
	}

}
