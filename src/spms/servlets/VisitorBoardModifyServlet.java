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
import spms.dao.VisitorBoardModifyDao;
import spms.vo.Board;

@WebServlet("/modify")
public class VisitorBoardModifyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		
		Connection conn = null;
		//Statement stmt = null;
		//ResultSet rs = null;
		try{
			
			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			VisitorBoardModifyDao visitorBoardModifyDao = new VisitorBoardModifyDao();
			visitorBoardModifyDao.setConnection(conn);
			
			request.setAttribute("board", visitorBoardModifyDao.getBoard(request.getParameter("vno")));
			
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/board/VisitorBoardModify.jsp");
			rd.include(request, response);
			

		}catch(Exception e){
			throw new ServletException(e);
		}
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement stmt = null;
		String pw1, pw2;
		try{
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			VisitorBoardModifyDao visitorBoardModifyDao = new VisitorBoardModifyDao();
			visitorBoardModifyDao.setConnection(conn);
			
			pw1 = visitorBoardModifyDao.getPW(request.getParameter("vno"));
			
			SecurityUtil securityUtil = new SecurityUtil();
			pw2 = securityUtil.encryptSHA256(request.getParameter("password"));
			System.out.println(pw1);
			System.out.println(pw2);
			
			
			if(pw1.equals(pw2)){
				visitorBoardModifyDao.updateBoard(request.getParameter("vno"),request.getParameter("content"));
			}
			
			response.sendRedirect("visitorboard");
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
}
