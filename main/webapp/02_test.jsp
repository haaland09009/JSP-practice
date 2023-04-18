<%@page import="java.sql.Connection"%>
<%@page import="dao.EmployeesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	EmployeesDAO empDao = EmployeesDAO.getInstance();
	Connection conn = empDao.getConnection();
	out.println("DBCP 연동에 성공하였습니다!");
%>	

</body>
</html>