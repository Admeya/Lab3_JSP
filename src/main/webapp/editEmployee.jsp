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
<h2 align="center">Добавление нового сотрудника</h2>
<div align="center">
    <form action="/tour/editEmpLK" method="post">
        <input type="hidden" name="idEmployee" id="idEmployee" value="${Employee.idEmployee}">
        <table>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login" value="${Employee.login}"></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="text" name="password" id="password" value="${Employee.password}"></td>
            </tr>
            <tr>
                <td><label for="email">E-mail:</label></td>
                <td><input type="text" name="email" id="email" value="${Employee.email}"></td>
            </tr>
            <tr>
                <td><label for="role">Роль</label></td>
                <td><input type="text" name="role" id="role" value="${Employee.role}"></td>
            </tr>
            <tr>
                <td><label for="emplSurname">Фамилия:</label></td>
                <td><input type="text" name="emplSurname" id="emplSurname" value="${Employee.surname}"></td>
            </tr>
            <tr>
                <td><label for="emplName">Имя:</label></td>
                <td><input type="text" name="emplName" id="emplName" value="${Employee.name}"></td>
            </tr>
            <tr>
                <td><label for="phone">Телефон:</label></td>
                <td><input type="text" name="phone" id="phone" value="${Employee.phone}"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Изменить" formmethod="post"></td>
                <td><input type="button" value="Назад" onclick="history.back()"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>