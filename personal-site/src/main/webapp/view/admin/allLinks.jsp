<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 2/24/2016
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View All Links</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />" >
</head>
<body>
<%@include file="../links.jsp"%>
<h2>All Links</h2>
<div class="content">
    <C:forEach var="link" items="${allLinks}">
        <section>
              <div class="link-element"><p>Url:</p> <span>${link.url}</span></div>
              <div class="link-element"><p>Name:</p> <span>${link.name}</span></div>
              <div class="link-element"><p>Acl:</p> <span>${link.acl}</span></div>
              <div class="link-element"><p>Description:</p> <span>${link.description}</span></div>
              <div class="link-element"><a class="link" description="edit ${link.url}" href="/admin/editLink?id=${link.id}">edit ${link.name}</a></div>
        </section>
    </C:forEach>
    <a class="link" description="add link to list" href="/admin/addLink">add link</a>
</div>
</body>
</html>
