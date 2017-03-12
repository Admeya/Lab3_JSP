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
<h2 align="center">Просмотр справочника стран</h2>
<a href="/tour/logout">Выход</a><br>
<input type="button" value="Назад" onclick="history.back()">
<div align="center">
    <table border="1">
        <tr>
            <th>id_country</th>
            <th>name_country</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${countries}" var="cou">
            <tr>
                <td><c:out value="${cou.idCountry}"></c:out></td>
                <td><c:out value="${cou.nameCountry}"></c:out></td>
                <td><a href="/tour/editCountry?idCountry=${cou.idCountry}">Редактировать </a><a
                        href="/tour/viewCountry?idCountry=${cou.idCountry}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="/tour/addCountry" method="get">
        <input type="submit" value="Добавить" formmethod="get">
    </form>
</div>
</body>
</html>