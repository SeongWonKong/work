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

import security.sha256.SecurityUtil;
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
			
			conn = (Connection)sc.getAttribute("conn");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select * from VISITOR_BOARD" +
					" where VNO=" + request.getParameter("vno"));
			
			rs.next();
			response.setContentType("text/html; charset=UTF-8");
			
			Board board = new Board()
					.setVno(rs.getInt("VNO"))
					.setEmail(rs.getString("EMAIL"))
					.setContent(rs.getString("CONTENT"))
					.setDate(rs.getDate("DATE"));
			
			request.setAttribute("board", board);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/VisitorBoardModify.jsp");
			rd.include(request, response);
			

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
		}
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		Statement stmt1 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String pw1, pw2;
		try{
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			stmt1 = conn.createStatement();		
			rs = stmt1.executeQuery(
					"select * from VISITOR_BOARD" +
					" where VNO=" + request.getParameter("vno"));
			
			rs.next();
			pw1 = rs.getString("PWD");//DB에 저장된 비밀번호
			stmt1.close();
			SecurityUtil securityUtil = new SecurityUtil();
			pw2 = securityUtil.encryptSHA256(request.getParameter("password"));
			System.out.println(pw1);
			System.out.println(pw2);
			
			
			if(pw1.equals(pw2)){
				stmt = conn.prepareStatement(
						"UPDATE VISITOR_BOARD SET CONTENT=?, UPDATE_DATE=NOW() WHERE VNO="+request.getParameter("vno"));
				stmt.setString(1,  request.getParameter("content"));
				stmt.executeUpdate();
			}
			
			response.sendRedirect("visitorboard");
			
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
		}
	}
}
