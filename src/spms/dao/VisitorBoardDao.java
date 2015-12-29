package spms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Board;

public class VisitorBoardDao {
	Connection connection;
	
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
}
