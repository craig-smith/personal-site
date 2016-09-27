<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html>
<head>
  <title>Update Account</title>
  <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
</head>
<body>
<%@include file="../links.jsp" %>
<div class="content">
  <form:form action="/user/updateAccount" method="post">
    <section>
      <h2>Update Account</h2>
      <div class="data-entry">
        <p class="data-entry-text">Username: ${user.userName}</p>
        <input type="hidden" value="${user.userName}" name="username">
      </div>
      <div class="data-entry">
        <p class="data-entry-text">password: (leave blank if you don't want to change) </p>
        <input class="data-entry-input" type="password" name="password">
      </div>
      <div class="data-entry">
        <p class="data-entry-text">Confirm Password: </p>
        <input class="data-entry-input" type="password" name="passwordConfirm">
      </div>
      <div class="data-entry">
        <p class="data-entry-text">email: </p>
        <input class="data-entry-input" type="email" name="email" value="${user.email}">
      </div>
      <input type="submit" value="submit">
    </section>
  </form:form>
 <%@include file="../errorMessage.jsp"%>
</div>
</body>
</html>