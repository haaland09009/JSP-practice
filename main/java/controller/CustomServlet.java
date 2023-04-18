package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeesDAO;
import dto.EmployeesVO;

@WebServlet("/custom.do")
public class CustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmployeesVO evo=(EmployeesVO) session.getAttribute("loginUser");
		Integer result=(Integer) session.getAttribute("result");
		if(evo != null && result==2){
			String url = "employee/custom.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else{
			response.sendRedirect("login.do");
		}		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EmployeesVO evo = new EmployeesVO();
		evo.setId(request.getParameter("id"));
		evo.setPass(request.getParameter("pass"));
		evo.setName(request.getParameter("name"));
		evo.setLev(request.getParameter("lev"));		
		evo.setGender(Integer.parseInt(request.getParameter("gender")));
		evo.setPhone(request.getParameter("phone"));
		
		EmployeesDAO memberDAO = EmployeesDAO.getInstance();
		memberDAO.insertMember(evo);
		
		request.setAttribute("evo", evo);
		request.setAttribute("message", "회원 등록에 성공했습니다.");
		String url = "employee/joincomplete.jsp";		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
