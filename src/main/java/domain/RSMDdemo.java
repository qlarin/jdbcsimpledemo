package domain;

import java.sql.*;

public class RSMDdemo {

	public static void main(String[] args) throws SQLException {
		
		Connection myConn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		
		
		try{
			
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			stm = myConn.createStatement();
			rs = stm.executeQuery("select * from authors");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int columnCount = rsmd.getColumnCount();
			System.out.println("Column count: " + columnCount + "\n");
			
			for(int c=1; c <= columnCount; c++){
				System.out.println("Column name: " + rsmd.getCatalogName(c));
				System.out.println("Column type: " + rsmd.getColumnTypeName(c));
				System.out.println("Is nullable: " + rsmd.isNullable(c));
				System.out.println("Is auto increment: " + rsmd.isAutoIncrement(c) + "\n");
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				rs.close();
			}
			if(stm != null){
				stm.close();
			}
			if(myConn != null){
				myConn.close();
			}
		}

	}

}
