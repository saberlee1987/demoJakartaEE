<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>person page</title>
</head>
<body>
<%--@elvariable id="persons" type="java.util.List<com.saber.demojavaee.models.Person>"--%>

<h3>person information data</h3>
<a href="${pageContext.request.contextPath}/person?action=savePerson">Add person</a><br>
<c:if test="${param.message!=null}">
    <h4 style="color: green">your person information added to db or changeIt</h4>
</c:if>
<c:if test="${persons!=null}">
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
                <th>View</th>
                <th>Update</th>
            </tr>
            <c:forEach var="person" items="${persons}">
                <tr>
                    <th>${person.firstName}</th>
                    <th>${person.lastName}</th>
                    <th>${person.nationalCode}</th>
                    <th>${person.age}</th>
                    <th>${person.mobile}</th>
                    <th>${person.email}</th>
                    <th>${person.createdAtPersian}</th>
                    <th>${person.updatedAtPersian}</th>
                    <th><a href="${pageContext.request.contextPath}/person?action=updatePerson&id=${person.id}">update</a></th>
                    <th><a href="${pageContext.request.contextPath}/person?action=view&id=${person.id}">view</a></th>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:if>
</body>
</html>
