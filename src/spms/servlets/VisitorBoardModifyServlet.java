package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
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
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>���� ���</title></head>");
			out.println("<body><h1>���� ���</h1>");
			out.println("<form action='modify' method='post'>");
			out.println();
			out.println("EMAIL : <input type='text' name='email' value='"+rs.getString("EMAIL")+"' readonly><br>");
			out.println("��ȣ : <input type='password' name='password' required><br>");
			out.println("���� : <input type='text' name='content' value='"+rs.getString("CONTENT")+"'><br>");
			out.println("<input type='hidden' name='vno' value='"+ request.getParameter("vno") +"'>");
			out.println("<input type='submit' value='�����ϱ�'>");
			out.println("<input type='reset' value='���'>");
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
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>���� ��� ���</title></head>");
			out.println("<body>");
			out.println("<p>���� �����Դϴ�!</p>");
			out.println("<a href='visitorboard'>�������</a>");
			out.println("</body></html>");
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
			try {if(conn!=null) conn.close();} catch(Exception e){}
		}
	}
}
