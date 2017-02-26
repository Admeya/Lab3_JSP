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
<h2 align="center">Редактирование личных данных</h2>
<div align="center">
    <form action="/tour/editLK" method="post">
        <input type="hidden" name="idClient" id="idClient" value="${Client.idClient}">
        <table>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login" value="${Client.login}"></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="text" name="password" id="password" value="${Client.pass}"></td>
            </tr>
            <tr>
                <td><label for="clientSurname">Фамилия:</label></td>
                <td><input type="text" name="clientSurname" id="clientSurname" value="${Client.surname}"></td>
            </tr>
            <tr>
                <td><label for="clientName">Имя:</label></td>
                <td><input type="text" name="clientName" id="clientName" value="${Client.name}"></td>
            </tr>
            <tr>
                <td><label for="clientMiddleName">Отчество:</label></td>
                <td><input type="text" name="clientMiddleName" id="clientMiddleName" value="${Client.middlename}"></td>
            </tr>
            <tr>
                <td><label for="birthdate">Дата рождения:</label></td>
                <td><input type="text" name="birthdate" id="birthdate" value="${Client.birthdate}"></td>
            </tr>
            <tr>
                <td><label for="passport">Серия и номер паспорта:</label></td>
                <td><input type="text" name="passport" id="passport" value="${Client.passportSerNum}"></td>
            </tr>
            <tr>
                <td><label for="phone">Телефон:</label></td>
                <td><input type="text" name="phone" id="phone" value="${Client.phone}"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Изменить" formmethod="post"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>