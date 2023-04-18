<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 관리</title>
</head>
<body>
	<form>
		<table align="center" width="550">
			<tr>
				<td colspan="2" align="center">
					<h3>사원 정보</h3>
					<div style="color: red;">${message}</div>
				</td>
			</tr>
			<tr>
				<td align="center">아이디</td>
				<td>${evo.id}</td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td>${evo.pass}</td>
			</tr>
			<tr>
				<td align="center">이름</td>
				<td>${evo.name}</td>
			</tr>

			<tr>
				<td align="center">권한</td>
				<td><c:choose>
						<c:when test='${evo.lev=="A"}'>운영자</c:when>
						<c:otherwise>일반회원</c:otherwise>
					</c:choose>
			</tr>
			<tr>
				<td align="center">성별</td>
				<td><c:choose>
						<c:when test='${evo.gender=="1"}'>남자</c:when>
						<c:otherwise>여자</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td align="center">전화번호</td>
				<td colspan="3">${evo.phone}</td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="button" value="메인 페이지로 이동"
					onclick="location.href='main.do'"></td>
			</tr>
		</table>
	</form>
</body>
</html>