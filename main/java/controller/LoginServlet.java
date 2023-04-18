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


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee/login.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 입력한 한글이 깨지지 않도록 하기 위함
		String url = "employee/login.jsp";
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String lev = request.getParameter("lev");
		
		
		EmployeesDAO edao = EmployeesDAO.getInstance();
		int result = edao.userCheck(id, pass, lev);
		
		if (result == 2 || result == 3) {   // 로그인에 성공하였을 때
			EmployeesVO evo  = new EmployeesVO();
			evo = edao.getMember(id);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", evo); // 로그인 정보를 세션에 저장
			session.setAttribute("result", result); // 레벨 정보를 세션에 저장
			url = "main.jsp"; // 로그인에 성공할 경우 메인페이지로 이동하기 위함
		} else {
			if(result == 1){ 
				request.setAttribute("message", "레벨이 일치하지 않습니다.");
			} else if(result == 0){			
				request.setAttribute("message", "비밀번호가 일치하지 않습니다.");
			} else{			
				request.setAttribute("message", "아이디가 일치하지 않습니다.");
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}