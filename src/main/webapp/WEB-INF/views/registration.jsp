<%--
  Created by IntelliJ IDEA.
  User: bot
  Date: 23.02.17
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">Заполните данные анкеты, пожалуйста.</h2>
<div align="center">
    <spring:form action="/tour/registration" method="post" modelAttribute="client">
        <table>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login" value=""></td>
            </tr>
            <tr>
                <td><label for="pass">Password:</label></td>
                <td><input type="text" name="pass" id="pass" value=""></td>
            </tr>
            <tr>
                <td><label for="surname">Фамилия:</label></td>
                <td><input type="text" name="surname" id="surname" value=""></td>
            </tr>
            <tr>
                <td><label for="name">Имя:</label></td>
                <td><input type="text" name="name" id="name" value=""></td>
            </tr>
            <tr>
                <td><label for="middlename">Отчество:</label></td>
                <td><input type="text" name="middlename" id="middlename" value=""></td>
            </tr>
            <tr>
                <td><label for="birthdate">Дата рождения:</label></td>
                <td><input type="text" name="birthdate" id="birthdate" value="" placeholder="yyyy-mm-dd"></td>
            </tr>
            <tr>
                <td><label for="passportSerNum">Серия и номер паспорта:</label></td>
                <td><input type="text" name="passportSerNum" id="passportSerNum" value="" placeholder="9999 999999">
                </td>
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
    </spring:form>
</div>
</body>
</html>