<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Modificar información Empleado</title>
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
                <h1>EMPLOYEE APP v2</h1>
                <h2>Ingrese el id del empleado a buscar</h2>
                <div class="formulario" style="text-align: center;">
                    <form action="ModifyEmployee" method="get">
                        <table cellspacing="3" cellpadding="3" border="1">
                            <tr>
                                <td align="right">ID Empleado:</td>
                                <td><input type="text" name="searchId"></td>
                            </tr>
                        </table>
                        <input type="submit" value="Buscar">
                    </form>
                </div>

                <c:choose>
                    <c:when test="${not empty sessionScope.employee}">
                        <h2>Empleado encontrado</h2>
                        <div class="formulario" style="text-align: center;">
                            <form action="ModifyEmployee" method="post">
                                <table cellspacing="3" cellpadding="3" border="1">
                                    <tr>
                                        <td align="right">ID Empleado:</td>
                                        <td><input type="text" name="id" value="${sessionScope.employee.id}" readonly>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Nombre Empleado:</td>
                                        <td><input type="text" name="name" value="${sessionScope.employee.name}"></td>
                                    </tr>
                                    <tr>
                                        <td align="right">Email empleado:</td>
                                        <td><input type="text" name="email" value="${sessionScope.employee.email}"></td>
                                    </tr>
                                    <tr>
                                        <td align="right">Teléfono empleado:</td>
                                        <td><input type="text" name="phone" value="${sessionScope.employee.phone}"></td>
                                    </tr>
                                </table>
                                <input type="submit" value="Modificar">
                            </form>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.oper == 'not_found'}">
                        <h2>Empleado no encontrado</h2>
                    </c:when>
                    <c:when test="${sessionScope.oper == 'success'}">
                        <h2>Empleado modificado exitosamente!</h2>
                        <c:remove var="oper" scope="session" />
                    </c:when>
                    <c:when test="${sessionScope.oper == 'error'}">
                        <h2>Error al modificar el empleado.</h2>
                        <c:remove var="oper" scope="session" />
                    </c:when>
                </c:choose>
            </div>

            <div class="footer">
                <h3>INTEGRANTES:</h3>
                <p>Juan David Ochoa Pinilla</p>
                <p>Pedro Eduardo Cruz Lopez</p>
                <p>Milton Nicolas Pirazan Forero</p>
            </div>
        </body>

        </html>