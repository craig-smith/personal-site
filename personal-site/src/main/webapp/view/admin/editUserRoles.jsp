<%--
  Created by IntelliJ IDEA.
  User: Craig
  Date: 3/13/2016
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html>
<head>
    <title>Edit Roles: ${user.userName}</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
    <script src="<spring:url value="/resources/scripts/jquery.js" /> " type="javascript"></script>
</head>

<body>
<%@include file="../links.jsp" %>
<div class="content">
  <section>
    <h2>Edit Roles: ${user.userName}</h2>
    <form:form action="/admin/editUserRoles" method="post" modelAttribute="roles">
      <input type="hidden" value="${user.userName}"/>
      <c:forEach var="role" items="${availableRoles}" varStatus="status">
       <div class="availableRoles">Role: ${role.role} <form:checkbox path="availableRoles[${status.index}].role" name="${role.role}" id="${role}" value="${role.role}"/></div>
      </c:forEach>
      <input type="submit" value="submit"/>
    </form:form>
  </section>
  <section>
    <h2>Current Roles</h2>
    <c:forEach var="role" items="${user.userRoll}">
      <div class="userRoll" name="${role.role}"> <c:out value="${role.role}"></c:out></div>
    </c:forEach>
  </section>
  <%@include file="../errorMessage.jsp"%>
</div>
<!--<script type="javascript">
  var userRoles = $(".userRoll");
  var availableRoles = $(".availableRoles");
  for(i = 0; i < userRoles.length; i++){
    for(j = 0; j < availableRoles.length; j++){
      if(availableRoles[j].prop('id') == userRoles[i].prop('name')){

      }
    }
  }
</script>-->
</body>
</html>
