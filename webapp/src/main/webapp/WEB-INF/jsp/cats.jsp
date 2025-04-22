<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cats</title>
</head>
<body>
<h2>Cats</h2>
<c:forEach var="cat" items="${cats}">
    <p><c:out value="${cat}"/></p>
</c:forEach>
<h1>Add cat</h1>
<form action="cats" method="POST">
    <p>Enter cat name:</p>
    <input type="text" name="catName"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>