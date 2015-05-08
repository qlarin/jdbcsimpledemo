package domain;

import java.sql.*;

public class MDdemo {

	public static void main(String[] args) throws SQLException {

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
}