<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="edu.uptc.swi.model.Employee" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Eliminar Empleado v2</title>
            <link href="css/empStyle.css" rel="stylesheet" type="text/css">
        </head>

        <body>
            <div class="topnav">
                <a href="index.jsp">Crear empleado</a>
                <a href="modify.jsp">Modificar empleado</a>
                <a href="delete.jsp">Borrar Empleado</a>
                <a href="showAll.jsp">Mostrar Todos</a>rrar Empleado</a>
            </div>

            <div class="content">
                <h1>EMPLOYEE APP</h1>
                <h2>Ingrese el ID del empleado a eliminar (Con MySQL) V2</h2>
                <div class="formulario" style="text-align: center;">
                    <form action="DeleteEmployee" method="get">
                        <table cellspacing="3" cellpadding="3" border="1">
                            <tr>
                                <td align="right">ID Empleado:</td>
                                <td><input type="text" name="searchId"></td>
                            </tr>
                        </table>
                        <input type="submit" value="Buscar">
                    </form>
                </div>

                <% Employee employee=(Employee) request.getSession().getAttribute("employee"); String oper=(String)
                    request.getSession().getAttribute("oper"); if ("found".equals(oper) && employee !=null) { %>
                    <h2>Empleado encontrado</h2>
                    <div class="formulario" style="text-align: center;">
                        <form action="DeleteEmployee" method="post">
                            <table cellspacing="3" cellpadding="3" border="1">
                                <tr>
                                    <td align="right">ID Empleado:</td>
                                    <td><input type="text" name="id" value="<%= employee.getId() %>" readonly></td>
                                </tr>
                                <tr>
                                    <td align="right">Eliminar al Empleado:</td>
                                    <td><input type="text" name="name" value="<%=employee.getName()%>" readonly></td>
                                </tr>
                            </table>
                            <input type="submit" value="Eliminar">
                        </form>
                    </div>
                    <% } else if ("not_found".equals(oper)) { %>
                        <h2>Empleado no encontrado</h2>
                        <% } else if ("deleted".equals(oper)) { %>
                            <h2>Empleado eliminado exitosamente!</h2>
                            <% request.getSession().removeAttribute("oper"); %>
                                <% } else if ("error".equals(oper)) { %>
                                    <h2>Error al eliminar el empleado.</h2>
                                    <% request.getSession().removeAttribute("oper"); %>
                                        <% } %>

            </div>

            <div class="footer">
                <h3>INTEGRANTES:</h3>
                <p>Juan David Ochoa Pinilla</p>
                <p>Pedro Eduardo Cruz Lopez</p>
                <p>Milton Nicolas Pirazan Forero</p>
            </div>
        </body>

        </html>