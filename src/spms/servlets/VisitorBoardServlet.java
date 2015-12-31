package spms.servlets;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import spms.dao.VisitorBoardDao;

@WebServlet("/visitorboard")
public class VisitorBoardServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
