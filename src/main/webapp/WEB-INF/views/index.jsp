<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
<label for="table">Получаемые данные из indexControl</label>
<table class="table" id="table">
    <thead>
    <tr>
        <th>Имя</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="string" items="<%=stringList%>" >
            <tr><td><c:out value="${string}"/> </td></tr>
        </c:forEach>
        <tr><td><c:out value="${user}"/> </td></tr>
        <c:forEach var="string" items="${listWithUsers}" >
            <tr><td><c:out value="${string}"/> </td></tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>