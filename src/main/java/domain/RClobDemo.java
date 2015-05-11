package domain;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RClobDemo {

	public static void main(String[] args) throws SQLException, IOException{
		
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		Reader input = null;
		FileWriter output = null;
		
		try{
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user1", "user1");
			stm = conn.createStatement();
			String sql = "Select clob_object from authors where id = 7";
			rs = stm.executeQuery(sql);
			
			File theFile = new File("src/main/resources/dbTest.txt");
			output = new FileWriter(theFile);
			
			if(rs.next()){
				input = rs.getCharacterStream("clob_object");
				System.out.println("Reading clob_object from DB..");
				System.out.println(sql);
				
				int theChar;
				while((theChar = input.read()) > 0){
					output.write(theChar);
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
