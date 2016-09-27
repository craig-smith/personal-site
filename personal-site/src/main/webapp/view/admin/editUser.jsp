<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 3/13/2016
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
    <title>Edit ${user.userName}</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
</head>
<body>
<%@include file="../links.jsp" %>
<div class="content">
  <form:form action="/admin/editUser" method="post" modelAttribute="user">
    <section>
      <h2>Edit Account: ${user.userName}</h2>
      <div class="data-entry">
        <p class="data-entry-text">Username: ${user.userName}</p>
        <form:input path="userName" type="hidden" value="${user.userName}" name="username"/>
      </div>
      <div class="data-entry">
        <p class="data-entry-text">email: </p>
        <form:input path="email" class="data-entry-input" type="email" name="email" value="${user.email}"/>
      </div>
      <div class="data-entry">
        <p class="data-entry-text">enabled: </p>
        <form:checkbox path="enabled" class="data-entry-input" name="enabled" checked="${user.enabled}"/>

      </div>
      <div class="data-entry">
        <p class="data-entry-text">Login Attempts: </p>
        <form:input path="loginAttempts" class="data-entry-input" type="number" name="loginAttempts" value="${user.loginAttempts}"/>
      </div>
      <input type="submit" value="submit">
    </section>
  </form:form>
  <form:form action="/admin/deleteUser" method="post">
    <input type="hidden" name="username" value="${user.userName}">
    <input type="submit" value="Delete User">
  </form:form>
</div>
<%@include file="../errorMessage.jsp"%>
</body>
</html>
