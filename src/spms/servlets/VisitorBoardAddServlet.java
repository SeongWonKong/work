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
import spms.dao.VisitorBoardDao;

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
		
		try{
			EmailValidator ev = new EmailValidator(); //email ��ȿ�� üũ(����)
			if(ev.validate(request.getParameter("email"))){
				ServletContext sc = this.getServletContext();
				SecurityUtil securityUtil = new SecurityUtil();
				String pswd = securityUtil.encryptSHA256(request.getParameter("password"));//��й�ȣ ��ȣȭ
	
				VisitorBoardDao visitorBoardDao = (VisitorBoardDao)sc.getAttribute("visitorBoardDao");
				
				visitorBoardDao.insertDB(request.getParameter("email"),pswd,request.getParameter("content"));
			}else{
				System.out.println("wrong email");
			}
			
			response.sendRedirect("visitorboard");
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
}
