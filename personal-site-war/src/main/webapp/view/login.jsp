<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 2/29/2016
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
  <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
</head>
<body>
<%@include file="links.jsp" %>
<div class="content">
<section>
  <h1>Login</h1>
<form:form action="/login" method="post">
  <div class="data-entry">
    <p class="data-entry-text">username: </p>
    <input class="data-entry-input" type="text" name="username">
  </div>
  <div class="data-entry">
    <p class="data-entry-text">Password: </p>
    <input class="data-entry-input" type="password" name="password">
  </div>

  <input type="submit" value="submit">
</form:form>
</section>
</div>
</body>
</html>
