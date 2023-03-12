<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/css/teachersmenu.css">
</head>
<body>
<div class="center">
    <p>Hello ${sessionScope.username}</p>
</div>

<div class="center">
    <div class="search-wrapper">
        <div class="bot-gap">
            <span class="title">Users Search</span>
        </div>
        <div class="bot-gap">
            <form method="POST" action="${pageContext.request.contextPath}/schoolapp/searchuser">
                <input name="email" type="email" class="search rounded" placeholder="Insert user's email"
                       autofocus/>
                <br><br>
                <button class="search-btn rounded color-btn" type="submit">Search</button>
            </form>
        </div>
    </div>

    <div class="insert-wrapper">
        <div class="bot-gap">
            <span class="title">Users Insert</span>
        </div>
        <div class="bot-gap">
            <form method="POST" action="${pageContext.request.contextPath}/schoolapp/insertUser">
                <input name="username" type="email" value="${requestScope.insertedUser.username}"
                       class="insert rounded" placeholder="email" autofocus/><br>
                <input name="password" type="password" value="${requestScope.insertedUser.password}"
                       class="insert rounded" placeholder="password" autofocus/>
                <br><br>
                <button class="search-btn rounded color-btn" type="submit">Insert</button>
            </form>
        </div>
    </div>
</div>

<div class="center">
    <c:if test="${requestScope.isError}">
        <p>${requestScope.message}</p>
    </c:if>
</div>

<div class="center">
    <c:if test="${requestScope.usersNotFound}">
        <p>No users found</p>
    </c:if>

    <p>${requestScope.error}</p>
</div>
</body>
</html>


