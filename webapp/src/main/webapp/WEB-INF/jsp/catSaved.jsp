<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cat saved</title>
</head>
<body>
<c:if test="${catName != null}">
    <p>Cat <c:out value="${catName}"/> is added</p>
</c:if>
<c:if test="${catName == null}">
    <p>Cat is not added. Name was not entered</p>
</c:if>
</body>
</html>
