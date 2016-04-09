<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 3/13/2016
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
</head>
<body>
<%@include file="../links.jsp" %>
<div class="content">
  <h2>User List</h2>
  <c:forEach var="user" items="${userList}">
    <section>
      <div>Username: ${user.userName}</div>
      <div>Email: ${user.email}</div>
      <div>Enabled: ${user.enabled}</div>
      <div>Login Attempts: ${user.loginAttempts}</div>
      <a href="/admin/editUser?username=${user.userName}">Edit User</a> ||
      <a href="/admin/editUserRoles?username=${user.userName}">Edit User Roles</a>
    </section>
  </c:forEach>
</div>
</body>
</html>
