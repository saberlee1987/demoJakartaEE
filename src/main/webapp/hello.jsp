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
<br>
<a href="${pageContext.request.contextPath}/person">person page</a>
</body>
</html>
