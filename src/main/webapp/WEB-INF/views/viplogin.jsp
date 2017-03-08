<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2 align="center">Вы зашли на страничку для входа сотрудников!</h2>
<div align="right">
    <form action="/tour/viplogin" method="post">
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