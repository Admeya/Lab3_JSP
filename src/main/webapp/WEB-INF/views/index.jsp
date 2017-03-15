<%--
  Created by IntelliJ IDEA.
  User: Ирина
  Date: 24.02.2017
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Туры</title>
</head>
<body>
<c:if test="${empty Client}">
    <div align="right">
        <a href="/tour/login">Вход</a>&nbsp;&nbsp;&nbsp;
        <a href="/tour/registration">Регистрация</a>&nbsp;&nbsp;&nbsp;
    </div>
</c:if>
<c:if test="${not empty Client}">
    <div align="left">
        <a href="/tour/lkClient">Личный кабинет</a><br>
            ${Client.name}, <a href="/tour/logout">Выход</a>
    </div>
</c:if>

<h2 align="center"> У нас самые лучшие туры</h2>

<div align="center">
    <table border="1">
        <tr>
            <th>Tour id</th>
            <th> Tour name</th>
            <th> Country id</th>
            <th> Country name</th>
            <th> Destination id</th>
            <th> Destination resort</th>
            <th> Destination hotel</th>
            <th> Tour date_start</th>
            <th> Tour date_end</th>
            <th> Tour cost</th>
            <th> Actions</th>
        </tr>
        <c:forEach items="${Tours}" var="tour">
            <tr>
                <td><c:out value="${tour.idTour}"></c:out></td>
                <td><c:out value="${tour.name}"></c:out></td>

                <td><c:out value="${tour.idCountry}"></c:out></td>
                <td><c:out value="${tour.nameCountry}"></c:out></td>
                <td><c:out value="${tour.idDestination}"></c:out></td>
                <td><c:out value="${tour.resort}"></c:out></td>
                <td><c:out value="${tour.hotel}"></c:out></td>

                <td><c:out value="${tour.dateStart}"></c:out></td>
                <td><c:out value="${tour.dateEnd}"></c:out></td>
                <td><c:out value="${tour.cost}"></c:out></td>
                <td><a href="/tour/setOrder?idTour=${tour.idTour}&idClient=${Client.idClient}">Сделать заказ</a>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
