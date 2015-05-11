package domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RBDemo {

	public static void main(String[] args) throws SQLException, IOException {
		
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		FileOutputStream output = null;
		InputStream input = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			stm = conn.createStatement();
			String sql = "select resume from authors where id = 7";
			rs = stm.executeQuery(sql);
			
			File theFile = new File("src/main/resources/dbTest.pdf");
			output = new FileOutputStream(theFile);
			
			if(rs.next()){
				input = rs.getBinaryStream("resume");
				System.out.println("Reading from DB..");
				System.out.println(sql);
				
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0){
					output.write(buffer);
				}
				
				System.out.println("\nData saved to: " + theFile.getAbsolutePath());
				System.out.println("\nTransfer Complete");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input != null){
				input.close();
			}
			if(output != null){
				output.close();
			}
			close(conn, stm);
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
