package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WBDemo {

	public static void main(String[] args) throws SQLException, IOException{
		
		Connection conn = null;
		PreparedStatement pstm = null;
		FileInputStream input = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			
			String sql = "update authors set resume = ? where id = 7";
			pstm = conn.prepareStatement(sql);
			
			File theFile = new File("src/main/resources/Test.pdf");
			input = new FileInputStream(theFile);
			pstm.setBinaryStream(1, input);
			System.out.println("Reading input: " + theFile.getAbsolutePath());
			System.out.println("\nStoring resume in DB: " + theFile);
			System.out.println(sql);
			
			pstm.executeUpdate();
			System.out.println("\nTransfer complete");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input != null){
				input.close();
			}
			close(conn, pstm);
		}

	}
	
	private static void close(Connection conn, Statement stm)
			throws SQLException {

		if (stm != null) {
			stm.close();
		}
		
		if (conn != null) {
			conn.close();
		}
	}

}
