<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Update</title>
</head>
<body>
<div>
  <form method="POST" action="${pageContext.request.contextPath}/schoolapp/updateuser">
    <label for="tid">Κωδικός</label>
    <input id="tid" type="text" name="id" value="${param.id}" readonly><br>
    <label for="firstname">username</label>
    <input id="firstname" type="email" name="username" value="${param.username}"><br>
    <label for="lastname">Επώνυμο</label>
    <input id="lastname" type="password" name="password" value="${param.password}"><br><br>
    <button type="submit">Update user</button>
  </form>
</div>

<c:if test="${requestScope.isError}">
  <p>${requestScope.message}</p>
</c:if>
</body>
</html>
