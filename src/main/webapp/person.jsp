<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>person view page</title>
</head>
<body>
<%--@elvariable id="person" type="com.saber.demojavaee.models.Person"--%>
<c:choose>
    <c:when test="${person!=null}">
        <h4>person firstName : ${person.firstName}</h4>
        <label for="firstName">firstName : </label><input type="text" id="firstName" value="${person.firstName}" readonly><br>
        <label for="lastName">lastName : </label><input type="text" id="lastName" value="${person.lastName}" readonly><br>
        <label for="age">age : </label><input type="text" id="age" value="${person.age}" readonly><br>
        <label for="nationalCode">nationalCode :</label> <input type="text" id="nationalCode"
                                                                value="${person.nationalCode}" readonly><br>
        <label for="email">email :</label> <input type="text" id="email" value="${person.email}" readonly><br>
        <label for="createdDate">createdDate : </label><input type="text" id="createdDate"
                                                              value="${person.createdAt}" readonly><br>
        <label for="updateDate">updateDate : </label><input type="text" id="updateDate"
                                                            value="${person.updatedAt}" readonly><br>
    </c:when>
    <c:otherwise>
        <h3>sorry person does not exist</h3>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/person">person page</a>
</body>
</html>
