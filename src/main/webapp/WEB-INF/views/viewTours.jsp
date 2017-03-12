<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">Просмотр справочника туров</h2>
<a href="/tour/logout">Выход</a><br>
<input type="button" value="Назад" onclick="history.back()">
<div align="center">
    <table border="1">
        <tr>
            <th>id_tour</th>
            <th>name</th>
            <th>date_start</th>
            <th>date_end</th>
            <th>cost</th>
            <th>id_destination</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${tours}" var="tour">
            <tr>
                <td><c:out value="${tour.idTour}"></c:out></td>
                <td><c:out value="${tour.name}"></c:out></td>
                <td><c:out value="${tour.dateStart}"></c:out></td>
                <td><c:out value="${tour.dateEnd}"></c:out></td>
                <td><c:out value="${tour.cost}"></c:out></td>
                <td><c:out value="${tour.idDestination}"></c:out></td>
                <td><a href="/tour/editTour?idTour=${tour.idTour}">Редактировать </a><a
                        href="/tour/viewTour?idTour=${tour.idTour}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="/tour/addTour" method="get">
        <input type="submit" value="Добавить" formmethod="get">
    </form>
</div>
</body>
</html>