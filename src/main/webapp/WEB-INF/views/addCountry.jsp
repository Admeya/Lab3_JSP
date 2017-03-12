<%--
  Created by IntelliJ IDEA.
  User: bot
  Date: 23.02.17
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">Добавление страны</h2>
<a href="/tour/logout">Выход</a><br>
<input type="button" value="Назад" onclick="history.back()">
<div align="center">
    <form action="/tour/addCountry" method="post">
        <table>
            <tr>
                <td><label for="nameCountry">Название страны:</label></td>
                <td><input type="text" name="nameCountry" id="nameCountry" value=""></td>
            </tr>
            <tr>
                <td><input type="submit" value="Добавить" formmethod="post"></td>
                <td><input type="button" value="Назад" onclick="history.back()"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>