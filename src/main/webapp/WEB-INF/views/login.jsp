<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<h2 align="center">Страничка входа для клиентов</h2>

<div align="left">
    <a href="/tour/index">На главную</a>
</div>
<div align="right">
    <a href="/tour/registration">Регистрация</a>

    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <table>
            <tr>
                <td><label for="j_username">Login:</label></td>
                <td><input type="text" name="j_username" id="j_username"></td>
            </tr>
            <tr>
                <td><label for="j_password">Password:</label></td>
                <td><input type="password" name="j_password" id="j_password"></td>
            </tr>
        </table>
        <button type="submit">Войти</button>
    </form>
</div>


</body>
</html>