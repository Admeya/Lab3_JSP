<%--
  Created by IntelliJ IDEA.
  User: bot
  Date: 23.02.17
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">Заполните данные анкеты, пожалуйста.</h2>
<div align="center">
    <form action="/tour/registration" method="post">
        <table>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login" value=""></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="text" name="password" id="password" value=""></td>
            </tr>
            <tr>
                <td><label for="clientSurname">Фамилия:</label></td>
                <td><input type="text" name="clientSurname" id="clientSurname" value=""></td>
            </tr>
            <tr>
                <td><label for="clientName">Имя:</label></td>
                <td><input type="text" name="clientName" id="clientName" value=""></td>
            </tr>
            <tr>
                <td><label for="clientMiddleName">Отчество:</label></td>
                <td><input type="text" name="clientMiddleName" id="clientMiddleName" value=""></td>
            </tr>
            <tr>
                <td><label for="birthdate">Дата рождения:</label></td>
                <td><input type="text" name="birthdate" id="birthdate" value="" placeholder="yyyy-mm-dd"></td>
            </tr>
            <tr>
                <td><label for="passport">Серия и номер паспорта:</label></td>
                <td><input type="text" name="passport" id="passport" value="" placeholder="9999 999999"></td>
            </tr>
            <tr>
                <td><label for="phone">Телефон:</label></td>
                <td><input type="text" name="phone" id="phone" value="" placeholder="+7-999-999-99-99"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Зарегистрироваться" formmethod="post"></td>
                <td><input type="button" value="Назад" onclick="history.back()"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>