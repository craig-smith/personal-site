<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: craig
  Date: 2/25/17
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
    <title>Activate</title>
</head>
<body>
<%@include file="links.jsp" %>
<div class="content">
  <section>
    <c:if test="${valid}">
      <p>Thank you for activating your account ${user.userName}</p>
    </c:if>
  </section>
</div>

<%@include file="errorMessage.jsp"%>
</body>
</html>
