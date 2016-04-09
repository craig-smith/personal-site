<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 2/24/2016
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="user-links">
  <h3 style="text-align: center">Links</h3>
  <c:forEach var="link" items="${links}">
      <div>
      <a class="link" href="${link.url}" description="${link.description}">${link.name}</a>
      </div>
  </c:forEach>
</div>
</body>
</html>
