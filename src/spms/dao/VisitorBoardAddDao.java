package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class VisitorBoardAddDao {
	Connection connection;
	PreparedStatement stmt = null;
	public void setConnection(Connection connection){
		this.connection = connection;
	}

	public void insertDB(String email,String pw, String content) throws Exception{
		try{
			stmt = connection.prepareStatement(
					"INSERT INTO VISITOR_BOARD(EMAIL,PWD,CONTENT,DATE,update_date)"
					+ " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1,  email);
			stmt.setString(2,  pw);
			stmt.setString(3,  content);
			stmt.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
		}
	}
}
