<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">Добавление тура</h2>
<a href="/tour/logout">Выход</a><br>
<input type="button" value="Назад" onclick="history.back()">
<div align="center">
    <form action="/tour/addTour" method="post">
        <table>
            <tr>
                <td><label for="name">name</label></td>
                <td><input type="text" name="name" id="name" value=""></td>
            </tr>
            <tr>
                <td><label for="dateStart">Дата начала:</label></td>
                <td><input type="text" name="dateStart" id="dateStart" value=""></td>
            </tr>
            <tr>
                <td><label for="dateEnd">Дата окончания:</label></td>
                <td><input type="text" name="dateEnd" id="dateEnd" value=""></td>
            </tr>
            <tr>
                <td><label for="cost">Стоимость:</label></td>
                <td><input type="text" name="cost" id="cost" value=""></td>
            </tr>
            <tr>
                <td><label for="idDestination">id_destination:</label></td>
                <td><input type="text" name="idDestination" id="idDestination" value=""></td>
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