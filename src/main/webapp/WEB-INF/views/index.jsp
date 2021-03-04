<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.accident.model.Accident" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
<%
    HttpSession sc = request.getSession();
    String a = (String) sc.getAttribute("user");
    String b = "Hello";
    List<String> stringList = new ArrayList<>();
    stringList.add("Vika");
    stringList.add("Masha");
    stringList.add("Natasha");
%>
<%--Hello : Accident--%>
<label for="tableFirst">Получаемые данные из indexControl</label>
<table class="table" id="tableFirst">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Text</th>
        <th>Adress</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="accident" items="${accidents}" >
        <tr><td>${accident.value.getId()}</td>
        <td><c:out value="${accident.value.getName()}"/></td>
        <td><c:out value="${accident.value.getText()}"/></td>
        <td><c:out value="${accident.value.getAddress()}"/></td></tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>