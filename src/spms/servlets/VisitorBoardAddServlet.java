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

import security.sha256.SecurityUtil;

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
			SecurityUtil securityUtil = new SecurityUtil();
			String pswd = securityUtil.encryptSHA256(request.getParameter("password"));
			
			conn = (Connection)sc.getAttribute("conn");
			
			stmt = conn.prepareStatement(
					"INSERT INTO VISITOR_BOARD(EMAIL,PWD,CONTENT,DATE)"
					+ " VALUES (?,?,?,NOW())");
			stmt.setString(1,  request.getParameter("email"));
			stmt.setString(2,  pswd);
			stmt.setString(3,  request.getParameter("content"));
			stmt.executeUpdate();
			
			response.sendRedirect("visitorboard");
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
		}
	}
}
