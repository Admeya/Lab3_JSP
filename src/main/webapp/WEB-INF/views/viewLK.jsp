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
<h2 align="center">Просмотр личных данных</h2>
<a href="/tour/lkClient?idClient=${Client.idClient}">Вернуться в личный кабинет</a>
<a href="/tour/logout">Выход</a>
<div align="center">
    <form action="/tour/editLK" method="post">
        <input type="hidden" name="idClient" id="idClient" value="${Client.idClient}">
        <table>
            <tr>
                <td>Login:</td>
                <td>${Client.login}</td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>${Client.password}</td>
            </tr>
            <tr>
                <td>Фамилия:</td>
                <td>${Client.surname}</td>
            </tr>
            <tr>
                <td>Имя:</td>
                <td>${Client.name}</td>
            </tr>
            <tr>
                <td>Отчество:</td>
                <td>${Client.middlename}</td>
            </tr>
            <tr>
                <td>Дата рождения:</td>
                <td>${Client.birthdate}</td>
            </tr>
            <tr>
                <td>Серия и номер паспорта:</td>
                <td>${Client.passportSerNum}</td>
            </tr>
            <tr>
                <td>Телефон:</td>
                <td>${Client.phone}</td>
            </tr>
            <tr>
                <%--<td><input type="button" value="В личный кабинет" onclick=""></td>--%>
                <%--<td><input type="submit" value="Изменить" formmethod="post"></td>--%>
            </tr>
        </table>
    </form>
</div>
</body>
</html>