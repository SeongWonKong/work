package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import spms.vo.Board;

public class VisitorBoardModifyDao {
	Connection connection;
	Statement stmt = null;
	ResultSet rs = null;
	
	public void setConnection(Connection connection){
		this.connection = connection;
	}
	
	public Board getBoard(String vno) throws Exception{
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select * from VISITOR_BOARD" +
					" where VNO=" + vno);
			
			rs.next();
			
			Board board = new Board()
					.setVno(rs.getInt("VNO"))
					.setEmail(rs.getString("EMAIL"))
					.setContent(rs.getString("CONTENT"))
					.setDate(rs.getDate("DATE"));
			
			return board;
		}catch(Exception e){
			throw e;
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
		}
	}
	
	public String getPW(String vno) throws Exception{
		String pw;
		try{
			stmt = connection.createStatement();		
			rs = stmt.executeQuery(
					"select * from VISITOR_BOARD" +
					" where VNO=" + vno);
			
			rs.next();
			pw = rs.getString("PWD");//DB에 저장된 비밀번호
			stmt.close();
			
			return pw;
		}catch(Exception e){
			throw e;
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
		}
		
	}
	
	public void updateBoard(String vno, String content) throws Exception{
		try{
			PreparedStatement stmt = null;
			stmt = connection.prepareStatement(
					"UPDATE VISITOR_BOARD SET CONTENT=?, UPDATE_DATE=NOW() WHERE VNO="+vno);
			stmt.setString(1, content);
			stmt.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
		}
	}
	
	
}
