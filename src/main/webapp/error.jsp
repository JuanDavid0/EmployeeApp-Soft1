<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <h1>Ocurrió un error</h1>
    <p>${sessionScope.error}</p>
    <a href="index.jsp">Volver al inicio</a>
    <c:remove var="error" scope="session" />
</body>
</html>
