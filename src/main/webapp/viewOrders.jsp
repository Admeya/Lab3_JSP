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
<h2 align="center">Операции с заказами</h2>
<a href="/tour/editOrder">Выход</a><br>
<input type="button" value="Назад" onclick="history.back()">
<div align="center">
    Новые заказы
    <table border="1">
        <tr>
            <th>id_order</th>
            <th>id_employee</th>
            <th>id_client</th>
            <th>Имя клиента</th>
            <th>Фамилия клиента</th>
            <th>Телефон клиента</th>
            <th>id_tour</th>
            <th>Тур</th>
            <th>Дата заказа</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${newOrders}" var="newOrd">
            <tr>
                <td><c:out value="${newOrd.idOrder}"></c:out></td>
                <td><c:out value="${newOrd.idEmployee}"></c:out></td>
                <td><c:out value="${newOrd.idClient}"></c:out></td>
                <td><c:out value="${newOrd.nameClient}"></c:out></td>
                <td><c:out value="${newOrd.surnameClient}"></c:out></td>
                <td><c:out value="${newOrd.phoneClient}"></c:out></td>
                <td><c:out value="${newOrd.idTour}"></c:out></td>
                <td><c:out value="${newOrd.nameTour}"></c:out></td>
                <td><c:out value="${newOrd.checkoutDate}"></c:out></td>
                <td><a href="/tour/editEmpLK?idEmpl=${empl.idEmployee}">Редактировать </a><a
                        href="/tour/lkAdmin?idEmpl=${empl.idEmployee}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<br><br><br>
<div align="center">
    Заказы в работе
    <table border="1">
        <tr>
            <th>id_order</th>
            <th>id_employee</th>
            <th>id_client</th>
            <th>Имя клиента</th>
            <th>Фамилия клиента</th>
            <th>Телефон клиента</th>
            <th>id_tour</th>
            <th>Тур</th>
            <th>Дата заказа</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${myOrders}" var="my">
            <tr>
                <td><c:out value="${my.idOrder}"></c:out></td>
                <td><c:out value="${my.idEmployee}"></c:out></td>
                <td><c:out value="${my.idClient}"></c:out></td>
                <td><c:out value="${my.nameClient}"></c:out></td>
                <td><c:out value="${my.surnameClient}"></c:out></td>
                <td><c:out value="${my.phoneClient}"></c:out></td>
                <td><c:out value="${my.idTour}"></c:out></td>
                <td><c:out value="${my.nameTour}"></c:out></td>
                <td><c:out value="${my.checkoutDate}"></c:out></td>
                <td><a href="/tour/editEmpLK?idEmpl=${empl.idEmployee}">Редактировать </a><a
                        href="/tour/lkAdmin?idEmpl=${empl.idEmployee}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>