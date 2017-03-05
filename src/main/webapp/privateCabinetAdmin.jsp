<%@ page import="ru.lab3.Entities.EmployeeEntity" %><%--
  Created by IntelliJ IDEA.
  User: Ирина
  Date: 22.02.2017
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stop. Admin cabinet!</title>

</head>
<body>
<h2 align="center">Личный кабинет сурового админа</h2>
Добро пожаловать, ${Client.name}, <a href="/tour/logout">Выход</a>
<br>
<br>
<h2 align="center">Пользователи системы</h2>
<div align="center">
    <table border="1">
        <tr>
            <th>id_Employee</th>
            <th>Employee name</th>
            <th>Employee Surname</th>
            <th>Employee phone</th>
            <th>login</th>
            <th>password</th>
            <th>e-mail</th>
            <th>role</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${Employees}" var="empl">
            <tr>
                <td><c:out value="${empl.idEmployee}"></c:out></td>
                <td><c:out value="${empl.name}"></c:out></td>
                <td><c:out value="${empl.surname}"></c:out></td>
                <td><c:out value="${empl.phone}"></c:out></td>
                <td><c:out value="${empl.login}"></c:out></td>
                <td><c:out value="${empl.password}"></c:out></td>
                <td><c:out value="${empl.email}"></c:out></td>
                <td><c:out value="${empl.role}"></c:out></td>
                <td><a href="/tour/editEmpLK?idEmpl=${empl.idEmployee}">Редактировать </a><a
                        href="/tour/lkAdmin?idEmpl=${empl.idEmployee}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="/tour/addEmployee.jsp" method="post">
        <input type="submit" value="Добавить" formmethod="post">
    </form>
</div>
</body>
</html>
