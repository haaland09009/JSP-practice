<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 관리</title>
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
<form action="custom.do" method="post" name="frm">
 <table align="center">
  <tr>
   <td colspan="2">사원 등록</td>
  </tr>
  <tr>
   <td>아이디</td>
   <td><input type="text" name="id"></td>
  </tr>
  <tr>
   <td>비밀번호</td>
   <td><input type="password" name="pass"></td>
  </tr>
   <tr>
   <td>이름</td>
   <td><input type="text" name="name"></td>
  </tr>
   <tr>
   <td align="center">권한</td>
   <td>
   <select name="lev">
    <option value="A">운영자</option>
    <option value="B">일반회원</option>
   </select>
   </td>
  </tr>
  <tr>
   <td align="center">성별</td>
   <td>
   <select name="gender">
    <option value="1">남자</option>
    <option value="2">여자</option>
   </select>
   </td>
  </tr>
  <tr>
   <td>전화번호</td>
   <td colspan="3"><input type="text" name="phone"></td>
  </tr>
  <tr>
   <td colspan="2">
    <input type="submit" value="등록" onclick="return joinCheck()">
    <input type="reset" value="취소">
    <input type="button" value="메인 페이지로 이동" onclick="location.href='main.jsp'">
 
   </td>
  </tr>
</table>
</form>


</body>
</html>