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
<html>
<head>
    <title>Edit Roles: ${user.userName}</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/main.css" />">
  <script src="<spring:url value="/resources/scripts/jquery.js"/> " type="javascript"/>
</head>
<%@include file="../links.jsp" %>
<body>
<div class="content">
  <section>
    <h2>Edit Roles: ${user.userName}</h2>
    <form:form action="admin/updateUserRoles" method="post" modelAttribute="roles">
      <c:forEach var="role" items="${availableRoles}">
       <div class="availableRoles">Role: ${role.role} <form:checkbox path="role" name="${role.role}" id="${role}"/></div>
      </c:forEach>
    </form:form>
  </section>
  <section>
    <h2>Current Roles</h2>
    <c:forEach var="role" items="${user.userRoll}">
      <div class="userRoll" name="${role}"> <c:out value="${role}"></c:out></div>
    </c:forEach>
  </section>
  <%@include file="../errorMessage.jsp"%>
</div>
<script type="javascript">
  var userRoles = $(".userRoll");
  var availableRoles = $(".availableRoles");
  for(i = 0; i < userRoles.length; i++){
    for(j = 0; j < availableRoles.length; j++){
      if(availableRoles[j].prop('id') == userRoles[i].prop('name')){

      }
    }
  }
</script>
</body>
</html>
