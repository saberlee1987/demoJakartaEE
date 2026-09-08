<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>

  <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>مشاهده اطلاعات شخص</title>

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/application.css">

</head>

<body>
<%--@elvariable id="person" type="com.saber.demojavaee.models.Person"--%>

<div class="page-container">

  <c:choose>

    <c:when test="${person != null}">

      <div class="page-header">

        <div>

          <h1>
            اطلاعات شخص
          </h1>

          <p>
            مشاهده جزئیات اطلاعات شخص
          </p>

        </div>

        <a class="back-button"
           href="${pageContext.request.contextPath}/person">

          ←
          بازگشت به لیست

        </a>

      </div>


      <div class="person-card">

        <div class="card-header">

          <div class="avatar">

              ${person.firstName.substring(0, 1)}

          </div>

          <div class="person-title">

            <h2>

                ${person.firstName}
                ${person.lastName}

            </h2>

            <span>

                        کد ملی:
                        ${person.nationalCode}

                    </span>

          </div>

        </div>


        <div class="information-grid">


          <div class="information-item">

                    <span class="information-label">
                        نام
                    </span>

            <span class="information-value">

                ${person.firstName}

            </span>

          </div>


          <div class="information-item">

                    <span class="information-label">
                        نام خانوادگی
                    </span>

            <span class="information-value">

                ${person.lastName}

            </span>

          </div>


          <div class="information-item">

                    <span class="information-label">
                        سن
                    </span>

            <span class="information-value">

                        ${person.age}

                        <span class="unit">
                            سال
                        </span>

                    </span>

          </div>


          <div class="information-item">

                    <span class="information-label">
                        کد ملی
                    </span>

            <span class="information-value ltr">

                ${person.nationalCode}

            </span>

          </div>


          <div class="information-item">

                    <span class="information-label">
                        شماره موبایل
                    </span>

            <span class="information-value ltr">

                ${person.mobile}

            </span>

          </div>


          <div class="information-item">

                    <span class="information-label">
                        ایمیل
                    </span>

            <span class="information-value ltr">

                ${person.email}

            </span>

          </div>


          <div class="information-item">

                    <span class="information-label">
                        تاریخ ایجاد
                    </span>

            <span class="information-value">

                ${person.createdAtPersian}

            </span>

          </div>


          <div class="information-item">

                    <span class="information-label">
                        آخرین ویرایش
                    </span>

            <span class="information-value">

                ${person.updatedAtPersian}

            </span>

          </div>

        </div>


        <div class="card-footer">

          <a class="edit-button"
             href="${pageContext.request.contextPath}/person?action=updatePerson&id=${person.id}">

            ویرایش اطلاعات

          </a>


          <a class="delete-button"
             href="${pageContext.request.contextPath}/person?action=delete&id=${person.id}"
             onclick="return confirm('آیا از حذف این شخص اطمینان دارید؟');">

            حذف شخص

          </a>

        </div>

      </div>

    </c:when>


    <c:otherwise>

      <div class="not-found-card">

        <div class="not-found-icon">
          !
        </div>

        <h2>
          شخص مورد نظر پیدا نشد
        </h2>

        <p>
          اطلاعات شخص مورد نظر در پایگاه داده وجود ندارد.
        </p>

        <a class="back-button"
           href="${pageContext.request.contextPath}/person">

          بازگشت به لیست اشخاص

        </a>

      </div>

    </c:otherwise>

  </c:choose>
  

</div>

</body>

</html>
