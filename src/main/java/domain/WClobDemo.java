package domain;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WClobDemo {

	public static void main(String[] args) throws SQLException, IOException{
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		FileReader input = null;
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			String sql = "update authors set clob_object = ? where id = 7";
			pstm = conn.prepareStatement(sql);
			
			File theFile = new File("src/main/resources/Test.txt");
			input = new FileReader(theFile);
			pstm.setCharacterStream(1, input);
			
			System.out.println("Reading input file: " + theFile.getAbsolutePath());
			System.out.println("\nStoring in DB: " + theFile);
			System.out.println(sql);
			
			pstm.executeUpdate();
			System.out.println("\nTransfer Complete");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input != null){
				input.close();
			}
			close(conn, pstm);
		}

	}
	
	private static void close(Connection conn, Statement stmt)
			throws SQLException {

		if (stmt != null) {
			stmt.close();
		}
		
		if (conn != null) {
			conn.close();
		}
	}

}
