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

@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MypageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmployeesVO evo = (EmployeesVO) session.getAttribute("loginUser");	
		if(evo != null){
			String url="employee/mypage.jsp";
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("login.do");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("name"));
		HttpSession session = request.getSession(); // 수정된 정보를 세션에 저장
		EmployeesVO evo = new EmployeesVO();
		evo.setId(request.getParameter("id"));
		evo.setPass(request.getParameter("pass"));
		evo.setName(request.getParameter("name"));
		evo.setLev(request.getParameter("lev"));

		if (request.getParameter("gender") != null)
			evo.setGender(Integer.parseInt(request.getParameter("gender").trim()));
		evo.setPhone(request.getParameter("phone"));

		EmployeesDAO eDao = EmployeesDAO.getInstance();
		eDao.updateMember(evo); // DAO에게 evo 객체를 DB에 수정하라고 명령

		EmployeesVO newevo = eDao.getMember(evo.getId()); // 수정된 정보를 가져온다.

		request.setAttribute("evo", newevo); // 수정된 정보 evo
		request.setAttribute("message", "회원 정보가 수정되었습니다.");

		session.setAttribute("loginUser", newevo);
		// 이해 못 함.
		System.out.println(newevo);

		int result = eDao.userCheck(evo.getId(), evo.getPass(), evo.getLev());
		session.setAttribute("result", result);

		String url = "employee/joincomplete.jsp"; // 회원 정보가 수정되었다고 알려주는 페이지
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
