<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
    <div align="center">
      <%--  <spring:url var="authUrl" value="/j_spring_security_check" />--%>
       <%--   <c:url value="/j_spring_security_check" var="loginUrl" /> --%>
        <form action="/j_spring_security_check" method="POST">
            Login:<br/><input type="text" name="j_login"><br/>
            Password:<br/><input type="password" name="j_password"><br/>
            <input type="submit" />

            <c:if test="${param.error ne null}">
                <p>Wrong login or password!</p>
            </c:if>

            <c:if test="${param.logout ne null}">
                <p>Chao!</p>
            </c:if>
        </form>

          <form action="/registration">
              </br><input type="submit" value="Registration" />
          </form>

          <c:if test="${success ne null}">
              <p> ${success}</p>
          </c:if>

    </div>
</body>
</html>


