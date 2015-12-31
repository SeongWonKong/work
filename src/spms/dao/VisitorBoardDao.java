package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Board;

public class VisitorBoardDao {
	Connection connection;
	Statement stmt = null;
	ResultSet rs = null;
	public void setConnection(Connection connection){
		this.connection = connection;
	}
	
	public List<Board> selectList() throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select VNO,CONTENT,EMAIL,DATE,update_date" +
							" from VISITOR_BOARD" +
							" order by update_date DESC");
			ArrayList<Board> boards = new ArrayList<Board>();
			
			while(rs.next()){
				boards.add(new Board()
						.setVno(rs.getInt("VNO"))
						.setEmail(rs.getString("EMAIL"))
						.setContent(rs.getString("CONTENT"))
						.setDate(rs.getDate("DATE"))
						.setTime(rs.getTime("DATE"))
						.setUdate(rs.getDate("update_date"))
						.setUtime(rs.getTime("update_date")));
			}
			return boards;
		}catch(Exception e){
			throw e;
			
		}finally{
			try{if(rs!=null) rs.close();} catch(Exception e){}
			try{if(stmt!=null) stmt.close();} catch(Exception e){}
		}
	}
	
	public void insertDB(String email,String pw, String content) throws Exception{
		PreparedStatement stmt = null;

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
