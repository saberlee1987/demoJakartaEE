<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fa" dir="rtl">

<head>

    <meta charset="UTF-8">
    <meta name="viewport"   content="width=device-width, initial-scale=1.0">

    <title>افزودن شخص</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/application.css">
</head>
<body>
<%--@elvariable id="personRequest" type="com.saber.demojavaee.dto.PersonRequestDto"--%>
<%--@elvariable id="errors" type="java.util.List<java.lang.String>"--%>
<div class="page-container">


    <div class="page-header">

        <div>

            <h1>
                افزودن شخص جدید
            </h1>

            <p>
                اطلاعات شخص جدید را وارد کنید
            </p>

        </div>

        <a class="back-button"
           href="${pageContext.request.contextPath}/person">

            بازگشت به لیست

        </a>

    </div>


    <div class="form-card">

        <div class="form-card-header">

            <h2>
                اطلاعات شخص
            </h2>

            <p>
                لطفاً تمام اطلاعات مورد نیاز را وارد کنید.
            </p>

        </div>


        <c:if test="${not empty errors}">

            <div class="validation-errors">

                <div class="validation-title">
                    خطا در اطلاعات وارد شده
                </div>

                <ul>

                    <c:forEach items="${errors}"
                               var="error">

                        <li>
                                ${error}
                        </li>

                    </c:forEach>

                </ul>

            </div>

        </c:if>


        <c:if test="${personRequest != null}">

            <form action="${pageContext.request.contextPath}/person"
                  method="post">

                <input type="hidden"
                       name="action"
                       value="savePerson">


                <div class="form-grid">


                    <div class="form-group">

                        <label for="firstName">
                            نام
                        </label>

                        <input type="text"
                               id="firstName"
                               name="firstName"
                               value="${personRequest.firstName}"
                               placeholder="نام را وارد کنید"
                               autocomplete="given-name">

                    </div>


                    <div class="form-group">

                        <label for="lastName">
                            نام خانوادگی
                        </label>

                        <input type="text"
                               id="lastName"
                               name="lastName"
                               value="${personRequest.lastName}"
                               placeholder="نام خانوادگی را وارد کنید"
                               autocomplete="family-name">

                    </div>


                    <div class="form-group">

                        <label for="age">
                            سن
                        </label>

                        <input type="text"
                               id="age"
                               name="age"
                               value="${personRequest.age}"
                               placeholder="سن را وارد کنید"
                               inputmode="numeric">

                    </div>


                    <div class="form-group">

                        <label for="nationalCode">
                            کد ملی
                        </label>

                        <input type="text"
                               id="nationalCode"
                               name="nationalCode"
                               value="${personRequest.nationalCode}"
                               placeholder="کد ملی را وارد کنید"
                               maxlength="10"
                               inputmode="numeric"
                               class="ltr-input">

                    </div>


                    <div class="form-group">

                        <label for="mobile">
                            شماره موبایل
                        </label>

                        <input type="text"
                               id="mobile"
                               name="mobile"
                               value="${personRequest.mobile}"
                               placeholder="09xxxxxxxxx"
                               maxlength="11"
                               inputmode="tel"
                               class="ltr-input">

                    </div>


                    <div class="form-group">

                        <label for="email">
                            ایمیل
                        </label>

                        <input type="email"
                               id="email"
                               name="email"
                               value="${personRequest.email}"
                               placeholder="example@gmail.com"
                               autocomplete="email"
                               class="ltr-input">

                    </div>

                </div>


                <div class="form-actions">

                    <button type="submit"
                            class="save-button">

                        افزودن شخص

                    </button>


                    <a class="cancel-button"
                       href="${pageContext.request.contextPath}/person">

                        انصراف

                    </a>

                </div>

            </form>

        </c:if>

    </div>
    

</div>

</body>

</html>
