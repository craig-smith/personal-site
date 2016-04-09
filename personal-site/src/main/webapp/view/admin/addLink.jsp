<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 2/24/2016
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Link</title>
  <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />" >
</head>
<body>
<%@include file="../links.jsp"%>
<div class="content">
  <h2>Add New Link</h2>
  <form:form action="/admin/addLink" method="post">
  <input type="hidden" name="id" >
  <div class="data-entry"> <p class="data-entry-text">Url:</p><input type="text" name="url" class="data-entry-input" ></div>
  <div class="data-entry"> <p class="data-entry-text">Name:</p> <input class="data-entry-input" type="text" name="name" ></div>
  <div class="data-entry"><p class="data-entry-text">Acl:</p> <input class="data-entry-input" type="text" name="acl" ></div>
  <div class="data-entry"> <p class="data-entry-text">Description:</p> <input class="data-entry-input" type="text" name="description" ></div>
  <div class="data-entry"> <input type="submit" value="save"></div>
  </form:form>
</div>
</body>
</html>
