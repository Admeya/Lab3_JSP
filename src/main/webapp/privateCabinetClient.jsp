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
    <title>First app</title>
    <link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2 align="center">Личный кабинет</h2>
Добро пожаловать, ${Client.name}, <a href="/tour/logout">Выход</a>
<br>
<br>
<a href="/tour/editLK?idClient=${Client.idClient}">Редактировать персональную информацию</a><br>
<a href="/tour/viewLK?idClient=${Client.idClient}">Просмотреть персональную информацию</a>
</body>
</html>
