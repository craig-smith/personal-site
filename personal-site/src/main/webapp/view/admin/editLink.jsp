<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 2/24/2016
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit ${link.name}</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
</head>
<body>
<%@include file="../links.jsp" %>
<h2>Edit ${link.name} Link</h2>
<div class="content">
    <form:form action="/admin/editLink" method="post">
        <input type="hidden" name="id" value="${link.id}">
    <section>
        <div class="data-entry">
            <p class="data-entry-text">URL:</p>
            <input class="data-entry-input" type="text" name="url" value="${link.url}">
        </div>
        <div class="data-entry">
            <p class="data-entry-text">Name:</p>
            <input class="data-entry-input" type="text" name="name" value="${link.name}">
        </div>
        <div class="data-entry">
            <p class="data-entry-text">Acl:</p>
            <input class="data-entry-input" type="text" name="acl" value="${link.acl}">
        </div>
        <div class="data-entry">
            <p class="data-entry-text">Description:</p>
            <input class="data-entry-input" type="text" name="description" value="${link.description}">
        </div>
        <div class="data-entry"><input type="submit" value="save"></div>
    </section>
    </form:form>

    <form:form action="/admin/deleteLink" method="post">
        <section>
        <input type="hidden" name="id" value="${link.id}">
        <div class="data-entry"><input type="submit" value="delete"></div>
        </section>
    </form:form>

</div><!-- end content div-->

</body>
</html>
