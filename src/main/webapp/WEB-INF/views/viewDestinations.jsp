<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">Просмотр справочника локаций</h2>
<a href="/tour/logout">Выход</a><br>
<input type="button" value="Назад" onclick="history.back()">
<div align="center">
    <table border="1">
        <tr>
            <th>id_destination</th>
            <th>id_country</th>
            <th>name_country</th>
            <th>resort</th>
            <th>hotel</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${destinations}" var="dest">
            <tr>
                <td><c:out value="${dest.idDestination}"></c:out></td>
                <td><c:out value="${dest.idCountry}"></c:out></td>
                <td><c:out value="${dest.nameCountry}"></c:out></td>
                <td><c:out value="${dest.resort}"></c:out></td>
                <td><c:out value="${dest.hotel}"></c:out></td>
                <td><a href="/tour/editDest?idDest=${dest.idDestination}">Редактировать </a><a
                        href="/tour/viewDest?idDest=${dest.idDestination}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="/tour/addCountry" method="get">
        <input type="submit" value="Добавить" formmethod="get">
    </form>
</div>
</body>
</html>