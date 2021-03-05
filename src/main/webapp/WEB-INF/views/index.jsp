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
<div style="width: 60%; padding-left: 30px; padding-top: 30px">
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
                <tr>
                <td><c:out value="${accident.getId()}"/></td>
                <td><c:out value="${accident.getName()}"/></td>
                <td><c:out value="${accident.getText()}"/></td>
                <td><c:out value="${accident.getAddress()}"/></td>
                <td>
                    <span>
                        <a href="<c:url value='/update?id=${accident.id}'/>">Редактировать инцидент</a>
                    </span>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
    <form>
        <input type="button" class="btn btn-primary" value="Добавить инцидент" onClick='location.href="<c:url value='/create'/>"'>
    </form>
</div>
</body>
</html>