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

import spms.dao.VisitorBoardDao;
import spms.vo.Board;



@WebServlet("/visitorboard")
public class VisitorBoardServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			ServletContext sc = this.getServletContext();	//		
			VisitorBoardDao vbd = (VisitorBoardDao)sc.getAttribute("visitorBoardDao");
			request.setAttribute("boards", vbd.selectList());
			
			response.setContentType("text/html; charset=UTF-8");//
			RequestDispatcher rd = request.getRequestDispatcher("/board/VisitorBoard.jsp");//ºä
			rd.include(request, response);//

		}
		catch(Exception e){
			throw new ServletException(e);
		}
	}

}
