<%--
  Created by IntelliJ IDEA.
  User: Ирина
  Date: 22.02.2017
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет сотрудника</title>
</head>
<body>
<h2 align="center">Личный кабинет</h2>
Добро пожаловать, ${Employee.name}, <a href="/tour/logout">Выход</a>
<br>
<br>
<a href="/tour/editOrder?idEmpl=${Employee.idEmployee}">Заказы</a><br>
<a href="/tour/viewCountry">Просмотр справочника стран</a><br>
<a href="/tour/viewDest">Просмотр справочника локаций</a><br>
<a href="/tour/viewTour">Просмотр справочника туров</a><br>

</body>
</html>
