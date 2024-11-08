<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Formulario Empleado</title>
    <link href="css/empStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="topnav">
        <a href="index.jsp">Crear empleado</a>
        <a href="modify.jsp">Modificar empleado</a>
        <a href="delete.jsp">Borrar Empleado</a>
        <a href="showAll.jsp">Mostrar Todos</a>
    </div>
    
    <div class="content">
        <h1>EMPLOYEE APP-2</h1>
        <h2>Por favor ingrese los datos del empleado . Prueba</h2>
        <div class="formulario" style="text-align: center;">
            <form action="AddEmployee" method="post">
                <table cellspacing="3" cellpadding="3" border="1">
                    <tr>
                        <td align="right">ID Empleado:</td>
                        <td><input type="text" name="id"></td>
                    </tr>
                    <tr>
                        <td align="right">Nombre Empleado:</td>
                        <td><input type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td align="right">Email empleado:</td>
                        <td><input type="text" name="email"></td>
                    </tr>
                    <tr>
                        <td align="right">Teléfono empleado:</td>
                        <td><input type="text" name="phone"></td>
                    </tr>
                </table>
                <input type="submit" value="Enviar">
            </form>

            <c:if test="${sessionScope.oper == 'success'}">
                <h4>Empleado adicionado exitosamente!</h4>
                <c:remove var="oper" scope="session" />
            </c:if>
        </div>
    </div>

    <div class="footer">
        <h3>INTEGRANTES:</h3>
        <p>Juan David Ochoa Pinilla</p>
        <p>Pedro Eduardo Cruz Lopez</p>
        <p>Milton Nicolas Pirazan Forero</p>
    </div>
</body>
</html>
