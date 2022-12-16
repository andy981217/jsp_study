package ch08.practice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistController
 */
@WebServlet("/rcontrol")
public class RegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RegistService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		service = new RegistService();
	}
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String view ="";
		
		if(action == null) {
			getServletContext().getRequestDispatcher("/rcontrol?action=list").forward(request, response);

		}else { 
			switch(action) {
			case "info":view=info(request,response); break;
			case "list":view=list(request,response); break;
			}
			getServletContext().getRequestDispatcher("/ch08.practice/"+view).forward(request, response);
		}	
	}
	private String info(HttpServletRequest request, HttpServletResponse response) {
		Regist r = service.find(request.getParameter("id"));
		request.setAttribute("r", r);
		return "registInfo.jsp";
	}
	private String list(HttpServletRequest request, HttpServletResponse response) {
		List<Regist>rlist = service.findAll();
		request.setAttribute("regists", rlist);
		return "registList.jsp";
	}
}
