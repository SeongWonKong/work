package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Board;

@WebServlet("/modify")
public class VisitorBoardModifyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(
					"select * from VISITOR_BOARD" +
					" where VNO=" + request.getParameter("vno"));
			
			rs.next();
			response.setContentType("text/html; charset=UTF-8");
			
			Board board = new Board()
		//	ArrayList<Board> boards = new ArrayList<Board>();		
		//	boards.add(new Board()
					.setVno(rs.getInt("VNO"))
					.setEmail(rs.getString("EMAIL"))
					.setContent(rs.getString("CONTENT"))
					.setDate(rs.getDate("DATE"));//);
			request.setAttribute("board", board);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/VisitorBoardModify.jsp");
			rd.include(request, response);
			

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
			try {if(conn!=null) conn.close();} catch(Exception e){}
		}
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			
			stmt = conn.prepareStatement(
					"UPDATE VISITOR_BOARD SET CONTENT=? WHERE VNO="+request.getParameter("vno"));
			stmt.setString(1,  request.getParameter("content"));
			stmt.executeUpdate();
			
			response.sendRedirect("visitorboard");
		/*	response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>방명록 등록 결과</title></head>");
			out.println("<body>");
			out.println("<p>수정 성공입니다!</p>");
			out.println("<a href='visitorboard'>목록으로</a>");
			out.println("</body></html>");*/
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
			try {if(conn!=null) conn.close();} catch(Exception e){}
		}
	}
}
