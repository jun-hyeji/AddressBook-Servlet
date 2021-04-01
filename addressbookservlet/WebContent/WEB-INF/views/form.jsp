<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddressBook Servlet</title>
</head>
<body>
	<h1>주소록</h1>
	<h3>새 주소 등록</h3>

	<form action="<c:url value='/addressbook'/>" method="post">
		<input type="hidden" name="action" value="insert"/>
		<p>이름</p> 
		<p><input type="text" name="name" id="name"></p>
		
		<p>휴대전화</p>
		<p><input type="text" name="hp" id="hp"></p>
		
		<p>집전화</p>
		<p><input type="text" name="tel" id="tel"></p>
		<input type="submit" value="주소 등록">
	</form>
</body>
</html>