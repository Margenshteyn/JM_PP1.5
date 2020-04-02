
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2> Welcome</h2
    <c:if test="${param.addUserLogin != null}">
        <h1>User: "<c:out value="${param.addUserLogin}"/>" added! </h1>
    </c:if>
    <c:if test="${param.userInRoom != null}">
        <h1>User:"<c:out value="${param.userInRoom}"/>", this is your room!</h1>
    </c:if>
</div>
</body>
</html>
