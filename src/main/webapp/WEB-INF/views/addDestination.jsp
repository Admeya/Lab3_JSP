<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">Добавление локации</h2>
<a href="/tour/logout">Выход</a><br>
<input type="button" value="Назад" onclick="history.back()">
<div align="center">
    <form action="/tour/addDest" method="post">
        <table>
            <tr>
                <td><label for="idCountry">id_country</label></td>
                <td><input type="text" name="idCountry" id="idCountry" value=""></td>
            </tr>
            <tr>
                <td><label for="resort">Курорт:</label></td>
                <td><input type="text" name="resort" id="resort" value="" maxlength="50"></td>
            </tr>
            <tr>
                <td><label for="hotel">Отель:</label></td>
                <td><input type="text" name="hotel" id="hotel" value="" maxlength="50"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Зарегистрировать" formmethod="post"></td>
                <td><input type="button" value="Назад" onclick="history.back()"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>