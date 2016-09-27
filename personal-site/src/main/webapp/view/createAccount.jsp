<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 2/29/2016
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html>
<head>
    <title>Create Account</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
</head>
<body>
<%@include file="links.jsp" %>
<div class="content">
<form:form action="/createAccount" method="post">
  <section>
    <h2>Create Account</h2>
    <div class="data-entry">
        <p class="data-entry-text">username: </p>
        <input class="data-entry-input" type="text" name="username">
    </div>
    <div class="data-entry">
        <p class="data-entry-text">password: </p>
        <input class="data-entry-input" type="password" name="password">
    </div>
    <div class="data-entry">
      <p class="data-entry-text">email: </p>
      <input class="data-entry-input" type="email" name="email">
    </div>
  <input type="submit" value="submit">
  </section>
</form:form>
    <%@include file="errorMessage.jsp"%>
</div>

</body>
</html>
