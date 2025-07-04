<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Page</title>
</head>
<body>
<h3>Hello page</h3>
<%--@elvariable id="colors" type="java.util.List<java.lang.String>"--%>
<%--@elvariable id="persons" type="java.util.List<com.saber.demojavaee.models.Person>"--%>
<c:if test="${colors!=null}">
    <table border="2">
        <tr>
            <th>Color</th>
        </tr>
        <c:forEach items="${colors}" var="color">
            <tr>
                <td>${color}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<hr>
<br>

<c:if test="${persons!=null && persons.size() > 0}" >
    <table border="2">
        <tr>
            <th>firstName</th>
            <th>lastName</th>
            <th>nationalCode</th>
            <th>age</th>
            <th>mobile</th>
            <th>email</th>
            <th>createdAt</th>
            <th>updatedAt</th>
        </tr>
        <c:forEach var="person" items="${persons}">
            <tr>
                <th>${person.firstName}</th>
                <th>${person.lastName}</th>
                <th>${person.nationalCode}</th>
                <th>${person.age}</th>
                <th>${person.mobile}</th>
                <th>${person.email}</th>
                <th>${person.createdAt}</th>
                <th>${person.updatedAt}</th>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
