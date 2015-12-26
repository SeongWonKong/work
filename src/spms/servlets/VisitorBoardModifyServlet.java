package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modify")
public class VisitorBoardModifyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb",
					"study",
					"study");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select * from VISITOR_BOARD" +
					" where VNO=" + request.getParameter("vno"));
			
			rs.next();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>방명록 등록</title></head>");
			out.println("<body><h1>방명록 등록</h1>");
			out.println("<form action='add' method='post'>");
			out.println();
			out.println("EMAIL : <input type='text' name='email' value='"+rs.getString("EMAIL")+"' disabled><br>");
			out.println("암호 : <input type='password' name='password'><br>");
			out.println("내용 : <input type='text' name='content' value="+rs.getString("CONTENT")+"'><br>");
			out.println("<input type='submit' value='수정하기'>");
			out.println("<input type='reset' value='취소'>");
			out.println("</form>");
			out.println("</body></html>");
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
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb",
					"study",
					"study");
			stmt = conn.prepareStatement(
					"INSERT INTO VISITOR_BOARD(EMAIL,PWD,CONTENT,DATE)"
					+ " VALUES (?,?,?,NOW())");
			stmt.setString(1,  request.getParameter("email"));
			stmt.setString(2,  request.getParameter("password"));
			stmt.setString(3,  request.getParameter("content"));
			stmt.executeUpdate();
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>방명록 등록 결과</title></head>");
			out.println("<body>");
			out.println("<p>등록 성공입니다!</p>");
			out.println("<a href='visitorboard'>목록으로</a>");
			out.println("</body></html>");
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
			try {if(conn!=null) conn.close();} catch(Exception e){}
		}
	}
}
