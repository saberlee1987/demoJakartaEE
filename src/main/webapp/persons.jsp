<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Person Management</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/application.css">

</head>

<body>
<%--@elvariable id="persons" type="java.util.List<com.saber.demojavaee.models.Person>"--%>
<%--@elvariable id="totalElements" type="java.lang.Long"--%>
<%--@elvariable id="pageSize" type="java.lang.Long"--%>
<%--@elvariable id="from" type="java.lang.Integer"--%>
<%--@elvariable id="to" type="java.lang.Integer"--%>
<%--@elvariable id="currentPage" type="java.lang.Integer"--%>
<%--@elvariable id="totalPages" type="java.lang.Integer"--%>
<div class="container">

    <div class="page-header">
        <div>

            <h2>مدیریت اطلاعات اشخاص</h2>

            <p class="page-description">
                مدیریت اطلاعات اشخاص
            </p>

        </div>
        <a class="add-button"
           href="${pageContext.request.contextPath}/person?action=savePerson">

        <span class="button-icon">
            +
        </span>
            افزودن شخص
        </a>

    </div>


    <c:if test="${param.message != null}">

        <div class="message success-message">

        <span class="message-icon">
            ✓
        </span>

            <span>
            اطلاعات شخص مورد نظر شما با موفقیت ثبت یا تغییر کرد.
        </span>

        </div>

    </c:if>


    <c:if test="${param.deleted != null}">

        <div class="message delete-message">

        <span class="message-icon">
            ✓
        </span>

            <span>
           اطلاعات شخصئ مورد نظر با موفقیت حذف شد.
        </span>

        </div>

    </c:if>


    <c:if test="${param.notExist != null}">

        <div class="message error-message">

        <span class="message-icon">
            !
        </span>

            <span>
            اطلاعات شخص مورد نظر یافت نشد.
        </span>

        </div>

    </c:if>

    <div class="table-card">

        <div class="table-header">

            <div>

                <h3>
                    لیست اشخاص
                </h3>

                <c:if test="${not empty persons}">

                <span class="record-count">

                    تعداد افراد:

                    <strong>
                            ${totalElements}
                    </strong>

                </span>

                </c:if>

            </div>

            <c:if test="${not empty persons}">

                <div class="page-size-container">

                    <label for="pageSize">

                        سطر در هر صفحه :

                    </label>


                    <form method="get"
                          action="${pageContext.request.contextPath}/person">

                        <input type="hidden"
                               name="action"
                               value="listPersons">


                        <select id="pageSize"
                                name="size"
                                onchange="this.form.submit()">

                            <option value="5"
                                ${pageSize == 5 ? 'selected' : ''}>
                                5
                            </option>

                            <option value="10"
                                ${pageSize == 10 ? 'selected' : ''}>
                                10
                            </option>

                            <option value="20"
                                ${pageSize == 20 ? 'selected' : ''}>
                                20
                            </option>

                            <option value="50"
                                ${pageSize == 50 ? 'selected' : ''}>
                                50
                            </option>

                        </select>

                    </form>

                </div>

            </c:if>

        </div>


        <c:choose>


            <c:when test="${not empty persons}">

                <div class="table-wrapper">


                    <table id="personTable" dir="rtl">

                        <thead>

                        <tr>

                            <th>نام</th>

                            <th>نام خانوادگی</th>

                            <th>کد ملی</th>

                            <th>سن</th>

                            <th>شماره موبایل</th>

                            <th>ایمیل</th>

                            <th>تاریخ ایجاد</th>

                            <th>تاریخ ویرایش</th>

                            <th>عملیات</th>

                        </tr>

                        </thead>

                        <tbody>

                        <c:forEach var="person"
                                   items="${persons}">

                            <tr>


                                <td>
                                        ${person.firstName}
                                </td>


                                <td>
                                        ${person.lastName}
                                </td>


                                <td>

                                <span class="national-code">

                                        ${person.nationalCode}

                                </span>

                                </td>


                                <td>
                                        ${person.age}
                                </td>


                                <td>
                                          <span class="mobile-number">
                                                  ${person.mobile}
                                          </span>
                                </td>


                                <td>
                                          <span class="email">
                                                  ${person.email}
                                          </span>
                                </td>


                                <td>
                                        ${person.createdAtPersian}
                                </td>


                                <td>
                                        ${person.updatedAtPersian}
                                </td>

                                <td>

                                    <div class="actions">

                                        <a class="action-button view-button"
                                           href="${pageContext.request.contextPath}/person?action=view&id=${person.id}">
                                            مشاهده
                                        </a>

                                        <a class="action-button update-button"
                                           href="${pageContext.request.contextPath}/person?action=updatePerson&id=${person.id}">
                                            ویرایش
                                        </a>

                                        <a class="action-button delete-button"
                                           href="${pageContext.request.contextPath}/person?action=delete&id=${person.id}"
                                           onclick="return confirmDelete();">
                                            حذف
                                        </a>
                                    </div>
                                </td>

                            </tr>

                        </c:forEach>

                        </tbody>

                    </table>

                </div>

                <div class="pagination-container">

                    <div class="pagination-info">

                        نمایش
                        <strong>${from}</strong>
                        تا
                        <strong>${to}</strong>
                        از
                        <strong>${totalElements}</strong>
                        رکورد

                    </div>


                    <div class="pagination">

                        <c:if test="${currentPage > 1}">

                            <a class="page-button"
                               href="${pageContext.request.contextPath}/person?action=listPersons&page=${currentPage - 1}&size=${pageSize}">
                                ‹
                            </a>

                        </c:if>

                        <a class="page-button ${currentPage == 1 ? 'active' : ''}"
                           href="${pageContext.request.contextPath}/person?action=listPersons&page=1&size=${pageSize}">
                            1
                        </a>

                        <c:if test="${currentPage > 4}">

            <span class="pagination-dots">
                ...
            </span>

                        </c:if>

                        <c:choose>

                            <c:when test="${currentPage <= 4}">

                                <c:forEach
                                        begin="2"
                                        end="${totalPages < 5 ? totalPages - 1 : 4}"
                                        var="pageNumber">

                                    <a
                                            class="page-button ${pageNumber == currentPage ? 'active' : ''}"
                                            href="${pageContext.request.contextPath}/person?action=listPersons&page=${pageNumber}&size=${pageSize}">

                                            ${pageNumber}

                                    </a>

                                </c:forEach>

                            </c:when>

                            <c:when test="${currentPage >= totalPages - 3}">

                                <c:forEach
                                        begin="${totalPages - 3}"
                                        end="${totalPages - 1}"
                                        var="pageNumber">

                                    <a
                                            class="page-button ${pageNumber == currentPage ? 'active' : ''}"
                                            href="${pageContext.request.contextPath}/person?action=listPersons&page=${pageNumber}&size=${pageSize}">

                                            ${pageNumber}

                                    </a>

                                </c:forEach>

                            </c:when>

                            <c:otherwise>

                                <a
                                        class="page-button"
                                        href="${pageContext.request.contextPath}/person?action=listPersons&page=${currentPage - 1}&size=${pageSize}">

                                        ${currentPage - 1}

                                </a>


                                <a
                                        class="page-button active"
                                        href="${pageContext.request.contextPath}/person?action=listPersons&page=${currentPage}&size=${pageSize}">

                                        ${currentPage}

                                </a>


                                <a
                                        class="page-button"
                                        href="${pageContext.request.contextPath}/person?action=listPersons&page=${currentPage + 1}&size=${pageSize}">

                                        ${currentPage + 1}

                                </a>

                            </c:otherwise>

                        </c:choose>

                        <c:if test="${currentPage < totalPages - 3}">

            <span class="pagination-dots">
                ...
            </span>

                        </c:if>

                        <c:if test="${totalPages > 1}">

                            <a
                                    class="page-button ${currentPage == totalPages ? 'active' : ''}"
                                    href="${pageContext.request.contextPath}/person?action=listPersons&page=${totalPages}&size=${pageSize}">

                                    ${totalPages}

                            </a>

                        </c:if>

                        <c:if test="${currentPage < totalPages}">

                            <a class="page-button"
                               href="${pageContext.request.contextPath}/person?action=listPersons&page=${currentPage + 1}&size=${pageSize}">

                                ›
                            </a>

                        </c:if>

                    </div>

                </div>

            </c:when>

            <c:otherwise>


                <div class="empty-message">


                    <div class="empty-icon">
                        ☺
                    </div>


                    <h3>
                        شخصی یافت نشد
                    </h3>


                    <p>
                        شخصی برای نمایش وجود ندارد
                    </p>


                    <a class="add-button"
                       href="${pageContext.request.contextPath}/person?action=savePerson">

                        + افزدن شخص جدید

                    </a>


                </div>


            </c:otherwise>


        </c:choose>


    </div>


</div>

<script>

    function confirmDelete() {

        return confirm(
            "Are you sure you want to delete this person?"
        );

    }

</script>

</body>

</html>
