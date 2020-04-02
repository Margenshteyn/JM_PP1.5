<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Start page</title>
</head>
<body>
<div align="center">
    <h1>Insert your data</h1> <br><br>
    <form action="/index" method="post" id="signIn">
        <table border="2" cellpadding="3">
            <tr>
                <td>Login</td>
                <td>Password</td>
            </tr>
            <tr>
                <td><input type="text" name="login" form="signIn"></td>
                <td><input type="text" name="password" form="signIn"></td>
            </tr>
        </table>

        <button type="submit" name="login" value="${user.login}">Sign in</button>
        <br><br>
        <c:if test="${param.wrongSignIn != null}">
            <h2>User with login: "<c:out value="${param.wrongSignIn}"/>" does not exist! </h2>
            <h2>Try again</h2>
        </c:if>
        <c:if test="${param.wrongPassword != null}">
            <h2>Wrong password! </h2>
            <h2>Try again</h2>
        </c:if>
        <br><br>
        <p:dialog header="Insert data" widgetVar="dlg2" modal="true" height="100">
            <h:outputText value="This is a Modal Dialog." />
        </p:dialog>
        <br><br>
    </form>
    <h2>If you here in the first time, please

        <button onclick="location.href='/user/userAdd'"> Sign up </button>
    </h2>
</div>
</body>
</html>
