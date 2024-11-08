<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Mostrar Todos los Empleados</title>
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
                <h1>Lista de Empleados V3</h1>

                <form action="ShowAllEmployee" method="get">
                    <input type="submit" value="Actualizar Lista">
                </form>

                <c:choose>
                    <c:when test="${not empty sessionScope.employeeList}">
                        <table border="1" cellspacing="3" cellpadding="3">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Email</th>
                                <th>Teléfono</th>
                            </tr>
                            <c:forEach var="employee" items="${sessionScope.employeeList}">
                                <tr>
                                    <td>${employee.id}</td>
                                    <td>${employee.name}</td>
                                    <td>${employee.email}</td>
                                    <td>${employee.phone}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:remove var="employeeList" scope="session" />
                    </c:when>
                    <c:otherwise>
                        <h2>No hay empleados para mostrar</h2>
                    </c:otherwise>
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