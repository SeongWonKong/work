package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class VisitorBoardAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/VisitorBoardAdd.jsp");
		rd.include(request, response);

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
					"INSERT INTO VISITOR_BOARD(EMAIL,PWD,CONTENT,DATE)"
					+ " VALUES (?,?,?,NOW())");
			stmt.setString(1,  request.getParameter("email"));
			stmt.setString(2,  request.getParameter("password"));
			stmt.setString(3,  request.getParameter("content"));
			stmt.executeUpdate();
			
			response.sendRedirect("visitorboard");
		/*	response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>방명록 등록 결과</title></head>");
			out.println("<body>");
			out.println("<p>등록 성공입니다!</p>");
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
