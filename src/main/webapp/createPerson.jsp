<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add person jsp</title>
</head>
<body>
<h3>fill information person</h3>
<%--@elvariable id="personRequest" type="com.saber.demojavaee.dto.PersonRequestDto"--%>
<%--@elvariable id="errors" type="java.util.List<java.lang.String>"--%>
<c:if test="${errors!=null && errors.size() > 0}">
    <ul>
        <c:forEach items="${errors}" var="error">
            <li style="color: red">${error}</li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${personRequest!=null}">
    <form action="${pageContext.request.contextPath}/person" method="post">
        <input type="hidden" name="action" value="savePerson">
        <label for="firstName">firstName : </label><input type="text" id="firstName" name="firstName"
                                                          value="${personRequest.firstName}"><br>
        <label for="lastName">lastName : </label><input type="text" id="lastName" name="lastName"
                                                        value="${personRequest.lastName}"><br>
        <label for="age">age : </label><input type="text" id="age" name="age" value="${personRequest.age}"><br>
        <label for="nationalCode">nationalCode :</label> <input type="text" id="nationalCode" name="nationalCode"
                                                                value="${personRequest.nationalCode}"><br>
        <label for="email">email :</label> <input type="text" id="email" name="email"
                                                  value="${personRequest.email}"><br>
        <label for="mobile">mobile :</label> <input type="text" id="mobile" name="mobile"
                                                  value="${personRequest.mobile}"><br>
        <input type="submit" value="Add person">
    </form>
</c:if>
</body>
</html>
