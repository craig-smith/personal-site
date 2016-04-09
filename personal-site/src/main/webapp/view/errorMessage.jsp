<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 3/12/2016
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if  test="${not empty errorMsg}">
  <h2>Oops! There was a problem.</h2>
  <c:forEach var="errorMessage" items="${errorMsg}">
    <p>${errorMessage.errorType}: ${errorMessage.errorMessage}</p>
  </c:forEach>
</c:if>
</body>
</html>
