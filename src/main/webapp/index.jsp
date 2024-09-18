<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Formulario Empleado</title>
        <link href="css/empStyle.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <div class="topnav">
            <a href="index.jsp">Crear empleado</a>
            <a href="modify.jsp">Modificar empleado</a>
            <a href="delete.jsp">Borrar Empleado</a>
        </div>
        <div class="content">
            <h1> EMPLOYEE APP</h1>
            <h2> Hola, por favor ingrese los datos del usuario </h2>
            <div class="formulario" style="text-align: center;"></div>
            <form action="AddEmployee" method="post">
                <table cellspacing="3" cellpadding="3" border="1">
                    <tr>
                        <td align="right"> ID Empleado: </td>
                        <td><input type="text" name="id"></td>
                    </tr>
                    <tr>
                        <td align="right"> Nombre Empleado: </td>
                        <td> <input type="text" name="name"> </td>
                    </tr>
                    <tr>
                        <td align="right"> Email empleado: </td>
                        <td> <input type="text" name="email"> </td>
                    </tr>
                    <tr>
                        <td align="right"> Telefono empleado: </td>
                        <td> <input type="text" name="phone"> </td>
                    </tr>
                </table>
                <input type="submit" value="Enviar">
            </form>
            <% String oper=String.valueOf(request.getSession().getAttribute("oper")); if("success".equals(oper)){%>
                <h4> Empleado adicionado exitosamente! </h4>
                <%}%>
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