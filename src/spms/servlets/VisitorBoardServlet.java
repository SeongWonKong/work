package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import spms.vo.Board;



@WebServlet("/visitorboard")
public class VisitorBoardServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(
					"select VNO,CONTENT,EMAIL,DATE,update_date" +
					" from VISITOR_BOARD" +
					" order by update_date DESC");
			response.setContentType("text/html; charset=UTF-8");
			
			ArrayList<Board> boards = new ArrayList<Board>();
			
			while(rs.next()){
				boards.add(new Board()
						.setVno(rs.getInt("VNO"))
						.setEmail(rs.getString("EMAIL"))
						.setContent(rs.getString("CONTENT"))
						.setDate(rs.getDate("DATE"))
						.setTime(rs.getTime("DATE")).setUdate(rs.getDate("update_date")).setUtime(rs.getTime("update_date")));
				
			}
			
			request.setAttribute("boards", boards);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/VisitorBoard.jsp");
			rd.include(request, response);
			
		}
		catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{if(rs!=null) rs.close();} catch(Exception e){}
			try{if(stmt!=null) stmt.close();} catch(Exception e){}
		//	try{if(conn!=null) conn.close();} catch(Exception e){}
		}
	}

}
