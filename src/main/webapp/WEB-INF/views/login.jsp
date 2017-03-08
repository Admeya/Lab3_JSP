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
    <form action="/tour/login" method="post">
        <table>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login" value=""></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" name="password" id="password" value=""></td>
            </tr>
        </table>
        <input type="submit" value="Войти" formmethod="post">
    </form>
</div>


</body>
</html>