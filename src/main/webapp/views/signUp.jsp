<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Form of registration</title>
</head>
<body>

<div align="center">
    <h1>Form of registration</h1>
    <form action="${pageContext.request.contextPath}/user/userAdd" method="post" id="form"></form>

    <table border="1" cellpadding="3">
        <tr>
            <td>Login</td>
            <td>Password</td>
            <td>Name</td>
            <td>Amount</td>
        </tr>

        <tr>
            <td><input type="text" name="login" form="form"></td>
            <td><input type="text" name="password" form="form"></td>
            <td><input type="text" name="name" form="form"></td>
            <td><input type="number" step="0.01" min="0" lang="en" name="amount" form="form"></td>

            <td>
                <button type="submit" class="btn-link" form="form">Registration</button>
            </td>
        </tr>
    </table>

    <c:if test="${param.addUserLogin != null}">
        <h2>User: "<c:out value="${param.addUserLogin}"/>" added </h2>
    </c:if>
    <c:if test="${param.wrongRequest != null}">
        <h2>User: "<c:out value="${param.wrongRequest}"/>" not added - login already exist! </h2>
    </c:if>
    <c:if test="${param.wrongLogin != null}">
        <h2>Login field couldn't be empty! </h2>
    </c:if>
    <br>
    <button onclick="location.href='/index'"> Home </button>
</div>
</body>
</html>
