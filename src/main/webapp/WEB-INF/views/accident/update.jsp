<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Update accident</title>
    <html>
<body>
<div style="width: 60%; padding-left: 30px; padding-top: 30px">
    <form  action="<c:url value='/save'/>" method='POST'>
        <table>
            <tr>
                <td>ID:</td>
                <td><input type='text' style="margin-top: 10px" name='id' value="${accident.getId()}"></td>
            </tr>
            <tr>
                <td>Название:  </td>
                <td><input type='text' style="margin-top: 10px" name='name' value="${accident.getName()}"></td>
            </tr>
            <tr>
                <td>Описание:  </td>
                <td><input type='text' style="margin-top: 10px" name='text' value="${accident.getText()}"></td>
            </tr>
            <tr>
                <td>Адрес:  </td>
                <td><input type='text' style="margin-top: 10px" name='address' value="${accident.getAddress()}"></td>
            </tr>
            <tr style="padding-bottom: 50px">
                <td colspan='2'><input name="submit" class="btn btn-primary" style="margin-top: 10px"  type="submit" value="Сохранить" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>