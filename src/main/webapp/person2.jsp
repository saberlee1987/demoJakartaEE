<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>person view page</title>
</head>
<body>
<%--@elvariable id="person" type="com.saber.demojavaee.models.Person"--%>
<jsp:useBean id="person2" class="com.saber.demojavaee.models.Person" scope="request"/>
<label for="firstName">firstName : </label><input type="text" id="firstName"
                                                  value="<jsp:getProperty name="person2" property="firstName"/>"
                                                  readonly><br>
<label for="lastName">lastName : </label><input type="text" id="lastName
                                            value="<jsp:getProperty name="person2" property="lastName"/>" readonly/><br>
<label for="age">age : </label><input type="text" id="age"
                                      value="<jsp:getProperty name="person2" property="age"/>" readonly><br>
<label for="nationalCode">nationalCode :</label> <input type="text" id="nationalCode"
                                                        value="<jsp:getProperty name="person2" property="nationalCode"/>"
                                                        readonly><br>
<label for="email">email :</label> <input type="text" id="email"
                                          value="<jsp:getProperty name="person2" property="email"/>"
                                          readonly><br>
<label for="mobile">mobile :</label> <input type="text" id="mobile"
                                            value="<jsp:getProperty name="person2" property="mobile"/>"
                                            readonly><br>
<label for="createdDate">createdDate : </label><input type="text" id="createdDate"
                                                      value="<jsp:getProperty name="person2" property="createdAtPersian"/>"
                                                      readonly><br>
<label for="updateDate">updateDate : </label><input type="text" id="updateDate"
                                                    value="<jsp:getProperty name="person2" property="updatedAtPersian"/>"
                                                    readonly><br><br>
<hr>
<c:choose>
    <c:when test="${person!=null}">
        <label for="firstName">firstName : </label><input type="text" id="firstName" value="${person.firstName}"
                                                          readonly><br>
        <label for="lastName">lastName : </label><input type="text" id="lastName" value="${person.lastName}"
                                                        readonly><br>
        <label for="age">age : </label><input type="text" id="age" value="${person.age}" readonly><br>
        <label for="nationalCode">nationalCode :</label> <input type="text" id="nationalCode"
                                                                value="${person.nationalCode}" readonly><br>
        <label for="email">email :</label> <input type="text" id="email" value="${person.email}" readonly><br>
        <label for="mobile">mobile :</label> <input type="text" id="mobile" value="${person.mobile}" readonly><br>
        <label for="createdDate">createdDate : </label><input type="text" id="createdDate" value="${person.createdAtPersian}"
                                                              readonly><br>
        <label for="updateDate">updateDate : </label><input type="text" id="updateDate" value="${person.updatedAtPersian}"
                                                            readonly><br>
    </c:when>
    <c:otherwise>
        <h3>sorry person does not exist</h3>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/person">person page</a>
</body>
</html>
